package org.jeecgframework.workflow.controller.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 与流程实例相关的功能在这里
 * @author liujinghua
 *
 */
@Controller
@RequestMapping("/processInstanceController")
public class ProcessInstanceController {
	
	@Autowired
	protected RepositoryService repositoryService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * easyui 运行中流程列表页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "runningProcessList")
	public ModelAndView runningProcessList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		return new ModelAndView("workflow/activiti/runninglist");
	}

	/**
	 * 我发起的流程列表数据
	 * @return
	 */
	@RequestMapping(params = "myRunningProcessListDataGrid")
	public void myRunningProcessListDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String currentUserName = ResourceUtil.getSessionUserName().getUserName();
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery().startedBy(currentUserName);
	    List<HistoricProcessInstance> list = historicProcessInstanceQuery.orderByProcessInstanceEndTime().asc().listPage((dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());

	    StringBuffer rows = new StringBuffer();
		for(HistoricProcessInstance hi : list){
			String starttime = DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
			String endtime = hi.getEndTime()==null?"":DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
			
			long totalTimes = hi.getEndTime()==null?(Calendar.getInstance().getTimeInMillis()-hi.getStartTime().getTime()):(hi.getEndTime().getTime()-hi.getStartTime().getTime());
			
			long dayCount = totalTimes /(1000*60*60*24);//计算天
			long restTimes = totalTimes %(1000*60*60*24);//剩下的时间用于计于小时
			long hourCount = restTimes/(1000*60*60);//小时
			restTimes = restTimes % (1000*60*60);
			long minuteCount = restTimes / (1000*60);
			
			String spendTimes = dayCount+"天"+hourCount+"小时"+minuteCount+"分";
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(hi.getProcessDefinitionId()).singleResult();
			rows.append("{'id':"+hi.getId()+",'prcocessDefinitionName':'"+StringUtils.trimToEmpty(processDefinition.getName())+"','startUserId':'"+hi.getStartUserId()+"','starttime':'"+starttime+"','endtime':'"+endtime+"','spendTimes':'"+spendTimes+"','processDefinitionId':'"+hi.getProcessDefinitionId() +"','processInstanceId':'"+hi.getId()+"'},");
		}
		
		
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+historicProcessInstanceQuery.count()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	

	/** 
     * 我发起的流程，作废按钮
     * 流程追回：删除流程实例，同时删除启动表单，
     * @param processInstanceId 流程部署ID
     */
	@RequestMapping(params = "invalidProcess")
	@ResponseBody
	public AjaxJson invalidProcess(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		runtimeService.deleteProcessInstance(processInstanceId, "发起人流程作废");
		String message = "作废流程成功";
		j.setMsg(message);
		return j;
	}
	
	/**
     * 我发起的流程，流程追回按钮
     * 流程追回：删除流程实例，启动表单单据保留，
     * @param processInstanceId 流程部署ID
     */
	@RequestMapping(params = "callBackProcess")
	@ResponseBody
	public AjaxJson callBackProcess(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		
		runtimeService.deleteProcessInstance(processInstanceId, "发起人流程追回");		
		String message = "流程追回成功";
		j.setMsg(message);
		return j;
	}

	
	/**
	 * easyui 委派页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "reassignInit")
	public ModelAndView reassignInit(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String processInstanceId = request.getParameter("processInstanceId");
		request.setAttribute("processInstanceId", processInstanceId);
		return new ModelAndView("workflow/activiti/reassignInit");
	}
	
	/**
     * 进行委派操作
     * @param processInstanceId 流程部署ID
     */
	@RequestMapping(params = "reassign")
	@ResponseBody
	public AjaxJson reassign(@RequestParam("processInstanceId") String processInstanceId, @RequestParam("userName") String assignUserId,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		//runtimeService.suspendProcessInstanceById(processInstanceId);
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
		
		String currentUserName = ResourceUtil.getSessionUserName().getUserName();
		taskService.setOwner(task.getId(), currentUserName);
		taskService.setAssignee(task.getId(), assignUserId);
		
		String message = "委派成功";
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 委派人选择页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "reassignUsers")
	public String reassignUsers() {
		return "workflow/activiti/reassignUsers";
	}
	
	/**
	 * 委派人列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridUsers")
	public void datagridUsers(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		this.userService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * easyui 运行中流程列表数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "runningProcessDataGrid")
	public void runningProcessDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		/*List<HistoricProcessInstance> historicProcessInstances = historyService
                .createHistoricProcessInstanceQuery()i
                .unfinished().list();*/
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		
		if(StringUtils.isNotEmpty(request.getParameter("startUserId"))){
			historicProcessInstanceQuery = historicProcessInstanceQuery.startedBy(request.getParameter("startUserId"));
		}
		if(StringUtils.isNotEmpty(request.getParameter("processInstanceId"))){
			historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceId(request.getParameter("processInstanceId"));
		}
		
		String starttime_begin = request.getParameter("starttime_begin");//时间开始
		String starttime_end = request.getParameter("starttime_end");//时间结束
		if(StringUtils.isNotEmpty(starttime_begin)){
			try {
				historicProcessInstanceQuery.startedAfter(DateUtils.parseDate(starttime_begin, "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(starttime_end)){
			try {
				historicProcessInstanceQuery.startedBefore(DateUtils.parseDate(starttime_end, "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String endtime_begin = request.getParameter("endtime_begin");//时间开始
		String endtime_end = request.getParameter("endtime_end");//时间结束
		if(StringUtils.isNotEmpty(endtime_begin)){
			try {
				historicProcessInstanceQuery.finishedAfter(DateUtils.parseDate(endtime_begin, "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(endtime_end)){
			try {
				historicProcessInstanceQuery.finishedBefore(DateUtils.parseDate(endtime_end, "yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		 
	    List<HistoricProcessInstance> list = historicProcessInstanceQuery.orderByProcessInstanceEndTime().asc().listPage((dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
		
		StringBuffer rows = new StringBuffer();
		for(HistoricProcessInstance hi : list){
			String starttime = DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
			String endtime = hi.getEndTime()==null?"":DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
			
			
			long totalTimes = hi.getEndTime()==null?(Calendar.getInstance().getTimeInMillis()-hi.getStartTime().getTime()):(hi.getEndTime().getTime()-hi.getStartTime().getTime());
			
			long dayCount = totalTimes /(1000*60*60*24);//计算天
			long restTimes = totalTimes %(1000*60*60*24);//剩下的时间用于计于小时
			long hourCount = restTimes/(1000*60*60);//小时
			restTimes = restTimes % (1000*60*60);
			long minuteCount = restTimes / (1000*60);
			
			String spendTimes = dayCount+"天"+hourCount+"小时"+minuteCount+"分";
			
			String isSuspended = "finished";
			String activityName = "";//当前任务名称
			String activityUser = "";//当前任务签收人
			if(hi.getEndTime()==null){//endtime为空表示流程实例未结束
				ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(hi.getId()).singleResult();
				Task task = taskService.createTaskQuery().processInstanceId(hi.getId()).singleResult();
				isSuspended = ""+pi.isSuspended();
				activityName = StringUtils.trimToEmpty(task.getName());
				activityUser = StringUtils.trimToEmpty(task.getAssignee());
			}
			
			
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(hi.getProcessDefinitionId()).singleResult();
			
			rows.append("{'id':"+hi.getId()+",'activityName':'"+activityName+"','activityUser':'"+activityUser+"','prcocessDefinitionName':'"+processDefinition.getName()+"','startUserId':'"+hi.getStartUserId()+"','starttime':'"+starttime+"','endtime':'"+endtime+"','spendTimes':'"+spendTimes+"','isSuspended':'"+isSuspended+"','processDefinitionId':'"+hi.getProcessDefinitionId() +"','processInstanceId':'"+hi.getId()+"'},");
		}
		
		
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+historicProcessInstanceQuery.count()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}

	/**
     * 暂停正在运行的流程实例
     * @param processInstanceId 流程部署ID
     */
	@RequestMapping(params = "suspend")
	@ResponseBody
	public AjaxJson suspend(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		runtimeService.suspendProcessInstanceById(processInstanceId);
		
		String message = "暂停成功";
		j.setMsg(message);
		return j;
	}
	
	/**
     * 启动暂停的流程实例
     * @param processInstanceId 流程部署ID
     */
	@RequestMapping(params = "restart")
	@ResponseBody
	public AjaxJson restart(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		runtimeService.activateProcessInstanceById(processInstanceId);
		
		String message = "启动成功";
		j.setMsg(message);
		return j;
	}
	
	/**
     * 作废运行中的流程实例
     * @param processInstanceId 流程部署ID
     */
	@RequestMapping(params = "close")
	@ResponseBody
	public AjaxJson close(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		//repositoryService.deleteDeployment(deploymentId, true);
		runtimeService.deleteProcessInstance(processInstanceId, "主动作废流程");
		
		String message = "作废成功";
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 我发起的流程列表页面
	 * @return
	 */
	@RequestMapping(params = "myRunningProcessList")
	public String processListStartedByMe() {
		return "workflow/activiti/myRunningProcessList";
	}
	
	
	
	
	
	
	
	
	// -----------------------------------------------------------------------------------
			// 以下各函数可以提成共用部件 (Add by Quainty)
			// -----------------------------------------------------------------------------------
			public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				try {
					PrintWriter pw=response.getWriter();
					pw.write(jObject.toString());
					pw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
}

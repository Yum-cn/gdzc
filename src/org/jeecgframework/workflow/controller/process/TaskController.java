package org.jeecgframework.workflow.controller.process;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSRoleUser;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.pojo.base.TPProcessnode;
import org.jeecgframework.workflow.pojo.base.TSBusConfig;
import org.jeecgframework.workflow.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @ClassName: TaskController
 * @Description: 我的任务
 * @author scott
 * @date 2012-8-19 下午04:20:06
 * 
 */
@Controller
@RequestMapping("/taskController")
public class TaskController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	private ActivitiService activitiService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected IdentityService identityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private HistoryService historyService; 

	/**
	 * 跳转到我的任务列表(总任务列表)
	 */
	@RequestMapping(params = "goTaskListTab")
	public ModelAndView goTaskListTab(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-tab");
	}
	
	/**
	 * 跳转到我的任务列表- 我的任务（个人）
	 */
	@RequestMapping(params = "goMyTaskList")
	public ModelAndView goMyTaskList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-person");
	}
	
	/**
	 * 跳转到我的任务列表 - 我的任务（角色组）需要签收
	 */
	@RequestMapping(params = "goGroupTaskList")
	public ModelAndView goGroupTaskList(HttpServletRequest request) {
		//TaskQuery userRoleTask = taskService.createTaskQuery().processDefinitionKey(getProcessDefKey()).taskCandidateUser(userId);
		//request.setAttribute("userRoleTask", userRoleTask);
		return new ModelAndView("workflow/task/taskList-group");
	}

	
	/**
	 * 跳转到我的任务列表- 我的任务（历史任务）
	 */
	@RequestMapping(params = "goHistoryTaskList")
	public ModelAndView goHistoryTaskList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-history");
	}
	
	/**
	 * 跳转到我的任务处理页面
	 */
	@RequestMapping(params = "goTaskTab")
	public ModelAndView goTaskTab(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		request.setAttribute("taskId", taskId);
		return new ModelAndView("workflow/task/task-tab");
	}
	
	/**
	 * 跳转到我的任务-附加页面
	 */
	@RequestMapping(params = "goTaskForm")
	public ModelAndView goTaskForm(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task  task = activitiService.getTask(taskId);
		//获取流程定义ID
		String pdId = task.getProcessDefinitionId();
		String insId = task.getProcessInstanceId();
		ProcessDefinition procDf = repositoryService.getProcessDefinition(pdId);
		String pkey = procDf.getKey();
		//根据activiti流程定义key获取jeecg的流程节点配置
		TPProcessnode node = activitiService.getTPProcessnode(WorkFlowGlobals.ProcNode_Start, pkey);
		if(node!=null&&node.getModelandview()!=null){
			//流程起始表单的地址
			String nodeStartForm = node.getModelandview();
			String bId = activitiService.getBusinessKeyByTask(taskId);
			nodeStartForm = nodeStartForm+"&load=detail&id="+bId;
			request.setAttribute(WorkFlowGlobals.ProcNode_Start, nodeStartForm);
		}
		return new ModelAndView("workflow/task/task-form");
	}
	
	/**
	 * 跳转到我的任务-任务处理页面
	 */
	@RequestMapping(params = "goTaskOperate")
	public ModelAndView goTaskNode(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task  task = activitiService.getTask(taskId);
		String taskKey = task.getTaskDefinitionKey();
		String pdId = task.getProcessDefinitionId();
		ProcessDefinition procDf = repositoryService.getProcessDefinition(pdId);
		String pkey = procDf.getKey();
		String bId = activitiService.getBusinessKeyByTask(taskId);
		//根据activiti流程定义key获取jeecg的流程节点配置
		TPProcessnode node = activitiService.getTPProcessnode(taskKey, pkey);
		if(node!=null&&node.getModelandview()!=null){
			//外置表单
			String modelandView = node.getModelandview();
			modelandView += "&id="+bId+"&taskId="+taskId;
			request.setAttribute("taskOperateUrl", modelandView);
		}
		request.setAttribute("taskId", taskId);
		return new ModelAndView("workflow/task/task-operate");
	}
	
	/**
	 * 跳转到我的任务-流程图
	 */
	@RequestMapping(params = "goTaskMap")
	public ModelAndView goTaskMap(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		String mapUrl = "activitiController.do?openProcessPic&tag=task&taskId="+taskId;
		request.setAttribute("mapUrl", mapUrl);
		return new ModelAndView("workflow/task/task-map");
	}
	/**
	 * 待办任务列表-用户所有的任务
	 */

	@RequestMapping(params = "taskAllList")
	public void taskAllList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		List taskList = activitiService.findPriTodoTasks(user.getUserName(),request);
		Long taskSize = activitiService.countPriTodaoTask(user.getUserName(),request);
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);

	}
	/**
	 * 待办任务列表-组任务
	 */

	@RequestMapping(params = "taskGroupList")
	public void taskGroupList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		//获取当前用户角色集
		List<TSRoleUser> roles = systemService.findByProperty(TSRoleUser.class, "TSUser", user);
		List taskList = activitiService.findGroupTodoTasks(roles, request);
		Long taskSize = activitiService.countGroupTodoTasks(roles, request);
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);

	}
	/**
	 * 待办任务列表-历史任务
	 */

	@RequestMapping(params = "taskHistoryList")
	public void taskHistoryList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		List taskList = activitiService.findHistoryTasks(user.getUserName(),request);
		Long taskSize = activitiService.countHistoryTasks(user.getUserName(),request);
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);

	}
}

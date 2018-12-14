package org.jeecgframework.workflow.controller.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.hibernate.qbc.PagerUtil;
import org.qihuasoft.core.common.model.common.UploadFile;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.MyClassLoader;
import org.qihuasoft.core.util.ReflectHelper;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.oConvertUtils;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.model.activiti.ActivitiCom;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.model.activiti.Variable;
import org.jeecgframework.workflow.model.diagram.HistoryProcessInstanceDiagramCmd;
import org.jeecgframework.workflow.pojo.activiti.ActHiProcinst;
import org.jeecgframework.workflow.pojo.activiti.ActIdUser;
import org.jeecgframework.workflow.pojo.activiti.ActRuTask;
import org.jeecgframework.workflow.pojo.base.TPForm;
import org.jeecgframework.workflow.pojo.base.TPFormpro;
import org.jeecgframework.workflow.pojo.base.TPProcess;
import org.jeecgframework.workflow.pojo.base.TSBusConfig;
import org.jeecgframework.workflow.pojo.base.TSPrjstatus;
import org.jeecgframework.workflow.pojo.base.TSTable;
import org.jeecgframework.workflow.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @ClassName: ActivitiController
 * @Description: TODO(流程引擎处理类)
 * @author jeecg
 * @date 2012-8-19 下午04:20:06
 * 
 */
@Controller
@RequestMapping("/activitiController")
public class ActivitiController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected RepositoryService repositoryService;
	protected RuntimeService runtimeService;
	private ActivitiService activitiService;
	protected TaskService taskService;
	protected IdentityService identityService;
	private SystemService systemService;
	private String message;
	private ModelAndView modelAndView = null;
	private HistoryService historyService; 
	@Autowired
	private ProcessEngine processEngine;


	@Autowired
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Autowired
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Autowired
	public void setActivitiService(ActivitiService activitiService) {
		this.activitiService = activitiService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	/**
	 * 流程列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "deploymentList")
	public ModelAndView deploymentList() {
		return new ModelAndView("workflow/deployment/deploymentList");
	}

	/**
	 * TODO(流程定义列表 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署)
	 */
	@RequestMapping(params = "processDeploymentGrid")
	public void processDeploymentGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<Object[]> objects = new ArrayList<Object[]>();
		List<ProcessDefinition> processDefinitionList = activitiService.processDefinitionList();
		for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
			objects.add(new Object[] { processDefinition, deployment });
		}
		dataGrid.setTotal(processDefinitionList.size());
		dataGrid.setResults(processDefinitionList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 流程部署页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "openDeployProcess")
	public ModelAndView openDeployProcess() {
		return new ModelAndView("workflow/deployment/deploypro");
	}

	@RequestMapping(params = "deployProcess", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson deployProcess(HttpServletRequest request) {
		UploadFile uploadFile = new UploadFile(request);
		AjaxJson j = new AjaxJson();
		Map<String, MultipartFile> fileMap = uploadFile.getMultipartRequest().getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			String fileName = file.getOriginalFilename();
			try {
				InputStream fileInputStream = file.getInputStream();
				String extension = FilenameUtils.getExtension(fileName);
				if (extension.equals("zip") || extension.equals("bar")) {
					ZipInputStream zip = new ZipInputStream(fileInputStream);
					repositoryService.createDeployment().addZipInputStream(zip).deploy();
				} else if (extension.equals("png")) {
					repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
				} else if (extension.indexOf("bpmn20.xml") != -1) {
					repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
				} else if (extension.equals("bpmn")) {
					/*
					 * bpmn扩展名特殊处理，转换为bpmn20.xml
					 */
					String baseName = FilenameUtils.getBaseName(fileName);
					repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
				} else {
					throw new ActivitiException("错误信息:不支改文件类型" + extension);
				}
			} catch (Exception e) {
				logger.error("错误信息:在部署过程中,文件输入流异常" + e.toString());
			}
		}

		return j;
	}

	/**
	 * 提交业务，启动流程
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "startBusProcess")
	@ResponseBody
	public AjaxJson startBusProcess(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
			String busconfigId = oConvertUtils.getString(request.getParameter("busconfigKey"));
			TSBusConfig tsBusbase = systemService.getEntity(TSBusConfig.class, busconfigId);
			if (tsBusbase != null) {
				Class entityClass = MyClassLoader.getClassByScn(tsBusbase.getTSTable().getEntityName());
				Object objbus = systemService.getEntity(entityClass, businessKey);
				TSPrjstatus prjstatus = systemService.findUniqueByProperty(TSPrjstatus.class, "code", "doing");
				ReflectHelper reflectHelper = new ReflectHelper(objbus);
				TSPrjstatus busPrjstatus = (TSPrjstatus) reflectHelper.getMethodValue("TSPrjstatus");
				String objbusstate = busPrjstatus.getCode();
				if (!prjstatus.getCode().equals(objbusstate)) {
					Map<String, Object> variables = new HashMap<String, Object>();
					activitiService.startWorkflow(objbus, businessKey, variables, tsBusbase);
					reflectHelper.setMethodValue("TSPrjstatus", prjstatus);
					systemService.saveOrUpdate(objbus);
					message = "提交成功,已进入办理流程";
				} else {
					message = "已提交,正在办理中";
				}
			} else {
				if (busconfigId.equals("undefined")) {
					message = "busconfigKey参数未设置,请在业务列表设置参数";
				} else {
					message = "流程未关联,请在流程配置中配置业务";
				}

			}
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				message = "没有部署流程!,请在流程配置中部署流程";

			} else {
				message = "启动流程失败!,内部错误";

			}
		} catch (Exception e) {
			message = "启动流程失败!,内部错误";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 签收任务
	 */
	@RequestMapping(params = "claim")
	@ResponseBody
	public AjaxJson claim(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String userId = ResourceUtil.getSessionUserName().getUserName().toString();
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		taskService.claim(taskId, userId);
		j.setMsg("签收完成");
		return j;
	}

	/**
	 * 委托别人办理
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goEntrust")
	public ModelAndView goEntrust(HttpServletRequest request) {
		return new ModelAndView("business/demobus/entruster");
	}
	
	/**
	 * 跳转委托人页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goEntrustAdd")
	public ModelAndView goEntrustAdd(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		request.setAttribute("taskId", taskId);
		return new ModelAndView("business/demobus/entruster-add");
	}
	
	/**
	 * 委托人列表填充
	 * @param actIdUser
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridEntruster")
	public void datagridEntruster(ActIdUser actIdUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActIdUser.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actIdUser, request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 选择委托人
	 * 
	 */
	@RequestMapping(params = "doEntrust")
	@ResponseBody
	public AjaxJson doEntrust(ActIdUser actIdUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "委托成功!";
		String taskId = request.getParameter("taskId");
		try{
			ActRuTask ruTask = this.systemService.get(ActRuTask.class, taskId);
			ruTask.setAssignee(actIdUser.getId());
			this.systemService.saveOrUpdate(ruTask);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "委托失败!";
			throw new BusinessException(e.getMessage());
		}
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 流程图显示
	 */
	@RequestMapping(params = "openProcessPic")
	public ModelAndView openProcessPic(HttpServletRequest request) {
		String tag = oConvertUtils.getString(request.getParameter("tag"));
		if (tag.equals("task")) {
			String taskId = oConvertUtils.getString(request.getParameter("taskId"));// taskID
			Task task = activitiService.getTask(taskId);// 引擎任务对象
			String processInstanceId = task.getProcessInstanceId();
			ActivityImpl activityImpl = activitiService.getProcessMap(processInstanceId);
			request.setAttribute("activityImpl", activityImpl);
			request.setAttribute("processInstanceId", processInstanceId);

		} else if (tag.equals("deployment")) {
			request.setAttribute("resourceName", oConvertUtils.getString(request.getParameter("resourceName")));
			request.setAttribute("deploymentId", oConvertUtils.getString(request.getParameter("deploymentId")));
		} else if (tag.equals("project")) {
			String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));// businessKey
			HistoricProcessInstance historicProcessInstance = activitiService.getHiProcInstByBusKey(businessKey);
			String processInstanceId = historicProcessInstance.getId();
			ActivityImpl activityImpl = activitiService.getProcessMap(processInstanceId);
			request.setAttribute("activityImpl", activityImpl);
			request.setAttribute("processInstanceId", processInstanceId);
		}
		request.setAttribute("tag", tag);
		return new ModelAndView("workflow/process/processPic");
	}

	/**
	 * 读取资源，通过流程ID
	 * 
	 * @param resourceType
	 *            资源类型(xml|image)
	 * @param processInstanceId
	 *            流程实例ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "processPic")
	public void processPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String processInstanceId = oConvertUtils.getString(request.getParameter("processInstanceId"));
		Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd( processInstanceId);
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine(); 
        InputStream is = processEngine.getManagementService().executeCommand(cmd);
        
        int len = 0;
        byte[] b = new byte[1024];

        while ((len = is.read(b, 0, 1024)) != -1) {
        	response.getOutputStream().write(b, 0, len);
        }
	}

	/**
	 * 读取资源，通过部署ID
	 * 
	 * @param deploymentId
	 *            流程部署的ID
	 * @param resourceName
	 *            资源名称(foo.xml|foo.png)
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "processPicByDeploy")
	public void processPicByDeploy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String deploymentId = oConvertUtils.getString(request.getParameter("deploymentId"));
		String resourceName = oConvertUtils.getString(request.getParameter("resourceName"));
		InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	/**
	 * 待办任务列表
	 */

	@RequestMapping(params = "taskList")
	public void taskList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		String buscode = oConvertUtils.getString(request.getParameter("busCode"));
		TSTable table = systemService.findUniqueByProperty(TSTable.class, "entityKey", buscode);
		if (table != null) {
			List<TSBusConfig> tsBusConfigs = systemService.findByProperty(TSBusConfig.class, "TSTable.id", table.getId());
			List taskList = activitiService.findTodoTasks(user.getUserName(), tsBusConfigs);
			dataGrid.setTotal(taskList.size());
			dataGrid.setResults(taskList);
			TagUtil.datagrid(response, dataGrid);
		}

	}

	/**
	 * 打开流程办理页面
	 */
	@RequestMapping(params = "openProcessHandle")
	public ModelAndView openProcessHandle(HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));// taskID
		ProcessHandle processHandle = activitiService.getProcessHandle(taskId);
		if (processHandle.getTpForm() != null) {
			TPForm tForm = processHandle.getTpForm(); // 表单对象
			// 根据表单id查询出表单的所有参数
			List<TPFormpro> formpros = tForm.getTPFormpros();
			request.setAttribute("formpros", formpros);
			request.setAttribute("action", tForm.getFormaction());
			modelAndView = new ModelAndView("workflow/processHandle/dynamicHandle");
		} else {
			String modelandview = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandview(), "activitiController.do?processHandle");
			modelAndView = new ModelAndView(new RedirectView(modelandview), "taskId", taskId);
		}
		return modelAndView;
	}

	/**
	 * 通用审批页面跳转
	 */
	@RequestMapping(params = "processHandle")
	public ModelAndView processHandle(HttpServletRequest request) {
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		request.setAttribute("taskId", taskId);
		return new ModelAndView("workflow/processhandle/processHandle");
	}

	/**
	 * 执行办理通用方法
	 * 
	 * @param request
	 * @param var
	 *            流程变量Map
	 * @return
	 */
	@RequestMapping(params = "processComplete")
	@ResponseBody
	public AjaxJson processComplete(HttpServletRequest request, Variable var) {
		AjaxJson j = new AjaxJson();
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		ProcessHandle processHandle = activitiService.getProcessHandle(taskId);
		Map<String, Object> map = var.getVariableMap(processHandle.getTpProcesspros());
		ActivitiCom complete = activitiService.complete(taskId, map);
		if (complete.getComplete()) {
			j.setMsg(complete.getMsg());
		} else {
			j.setMsg(complete.getMsg());
		}
		return j;
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * 
	 * @param deploymentId
	 */
	@RequestMapping(params = "deleteProcess")
	@ResponseBody
	public AjaxJson deleteProcess(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String deploymentId = oConvertUtils.getString(request.getParameter("deploymentId"));
		String key = oConvertUtils.getString(request.getParameter("key"));// 流程唯一标示
		TPProcess process = systemService.findUniqueByProperty(TPProcess.class, "processkey", key);
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).processDefinitionKey(key).singleResult();
		List<ActHiProcinst> actHiProcinsts = systemService.findByProperty(ActHiProcinst.class, "procDefId", processDefinition.getId());
		if (actHiProcinsts.size() <= 0) {
			repositoryService.deleteDeployment(deploymentId, true);
			process.setProcessstate(WorkFlowGlobals.Process_Deploy_NO);
			systemService.updateEntitie(process);// 还原流程为未发布状态
			message = "流程删除成功";
		} else {
			message = "流程跟业务已关联无法删除";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 挂起激活流程
	 */
	@RequestMapping(params = "setProcessState")
	@ResponseBody
	public AjaxJson setProcessState(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String state = oConvertUtils.getString(request.getParameter("state"));
		String processDefinitionId = oConvertUtils.getString(request.getParameter("processDefinitionId"));
		if (state.equals("active")) {
			repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
			message = "流程已激活";
		} else {
			repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
			message = "流程已挂起";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 获得引擎用户列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "getUsers")
	@ResponseBody
	public void getUsers(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(User.class, dataGrid);
		List<User> userList = identityService.createUserQuery().list();
		dataGrid.setTotal(userList.size());
		dataGrid.setResults(userList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 获得引擎角色列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "getGroups")
	@ResponseBody
	public void getGroups(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<Group> groupList = identityService.createGroupQuery().list();
		dataGrid.setTotal(groupList.size());
		dataGrid.setResults(groupList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 运行中的流程实例页面跳转
	 */
	@RequestMapping(params = "taskRunningList")
	public ModelAndView taskRunningList(HttpServletRequest request) {
		return new ModelAndView("business/demobus/taskRunningList");
	}
	
	/**
	 * 运行中的流程实例列表
	 */
	@RequestMapping(params = "taskRunningGrid")
	public void taskRunningGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		int allCounts = (int)runtimeService.createProcessInstanceQuery().count();
		int pageSize = dataGrid.getRows();// 每页显示数
		int curPageNO = PagerUtil.getcurPageNo(allCounts, dataGrid.getPage(),
				pageSize);// 当前页
		int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().listPage(offset, pageSize);
		dataGrid.setResults(list);
		dataGrid.setTotal(list.size());
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 结束的流程实例页面跳转
	 */
	@RequestMapping(params = "taskFinishedList")
	public ModelAndView taskFinishedList(HttpServletRequest request) {
		return new ModelAndView("business/demobus/taskFinishedList");
	}
	
	/**
	 * 运行中的流程实例列表
	 */
	@RequestMapping(params = "taskFinishedGrid")
	public void taskFinishedGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		int allCounts = (int)historyService.createHistoricProcessInstanceQuery().count();
		int pageSize = dataGrid.getRows();// 每页显示数
		int curPageNO = PagerUtil.getcurPageNo(allCounts, dataGrid.getPage(),
				pageSize);// 当前页
		int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().finished().listPage(offset, pageSize);
		dataGrid.setResults(list);
		dataGrid.setTotal(allCounts);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 读取带跟踪的流程图片
	 * @throws Exception
	 */
	@RequestMapping(params = "traceImage")
    public void traceImage(@RequestParam("processInstanceId") String processInstanceId,
    		HttpServletResponse response) throws Exception {
    	
		Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine(); 
        InputStream is = processEngine.getManagementService().executeCommand(cmd);
        
        int len = 0;
        byte[] b = new byte[1024];

        while ((len = is.read(b, 0, 1024)) != -1) {
        	response.getOutputStream().write(b, 0, len);
        }
    }
	
	/**
	 * easyui 流程历史页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "viewProcessInstanceHistory")
	public ModelAndView viewProcessInstanceHistory(@RequestParam("processInstanceId") String processInstanceId,
			HttpServletRequest request, HttpServletResponse respone,Model model) {
		
		model.addAttribute("processInstanceId", processInstanceId);
		
		return new ModelAndView("workflow/activiti/viewProcessInstanceHistory");
	}
	
	/**
	 * easyui 流程历史数据获取
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "taskHistoryList")
	public void taskHistoryList(@RequestParam("processInstanceId") String processInstanceId,
			HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid) {
		
        List<HistoricTaskInstance> historicTasks = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).list();
        
        StringBuffer rows = new StringBuffer();
        for(HistoricTaskInstance hi : historicTasks){
        	String starttime = hi.getStartTime()==null?"":DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        	String endtime = hi.getEndTime()==null?"":DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
			rows.append("{'name':'"+hi.getName()+"','processInstanceId':'"+hi.getProcessInstanceId() +"','startTime':'"+starttime+"','endTime':'"+endtime+"','assignee':'"+StringUtils.trimToEmpty(hi.getAssignee())+"','deleteReason':'"+StringUtils.trimToEmpty(hi.getDeleteReason())+"'},");
        	//System.out.println(hi.getName()+"@"+hi.getAssignee()+"@"+hi.getStartTime()+"@"+hi.getEndTime());
        }
		
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+historicTasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
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

	
	/**
	 * easyui 待领任务页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "waitingClaimTask")
	public ModelAndView waitingClaimTask() {
		
		return new ModelAndView("workflow/activiti/waitingClaimTask");
	}
	
	/**
	 * easyui AJAX请求数据 待领任务
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "waitingClaimTaskDataGrid")
	public void waitingClaimTaskDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userId = ResourceUtil.getSessionUserName().getUserName();
		TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).active().list();//.taskCandidateGroup("hr").active().list();
		
		StringBuffer rows = new StringBuffer();
		for(Task t : tasks){
			rows.append("{'name':'"+t.getName() +"','taskId':'"+t.getId()+"','processDefinitionId':'"+t.getProcessDefinitionId()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+tasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	
	/**
	 * easyui 待办任务页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "claimedTask")
	public ModelAndView claimedTask() {
		
		return new ModelAndView("workflow/activiti/claimedTask");
	}
	
	/**
	 * easyui AJAX请求数据 待办任务
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "claimedTaskDataGrid")
	public void claimedTaskDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userId = ResourceUtil.getSessionUserName().getUserName();
		TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
		
		StringBuffer rows = new StringBuffer();
		for(Task t : tasks){
			rows.append("{'name':'"+t.getName() +"','description':'"+t.getDescription()+"','taskId':'"+t.getId()+"','processDefinitionId':'"+t.getProcessDefinitionId()+"','processInstanceId':'"+t.getProcessInstanceId()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+tasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	
	/**
	 * easyui 已办任务页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "finishedTask")
	public ModelAndView finishedTask() {
		return new ModelAndView("workflow/activiti/finishedTask");
	}
	
	/**
	 * easyui AJAX请求数据 已办任务
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "finishedTaskDataGrid")
	public void finishedTask(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userId = ResourceUtil.getSessionUserName().getUserName();
		List<HistoricTaskInstance> historicTasks = historyService
                .createHistoricTaskInstanceQuery().taskAssignee(userId)
                .finished().list();
		
		StringBuffer rows = new StringBuffer();
		for(HistoricTaskInstance t : historicTasks){
			rows.append("{'name':'"+t.getName() +"','description':'"+t.getDescription()+"','taskId':'"+t.getId()+"','processDefinitionId':'"+t.getProcessDefinitionId()+"','processInstanceId':'"+t.getProcessInstanceId()+"'},");
		}
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'total':"+historicTasks.size()+",'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}

	/**
     * 签收任务
     * @param taskId
     */
	@RequestMapping(params = "claimTask")
	@ResponseBody
	public AjaxJson claimTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		String userId = ResourceUtil.getSessionUserName().getUserName();
		
		TaskService taskService = processEngine.getTaskService();
        taskService.claim(taskId, userId);
		
		String message = "签收成功";
		j.setMsg(message);
		return j;
	}

}

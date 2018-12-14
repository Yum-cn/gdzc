package com.sc.eventmanager.controller;
import com.sc.eventmanager.entity.ScEventManagerEntity;
import com.sc.eventmanager.service.ScEventManagerServiceI;
import com.sc.schedulemanager.entity.ScheduleManagerEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.qihuasoft.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.qihuasoft.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.qihuasoft.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.qihuasoft.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller  
 * @Description: 紧急事件管理
 * @author onlineGenerator
 * @date 2017-01-08 20:14:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/scEventManagerController")
public class ScEventManagerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScEventManagerController.class);

	@Autowired
	private ScEventManagerServiceI scEventManagerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	
	/**
	 * 添加事件
	 * @param request
	 * @return
	 */
	
	@RequestMapping(params = "mobileMyEventAdd")
	public ModelAndView mobileMyEventAdd(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScEventManagerEntity.class); 
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/schedulemanager/mobileMyEventAdd");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "mobileMyEventUpdate")
	public ModelAndView mobileMyEventUpdate(ScEventManagerEntity scEventManager, HttpServletRequest req) {
		try{
			if (StringUtil.isNotEmpty(scEventManager.getId())) {
				scEventManager = scEventManagerService.getEntity(ScEventManagerEntity.class, scEventManager.getId());
				req.setAttribute("scEventManagerPage", scEventManager);
			}
			return new ModelAndView("com/sc/schedulemanager/mobileMyEventUpdate");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 紧急事件管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScEventManagerEntity.class); 
			String query_createName = request.getParameter("createName");
			if(query_createName!=null && !"".equals(query_createName)){
				
				request.setAttribute("createName", query_createName);
				detachedCriteria.add(Restrictions.like("createName", "%"+query_createName+"%"));
			}			
			String query_type = request.getParameter("type");
			if(query_type!=null && !"".equals(query_type)){
				
				request.setAttribute("type", query_type);
				detachedCriteria.add(Restrictions.like("type", "%"+query_type+"%"));
			}
			detachedCriteria.addOrder(Order.desc("timestring"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/eventmanager/scEventManagerList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		try{
			String hql = "from ScEventManagerEntity order by timestring desc";
			
			List resultList = systemService.findByQueryString(hql);
			if(resultList!=null){
				for(int i=0;i<resultList.size();i++){
					ScEventManagerEntity sme = (ScEventManagerEntity)resultList.get(i);
					String timestring = sme.getTimestring();
					try{
						SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd"); 
						SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日");
						Date temp = format2.parse(timestring);
						timestring = format1.format(temp);
						sme.setTimestring(timestring);

					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
			request.setAttribute("resultList", resultList);
			request.setAttribute("defaultDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			return new ModelAndView("main/index");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ScEventManagerEntity scEventManager,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ScEventManagerEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scEventManager, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.scEventManagerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除紧急事件管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	public ModelAndView doDel(ScEventManagerEntity scEventManager, HttpServletRequest request) {
		String message = null;
		scEventManager = systemService.getEntity(ScEventManagerEntity.class, scEventManager.getId());
		message = "紧急事件管理删除成功";
		try{
			scEventManagerService.delete(scEventManager);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "紧急事件管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex&tab=2");
		return new ModelAndView("mobulesuccess");
	}
	
	/**
	 * 批量删除紧急事件管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "紧急事件管理删除成功";
		try{
			for(String id:ids.split(",")){
				ScEventManagerEntity scEventManager = systemService.getEntity(ScEventManagerEntity.class, 
				id
				);
				scEventManagerService.delete(scEventManager);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "紧急事件管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加紧急事件管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(ScEventManagerEntity scEventManager, HttpServletRequest request) {
		String message = null;
		message = "紧急事件管理添加成功";
		try{
			scEventManagerService.save(scEventManager);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "紧急事件管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex&tab=2");
		return new ModelAndView("mobulesuccess");
	}
	
	/**
	 * 更新紧急事件管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(ScEventManagerEntity scEventManager, HttpServletRequest request) {
		String message = null;
		message = "紧急事件管理更新成功";
		ScEventManagerEntity t = scEventManagerService.get(ScEventManagerEntity.class, scEventManager.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(scEventManager, t);
			scEventManagerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "紧急事件管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex&tab=2");
		return new ModelAndView("mobulesuccess");
	}
	

	/**
	 * 紧急事件管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ScEventManagerEntity scEventManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scEventManager.getId())) {
			scEventManager = scEventManagerService.getEntity(ScEventManagerEntity.class, scEventManager.getId());
			req.setAttribute("scEventManagerPage", scEventManager);
		}

		return new ModelAndView("com/sc/eventmanager/scEventManager-add");
	}
	/**
	 * 紧急事件管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ScEventManagerEntity scEventManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scEventManager.getId())) {
			scEventManager = scEventManagerService.getEntity(ScEventManagerEntity.class, scEventManager.getId());
			req.setAttribute("scEventManagerPage", scEventManager);
		}
		return new ModelAndView("com/sc/eventmanager/scEventManager-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","scEventManagerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ScEventManagerEntity scEventManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ScEventManagerEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scEventManager, request.getParameterMap());
		List<ScEventManagerEntity> scEventManagers = this.scEventManagerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"紧急事件管理");
		modelMap.put(NormalExcelConstants.CLASS,ScEventManagerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("紧急事件管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,scEventManagers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ScEventManagerEntity scEventManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"紧急事件管理");
    	modelMap.put(NormalExcelConstants.CLASS,ScEventManagerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("紧急事件管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<ScEventManagerEntity> listScEventManagerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ScEventManagerEntity.class,params);
				for (ScEventManagerEntity scEventManager : listScEventManagerEntitys) {
					scEventManagerService.save(scEventManager);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ScEventManagerEntity> list() {
		List<ScEventManagerEntity> listScEventManagers=scEventManagerService.getList(ScEventManagerEntity.class);
		return listScEventManagers;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ScEventManagerEntity task = scEventManagerService.get(ScEventManagerEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ScEventManagerEntity scEventManager, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScEventManagerEntity>> failures = validator.validate(scEventManager);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scEventManagerService.save(scEventManager);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = scEventManager.getId();
		URI uri = uriBuilder.path("/rest/scEventManagerController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ScEventManagerEntity scEventManager) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScEventManagerEntity>> failures = validator.validate(scEventManager);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scEventManagerService.saveOrUpdate(scEventManager);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		scEventManagerService.deleteEntityById(ScEventManagerEntity.class, id);
	}
}

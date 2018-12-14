package com.sc.schedulemanager.controller;
import com.sc.eventmanager.entity.ScEventManagerEntity;
import com.sc.schedulemanager.entity.ScheduleManagerEntity;
import com.sc.schedulemanager.service.ScheduleManagerServiceI;
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
import org.qihuasoft.web.system.pojo.base.TSUser;
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
 * @Description: 日程管理
 * @author onlineGenerator
 * @date 2017-01-06 20:08:24
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/scheduleManagerController")
public class ScheduleManagerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScheduleManagerController.class);

	@Autowired
	private ScheduleManagerServiceI scheduleManagerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "mobileIndex")
	public ModelAndView mobileIndex(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		try{
			String selectDate = request.getParameter("selectDate");
			String dateValues = "";
			String viewDate = "";
			if(selectDate!=null && !"".equals(selectDate)){
				try{
					dateValues = "['"+selectDate+"']";
					viewDate = selectDate;
					SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd"); 
					SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日");
					Date temp = format1.parse(selectDate);
					selectDate = format2.format(temp);

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}else{
				try{
					dateValues = "['"+selectDate+"']";
					SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd"); 
					SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日");
					selectDate = format2.format(new Date());
					dateValues = "['"+format1.format(new Date())+"']";
					viewDate = format1.format(new Date());
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			//时间排序
			String tsSql = "select left(timestring,11) as ts from `sc_schedule_manager` where create_by='"+user.getUserName()+"' group by ts order by ts desc";
			List tsList = scheduleManagerService.findListbySql(tsSql);
			if(tsList!=null && tsList.size()>0){
				dateValues = "[";
				String tsTemp = "";
				for(int i=0;i<tsList.size();i++){
					Object obj = (Object)tsList.get(i);
					if(obj!=null){
						SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd"); 
						SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日");
						Date temp = format2.parse(String.valueOf(obj));
						String tempDate = format1.format(temp);
						tsTemp = tsTemp + ",'" + tempDate + "'";
//						tsTemp = tsTemp + ",'" + String.valueOf(obj) + "'";
					}
				}
				if(tsTemp!=null && !"".equals(tsTemp)){
					tsTemp = tsTemp.replaceFirst(",", "");
				}
				dateValues = dateValues + tsTemp + "]";
			}
			String hql = "from ScheduleManagerEntity where timeString like '%"+selectDate+"%' and createBy='"+user.getUserName()+"'";
			List resultList =   scheduleManagerService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			request.setAttribute("dateValues", dateValues);
			request.setAttribute("viewDate", viewDate);
			
			String eventtitle = request.getParameter("eventtitle");
			if(eventtitle!=null && !"".equals(eventtitle)){
				hql = "from ScEventManagerEntity where title like '%"+eventtitle+"%' and createBy='"+user.getUserName()+"' order by timestring desc";
			}else{
				hql = "from ScEventManagerEntity  where createBy='"+user.getUserName()+"' order by timestring desc";
			}
			List eventList =   scheduleManagerService.findByQueryString(hql);
			request.setAttribute("eventList", eventList);
			String tab = request.getParameter("tab");
			if(eventtitle!=null && !"".equals(eventtitle)){
				request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex&tab=2");
			}
			
			hql = "from ScSubstituteManagerEntity  where substituteUserid='"+user.getId()+"' order by createDate desc";
			List substituteList =   scheduleManagerService.findByQueryString(hql);
			request.setAttribute("substituteList", substituteList);
			
			//统计当天以后有多少代课的数量
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now=sdf.format(new Date());
			now = now + " 00:00:00";
			hql = "from ScSubstituteManagerEntity  where substituteUserid='"+user.getId()+"' and substituteTime>='"+now+"' order by createDate desc";
			List substituteNum =   scheduleManagerService.findByQueryString(hql);
			if(substituteNum==null)
				request.setAttribute("substituteNum", 0);
			else
				request.setAttribute("substituteNum", substituteNum.size());
			
			return new ModelAndView("com/sc/schedulemanager/mobileIndex");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 添加日程
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "mobileMyScheduleAdd")
	public ModelAndView mobileMyScheduleAdd(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleManagerEntity.class); 
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/schedulemanager/mobileMyScheduleAdd");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "mobileMyScheduleUpdate")
	public ModelAndView mobileMyScheduleUpdate(ScheduleManagerEntity scheduleManager,HttpServletRequest request) {
		try{
			if (StringUtil.isNotEmpty(scheduleManager.getId())) {
				scheduleManager = scheduleManagerService.getEntity(ScheduleManagerEntity.class, scheduleManager.getId());
				request.setAttribute("scheduleManagerPage", scheduleManager);
			}
			return new ModelAndView("com/sc/schedulemanager/mobileMyScheduleUpdate");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 代课接收表单
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "mobileMySubstituteForm")
	public ModelAndView mobileMySubstituteForm(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleManagerEntity.class); 
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/schedulemanager/mobileMySubstituteForm");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 日程管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleManagerEntity.class); 
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
			detachedCriteria.addOrder(Order.desc("timeString"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/schedulemanager/scheduleManagerList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		try{
			String createName = request.getParameter("createName");
			if(createName!=null && !"".equals(createName)){
				String hql = "from ScheduleManagerEntity where createName like '%"+createName+"%' order by timeString desc";
				
				List resultList = systemService.findByQueryString(hql);
				if(resultList!=null){
					for(int i=0;i<resultList.size();i++){
						ScheduleManagerEntity sme = (ScheduleManagerEntity)resultList.get(i);
						String timestring = sme.getTimeString();
						try{
							SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd"); 
							SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日");
							Date temp = format2.parse(timestring);
							timestring = format1.format(temp);
							sme.setTimeString(timestring);
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
				request.setAttribute("resultList", resultList);
			}
			request.setAttribute("defaultDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			return new ModelAndView("main/index2");
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
	public void datagrid(ScheduleManagerEntity scheduleManager,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ScheduleManagerEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scheduleManager, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.scheduleManagerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除日程管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	public ModelAndView doDel(ScheduleManagerEntity scheduleManager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		scheduleManager = systemService.getEntity(ScheduleManagerEntity.class, scheduleManager.getId());
		message = "日程管理删除成功";
		try{
			scheduleManagerService.delete(scheduleManager);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "日程管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex");
		return new ModelAndView("mobulesuccess");
	}
	
	/**
	 * 批量删除日程管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "日程管理删除成功";
		try{
			for(String id:ids.split(",")){
				ScheduleManagerEntity scheduleManager = systemService.getEntity(ScheduleManagerEntity.class, 
				id
				);
				scheduleManagerService.delete(scheduleManager);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "日程管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加日程管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(ScheduleManagerEntity scheduleManager, HttpServletRequest request) {
		String message = null;
		message = "日程管理添加成功";
		try{
			scheduleManagerService.save(scheduleManager);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "日程管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex");
		return new ModelAndView("mobulesuccess");
	}
	
	/**
	 * 更新日程管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(ScheduleManagerEntity scheduleManager, HttpServletRequest request) {
		String message = null;
		message = "日程管理更新成功";
		ScheduleManagerEntity t = scheduleManagerService.get(ScheduleManagerEntity.class, scheduleManager.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(scheduleManager, t);
			scheduleManagerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "日程管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scheduleManagerController.do?mobileIndex");
		return new ModelAndView("mobulesuccess");
	}
	

	/**
	 * 日程管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ScheduleManagerEntity scheduleManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scheduleManager.getId())) {
			scheduleManager = scheduleManagerService.getEntity(ScheduleManagerEntity.class, scheduleManager.getId());
			req.setAttribute("scheduleManagerPage", scheduleManager);
		}

		return new ModelAndView("com/sc/schedulemanager/scheduleManager-add");
	}
	/**
	 * 日程管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ScheduleManagerEntity scheduleManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scheduleManager.getId())) {
			scheduleManager = scheduleManagerService.getEntity(ScheduleManagerEntity.class, scheduleManager.getId());
			req.setAttribute("scheduleManagerPage", scheduleManager);
		}
		return new ModelAndView("com/sc/schedulemanager/scheduleManager-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","scheduleManagerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ScheduleManagerEntity scheduleManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ScheduleManagerEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scheduleManager, request.getParameterMap());
		List<ScheduleManagerEntity> scheduleManagers = this.scheduleManagerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"日程管理");
		modelMap.put(NormalExcelConstants.CLASS,ScheduleManagerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("日程管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,scheduleManagers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ScheduleManagerEntity scheduleManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"日程管理");
    	modelMap.put(NormalExcelConstants.CLASS,ScheduleManagerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("日程管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ScheduleManagerEntity> listScheduleManagerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ScheduleManagerEntity.class,params);
				for (ScheduleManagerEntity scheduleManager : listScheduleManagerEntitys) {
					scheduleManagerService.save(scheduleManager);
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
	public List<ScheduleManagerEntity> list() {
		List<ScheduleManagerEntity> listScheduleManagers=scheduleManagerService.getList(ScheduleManagerEntity.class);
		return listScheduleManagers;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ScheduleManagerEntity task = scheduleManagerService.get(ScheduleManagerEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ScheduleManagerEntity scheduleManager, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScheduleManagerEntity>> failures = validator.validate(scheduleManager);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scheduleManagerService.save(scheduleManager);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = scheduleManager.getId();
		URI uri = uriBuilder.path("/rest/scheduleManagerController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ScheduleManagerEntity scheduleManager) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScheduleManagerEntity>> failures = validator.validate(scheduleManager);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scheduleManagerService.saveOrUpdate(scheduleManager);
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
		scheduleManagerService.deleteEntityById(ScheduleManagerEntity.class, id);
	}
}

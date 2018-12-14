package com.sc.substitutemanager.controller;
import com.sc.substitutemanager.entity.ScSubstituteManagerEntity;
import com.sc.substitutemanager.entity.TotalSubstituteManagerEntity;
import com.sc.substitutemanager.service.ScSubstituteManagerServiceI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;
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
import org.qihuasoft.core.util.MutiLangUtil;
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
 * @Description: 代课分配管理
 * @author onlineGenerator
 * @date 2017-01-09 15:38:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/scSubstituteManagerController")
public class ScSubstituteManagerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScSubstituteManagerController.class);

	@Autowired
	private ScSubstituteManagerServiceI scSubstituteManagerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "mobileMySubstituteView")
	public ModelAndView mobileMySubstituteView(ScSubstituteManagerEntity scSubstituteManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scSubstituteManager.getId())) {
			scSubstituteManager = scSubstituteManagerService.getEntity(ScSubstituteManagerEntity.class, scSubstituteManager.getId());
			req.setAttribute("scSubstituteManagerPage", scSubstituteManager);
		}
		return new ModelAndView("com/sc/schedulemanager/mobileMySubstituteForm");
	}

	/**
	 * 代课分配管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScSubstituteManagerEntity.class); 
			String query_substituteMan = request.getParameter("substituteMan");
			if(query_substituteMan!=null && !"".equals(query_substituteMan)){
				request.setAttribute("substituteMan", query_substituteMan);
				detachedCriteria.add(Restrictions.like("substituteMan", "%"+query_substituteMan+"%"));
			}
			String query_leaveMan = request.getParameter("leaveMan");
			if(query_leaveMan!=null && !"".equals(query_leaveMan)){
				request.setAttribute("leaveMan", query_leaveMan);
				detachedCriteria.add(Restrictions.like("leaveMan", "%"+query_leaveMan+"%"));
			}
			String query_substituteClass = request.getParameter("substituteClass");
			if(query_substituteClass!=null && !"".equals(query_substituteClass)){
				request.setAttribute("substituteClass", query_substituteClass);
				detachedCriteria.add(Restrictions.like("substituteClass", "%"+query_substituteClass+"%"));
			}
			String query_substituteTime = request.getParameter("substituteTime");
			if(query_substituteTime!=null && !"".equals(query_substituteTime)){
				
				request.setAttribute("substituteTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_substituteTime));
				String startTime = query_substituteTime+" 00:00:00";
				String endTime = query_substituteTime+" 23:59:59";
				detachedCriteria.add(Restrictions.ge("substituteTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)));
				detachedCriteria.add(Restrictions.le("substituteTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime)));
				
			}
			detachedCriteria.addOrder(Order.desc("substituteTime"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/substitutemanager/scSubstituteManagerList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "statisticsList")
	public ModelAndView statisticsList(HttpServletRequest request) {
		try{
			String query_startTime = request.getParameter("startTime");
			String query_endTime = request.getParameter("endTime");
			String sql = "";
			if(query_startTime!=null && !"".equals(query_startTime)){
				request.setAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_startTime));
				query_startTime = query_startTime+" 00:00:00";
				
			}
			if(query_endTime!=null && !"".equals(query_endTime)){
				request.setAttribute("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_endTime));
				query_endTime = query_endTime+" 23:59:59";
				
			}
			if(query_endTime!=null && !"".equals(query_endTime) && query_startTime!=null && !"".equals(query_startTime))
				sql = "select substitute_man,count(substitute_man) as total from `sc_substitute_manager` where substitute_time>='"+query_startTime+"' and substitute_time<='"+query_endTime+"' group by substitute_man order by total desc";
			else
				sql = "select substitute_man,count(substitute_man) as total from `sc_substitute_manager`  group by substitute_man order by total desc";
			List resultList = systemService.findListbySql(sql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/sc/substitutemanager/statisticsList");
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
	public void datagrid(ScSubstituteManagerEntity scSubstituteManager,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ScSubstituteManagerEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scSubstituteManager, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.scSubstituteManagerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除代课分配管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ScSubstituteManagerEntity scSubstituteManager, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		scSubstituteManager = systemService.getEntity(ScSubstituteManagerEntity.class, scSubstituteManager.getId());
		message = "代课分配管理删除成功";
		try{
			scSubstituteManagerService.delete(scSubstituteManager);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "代课分配管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除代课分配管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "代课分配管理删除成功";
		try{
			for(String id:ids.split(",")){
				ScSubstituteManagerEntity scSubstituteManager = systemService.getEntity(ScSubstituteManagerEntity.class, 
				id
				);
				scSubstituteManagerService.delete(scSubstituteManager);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "代课分配管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加代课分配管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(ScSubstituteManagerEntity scSubstituteManager, HttpServletRequest request) {
		String message = null;
		message = "代课分配管理添加成功";
		try{
			scSubstituteManagerService.save(scSubstituteManager);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "代课分配管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scSubstituteManagerController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新代课分配管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(ScSubstituteManagerEntity scSubstituteManager, HttpServletRequest request) {
		String message = null;
		message = "代课分配管理更新成功";
		ScSubstituteManagerEntity t = scSubstituteManagerService.get(ScSubstituteManagerEntity.class, scSubstituteManager.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(scSubstituteManager, t);
			scSubstituteManagerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "代课分配管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scSubstituteManagerController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 代课分配管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ScSubstituteManagerEntity scSubstituteManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scSubstituteManager.getId())) {
			scSubstituteManager = scSubstituteManagerService.getEntity(ScSubstituteManagerEntity.class, scSubstituteManager.getId());
			req.setAttribute("scSubstituteManagerPage", scSubstituteManager);
		}

		return new ModelAndView("com/sc/substitutemanager/scSubstituteManager-add");
	}
	/**
	 * 代课分配管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ScSubstituteManagerEntity scSubstituteManager, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scSubstituteManager.getId())) {
			scSubstituteManager = scSubstituteManagerService.getEntity(ScSubstituteManagerEntity.class, scSubstituteManager.getId());
			req.setAttribute("scSubstituteManagerPage", scSubstituteManager);
		}
		return new ModelAndView("com/sc/substitutemanager/scSubstituteManager-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","scSubstituteManagerController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ScSubstituteManagerEntity scSubstituteManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ScSubstituteManagerEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scSubstituteManager, request.getParameterMap());
		List<ScSubstituteManagerEntity> scSubstituteManagers = this.scSubstituteManagerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"代课分配管理");
		modelMap.put(NormalExcelConstants.CLASS,ScSubstituteManagerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("代课分配管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		if(scSubstituteManagers!=null){
			HashMap<String,String> dicCodeMap = new HashMap<String,String>();
			String sql = "select typecode,typename from `t_s_type` ";
			List list = systemService.findListbySql(sql);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[])list.get(i);
					dicCodeMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
				}
			}
			for(int i=0;i<scSubstituteManagers.size();i++){
				ScSubstituteManagerEntity sme = (ScSubstituteManagerEntity)scSubstituteManagers.get(i);
				sme.setSubstituteSection(dicCodeMap.get(sme.getSubstituteSection()));
			}
		}
		modelMap.put(NormalExcelConstants.DATA_LIST,scSubstituteManagers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@RequestMapping(params = "exportStatisticsXls")
	public String exportStatisticsXls(ScSubstituteManagerEntity scSubstituteManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		
		String query_startTime = request.getParameter("startTime");
		String query_endTime = request.getParameter("endTime");
		String sql = "";
		try{
			if(query_startTime!=null && !"".equals(query_startTime)){
				request.setAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_startTime));
				query_startTime = query_startTime+" 00:00:00";
				
			}
			if(query_endTime!=null && !"".equals(query_endTime)){
				request.setAttribute("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_endTime));
				query_endTime = query_endTime+" 23:59:59";
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(query_endTime!=null && !"".equals(query_endTime) && query_startTime!=null && !"".equals(query_startTime))
			sql = "select substitute_man,count(substitute_man) as total from `sc_substitute_manager` where substitute_time>='"+query_startTime+"' and substitute_time<='"+query_endTime+"' group by substitute_man order by total desc";
		else
			sql = "select substitute_man,count(substitute_man) as total from `sc_substitute_manager`  group by substitute_man order by total desc";
		List resultList = systemService.findListbySql(sql);
		List<TotalSubstituteManagerEntity> result = new ArrayList<TotalSubstituteManagerEntity>();
		if(resultList!=null && resultList.size()>0){
			for(int i=0;i<resultList.size();i++){
				Object[] obj = (Object[])resultList.get(i);
				TotalSubstituteManagerEntity stm = new TotalSubstituteManagerEntity();
				stm.setLeaveMan(String.valueOf(obj[0]));
				stm.setTotal(String.valueOf(obj[1]));
				result.add(stm);
			}
		}

//		List<ScSubstituteManagerEntity> scSubstituteManagers = this.scSubstituteManagerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"代课统计");
		modelMap.put(NormalExcelConstants.CLASS,TotalSubstituteManagerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("代课统计", "统计日期:"+query_startTime+"至"+query_endTime,
			"导出信息"));

		modelMap.put(NormalExcelConstants.DATA_LIST,result);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ScSubstituteManagerEntity scSubstituteManager,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"代课分配管理");
    	modelMap.put(NormalExcelConstants.CLASS,ScSubstituteManagerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("代课分配数据导出", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ScSubstituteManagerEntity> listScSubstituteManagerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ScSubstituteManagerEntity.class,params);
				for (ScSubstituteManagerEntity scSubstituteManager : listScSubstituteManagerEntitys) {
					scSubstituteManagerService.save(scSubstituteManager);
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
	public List<ScSubstituteManagerEntity> list() {
		List<ScSubstituteManagerEntity> listScSubstituteManagers=scSubstituteManagerService.getList(ScSubstituteManagerEntity.class);
		return listScSubstituteManagers;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ScSubstituteManagerEntity task = scSubstituteManagerService.get(ScSubstituteManagerEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ScSubstituteManagerEntity scSubstituteManager, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScSubstituteManagerEntity>> failures = validator.validate(scSubstituteManager);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scSubstituteManagerService.save(scSubstituteManager);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = scSubstituteManager.getId();
		URI uri = uriBuilder.path("/rest/scSubstituteManagerController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ScSubstituteManagerEntity scSubstituteManager) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScSubstituteManagerEntity>> failures = validator.validate(scSubstituteManager);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scSubstituteManagerService.saveOrUpdate(scSubstituteManager);
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
		scSubstituteManagerService.deleteEntityById(ScSubstituteManagerEntity.class, id);
	}
}

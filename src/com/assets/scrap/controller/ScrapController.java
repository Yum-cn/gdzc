package com.assets.scrap.controller;
import com.assets.resume.entity.ResumeEntity;
import com.assets.scrap.entity.ScrapEntity;
import com.assets.scrap.service.ScrapServiceI;
import com.assets.store.entity.StoreEntity;

import java.util.ArrayList;
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
 * @Description: 资产报废
 * @author onlineGenerator
 * @date 2018-05-12 12:09:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/scrapController")
public class ScrapController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScrapController.class);

	@Autowired
	private ScrapServiceI scrapService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 资产报废列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScrapEntity.class); 
			String query_scrapTime = request.getParameter("scrapTime");
			if(query_scrapTime!=null && !"".equals(query_scrapTime)){
				
				request.setAttribute("scrapTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_scrapTime));
				String startTime = query_scrapTime+" 00:00:00";
				String endTime = query_scrapTime+" 23:59:59";
				detachedCriteria.add(Restrictions.ge("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)));
				detachedCriteria.add(Restrictions.le("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime)));
				
			}
			String query_reason = request.getParameter("reason");
			if(query_reason!=null && !"".equals(query_reason)){
				
				request.setAttribute("reason", query_reason);
				detachedCriteria.add(Restrictions.like("reason", "%"+query_reason+"%"));
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/scrap/scrapList");
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
	public void datagrid(ScrapEntity scrap,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ScrapEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scrap, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.scrapService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除资产报废
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ScrapEntity scrap, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		scrap = systemService.getEntity(ScrapEntity.class, scrap.getId());
		message = "资产报废删除成功";
		try{
			scrapService.delete(scrap);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资产报废删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资产报废
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资产报废删除成功";
		try{
			for(String id:ids.split(",")){
				ScrapEntity scrap = systemService.getEntity(ScrapEntity.class, 
				id
				);
				scrapService.delete(scrap);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资产报废删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加资产报废
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(ScrapEntity scrap, HttpServletRequest request) {
		String message = null;
		message = "资产报废添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();

			String oddNumbers = "BF"+System.currentTimeMillis();
			scrap.setOddNumbers(oddNumbers);
			if(scrap!=null && scrap.getRelationId()!=null){
				String[] relationIds = scrap.getRelationId().split(",");
				if(relationIds!=null && relationIds.length>0){
					for(int i=0;i<relationIds.length;i++){
						StoreEntity se = systemService.get(StoreEntity.class, relationIds[i]);
						se.setBpmStatus("bf");
						systemService.saveOrUpdate(se);
						ResumeEntity resume = new ResumeEntity();
						resume.setCreateBy(user.getId());
						resume.setHandleUser(user.getRealName());
						resume.setHandleTime(new java.util.Date());
						resume.setStoreId(se.getId());
						resume.setStoreName(se.getName());
						resume.setContent("将资产("+se.getName()+")报废");
						resume.setType("资产报废");
						systemService.save(resume);						
					}
				}
			}
			scrapService.save(scrap);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资产报废添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scrapController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新资产报废
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(ScrapEntity scrap, HttpServletRequest request) {
		String message = null;
		message = "资产报废更新成功";
		ScrapEntity t = scrapService.get(ScrapEntity.class, scrap.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(scrap, t);
			scrapService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资产报废更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "scrapController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 资产报废新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ScrapEntity scrap, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scrap.getId())) {
			scrap = scrapService.getEntity(ScrapEntity.class, scrap.getId());
			req.setAttribute("scrapPage", scrap);
		}

		return new ModelAndView("com/assets/scrap/scrap-add");
	}
	/**
	 * 资产报废编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ScrapEntity scrap, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scrap.getId())) {
			scrap = scrapService.getEntity(ScrapEntity.class, scrap.getId());
			req.setAttribute("scrapPage", scrap);
		}
		return new ModelAndView("com/assets/scrap/scrap-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","scrapController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ScrapEntity scrap,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ScrapEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, scrap, request.getParameterMap());
		List<ScrapEntity> scraps = this.scrapService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资产报废");
		modelMap.put(NormalExcelConstants.CLASS,ScrapEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资产报废列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,scraps);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ScrapEntity scrap,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资产报废");
    	modelMap.put(NormalExcelConstants.CLASS,ScrapEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资产报废列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ScrapEntity> listScrapEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ScrapEntity.class,params);
				for (ScrapEntity scrap : listScrapEntitys) {
					scrapService.save(scrap);
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
	public List<ScrapEntity> list() {
		List<ScrapEntity> listScraps=scrapService.getList(ScrapEntity.class);
		return listScraps;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ScrapEntity task = scrapService.get(ScrapEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ScrapEntity scrap, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScrapEntity>> failures = validator.validate(scrap);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scrapService.save(scrap);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = scrap.getId();
		URI uri = uriBuilder.path("/rest/scrapController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ScrapEntity scrap) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ScrapEntity>> failures = validator.validate(scrap);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			scrapService.saveOrUpdate(scrap);
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
		scrapService.deleteEntityById(ScrapEntity.class, id);
	}
}

package com.document.doclibrarylist.controller;
import com.document.doclibrarylist.entity.DocLibraryListEntity;
import com.document.doclibrarylist.service.DocLibraryListServiceI;
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
 * @Description: 库目录
 * @author onlineGenerator
 * @date 2017-01-24 12:52:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/docLibraryListController")
public class DocLibraryListController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DocLibraryListController.class);

	@Autowired
	private DocLibraryListServiceI docLibraryListService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 库目录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocLibraryListEntity.class); 
			
			String pId=request.getParameter("pId");
			request.setAttribute("pId", pId);
			if(pId!=null && !"".equals(pId)){
				detachedCriteria.add(Restrictions.eq("pId", ""+pId+""));
			}else{
				detachedCriteria.add(Restrictions.eq("pId", ""));
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibrarylist/docLibraryListList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 树的列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "libraryTree")
	public ModelAndView libraryTree(HttpServletRequest request) {
		try{
			String hql = "from DocLibraryListEntity order by createDate desc";
			List resultList = docLibraryListService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibrarylist/libraryListTree");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	/**
	 * 删除库目录
	 * 
	 * @return
	 */
	@RequestMapping(params = "createPackage")
	@ResponseBody
	public AjaxJson createPackage(HttpServletRequest request,String pId,String pName) {
		String message = null;
		AjaxJson j = new AjaxJson();
		DocLibraryListEntity docLibraryList=new DocLibraryListEntity();
		message = "新建文件夹成功";
		try{
			docLibraryList.setpId(pId);
			docLibraryList.setFolderName(pName);
			docLibraryListService.save(docLibraryList);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "新建文件夹失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
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
	public void datagrid(DocLibraryListEntity docLibraryList,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DocLibraryListEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docLibraryList, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.docLibraryListService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除库目录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DocLibraryListEntity docLibraryList, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docLibraryList = systemService.getEntity(DocLibraryListEntity.class, docLibraryList.getId());
		message = "库目录删除成功";
		try{
			docLibraryListService.delete(docLibraryList);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "库目录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除库目录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "库目录删除成功";
		try{
			for(String id:ids.split(",")){
				DocLibraryListEntity docLibraryList = systemService.getEntity(DocLibraryListEntity.class, 
				id
				);
				docLibraryListService.delete(docLibraryList);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "库目录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加库目录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(DocLibraryListEntity docLibraryList, HttpServletRequest request) {
		String message = null;
		message = "库目录添加成功";
		try{
			docLibraryListService.save(docLibraryList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "库目录添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docLibraryListController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新库目录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(DocLibraryListEntity docLibraryList, HttpServletRequest request) {
		String message = null;
		message = "库目录更新成功";
		DocLibraryListEntity t = docLibraryListService.get(DocLibraryListEntity.class, docLibraryList.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(docLibraryList, t);
			docLibraryListService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "库目录更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docLibraryListController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 库目录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DocLibraryListEntity docLibraryList, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docLibraryList.getId())) {
			docLibraryList = docLibraryListService.getEntity(DocLibraryListEntity.class, docLibraryList.getId());
			req.setAttribute("docLibraryListPage", docLibraryList);
		}

		return new ModelAndView("com/document/doclibrarylist/docLibraryList-add");
	}
	/**
	 * 库目录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DocLibraryListEntity docLibraryList, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docLibraryList.getId())) {
			docLibraryList = docLibraryListService.getEntity(DocLibraryListEntity.class, docLibraryList.getId());
			req.setAttribute("docLibraryListPage", docLibraryList);
		}
		return new ModelAndView("com/document/doclibrarylist/docLibraryList-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","docLibraryListController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DocLibraryListEntity docLibraryList,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DocLibraryListEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docLibraryList, request.getParameterMap());
		List<DocLibraryListEntity> docLibraryLists = this.docLibraryListService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"库目录");
		modelMap.put(NormalExcelConstants.CLASS,DocLibraryListEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("库目录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,docLibraryLists);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DocLibraryListEntity docLibraryList,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"库目录");
    	modelMap.put(NormalExcelConstants.CLASS,DocLibraryListEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("库目录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<DocLibraryListEntity> listDocLibraryListEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DocLibraryListEntity.class,params);
				for (DocLibraryListEntity docLibraryList : listDocLibraryListEntitys) {
					docLibraryListService.save(docLibraryList);
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
	public List<DocLibraryListEntity> list() {
		List<DocLibraryListEntity> listDocLibraryLists=docLibraryListService.getList(DocLibraryListEntity.class);
		return listDocLibraryLists;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DocLibraryListEntity task = docLibraryListService.get(DocLibraryListEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DocLibraryListEntity docLibraryList, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocLibraryListEntity>> failures = validator.validate(docLibraryList);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docLibraryListService.save(docLibraryList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = docLibraryList.getId();
		URI uri = uriBuilder.path("/rest/docLibraryListController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DocLibraryListEntity docLibraryList) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocLibraryListEntity>> failures = validator.validate(docLibraryList);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docLibraryListService.saveOrUpdate(docLibraryList);
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
		docLibraryListService.deleteEntityById(DocLibraryListEntity.class, id);
	}
}

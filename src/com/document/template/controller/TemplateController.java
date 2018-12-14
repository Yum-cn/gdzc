package com.document.template.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.qihuasoft.core.beanvalidator.BeanValidators;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.DateUtils;
import org.qihuasoft.core.util.ExceptionUtil;
import org.qihuasoft.core.util.MyBeanUtils;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.docmyfile.service.DocMyFileServiceI;
import com.document.template.entity.TemplateEntity;
import com.document.template.service.TemplateServiceI;
import com.zhuozhengsoft.pageoffice.FileSaver;

/**   
 * @Title: Controller  
 * @Description: 材料模板
 * @author onlineGenerator
 * @date 2017-01-11 19:55:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/templateController")
public class TemplateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TemplateController.class);
	@Autowired
	private DocMyFileServiceI docMyFileService;
	@Autowired
	private TemplateServiceI templateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 材料模板列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TemplateEntity.class); 
			String query_title = request.getParameter("title");
			if(query_title!=null && !"".equals(query_title)){
				
				request.setAttribute("title", query_title);
				detachedCriteria.add(Restrictions.like("title", "%"+query_title+"%"));
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/template/templateList");
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
	public void datagrid(TemplateEntity template,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TemplateEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, template, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.templateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除材料模板
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TemplateEntity template, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		template = systemService.getEntity(TemplateEntity.class, template.getId());
		message = "材料模板删除成功";
		try{
			templateService.delete(template);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "材料模板删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除材料模板
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "材料模板删除成功";
		try{
			for(String id:ids.split(",")){
				TemplateEntity template = systemService.getEntity(TemplateEntity.class, 
				id
				);
				templateService.delete(template);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "材料模板删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加材料模板
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(TemplateEntity template, HttpServletRequest request) {
		String message = null;
		message = "材料模板添加成功";
		try{
			templateService.save(template);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "材料模板添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "templateController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新材料模板
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(TemplateEntity template, HttpServletRequest request) {
		String message = null;
		message = "材料模板更新成功";
		TemplateEntity t = templateService.get(TemplateEntity.class, template.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(template, t);
			templateService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料模板更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "templateController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 材料模板新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TemplateEntity template, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(template.getId())) {
			template = templateService.getEntity(TemplateEntity.class, template.getId());
			req.setAttribute("templatePage", template);
		}

		return new ModelAndView("com/document/template/template-add");
	}
	/**
	 * 材料模板编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TemplateEntity template, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(template.getId())) {
			template = templateService.getEntity(TemplateEntity.class, template.getId());
			req.setAttribute("templatePage", template);
		}
		return new ModelAndView("com/document/template/template-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","templateController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TemplateEntity template,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TemplateEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, template, request.getParameterMap());
		List<TemplateEntity> templates = this.templateService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"材料模板");
		modelMap.put(NormalExcelConstants.CLASS,TemplateEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("材料模板列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,templates);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TemplateEntity template,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"材料模板");
    	modelMap.put(NormalExcelConstants.CLASS,TemplateEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("材料模板列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<TemplateEntity> listTemplateEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TemplateEntity.class,params);
				for (TemplateEntity template : listTemplateEntitys) {
					templateService.save(template);
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
	public List<TemplateEntity> list() {
		List<TemplateEntity> listTemplates=templateService.getList(TemplateEntity.class);
		return listTemplates;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TemplateEntity task = templateService.get(TemplateEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TemplateEntity template, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TemplateEntity>> failures = validator.validate(template);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			templateService.save(template);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = template.getId();
		URI uri = uriBuilder.path("/rest/templateController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TemplateEntity template) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TemplateEntity>> failures = validator.validate(template);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			templateService.saveOrUpdate(template);
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
		templateService.deleteEntityById(TemplateEntity.class, id);
	}
	
	/**
	 * 在线创建格式文件
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "createTemplateOnline")
	public ModelAndView editWord(HttpServletRequest request) {
		try{
			String pId = request.getParameter("pId");
			request.setAttribute("pId", pId);
			String id = request.getParameter("id");
			TemplateEntity template = templateService.getEntity(TemplateEntity.class, id);
			request.setAttribute("template", template);
			String fileName = request.getParameter("fileName");
			fileName = URLDecoder.decode(fileName,"UTF-8");
			//存数据库
			DocMyFileEntity docMyFile = new DocMyFileEntity();
			docMyFile.setFileName(fileName);
			docMyFile.setpId(pId);
			docMyFile.setUploadTime(new Date());
			docMyFile.setIsPackage("N");
			docMyFile.setDeleteFlag("0");
			docMyFileService.save(docMyFile);
		request.setAttribute("docMyFilePage", docMyFile);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("com/document/pageoffice/createTemplateOnline");
	}
	
	@RequestMapping(params = "saveCreateTemplate")
	public ModelAndView saveCreateTemplate(HttpServletRequest request,HttpServletResponse response) {
		try {
			TSUser user = ResourceUtil.getSessionUserName();
			String pId = request.getParameter("pId");
			String fileType = request.getParameter("type");
			String userId = user.getId();
			int fileSize = 0;
			String yyyyMM = DateUtils.date2Str(DateUtils.date_yyyy_mm);
			String userFilePath = request.getSession().getServletContext().getRealPath("upload/") + "/" + userId + "/" + yyyyMM + "/";
			File file =new File(userFilePath);    
			//如果文件夹不存在则创建    
			File dir = new File(userFilePath);
			if (!dir.exists()) {
				try {
					dir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			FileSaver fs = new FileSaver(request, response);
			// 保存文件
			String uuid = java.util.UUID.randomUUID().toString();
			String filePath = userFilePath + "/" + uuid + "."+fileType;
			String webPath = "upload/" + userId + "/" + yyyyMM + "/" + uuid + "."+fileType;
			fs.saveToFile(filePath);
			fileSize = fs.getFileSize();
			long longFileSize = fileSize;
			String StringFileSize=String.valueOf(longFileSize);
			
			//获取文件大小
//			long longFileSize = fileSize;
//			String StringFileSize="";
//			if(longFileSize>1000000){
//				double doubleFileSize=(double)longFileSize/(1024*1024);
//				BigDecimal s=new BigDecimal(doubleFileSize);
//				double size = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//				StringFileSize=String.valueOf(size)+"MB";
//			}else{
//				double doubleFileSize=(double)longFileSize/(1024);
//				BigDecimal s=new BigDecimal(doubleFileSize);
//				double size = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//				StringFileSize=String.valueOf(size)+"KB";
//			}
			//存数据库
			String id = request.getParameter("id");
			DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
			docMyFile.setFilePath(webPath);
			docMyFile.setFileSize(StringFileSize);
//			docMyFile.setFileName(uuid);
			docMyFile.setFileType(fileType);
			docMyFile.setpId(pId);
			docMyFile.setUploadTime(new Date());
			docMyFile.setIsPackage("N");
			docMyFile.setDeleteFlag("0");
			docMyFileService.save(docMyFile);
			// 设置保存结果
			fs.setCustomSaveResult(docMyFile.getId());
			// fs.showPage(300,300);
			fs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	@RequestMapping(params = "saveEditTemplate")
	public ModelAndView saveEditTemplate(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		TSUser user = ResourceUtil.getSessionUserName();
		DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
		String userFilePath = request.getSession().getServletContext().getRealPath("/") + "/" + docMyFile.getFilePath();
		FileSaver fs = new FileSaver(request, response);
		fs.saveToFile(userFilePath);
		//fs.showPage(300,300);
		fs.close();
		return null;
	}
}

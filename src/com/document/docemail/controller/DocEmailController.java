package com.document.docemail.controller;
import com.document.docemail.entity.DocEmailEntity;
import com.document.docemail.service.DocEmailServiceI;
import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.docmyfile.service.DocMyFileServiceI;

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
import org.qihuasoft.core.util.MailUtils;
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
 * @Description: 邮件表
 * @author onlineGenerator
 * @date 2017-01-23 00:30:27
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/docEmailController")
public class DocEmailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DocEmailController.class);

	@Autowired
	private DocEmailServiceI docEmailService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	@Autowired
	private DocMyFileServiceI docMyFileService;


	/**
	 * 邮件表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocEmailEntity.class); 
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/docemail/docEmailList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	
	/**
	 * 发送邮箱
	 * 
	 * @return
	 */
	@RequestMapping(params = "goSentEmail")
	public ModelAndView goSentEmail(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocMyFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			String id = request.getParameter("id");
			String fileName = request.getParameter("fileName");
			fileName = java.net.URLDecoder.decode(fileName,"UTF-8");
			request.setAttribute("fileName", fileName);
			detachedCriteria.add(Restrictions.eq("id", id));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/docemail/docSentEmail");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 邮件保存
	 * 
	 * @return
	 */
	@RequestMapping(params = "doSentEmail")
	@ResponseBody
	public AjaxJson doSentEmail(String addressee,String carbonCopy,String emailTitle,String emailContent,String fileId,HttpServletRequest request) {
		String message=null;
		AjaxJson j = new AjaxJson();
		message="邮件发送成功";
		DocEmailEntity email=new DocEmailEntity(); 
		
		try {
			email.setFileId(fileId);
			email.setAddressee(addressee);
			email.setCarbonCopy(carbonCopy);
			email.setEmailTitle(emailTitle);
			email.setEmailContent(emailContent);
			docEmailService.save(email);
			MailUtils cn = new MailUtils();
	        // 设置发件人地址、收件人地址和邮件标题
	        cn.setAddress("microhardboy@126.com", addressee, emailTitle,carbonCopy);
	        // 设置要发送附件的位置和标题
	        DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, fileId);
	        String filePath = request.getRealPath("/")+"/"+docMyFile.getFilePath();
	        cn.setAffix(filePath, docMyFile.getFileName()+"."+docMyFile.getFileType());
	        cn.send("smtp.126.com", "microhardboy@126.com", "dingyuan_1985");
		} catch (Exception e) {
			e.printStackTrace();
			message = "邮件发送失败";
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
	public void datagrid(DocEmailEntity docEmail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DocEmailEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docEmail, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.docEmailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除邮件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DocEmailEntity docEmail, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docEmail = systemService.getEntity(DocEmailEntity.class, docEmail.getId());
		message = "邮件表删除成功";
		try{
			docEmailService.delete(docEmail);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "邮件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除邮件表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "邮件表删除成功";
		try{
			for(String id:ids.split(",")){
				DocEmailEntity docEmail = systemService.getEntity(DocEmailEntity.class, 
				id
				);
				docEmailService.delete(docEmail);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "邮件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加邮件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(DocEmailEntity docEmail, HttpServletRequest request) {
		String message = null;
		message = "邮件表添加成功";
		try{
			docEmailService.save(docEmail);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "邮件表添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docEmailController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新邮件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(DocEmailEntity docEmail, HttpServletRequest request) {
		String message = null;
		message = "邮件表更新成功";
		DocEmailEntity t = docEmailService.get(DocEmailEntity.class, docEmail.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(docEmail, t);
			docEmailService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "邮件表更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docEmailController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 邮件表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DocEmailEntity docEmail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docEmail.getId())) {
			docEmail = docEmailService.getEntity(DocEmailEntity.class, docEmail.getId());
			req.setAttribute("docEmailPage", docEmail);
		}

		return new ModelAndView("com/document/docemail/docEmail-add");
	}
	/**
	 * 邮件表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DocEmailEntity docEmail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docEmail.getId())) {
			docEmail = docEmailService.getEntity(DocEmailEntity.class, docEmail.getId());
			req.setAttribute("docEmailPage", docEmail);
		}
		return new ModelAndView("com/document/docemail/docEmail-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","docEmailController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DocEmailEntity docEmail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DocEmailEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docEmail, request.getParameterMap());
		List<DocEmailEntity> docEmails = this.docEmailService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"邮件表");
		modelMap.put(NormalExcelConstants.CLASS,DocEmailEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("邮件表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,docEmails);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DocEmailEntity docEmail,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"邮件表");
    	modelMap.put(NormalExcelConstants.CLASS,DocEmailEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("邮件表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<DocEmailEntity> listDocEmailEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DocEmailEntity.class,params);
				for (DocEmailEntity docEmail : listDocEmailEntitys) {
					docEmailService.save(docEmail);
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
	public List<DocEmailEntity> list() {
		List<DocEmailEntity> listDocEmails=docEmailService.getList(DocEmailEntity.class);
		return listDocEmails;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DocEmailEntity task = docEmailService.get(DocEmailEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DocEmailEntity docEmail, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocEmailEntity>> failures = validator.validate(docEmail);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docEmailService.save(docEmail);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = docEmail.getId();
		URI uri = uriBuilder.path("/rest/docEmailController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DocEmailEntity docEmail) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocEmailEntity>> failures = validator.validate(docEmail);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docEmailService.saveOrUpdate(docEmail);
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
		docEmailService.deleteEntityById(DocEmailEntity.class, id);
	}
}

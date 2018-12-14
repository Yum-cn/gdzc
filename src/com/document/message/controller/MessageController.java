package com.document.message.controller;
import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.message.entity.MessageEntity;
import com.document.message.service.MessageServiceI;
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
 * @Description: 文档消息
 * @author onlineGenerator
 * @date 2017-01-24 15:17:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/messageController")
public class MessageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MessageController.class);

	@Autowired
	private MessageServiceI myMessageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	
	/**
	 * 发布协同
	 * 
	 * @return
	 */
	@RequestMapping(params = "goTeamwork")
	public ModelAndView goTeamwork(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocMyFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			String id = request.getParameter("id");
			String fileName = request.getParameter("fileName");
			fileName = java.net.URLDecoder.decode(fileName,"UTF-8");
			request.setAttribute("fileName", fileName);
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
			detachedCriteria.add(Restrictions.eq("id", id));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			request.setAttribute("fileType", fileType);
			return new ModelAndView("com/document/message/goTeamwork");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 内部群发
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goMass")
	public ModelAndView goMass(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocMyFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			String id = request.getParameter("id");
			String fileName = request.getParameter("fileName");
			fileName = java.net.URLDecoder.decode(fileName,"UTF-8");
			request.setAttribute("fileName", fileName);
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
			detachedCriteria.add(Restrictions.eq("id", id));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			request.setAttribute("fileType", fileType);
			return new ModelAndView("com/document/message/goMass");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "doTeamwork")
	@ResponseBody
	public AjaxJson doTeamwork(MessageEntity myMessage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message="发送协作成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			myMessage.setSendUser(user.getRealName());
			myMessage.setSendUserId(user.getId());
			myMessage.setType("1");
			myMessageService.save(myMessage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发送协作失败";
			throw new BusinessException(e.getMessage());
		}
		return j;
	}
	@RequestMapping(params = "doMass")
	@ResponseBody
	public AjaxJson doMass(MessageEntity myMessage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message="发送消息文档成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			myMessage.setSendUser(user.getRealName());
			myMessage.setSendUserId(user.getId());
			myMessage.setType("2");
			myMessageService.save(myMessage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "发送消息文档失败";
			throw new BusinessException(e.getMessage());
		}
		return j;
	}
	/**
	 * 文档消息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MessageEntity.class); 
			String query_createDate = request.getParameter("createDate");
			if(query_createDate!=null && !"".equals(query_createDate)){
				
				request.setAttribute("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate));
				String startTime = query_createDate+" 00:00:00";
				String endTime = query_createDate+" 23:59:59";
				detachedCriteria.add(Restrictions.ge("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)));
				detachedCriteria.add(Restrictions.le("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime)));
				
			}
			String query_title = request.getParameter("title");
			if(query_title!=null && !"".equals(query_title)){
				request.setAttribute("title", query_title);
				detachedCriteria.add(Restrictions.like("title", "%"+query_title+"%"));
			}			
			TSUser user = ResourceUtil.getSessionUserName();
			detachedCriteria.add(Restrictions.like("receiveUserId", "%"+user.getId()+"%"));
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/message/myMessageList");
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
	public void datagrid(MessageEntity myMessage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MessageEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, myMessage, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.myMessageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文档消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MessageEntity myMessage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		myMessage = systemService.getEntity(MessageEntity.class, myMessage.getId());
		message = "文档消息删除成功";
		try{
			myMessageService.delete(myMessage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文档消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除文档消息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文档消息删除成功";
		try{
			for(String id:ids.split(",")){
				MessageEntity myMessage = systemService.getEntity(MessageEntity.class, 
				id
				);
				myMessageService.delete(myMessage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "文档消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加文档消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(MessageEntity myMessage, HttpServletRequest request) {
		String message = null;
		message = "文档消息添加成功";
		try{
			myMessageService.save(myMessage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文档消息添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "myMessageController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新文档消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(MessageEntity myMessage, HttpServletRequest request) {
		String message = null;
		message = "文档消息更新成功";
		MessageEntity t = myMessageService.get(MessageEntity.class, myMessage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(myMessage, t);
			myMessageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "文档消息更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "myMessageController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 文档消息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MessageEntity myMessage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(myMessage.getId())) {
			myMessage = myMessageService.getEntity(MessageEntity.class, myMessage.getId());
			req.setAttribute("myMessagePage", myMessage);
		}

		return new ModelAndView("com/document/message/myMessage-add");
	}
	/**
	 * 文档消息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MessageEntity myMessage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(myMessage.getId())) {
			myMessage = myMessageService.getEntity(MessageEntity.class, myMessage.getId());
			req.setAttribute("myMessagePage", myMessage);
		}
		return new ModelAndView("com/document/message/myMessage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","myMessageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MessageEntity myMessage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MessageEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, myMessage, request.getParameterMap());
		List<MessageEntity> myMessages = this.myMessageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"文档消息");
		modelMap.put(NormalExcelConstants.CLASS,MessageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("文档消息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,myMessages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MessageEntity myMessage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"文档消息");
    	modelMap.put(NormalExcelConstants.CLASS,MessageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("文档消息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<MessageEntity> listMyMessageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),MessageEntity.class,params);
				for (MessageEntity myMessage : listMyMessageEntitys) {
					myMessageService.save(myMessage);
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
	public List<MessageEntity> list() {
		List<MessageEntity> listMyMessages=myMessageService.getList(MessageEntity.class);
		return listMyMessages;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		MessageEntity task = myMessageService.get(MessageEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody MessageEntity myMessage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MessageEntity>> failures = validator.validate(myMessage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			myMessageService.save(myMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = myMessage.getId();
		URI uri = uriBuilder.path("/rest/myMessageController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody MessageEntity myMessage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<MessageEntity>> failures = validator.validate(myMessage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			myMessageService.saveOrUpdate(myMessage);
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
		myMessageService.deleteEntityById(MessageEntity.class, id);
	}
}

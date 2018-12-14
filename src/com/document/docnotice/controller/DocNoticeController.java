package com.document.docnotice.controller;
import com.document.docnotice.entity.DocNoticeCountEntity;
import com.document.docnotice.entity.DocNoticeEntity;
import com.document.docnotice.service.DocNoticeCountServiceI;
import com.document.docnotice.service.DocNoticeServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.DateFormat;
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
 * @Description: 通知公告
 * @author onlineGenerator
 * @date 2017-02-13 12:05:10
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/docNoticeController")
public class DocNoticeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DocNoticeController.class);

	@Autowired
	private DocNoticeServiceI docNoticeService;
	@Autowired
	private DocNoticeCountServiceI docNoticeCountService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 通知公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			//获取全部实体类
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocNoticeEntity.class); 
			//按 createDate 降序排列
			detachedCriteria.addOrder(Order.desc("createDate"));
			//条件查询
			String query_title = request.getParameter("title");
			if(query_title!=null&&query_title!=""){
				request.setAttribute("title", query_title);
				detachedCriteria.add(Restrictions.like("noticeTitle", "%"+query_title+"%"));
			}
			String query_sender = request.getParameter("sender");
			if(query_sender!=null&&query_sender!=""){
				request.setAttribute("sender", query_sender);
				detachedCriteria.add(Restrictions.like("noticeSender", "%"+query_sender+"%"));
			}
			//存到request中 返回到页面
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/docnotice/docNoticeList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	
	/**
	 * 跳转到阅读界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "goReadNotice")
	public ModelAndView goReadNotice(HttpServletRequest req,String noticeId) {
			DocNoticeEntity docNotice = docNoticeService.getEntity(DocNoticeEntity.class, noticeId);
			TSUser user = ResourceUtil.getSessionUserName();
			//当前登陆人信息
			String userName = user.getUserName();
			//当前时间
			Date date = new Date();
			//格式化时间
			/*DateFormat dateF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = dateF.format(date);*/  
			
			DocNoticeCountEntity countEntity = new DocNoticeCountEntity();
			countEntity.setNoticeId(noticeId);
			countEntity.setReader(userName);
			countEntity.setReadDate(date);
			try {
				docNoticeCountService.save(countEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("docReadNotice", docNotice);

		return new ModelAndView("com/document/docnotice/readNotice");
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
	public void datagrid(DocNoticeEntity docNotice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DocNoticeEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docNotice, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.docNoticeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除通知公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String noticeId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		//根据主键查信息
		DocNoticeEntity docNotice = systemService.getEntity(DocNoticeEntity.class, noticeId);
		//删除noticeID对应的阅读记录
		String hql = "from DocNoticeCountEntity where noticeId = '"+noticeId+"'"; 
		List delList = docNoticeCountService.findByQueryString(hql);
		message = "通知公告删除成功";
		try{
			docNoticeService.delete(docNotice);
			//删除noticeId关联的阅读记录
			if(delList.size()>0){
				for(int i=0;i<delList.size();i++){
					docNoticeCountService.delete(delList.get(i));
				}
			}
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通知公告删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除通知公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通知公告删除成功";
		try{
			for(String id:ids.split(",")){
				DocNoticeEntity docNotice = systemService.getEntity(DocNoticeEntity.class, 
				id
				);
				docNoticeService.delete(docNotice);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通知公告删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加通知公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(DocNoticeEntity docNotice, HttpServletRequest request) {
		String message = null;
		message = "通知公告添加成功";
		try{
			docNoticeService.save(docNotice);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通知公告添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docNoticeController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新通知公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(DocNoticeEntity docNotice, HttpServletRequest request) {
		String message = null;
		message = "通知公告更新成功";
		DocNoticeEntity t = docNoticeService.get(DocNoticeEntity.class, docNotice.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(docNotice, t);
			docNoticeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通知公告更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docNoticeController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 通知公告新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DocNoticeEntity docNotice, HttpServletRequest req) {
		TSUser user = ResourceUtil.getSessionUserName();
		String realName = user.getRealName();
		req.setAttribute("realName", realName);
		System.out.println("sdfsdfsdfsdf"+realName);
		return new ModelAndView("com/document/docnotice/docNotice-add");
	}
	/**
	 * 通知公告编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DocNoticeEntity docNotice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docNotice.getId())) {
			docNotice = docNoticeService.getEntity(DocNoticeEntity.class, docNotice.getId());
			req.setAttribute("docNoticePage", docNotice);
		}
		return new ModelAndView("com/document/docnotice/docNotice-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","docNoticeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DocNoticeEntity docNotice,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DocNoticeEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docNotice, request.getParameterMap());
		List<DocNoticeEntity> docNotices = this.docNoticeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"通知公告");
		modelMap.put(NormalExcelConstants.CLASS,DocNoticeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通知公告列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,docNotices);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DocNoticeEntity docNotice,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"通知公告");
    	modelMap.put(NormalExcelConstants.CLASS,DocNoticeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通知公告列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<DocNoticeEntity> listDocNoticeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DocNoticeEntity.class,params);
				for (DocNoticeEntity docNotice : listDocNoticeEntitys) {
					docNoticeService.save(docNotice);
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
	public List<DocNoticeEntity> list() {
		List<DocNoticeEntity> listDocNotices=docNoticeService.getList(DocNoticeEntity.class);
		return listDocNotices;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DocNoticeEntity task = docNoticeService.get(DocNoticeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DocNoticeEntity docNotice, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocNoticeEntity>> failures = validator.validate(docNotice);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docNoticeService.save(docNotice);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = docNotice.getId();
		URI uri = uriBuilder.path("/rest/docNoticeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DocNoticeEntity docNotice) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocNoticeEntity>> failures = validator.validate(docNotice);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docNoticeService.saveOrUpdate(docNotice);
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
		docNoticeService.deleteEntityById(DocNoticeEntity.class, id);
	}
}

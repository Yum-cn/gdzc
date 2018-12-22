package com.assets.store.controller;
import com.assets.resume.entity.ResumeEntity;
import com.assets.store.entity.StoreEntity;
import com.assets.store.service.StoreServiceI;
import com.research.entity.apply.ApplyTopicEntity;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import java.awt.Color;
import java.io.File;
import java.io.OutputStream;
import org.qihuasoft.core.util.BrowserUtils;
import org.qihuasoft.core.util.DimensionCode;
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
 * @Description: 入库管理
 * @author onlineGenerator
 * @date 2018-05-12 11:09:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/storeController")
public class StoreController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreServiceI storeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 入库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoreEntity.class); 
			String query_code = request.getParameter("code");
			if(query_code!=null && !"".equals(query_code)){
				
				request.setAttribute("code", query_code);
				detachedCriteria.add(Restrictions.like("code", "%"+query_code+"%"));
			}
			String query_type = request.getParameter("type");
			if(query_type!=null && !"".equals(query_type)){
				
				request.setAttribute("type", query_type);
				detachedCriteria.add(Restrictions.like("type", "%"+query_type+"%"));
			}
			String query_name = request.getParameter("name");
			if(query_name!=null && !"".equals(query_name)){
				
				request.setAttribute("name", query_name);
				detachedCriteria.add(Restrictions.like("name", "%"+query_name+"%"));
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/store/storeList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "selectList")
	public ModelAndView selectList(HttpServletRequest request) {
		try{
			String status = request.getParameter("status");
			request.setAttribute("status", status);
			String category = request.getParameter("category");
			request.setAttribute("category", category);
			return new ModelAndView("com/assets/store/selectList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@RequestMapping(params = "print")
	public ModelAndView print(HttpServletRequest request, HttpServletResponse response) {
		try{
			String id = request.getParameter("id");
			StoreEntity se = systemService.get(StoreEntity.class, id);
			String fileName = java.util.UUID.randomUUID().toString();
//			String url = "http://" + request.getServerName() + ":"
//					+ request.getServerPort()
//					+ request.getContextPath()
//					+ "/questionnaireController.do?generatePaper&id=";
			String path = request.getRealPath("/")+"/upload/temp/"+fileName+".png";
			File qrFile = new File(path);
			String qrPath = "http://" + request.getServerName() + ":"
					+ request.getServerPort()
					+ request.getContextPath() + "/upload/temp/"+fileName+".png";
			StringBuffer sb = new StringBuffer();
			sb.append("资产编码："+se.getCode()+"\n");
//			sb.append("资产类别：\n");
			sb.append("入库时间："+se.getStorageTime()+"\n");
			sb.append("资产名称："+se.getName()+"\n");
//			sb.append("品牌："+se.getBrand()+"\n");
//			sb.append("渠       道："+se.getSource()+"\n");
			sb.append("金 额："+se.getAmount()+"\n");
//			sb.append("过保时间："+se.getOverInsuranceTime()+"\n");
			sb.append("过保时间："+se.getRepairEndTime()+"\n");
			DimensionCode.encoderCode(sb.toString(), response,qrFile);
			request.setAttribute("se", se);

			int font = 14; // 字体大小
			int fontStyle = 1; // 字体风格
			String newImageWithText = request.getRealPath("/")+"/upload/temp/"+fileName+"_result.png";
			qrPath = "http://" + request.getServerName() + ":"
					+ request.getServerPort()
					+ request.getContextPath() + "/upload/temp/"+fileName+"_result.png";
			// 用来存放带有logo+文字的二维码图片
//			String newImageWithText = "D:/imageWithText.jpg";
//			// 带有logo的二维码图片
			String targetImage = path;// "D:/newPic.jpg";
//			// 附加在图片上的文字信息
//			String text = "资产编码：" + se.getCode();
			String text = "";
//
//			// 在二维码下方添加文字（文字居中）
			DimensionCode.pressText(text, newImageWithText, targetImage,
					fontStyle, Color.black, font, 300, 310);
			request.setAttribute("qrPath", qrPath);
			return new ModelAndView("com/assets/store/print");
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
	public void datagrid(StoreEntity store,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		try{
			
			//入库、出库、转移、退库、维修、报废
			
			String status = request.getParameter("status");
			if(status!=null && "lingyong".equals(status)){//领用功能
				CriteriaQuery cq1 = new CriteriaQuery(StoreEntity.class, dataGrid);
				cq1.eq("bpmStatus", "wy");
				cq1.eq("bpmStatus", "xz");
				cq.add(cq.or(cq1, 0, 1));
			}else if(status!=null && "diaobo".equals(status)){//调拨功能
				cq.eq("bpmStatus", "yy");
			}else if(status!=null && "tuiku".equals(status)){//退库功能
				cq.eq("bpmStatus", "yy");
			}else if(status!=null && "weixiu".equals(status)){//维修功能
				cq.notEq("bpmStatus", "wx");
			}else if(status!=null && "ruku".equals(status)){//领用功能
				cq.eq("bpmStatus", "wy");
			}
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.storeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除入库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(StoreEntity store, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		store = systemService.getEntity(StoreEntity.class, store.getId());
		message = "入库管理删除成功";
		try{
			storeService.delete(store);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "入库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除入库管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库管理删除成功";
		try{
			for(String id:ids.split(",")){
				StoreEntity store = systemService.getEntity(StoreEntity.class, 
				id
				);
				storeService.delete(store);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "入库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(StoreEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理添加成功";
		try{
			store.setBpmStatus("wy");
			storeService.save(store);

			TSUser user = ResourceUtil.getSessionUserName();

			ResumeEntity resume = new ResumeEntity();
			resume.setCreateBy(user.getId());
			resume.setHandleUser(user.getRealName());
			resume.setHandleTime(new java.util.Date());
			resume.setStoreId(store.getId());
			resume.setStoreName(store.getName());
			resume.setContent("将资产("+store.getName()+")入库");
			resume.setType("资产入库");		
			systemService.save(resume);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "入库管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "storeController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(StoreEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理更新成功";
		StoreEntity t = storeService.get(StoreEntity.class, store.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(store, t);
			storeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "storeController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 入库管理新增页面跳转
	 * 
	 * @return
	 */
	/**
	 * @author   Yum
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(StoreEntity store,String category, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = storeService.getEntity(StoreEntity.class, store.getId());
		}else{
			store = new StoreEntity();
		}
		store.setCategory(category);
		if(StringUtils.equals(category, "software")){
			store.setType("software");
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-software");
		}else if(StringUtils.equals(category, "hardware")){
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-hardware");
		}
		
		return new ModelAndView("");
	}
	/**
	 * 入库管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(StoreEntity store, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = storeService.getEntity(StoreEntity.class, store.getId());
			req.setAttribute("storePage", store);
			
			if(StringUtils.equals(store.getCategory(), "software")){
				return new ModelAndView("com/assets/store/store-update-software");
			}else if(StringUtils.equals(store.getCategory(), "hardware")){
				return new ModelAndView("com/assets/store/store-update-hardware");
			}
		}
		return new ModelAndView("com/assets/store/store-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","storeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(StoreEntity store,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		List<StoreEntity> stores = this.storeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"入库管理");
		modelMap.put(NormalExcelConstants.CLASS,StoreEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("入库管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,stores);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(StoreEntity store,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"入库管理");
    	modelMap.put(NormalExcelConstants.CLASS,StoreEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("入库管理列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<StoreEntity> listStoreEntitys = ExcelImportUtil.importExcel(file.getInputStream(),StoreEntity.class,params);
				for (StoreEntity store : listStoreEntitys) {
					storeService.save(store);
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
	public List<StoreEntity> list() {
		List<StoreEntity> listStores=storeService.getList(StoreEntity.class);
		return listStores;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		StoreEntity task = storeService.get(StoreEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody StoreEntity store, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<StoreEntity>> failures = validator.validate(store);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			storeService.save(store);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = store.getId();
		URI uri = uriBuilder.path("/rest/storeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody StoreEntity store) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<StoreEntity>> failures = validator.validate(store);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			storeService.saveOrUpdate(store);
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
		storeService.deleteEntityById(StoreEntity.class, id);
	}
}

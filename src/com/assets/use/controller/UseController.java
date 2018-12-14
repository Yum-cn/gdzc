package com.assets.use.controller;
import com.assets.resume.entity.ResumeEntity;
import com.assets.store.entity.StoreEntity;
import com.assets.use.entity.UseEntity;
import com.assets.use.service.UseServiceI;
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
 * @Description: 出库领用
 * @author onlineGenerator
 * @date 2018-05-12 11:45:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/useController")
public class UseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UseController.class);

	@Autowired
	private UseServiceI useService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 出库领用列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UseEntity.class); 
			String query_useUser = request.getParameter("useUser");
			if(query_useUser!=null && !"".equals(query_useUser)){
				
				request.setAttribute("useUser", query_useUser);
				detachedCriteria.add(Restrictions.like("useUser", "%"+query_useUser+"%"));
			}
			String query_useTime = request.getParameter("useTime");
			if(query_useTime!=null && !"".equals(query_useTime)){
				
				request.setAttribute("useTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_useTime));
				String startTime = query_useTime+" 00:00:00";
				String endTime = query_useTime+" 23:59:59";
				detachedCriteria.add(Restrictions.ge("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)));
				detachedCriteria.add(Restrictions.le("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime)));
				
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/use/useList");
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
	public void datagrid(UseEntity use,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UseEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, use, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.useService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "readCardNo")
	@ResponseBody
	public AjaxJson readCardNo(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		List list = systemService.findListbySql("select t3.departname,t1.username from `guard_card` t,t_s_base_user t1,t_s_user_org t2,t_s_depart t3 where t.card_no=t1.cardno and t1.id=t2.user_id and t2.org_id=t3.id and t.card_no=(select card_no from `guard_card` where careate_time>=DATE_SUB(NOW(),INTERVAL 2 MINUTE) order by careate_time desc limit 0,1)  order by careate_time desc limit 0,1");
		if(list!=null && list.size()>0){
			Object[] obj = (Object[])list.get(0);
			HashMap map = new HashMap();
			map.put("departname", obj[0]);
			map.put("username", obj[1]);
			j.setAttributes(map);
		}
		return j;
	}
	/**
	 * 删除出库领用
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(UseEntity use, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		use = systemService.getEntity(UseEntity.class, use.getId());
		message = "出库领用删除成功";
		try{
			useService.delete(use);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "出库领用删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除出库领用
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "出库领用删除成功";
		try{
			for(String id:ids.split(",")){
				UseEntity use = systemService.getEntity(UseEntity.class, 
				id
				);
				useService.delete(use);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "出库领用删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加出库领用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(UseEntity use, HttpServletRequest request) {
		String message = null;
		message = "出库领用添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			String oddNumbers = "LY"+System.currentTimeMillis();
			use.setOddNumbers(oddNumbers);
			if(use!=null && use.getRelationId()!=null){
				String[] relationIds = use.getRelationId().split(",");
				if(relationIds!=null && relationIds.length>0){
					for(int i=0;i<relationIds.length;i++){
						StoreEntity se = systemService.get(StoreEntity.class, relationIds[i]);
						se.setBpmStatus("yy");
						systemService.saveOrUpdate(se);
						ResumeEntity resume = new ResumeEntity();
						resume.setCreateBy(user.getId());
						resume.setHandleUser(user.getRealName());
						resume.setHandleTime(new java.util.Date());
						resume.setStoreId(se.getId());
						resume.setStoreName(se.getName());
						resume.setContent("	("+user.getRealName()+")领用资产");
						resume.setType("出库领用");
						systemService.save(resume);
					}
				}
			}
			useService.save(use);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "出库领用添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "useController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新出库领用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(UseEntity use, HttpServletRequest request) {
		String message = null;
		message = "出库领用更新成功";
		UseEntity t = useService.get(UseEntity.class, use.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(use, t);
			useService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "出库领用更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "useController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 出库领用新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(UseEntity use, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(use.getId())) {
			use = useService.getEntity(UseEntity.class, use.getId());
			req.setAttribute("usePage", use);
		}

		return new ModelAndView("com/assets/use/use-add");
	}
	/**
	 * 出库领用编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(UseEntity use, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(use.getId())) {
			use = useService.getEntity(UseEntity.class, use.getId());
			req.setAttribute("usePage", use);
		}
		return new ModelAndView("com/assets/use/use-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","useController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(UseEntity use,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(UseEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, use, request.getParameterMap());
		List<UseEntity> uses = this.useService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"出库领用");
		modelMap.put(NormalExcelConstants.CLASS,UseEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("出库领用列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,uses);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(UseEntity use,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"出库领用");
    	modelMap.put(NormalExcelConstants.CLASS,UseEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("出库领用列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<UseEntity> listUseEntitys = ExcelImportUtil.importExcel(file.getInputStream(),UseEntity.class,params);
				for (UseEntity use : listUseEntitys) {
					useService.save(use);
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
	public List<UseEntity> list() {
		List<UseEntity> listUses=useService.getList(UseEntity.class);
		return listUses;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		UseEntity task = useService.get(UseEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody UseEntity use, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<UseEntity>> failures = validator.validate(use);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			useService.save(use);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = use.getId();
		URI uri = uriBuilder.path("/rest/useController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody UseEntity use) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<UseEntity>> failures = validator.validate(use);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			useService.saveOrUpdate(use);
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
		useService.deleteEntityById(UseEntity.class, id);
	}
}

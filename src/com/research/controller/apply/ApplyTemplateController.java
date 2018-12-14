package com.research.controller.apply;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.research.entity.apply.ApplyTemplateEntity;
import com.research.service.apply.ApplyTemplateServiceI;

/**   
 * @Title: Controller
 * @Description: 课题申报模板
 * @author onlineGenerator
 * @date 2016-07-24 21:32:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyTemplateController")
public class ApplyTemplateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyTemplateController.class);

	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 课题申报模板列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyTemplate")
	public ModelAndView applyTemplate(HttpServletRequest request) {
		return new ModelAndView("com/research/apply/applyTemplateList");
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
	public void datagrid(ApplyTemplateEntity applyTemplate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyTemplateEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyTemplate, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.applyTemplateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除课题申报模板
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ApplyTemplateEntity applyTemplate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		applyTemplate = systemService.getEntity(ApplyTemplateEntity.class, applyTemplate.getId());
		message = "课题申报模板删除成功";
		try{
			applyTemplateService.delete(applyTemplate);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "课题申报模板删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除课题申报模板
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "课题申报模板删除成功";
		try{
			for(String id:ids.split(",")){
				ApplyTemplateEntity applyTemplate = systemService.getEntity(ApplyTemplateEntity.class, 
				id
				);
				applyTemplateService.delete(applyTemplate);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "课题申报模板删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加课题申报模板
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ApplyTemplateEntity applyTemplate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "课题申报模板添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			applyTemplate.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				applyTemplate.setCreateOrg(user.getDepartid());
			}
			applyTemplate.setCreateTime(new Date());
			applyTemplateService.save(applyTemplate);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "课题申报模板添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新课题申报模板
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ApplyTemplateEntity applyTemplate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "课题申报模板更新成功";
		ApplyTemplateEntity t = applyTemplateService.get(ApplyTemplateEntity.class, applyTemplate.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(applyTemplate, t);
			applyTemplateService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "课题申报模板更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 课题申报模板新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ApplyTemplateEntity applyTemplate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyTemplate.getId())) {
			applyTemplate = applyTemplateService.getEntity(ApplyTemplateEntity.class, applyTemplate.getId());
			req.setAttribute("applyTemplatePage", applyTemplate);
		}
		return new ModelAndView("com/research/apply/applyTemplate-add");
	}
	/**
	 * 课题申报模板编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ApplyTemplateEntity applyTemplate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyTemplate.getId())) {
			applyTemplate = applyTemplateService.getEntity(ApplyTemplateEntity.class, applyTemplate.getId());
			req.setAttribute("applyTemplatePage", applyTemplate);
		}
		return new ModelAndView("com/research/apply/applyTemplate-update");
	}
}

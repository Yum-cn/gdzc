package com.research.controller.opinion;
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

import com.research.entity.apply.ApplyTopicEntity;
import com.research.entity.opinion.AuditOpinionEntity;
import com.research.service.apply.ApplyTopicServiceI;
import com.research.service.opinion.AuditOpinionServiceI;

/**   
 * @Title: Controller
 * @Description: 审核意见表
 * @author onlineGenerator
 * @date 2016-07-31 20:02:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/auditOpinionController")
public class AuditOpinionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuditOpinionController.class);

	@Autowired
	private ApplyTopicServiceI applyTopicService;
	@Autowired
	private AuditOpinionServiceI auditOpinionService;
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
	 * 审核意见表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "auditOpinion")
	public ModelAndView auditOpinion(HttpServletRequest request) {
		String topId = request.getParameter("id");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/apply/applyTopicOpinionList");
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
	public void datagrid(AuditOpinionEntity auditOpinion,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AuditOpinionEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, auditOpinion, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.auditOpinionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除审核意见表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AuditOpinionEntity auditOpinion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		auditOpinion = systemService.getEntity(AuditOpinionEntity.class, auditOpinion.getId());
		message = "审核意见表删除成功";
		try{
			auditOpinionService.delete(auditOpinion);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "审核意见表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除审核意见表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "审核意见表删除成功";
		try{
			for(String id:ids.split(",")){
				AuditOpinionEntity auditOpinion = systemService.getEntity(AuditOpinionEntity.class, 
				id
				);
				auditOpinionService.delete(auditOpinion);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "审核意见表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加审核意见表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AuditOpinionEntity auditOpinion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "审核意见表添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			auditOpinion.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				auditOpinion.setCreateOrg(user.getDepartid());
			}
			auditOpinion.setCreateDate(new Date());
			auditOpinionService.save(auditOpinion);
			ApplyTopicEntity ate = systemService.get(ApplyTopicEntity.class, auditOpinion.getTopId());
			//if("0".equals(auditOpinion.getStatus()))
			//	ate.setStatus("5");
			//if("1".equals(auditOpinion.getStatus()))
			//	ate.setStatus("6");
			applyTopicService.save(ate);
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "审核意见表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新审核意见表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AuditOpinionEntity auditOpinion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "审核意见表更新成功";
		AuditOpinionEntity t = auditOpinionService.get(AuditOpinionEntity.class, auditOpinion.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(auditOpinion, t);
			auditOpinionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "审核意见表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 审核意见表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AuditOpinionEntity auditOpinion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(auditOpinion.getId())) {
			auditOpinion = auditOpinionService.getEntity(AuditOpinionEntity.class, auditOpinion.getId());
			req.setAttribute("auditOpinionPage", auditOpinion);
		}
		return new ModelAndView("com/research/opinion/auditOpinion-add");
	}
	/**
	 * 审核意见表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AuditOpinionEntity auditOpinion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(auditOpinion.getId())) {
			auditOpinion = auditOpinionService.getEntity(AuditOpinionEntity.class, auditOpinion.getId());
			req.setAttribute("auditOpinionPage", auditOpinion);
		}
		return new ModelAndView("com/research/opinion/auditOpinion-update");
	}
}

package com.research.controller.apply;
import java.util.Date;
import java.util.HashMap;
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
import org.qihuasoft.web.system.pojo.base.TSRole;
import org.qihuasoft.web.system.pojo.base.TSRoleUser;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.research.entity.apply.ApplyTopicEntity;
import com.research.service.apply.ApplyTemplateServiceI;
import com.research.service.apply.ApplyTopicServiceI;

/**   
 * @Title: Controller
 * @Description: 校本课题申报表
 * @author onlineGenerator
 * @date 2016-07-24 21:33:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/applyTopicController")
public class ApplyTopicController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplyTopicController.class);
	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private ApplyTopicServiceI applyTopicService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@RequestMapping(params = "applyTopicMain")
	public ModelAndView applyTopicMain(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/apply/applyTopicMain");
	}
	@RequestMapping(params = "applyTopicMainView")
	public ModelAndView applyTopicMainView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/apply/applyTopicMainView");
	}
	/**
	 * 校本课题申报表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "applyTopic")
	public ModelAndView applyTopic(HttpServletRequest request) {
		return new ModelAndView("com/research/apply/applyTopicList");
	}
	
	@RequestMapping(params = "applyTopicAudit")
	public ModelAndView applyTopicAudit(HttpServletRequest request) {
		return new ModelAndView("com/research/apply/applyTopicAuditList");
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
	public void datagrid(ApplyTopicEntity applyTopic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyTopic, request.getParameterMap());
		try{
		//自定义追加查询条件
			String roles = "";
			TSUser user = ResourceUtil.getSessionUserName();
			List<TSRoleUser> rUsers = systemService.findByProperty(
					TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				roles += role.getRoleCode() + ",";
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			if(roles!=null && roles.indexOf("admin")==-1){
				CriteriaQuery cq1 = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
				cq1.eq("createUser", user.getId());
				cq1.like("groupMember", "%"+user.getId()+"%");
				cq.add(cq.or(cq1, 0, 1));
			}
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.applyTopicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "auditdatagrid")
	public void auditdatagrid(ApplyTopicEntity applyTopic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyTopic, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("status", "1");
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
			cq.add();
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		this.applyTopicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "researchDatagrid")
	public void researchDatagrid(ApplyTopicEntity applyTopic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyTopic, request.getParameterMap());
		try{
		//自定义追加查询条件
			String roles = "";
			TSUser user = ResourceUtil.getSessionUserName();
			List<TSRoleUser> rUsers = systemService.findByProperty(
					TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				roles += role.getRoleCode() + ",";
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			if(roles!=null && roles.indexOf("admin")==-1){
				//cq.eq("createUser", user.getId());
				CriteriaQuery cq1 = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
				cq1.eq("createUser", user.getId());
				cq1.like("groupMember", "%"+user.getId()+"%");
				cq.add(cq.or(cq1, 0, 1));
			}
			cq.eq("status", "3");
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
			cq.add();
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		this.applyTopicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "conclusionDatagrid")
	public void conclusionDatagrid(ApplyTopicEntity applyTopic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyTopic, request.getParameterMap());
		try{
		//自定义追加查询条件
			String roles = "";
			TSUser user = ResourceUtil.getSessionUserName();
			List<TSRoleUser> rUsers = systemService.findByProperty(
					TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				roles += role.getRoleCode() + ",";
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			if(roles!=null && roles.indexOf("admin")==-1){
				cq.eq("createUser", user.getId());
				
			}
			Object[] obj = new Object[2];
			obj[0]="3";
			obj[1]="4";
			cq.in("status", obj);
			
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
			
			cq.add();
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		this.applyTopicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "conclusionAuditDatagrid")
	public void conclusionAuditDatagrid(ApplyTopicEntity applyTopic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ApplyTopicEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, applyTopic, request.getParameterMap());
		try{
		//自定义追加查询条件
			String roles = "";
			TSUser user = ResourceUtil.getSessionUserName();
			List<TSRoleUser> rUsers = systemService.findByProperty(
					TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				roles += role.getRoleCode() + ",";
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			if(roles!=null && roles.indexOf("admin")==-1){
				cq.eq("createUser", user.getId());
				
			}
			Object[] obj = new Object[2];
			obj[0]="4";
			cq.in("status", obj);
			
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
			
			cq.add();
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		this.applyTopicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 删除校本课题申报表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ApplyTopicEntity applyTopic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		applyTopic = systemService.getEntity(ApplyTopicEntity.class, applyTopic.getId());
		message = "校本课题申报表删除成功";
		try{
			applyTopicService.delete(applyTopic);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "校本课题申报表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除校本课题申报表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "校本课题申报表删除成功";
		try{
			for(String id:ids.split(",")){
				ApplyTopicEntity applyTopic = systemService.getEntity(ApplyTopicEntity.class, 
				id
				);
				applyTopicService.delete(applyTopic);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "校本课题申报表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加校本课题申报表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ApplyTopicEntity applyTopic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "校本课题申报表添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			applyTopic.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				applyTopic.setCreateOrg(user.getDepartid());
			}
			applyTopic.setCreateTime(new Date());
			applyTopicService.save(applyTopic);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "校本课题申报表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新校本课题申报表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ApplyTopicEntity applyTopic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "校本课题申报表更新成功";
		ApplyTopicEntity t = applyTopicService.get(ApplyTopicEntity.class, applyTopic.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(applyTopic, t);
			System.out.println(">>>"+t.getStatus());
			applyTopicService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "校本课题申报表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 校本课题申报表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ApplyTopicEntity applyTopic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyTopic.getId())) {
			applyTopic = applyTopicService.getEntity(ApplyTopicEntity.class, applyTopic.getId());
			req.setAttribute("applyTopicPage", applyTopic);
		}
		String sql = "select id,typename from `t_s_type` where typegroupid='4028ef8156214dac01562159c1db0003'";
		List xkList = systemService.findListbySql(sql);
		String researchGroupSql = "select id,name from `st_research_group`";
		List researchGroupList = systemService.findListbySql(researchGroupSql);
		req.setAttribute("researchGroupList", researchGroupList);
		req.setAttribute("xkList", xkList);
		List templateList = applyTemplateService.findHql("from ApplyTemplateEntity where type='WTMS'");
		if(templateList!=null && templateList.size()>0){
			req.setAttribute("templateEntity", templateList.get(0));
		}
		return new ModelAndView("com/research/apply/applyTopic-add");
	}
	/**
	 * 校本课题申报表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ApplyTopicEntity applyTopic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyTopic.getId())) {
			applyTopic = applyTopicService.getEntity(ApplyTopicEntity.class, applyTopic.getId());
			req.setAttribute("applyTopicPage", applyTopic);
		}
		String sql = "select id,typename from `t_s_type` where typegroupid='4028ef8156214dac01562159c1db0003'";
		List xkList = systemService.findListbySql(sql);
		req.setAttribute("xkList", xkList);
		String citysql = "SELECT id,name FROM `st_research_group_type` where parent_id='"+applyTopic.getResearch()+"'";
		List cityList = applyTopicService.findListbySql(citysql);
		req.setAttribute("smallList", cityList);
		return new ModelAndView("com/research/apply/applyTopic-update");
	}
	
	@RequestMapping(params = "goView")
	public ModelAndView goView(ApplyTopicEntity applyTopic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyTopic.getId())) {
			applyTopic = applyTopicService.getEntity(ApplyTopicEntity.class, applyTopic.getId());
			req.setAttribute("applyTopicPage", applyTopic);
		}
		String sql = "select id,typename from `t_s_type` where typegroupid='4028ef8156214dac01562159c1db0003'";
		List xkList = systemService.findListbySql(sql);
		req.setAttribute("xkList", xkList);
		return new ModelAndView("com/research/apply/applyTopic-view");
	}
	
	@RequestMapping(params = "goViewNoAudit")
	public ModelAndView goViewNoAudit(ApplyTopicEntity applyTopic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(applyTopic.getId())) {
			applyTopic = applyTopicService.getEntity(ApplyTopicEntity.class, applyTopic.getId());
			req.setAttribute("applyTopicPage", applyTopic);
		}
		String sql = "select id,typename from `t_s_type` where typegroupid='4028ef8156214dac01562159c1db0003'";
		List xkList = systemService.findListbySql(sql);
		req.setAttribute("xkList", xkList);
		return new ModelAndView("com/research/apply/applyTopic-viewNoAudit");
	}
	@RequestMapping(params = "getCity")
	@ResponseBody
	public AjaxJson getCity(String province, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "城市获取成功";
		String sql = "SELECT id,name FROM `st_research_group_type` where parent_id='"+province+"'";
		List cityList = applyTopicService.findListbySql(sql);
		j.setObj(cityList);
		j.setMsg(message);
		return j;
	}
}

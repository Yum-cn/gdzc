package com.research.controller.process;
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

import com.research.entity.process.SeeResourcesEntity;
import com.research.service.process.SeeResourcesServiceI;

/**   
 * @Title: Controller
 * @Description: 参阅资料
 * @author onlineGenerator
 * @date 2016-07-27 13:41:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/seeResourcesController")
public class SeeResourcesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SeeResourcesController.class);

	@Autowired
	private SeeResourcesServiceI seeResourcesService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@RequestMapping(params = "seeResourcesMain")
	public ModelAndView seeResourcesMain(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/seeResources/seeResourcesMain");
	}
	@RequestMapping(params = "topicList")
	public ModelAndView topicList(HttpServletRequest request) {
		return new ModelAndView("com/research/process/seeResources/topicPlanList");
	}
	/**
	 * 参阅资料列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "seeResources")
	public ModelAndView seeResources(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/seeResources/seeResourcesList");
	}
	@RequestMapping(params = "seeResourcesView")
	public ModelAndView seeResourcesView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/seeResources/seeResourcesViewList");
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
	public void datagrid(SeeResourcesEntity seeResources,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SeeResourcesEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, seeResources, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.seeResourcesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除参阅资料
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(SeeResourcesEntity seeResources, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		seeResources = systemService.getEntity(SeeResourcesEntity.class, seeResources.getId());
		message = "参阅资料删除成功";
		try{
			seeResourcesService.delete(seeResources);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "参阅资料删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除参阅资料
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "参阅资料删除成功";
		try{
			for(String id:ids.split(",")){
				SeeResourcesEntity seeResources = systemService.getEntity(SeeResourcesEntity.class, 
				id
				);
				seeResourcesService.delete(seeResources);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "参阅资料删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加参阅资料
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(SeeResourcesEntity seeResources, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "参阅资料添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			seeResources.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				seeResources.setCreateOrg(user.getDepartid());
			}
			seeResources.setCreateDate(new Date());
			seeResourcesService.save(seeResources);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "参阅资料添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新参阅资料
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(SeeResourcesEntity seeResources, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "参阅资料更新成功";
		SeeResourcesEntity t = seeResourcesService.get(SeeResourcesEntity.class, seeResources.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(seeResources, t);
			seeResourcesService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "参阅资料更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 参阅资料新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(SeeResourcesEntity seeResources, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(seeResources.getId())) {
			seeResources = seeResourcesService.getEntity(SeeResourcesEntity.class, seeResources.getId());
			req.setAttribute("seeResourcesPage", seeResources);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/seeResources/seeResources-add");
	}
	/**
	 * 参阅资料编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(SeeResourcesEntity seeResources, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(seeResources.getId())) {
			seeResources = seeResourcesService.getEntity(SeeResourcesEntity.class, seeResources.getId());
			req.setAttribute("seeResourcesPage", seeResources);
		}
		return new ModelAndView("com/research/process/seeResources/seeResources-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(SeeResourcesEntity seeResources, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(seeResources.getId())) {
			seeResources = seeResourcesService.getEntity(SeeResourcesEntity.class, seeResources.getId());
			req.setAttribute("seeResourcesPage", seeResources);
		}
		return new ModelAndView("com/research/process/seeResources/seeResources-view");
	}
}

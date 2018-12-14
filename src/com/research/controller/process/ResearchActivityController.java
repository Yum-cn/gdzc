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

import com.research.entity.process.ResearchActivityEntity;
import com.research.service.apply.ApplyTemplateServiceI;
import com.research.service.process.ResearchActivityServiceI;

/**   
 * @Title: Controller
 * @Description: 科研活动管理
 * @author onlineGenerator
 * @date 2016-07-27 12:38:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/researchActivityController")
public class ResearchActivityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResearchActivityController.class);
	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private ResearchActivityServiceI researchActivityService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "topicList")
	public ModelAndView topicList(HttpServletRequest request) {
		return new ModelAndView("com/research/process/activity/topicPlanList");
	}
	/**
	 * 科研活动管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "researchActivity")
	public ModelAndView researchActivity(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/activity/researchActivityList");
	}
	@RequestMapping(params = "researchActivityView")
	public ModelAndView researchActivityView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/activity/researchActivityViewList");
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
	public void datagrid(ResearchActivityEntity researchActivity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ResearchActivityEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, researchActivity, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.researchActivityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除科研活动管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ResearchActivityEntity researchActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		researchActivity = systemService.getEntity(ResearchActivityEntity.class, researchActivity.getId());
		message = "科研活动管理删除成功";
		try{
			researchActivityService.delete(researchActivity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科研活动管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除科研活动管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "科研活动管理删除成功";
		try{
			for(String id:ids.split(",")){
				ResearchActivityEntity researchActivity = systemService.getEntity(ResearchActivityEntity.class, 
				id
				);
				researchActivityService.delete(researchActivity);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "科研活动管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科研活动管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ResearchActivityEntity researchActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "科研活动管理添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			researchActivity.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				researchActivity.setCreateOrg(user.getDepartid());
			}
			researchActivity.setCreateDate(new Date());
			researchActivityService.save(researchActivity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科研活动管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新科研活动管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ResearchActivityEntity researchActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "科研活动管理更新成功";
		ResearchActivityEntity t = researchActivityService.get(ResearchActivityEntity.class, researchActivity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(researchActivity, t);
			researchActivityService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "科研活动管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 科研活动管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ResearchActivityEntity researchActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchActivity.getId())) {
			researchActivity = researchActivityService.getEntity(ResearchActivityEntity.class, researchActivity.getId());
			req.setAttribute("researchActivityPage", researchActivity);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		List templateList = applyTemplateService.findHql("from ApplyTemplateEntity where type='HDGL'");
		if(templateList!=null && templateList.size()>0){
			req.setAttribute("templateEntity", templateList.get(0));
		}
		return new ModelAndView("com/research/process/activity/researchActivity-add");
	}
	/**
	 * 科研活动管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ResearchActivityEntity researchActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchActivity.getId())) {
			researchActivity = researchActivityService.getEntity(ResearchActivityEntity.class, researchActivity.getId());
			req.setAttribute("researchActivityPage", researchActivity);
		}
		return new ModelAndView("com/research/process/activity/researchActivity-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ResearchActivityEntity researchActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchActivity.getId())) {
			researchActivity = researchActivityService.getEntity(ResearchActivityEntity.class, researchActivity.getId());
			req.setAttribute("researchActivityPage", researchActivity);
		}
		return new ModelAndView("com/research/process/activity/researchActivity-view");
	}
}

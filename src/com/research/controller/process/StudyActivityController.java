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

import com.research.entity.process.StudyActivityEntity;
import com.research.service.process.StudyActivityServiceI;

/**   
 * @Title: Controller
 * @Description: 研究活动管理
 * @author onlineGenerator
 * @date 2016-08-12 20:49:03
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/studyActivityController")
public class StudyActivityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StudyActivityController.class);

	@Autowired
	private StudyActivityServiceI studyActivityService;
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
		return new ModelAndView("com/research/process/study/topicPlanList");
	}

	/**
	 * 研究活动管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "studyActivity")
	public ModelAndView studyActivity(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/study/studyActivityList");
	}
	@RequestMapping(params = "studyActivityView")
	public ModelAndView studyActivityView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/study/studyActivityViewList");
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
	public void datagrid(StudyActivityEntity studyActivity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StudyActivityEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, studyActivity, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.studyActivityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除研究活动管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(StudyActivityEntity studyActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		studyActivity = systemService.getEntity(StudyActivityEntity.class, studyActivity.getId());
		message = "研究活动管理删除成功";
		try{
			studyActivityService.delete(studyActivity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究活动管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除研究活动管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "研究活动管理删除成功";
		try{
			for(String id:ids.split(",")){
				StudyActivityEntity studyActivity = systemService.getEntity(StudyActivityEntity.class, 
				id
				);
				studyActivityService.delete(studyActivity);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "研究活动管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加研究活动管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(StudyActivityEntity studyActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究活动管理添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			studyActivity.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				studyActivity.setCreateOrg(user.getDepartid());
			}
			studyActivity.setCreateTime(new Date());
			studyActivityService.save(studyActivity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究活动管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新研究活动管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(StudyActivityEntity studyActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究活动管理更新成功";
		StudyActivityEntity t = studyActivityService.get(StudyActivityEntity.class, studyActivity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(studyActivity, t);
			studyActivityService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "研究活动管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 研究活动管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(StudyActivityEntity studyActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(studyActivity.getId())) {
			studyActivity = studyActivityService.getEntity(StudyActivityEntity.class, studyActivity.getId());
			req.setAttribute("studyActivityPage", studyActivity);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/study/studyActivity-add");
	}
	/**
	 * 研究活动管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(StudyActivityEntity studyActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(studyActivity.getId())) {
			studyActivity = studyActivityService.getEntity(StudyActivityEntity.class, studyActivity.getId());
			req.setAttribute("studyActivityPage", studyActivity);
		}
		return new ModelAndView("com/research/process/study/studyActivity-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(StudyActivityEntity studyActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(studyActivity.getId())) {
			studyActivity = studyActivityService.getEntity(StudyActivityEntity.class, studyActivity.getId());
			req.setAttribute("studyActivityPage", studyActivity);
		}
		return new ModelAndView("com/research/process/study/studyActivity-view");
	}
}

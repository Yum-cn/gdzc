package com.research.controller.process;
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
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.research.entity.process.LessonPlanEntity;
import com.research.service.apply.ApplyTemplateServiceI;
import com.research.service.process.LessonPlanServiceI;

/**   
 * @Title: Controller
 * @Description: 教案管理
 * @author onlineGenerator
 * @date 2016-07-27 12:03:05
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lessonPlanController")
public class LessonPlanController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LessonPlanController.class);
	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private LessonPlanServiceI lessonPlanService;
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
		return new ModelAndView("com/research/process/lessonPlan/topicPlanList");
	}
	/**
	 * 教案管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "lessonPlan")
	public ModelAndView lessonPlan(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/lessonPlan/lessonPlanList");
	}
	@RequestMapping(params = "lessonViewPlan")
	public ModelAndView lessonViewPlan(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/lessonPlan/lessonPlanViewList");
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
	public void datagrid(LessonPlanEntity lessonPlan,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LessonPlanEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lessonPlan, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lessonPlanService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除教案管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LessonPlanEntity lessonPlan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		lessonPlan = systemService.getEntity(LessonPlanEntity.class, lessonPlan.getId());
		message = "教案管理删除成功";
		try{
			lessonPlanService.delete(lessonPlan);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "教案管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除教案管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "教案管理删除成功";
		try{
			for(String id:ids.split(",")){
				LessonPlanEntity lessonPlan = systemService.getEntity(LessonPlanEntity.class, 
				id
				);
				lessonPlanService.delete(lessonPlan);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "教案管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加教案管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LessonPlanEntity lessonPlan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "教案管理添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			lessonPlan.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				lessonPlan.setCreateOrg(user.getDepartid());
			}
			lessonPlan.setCreateTime(new Date());
			lessonPlanService.save(lessonPlan);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "教案管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新教案管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LessonPlanEntity lessonPlan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "教案管理更新成功";
		LessonPlanEntity t = lessonPlanService.get(LessonPlanEntity.class, lessonPlan.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lessonPlan, t);
			lessonPlanService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "教案管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 教案管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LessonPlanEntity lessonPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lessonPlan.getId())) {
			lessonPlan = lessonPlanService.getEntity(LessonPlanEntity.class, lessonPlan.getId());
			req.setAttribute("lessonPlanPage", lessonPlan);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		List templateList = applyTemplateService.findHql("from ApplyTemplateEntity where type='JAGL'");
		if(templateList!=null && templateList.size()>0){
			req.setAttribute("templateEntity", templateList.get(0));
		}
		return new ModelAndView("com/research/process/lessonPlan/lessonPlan-add");
	}
	/**
	 * 教案管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LessonPlanEntity lessonPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lessonPlan.getId())) {
			lessonPlan = lessonPlanService.getEntity(LessonPlanEntity.class, lessonPlan.getId());
			req.setAttribute("lessonPlanPage", lessonPlan);
		}
		return new ModelAndView("com/research/process/lessonPlan/lessonPlan-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(LessonPlanEntity lessonPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lessonPlan.getId())) {
			lessonPlan = lessonPlanService.getEntity(LessonPlanEntity.class, lessonPlan.getId());
			req.setAttribute("lessonPlanPage", lessonPlan);
		}
		return new ModelAndView("com/research/process/lessonPlan/lessonPlan-view");
	}
}

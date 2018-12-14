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

import com.research.entity.process.ProcessPlanEntity;
import com.research.service.process.ProcessPlanServiceI;

/**   
 * @Title: Controller
 * @Description: 工作计划
 * @author onlineGenerator
 * @date 2016-07-26 13:40:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/processPlanController")
public class ProcessPlanController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessPlanController.class);

	@Autowired
	private ProcessPlanServiceI processPlanService;
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
		return new ModelAndView("com/research/process/plan/topicPlanList");
	}

	/**
	 * 工作计划列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "processPlan")
	public ModelAndView processPlan(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/plan/processPlanList");
	}
	@RequestMapping(params = "processViewPlan")
	public ModelAndView processViewPlan(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/plan/processPlanViewList");
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
	public void datagrid(ProcessPlanEntity processPlan,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProcessPlanEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, processPlan, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createDate", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.processPlanService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工作计划
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ProcessPlanEntity processPlan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		processPlan = systemService.getEntity(ProcessPlanEntity.class, processPlan.getId());
		message = "工作计划删除成功";
		try{
			processPlanService.delete(processPlan);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工作计划删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工作计划
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "工作计划删除成功";
		try{
			for(String id:ids.split(",")){
				ProcessPlanEntity processPlan = systemService.getEntity(ProcessPlanEntity.class, 
				id
				);
				processPlanService.delete(processPlan);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工作计划删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProcessPlanEntity processPlan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工作计划添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			processPlan.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				processPlan.setCreateOrg(user.getDepartid());
			}
			processPlan.setCreateDate(new Date());
			processPlanService.save(processPlan);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工作计划添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工作计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProcessPlanEntity processPlan, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工作计划更新成功";
		ProcessPlanEntity t = processPlanService.get(ProcessPlanEntity.class, processPlan.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(processPlan, t);
			processPlanService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工作计划更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工作计划新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ProcessPlanEntity processPlan, HttpServletRequest req) {
		if (processPlan!=null) {
			//processPlan = processPlanService.getEntity(ProcessPlanEntity.class, processPlan.getId());
			req.setAttribute("processPlanPage", processPlan);
		}
		return new ModelAndView("com/research/process/plan/processPlan-add");
	}
	/**
	 * 工作计划编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ProcessPlanEntity processPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(processPlan.getId())) {
			processPlan = processPlanService.getEntity(ProcessPlanEntity.class, processPlan.getId());
			req.setAttribute("processPlanPage", processPlan);
		}
		return new ModelAndView("com/research/process/plan/processPlan-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ProcessPlanEntity processPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(processPlan.getId())) {
			processPlan = processPlanService.getEntity(ProcessPlanEntity.class, processPlan.getId());
			req.setAttribute("processPlanPage", processPlan);
		}
		return new ModelAndView("com/research/process/plan/processPlan-view");
	}
}

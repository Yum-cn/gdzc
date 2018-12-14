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

import com.research.entity.process.InterflowEntity;
import com.research.service.process.InterflowServiceI;

/**   
 * @Title: Controller
 * @Description: 交流活动
 * @author onlineGenerator
 * @date 2016-08-12 20:40:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/interflowController")
public class InterflowController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InterflowController.class);

	@Autowired
	private InterflowServiceI interflowService;
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
		return new ModelAndView("com/research/process/interflow/topicPlanList");
	}
	/**
	 * 交流活动列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "interflow")
	public ModelAndView interflow(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/interflow/interflowList");
	}
	@RequestMapping(params = "interflowView")
	public ModelAndView interflowView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/interflow/interflowViewList");
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
	public void datagrid(InterflowEntity interflow,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InterflowEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, interflow, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.interflowService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除交流活动
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(InterflowEntity interflow, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		interflow = systemService.getEntity(InterflowEntity.class, interflow.getId());
		message = "交流活动删除成功";
		try{
			interflowService.delete(interflow);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "交流活动删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除交流活动
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "交流活动删除成功";
		try{
			for(String id:ids.split(",")){
				InterflowEntity interflow = systemService.getEntity(InterflowEntity.class, 
				id
				);
				interflowService.delete(interflow);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "交流活动删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加交流活动
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(InterflowEntity interflow, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "交流活动添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			interflow.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				interflow.setCreateOrg(user.getDepartid());
			}
			interflow.setCreateTime(new Date());
			interflowService.save(interflow);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "交流活动添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新交流活动
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(InterflowEntity interflow, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "交流活动更新成功";
		InterflowEntity t = interflowService.get(InterflowEntity.class, interflow.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(interflow, t);
			interflowService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "交流活动更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 交流活动新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(InterflowEntity interflow, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(interflow.getId())) {
			interflow = interflowService.getEntity(InterflowEntity.class, interflow.getId());
			req.setAttribute("interflowPage", interflow);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/interflow/interflow-add");
	}
	/**
	 * 交流活动编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(InterflowEntity interflow, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(interflow.getId())) {
			interflow = interflowService.getEntity(InterflowEntity.class, interflow.getId());
			req.setAttribute("interflowPage", interflow);
		}
		return new ModelAndView("com/research/process/interflow/interflow-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(InterflowEntity interflow, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(interflow.getId())) {
			interflow = interflowService.getEntity(InterflowEntity.class, interflow.getId());
			req.setAttribute("interflowPage", interflow);
		}
		return new ModelAndView("com/research/process/interflow/interflow-view");
	}
}

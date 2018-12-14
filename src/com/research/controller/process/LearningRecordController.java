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

import com.research.entity.process.LearningRecordEntity;
import com.research.service.process.LearningRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 学习记录
 * @author onlineGenerator
 * @date 2016-08-14 10:42:01
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/learningRecordController")
public class LearningRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LearningRecordController.class);

	@Autowired
	private LearningRecordServiceI learningRecordService;
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
	 * 学习记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "learningRecord")
	public ModelAndView learningRecord(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/learning/learningRecordList");
	}
	@RequestMapping(params = "learningRecordView")
	public ModelAndView learningRecordView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/learning/learningRecordViewList");
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
	public void datagrid(LearningRecordEntity learningRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LearningRecordEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, learningRecord, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.learningRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除学习记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LearningRecordEntity learningRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		learningRecord = systemService.getEntity(LearningRecordEntity.class, learningRecord.getId());
		message = "学习记录删除成功";
		try{
			learningRecordService.delete(learningRecord);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "学习记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除学习记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "学习记录删除成功";
		try{
			for(String id:ids.split(",")){
				LearningRecordEntity learningRecord = systemService.getEntity(LearningRecordEntity.class, 
				id
				);
				learningRecordService.delete(learningRecord);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "学习记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加学习记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LearningRecordEntity learningRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "学习记录添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			learningRecord.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				learningRecord.setCreateOrg(user.getDepartid());
			}
			learningRecord.setCreateDate(new Date());
			learningRecordService.save(learningRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "学习记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新学习记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LearningRecordEntity learningRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "学习记录更新成功";
		LearningRecordEntity t = learningRecordService.get(LearningRecordEntity.class, learningRecord.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(learningRecord, t);
			learningRecordService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "学习记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 学习记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LearningRecordEntity learningRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(learningRecord.getId())) {
			learningRecord = learningRecordService.getEntity(LearningRecordEntity.class, learningRecord.getId());
			req.setAttribute("learningRecordPage", learningRecord);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/learning/learningRecord-add");
	}
	/**
	 * 学习记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LearningRecordEntity learningRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(learningRecord.getId())) {
			learningRecord = learningRecordService.getEntity(LearningRecordEntity.class, learningRecord.getId());
			req.setAttribute("learningRecordPage", learningRecord);
		}
		return new ModelAndView("com/research/process/learning/learningRecord-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(LearningRecordEntity learningRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(learningRecord.getId())) {
			learningRecord = learningRecordService.getEntity(LearningRecordEntity.class, learningRecord.getId());
			req.setAttribute("learningRecordPage", learningRecord);
		}
		return new ModelAndView("com/research/process/learning/learningRecord-view");
	}
}

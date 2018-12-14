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

import com.research.entity.process.ObservationRecordEntity;
import com.research.service.process.ObservationRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 观察记录
 * @author onlineGenerator
 * @date 2016-08-12 20:34:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/observationRecordController")
public class ObservationRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ObservationRecordController.class);

	@Autowired
	private ObservationRecordServiceI observationRecordService;
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
		return new ModelAndView("com/research/process/observation/topicPlanList");
	}

	/**
	 * 观察记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "observationRecord")
	public ModelAndView observationRecord(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/observation/observationRecordList");
	}
	@RequestMapping(params = "observationRecordView")
	public ModelAndView observationRecordView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/observation/observationRecordViewList");
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
	public void datagrid(ObservationRecordEntity observationRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ObservationRecordEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, observationRecord, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.observationRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除观察记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ObservationRecordEntity observationRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		observationRecord = systemService.getEntity(ObservationRecordEntity.class, observationRecord.getId());
		message = "观察记录删除成功";
		try{
			observationRecordService.delete(observationRecord);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "观察记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除观察记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "观察记录删除成功";
		try{
			for(String id:ids.split(",")){
				ObservationRecordEntity observationRecord = systemService.getEntity(ObservationRecordEntity.class, 
				id
				);
				observationRecordService.delete(observationRecord);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "观察记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加观察记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ObservationRecordEntity observationRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "观察记录添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			observationRecord.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				observationRecord.setCreateOrg(user.getDepartid());
			}
			observationRecord.setCreateTime(new Date());
			observationRecordService.save(observationRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "观察记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新观察记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ObservationRecordEntity observationRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "观察记录更新成功";
		ObservationRecordEntity t = observationRecordService.get(ObservationRecordEntity.class, observationRecord.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(observationRecord, t);
			observationRecordService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "观察记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 观察记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ObservationRecordEntity observationRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(observationRecord.getId())) {
			observationRecord = observationRecordService.getEntity(ObservationRecordEntity.class, observationRecord.getId());
			req.setAttribute("observationRecordPage", observationRecord);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/observation/observationRecord-add");
	}
	/**
	 * 观察记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ObservationRecordEntity observationRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(observationRecord.getId())) {
			observationRecord = observationRecordService.getEntity(ObservationRecordEntity.class, observationRecord.getId());
			req.setAttribute("observationRecordPage", observationRecord);
		}
		return new ModelAndView("com/research/process/observation/observationRecord-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ObservationRecordEntity observationRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(observationRecord.getId())) {
			observationRecord = observationRecordService.getEntity(ObservationRecordEntity.class, observationRecord.getId());
			req.setAttribute("observationRecordPage", observationRecord);
		}
		return new ModelAndView("com/research/process/observation/observationRecord-view");
	}
}

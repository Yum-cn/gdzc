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

import com.research.entity.process.TeachingProgramEntity;
import com.research.service.process.TeachingProgramServiceI;

/**   
 * @Title: Controller
 * @Description: 教学大纲
 * @author onlineGenerator
 * @date 2016-08-15 20:19:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/teachingProgramController")
public class TeachingProgramController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TeachingProgramController.class);

	@Autowired
	private TeachingProgramServiceI teachingProgramService;
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
	 * 教学大纲列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "teachingProgram")
	public ModelAndView teachingProgram(HttpServletRequest request) {
		return new ModelAndView("com/research/process/program/teachingProgramList");
	}
	@RequestMapping(params = "teachingProgramView")
	public ModelAndView teachingProgramView(HttpServletRequest request) {
		return new ModelAndView("com/research/process/program/teachingProgramView");
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
	public void datagrid(TeachingProgramEntity teachingProgram,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TeachingProgramEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, teachingProgram, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.teachingProgramService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除教学大纲
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TeachingProgramEntity teachingProgram, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		teachingProgram = systemService.getEntity(TeachingProgramEntity.class, teachingProgram.getId());
		message = "教学大纲删除成功";
		try{
			teachingProgramService.delete(teachingProgram);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "教学大纲删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除教学大纲
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "教学大纲删除成功";
		try{
			for(String id:ids.split(",")){
				TeachingProgramEntity teachingProgram = systemService.getEntity(TeachingProgramEntity.class, 
				id
				);
				teachingProgramService.delete(teachingProgram);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "教学大纲删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加教学大纲
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TeachingProgramEntity teachingProgram, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "教学大纲添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			teachingProgram.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				teachingProgram.setCreateOrg(user.getDepartid());
			}
			teachingProgram.setCreateTime(new Date());
			teachingProgramService.save(teachingProgram);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "教学大纲添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新教学大纲
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TeachingProgramEntity teachingProgram, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "教学大纲更新成功";
		TeachingProgramEntity t = teachingProgramService.get(TeachingProgramEntity.class, teachingProgram.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(teachingProgram, t);
			teachingProgramService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "教学大纲更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 教学大纲新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TeachingProgramEntity teachingProgram, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(teachingProgram.getId())) {
			teachingProgram = teachingProgramService.getEntity(TeachingProgramEntity.class, teachingProgram.getId());
			req.setAttribute("teachingProgramPage", teachingProgram);
		}
		return new ModelAndView("com/research/process/program/teachingProgram-add");
	}
	/**
	 * 教学大纲编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TeachingProgramEntity teachingProgram, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(teachingProgram.getId())) {
			teachingProgram = teachingProgramService.getEntity(TeachingProgramEntity.class, teachingProgram.getId());
			req.setAttribute("teachingProgramPage", teachingProgram);
		}
		return new ModelAndView("com/research/process/program/teachingProgram-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(TeachingProgramEntity teachingProgram, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(teachingProgram.getId())) {
			teachingProgram = teachingProgramService.getEntity(TeachingProgramEntity.class, teachingProgram.getId());
			req.setAttribute("teachingProgramPage", teachingProgram);
		}
		return new ModelAndView("com/research/process/program/teachingProgram-view");
	}
}

package com.research.controller.config;
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
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.research.entity.config.ResearchGroupTypeEntity;
import com.research.service.config.ResearchGroupTypeServiceI;

/**   
 * @Title: Controller
 * @Description: 研究领域小类
 * @author onlineGenerator
 * @date 2016-08-17 17:45:11
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/researchGroupTypeController")
public class ResearchGroupTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResearchGroupTypeController.class);

	@Autowired
	private ResearchGroupTypeServiceI researchGroupTypeService;
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
	 * 研究领域小类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "researchGroupType")
	public ModelAndView researchGroupType(HttpServletRequest request) {
		String groupId = request.getParameter("groupId");
		request.setAttribute("groupId", groupId);
		return new ModelAndView("com/research/config/researchGroupType/researchGroupTypeList");
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
	public void datagrid(ResearchGroupTypeEntity researchGroupType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ResearchGroupTypeEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, researchGroupType, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.researchGroupTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除研究领域小类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ResearchGroupTypeEntity researchGroupType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		researchGroupType = systemService.getEntity(ResearchGroupTypeEntity.class, researchGroupType.getId());
		message = "研究领域小类删除成功";
		try{
			researchGroupTypeService.delete(researchGroupType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究领域小类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除研究领域小类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "研究领域小类删除成功";
		try{
			for(String id:ids.split(",")){
				ResearchGroupTypeEntity researchGroupType = systemService.getEntity(ResearchGroupTypeEntity.class, 
				id
				);
				researchGroupTypeService.delete(researchGroupType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "研究领域小类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加研究领域小类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ResearchGroupTypeEntity researchGroupType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究领域小类添加成功";
		try{

			researchGroupTypeService.save(researchGroupType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究领域小类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新研究领域小类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ResearchGroupTypeEntity researchGroupType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究领域小类更新成功";
		ResearchGroupTypeEntity t = researchGroupTypeService.get(ResearchGroupTypeEntity.class, researchGroupType.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(researchGroupType, t);
			researchGroupTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "研究领域小类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 研究领域小类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ResearchGroupTypeEntity researchGroupType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchGroupType.getId())) {
			researchGroupType = researchGroupTypeService.getEntity(ResearchGroupTypeEntity.class, researchGroupType.getId());
			req.setAttribute("researchGroupTypePage", researchGroupType);
		}
		String groupId = req.getParameter("groupId");
		req.setAttribute("groupId", groupId);
		return new ModelAndView("com/research/config/researchGroupType/researchGroupType-add");
	}
	/**
	 * 研究领域小类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ResearchGroupTypeEntity researchGroupType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchGroupType.getId())) {
			researchGroupType = researchGroupTypeService.getEntity(ResearchGroupTypeEntity.class, researchGroupType.getId());
			req.setAttribute("researchGroupTypePage", researchGroupType);
		}
		return new ModelAndView("com/research/config/researchGroupType/researchGroupType-update");
	}
}

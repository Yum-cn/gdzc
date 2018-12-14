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

import com.research.entity.config.ResearchGroupEntity;
import com.research.service.config.ResearchGroupServiceI;

/**   
 * @Title: Controller
 * @Description: 研究领域大类
 * @author onlineGenerator
 * @date 2016-08-17 17:45:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/researchGroupController")
public class ResearchGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResearchGroupController.class);

	@Autowired
	private ResearchGroupServiceI researchGroupService;
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
	 * 研究领域大类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "researchGroup")
	public ModelAndView researchGroup(HttpServletRequest request) {
		return new ModelAndView("com/research/config/researchGroup/researchGroupList");
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
	public void datagrid(ResearchGroupEntity researchGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ResearchGroupEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, researchGroup, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.researchGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除研究领域大类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ResearchGroupEntity researchGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		researchGroup = systemService.getEntity(ResearchGroupEntity.class, researchGroup.getId());
		message = "研究领域大类删除成功";
		try{
			researchGroupService.delete(researchGroup);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究领域大类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除研究领域大类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "研究领域大类删除成功";
		try{
			for(String id:ids.split(",")){
				ResearchGroupEntity researchGroup = systemService.getEntity(ResearchGroupEntity.class, 
				id
				);
				researchGroupService.delete(researchGroup);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "研究领域大类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加研究领域大类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ResearchGroupEntity researchGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究领域大类添加成功";
		try{
			researchGroupService.save(researchGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究领域大类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新研究领域大类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ResearchGroupEntity researchGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究领域大类更新成功";
		ResearchGroupEntity t = researchGroupService.get(ResearchGroupEntity.class, researchGroup.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(researchGroup, t);
			researchGroupService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "研究领域大类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 研究领域大类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ResearchGroupEntity researchGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchGroup.getId())) {
			researchGroup = researchGroupService.getEntity(ResearchGroupEntity.class, researchGroup.getId());
			req.setAttribute("researchGroupPage", researchGroup);
		}
		return new ModelAndView("com/research/config/researchGroup/researchGroup-add");
	}
	/**
	 * 研究领域大类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ResearchGroupEntity researchGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchGroup.getId())) {
			researchGroup = researchGroupService.getEntity(ResearchGroupEntity.class, researchGroup.getId());
			req.setAttribute("researchGroupPage", researchGroup);
		}
		return new ModelAndView("com/research/config/researchGroup/researchGroup-update");
	}
}

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

import com.research.entity.process.ResearchReflectEntity;
import com.research.service.apply.ApplyTemplateServiceI;
import com.research.service.process.ResearchReflectServiceI;

/**   
 * @Title: Controller
 * @Description: 研究反思
 * @author onlineGenerator
 * @date 2016-07-29 11:52:36
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/researchReflectController")
public class ResearchReflectController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResearchReflectController.class);
	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private ResearchReflectServiceI researchReflectService;
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
		return new ModelAndView("com/research/process/reflect/topicPlanList");
	}
	/**
	 * 研究反思列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "researchReflect")
	public ModelAndView researchReflect(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/reflect/researchReflectList");
	}
	@RequestMapping(params = "researchReflectView")
	public ModelAndView researchReflectView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/reflect/researchReflectViewList");
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
	public void datagrid(ResearchReflectEntity researchReflect,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ResearchReflectEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, researchReflect, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createDate", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.researchReflectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除研究反思
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ResearchReflectEntity researchReflect, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		researchReflect = systemService.getEntity(ResearchReflectEntity.class, researchReflect.getId());
		message = "研究反思删除成功";
		try{
			researchReflectService.delete(researchReflect);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究反思删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除研究反思
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "研究反思删除成功";
		try{
			for(String id:ids.split(",")){
				ResearchReflectEntity researchReflect = systemService.getEntity(ResearchReflectEntity.class, 
				id
				);
				researchReflectService.delete(researchReflect);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "研究反思删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加研究反思
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ResearchReflectEntity researchReflect, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究反思添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			researchReflect.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				researchReflect.setCreateOrg(user.getDepartid());
			}
			researchReflect.setCreateDate(new Date());
			researchReflectService.save(researchReflect);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "研究反思添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新研究反思
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ResearchReflectEntity researchReflect, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "研究反思更新成功";
		ResearchReflectEntity t = researchReflectService.get(ResearchReflectEntity.class, researchReflect.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(researchReflect, t);
			researchReflectService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "研究反思更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 研究反思新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ResearchReflectEntity researchReflect, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchReflect.getId())) {
			researchReflect = researchReflectService.getEntity(ResearchReflectEntity.class, researchReflect.getId());
			req.setAttribute("researchReflectPage", researchReflect);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		List templateList = applyTemplateService.findHql("from ApplyTemplateEntity where type='ALGL'");
		if(templateList!=null && templateList.size()>0){
			req.setAttribute("templateEntity", templateList.get(0));
		}
		return new ModelAndView("com/research/process/reflect/researchReflect-add");
	}
	/**
	 * 研究反思编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ResearchReflectEntity researchReflect, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchReflect.getId())) {
			researchReflect = researchReflectService.getEntity(ResearchReflectEntity.class, researchReflect.getId());
			req.setAttribute("researchReflectPage", researchReflect);
		}
		return new ModelAndView("com/research/process/reflect/researchReflect-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ResearchReflectEntity researchReflect, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchReflect.getId())) {
			researchReflect = researchReflectService.getEntity(ResearchReflectEntity.class, researchReflect.getId());
			req.setAttribute("researchReflectPage", researchReflect);
		}
		return new ModelAndView("com/research/process/reflect/researchReflect-view");
	}
}

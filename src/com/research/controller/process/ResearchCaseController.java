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

import com.research.entity.process.ResearchCaseEntity;
import com.research.service.apply.ApplyTemplateServiceI;
import com.research.service.process.ResearchCaseServiceI;

/**   
 * @Title: Controller
 * @Description: 案例管理
 * @author onlineGenerator
 * @date 2016-07-27 13:27:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/researchCaseController")
public class ResearchCaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResearchCaseController.class);
	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private ResearchCaseServiceI researchCaseService;
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
		return new ModelAndView("com/research/process/case/topicPlanList");
	}
	/**
	 * 案例管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "researchCase")
	public ModelAndView researchCase(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/case/researchCaseList");
	}
	@RequestMapping(params = "researchCaseView")
	public ModelAndView researchCaseView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/case/researchCaseViewList");
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
	public void datagrid(ResearchCaseEntity researchCase,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ResearchCaseEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, researchCase, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.researchCaseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除案例管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ResearchCaseEntity researchCase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		researchCase = systemService.getEntity(ResearchCaseEntity.class, researchCase.getId());
		message = "案例管理删除成功";
		try{
			researchCaseService.delete(researchCase);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "案例管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除案例管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "案例管理删除成功";
		try{
			for(String id:ids.split(",")){
				ResearchCaseEntity researchCase = systemService.getEntity(ResearchCaseEntity.class, 
				id
				);
				researchCaseService.delete(researchCase);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "案例管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加案例管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ResearchCaseEntity researchCase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "案例管理添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			researchCase.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				researchCase.setCreateOrg(user.getDepartid());
			}
			researchCase.setCreateTime(new Date());
			researchCaseService.save(researchCase);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "案例管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新案例管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ResearchCaseEntity researchCase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "案例管理更新成功";
		ResearchCaseEntity t = researchCaseService.get(ResearchCaseEntity.class, researchCase.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(researchCase, t);
			researchCaseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "案例管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 案例管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ResearchCaseEntity researchCase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchCase.getId())) {
			researchCase = researchCaseService.getEntity(ResearchCaseEntity.class, researchCase.getId());
			req.setAttribute("researchCasePage", researchCase);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		List templateList = applyTemplateService.findHql("from ApplyTemplateEntity where type='ALGL'");
		if(templateList!=null && templateList.size()>0){
			req.setAttribute("templateEntity", templateList.get(0));
		}
		return new ModelAndView("com/research/process/case/researchCase-add");
	}
	/**
	 * 案例管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ResearchCaseEntity researchCase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchCase.getId())) {
			researchCase = researchCaseService.getEntity(ResearchCaseEntity.class, researchCase.getId());
			req.setAttribute("researchCasePage", researchCase);
		}
		return new ModelAndView("com/research/process/case/researchCase-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ResearchCaseEntity researchCase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchCase.getId())) {
			researchCase = researchCaseService.getEntity(ResearchCaseEntity.class, researchCase.getId());
			req.setAttribute("researchCasePage", researchCase);
		}
		return new ModelAndView("com/research/process/case/researchCase-view");
	}
}

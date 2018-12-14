package com.research.controller.process;
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

import com.research.entity.process.StageSummaryEntity;
import com.research.service.apply.ApplyTemplateServiceI;
import com.research.service.process.StageSummaryServiceI;

/**   
 * @Title: Controller
 * @Description: 阶段性总结
 * @author onlineGenerator
 * @date 2016-07-27 13:50:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/stageSummaryController")
public class StageSummaryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StageSummaryController.class);
	@Autowired
	private ApplyTemplateServiceI applyTemplateService;
	@Autowired
	private StageSummaryServiceI stageSummaryService;
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
		return new ModelAndView("com/research/process/stageSummary/topicPlanList");
	}
	/**
	 * 阶段性总结列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "stageSummary")
	public ModelAndView stageSummary(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/stageSummary/stageSummaryList");
	}
	@RequestMapping(params = "stageSummaryView")
	public ModelAndView stageSummaryView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/stageSummary/stageSummaryViewList");
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
	public void datagrid(StageSummaryEntity stageSummary,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StageSummaryEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, stageSummary, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createDate", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.stageSummaryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除阶段性总结
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(StageSummaryEntity stageSummary, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		stageSummary = systemService.getEntity(StageSummaryEntity.class, stageSummary.getId());
		message = "阶段性总结删除成功";
		try{
			stageSummaryService.delete(stageSummary);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "阶段性总结删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除阶段性总结
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "阶段性总结删除成功";
		try{
			for(String id:ids.split(",")){
				StageSummaryEntity stageSummary = systemService.getEntity(StageSummaryEntity.class, 
				id
				);
				stageSummaryService.delete(stageSummary);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "阶段性总结删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加阶段性总结
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(StageSummaryEntity stageSummary, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "阶段性总结添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			stageSummary.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				stageSummary.setCreateOrg(user.getDepartid());
			}
			stageSummaryService.save(stageSummary);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "阶段性总结添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新阶段性总结
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(StageSummaryEntity stageSummary, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "阶段性总结更新成功";
		StageSummaryEntity t = stageSummaryService.get(StageSummaryEntity.class, stageSummary.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(stageSummary, t);
			stageSummaryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "阶段性总结更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 阶段性总结新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(StageSummaryEntity stageSummary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(stageSummary.getId())) {
			stageSummary = stageSummaryService.getEntity(StageSummaryEntity.class, stageSummary.getId());
			req.setAttribute("stageSummaryPage", stageSummary);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		List templateList = applyTemplateService.findHql("from ApplyTemplateEntity where type='JDXZJ'");
		if(templateList!=null && templateList.size()>0){
			req.setAttribute("templateEntity", templateList.get(0));
		}
		return new ModelAndView("com/research/process/stageSummary/stageSummary-add");
	}
	/**
	 * 阶段性总结编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(StageSummaryEntity stageSummary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(stageSummary.getId())) {
			stageSummary = stageSummaryService.getEntity(StageSummaryEntity.class, stageSummary.getId());
			req.setAttribute("stageSummaryPage", stageSummary);
		}
		return new ModelAndView("com/research/process/stageSummary/stageSummary-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(StageSummaryEntity stageSummary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(stageSummary.getId())) {
			stageSummary = stageSummaryService.getEntity(StageSummaryEntity.class, stageSummary.getId());
			req.setAttribute("stageSummaryPage", stageSummary);
		}
		return new ModelAndView("com/research/process/stageSummary/stageSummary-view");
	}
}

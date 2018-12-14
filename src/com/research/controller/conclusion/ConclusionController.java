package com.research.controller.conclusion;
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
import org.qihuasoft.web.system.pojo.base.TSRole;
import org.qihuasoft.web.system.pojo.base.TSRoleUser;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.research.entity.apply.ApplyTopicEntity;
import com.research.entity.conclusion.ConclusionEntity;
import com.research.service.apply.ApplyTopicServiceI;
import com.research.service.conclusion.ConclusionServiceI;

/**   
 * @Title: Controller
 * @Description: 结题申请
 * @author onlineGenerator
 * @date 2016-07-29 18:55:25
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/conclusionController")
public class ConclusionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConclusionController.class);

	@Autowired
	private ConclusionServiceI conclusionService;
	@Autowired
	private ApplyTopicServiceI applyTopicService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@RequestMapping(params = "topicPlanAuditList")
	public ModelAndView topicPlanAuditList(HttpServletRequest request) {
		return new ModelAndView("com/research/conclusion/topicPlanAuditList");
	}
	@RequestMapping(params = "researchDataFile")
	public ModelAndView researchDataFile(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		//工作计划
		String planCount = "0";
		String planCountSql = "select count(*) from `st_process_plan` where top_id='"+topId+"'";
		List planCountList = systemService.findListbySql(planCountSql);
		if(planCountList!=null && planCountList.size()>0){
			planCount = String.valueOf(planCountList.get(0));
			request.setAttribute("planCount", planCount);
		}
		//教案管理
		String lessonPlanCount = "0";
		String lessonPlanCountSql = "select count(*) from `st_lesson_plan` where top_id='"+topId+"'";
		List lessonPlanCountList = systemService.findListbySql(lessonPlanCountSql);
		if(lessonPlanCountList!=null && lessonPlanCountList.size()>0){
			lessonPlanCount = String.valueOf(lessonPlanCountList.get(0));
			request.setAttribute("lessonPlanCount", lessonPlanCount);
		}
		//活动管理
		String activityCount = "0";
		String activityCountSql = "select count(*) from `st_research_activity` where top_id='"+topId+"'";
		List activityCountList = systemService.findListbySql(activityCountSql);
		if(activityCountList!=null && activityCountList.size()>0){
			activityCount = String.valueOf(activityCountList.get(0));
			request.setAttribute("activityCount", activityCount);
		}
		
		//案例管理
		String caseCount = "0";
		String caseCountSql = "select count(*) from `st_research_case` where top_id='"+topId+"'";
		List caseCountList = systemService.findListbySql(caseCountSql);
		if(caseCountList!=null && caseCountList.size()>0){
			caseCount = String.valueOf(caseCountList.get(0));
			request.setAttribute("caseCount", caseCount);
		}
		
		//参阅资料
		String resourcesCount = "0";
		String resourcesCountSql = "select count(*) from `st_see_resources` where top_id='"+topId+"'";
		List resourcesCountList = systemService.findListbySql(resourcesCountSql);
		if(resourcesCountList!=null && resourcesCountList.size()>0){
			resourcesCount = String.valueOf(resourcesCountList.get(0));
			request.setAttribute("resourcesCount", resourcesCount);
		}
		
		//阶段性总结
		String summaryCount = "0";
		String summaryCountSql = "select count(*) from `st_stage_summary` where top_id='"+topId+"'";
		List summaryCountList = systemService.findListbySql(summaryCountSql);
		if(summaryCountList!=null && summaryCountList.size()>0){
			summaryCount = String.valueOf(summaryCountList.get(0));
			request.setAttribute("summaryCount", summaryCount);
		}
		//研究反思
		String reflectCount = "0";
		String reflectCountSql = "select count(*) from `st_research_reflect` where top_id='"+topId+"'";
		List reflectCountList = systemService.findListbySql(reflectCountSql);
		if(reflectCountList!=null && reflectCountList.size()>0){
			reflectCount = String.valueOf(reflectCountList.get(0));
			request.setAttribute("reflectCount", reflectCount);
		}
		//教学随笔
		String essayCount = "0";
		String essayCountSql = "select count(*) from `st_research_reflect` where top_id='"+topId+"'";
		List essayCountList = systemService.findListbySql(essayCountSql);
		if(essayCountList!=null && essayCountList.size()>0){
			essayCount = String.valueOf(essayCountList.get(0));
			request.setAttribute("essayCount", essayCount);
		}
		
		return new ModelAndView("com/research/conclusion/researchDataFile");
	}
	@RequestMapping(params = "topicList")
	public ModelAndView topicList(HttpServletRequest request) {
		return new ModelAndView("com/research/conclusion/topicPlanList");
	}

	/**
	 * 结题申请列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "conclusion")
	public ModelAndView conclusion(HttpServletRequest request) {
		return new ModelAndView("com/research/conclusion/conclusionList");
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
	public void datagrid(ConclusionEntity conclusion,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConclusionEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conclusion, request.getParameterMap());
		try{
		//自定义追加查询条件

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conclusionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除结题申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConclusionEntity conclusion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		conclusion = systemService.getEntity(ConclusionEntity.class, conclusion.getId());
		message = "结题申请删除成功";
		try{
			conclusionService.delete(conclusion);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "结题申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除结题申请
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "结题申请删除成功";
		try{
			for(String id:ids.split(",")){
				ConclusionEntity conclusion = systemService.getEntity(ConclusionEntity.class, 
				id
				);
				conclusionService.delete(conclusion);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "结题申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加结题申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConclusionEntity conclusion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String fruitMainFileDoc = request.getParameter("fruitMainFileDoc");
		String fruitMainFileDocName = request.getParameter("fruitMainFileDocName");
		ApplyTopicEntity ate = systemService.get(ApplyTopicEntity.class, conclusion.getTopId());
		ate.setFruitMainFileDoc(fruitMainFileDoc);
		ate.setFruitMainFileDocName(fruitMainFileDocName);
		message = "结题申请添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			if(conclusion.getName()!=null && conclusion.getName().indexOf(",")!=-1){
				String[] names = conclusion.getName().split(",");
				String[] shapes = conclusion.getShape().split(",");
				String[] filePaths = conclusion.getFilePath().split(",");
				String[] fileNames = conclusion.getFileName().split(",");
				if(names!=null){
					String sql = "delete from `st_conclusion` where top_id='"+conclusion.getTopId()+"'";
					conclusionService.executeSql(sql);
					for(int i=0;i<names.length;i++){
						ConclusionEntity ce = new ConclusionEntity();
						ce.setTopId(conclusion.getTopId());
						ce.setName(names[i]);
						ce.setShape(shapes[i]);
						ce.setFilePath(filePaths[i]);
						ce.setFileName(fileNames[i]);
						
						ce.setCreateUser(user.getId());
						if(user.getDepartid() != null){
							ce.setCreateOrg(user.getDepartid());
						}
						ce.setCreateDate(new Date());
						conclusionService.save(ce);
					}
				}
			}else{
				String sql = "delete from `st_conclusion` where top_id='"+conclusion.getTopId()+"'";
				conclusionService.executeSql(sql);
				conclusionService.save(conclusion);
			}
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "结题申请添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新结题申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConclusionEntity conclusion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "结题申请更新成功";
		ConclusionEntity t = conclusionService.get(ConclusionEntity.class, conclusion.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(conclusion, t);
			conclusionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "结题申请更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 结题申请新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConclusionEntity conclusion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conclusion.getId())) {
			conclusion = conclusionService.getEntity(ConclusionEntity.class, conclusion.getId());
			req.setAttribute("conclusionPage", conclusion);
		}
		//读取课题信息
		if (StringUtil.isNotEmpty(conclusion.getTopId())) {
			ApplyTopicEntity applyTopic = applyTopicService.getEntity(ApplyTopicEntity.class, conclusion.getTopId());
			req.setAttribute("applyTopicPage", applyTopic);
			req.setAttribute("topId", conclusion.getTopId());
			
			List conclusionList = conclusionService.findByQueryString("from ConclusionEntity where topId='"+conclusion.getTopId()+"'");
//			if(conclusionList!=null){
//				for(int i=0;i<conclusionList.size();i++){
//					ConclusionEntity ce =(ConclusionEntity)conclusionList.get(i);
//					if(ce!=null && ce.getName().indexOf(",")!=-1){
//						ce.getName().split(",");
//					}
//				}
//			}

			req.setAttribute("conclusionList", conclusionList);
		}
		
		String sql = "select id,typename from `t_s_type` where typegroupid='4028ef8156214dac01562159c1db0003'";
		List xkList = systemService.findListbySql(sql);
		req.setAttribute("xkList", xkList);
		
		return new ModelAndView("com/research/conclusion/conclusion-add");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ConclusionEntity conclusion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conclusion.getId())) {
			conclusion = conclusionService.getEntity(ConclusionEntity.class, conclusion.getId());
			req.setAttribute("conclusionPage", conclusion);
		}
		//读取课题信息
		if (StringUtil.isNotEmpty(conclusion.getTopId())) {
			ApplyTopicEntity applyTopic = applyTopicService.getEntity(ApplyTopicEntity.class, conclusion.getTopId());
			req.setAttribute("applyTopicPage", applyTopic);
			req.setAttribute("topId", conclusion.getTopId());
			
			List conclusionList = conclusionService.findByQueryString("from ConclusionEntity where topId='"+conclusion.getTopId()+"'");
//			if(conclusionList!=null){
//				for(int i=0;i<conclusionList.size();i++){
//					ConclusionEntity ce =(ConclusionEntity)conclusionList.get(i);
//					if(ce!=null && ce.getName().indexOf(",")!=-1){
//						ce.getName().split(",");
//					}
//				}
//			}

			req.setAttribute("conclusionList", conclusionList);
		}
		
		String sql = "select id,typename from `t_s_type` where typegroupid='4028ef8156214dac01562159c1db0003'";
		List xkList = systemService.findListbySql(sql);
		req.setAttribute("xkList", xkList);
		
		return new ModelAndView("com/research/conclusion/conclusion-goView");
	}
	/**
	 * 结题申请编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConclusionEntity conclusion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conclusion.getId())) {
			conclusion = conclusionService.getEntity(ConclusionEntity.class, conclusion.getId());
			req.setAttribute("conclusionPage", conclusion);
		}
		return new ModelAndView("com/research/conclusion/conclusion-update");
	}
}

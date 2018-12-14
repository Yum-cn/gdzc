package com.survey.controller.questionlibrary;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.ContextHolderUtils;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.survey.entity.questionlibrary.QuestionLibraryEntity;
import com.survey.entity.questionnaire.QuestionnaireEntity;
import com.survey.entity.questionoption.QuestionOptionEntity;
import com.survey.service.questionlibrary.QuestionLibraryServiceI;
import com.survey.service.questionoption.QuestionOptionServiceI;

/**   
 * @Title: Controller
 * @Description: 题库主表信息
 * @author onlineGenerator
 * @date 2015-09-13 20:43:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/questionLibraryController")
public class QuestionLibraryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QuestionLibraryController.class);

	@Autowired
	private QuestionLibraryServiceI questionLibraryService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private QuestionOptionServiceI questionOptionService;
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
	 * 题库主表信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "questionLibrary")
	public ModelAndView questionLibrary(HttpServletRequest request) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(QuestionLibraryEntity.class);
		String query_title = request.getParameter("title");
		if(query_title!=null&&query_title!=""){
			request.setAttribute("title", query_title);
			detachedCriteria.add(Restrictions.like("title", "%"+query_title+"%"));
		}
		List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
		request.setAttribute("resultList", resultList);
		return new ModelAndView("com/survey/questionlibrary/questionLibraryList");
	}
	
	/**
	 * 题库主表信息列表 弹出框选择页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "selectQuestionLibrary")
	public ModelAndView selectQuestionLibrary(HttpServletRequest request) {
		String qid = request.getParameter("qid");
		request.setAttribute("qid", qid);
		return new ModelAndView("com/survey/questionlibrary/selectQuestionLibraryList");
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
	public void datagrid(QuestionLibraryEntity questionLibrary,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QuestionLibraryEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, questionLibrary, request.getParameterMap());
		try{
		//自定义追加查询条件

			
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.questionLibraryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * easyui AJAX请求数据 提供给选择试题弹出框使用
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "selectDatagrid")
	public void selectDatagrid(QuestionLibraryEntity questionLibrary,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QuestionLibraryEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, questionLibrary, request.getParameterMap());
		try{
		//自定义追加查询条件
			String qid = request.getParameter("qid");
			if(qid!=null && !"".equals(qid)){
				String[] qidArray = qid.split(",");
				if(qidArray!=null && qidArray.length>0){
					for(int i=0;i<qidArray.length;i++){
						cq.notEq("id", qidArray[i]);
					}
				}
			}
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.questionLibraryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除题库主表信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QuestionLibraryEntity questionLibrary, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		questionLibrary = systemService.getEntity(QuestionLibraryEntity.class, questionLibrary.getId());
		message = "题库主表信息删除成功";
		try{
			questionLibraryService.delete(questionLibrary);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "题库主表信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除题库主表信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "题库主表信息删除成功";
		try{
			for(String id:ids.split(",")){
				QuestionLibraryEntity questionLibrary = systemService.getEntity(QuestionLibraryEntity.class, 
				id
				);
				questionLibraryService.delete(questionLibrary);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "题库主表信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加题库主表信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QuestionLibraryEntity questionLibrary, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "题库主表信息添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			questionLibrary.setCreateuser(user.getId());
			questionLibrary.setCreateTime(new Date());
			questionLibraryService.save(questionLibrary);
			String[] option = request.getParameterValues("option");
			if(option!=null){
				for(int i=0;i<option.length;i++){
					if(option[i]!=null && !"".equals(option[i])){
						QuestionOptionEntity questionOption = new QuestionOptionEntity();
						questionOption.setQuestionId(questionLibrary.getId());
						questionOption.setOptionName(option[i]);
						questionOptionService.save(questionOption);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "题库主表信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新题库主表信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QuestionLibraryEntity questionLibrary, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "题库主表信息更新成功";
		QuestionLibraryEntity t = questionLibraryService.get(QuestionLibraryEntity.class, questionLibrary.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(questionLibrary, t);
			questionLibraryService.saveOrUpdate(t);
			String[] option = request.getParameterValues("option");
			if(option!=null && option.length>0){
				String sql = "delete from `survey_question_option` where question_id=?";
				questionOptionService.executeSql(sql, questionLibrary.getId());
				for(int i=0;i<option.length;i++){
					if(option[i]!=null && !"".equals(option[i])){
						QuestionOptionEntity questionOption = new QuestionOptionEntity();
						questionOption.setQuestionId(questionLibrary.getId());
						questionOption.setOptionName(option[i]);
						questionOptionService.save(questionOption);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "题库主表信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 题库主表信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QuestionLibraryEntity questionLibrary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(questionLibrary.getId())) {
			questionLibrary = questionLibraryService.getEntity(QuestionLibraryEntity.class, questionLibrary.getId());
			req.setAttribute("questionLibraryPage", questionLibrary);
		}
		return new ModelAndView("com/survey/questionlibrary/questionLibrary-add");
	}
	/**
	 * 题库主表信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QuestionLibraryEntity questionLibrary, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(questionLibrary.getId())) {
			questionLibrary = questionLibraryService.getEntity(QuestionLibraryEntity.class, questionLibrary.getId());
			req.setAttribute("questionLibraryPage", questionLibrary);
			String hql = "from QuestionOptionEntity where questionId='"+questionLibrary.getId()+"'";
			List<QuestionOptionEntity> optionList = questionOptionService.findByQueryString(hql);
			req.setAttribute("optionList", optionList);
		}
		return new ModelAndView("com/survey/questionlibrary/questionLibrary-update");
	}
}

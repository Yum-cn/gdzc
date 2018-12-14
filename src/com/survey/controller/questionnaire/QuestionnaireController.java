package com.survey.controller.questionnaire;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.qihuasoft.core.util.BrowserUtils;
import org.qihuasoft.core.util.DimensionCode;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//import org.jeecgframework.poi.excel.ExcelExportOfTemplateUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
//import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.document.docmyfile.entity.DocMyFileEntity;
import com.survey.entity.questionlibrary.QuestionLibraryEntity;
import com.survey.entity.questionnaire.QuestionnaireEntity;
import com.survey.entity.questionnairequestion.QuestionnaireQuestionEntity;
import com.survey.entity.questionoption.QuestionOptionEntity;
import com.survey.entity.questionqptionanswer.QuestionOptionAnswerEntity;
import com.survey.service.questionlibrary.QuestionLibraryServiceI;
import com.survey.service.questionnaire.QuestionnaireServiceI;
import com.survey.service.questionnairequestion.QuestionnaireQuestionServiceI;
import com.survey.service.questionoption.QuestionOptionServiceI;
import com.survey.service.questionqptionanswer.QuestionOptionAnswerServiceI;

/**   
 * @Title: Controller
 * @Description: 问卷管理
 * @author onlineGenerator
 * @date 2015-09-13 23:20:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/questionnaireController")
public class QuestionnaireController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QuestionnaireController.class);

	@Autowired
	private QuestionnaireServiceI questionnaireService;
	@Autowired
	private QuestionnaireQuestionServiceI questionnaireQuestionService;
	@Autowired
	private QuestionOptionAnswerServiceI questionOptionAnswerService;
	@Autowired
	private QuestionOptionServiceI questionOptionService;
	@Autowired
	private QuestionLibraryServiceI questionLibraryService;
	@Autowired
	private CommonService commonService;
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
	 * 统计分析初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "analysis")
	public ModelAndView analysis(String id,HttpServletRequest request,HttpServletResponse response) {
		String sql = "SELECT t1.`id`, t1.`type`, t1.`small_type`, t1.`style`, t1.`title`, t1.`createuser`, t1.`create_time` FROM `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"'";
		List questionLibraryList = questionnaireQuestionService.findListbySql(sql);
		HashMap map = new HashMap();
		HashMap countOptionMap = new HashMap();	
		HashMap optionAnswerMap = new HashMap();
		HashMap sortMap = new HashMap();
		if(questionLibraryList!=null){
			for(int i=0;i<questionLibraryList.size();i++){
				Object[] obj = (Object[])questionLibraryList.get(i);
				if(String.valueOf(obj[3]).equals("TIANKONG") || String.valueOf(obj[3]).equals("JIANDATI")){
					sql = "SELECT * FROM `survey_question_option_answer` where question_id='"+obj[0]+"' and questionnaire_id='"+id+"' order by create_time desc";
					List optionAnswerList = questionnaireQuestionService.findListbySql(sql);
					optionAnswerMap.put(obj[0], optionAnswerList);
				}else{
					sql = "select id,question_id,option_name from `survey_question_option` where question_id='"+obj[0]+"'";
					List questionOptionList = questionnaireQuestionService.findListbySql(sql);
					map.put(obj[0], questionOptionList);
					if(questionOptionList!=null){
						for(int j=0;j<questionOptionList.size();j++){
							Object[] optionObj = (Object[])questionOptionList.get(j);
							String countOptionSQL = "select `id`, `questionnaire_id`, `question_id`, `option_id`, `sortnum`, `full_text`, `create_time` from `survey_question_option_answer` where questionnaire_id='"+id+"' and question_id='"+optionObj[1]+"' and option_id='"+optionObj[0]+"'";
							List countOptionList = questionnaireQuestionService.findListbySql(countOptionSQL);
							countOptionMap.put(optionObj[0], countOptionList.size());
							if(String.valueOf(obj[3]).equals("PAIXU")){
								sortMap.put(optionObj[0], countOptionList);
							}
						}
					}
				}
			}
		}
		request.setAttribute("questionLibraryList", questionLibraryList);
		request.setAttribute("questionOptionMap", map);
		request.setAttribute("countOptionMap", countOptionMap);
		request.setAttribute("optionAnswerMap", optionAnswerMap);
		request.setAttribute("sortMap", sortMap);
		request.setAttribute("id", id);
		return new ModelAndView("com/survey/questionnaire/analysis");
	}
	/**
	 * 曲线图初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "diagramChart")
	public ModelAndView diagramChart(String id,HttpServletRequest request,HttpServletResponse response) {
		String sql = "SELECT t1.`id`, t1.`type`, t1.`small_type`, t1.`style`, t1.`title`, t1.`createuser`, t1.`create_time` FROM `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"'";
		List questionLibraryList = questionnaireQuestionService.findListbySql(sql);
		HashMap map = new HashMap();
		HashMap countOptionMap = new HashMap();		
		if(questionLibraryList!=null){
			for(int i=0;i<questionLibraryList.size();i++){
				Object[] obj = (Object[])questionLibraryList.get(i);
				sql = "select id,question_id,option_name from `survey_question_option` where question_id='"+obj[0]+"'";
				List questionOptionList = questionnaireQuestionService.findListbySql(sql);
				map.put(obj[0], questionOptionList);
				if(questionOptionList!=null){
					for(int j=0;j<questionOptionList.size();j++){
						Object[] optionObj = (Object[])questionOptionList.get(j);
						String countOptionSQL = "select `id`, `questionnaire_id`, `question_id`, `option_id`, `sortnum`, `full_text`, `create_time` from `survey_question_option_answer` where questionnaire_id='"+id+"' and question_id='"+optionObj[1]+"' and option_id='"+optionObj[0]+"'";
						List countOptionList = questionnaireQuestionService.findListbySql(countOptionSQL);
						countOptionMap.put(optionObj[0], countOptionList.size());
					}
				}
			}
		}
		request.setAttribute("questionLibraryList", questionLibraryList);
		request.setAttribute("questionOptionMap", map);
		request.setAttribute("countOptionMap", countOptionMap);
		request.setAttribute("id", id);
		return new ModelAndView("com/survey/questionnaire/diagramChart");
	}
	
	/**
	 * 条形图初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "barChart")
	public ModelAndView barChart(String id,String questionId,HttpServletRequest request,HttpServletResponse response) {
		String sql = "SELECT t1.`id`, t1.`type`, t1.`small_type`, t1.`style`, t1.`title`, t1.`createuser`, t1.`create_time` FROM `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"' and t1.id='"+questionId+"'";
		List questionLibraryList = questionnaireQuestionService.findListbySql(sql);
		String result = "";
		String categories = "";
		String topTitle = "";
		if(questionLibraryList!=null){
			for(int i=0;i<questionLibraryList.size();i++){
				Object[] obj = (Object[])questionLibraryList.get(i);
				sql = "select id,question_id,option_name from `survey_question_option` where question_id='"+obj[0]+"'";
				List questionOptionList = questionnaireQuestionService.findListbySql(sql);
				if(questionOptionList!=null){
					for(int j=0;j<questionOptionList.size();j++){
						Object[] optionObj = (Object[])questionOptionList.get(j);
						categories = categories+ ",'选项"+(j+1)+"'";
						topTitle = topTitle +"选项"+(j+1)+":"+optionObj[2]+"<br>";
						String countOptionSQL = "select `id`, `questionnaire_id`, `question_id`, `option_id`, `sortnum`, `full_text`, `create_time` from `survey_question_option_answer` where questionnaire_id='"+id+"' and question_id='"+optionObj[1]+"' and option_id='"+optionObj[0]+"'";
						List countOptionList = questionnaireQuestionService.findListbySql(countOptionSQL);
						result = result + ","+countOptionList.size()+"";
					}
				}
			}
		}
		result = result.replaceFirst(",", "");
		result = "[" + result + "]";
		categories = categories.replaceFirst(",", "");
		categories = "["+categories+"]";
		request.setAttribute("questionLibraryList", questionLibraryList);

		String title = "";
		if(questionLibraryList!=null && questionLibraryList.size()>0){
			Object[] titleObject = (Object[]) questionLibraryList.get(0);
			title = String.valueOf(titleObject[4]);
		}
		request.setAttribute("title", title);
		request.setAttribute("topTitle", topTitle);
		request.setAttribute("result", result);
		request.setAttribute("categories", categories);
		return new ModelAndView("com/survey/questionnaire/barChart");
	}
	
	/**
	 * 柱状图初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "histogramChart")
	public ModelAndView histogramChart(String id,String questionId,HttpServletRequest request,HttpServletResponse response) {
		String sql = "SELECT t1.`id`, t1.`type`, t1.`small_type`, t1.`style`, t1.`title`, t1.`createuser`, t1.`create_time` FROM `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"' and t1.id='"+questionId+"'";
		List questionLibraryList = questionnaireQuestionService.findListbySql(sql);
		String result = "";
		String categories = "";
		String topTitle = "";
		if(questionLibraryList!=null){
			for(int i=0;i<questionLibraryList.size();i++){
				Object[] obj = (Object[])questionLibraryList.get(i);
				sql = "select id,question_id,option_name from `survey_question_option` where question_id='"+obj[0]+"'";
				List questionOptionList = questionnaireQuestionService.findListbySql(sql);
				if(questionOptionList!=null){
					for(int j=0;j<questionOptionList.size();j++){
						Object[] optionObj = (Object[])questionOptionList.get(j);
						categories = categories+ ",'选项"+(j+1)+"'";
						topTitle = topTitle +"选项"+(j+1)+":"+optionObj[2]+"<br>";
						String countOptionSQL = "select `id`, `questionnaire_id`, `question_id`, `option_id`, `sortnum`, `full_text`, `create_time` from `survey_question_option_answer` where questionnaire_id='"+id+"' and question_id='"+optionObj[1]+"' and option_id='"+optionObj[0]+"'";
						List countOptionList = questionnaireQuestionService.findListbySql(countOptionSQL);
						result = result + ",{y:"+countOptionList.size()+",color: colors["+j+"]}";
					}
				}
			}
		}
		result = result.replaceFirst(",", "");
		categories = categories.replaceFirst(",", "");
		categories = "["+categories+"]";
		request.setAttribute("questionLibraryList", questionLibraryList);

		String title = "";
		if(questionLibraryList!=null && questionLibraryList.size()>0){
			Object[] titleObject = (Object[]) questionLibraryList.get(0);
			title = String.valueOf(titleObject[4]);
		}
		request.setAttribute("title", title);
		request.setAttribute("topTitle", topTitle);
		request.setAttribute("result", result);
		request.setAttribute("categories", categories);
		return new ModelAndView("com/survey/questionnaire/histogramChart");
	}
	/**
	 * 饼图初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "pieChart")
	public ModelAndView pieChart(String id,String questionId,HttpServletRequest request,HttpServletResponse response) {
		String sql = "SELECT t1.`id`, t1.`type`, t1.`small_type`, t1.`style`, t1.`title`, t1.`createuser`, t1.`create_time` FROM `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"' and t1.id='"+questionId+"'";
		List questionLibraryList = questionnaireQuestionService.findListbySql(sql);
		String result = "";
		if(questionLibraryList!=null){
			for(int i=0;i<questionLibraryList.size();i++){
				Object[] obj = (Object[])questionLibraryList.get(i);
				sql = "select id,question_id,option_name from `survey_question_option` where question_id='"+obj[0]+"'";
				List questionOptionList = questionnaireQuestionService.findListbySql(sql);
				if(questionOptionList!=null){
					
					for(int j=0;j<questionOptionList.size();j++){
						Object[] optionObj = (Object[])questionOptionList.get(j);
						String countOptionSQL = "select `id`, `questionnaire_id`, `question_id`, `option_id`, `sortnum`, `full_text`, `create_time` from `survey_question_option_answer` where questionnaire_id='"+id+"' and question_id='"+optionObj[1]+"' and option_id='"+optionObj[0]+"'";
						List countOptionList = questionnaireQuestionService.findListbySql(countOptionSQL);
						result = result + ",['"+optionObj[2]+"("+countOptionList.size()+"票)',   "+countOptionList.size()+"]";
					}
				}
			}
		}
		result = result.replaceFirst(",", "");
		request.setAttribute("questionLibraryList", questionLibraryList);

		String title = "";
		if(questionLibraryList!=null && questionLibraryList.size()>0){
			Object[] titleObject = (Object[]) questionLibraryList.get(0);
			title = String.valueOf(titleObject[4]);
		}
		request.setAttribute("title", title);
		request.setAttribute("result", result);
		return new ModelAndView("com/survey/questionnaire/pieChart");
	}
	
	/**
	 * 问卷管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "questionnaire")
	public ModelAndView questionnaire(HttpServletRequest request,HttpServletResponse response) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(QuestionnaireEntity.class);
		String query_name = request.getParameter("name");
		if(query_name!=null&&query_name!=""){
			request.setAttribute("name", query_name);
			detachedCriteria.add(Restrictions.like("name", "%"+query_name+"%"));
		}
		List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
		request.setAttribute("resultList", resultList);
		return new ModelAndView("com/survey/questionnaire/questionnaireList");
	}
	/**
	 * 生成二维码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "generateCode")
	public ModelAndView generateCode(String id,HttpServletRequest request,HttpServletResponse response) {
		String url = "http://" + request.getServerName() + ":"
				+ request.getServerPort()
				+ request.getContextPath()
				+ "/questionnaireController.do?generatePaper&id="
				+ id;
//		DimensionCode.encoderCode(url, response);
		return null;
	}
	
	/**
	 * 生成手机端调查问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "generatePaper")
	public ModelAndView generatePaper(String id,HttpServletRequest request,HttpServletResponse response) {
		QuestionnaireEntity t = questionnaireService.get(QuestionnaireEntity.class, id);
		request.setAttribute("questionnaire", t);
		LinkedHashMap questionMap = new LinkedHashMap();
		String sql = "select t.id as q_q_id,t1.id  as q_id,t1.title as title,t1.style as style from `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"'";
		List questionList = questionnaireQuestionService.findListbySql(sql);
		if(questionList!=null){
			for(int i=0;i<questionList.size();i++){
				Object[] questionObject = (Object[])questionList.get(i);
				sql = "select `id`, `question_id`, `option_name` from `survey_question_option` where question_id='"+questionObject[1]+"'";
				List optionList = questionnaireQuestionService.findListbySql(sql);
				questionMap.put(questionObject, optionList);
			}
		}
		request.setAttribute("questionMap", questionMap);
		request.setAttribute("id", id);
		return new ModelAndView("com/survey/questionnaire/generatePaper");
	}

	/**
	 * 保存问卷数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "saveAnswer")
	public ModelAndView saveAnswer(String id,HttpServletRequest request,HttpServletResponse response) {

		String sql = "select t.id as q_q_id,t1.id  as q_id,t1.title as title,t1.style as style , t.questionnaire_id from `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+id+"'";
		List questionList = questionnaireQuestionService.findListbySql(sql);
		if(questionList!=null){
			for(int i=0;i<questionList.size();i++){
				Object[] questionObject = (Object[])questionList.get(i);
				if(questionObject[3].equals("DANXUAN")){
					String danxuanValue = request.getParameter("q"+(i+1));
					if(danxuanValue != null && !"".equals(danxuanValue)){
						QuestionOptionAnswerEntity qqa = new QuestionOptionAnswerEntity();
						qqa.setCreateTime(new Date());
						qqa.setOptionId(danxuanValue);
						qqa.setQuestionId(String.valueOf(questionObject[1]));
						qqa.setQuestionnaireId(String.valueOf(questionObject[4]));
						questionOptionAnswerService.save(qqa);
					}
				}
				if(questionObject[3].equals("DUOXUAN")){
					String[] duoxuanValue = request.getParameterValues("q"+(i+1));
					if(duoxuanValue!=null){
						for(int j=0;j<duoxuanValue.length;j++){
							QuestionOptionAnswerEntity qqa = new QuestionOptionAnswerEntity();
							qqa.setCreateTime(new Date());
							qqa.setOptionId(duoxuanValue[j]);
							qqa.setQuestionId(String.valueOf(questionObject[1]));
							qqa.setQuestionnaireId(String.valueOf(questionObject[4]));
							questionOptionAnswerService.save(qqa);
						}
					}
				}
				if(questionObject[3].equals("PAIXU")){
					String[] paixuValue = request.getParameterValues("q"+(i+1));
					String[] paixuIdValue = request.getParameterValues("q"+(i+1)+"_id");
					if(paixuValue!=null){
						for(int j=0;j<paixuValue.length;j++){
							QuestionOptionAnswerEntity qqa = new QuestionOptionAnswerEntity();
							qqa.setCreateTime(new Date());
							qqa.setOptionId(paixuIdValue[j]);
							qqa.setSortnum(paixuValue[j]);
							qqa.setQuestionId(String.valueOf(questionObject[1]));
							qqa.setQuestionnaireId(String.valueOf(questionObject[4]));
							questionOptionAnswerService.save(qqa);
						}
					}
				}
				if(questionObject[3].equals("TIANKONG")){
					String[] tiankongValue = request.getParameterValues("q"+(i+1));
					String tiankongIdValue = request.getParameter("q"+(i+1)+"_id");
					String title = String.valueOf(questionObject[2]);
					if(tiankongValue!=null){
						for(int j=0;j<tiankongValue.length;j++){
							title = title.replaceFirst("__", "("+tiankongValue[j]+")");
						}
						QuestionOptionAnswerEntity qqa = new QuestionOptionAnswerEntity();
						qqa.setCreateTime(new Date());
						qqa.setOptionId(tiankongIdValue);
						qqa.setQuestionId(String.valueOf(questionObject[1]));
						qqa.setQuestionnaireId(String.valueOf(questionObject[4]));
						qqa.setFullText(title);
						questionOptionAnswerService.save(qqa);
					}
				}
				if(questionObject[3].equals("JIANDATI")){
					String jiandaValue = request.getParameter("q"+(i+1));
					String jiandaIdValue = request.getParameter("q"+(i+1)+"_id");
					if(jiandaValue != null && !"".equals(jiandaValue)){
						QuestionOptionAnswerEntity qqa = new QuestionOptionAnswerEntity();
						qqa.setCreateTime(new Date());
						qqa.setOptionId(jiandaIdValue);
						qqa.setFullText(jiandaValue);
						qqa.setQuestionId(String.valueOf(questionObject[1]));
						qqa.setQuestionnaireId(String.valueOf(questionObject[4]));
						questionOptionAnswerService.save(qqa);
					}
				}
			}
		}
		try{
			response.setContentType("text/html"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("  alert('问卷提交成功');"); 
			out.println("</script>"); 
			out.flush(); 
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
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
	public void datagrid(QuestionnaireEntity questionnaire,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QuestionnaireEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, questionnaire, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createTime", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.questionnaireService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除问卷管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QuestionnaireEntity questionnaire, HttpServletRequest request,String id) {
		AjaxJson j = new AjaxJson();
		questionnaire = systemService.getEntity(QuestionnaireEntity.class, id);
		message = "问卷管理删除成功";
		try{
			questionnaireService.delete(questionnaire);
			String sql = "delete from `survey_questionnaire_question` where questionnaire_id=?";
			questionnaireQuestionService.executeSql(sql, questionnaire.getId());
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "问卷管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除问卷管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "问卷管理删除成功";
		try{
			for(String id:ids.split(",")){
				QuestionnaireEntity questionnaire = systemService.getEntity(QuestionnaireEntity.class, 
				id
				);
				String sql = "delete from `survey_questionnaire_question` where questionnaire_id=?";
				questionnaireQuestionService.executeSql(sql, id);
				questionnaireService.delete(questionnaire);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "问卷管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加问卷管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(QuestionnaireEntity questionnaire, HttpServletRequest request) {
		//AjaxJson j = new AjaxJson();
		message = "问卷管理添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			questionnaire.setCreateTime(new Date());
			questionnaire.setCreateUser(user.getId());
			questionnaireService.save(questionnaire);
			String[] questionIds = request.getParameterValues("questionId");
			if(questionIds!=null){
				for(int i=0;i<questionIds.length;i++){
					if(questionIds[i]!=null && !"".equals(questionIds[i])){
						QuestionnaireQuestionEntity questionnaireQuestion = new QuestionnaireQuestionEntity();
						questionnaireQuestion.setCreateTime(new Date());
						questionnaireQuestion.setQuestionnaireId(questionnaire.getId());
						questionnaireQuestion.setQuestionId(questionIds[i]);
						questionnaireQuestionService.save(questionnaireQuestion);
					}
				}
			}

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "问卷管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("returnURL", "questionnaireController.do?questionnaire");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新问卷管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QuestionnaireEntity questionnaire, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "问卷管理更新成功";
		QuestionnaireEntity t = questionnaireService.get(QuestionnaireEntity.class, questionnaire.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(questionnaire, t);
			questionnaireService.saveOrUpdate(t);
			String[] questionIds = request.getParameterValues("questionId");
			if(questionIds!=null && questionIds.length>0){
				String sql = "delete from `survey_questionnaire_question` where questionnaire_id=?";
				questionnaireQuestionService.executeSql(sql, questionnaire.getId());
				for(int i=0;i<questionIds.length;i++){
					if(questionIds[i]!=null && !"".equals(questionIds[i])){
						QuestionnaireQuestionEntity questionnaireQuestion = new QuestionnaireQuestionEntity();
						questionnaireQuestion.setCreateTime(new Date());
						questionnaireQuestion.setQuestionnaireId(questionnaire.getId());
						questionnaireQuestion.setQuestionId(questionIds[i]);
						questionnaireQuestionService.save(questionnaireQuestion);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "问卷管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 问卷管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QuestionnaireEntity questionnaire, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(questionnaire.getId())) {
			questionnaire = questionnaireService.getEntity(QuestionnaireEntity.class, questionnaire.getId());
			req.setAttribute("questionnairePage", questionnaire);
		}
		return new ModelAndView("com/survey/questionnaire/questionnaire-add");
	}
	/**
	 * 问卷管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QuestionnaireEntity questionnaire, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(questionnaire.getId())) {
			questionnaire = questionnaireService.getEntity(QuestionnaireEntity.class, questionnaire.getId());
			req.setAttribute("questionnairePage", questionnaire);
			String sql = "select t.id as q_q_id,t1.id  as q_id,t1.title as title from `survey_questionnaire_question` t,survey_question_library t1 where t.question_id=t1.id and t.questionnaire_id='"+questionnaire.getId()+"'";
			List questionList = questionnaireQuestionService.findListbySql(sql);
			req.setAttribute("questionList", questionList);
		}
		return new ModelAndView("com/survey/questionnaire/questionnaire-update");
	}
	
	@RequestMapping(params = "exportXls")
	public void exportXls(QuestionOptionAnswerEntity questionOptionAnswerEntity,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "题库信息";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(QuestionOptionAnswerEntity.class, dataGrid);
			org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, questionOptionAnswerEntity, request.getParameterMap());
			TSUser user = ResourceUtil.getSessionUserName();
			
			String sql = "SELECT t1.STYLE, t1.title, t2.option_name AS result FROM `survey_question_option_answer` t, survey_question_library t1, survey_question_option t2 WHERE t.question_id = t1.id AND t.option_id = t2.id AND t.question_id IN ( SELECT question_id FROM `survey_questionnaire_question` WHERE questionnaire_id = '"+questionOptionAnswerEntity.getQuestionnaireId()+"' ) ";
			sql = sql + " UNION ALL ";
			sql = sql + " SELECT t1.STYLE, t1.title, t.full_text AS result FROM `survey_question_option_answer` t, survey_question_library t1 WHERE t.question_id = t1.id AND t1.style = 'JIANDATI' AND t.question_id IN ( SELECT question_id FROM `survey_questionnaire_question` WHERE questionnaire_id = '"+questionOptionAnswerEntity.getQuestionnaireId()+"' )  ";
			sql = sql + " UNION ALL ";
			sql = sql + " SELECT t1.STYLE, t1.title, t.sortnum AS result FROM `survey_question_option_answer` t, survey_question_library t1 WHERE t.question_id = t1.id AND t1.style = 'PAIXU' AND t.question_id IN ( SELECT question_id FROM `survey_questionnaire_question` WHERE questionnaire_id = '"+questionOptionAnswerEntity.getQuestionnaireId()+"' )  ";
			sql = sql + " UNION ALL ";
			sql = sql + " SELECT t1.STYLE, t1.title, t.sortnum AS result FROM `survey_question_option_answer` t, survey_question_library t1 WHERE t.question_id = t1.id AND t1.style = 'TIANKONG' AND t.question_id IN ( SELECT question_id FROM `survey_questionnaire_question` WHERE questionnaire_id = '"+questionOptionAnswerEntity.getQuestionnaireId()+"' ) ORDER BY title, result ASC ";
			List result = systemService.findListbySql(sql);
			List parseResult = new ArrayList();
			if(result!=null && result.size()>0){
				for(int i=0;i<result.size();i++){
					Object[] obj = (Object[])result.get(i);
					QuestionOptionAnswerEntity qoe = new QuestionOptionAnswerEntity();
					qoe.setQuestionIdName(String.valueOf(obj[1]).trim());
					qoe.setOptionIdName(String.valueOf(obj[2]).trim());
					if(obj[0]!=null && !"".equals(String.valueOf(obj[0]))){
						if("DANXUAN".equals(String.valueOf(obj[0]))){
							qoe.setStyle("单选题");
						}else if("DUOXUAN".equals(String.valueOf(obj[0]))){
							qoe.setStyle("多选题");
						}else if("PAIXU".equals(String.valueOf(obj[0]))){
							qoe.setStyle("排序题");
						}else if("TIANKONG".equals(String.valueOf(obj[0]))){
							qoe.setStyle("填空题");
						}else if("JIANDATI".equals(String.valueOf(obj[0]))){
							qoe.setStyle("简答题");
						}
						parseResult.add(qoe);
					}
//					QuestionOptionAnswerEntity qoae = (QuestionOptionAnswerEntity)courses.get(i);
//					QuestionLibraryEntity qle = questionLibraryService.get(QuestionLibraryEntity.class, qoae.getQuestionId());
//					if(qle!=null) qoae.setQuestionIdName(qle.getTitle());
//					QuestionOptionEntity qoe = questionOptionService.get(QuestionOptionEntity.class, qoae.getOptionId());
//					if(qoe!=null) qoae.setQuestionIdName(qoe.getOptionName());
				}
			}
			
			//List<QuestionOptionAnswerEntity> courses = this.questionOptionAnswerService.getListByCriteriaQuery(cq,false);
			//cq.setResults(parseResult);
			workbook = (HSSFWorkbook) ExcelExportUtil.exportExcel(new ExportParams("题库列表", "导出人:"+user.getRealName(),
					"导出信息"), QuestionOptionAnswerEntity.class, parseResult);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
}

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

import com.research.entity.process.ResearchEssayEntity;
import com.research.service.process.ResearchEssayServiceI;

/**   
 * @Title: Controller
 * @Description: 教学随笔
 * @author onlineGenerator
 * @date 2016-07-29 12:26:08
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/researchEssayController")
public class ResearchEssayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResearchEssayController.class);

	@Autowired
	private ResearchEssayServiceI researchEssayService;
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
		return new ModelAndView("com/research/process/essay/topicPlanList");
	}
	/**
	 * 教学随笔列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "researchEssay")
	public ModelAndView researchEssay(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/essay/researchEssayList");
	}
	@RequestMapping(params = "researchEssayView")
	public ModelAndView researchEssayView(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		request.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/essay/researchEssayViewList");
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
	public void datagrid(ResearchEssayEntity researchEssay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ResearchEssayEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, researchEssay, request.getParameterMap());
		try{
		//自定义追加查询条件
			HashMap map = new HashMap();
			map.put("createDate", "desc");
			cq.setOrder(map);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.researchEssayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除教学随笔
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ResearchEssayEntity researchEssay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		researchEssay = systemService.getEntity(ResearchEssayEntity.class, researchEssay.getId());
		message = "教学随笔删除成功";
		try{
			researchEssayService.delete(researchEssay);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "教学随笔删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除教学随笔
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "教学随笔删除成功";
		try{
			for(String id:ids.split(",")){
				ResearchEssayEntity researchEssay = systemService.getEntity(ResearchEssayEntity.class, 
				id
				);
				researchEssayService.delete(researchEssay);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "教学随笔删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加教学随笔
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ResearchEssayEntity researchEssay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "教学随笔添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			researchEssay.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				researchEssay.setCreateOrg(user.getDepartid());
			}
			researchEssay.setCreateDate(new Date());
			researchEssayService.save(researchEssay);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "教学随笔添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新教学随笔
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ResearchEssayEntity researchEssay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "教学随笔更新成功";
		ResearchEssayEntity t = researchEssayService.get(ResearchEssayEntity.class, researchEssay.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(researchEssay, t);
			researchEssayService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "教学随笔更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 教学随笔新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ResearchEssayEntity researchEssay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchEssay.getId())) {
			researchEssay = researchEssayService.getEntity(ResearchEssayEntity.class, researchEssay.getId());
			req.setAttribute("researchEssayPage", researchEssay);
		}
		String topId = req.getParameter("topId");
		req.setAttribute("topId", topId);
		return new ModelAndView("com/research/process/essay/researchEssay-add");
	}
	/**
	 * 教学随笔编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ResearchEssayEntity researchEssay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchEssay.getId())) {
			researchEssay = researchEssayService.getEntity(ResearchEssayEntity.class, researchEssay.getId());
			req.setAttribute("researchEssayPage", researchEssay);
		}
		return new ModelAndView("com/research/process/essay/researchEssay-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(ResearchEssayEntity researchEssay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(researchEssay.getId())) {
			researchEssay = researchEssayService.getEntity(ResearchEssayEntity.class, researchEssay.getId());
			req.setAttribute("researchEssayPage", researchEssay);
		}
		return new ModelAndView("com/research/process/essay/researchEssay-view");
	}
}

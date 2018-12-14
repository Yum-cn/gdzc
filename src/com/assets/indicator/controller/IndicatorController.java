package com.assets.indicator.controller;
import java.util.ArrayList;
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
import org.qihuasoft.web.system.pojo.base.TSType;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import com.assets.indicator.entity.IndicatorEntity;
import com.assets.indicator.service.IndicatorServiceI;



/**   
 * @Title: Controller
 * @Description: 指标
 * @author onlineGenerator
 * @date 2015-04-01 10:01:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/indicatorController")
public class IndicatorController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IndicatorController.class);

	@Autowired
	private IndicatorServiceI indicatorService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "selectIndicator")
	public ModelAndView selectIndicator(HttpServletRequest request) {
		String pjdl = request.getParameter("pjdl");
		String parentId = request.getParameter("parentId");
		request.setAttribute("pjdl", pjdl);
		request.setAttribute("parentId", parentId);
		List allList = new ArrayList();
		this.recursionIndicator(allList, pjdl);
		String hql = "from IndicatorEntity where parentId='4028ef82624d7ec601624d9cac75005d'";//公共库
		List<IndicatorEntity> list = systemService.findByQueryString(hql);
//		allList.addAll(list);
		request.setAttribute("allList", allList);
		request.setAttribute("list", list);
		return new ModelAndView("com/assets/indicator/selectIndicator");
	}
	/**
	 * 递归查询下面的指标项
	 * @return
	 */
	private void recursionIndicator(List allList ,String parentId){
		String hql = "from IndicatorEntity where parentId='"+parentId+"'";
		List<IndicatorEntity> list = systemService.findByQueryString(hql);
		if(list!=null && list.size()>0){
			allList.addAll(list);
			for(int i=0;i<list.size();i++){
				IndicatorEntity ie = (IndicatorEntity)list.get(i);
				this.recursionIndicator(allList, ie.getId());
			}
			
		}
	}
	/**
	 * 指标列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "indicator")
	public ModelAndView indicator(HttpServletRequest request) {
		String pjdl = request.getParameter("pjdl");
		request.setAttribute("pjdl", pjdl);
		String parentId = request.getParameter("parentId");
		request.setAttribute("parentId", parentId);
		return new ModelAndView("com/assets/indicator/indicatorList");
	}
	@RequestMapping(params = "indicatorMain")
	public ModelAndView indicatorMain(HttpServletRequest request) {
		return new ModelAndView("com/assets/indicator/indicatorMain");
	}
	@RequestMapping(params = "indicatorLeft")
	public ModelAndView indicatorLeft(HttpServletRequest request) {
		String sql = "select `id`, `typecode`, `typename`, `typepid`, `typegroupid` from `t_s_type` where typegroupid='402893814ca37f13014ca402f7560047'";
		List list = systemService.findListbySql(sql);
		request.setAttribute("list", list);
		String hql = "from IndicatorEntity";
		List indicatorList = systemService.findByQueryString(hql);
		request.setAttribute("indicatorList", indicatorList);
		return new ModelAndView("com/assets/indicator/indicatorLeft");
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
	public void datagrid(IndicatorEntity indicator,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if(!"root".equals(indicator.getParentId())){
			indicator.setPjdl(null);
		}else{
			indicator.setParentId(null);
		}
		CriteriaQuery cq = new CriteriaQuery(IndicatorEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, indicator, request.getParameterMap());
		try{
		//自定义追加查询条件
			if(!"root".equals(indicator.getParentId())){
				cq.eq("parentId", indicator.getParentId());
				indicator.setPjdl(null);
			}else{
				cq.eq("pjdl", indicator.getPjdl());
				indicator.setParentId(null);
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.indicatorService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除指标
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IndicatorEntity indicator, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		indicator = systemService.getEntity(IndicatorEntity.class, indicator.getId());
		message = "指标删除成功";
		try{
			indicatorService.delete(indicator);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "指标删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除指标
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "指标删除成功";
		try{
			for(String id:ids.split(",")){
				IndicatorEntity indicator = systemService.getEntity(IndicatorEntity.class, 
				id
				);
				indicatorService.delete(indicator);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "指标删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加指标
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IndicatorEntity indicator, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "指标添加成功";
		try{
			IndicatorEntity ie = systemService.get(IndicatorEntity.class, indicator.getParentId());
			TSType tsType = null;
			if(ie==null){
				tsType = systemService.get(TSType.class, indicator.getParentId());
			}else{
				String rootId = this.recursionRootIndicator(indicator.getParentId());
				tsType = systemService.get(TSType.class, rootId);
			} 
			if(tsType!=null){
				indicator.setPjdl(tsType.getTypecode());
			}
			indicatorService.save(indicator);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "指标添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 递归查询最顶级的指标项
	 * @return
	 */
	private String recursionRootIndicator(String parentId){
		String hql = "from IndicatorEntity where id='"+parentId+"'";
		List<IndicatorEntity> list = systemService.findByQueryString(hql);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				IndicatorEntity ie = (IndicatorEntity)list.get(i);
				return this.recursionRootIndicator(ie.getParentId());
			}
		}
		return parentId;
	}
	/**
	 * 更新指标
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IndicatorEntity indicator, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "指标更新成功";
		IndicatorEntity t = indicatorService.get(IndicatorEntity.class, indicator.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(indicator, t);
			indicatorService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "指标更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 指标新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IndicatorEntity indicator, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(indicator.getId())) {
			indicator = indicatorService.getEntity(IndicatorEntity.class, indicator.getId());
			req.setAttribute("indicatorPage", indicator);
		}
		req.setAttribute("pjdl", indicator.getPjdl());
		req.setAttribute("parentId", indicator.getParentId());
		return new ModelAndView("com/assets/indicator/indicator-add");
	}
	/**
	 * 指标编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IndicatorEntity indicator, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(indicator.getId())) {
			indicator = indicatorService.getEntity(IndicatorEntity.class, indicator.getId());
			req.setAttribute("indicatorPage", indicator);
		}
		return new ModelAndView("com/assets/indicator/indicator-update");
	}
}

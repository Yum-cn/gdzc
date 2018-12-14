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

import com.research.entity.process.CertificateRegisterEntity;
import com.research.service.process.CertificateRegisterServiceI;

/**   
 * @Title: Controller
 * @Description: 证书登记
 * @author onlineGenerator
 * @date 2016-08-14 16:54:47
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/certificateRegisterController")
public class CertificateRegisterController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CertificateRegisterController.class);

	@Autowired
	private CertificateRegisterServiceI certificateRegisterService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "certificateRegisterMain")
	public ModelAndView seeResourcesMain(HttpServletRequest request) {

		return new ModelAndView("com/research/process/certificate/certificateRegisterMain");
	}
	/**
	 * 证书登记列表 页面跳转  集体
	 * 
	 * @return
	 */
	@RequestMapping(params = "certificateRegister")
	public ModelAndView certificateRegister(HttpServletRequest request) {
		return new ModelAndView("com/research/process/certificate/certificateRegisterList");
	}
	//教师
	@RequestMapping(params = "certificateRegister1")
	public ModelAndView certificateRegister1(HttpServletRequest request) {
		return new ModelAndView("com/research/process/certificate/certificateRegisterList1");
	}
	//个人
	@RequestMapping(params = "certificateRegister2")
	public ModelAndView certificateRegister2(HttpServletRequest request) {
		return new ModelAndView("com/research/process/certificate/certificateRegisterList2");
	}
	//审核
	@RequestMapping(params = "certificateRegisterAudit")
	public ModelAndView certificateRegisterAudit(HttpServletRequest request) {
		return new ModelAndView("com/research/process/certificate/certificateRegisterList-audit");
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
	public void datagrid(CertificateRegisterEntity certificateRegister,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CertificateRegisterEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, certificateRegister, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.certificateRegisterService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除证书登记
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CertificateRegisterEntity certificateRegister, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		certificateRegister = systemService.getEntity(CertificateRegisterEntity.class, certificateRegister.getId());
		message = "证书登记删除成功";
		try{
			certificateRegisterService.delete(certificateRegister);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "证书登记删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除证书登记
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "证书登记删除成功";
		try{
			for(String id:ids.split(",")){
				CertificateRegisterEntity certificateRegister = systemService.getEntity(CertificateRegisterEntity.class, 
				id
				);
				certificateRegisterService.delete(certificateRegister);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "证书登记删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加证书登记
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CertificateRegisterEntity certificateRegister, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "证书登记添加成功";
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			certificateRegister.setCreateUser(user.getId());
			if(user.getDepartid() != null){
				certificateRegister.setCreateOrg(user.getDepartid());
			}
			certificateRegister.setCreateTime(new Date());
			certificateRegisterService.save(certificateRegister);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "证书登记添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新证书登记
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CertificateRegisterEntity certificateRegister, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "证书登记更新成功";
		CertificateRegisterEntity t = certificateRegisterService.get(CertificateRegisterEntity.class, certificateRegister.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(certificateRegister, t);
			certificateRegisterService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "证书登记更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 证书登记新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CertificateRegisterEntity certificateRegister, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(certificateRegister.getId())) {
			certificateRegister = certificateRegisterService.getEntity(CertificateRegisterEntity.class, certificateRegister.getId());
			req.setAttribute("certificateRegisterPage", certificateRegister);
		}
		String funType = req.getParameter("funType");
		req.setAttribute("funType", funType);
		return new ModelAndView("com/research/process/certificate/certificateRegister-add");
	}
	/**
	 * 证书登记编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CertificateRegisterEntity certificateRegister, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(certificateRegister.getId())) {
			certificateRegister = certificateRegisterService.getEntity(CertificateRegisterEntity.class, certificateRegister.getId());
			req.setAttribute("certificateRegisterPage", certificateRegister);
		}
		
		return new ModelAndView("com/research/process/certificate/certificateRegister-update");
	}
	@RequestMapping(params = "goView")
	public ModelAndView goView(CertificateRegisterEntity certificateRegister, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(certificateRegister.getId())) {
			certificateRegister = certificateRegisterService.getEntity(CertificateRegisterEntity.class, certificateRegister.getId());
			req.setAttribute("certificateRegisterPage", certificateRegister);
		}
		
		return new ModelAndView("com/research/process/certificate/certificateRegister-goView");
	}
}

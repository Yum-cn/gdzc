package org.qihuasoft.web.system.controller.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/**
 * 
 * @author 隋磊
 * 桌面main控制器
 */
@Controller
@RequestMapping("/DeskMainCountController")
public class DeskMainController extends BaseController {


	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private CommonService commonService;
	
	
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			//当前登陆人相关信息
			TSUser user = ResourceUtil.getSessionUserName();
			String id = user.getId();  //当前登陆人的id
			String realName = user.getRealName(); //当前登陆人的真实姓名
			//资产分类分析
			String sql = "select t1.typename,count(t.type) from `assets_store` t,t_s_type t1 where t.type=t1.typecode group by type";
			List zcflList = systemService.findListbySql(sql);
			request.setAttribute("zcflList", zcflList);
			
			//资产状态分析
			sql = "select t1.typename,count(t.bpm_status) from `assets_store` t,t_s_type t1 where t.bpm_status=t1.typecode group by bpm_status";
			List zcztList = systemService.findListbySql(sql);
			request.setAttribute("zcztList", zcztList);
			
			//未使用总数
			sql = "select count(*) from assets_store where bpm_status='wy'";
			List wyList = systemService.findListbySql(sql);
			request.setAttribute("wyList", wyList);
			
			//已使用总数
			sql = "select count(*) from assets_store where bpm_status='yy'";
			List yyList = systemService.findListbySql(sql);
			request.setAttribute("yyList", yyList);
			
			//闲置总数
			sql = "select count(*) from assets_store where bpm_status='xz'";
			List xzList = systemService.findListbySql(sql);
			request.setAttribute("xzList", xzList);
			
			//报废总数
			sql = "select count(*) from assets_store where bpm_status='bf'";
			List bfList = systemService.findListbySql(sql);
			request.setAttribute("bfList", bfList);
			
			//维修总数
			sql = "select count(*) from assets_store where bpm_status='wx'";
			List wxList = systemService.findListbySql(sql);
			request.setAttribute("wxList", wxList);
			
			//资产总数
			sql = "select count(*) from assets_store ";
			List contList = systemService.findListbySql(sql);
			request.setAttribute("contList", contList);
			
			return new ModelAndView("main/deskIndex");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}

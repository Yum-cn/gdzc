package org.qihuasoft.web.system.controller.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
			/*//资产分类分析
			String sql = "select t1.typename,count(t.type) from `assets_store` t,t_s_type t1 where t.type=t1.typecode group by type";
			List zcflList = systemService.findListbySql(sql);
			request.setAttribute("zcflList", zcflList);
			
			//资产状态分析
			sql = "select t1.typename,count(t.bpm_status) from `assets_store` t,t_s_type t1 where t.bpm_status=t1.typecode group by bpm_status";
			List zcztList = systemService.findListbySql(sql);
			request.setAttribute("zcztList", zcztList);*/
			
			//所属网络分析
			String sql = "select t1.typename,count(t.netTypeCode) from `assets_store` t,t_s_type t1 where t.netTypeCode=t1.typecode group by t.netTypeCode";
			List sswlList = systemService.findListbySql(sql);
			request.setAttribute("sswlList", sswlList);
			
			//组别管理分析
			sql = "select t1.typename,count(t.groupTypeCode) from `assets_store` t,t_s_type t1 where t.groupTypeCode=t1.typecode group by t.groupTypeCode";
			List zbglList = systemService.findListbySql(sql);
			request.setAttribute("zbglList", zbglList);
			
			//采购管理分析
			Calendar cal = Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			int bYear = nowYear - 1 ;
			int bbYear = nowYear - 2 ;
			
			sql = " SELECT payTime,category,num,amount from ( "
					+" select SUBSTR(t.payTime FROM 1 FOR 4) payTime,t.category,count(t.id) num,FORMAT(sum(t.amount),2) amount from `assets_store` t  where t.payTime >='"+bbYear+" 00:00:00'  GROUP BY SUBSTR(t.payTime FROM 1 FOR 4),t.category "
					+" UNION select 'total',t1.category,count(t1.id) num,FORMAT(sum(t1.amount),2) amount from `assets_store` t1  where t1.payTime >='"+bbYear+" 00:00:00'  GROUP BY t1.category) as tt  ";
			List cgglList = systemService.findListbySql(sql);
			
			List<String> stringList = Arrays.asList(String.valueOf(bbYear), String.valueOf(bYear), String.valueOf(nowYear),"近三年总量");
			List softwareList = new ArrayList();
			List hardwareList = new ArrayList();
			List<String> stringAmountList = Arrays.asList(String.valueOf(bbYear),String.valueOf(bYear), String.valueOf(nowYear));
			List softwareAmountList = new ArrayList();
			List hardwareAmountList = new ArrayList();
			for (int i = 0; i <= 3; i++) {
				
				if(i==0){
					
					for (int j = 0; j < cgglList.size(); j++) {
						Object[] temp = (Object[]) cgglList.get(j);
						
						if(StringUtils.equals(String.valueOf(bbYear), String.valueOf(temp[0])) && StringUtils.equals("software", String.valueOf(temp[1]))){
							softwareList.add(temp[2]);
							String tempAmount = StringUtils.replace(String.valueOf(temp[3]), ",", "");
							softwareAmountList.add(tempAmount);
						}
						if(StringUtils.equals(String.valueOf(bbYear), String.valueOf(temp[0])) && StringUtils.equals("hardware", String.valueOf(temp[1]))){
							hardwareList.add(temp[2]);
							String tempAmount = StringUtils.replace(String.valueOf(temp[3]), ",", "");
							hardwareAmountList.add(tempAmount);
						}
						
					}
					if(softwareList.size()!=1){
						softwareList.add(0);
					}
					if(hardwareList.size()!=1){
						hardwareList.add(0);
					}
					
					if(softwareAmountList.size()!=1){
						softwareAmountList.add(0F);
					}
					if(hardwareAmountList.size()!=1){
						hardwareAmountList.add(0F);
					}
				}
				
				if(i==1){
					
					for (int j = 0; j < cgglList.size(); j++) {
						Object[] temp = (Object[]) cgglList.get(j);
						
						if(StringUtils.equals(String.valueOf(bYear), String.valueOf(temp[0])) && StringUtils.equals("software", String.valueOf(temp[1]))){
							softwareList.add(temp[2]);
							String tempAmount = StringUtils.replace(String.valueOf(temp[3]), ",", "");
							softwareAmountList.add(tempAmount);
						}
						if(StringUtils.equals(String.valueOf(bYear), String.valueOf(temp[0])) && StringUtils.equals("hardware", String.valueOf(temp[1]))){
							hardwareList.add(temp[2]);
							String tempAmount = StringUtils.replace(String.valueOf(temp[3]), ",", "");
							hardwareAmountList.add(tempAmount);
						}
						
					}
					if(softwareList.size()!=2){
						softwareList.add(0);
					}
					if(hardwareList.size()!=2){
						hardwareList.add(0);
					}
					if(softwareAmountList.size()!=2){
						softwareAmountList.add(0F);
					}
					if(hardwareAmountList.size()!=2){
						hardwareAmountList.add(0F);
					}
				}
				
				if(i==2){
					
					for (int j = 0; j < cgglList.size(); j++) {
						Object[] temp = (Object[]) cgglList.get(j);
						
						if(StringUtils.equals(String.valueOf(nowYear), String.valueOf(temp[0])) && StringUtils.equals("software", String.valueOf(temp[1]))){
							softwareList.add(temp[2]);
							String tempAmount = StringUtils.replace(String.valueOf(temp[3]), ",", "");
							softwareAmountList.add(tempAmount);
						}
						if(StringUtils.equals(String.valueOf(nowYear), String.valueOf(temp[0])) && StringUtils.equals("hardware", String.valueOf(temp[1]))){
							hardwareList.add(temp[2]);
							String tempAmount = StringUtils.replace(String.valueOf(temp[3]), ",", "");
							hardwareAmountList.add(tempAmount);
						}
						
					}
					if(softwareList.size()!=3){
						softwareList.add(0F);
					}
					if(hardwareList.size()!=3){
						hardwareList.add(0F);
					}
					if(softwareAmountList.size()!=3){
						softwareAmountList.add(0F);
					}
					if(hardwareAmountList.size()!=3){
						hardwareAmountList.add(0F);
					}
				}
				
				if(i==3){
					
					for (int j = 0; j < cgglList.size(); j++) {
						Object[] temp = (Object[]) cgglList.get(j);
						
						if(StringUtils.equals("total", String.valueOf(temp[0])) && StringUtils.equals("software", String.valueOf(temp[1]))){
							softwareList.add(temp[2]);
						}
						if(StringUtils.equals("total", String.valueOf(temp[0])) && StringUtils.equals("hardware", String.valueOf(temp[1]))){
							hardwareList.add(temp[2]);
						}
						
					}
					if(softwareList.size()!=4){
						softwareList.add(0);
					}
					if(hardwareList.size()!=4){
						hardwareList.add(0);
					}
				}
				
			}
		
			request.setAttribute("stringList", stringList);
			request.setAttribute("softwareList", softwareList);
			request.setAttribute("hardwareList", hardwareList);
			
			request.setAttribute("stringAmountList", stringAmountList);
			request.setAttribute("softwareAmountList", softwareAmountList);
			request.setAttribute("hardwareAmountList", hardwareAmountList);
			
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

package org.qihuasoft.web.system.controller.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.ComboBox;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.enums.SysDatabaseEnum;
import org.qihuasoft.core.util.MutiLangUtil;
import org.qihuasoft.core.util.MyBeanUtils;
import org.qihuasoft.core.util.PasswordUtil;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.DynamicDataSourceEntity;
import org.qihuasoft.web.system.service.DynamicDataSourceServiceI;
import org.qihuasoft.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller
 * @Description: 数据源配置
 * @author zhangdaihao
 * @date 2014-09-05 13:22:10
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/dynamicDataSourceController")
public class DynamicDataSourceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DynamicDataSourceController.class);

	@Autowired
	private DynamicDataSourceServiceI dynamicDataSourceService;
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
	 * 数据源配置列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "dbSource")
	public ModelAndView dbSource(HttpServletRequest request) {
		return new ModelAndView("system/dbsource/dbSourceList");
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(DynamicDataSourceEntity dbSource,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DynamicDataSourceEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, dbSource, request.getParameterMap());
		this.dynamicDataSourceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * 删除数据源配置
	 *
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DynamicDataSourceEntity dbSource, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		dbSource = systemService.getEntity(DynamicDataSourceEntity.class, dbSource.getId());

		message = MutiLangUtil.paramDelSuccess("common.datasource.manage");

		dynamicDataSourceService.delete(dbSource);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}


	/**
	 * 添加数据源配置
	 *
	 * @param dbSource
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DynamicDataSourceEntity dbSource, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(dbSource.getId())) {
			message = MutiLangUtil.paramUpdSuccess("common.datasource.manage");
			DynamicDataSourceEntity t = dynamicDataSourceService.get(DynamicDataSourceEntity.class, dbSource.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(dbSource, t);

				t.setDbPassword(PasswordUtil.encrypt(t.getDbPassword(), t.getDbUser(), PasswordUtil.getStaticSalt()));

				dynamicDataSourceService.saveOrUpdate(t);
				dynamicDataSourceService.refleshCache();
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = MutiLangUtil.paramUpdFail("common.datasource.manage");
			}
		} else {
			message = MutiLangUtil.paramAddSuccess("common.datasource.manage");

			try {
				dbSource.setDbPassword(PasswordUtil.encrypt(dbSource.getDbPassword(), dbSource.getDbUser(), PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			dynamicDataSourceService.save(dbSource);
			dynamicDataSourceService.refleshCache();
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 数据源配置列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DynamicDataSourceEntity dbSource, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(dbSource.getId())) {
			dbSource = dynamicDataSourceService.getEntity(DynamicDataSourceEntity.class, dbSource.getId());

			try {
				//String result = PasswordUtil.decrypt(d.getDbPassword(), d.getDbUser(), PasswordUtil.getStaticSalt());
				//System.out.println("==result"+result);
				dbSource.setDbPassword(PasswordUtil.decrypt(dbSource.getDbPassword(), dbSource.getDbUser(), PasswordUtil.getStaticSalt()));//解密字符串,密文展示
			} catch (Exception e) {
				e.printStackTrace();
			}

			req.setAttribute("dbSourcePage", dbSource);
		}
		return new ModelAndView("system/dbsource/dbSource");
	}


    /**
     * 获取数据源列表
     * @return
     */
    @RequestMapping(params = "getAll")
    @ResponseBody
    public List<ComboBox> getAll(){
        List<DynamicDataSourceEntity> list= dynamicDataSourceService.getList(DynamicDataSourceEntity.class);
        List<ComboBox> comboBoxes=new ArrayList<ComboBox>();
        if(list!=null&&list.size()>0){
            for(DynamicDataSourceEntity entity:list){
                ComboBox comboBox=new ComboBox();
                comboBox.setId(entity.getId());
                comboBox.setText(entity.getDbKey());
                comboBoxes.add(comboBox);
            }
        }
        return  comboBoxes;
    }



    //add-begin--Author:	jg_huangxg Date: 20150625 for：[bugfree号]根据字典表数据库类型获取数据源对象

    @RequestMapping(params = "getDynamicDataSourceParameter")
	@ResponseBody
    public AjaxJson getDynamicDataSourceParameter(@RequestParam String dbType){
    	AjaxJson j = new AjaxJson();
    	SysDatabaseEnum sysDatabaseEnum = SysDatabaseEnum.toEnum(dbType);

    	if (sysDatabaseEnum != null) {
    		Map<String, String> map = new HashMap<String, String>();
        	map.put("driverClass", sysDatabaseEnum.getDriverClass());
        	map.put("url", sysDatabaseEnum.getUrl());
        	map.put("dbtype", sysDatabaseEnum.getDbtype());
        	j.setObj(map);
		}else {
			j.setObj("");
		}

    	return j;
    }

	//add-end--Author: jg_huangxg Date: 20150625 for：[bugfree号]根据字典表数据库类型获取数据源对象
}

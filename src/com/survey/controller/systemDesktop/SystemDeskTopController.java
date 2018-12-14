package com.survey.controller.systemDesktop;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.extend.datasource.DataSourceContextHolder;
import org.qihuasoft.core.extend.datasource.DataSourceType;
import org.qihuasoft.core.util.ContextHolderUtils;
import org.qihuasoft.core.util.IpUtil;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
//import org.qihuasoft.core.util.VideoToFlv;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.tag.vo.datatable.SortDirection;
import org.qihuasoft.web.system.manager.ClientManager;
import org.qihuasoft.web.system.pojo.base.Client;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.pojo.base.TSDocument;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.web.system.service.UserService;
import org.qihuasoft.core.util.MyBeanUtils;

//import com.train.entity.resourcedirectory.ResourceDirectoryEntity;
//import com.train.entity.resourcelibrary.ResourceLibraryEntity;
//import com.train.service.resourcelibrary.ResourceLibraryServiceI;

/**   
 * @Title: Controller
 * @Description: 培训资源管理
 * @author onlineGenerator
 * @date 2015-09-20 13:53:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/systemDeskTop")
public class SystemDeskTopController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SystemDeskTopController.class);

//	@Autowired
//	private ResourceLibraryServiceI resourceLibraryService;
//	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 
	 * @param request
	 */
	@RequestMapping(params = "main")
	public ModelAndView builderMenu(HttpServletRequest request){
		//积分排行
		String scoreSql = "select t1.realname, ifnull(t.score,0)  from `t_s_user` t,t_s_base_user t1 where t.id=t1.id order by t.score desc limit 0,10";
		List scoreList = systemService.findListbySql(scoreSql);
		request.setAttribute("scoreList", scoreList);
		
//		//上传排行榜
//		String ranksql = "select count(create_user_id) as total,upload_user from `tr_resource_library` group by create_user_id order by total desc limit 0,10";
//		List rankList = resourceLibraryService.findListbySql(ranksql);
//		request.setAttribute("rankList", rankList);
//		
//		//资源排行榜
//		String ressql = "select name,preview_num from `tr_resource_library` order by preview_num desc limit 0 ,10";
//		List resList = resourceLibraryService.findListbySql(ressql);
//		request.setAttribute("resList", resList);
		
		return new ModelAndView("desktop/systemDesktop");
	}

}

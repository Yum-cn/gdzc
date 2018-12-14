package com.document.utils.controller;
import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.docmyfile.service.DocMyFileServiceI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.UUID;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.qihuasoft.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tools.zip.ZipFile;
import org.qihuasoft.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.qihuasoft.core.util.ExceptionUtil;
import org.qihuasoft.core.util.indexer.Article;
import org.qihuasoft.core.util.indexer.DocumentUtils;
import org.qihuasoft.core.util.indexer.IndexDao;
import org.qihuasoft.core.util.indexer.QueryResult;
import org.qihuasoft.core.util.reader.WordReader;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.qihuasoft.core.beanvalidator.BeanValidators;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.math.BigDecimal;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller  
 * @Description: 我的文档
 * @author onlineGenerator
 * @date 2017-01-06 14:32:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/fullTextQueryController")
public class FullTextQueryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FullTextQueryController.class);

	@Autowired
	private DocMyFileServiceI docMyFileService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		try{

			return new ModelAndView("com/document/fulltextquery/fullTextQueryIndex");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	} 

	/**
	 * 我的文档列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			String keyword = request.getParameter("keyword");
			request.setAttribute("keyword", keyword);
			if(keyword!=null && !"".equals(keyword)){
		    	IndexDao dao = new IndexDao();
		    	int currentPage = 1;
		    	String str_currentPage = request.getParameter("str_currentPage");
		    	if(str_currentPage!=null && !"".equals(str_currentPage)){
		    		currentPage = Integer.parseInt(str_currentPage);
		    	}
		    	currentPage = currentPage-1;
		    	int firstResult = currentPage*10;
		    	int maxResult = currentPage*10+10;
		    	QueryResult qr = dao.search(keyword, firstResult, maxResult);
		    	List list = qr.getList();
		    	request.setAttribute("list", list);
		    	this.pageBaseMethod(request, 10, qr.getCount());
			}else{
				this.pageBaseMethod(request, 10, 0);
			}
			return new ModelAndView("com/document/fulltextquery/fullTextQueryList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	public void pageBaseMethod(HttpServletRequest request, int pageRow,int totalRow) {
		// 当前页
		// 总条数
		// 总页数

		int currentPage = 1;

		int totalPage = 0;
		// 获取当前页
		String str_currentPage = request.getParameter("str_currentPage");
		currentPage = str_currentPage == null || "".equals(str_currentPage) ? 1
				: Integer.parseInt(str_currentPage);
		// 获取每页的条数
		String str_pageRow = request.getParameter("str_pageRow");
		pageRow = str_pageRow == null || "".equals(str_pageRow) ? pageRow
				: Integer.parseInt(str_pageRow);




		totalPage = (totalRow + pageRow - 1) / pageRow;

		currentPage = currentPage < 1 ? 1 : currentPage;
		currentPage = currentPage > totalPage ? totalPage : currentPage;



		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageRow", pageRow);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalPage", totalPage);
	}
}

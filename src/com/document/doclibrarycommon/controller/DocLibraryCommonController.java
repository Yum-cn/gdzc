package com.document.doclibrarycommon.controller;
import com.document.docemail.entity.DocEmailEntity;
import com.document.docemail.service.DocEmailServiceI;
import com.document.doclibrarycommon.entity.DocPermissionEntity;
import com.document.doclibrarycommon.entity.DocLibraryCommonEntity;
import com.document.doclibrarycommon.service.DocPermissionServiceI;
import com.document.doclibrarycommon.service.DocLibraryCommonServiceI;
import com.document.doclibrarylist.entity.DocLibraryListEntity;
import com.document.docmyfile.entity.DocMyFileEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import org.qihuasoft.core.util.DateUtils;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSDepart;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.qihuasoft.core.util.MyBeanUtils;

import java.io.File;
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
import org.qihuasoft.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.qihuasoft.core.util.ExceptionUtil;
import org.qihuasoft.core.util.indexer.DocumentUtils;

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
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.math.BigDecimal;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller  
 * @Description: 公共文件目录
 * @author onlineGenerator
 * @date 2017-01-24 14:54:31
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/docLibraryCommonController")
public class DocLibraryCommonController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DocLibraryCommonController.class);

	@Autowired
	private DocLibraryCommonServiceI libCommonFileService;
	@Autowired
	private DocEmailServiceI docEmailService;
	@Autowired
	private DocPermissionServiceI docPermissionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 公共文件目录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocLibraryCommonEntity.class); 
			String pId=request.getParameter("pId");
			if(pId==null || "".equals(pId)){
				pId = "1";
			}
			request.setAttribute("pId", pId);
			if(pId!=null && !"".equals(pId)){
				detachedCriteria.add(Restrictions.eq("pId", ""+pId+""));
				detachedCriteria.add(Restrictions.eq("isPackage", "Y"));
			}else{
				detachedCriteria.add(Restrictions.eq("pId", ""));
			}
			
			//计算当前文件夹下有多少文件
			String hql = "select count(*) from DocLibraryCommonEntity where pId='"+pId+"' and isPackage='N'";
			List fileCount = libCommonFileService.findByQueryString(hql);
			request.setAttribute("fileCount", fileCount);
			
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibrarycommon/docLibraryCommonList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 树的列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "libraryTree")
	public ModelAndView libraryTree(HttpServletRequest request) {
		try{
			String hql = "from DocLibraryCommonEntity where isPackage = 'Y' order by createDate desc";
			List resultList = libCommonFileService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibrarycommon/libraryListTree");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	/**
	 * 创建文件夹
	 * 
	 * @return
	 */
	@RequestMapping(params = "createPackage")
	@ResponseBody
	public AjaxJson createPackage(HttpServletRequest request,String pId,String pName) {
		String message = null;
		AjaxJson j = new AjaxJson();
		DocLibraryCommonEntity libCommonFileList=new DocLibraryCommonEntity();
		message = "新建文件夹成功";
		try{
			libCommonFileList.setIsPackage("Y");
			libCommonFileList.setFolderName(pName);
			libCommonFileList.setpId(pId);
			libCommonFileService.save(libCommonFileList);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "新建文件夹失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "showProperty")
	public ModelAndView showProperty(HttpServletRequest request) {
		try{
			String id = request.getParameter("id");
			if (StringUtil.isNotEmpty(id)) {
				DocLibraryCommonEntity docMyFile = libCommonFileService.getEntity(DocLibraryCommonEntity.class, id);
				request.setAttribute("docMyFile", docMyFile);
			}
			String hql="from DocEmailEntity WHERE fileId='"+id+"'";
			List<DocEmailEntity> propertyList=docEmailService.findByQueryString(hql);
			request.setAttribute("propertyList", propertyList);
			return new ModelAndView("com/document/docmyfile/docProperty");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	/**
	 * 公共文件目录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goPermissionSetPage")
	public ModelAndView goPermissionSetPage(DocLibraryCommonEntity libCommonFile, HttpServletRequest req) {
		// id是当前文件夹的ID
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		//根据文件夹ID 查询 权限表中 packageId = id 的数据有几条
		String hql = "from DocPermissionEntity where packageId = '"+id+"' order by createDate desc";
		List<DocPermissionEntity> docPermissionList = docPermissionService.findByQueryString(hql);
		//判断查询的HQL是否有值  如果有 将list封到 
		if(docPermissionList.size()>0){
			req.setAttribute("docPermissionList", docPermissionList);
		}else{
			req.setAttribute("docPermissionList", "0");
		}
		
		return new ModelAndView("com/document/doclibrarycommon/permissionSetPage");
	}
	
	/**
	 * 权限设置
	 * 
	 * @return
	 */
	@RequestMapping(params = "savePermission")
	@ResponseBody
	public void savePermission(HttpServletRequest request) {
		//前端已经排除了空值现象
		String packageId=request.getParameter("packageId");   //request.getParameter根据name属性取值
		String[] userIds = request.getParameterValues("userIds");
		String[] usernames = request.getParameterValues("usernames");
		String delUserIds = request.getParameter("delUserIds");   //执行删除方法的数据字符串
		String[] saveOrUploadIds = request.getParameterValues("primarykeyIds");//根据这个字段 判断 该条数据是原数据还是新添加的数据
		//权限的value是数字   0表示编辑  1表示阅读  2表示上传  3表示打印
		String[] permissions = request.getParameterValues("permissions");
		
		//判断  saveOrUploadIds 长度是否大于0 大于0  有数据 执行更新或者添加   否则  无值不进行添加/更新操作
		if(saveOrUploadIds!=null && saveOrUploadIds.length>0){
			for(int j=0;j<saveOrUploadIds.length;j++){
				if(saveOrUploadIds[j]==""){ //id == null  执行save方法
						DocPermissionEntity permissionEntity=new DocPermissionEntity();
						permissionEntity.setUserId(userIds[j]);  //存人员ID
						permissionEntity.setPackageId(packageId); //存文件夹ID
						permissionEntity.setUserName(usernames[j]);
						//存权限   第一步 判断是否有权限  如果有, 拆解权限字符串
						if(permissions.length>0){
							String[] pList = permissions[j].split(",");
							//第二步  遍历权限数组
							for(int i=0;i<pList.length;i++){
								//第三步  判断 哪个权限是被选中的  存入数据库
								if(pList[i].equals("0")){   //编辑权限
									permissionEntity.setPermissionEdit(pList[i]);
								}else if(pList[i].equals("1")){  //阅读权限
									permissionEntity.setPermissionRead(pList[i]);
								}else if(pList[i].equals("2")){  //上传权限
									permissionEntity.setPermissionUpload(pList[i]);
								}else if(pList[i].equals("3")){  //打印权限
									permissionEntity.setPermissionPrint(pList[i]);
								}
							}
						}
						
						try {
							docPermissionService.save(permissionEntity);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}else{    //id不为空 执行 更新方法  更新只需要更新权限即可
					DocPermissionEntity updateEntity =systemService.getEntity(DocPermissionEntity.class, saveOrUploadIds[j]);
					updateEntity.setPermissionEdit(null);
					updateEntity.setPermissionRead(null);
					updateEntity.setPermissionUpload(null);
					updateEntity.setPermissionPrint(null);
					if(permissions.length>0){
						String[] pList = permissions[j].split(",");
						//第二步  遍历权限数组
						for(int i=0;i<pList.length;i++){
							//第三步  判断 哪个权限是被选中的  存入数据库
							if(pList[i].equals("0")){   //编辑权限
								updateEntity.setPermissionEdit(pList[i]);
							}else if(pList[i].equals("1")){  //阅读权限
								updateEntity.setPermissionRead(pList[i]);
							}else if(pList[i].equals("2")){  //上传权限
								updateEntity.setPermissionUpload(pList[i]);
							}else if(pList[i].equals("3")){  //打印权限
								updateEntity.setPermissionPrint(pList[i]);
							}
						}
					}
					
					try {
						docPermissionService.saveOrUpdate(updateEntity);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}   //更新或保存 if结尾
		
		
		//原数据删除方法
		if(delUserIds!=null && delUserIds!=""){
			String[] delUserIdList = delUserIds.split("@");
			for(int i=0;i<delUserIdList.length;i++){
				DocPermissionEntity per = systemService.getEntity(DocPermissionEntity.class, delUserIdList[i]);
				try {
					//执行删除方法
					docPermissionService.delete(per);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 回收站
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "recycle")
	public ModelAndView recycle(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocLibraryCommonEntity.class); 
			//条件查询
			String query_fileName=request.getParameter("fileName");
			if(query_fileName!=null&&query_fileName!=""){
				request.setAttribute("fileName", query_fileName);
				detachedCriteria.add(Restrictions.like("fileName", "%"+query_fileName+"%"));
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			
			return new ModelAndView("com/document/docmyfile/docRecycleList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 最新文档
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "queryRecentFile")
	public ModelAndView recentFile(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocLibraryCommonEntity.class); 
			
			detachedCriteria.add(Restrictions.eq("isPackage", "N"));
			
			
			Date date = new Date();
			Calendar before7Day  =Calendar.getInstance();//获取当前时间的 java.util.GregorianCalendar 对象
			before7Day.add(Calendar.DATE, -7); //进行时间范围操作
			Date b7d = before7Day.getTime(); //将GregorianCalendar对象转为Date对象
			//Restrictions中时间操作对象应该是Date类型的
			detachedCriteria.add(Restrictions.between("createDate", b7d, date));
			
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			
			return new ModelAndView("com/document/docmyfile/docRecentFileList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 树的列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "fileTree")
	public ModelAndView fileTree(HttpServletRequest request) {
		try{
			String hql = "from DocLibraryCommonEntity where isPackage ='Y' order by createDate desc";
			List resultList = libCommonFileService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibrarycommon/libCommonMoveTree");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@RequestMapping(params = "doMoveFile")
	@ResponseBody
	public AjaxJson doMoveFile(HttpServletRequest request,String fileId,String newPId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文件移动成功";
		
		String hql = "from DocLibraryCommonEntity where id='"+fileId+"'";
		List fileList = libCommonFileService.findByQueryString(hql);
		DocLibraryCommonEntity moveFile=(DocLibraryCommonEntity) fileList.get(0);
		moveFile.setpId(newPId);
		try{
			libCommonFileService.save(moveFile);
		}catch (Exception e) {
			e.printStackTrace();
			message = "文件移动失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 移动文件夹
	 * @param request
	 * @param newPackageId
	 * @param PackageId
	 * @return
	 */
	@RequestMapping(params = "doMovePackage")
	@ResponseBody
	public AjaxJson doMovePackage(HttpServletRequest request,String newPackageId,String packageId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文件夹移动成功";
		
		String hql = "from DocLibraryCommonEntity where id='"+packageId+"'";
		List fileList = libCommonFileService.findByQueryString(hql);
		DocLibraryCommonEntity movePackage=(DocLibraryCommonEntity) fileList.get(0);
		movePackage.setpId(newPackageId);
		try{
			libCommonFileService.save(movePackage);
		}catch (Exception e) {
			e.printStackTrace();
			message = "文件夹移动失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 新建文件夹
	 * 
	 */
	
	
	/**
	 * 查询文件夹名称
	 * 
	 */
	
	@RequestMapping(params = "queryPackageName")
	@ResponseBody
	public AjaxJson queryPackageName(String packageId) {
		HashMap map = new HashMap();
		AjaxJson j = new AjaxJson();
		String hql="from DocLibraryCommonEntity where id='"+packageId+"'";
		List pList = libCommonFileService.findByQueryString(hql);
		if(pList!=null&&pList.size()>0){
			DocLibraryCommonEntity reNamePackage =(DocLibraryCommonEntity) pList.get(0);
			String isP=reNamePackage.getIsPackage();
			System.out.println("isP的值      "+isP);
			if("Y".equals(isP)){
				String packageName = reNamePackage.getFolderName(); 
				map.put("packageName", packageName);
			}else{
				j.setSuccess(false);
			}
		}
		
		j.setAttributes(map);
		return j;
	}
	/**
	 *   文件夹重命名
	 * 
	 */
	
	@RequestMapping(params = "rePackageName")
	@ResponseBody
	public AjaxJson rePackageName(String newPackageName,String id) {
		String message=null;
		AjaxJson j = new AjaxJson();
		message="文件夹重命名成功";
		DocLibraryCommonEntity pke= libCommonFileService.get(DocLibraryCommonEntity.class, id);
		pke.setFolderName(newPackageName);
		try {
			libCommonFileService.save(pke);
		} catch (Exception e) {
			e.printStackTrace();
			message = "文件夹重命名失败";
			throw new BusinessException(e.getMessage());
		}
		
		j.setMsg(message);
		return j;
	}
	
	
	
	
	
	@RequestMapping(params = "doCopyFile")
	@ResponseBody
	public AjaxJson doCopyFile(HttpServletRequest request,String fileId,String newPId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "复制文件成功";
		DocLibraryCommonEntity newFileObject=new DocLibraryCommonEntity();
		
		DocLibraryCommonEntity copyFile = libCommonFileService.get(DocLibraryCommonEntity.class, fileId);
		try{
			MyBeanUtils.copyBeanNotNull2Bean(copyFile,newFileObject); //第一个参数是数据有更新的对象  第二个是新的要保存的对象
			newFileObject.setpId(newPId);
			libCommonFileService.save(newFileObject);
		}catch (Exception e) {
			e.printStackTrace();
			message = "复制文件失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 上传文件
	 * 
	 * @return
	 */
	@RequestMapping(params = "uploadFile")
	@ResponseBody
	public AjaxJson uploadFile(@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "上传文件成功";
		//文件的其他存储信息
		Date date = new Date();
		//获取当前登陆人
		TSUser user = ResourceUtil.getSessionUserName();
		String userId = user.getId();
		String yyyyMM = DateUtils.date2Str(DateUtils.date_yyyy_mm);
		//文件在项目中存放的位置
		String userFilePath = request.getSession().getServletContext().getRealPath("upload/") + "/" + userId + "/" + yyyyMM + "/";
		String pId=request.getParameter("pId");
		
		//文件名
		for(int i=0;i<file.length;i++){
			String fileName=file[i].getOriginalFilename();
			
			String fName=fileName.substring(0, fileName.lastIndexOf("."));
			//文件类型
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
			//UUID型文件名
			fileName=UUID.randomUUID().toString()+"."+fileType;
			//文件临时存放位置
			File targetFile=new File(userFilePath,fileName);
			//如果文件夹不存在 就创建文件夹
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }
			//获取文件大小
			long longFileSize = file[i].getSize();
			String StringFileSize="";
			if(longFileSize>1000000){
				double doubleFileSize=(double)longFileSize/(1024*1024);
				BigDecimal s=new BigDecimal(doubleFileSize);
				double fileSize = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				StringFileSize=String.valueOf(fileSize)+"MB";
			}else{
				double doubleFileSize=(double)longFileSize/(1024);
				BigDecimal s=new BigDecimal(doubleFileSize);
				double fileSize = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				StringFileSize=String.valueOf(fileSize)+"KB";
			}
			//制造文件存放相对路径
			String filePath="upload/" + userId + "/" + yyyyMM + "/" +fileName;
			  
			try{
				file[i].transferTo(targetFile);
				//存数据库
				DocLibraryCommonEntity docMyFile = new DocLibraryCommonEntity();
				docMyFile.setFilePath(filePath);
				docMyFile.setFileSize(StringFileSize);
				docMyFile.setFileName(fName);
				docMyFile.setFileType(fileType);
				docMyFile.setpId(pId);
				docMyFile.setUploadTime(date);
				docMyFile.setIsPackage("N");
				libCommonFileService.save(docMyFile);
			}catch(Exception e){
				e.printStackTrace();
				message = "上传文件失败";
				throw new BusinessException(e.getMessage());
			}
		}
		
		
		j.setMsg(message);
		return j;
	}
	
	
	
	@RequestMapping(params = "uploadFileZip")
	@ResponseBody
	public AjaxJson uploadFileZip(@RequestParam(value = "fileZIP", required = false) MultipartFile[] file,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "上传文件成功";
		//文件的其他存储信息
		Date date = new Date();
		
		String pId=request.getParameter("pId");
		//文件在项目中存放的位置
		String path = request.getSession().getServletContext().getRealPath("upload")+"\\files";  //第一个\是转义字符
		//文件名
		for(int i=0;i<file.length;i++){
			String fileName=file[i].getOriginalFilename();
			
			String fName=fileName.substring(0, fileName.lastIndexOf("."));
			//文件类型
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
			//UUID型文件名
			fileName=UUID.randomUUID().toString()+"."+fileType;
			//文件临时存放位置
			File targetFile=new File(path,fileName);
			//获取文件大小
			long longFileSize = file[i].getSize();
			String StringFileSize="";
			if(longFileSize>1000000){
				double doubleFileSize=(double)longFileSize/(1024*1024);
				BigDecimal s=new BigDecimal(doubleFileSize);
				double fileSize = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				StringFileSize=String.valueOf(fileSize)+"MB";
			}else{
				double doubleFileSize=(double)longFileSize/(1024);
				BigDecimal s=new BigDecimal(doubleFileSize);
				double fileSize = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				StringFileSize=String.valueOf(fileSize)+"KB";
			}
			//制造文件存放相对路径
			String filePath="upload/files/"+fileName;
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
			try{
				file[i].transferTo(targetFile);
				//存数据库
				DocLibraryCommonEntity docMyFile = new DocLibraryCommonEntity();
				docMyFile.setFilePath(filePath);
				docMyFile.setFileSize(StringFileSize);
				docMyFile.setFileName(fName);
				docMyFile.setFileType(fileType);
				docMyFile.setpId(pId);
				docMyFile.setUploadTime(date);
				docMyFile.setIsPackage("N");
				libCommonFileService.save(docMyFile);
//				DocumentUtils.saveDocumentIndex(docMyFile, request);//创建全文索引
			}catch(Exception e){
				e.printStackTrace();
				message = "上传文件失败";
				throw new BusinessException(e.getMessage());
			}
		}
		
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 重命名
	 * 
	 * @return
	 */
	@RequestMapping(params = "reName")
	@ResponseBody
	public AjaxJson reName(HttpServletRequest request,String fileName,String id) {
		String message = null;
		AjaxJson j = new AjaxJson();
		System.out.println("看看ID是什么                         "+id);
		System.out.println("看看NAME是什么                         "+fileName);
		String hql="from DocLibraryCommonEntity where id='"+id+"'";
		List fList=libCommonFileService.findByQueryString(hql);
		DocLibraryCommonEntity file = (DocLibraryCommonEntity) fList.get(0);
		file.setFileName(fileName);
		message = "重命名成功";
		try{
			libCommonFileService.saveOrUpdate(file);
		}catch(Exception e){
			e.printStackTrace();
			message = "重命名失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
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
	public void datagrid(DocLibraryCommonEntity libCommonFile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DocLibraryCommonEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, libCommonFile, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.libCommonFileService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公共文件目录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DocLibraryCommonEntity libCommonFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		libCommonFile = systemService.getEntity(DocLibraryCommonEntity.class, libCommonFile.getId());
		message = "公共文件目录删除成功";
		try{
			libCommonFileService.delete(libCommonFile);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公共文件目录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除公共文件目录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公共文件目录删除成功";
		try{
			for(String id:ids.split(",")){
				DocLibraryCommonEntity libCommonFile = systemService.getEntity(DocLibraryCommonEntity.class, 
				id
				);
				libCommonFileService.delete(libCommonFile);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "公共文件目录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加公共文件目录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(DocLibraryCommonEntity libCommonFile, HttpServletRequest request) {
		String message = null;
		message = "公共文件目录添加成功";
		try{
			libCommonFileService.save(libCommonFile);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公共文件目录添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docLibraryCommonController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新公共文件目录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(DocLibraryCommonEntity libCommonFile, HttpServletRequest request) {
		String message = null;
		message = "公共文件目录更新成功";
		DocLibraryCommonEntity t = libCommonFileService.get(DocLibraryCommonEntity.class, libCommonFile.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(libCommonFile, t);
			libCommonFileService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "公共文件目录更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docLibraryCommonController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 公共文件目录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DocLibraryCommonEntity libCommonFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(libCommonFile.getId())) {
			libCommonFile = libCommonFileService.getEntity(DocLibraryCommonEntity.class, libCommonFile.getId());
			req.setAttribute("libCommonFilePage", libCommonFile);
		}

		return new ModelAndView("com/document/doclibrarycommon/docLibraryCommon-add");
	}
	/**
	 * 公共文件目录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DocLibraryCommonEntity libCommonFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(libCommonFile.getId())) {
			libCommonFile = libCommonFileService.getEntity(DocLibraryCommonEntity.class, libCommonFile.getId());
			req.setAttribute("libCommonFilePage", libCommonFile);
		}
		return new ModelAndView("com/document/doclibrarycommon/docLibraryCommon-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","libCommonFileController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DocLibraryCommonEntity libCommonFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DocLibraryCommonEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, libCommonFile, request.getParameterMap());
		List<DocLibraryCommonEntity> libCommonFiles = this.libCommonFileService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"公共文件目录");
		modelMap.put(NormalExcelConstants.CLASS,DocLibraryCommonEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公共文件目录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,libCommonFiles);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DocLibraryCommonEntity libCommonFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"公共文件目录");
    	modelMap.put(NormalExcelConstants.CLASS,DocLibraryCommonEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公共文件目录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<DocLibraryCommonEntity> listDocLibraryCommonEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DocLibraryCommonEntity.class,params);
				for (DocLibraryCommonEntity libCommonFile : listDocLibraryCommonEntitys) {
					libCommonFileService.save(libCommonFile);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<DocLibraryCommonEntity> list() {
		List<DocLibraryCommonEntity> listLibCommonFiles=libCommonFileService.getList(DocLibraryCommonEntity.class);
		return listLibCommonFiles;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DocLibraryCommonEntity task = libCommonFileService.get(DocLibraryCommonEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DocLibraryCommonEntity libCommonFile, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocLibraryCommonEntity>> failures = validator.validate(libCommonFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			libCommonFileService.save(libCommonFile);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = libCommonFile.getId();
		URI uri = uriBuilder.path("/rest/libCommonFileController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DocLibraryCommonEntity libCommonFile) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocLibraryCommonEntity>> failures = validator.validate(libCommonFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			libCommonFileService.saveOrUpdate(libCommonFile);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		libCommonFileService.deleteEntityById(DocLibraryCommonEntity.class, id);
	}
}

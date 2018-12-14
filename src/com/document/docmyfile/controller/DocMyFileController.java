package com.document.docmyfile.controller;
import com.document.docemail.service.DocEmailServiceI;
import com.document.doclibraryfile.entity.DocLibFileEntity;
import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.docmyfile.service.DocMyFileServiceI;
import com.document.message.entity.MessageEntity;
import com.document.template.service.TemplateServiceI;
import com.document.version.entity.VersionEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.UUID;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/docMyFileController")
public class DocMyFileController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DocMyFileController.class);

	@Autowired
	private DocMyFileServiceI docMyFileService;
	@Autowired
	private DocEmailServiceI docEmailService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	@Autowired
	private TemplateServiceI templateService;

	/**
	 * 文件下载，二进制流方式
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "downloadFiles")  
    public ModelAndView downloadFiles(HttpServletRequest request,HttpServletResponse response){  
		String id = request.getParameter("id");
		DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
		String fileName = docMyFile.getFileName() + "." + docMyFile.getFileType();
		String userFilePath = request.getSession().getServletContext().getRealPath("/") + "/" + docMyFile.getFilePath();
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
        ServletOutputStream out;  
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        File file = new File(userFilePath);  
        try {  
            FileInputStream inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while (b != -1){  
                b = inputStream.read(buffer);  
                //4.写到输出流(out)中  
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;
    }
	
	/**
	 * 我的文档列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocMyFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			detachedCriteria.add(Restrictions.eq("deleteFlag", "0"));
			
			//根据页面的name属性抓取值
			String pId=request.getParameter("pId");
			request.setAttribute("pId", pId);
			//条件查询
			String query_fileName=request.getParameter("fileName");
			if(query_fileName!=null&&query_fileName!=""){
				request.setAttribute("fileName", query_fileName);
				detachedCriteria.add(Restrictions.like("fileName", "%"+query_fileName+"%"));
			}
			
			if(pId!=null && !"".equals(pId)){
				detachedCriteria.add(Restrictions.eq("pId", ""+pId+""));
				detachedCriteria.add(Restrictions.eq("isPackage", "N"));
			}else{
				detachedCriteria.add(Restrictions.eq("pId", ""));
			}
			//当前登录人的文档信息
			TSUser user = ResourceUtil.getSessionUserName();
			detachedCriteria.add(Restrictions.eq("createBy",user.getUserName()));
			
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			//查询所有的格式文件
			String templateHQL = "from TemplateEntity order by createDate desc";
			List templateList = templateService.findByQueryString(templateHQL);
			request.setAttribute("templateList", templateList);
			return new ModelAndView("com/document/docmyfile/docMyFileList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	/**
	 * 显示属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "showProperty")
	public ModelAndView showProperty(HttpServletRequest request) {
		try{
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			String id = request.getParameter("id");
			if (StringUtil.isNotEmpty(id)) {
				DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
				request.setAttribute("docMyFile", docMyFile);
			}

			//协同记录
			String hql="from MessageEntity WHERE documentId='"+id+"' and type=1";
			List<MessageEntity> messageList=docEmailService.findByQueryString(hql);
			List teamworkPropertyList = new ArrayList();
			if(messageList!=null && messageList.size()>0){
				for(int i=0;i<messageList.size();i++){
					MessageEntity me = (MessageEntity)messageList.get(i);
					String receiveUser = me.getReceiveUser();
					if(receiveUser!=null && !"".equals(receiveUser)){
						String[] receiveUserArray = receiveUser.split(",");
						if(receiveUserArray!=null && receiveUserArray.length>0){
							for(int j=0;j<receiveUserArray.length;j++){
								MessageEntity reMe = new MessageEntity();
								reMe.setCreateDate(me.getCreateDate());
								reMe.setReceiveUser(receiveUserArray[j]);
								teamworkPropertyList.add(reMe);
							}
						}
					}
				}
			}
			
			//内部群发
			hql="from MessageEntity WHERE documentId='"+id+"' and type=2";
			messageList=docEmailService.findByQueryString(hql);
			List massPropertyList = new ArrayList();
			if(messageList!=null && messageList.size()>0){
				for(int i=0;i<messageList.size();i++){
					MessageEntity me = (MessageEntity)messageList.get(i);
					String receiveUser = me.getReceiveUser();
					if(receiveUser!=null && !"".equals(receiveUser)){
						String[] receiveUserArray = receiveUser.split(",");
						if(receiveUserArray!=null && receiveUserArray.length>0){
							for(int j=0;j<receiveUserArray.length;j++){
								MessageEntity reMe = new MessageEntity();
								reMe.setCreateDate(me.getCreateDate());
								reMe.setReceiveUser(receiveUserArray[j]);
								String usePermission = me.getUsePermission();
								usePermission = usePermission.replace("1", "编辑");
								usePermission = usePermission.replace("2", "阅读");
								usePermission = usePermission.replace("3", "打印");
								usePermission = usePermission.replace("4", "下载");
								usePermission = usePermission.replaceFirst(",", "");
								reMe.setUsePermission(usePermission);
								massPropertyList.add(reMe);
							}
						}
					}
				}
			}
			request.setAttribute("propertyList", messageList);
			request.setAttribute("teamworkPropertyList", teamworkPropertyList);
			request.setAttribute("massPropertyList", massPropertyList);
			return new ModelAndView("com/document/docmyfile/docProperty");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 显示属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "showVersion")
	public ModelAndView showVersion(HttpServletRequest request) {
		try{
			String id = request.getParameter("id");
			
			//通过ID查找对应的文件的历史版本
			String hql="from VersionEntity WHERE documentId='"+id+"' order by versionNumber desc";
			List<VersionEntity> versionList=docEmailService.findByQueryString(hql);
			//List versionList = new ArrayList();
			
			request.setAttribute("versionList", versionList);
			return new ModelAndView("com/document/docmyfile/docVersion");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
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
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocMyFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			detachedCriteria.add(Restrictions.eq("deleteFlag", "1"));
			//条件查询
			String query_fileName=request.getParameter("fileName");
			if(query_fileName!=null&&query_fileName!=""){
				request.setAttribute("fileName", query_fileName);
				detachedCriteria.add(Restrictions.like("fileName", "%"+query_fileName+"%"));
			}
			//当前登录人的回收站
			TSUser user = ResourceUtil.getSessionUserName();
			detachedCriteria.add(Restrictions.eq("createBy",user.getUserName()));
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			
			//回收站中库目录文件
			DetachedCriteria falseDelLibFile = DetachedCriteria.forClass(DocLibFileEntity.class);
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			falseDelLibFile.add(Restrictions.eq("deleteFlag", "1"));
			falseDelLibFile.add(Restrictions.eq("createBy",user.getUserName()));
			falseDelLibFile.addOrder(Order.desc("createDate"));
			List libFileList =  this.pageBaseMethod(request, falseDelLibFile, commonService, 10);
			request.setAttribute("libFileList", libFileList);
			
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
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocMyFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			detachedCriteria.add(Restrictions.eq("deleteFlag", "0"));
			detachedCriteria.add(Restrictions.eq("isPackage", "N"));
			
			
			Date date = new Date();
			Calendar before7Day  =Calendar.getInstance();//获取当前时间的 java.util.GregorianCalendar 对象
			before7Day.add(Calendar.DATE, -7); //进行时间范围操作
			Date b7d = before7Day.getTime(); //将GregorianCalendar对象转为Date对象
			//Restrictions中时间操作对象应该是Date类型的
			detachedCriteria.add(Restrictions.between("createDate", b7d, date));//第一个参数是字段信息  后两个参数是时间范围
			//当前登录人的最新文档信息
			TSUser user = ResourceUtil.getSessionUserName();
			detachedCriteria.add(Restrictions.eq("createBy",user.getUserName()));
			
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
			TSUser user = ResourceUtil.getSessionUserName();
			String hql = "from DocMyFileEntity where isPackage ='Y' and createBy='"+user.getUserName()+"' and deleteFlag ='0' order by createDate desc";
			List resultList = docMyFileService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/docmyfile/docMyFile_moveFileTree");
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
		
		String hql = "from DocMyFileEntity where id='"+fileId+"'";
		List fileList = docMyFileService.findByQueryString(hql);
		DocMyFileEntity moveFile=(DocMyFileEntity) fileList.get(0);
		moveFile.setpId(newPId);
		try{
			docMyFileService.save(moveFile);
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
		
		String hql = "from DocMyFileEntity where id='"+packageId+"'";
		List fileList = docMyFileService.findByQueryString(hql);
		DocMyFileEntity movePackage=(DocMyFileEntity) fileList.get(0);
		movePackage.setpId(newPackageId);
		try{
			docMyFileService.save(movePackage);
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
	
	@RequestMapping(params = "createPackage")
	@ResponseBody
	public AjaxJson createPackage(String pId,String pName) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "新建文件夹成功";
		DocMyFileEntity newPackage=new DocMyFileEntity();
		newPackage.setDeleteFlag("0");
		newPackage.setpId(pId);
		newPackage.setFolderName(pName);
		newPackage.setIsPackage("Y");
		try{
			docMyFileService.save(newPackage);
		}catch (Exception e) {
			e.printStackTrace();
			message = "新建文件夹失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 查询文件夹名称
	 * 
	 */
	
	@RequestMapping(params = "queryPackageName")
	@ResponseBody
	public AjaxJson queryPackageName(String packageId) {
		HashMap map = new HashMap();
		AjaxJson j = new AjaxJson();
		String hql="from DocMyFileEntity where id='"+packageId+"'";
		List pList = docMyFileService.findByQueryString(hql);
		if(pList!=null&&pList.size()>0){
			DocMyFileEntity reNamePackage =(DocMyFileEntity) pList.get(0);
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
		DocMyFileEntity pke= docMyFileService.get(DocMyFileEntity.class, id);
		pke.setFileName(newPackageName);
		try {
			docMyFileService.save(pke);
		} catch (Exception e) {
			e.printStackTrace();
			message = "文件夹重命名失败";
			throw new BusinessException(e.getMessage());
		}
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 复制文件
	 * 
	 * @return
	 */
	@RequestMapping(params = "doCopyFile")
	@ResponseBody
	public AjaxJson doCopyFile(HttpServletRequest request,String fileId,String newPId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "复制文件成功";
		DocMyFileEntity newFileObject=new DocMyFileEntity();
		
		DocMyFileEntity copyFile = docMyFileService.get(DocMyFileEntity.class, fileId);
		try{
			MyBeanUtils.copyBeanNotNull2Bean(copyFile,newFileObject); //第一个参数是数据有更新的对象  第二个是新的要保存的对象
			newFileObject.setpId(newPId);
			docMyFileService.save(newFileObject);
		}catch (Exception e) {
			e.printStackTrace();
			message = "复制文件失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 树的列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "main")
	public ModelAndView main(HttpServletRequest request) {
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			String hql = "from DocMyFileEntity where deleteFlag ='0' and isPackage = 'Y' and createBy='"+user.getUserName()+"' order by createDate desc";
			List resultList = docMyFileService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/docmyfile/docMyFile_main");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
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
			String uuidFileName = UUID.randomUUID().toString();
			String fileName=file[i].getOriginalFilename();
			
			String fName=fileName.substring(0, fileName.lastIndexOf("."));
			//文件类型
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
			fileName=uuidFileName + "." + fileType;
			//文件临时存放位置
			File targetFile=new File(userFilePath,fileName);
			//如果文件夹不存在 就创建文件夹
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }
			//获取文件大小    文件大小在前端进行判断 方便其他调用文件大小时运算
			long longFileSize = file[i].getSize();
			String StringFileSize="";
			/*if(longFileSize>1000000){
				double doubleFileSize=(double)longFileSize/(1024*1024);
				BigDecimal s=new BigDecimal(doubleFileSize);
				double fileSize = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				StringFileSize=String.valueOf(fileSize)+"MB";
			}else{
				double doubleFileSize=(double)longFileSize/(1024);
				BigDecimal s=new BigDecimal(doubleFileSize);
				double fileSize = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				StringFileSize=String.valueOf(fileSize)+"KB";
			}*/
			//制造文件存放相对路径
			String filePath="upload/" + userId + "/" + yyyyMM + "/" +uuidFileName+"."+fileType;
			StringFileSize=String.valueOf(longFileSize);
			try{
				file[i].transferTo(targetFile);
				//存数据库
				DocMyFileEntity docMyFile = new DocMyFileEntity();
				docMyFile.setFilePath(filePath);
				docMyFile.setFileSize(StringFileSize);
				docMyFile.setFileName(fName);
				docMyFile.setFileType(fileType);
				docMyFile.setpId(pId);
				docMyFile.setUploadTime(date);
				docMyFile.setIsPackage("N");
				docMyFile.setDeleteFlag("0");
				docMyFileService.save(docMyFile);
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
				DocMyFileEntity docMyFile = new DocMyFileEntity();
				docMyFile.setFilePath(filePath);
				docMyFile.setFileSize(StringFileSize);
				docMyFile.setFileName(fName);
				docMyFile.setFileType(fileType);
				docMyFile.setpId(pId);
				docMyFile.setUploadTime(date);
				docMyFile.setIsPackage("N");
				docMyFile.setDeleteFlag("0");
				docMyFileService.save(docMyFile);
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
	 * void方法  这部只做保存 不要返回值
	 * 
	 */
//	@RequestMapping(params = "uploadZIP")
//	
//	public void uploadZIP(@RequestParam(value = "file", required = false) MultipartFile fileZIP,
//			HttpServletRequest request){
//		System.out.println("进入亚索包的表单提交控制器");
		
		/*ZipInputStream Zin=new ZipInputStream((InputStream) file);
		BufferedInputStream Bin=new BufferedInputStream(Zin); 
		String Parent="upload/files/"; //输出路径（文件夹目录）  
        File Fout=null;  
        ZipEntry entry;  
        try {  
            while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
                Fout=new File(Parent,entry.getName());  
                if(!Fout.exists()){  
                    (new File(Fout.getParent())).mkdirs();  
                }  
                FileOutputStream out=new FileOutputStream(Fout);  
                BufferedOutputStream Bout=new BufferedOutputStream(out);  
                int b;  
                while((b=Bin.read())!=-1){  
                    Bout.write(b);  
                }  
                Bout.close();  
                out.close();  
                System.out.println(Fout+"解压成功");      
            }  
            Bin.close();  
            Zin.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  */
//    }   
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(DocMyFileEntity docMyFile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DocMyFileEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docMyFile, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.docMyFileService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 文件重命名
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
		String hql="from DocMyFileEntity where id='"+id+"'";
		List fList=docMyFileService.findByQueryString(hql);
		DocMyFileEntity file = (DocMyFileEntity) fList.get(0);
		file.setFileName(fileName);
		message = "重命名成功";
		try{
			docMyFileService.saveOrUpdate(file);
		}catch(Exception e){
			e.printStackTrace();
			message = "重命名失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 逻辑删除文件
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DocMyFileEntity docMyFile, HttpServletRequest request,String fileId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docMyFile = systemService.getEntity(DocMyFileEntity.class, fileId);
		message = "我的文档删除成功";
		try{
			docMyFile.setDeleteFlag("1");
			docMyFileService.updateEntitie(docMyFile);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "我的文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 逻辑删除文件夹
	 * 
	 * @return
	 */
	@RequestMapping(params = "falseDelPackage")
	@ResponseBody
	public AjaxJson falseDelPackage(DocMyFileEntity docMyFile, HttpServletRequest request,String packageId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docMyFile = systemService.getEntity(DocMyFileEntity.class, packageId);
		message = "文件夹移入回收站";
		try{
			docMyFile.setDeleteFlag("1");
			docMyFileService.updateEntitie(docMyFile);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "我的文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 物理删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "removeFile")
	@ResponseBody
	public AjaxJson removeFile(DocMyFileEntity docMyFile, HttpServletRequest request,String fileId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docMyFile = systemService.getEntity(DocMyFileEntity.class, fileId);
		message = "文档已彻底删除";
		try{
			docMyFileService.delete(docMyFile);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 删除我的文档
	 * 
	 * @return
	 */
	@RequestMapping(params = "restoreFile")
	@ResponseBody
	public AjaxJson restoreFile(DocMyFileEntity docMyFile,HttpServletRequest request,String id) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docMyFile = systemService.getEntity(DocMyFileEntity.class, id);
		message = "文件还原成功";
		try{
			docMyFile.setDeleteFlag("0");
			docMyFileService.updateEntitie(docMyFile);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文件还原失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除我的文档
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "我的文档删除成功";
		try{
			for(String id:ids.split(",")){
				DocMyFileEntity docMyFile = systemService.getEntity(DocMyFileEntity.class, 
				id
				);
				docMyFile.setDeleteFlag("1");
				docMyFileService.updateEntitie(docMyFile);
//				docMyFileService.delete(docMyFile);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "我的文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加我的文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(DocMyFileEntity docMyFile, HttpServletRequest request) {
		String message = null;
		message = "我的文档添加成功";
		try{
			docMyFile.getpId();
			docMyFile.setDeleteFlag("0");
			docMyFileService.save(docMyFile);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "我的文档添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docMyFileController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新我的文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(DocMyFileEntity docMyFile, HttpServletRequest request) {
		String message = null;
		message = "我的文档更新成功";
		DocMyFileEntity t = docMyFileService.get(DocMyFileEntity.class, docMyFile.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(docMyFile, t);
			docMyFileService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "我的文档更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docMyFileController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 我的文档新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DocMyFileEntity docMyFile, HttpServletRequest req) {
		String pId=req.getParameter("pId");
		req.setAttribute("pId", pId);
		if (StringUtil.isNotEmpty(docMyFile.getId())) {
			docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, docMyFile.getId());
			req.setAttribute("docMyFilePage", docMyFile);
		}

		return new ModelAndView("com/document/docmyfile/docMyFile-add");
	}
	/**
	 * 我的文档编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DocMyFileEntity docMyFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docMyFile.getId())) {
			docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, docMyFile.getId());
			req.setAttribute("docMyFilePage", docMyFile);
		}
		return new ModelAndView("com/document/docmyfile/docMyFile-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","docMyFileController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DocMyFileEntity docMyFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DocMyFileEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docMyFile, request.getParameterMap());
		List<DocMyFileEntity> docMyFiles = this.docMyFileService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"我的文档");
		modelMap.put(NormalExcelConstants.CLASS,DocMyFileEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("我的文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,docMyFiles);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DocMyFileEntity docMyFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"我的文档");
    	modelMap.put(NormalExcelConstants.CLASS,DocMyFileEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("我的文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<DocMyFileEntity> listDocMyFileEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DocMyFileEntity.class,params);
				for (DocMyFileEntity docMyFile : listDocMyFileEntitys) {
					docMyFileService.save(docMyFile);
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
	public List<DocMyFileEntity> list() {
		List<DocMyFileEntity> listDocMyFiles=docMyFileService.getList(DocMyFileEntity.class);
		return listDocMyFiles;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DocMyFileEntity task = docMyFileService.get(DocMyFileEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DocMyFileEntity docMyFile, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocMyFileEntity>> failures = validator.validate(docMyFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docMyFileService.save(docMyFile);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = docMyFile.getId();
		URI uri = uriBuilder.path("/rest/docMyFileController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DocMyFileEntity docMyFile) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocMyFileEntity>> failures = validator.validate(docMyFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docMyFileService.saveOrUpdate(docMyFile);
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
		docMyFileService.deleteEntityById(DocMyFileEntity.class, id);
	}
	

}

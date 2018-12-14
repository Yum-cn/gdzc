package com.document.doclibraryfile.controller;
import com.document.doclibrarycommon.service.DocLibraryCommonServiceI;
import com.document.doclibraryfile.entity.DocLibFileEntity;
import com.document.doclibraryfile.service.DocLibFileServiceI;
import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.message.entity.MessageEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
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
import java.io.FileInputStream;
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
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller  
 * @Description: 库目录文档
 * @author onlineGenerator
 * @date 2017-02-22 17:14:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/docLibFileController")
public class DocLibFileController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DocLibFileController.class);

	@Autowired
	private DocLibFileServiceI docLibFileService;
	@Autowired
	private DocLibraryCommonServiceI libCommonFileService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	


	/**
	 * 库目录文档列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocLibFileEntity.class); 
			//过滤 deleteFlag值为1的 数据  (值为1的数据执行了逻辑删除)
			detachedCriteria.add(Restrictions.eq("deleteFlag", "0"));
			detachedCriteria.addOrder(Order.desc("createDate"));
			
			//条件查询
			String query_fileName=request.getParameter("fileName");
			if(query_fileName!=null&&query_fileName!=""){
				request.setAttribute("fileName", query_fileName);
				detachedCriteria.add(Restrictions.like("fileName", "%"+query_fileName+"%"));
			}
			
			//根据页面的name属性抓取值
			String pId=request.getParameter("pId");
			request.setAttribute("pId", pId);
			
			if(pId!=null && !"".equals(pId)){
				detachedCriteria.add(Restrictions.eq("pId", ""+pId+""));
			}else{
				detachedCriteria.add(Restrictions.eq("pId", ""));
			}
			
			List resultList =  this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibfile/docLibFileList");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * easyui AJAX请求数据
	 * 查询树
	 * @param request
	 */
	
	@RequestMapping(params = "libFileTree")
	public ModelAndView libFileTree(HttpServletRequest request) {
		try{
			String hql = "from DocLibraryCommonEntity where isPackage = 'Y' order by createDate desc";
			List resultList = libCommonFileService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibfile/docLibTree");
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
				DocLibFileEntity libfile = docLibFileService.getEntity(DocLibFileEntity.class, id);
				request.setAttribute("docMyFile", libfile);
			}

			//协同记录
			String hql="from MessageEntity WHERE documentId='"+id+"' and type=1";
			List<MessageEntity> messageList=docLibFileService.findByQueryString(hql);
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
			messageList=docLibFileService.findByQueryString(hql);
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
			return new ModelAndView("com/document/doclibfile/libFileProperty");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * 测底删除我的文档
	 * 
	 * @return
	 */
	@RequestMapping(params = "restoreFile")
	@ResponseBody
	public AjaxJson restoreFile(DocLibFileEntity docMyFile,HttpServletRequest request,String id) {
		String message = null;
		AjaxJson j = new AjaxJson();
		docMyFile = systemService.getEntity(DocLibFileEntity.class, id);
		message = "文件还原成功";
		try{
			docMyFile.setDeleteFlag("0");
			docLibFileService.updateEntitie(docMyFile);
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
		if(pId==null||pId==""){
			pId="1";
		}
		
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
			
			//制造文件存放相对路径
			String filePath="upload/" + userId + "/" + yyyyMM + "/" +uuidFileName+"."+fileType;
			StringFileSize=String.valueOf(longFileSize);
			try{
				file[i].transferTo(targetFile);
				//存数据库
				DocLibFileEntity libFileEntity = new DocLibFileEntity();
				libFileEntity.setFilePath(filePath);
				libFileEntity.setFileSize(StringFileSize);
				libFileEntity.setFileName(fName);
				libFileEntity.setFileType(fileType);
				libFileEntity.setpId(pId);
				libFileEntity.setDeleteFlag("0");
				libFileEntity.setUploadDate(date);
				docLibFileService.save(libFileEntity);
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
	 * 文件重命名
	 * 
	 * @return
	 */
	@RequestMapping(params = "reName")
	@ResponseBody
	public AjaxJson reName(HttpServletRequest request,String fileName,String id) {
		String message = null;
		AjaxJson j = new AjaxJson();
		
		String hql="from DocLibFileEntity where id='"+id+"'";
		List fList=docLibFileService.findByQueryString(hql);
		DocLibFileEntity file = (DocLibFileEntity) fList.get(0);
		file.setFileName(fileName);
		message = "重命名成功";
		try{
			docLibFileService.saveOrUpdate(file);
		}catch(Exception e){
			e.printStackTrace();
			message = "重命名失败";
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
		DocLibFileEntity newFileObject=new DocLibFileEntity();
		
		DocLibFileEntity copyFile = docLibFileService.get(DocLibFileEntity.class, fileId);
		try{
			MyBeanUtils.copyBeanNotNull2Bean(copyFile,newFileObject); //第一个参数是数据有更新的对象  第二个是新的要保存的对象
			newFileObject.setpId(newPId);
			docLibFileService.save(newFileObject);
		}catch (Exception e) {
			e.printStackTrace();
			message = "复制文件失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 文件下载，二进制流方式
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "downloadFiles")  
    public ModelAndView downloadFiles(HttpServletRequest request,HttpServletResponse response){  
		String id = request.getParameter("id");
		DocLibFileEntity docMyFile = docLibFileService.getEntity(DocLibFileEntity.class, id);
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
	 * 移动文件树的列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "fileTree")
	public ModelAndView fileTree(HttpServletRequest request) {
		try{
			TSUser user = ResourceUtil.getSessionUserName();
			String hql = "from DocLibraryCommonEntity where isPackage = 'Y'  order by createDate desc";
			List resultList = docLibFileService.findByQueryString(hql);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/document/doclibfile/moveFileTree");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * 物理删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "removeFile")
	@ResponseBody
	public AjaxJson removeFile(DocLibFileEntity libFile, HttpServletRequest request,String fileId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		libFile = systemService.getEntity(DocLibFileEntity.class, fileId);
		message = "文档已彻底删除";
		try{
			docLibFileService.delete(libFile);
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
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(DocLibFileEntity docLibFile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DocLibFileEntity.class, dataGrid);
		//查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docLibFile, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.docLibFileService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 逻辑删除文件
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HttpServletRequest request,String fileId) {
		String message = null;
		AjaxJson j = new AjaxJson();
		DocLibFileEntity docMyFile = systemService.getEntity(DocLibFileEntity.class, fileId);
		message = "我的文档删除成功";
		try{
			docMyFile.setDeleteFlag("1");
			docLibFileService.updateEntitie(docMyFile);
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
	 * 批量删除库目录文档
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "库目录文档删除成功";
		try{
			for(String id:ids.split(",")){
				DocLibFileEntity docLibFile = systemService.getEntity(DocLibFileEntity.class, 
				id
				);
				docLibFileService.delete(docLibFile);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "库目录文档删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加库目录文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(DocLibFileEntity docLibFile, HttpServletRequest request) {
		String message = null;
		message = "库目录文档添加成功";
		try{
			docLibFileService.save(docLibFile);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "库目录文档添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docLibFileController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 更新库目录文档
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(DocLibFileEntity docLibFile, HttpServletRequest request) {
		String message = null;
		message = "库目录文档更新成功";
		DocLibFileEntity t = docLibFileService.get(DocLibFileEntity.class, docLibFile.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(docLibFile, t);
			docLibFileService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "库目录文档更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "docLibFileController.do?list");
		return new ModelAndView("success");
	}
	

	/**
	 * 库目录文档新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DocLibFileEntity docLibFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docLibFile.getId())) {
			docLibFile = docLibFileService.getEntity(DocLibFileEntity.class, docLibFile.getId());
			req.setAttribute("docLibFilePage", docLibFile);
		}

		return new ModelAndView("com/document/doclibfile/docLibFile-add");
	}
	/**
	 * 库目录文档编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DocLibFileEntity docLibFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(docLibFile.getId())) {
			docLibFile = docLibFileService.getEntity(DocLibFileEntity.class, docLibFile.getId());
			req.setAttribute("docLibFilePage", docLibFile);
		}
		return new ModelAndView("com/document/doclibfile/docLibFile-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","docLibFileController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DocLibFileEntity docLibFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DocLibFileEntity.class, dataGrid);
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, docLibFile, request.getParameterMap());
		List<DocLibFileEntity> docLibFiles = this.docLibFileService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"库目录文档");
		modelMap.put(NormalExcelConstants.CLASS,DocLibFileEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("库目录文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,docLibFiles);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DocLibFileEntity docLibFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"库目录文档");
    	modelMap.put(NormalExcelConstants.CLASS,DocLibFileEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("库目录文档列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<DocLibFileEntity> listDocLibFileEntitys = ExcelImportUtil.importExcel(file.getInputStream(),DocLibFileEntity.class,params);
				for (DocLibFileEntity docLibFile : listDocLibFileEntitys) {
					docLibFileService.save(docLibFile);
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
	public List<DocLibFileEntity> list() {
		List<DocLibFileEntity> listDocLibFiles=docLibFileService.getList(DocLibFileEntity.class);
		return listDocLibFiles;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DocLibFileEntity task = docLibFileService.get(DocLibFileEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DocLibFileEntity docLibFile, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocLibFileEntity>> failures = validator.validate(docLibFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docLibFileService.save(docLibFile);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = docLibFile.getId();
		URI uri = uriBuilder.path("/rest/docLibFileController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DocLibFileEntity docLibFile) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DocLibFileEntity>> failures = validator.validate(docLibFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			docLibFileService.saveOrUpdate(docLibFile);
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
		docLibFileService.deleteEntityById(DocLibFileEntity.class, id);
	}
}

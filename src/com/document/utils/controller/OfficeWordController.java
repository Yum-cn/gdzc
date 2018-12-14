package com.document.utils.controller;
import java.io.File;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.util.CopyFileUtil;
import org.qihuasoft.core.util.DateUtils;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.document.docmyfile.entity.DocMyFileEntity;
import com.document.docmyfile.service.DocMyFileServiceI;
import com.document.version.entity.VersionEntity;
import com.document.version.service.VersionServiceI;
import com.zhuozhengsoft.pageoffice.FileSaver;

/**   
 * @Title: Controller  
 * @Description: 我的文档
 * @author onlineGenerator
 * @date 2017-01-06 14:32:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/officeWordController")
public class OfficeWordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OfficeWordController.class);
	@Autowired
	private VersionServiceI versionService;
	@Autowired
	private DocMyFileServiceI docMyFileService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;
	
	@RequestMapping(params = "createWord")
	public ModelAndView oepnOnlineWord(HttpServletRequest request) {
		try{
			String pId = request.getParameter("pId");
			request.setAttribute("pId", pId);
			String fileName = request.getParameter("fileName");
			fileName = URLDecoder.decode(fileName,"UTF-8");
			//存数据库
			DocMyFileEntity docMyFile = new DocMyFileEntity();
			docMyFile.setFileName(fileName);
			docMyFile.setpId(pId);
			docMyFile.setUploadTime(new Date());
			docMyFile.setIsPackage("N");
			docMyFile.setDeleteFlag("0");
			docMyFile.setFileType("doc");
			docMyFile.setFileSize("0");
			docMyFileService.save(docMyFile);
			request.setAttribute("docMyFilePage", docMyFile);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("com/document/pageoffice/createWord");
	}

	@RequestMapping(params = "readWord")
	public ModelAndView readWord(HttpServletRequest request) {
		String id = request.getParameter("id");
		DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
		request.setAttribute("docMyFilePage", docMyFile);
		return new ModelAndView("com/document/pageoffice/readWord");
	}

	@RequestMapping(params = "editWord")
	public ModelAndView editWord(HttpServletRequest request) {
		String id = request.getParameter("id");
		DocMyFileEntity docMyFile = docMyFileService.getEntity(
				DocMyFileEntity.class, id);
		request.setAttribute("docMyFilePage", docMyFile);
		return new ModelAndView("com/document/pageoffice/editWord");
	}
	@RequestMapping(params = "printWord")
	public ModelAndView printWord(HttpServletRequest request) {
		String id = request.getParameter("id");
		DocMyFileEntity docMyFile = docMyFileService.getEntity(
				DocMyFileEntity.class, id);
		request.setAttribute("docMyFilePage", docMyFile);
		return new ModelAndView("com/document/pageoffice/printWord");
	}
	@RequestMapping(params = "saveCreateWord")
	public ModelAndView saveCreateWord(HttpServletRequest request,HttpServletResponse response) {
		try {
			TSUser user = ResourceUtil.getSessionUserName();
			String pId = request.getParameter("pId");
			String userId = user.getId();
			int fileSize = 0;
			String yyyyMM = DateUtils.date2Str(DateUtils.date_yyyy_mm);
			String userFilePath = request.getSession().getServletContext().getRealPath("upload/") + "/" + userId + "/" + yyyyMM + "/";
			File file =new File(userFilePath);    
			//如果文件夹不存在则创建    
			File dir = new File(userFilePath);
			if (!dir.exists()) {
				try {
					dir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			FileSaver fs = new FileSaver(request, response);
			// 保存文件
			String uuid = java.util.UUID.randomUUID().toString();
			String filePath = userFilePath + "/" + uuid + ".doc";
			String webPath = "upload/" + userId + "/" + yyyyMM + "/" + uuid + ".doc";
			fs.saveToFile(filePath);
			fileSize = fs.getFileSize();

			long longFileSize = fileSize;
			String StringFileSize=String.valueOf(longFileSize);
			//获取文件大小
//			long longFileSize = fileSize;
//			String StringFileSize="";
//			if(longFileSize>1000000){
//				double doubleFileSize=(double)longFileSize/(1024*1024);
//				BigDecimal s=new BigDecimal(doubleFileSize);
//				double size = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//				StringFileSize=String.valueOf(size)+"MB";
//			}else{
//				double doubleFileSize=(double)longFileSize/(1024);
//				BigDecimal s=new BigDecimal(doubleFileSize);
//				double size = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//				StringFileSize=String.valueOf(size)+"KB";
//			}
			//存数据库
			String id = request.getParameter("id");
			DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
			docMyFile.setFilePath(webPath);
			docMyFile.setFileSize(StringFileSize);
			docMyFile.setFileType("doc");
			docMyFile.setpId(pId);
			docMyFile.setUploadTime(new Date());
			docMyFile.setIsPackage("N");
			docMyFile.setDeleteFlag("0");
			docMyFileService.save(docMyFile);
			// 设置保存结果
			fs.setCustomSaveResult(docMyFile.getId());
			// fs.showPage(300,300);
			fs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	@RequestMapping(params = "saveEditWord")
	public ModelAndView saveEditWord(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		TSUser user = ResourceUtil.getSessionUserName();
		DocMyFileEntity docMyFile = docMyFileService.getEntity(DocMyFileEntity.class, id);
		String userFilePath = request.getSession().getServletContext().getRealPath("/") + "/" + docMyFile.getFilePath();
		 
		FileSaver fs = new FileSaver(request, response);
		fs.saveToFile(userFilePath);
		//fs.showPage(300,300);
		fs.close();
		//获取文件大小
		int fileSize = fs.getFileSize();
		long longFileSize = fileSize;
		String StringFileSize=String.valueOf(longFileSize);
//		long longFileSize = fileSize;
//		String StringFileSize="";
//		if(longFileSize>1000000){
//			double doubleFileSize=(double)longFileSize/(1024*1024);
//			BigDecimal s=new BigDecimal(doubleFileSize);
//			double size = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//			StringFileSize=String.valueOf(size)+"MB";
//		}else{
//			double doubleFileSize=(double)longFileSize/(1024);
//			BigDecimal s=new BigDecimal(doubleFileSize);
//			double size = s.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//			StringFileSize=String.valueOf(size)+"KB";
//		}
		docMyFile.setFileSize(StringFileSize);
		try {
			docMyFileService.saveOrUpdate(docMyFile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//生成文档版本号
		
		String destFileName = this.getFileNameNoEx(userFilePath);
		String versionFilePath = this.getFileNameNoEx(docMyFile.getFilePath());
		String versionNumberSQL = "select CONCAT(version_number+1,'') as version_number from `doc_version` where document_id='"+id+"' order by version_number desc limit 0 ,1";
		List versionNumberList = systemService.findListbySql(versionNumberSQL);
		String versionNumber = "0001";
		if(versionNumberList!=null && versionNumberList.size()>0){
			versionNumber = String.valueOf(versionNumberList.get(0));
			for(int i=versionNumber.length();i<4;i++){
				versionNumber = "0" + versionNumber;
			}
		}
		destFileName = destFileName + "_" + versionNumber + "." + docMyFile.getFileType();
		versionFilePath = versionFilePath + "_" + versionNumber + "." + docMyFile.getFileType();
		CopyFileUtil.copyFile(userFilePath, destFileName, true);
		VersionEntity ve = new VersionEntity();
		ve.setPath(versionFilePath);
		ve.setType("doc");
		ve.setVersionNumber(versionNumber);
		ve.setDocumentId(id);
		try {
			versionService.save(ve);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFileNameNoEx(String filename) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int dot = filename.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (filename.length()))) { 
                return filename.substring(0, dot); 
            } 
        } 
        return filename; 
    }
}

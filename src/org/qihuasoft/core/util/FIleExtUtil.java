package org.qihuasoft.core.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FIleExtUtil {

	public static Map<String, String> extMap = new HashMap<String, String>();

	static {
//		extMap.put("images", "gif,jpg,jpeg,png,bmp");
//		extMap.put("flashs", "swf,flv");
//		extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
//		extMap.put("files", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2");
		
		extMap.put("jpg", "图片"); //JPEG (jpg)    
        extMap.put("png", "图片");  //PNG (png)    
        extMap.put("gif", "图片");  //GIF (gif)    
        extMap.put("tif", "图片");  //TIFF (tif)    
        extMap.put("bmp", "图片"); //Windows Bitmap (bmp)    
        extMap.put("dwg", "图片"); //CAD (dwg)    
        extMap.put("html", "文件");  //HTML (html)    
        extMap.put("rtf", "文件");  //Rich Text Format (rtf)    
        extMap.put("xml", "文件");    
        extMap.put("zip", "压缩包");    
        extMap.put("rar", "压缩包");    
        extMap.put("psd", "设计图");  //Photoshop (psd)    
        extMap.put("eml", "邮件");  //Email [thorough only] (eml)    
        extMap.put("dbx", "邮件");  //Outlook Express (dbx)    
        extMap.put("pst", "邮件");  //Outlook (pst)    
        extMap.put("xls", "office文件");  //MS Word    
        extMap.put("xlsx", "office文件件");  //MS Word    
        extMap.put("doc", "office文件");  //MS Excel 注意：word 和 excel的文件头一样    
        extMap.put("docx", "office文件");  //MS Excel 注意：word 和 excel的文件头一样    
        extMap.put("ppt", "office文件");  //MS Excel 注意：word 和 excel的文件头一样    
        extMap.put("pptx", "office文件");  //MS Excel 注意：word 和 excel的文件头一样   
        extMap.put("mdb", "office文件");  //MS Access (mdb)    
        extMap.put("wpd", "office文件"); //WordPerfect (wpd)     
        extMap.put("eps", "其他");    
        extMap.put("ps", "其他");    
        extMap.put("pdf", "pdf文件");  //Adobe Acrobat (pdf)    
        extMap.put("qdf", "其他");  //Quicken (qdf)    
        extMap.put("pwl", "其他");  //Windows Password (pwl)    
        extMap.put("wav", "视频");  //Wave (wav)    
        extMap.put("avi", "视频");    
        extMap.put("ram", "音频");  //Real Audio (ram)    
        extMap.put("rm", "视频");  //Real Media (rm)    
        extMap.put("mpg", "视频");  //    
        extMap.put("mov", "视频");  //Quicktime (mov)    
        extMap.put("asf", "视频"); //Windows Media (asf)   
        extMap.put("mp4", "视频"); //Windows Media (asf)   
        extMap.put("mid", "音频");  //MIDI (mid)    
	}

	
}

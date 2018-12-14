package org.qihuasoft.core.util.indexer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.qihuasoft.core.util.DateUtils;
import org.qihuasoft.core.util.MyBeanUtils;
import org.qihuasoft.core.util.reader.ExcelReader;
import org.qihuasoft.core.util.reader.HtmlReader;
import org.qihuasoft.core.util.reader.PdfReader;
import org.qihuasoft.core.util.reader.PowerPointReader;
import org.qihuasoft.core.util.reader.RtfReader;
import org.qihuasoft.core.util.reader.TxtReader;
import org.qihuasoft.core.util.reader.WordReader;

//import com.document.docmyfile.entity.DocMyFileEntity;

public class DocumentUtils {
	public static Document article2Document(Article article) {  
        Document doc = new Document();  
        doc.add(new Field("id", article.getId().toString(), TextField.TYPE_STORED));  
        doc.add(new Field("createName", article.getCreateName(), TextField.TYPE_STORED));  
        doc.add(new Field("createBy", article.getCreateBy(), TextField.TYPE_STORED)); 
        String createDate="";
        if(article!=null && article.getCreateDate()!=null){
        	createDate = DateUtils.date2Str(article.getCreateDate(), DateUtils.date_sdf);
        }
        doc.add(new Field("createDate", createDate, TextField.TYPE_STORED));  
        doc.add(new Field("fileName", article.getFileName(), TextField.TYPE_STORED));  
        doc.add(new Field("filePath", article.getFilePath(), TextField.TYPE_STORED));  
        doc.add(new Field("isPackage", article.getIsPackage(), TextField.TYPE_STORED));  
        doc.add(new Field("pId", article.getpId(), TextField.TYPE_STORED));  
        doc.add(new Field("deleteFlag", article.getDeleteFlag(), TextField.TYPE_STORED));  
        doc.add(new Field("content", article.getContent(), TextField.TYPE_STORED));  
        return doc;  
    }  
  
    public static Article document2Ariticle(Document doc) {  
        Article article = new Article();  
        article.setId(doc.get("id"));  
        article.setCreateName(doc.get("createName"));
        article.setCreateBy(doc.get("createBy"));
//        article.setCreateDate(doc.get("createDate"));
        article.setFileName(doc.get("fileName"));
        article.setFilePath(doc.get("filePath"));
        article.setIsPackage(doc.get("isPackage"));
        article.setpId(doc.get("pId"));
        article.setDeleteFlag(doc.get("deleteFlag"));
        if(doc!=null && doc.get("createDate")!=null && !"".equals(doc.get("createDate"))){
        	Date createDate = DateUtils.str2Date(doc.get("createDate"), DateUtils.date_sdf);
        	article.setCreateDate(createDate);
        }     
        article.setContent(doc.get("content"));
        return article;  
    }  
//    public static boolean saveDocumentIndex(DocMyFileEntity docMyFile,HttpServletRequest request){
//    	try{
//			if(docMyFile.getFilePath()!=null && !"".equals(docMyFile.getFilePath())){
//				String docRealPath  = request.getRealPath("/")+docMyFile.getFilePath();
//				String content = "";
//				if(docMyFile.getFilePath().indexOf("doc")!=-1 || docMyFile.getFilePath().indexOf("docx")!=-1){
//					if(docMyFile.getFilePath().indexOf("docx")!=-1){
//						WordReader wr = new WordReader();
//						content = wr.getTextFromWord2007(docRealPath);
//					}else{
//						WordReader wr = new WordReader();
//						content = wr.getTextFromWord(docRealPath);
//					}
//				}
//				if(docMyFile.getFilePath().indexOf("pdf")!=-1){
//					PdfReader pr = new PdfReader();
//					content = pr.getTextFromPdf(docRealPath);
//				}
//				if(docMyFile.getFilePath().indexOf("rtf")!=-1){
//					RtfReader rr = new RtfReader();
//					content = rr.getTextFromRtf(docRealPath);
//				}
//				if(docMyFile.getFilePath().indexOf("xls")!=-1 || docMyFile.getFilePath().indexOf("xlsx")!=-1){
//					if(docMyFile.getFilePath().indexOf("xlsx")!=-1){
//						ExcelReader er = new ExcelReader();
//						content = er.getTextFromExcel2007(docRealPath);
//					}else{
//						ExcelReader er = new ExcelReader();
//						content = er.getTextFromExcel(docRealPath);
//					}
//				}				
//				if(docMyFile.getFilePath().indexOf("txt")!=-1){
//					TxtReader rr = new TxtReader();
//					content = rr.getTextFromTxt(docRealPath);
//				}
//				if(docMyFile.getFilePath().indexOf("html")!=-1 || docMyFile.getFilePath().indexOf("htm")!=-1){
//					HtmlReader hr = new HtmlReader();
//					content = hr.getTextFromHtml(docRealPath);
//				}
//				if(docMyFile.getFilePath().indexOf("ppt")!=-1 || docMyFile.getFilePath().indexOf("pptx")!=-1){
//					if(docMyFile.getFilePath().indexOf("pptx")!=-1){
//						PowerPointReader ppr = new PowerPointReader();
//						content = ppr.getTextFromPPT2007(docRealPath);
//					}else{
//						PowerPointReader ppr = new PowerPointReader();
//						content = ppr.getTextFromPPT(docRealPath);
//					}
//				}
//
//				Article ac = new Article();
//		    	IndexDao dao = new IndexDao();
//		    	ac.setContent(content);
//				MyBeanUtils.copyBeanNotNull2Bean(docMyFile, ac);
//
//		    	dao.save(ac);
//			}
//			return true;
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}
//    	return false;
//    }
}

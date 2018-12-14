package org.qihuasoft.core.util.reader;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;  
  
import org.apache.poi.hslf.extractor.PowerPointExtractor;  
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;  
import org.apache.poi.xslf.usermodel.XMLSlideShow; 
public class PowerPointReader {
	//直接抽取ppt97-2003的全部内容 ppt  
    public static String getTextFromPPT(String filePath) {  
        InputStream is = null;  
        PowerPointExtractor extractor = null;  
        String text="";  
        try {  
            is = new FileInputStream(filePath);  
            extractor = new PowerPointExtractor(is);  
            text=extractor.getText();
            is.close();
        } catch (FileNotFoundException e) {  
            System.out.println("没有找到指定路径"+filePath);  
            e.printStackTrace();  
        } catch (IOException e) {  
            System.out.println("getTextFromPPT IO错误");  
            e.printStackTrace();  
        }  
        return text;  
    }  
    //抽取幻灯片2007+全部内容  pptx  
    public static String getTextFromPPT2007(String filePath){  
        InputStream is = null;  
        XMLSlideShow slide = null;  
        String text="";  
        try {  
            is = new FileInputStream(filePath);  
            slide = new XMLSlideShow(is);  
            XSLFPowerPointExtractor extractor=new XSLFPowerPointExtractor(slide);  
            text=extractor.getText();  
            is.close();  
        } catch (FileNotFoundException e) {  
            System.out.println("没有找到指定路径"+filePath);  
            e.printStackTrace();  
        } catch (IOException e) {  
            System.out.println("getTextFromPPT2007 IO错误");  
            e.printStackTrace();  
        }  
        return text;  
    }  
}

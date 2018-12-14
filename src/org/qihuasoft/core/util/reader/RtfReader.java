package org.qihuasoft.core.util.reader;
import java.io.File;   
import java.io.FileInputStream;   
import java.io.IOException;   
import java.io.InputStream;   
  
import javax.swing.text.BadLocationException;   
import javax.swing.text.DefaultStyledDocument;   
import javax.swing.text.rtf.RTFEditorKit;   
  
  
public class RtfReader {   
    public RtfReader(){   
    }   
    /**  
     * @param filePath 文件路径  
     * @return 读出的rtf的内容  
     */  
    public String getTextFromRtf(String filePath) {   
        String result = null;   
        File file = new File(filePath);   
        try {          
            DefaultStyledDocument styledDoc = new DefaultStyledDocument();   
            InputStream is = new FileInputStream(file);   
            new RTFEditorKit().read(is, styledDoc, 0);   
            result = new String(styledDoc.getText(0,styledDoc.getLength()).getBytes("ISO8859_1"));   
            //提取文本，读取中文需要使用ISO8859_1编码，否则会出现乱码   
        } catch (IOException e) {   
            e.printStackTrace();   
        } catch (BadLocationException e) {   
            e.printStackTrace();   
        }   
        return result;   
    }   
       
}  
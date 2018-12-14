package org.qihuasoft.core.util.indexer;

import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.qihuasoft.core.util.PropertiesUtil;

public class LuceneUtils {
	 private static Logger logger = Logger.getLogger(LuceneUtils.class);  
	    private  Directory directory;  
	    private Analyzer analyzer;  
	    public LuceneUtils() {  
	        try {
	    		PropertiesUtil p = new PropertiesUtil("config/system_config.properties");
	    		String lucenePath = p.readProperty("lucene_path");
	            directory = FSDirectory.open(Paths.get(lucenePath));   
	            // analyzer = new StandardAnalyzer();  
	            analyzer = new SmartChineseAnalyzer();  
	        } catch (Exception e) {  
	            logger.error("LuceneUtils error!", e);  
	        }  
	    }  
	  
	    public Directory getDirectory() {  
	        return directory;  
	    }  
	  
	    public Analyzer getAnalyzer() {  
	        return analyzer;  
	    }  
	  
	    public void closeIndexWriter(IndexWriter indexWriter) {  
	        if (indexWriter != null) {  
	            try {  
	                indexWriter.close();  
	            } catch (Exception e2) {  
	                logger.error("indexWriter.close error", e2);  
	            }  
	        }  
	    } 

}

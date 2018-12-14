package com.document.docmyfile.service;
import com.document.docmyfile.entity.DocMyFileEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocMyFileServiceI extends CommonService{
	
 	public void delete(DocMyFileEntity entity) throws Exception;
 	
 	public Serializable save(DocMyFileEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocMyFileEntity entity) throws Exception;
 	
}

package com.document.doclibraryfile.service;
import com.document.doclibraryfile.entity.DocLibFileEntity;

import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocLibFileServiceI extends CommonService{
	
 	public void delete(DocLibFileEntity entity) throws Exception;
 	
 	public Serializable save(DocLibFileEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocLibFileEntity entity) throws Exception;
 	
}

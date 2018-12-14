package com.document.doclibrarycommon.service;
import com.document.doclibrarycommon.entity.DocLibraryCommonEntity;

import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocLibraryCommonServiceI extends CommonService{
	
 	public void delete(DocLibraryCommonEntity entity) throws Exception;
 	
 	public Serializable save(DocLibraryCommonEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocLibraryCommonEntity entity) throws Exception;
 	
}

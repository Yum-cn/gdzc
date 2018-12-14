package com.document.doclibrarycommon.service;
import org.qihuasoft.core.common.service.CommonService;

import com.document.doclibrarycommon.entity.DocPermissionEntity;

import java.io.Serializable;

public interface DocPermissionServiceI extends CommonService{
	
 	public void delete(DocPermissionEntity entity) throws Exception;
 	
 	public Serializable save(DocPermissionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocPermissionEntity entity) throws Exception;
 	
}

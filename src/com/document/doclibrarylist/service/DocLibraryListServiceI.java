package com.document.doclibrarylist.service;
import com.document.doclibrarylist.entity.DocLibraryListEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocLibraryListServiceI extends CommonService{
	
 	public void delete(DocLibraryListEntity entity) throws Exception;
 	
 	public Serializable save(DocLibraryListEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocLibraryListEntity entity) throws Exception;
 	
}

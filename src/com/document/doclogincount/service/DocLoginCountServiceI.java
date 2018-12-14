package com.document.doclogincount.service;
import com.document.doclogincount.entity.DocLoginCountEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocLoginCountServiceI extends CommonService{
	
 	public void delete(DocLoginCountEntity entity) throws Exception;
 	
 	public Serializable save(DocLoginCountEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocLoginCountEntity entity) throws Exception;
 	
}

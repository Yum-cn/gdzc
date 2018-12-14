package com.document.docemail.service;
import com.document.docemail.entity.DocEmailEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocEmailServiceI extends CommonService{
	
 	public void delete(DocEmailEntity entity) throws Exception;
 	
 	public Serializable save(DocEmailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocEmailEntity entity) throws Exception;
 	
}

package com.document.docnotice.service;
import com.document.docnotice.entity.DocNoticeEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocNoticeServiceI extends CommonService{
	
 	public void delete(DocNoticeEntity entity) throws Exception;
 	
 	public Serializable save(DocNoticeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocNoticeEntity entity) throws Exception;
 	
}

package com.document.docnotice.service;


import org.qihuasoft.core.common.service.CommonService;

import com.document.docnotice.entity.DocNoticeCountEntity;

import java.io.Serializable;

public interface DocNoticeCountServiceI extends CommonService{
	
 	public void delete(DocNoticeCountEntity entity) throws Exception;
 	
 	public Serializable save(DocNoticeCountEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocNoticeCountEntity entity) throws Exception;
 	
}

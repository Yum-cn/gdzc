package com.document.template.service;
import org.qihuasoft.core.common.service.CommonService;

import com.document.template.entity.TemplateEntity;

import java.io.Serializable;

public interface TemplateServiceI extends CommonService{
	
 	public void delete(TemplateEntity entity) throws Exception;
 	
 	public Serializable save(TemplateEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TemplateEntity entity) throws Exception;
 	
}

package com.assets.resume.service;
import com.assets.resume.entity.ResumeEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ResumeServiceI extends CommonService{
	
 	public void delete(ResumeEntity entity) throws Exception;
 	
 	public Serializable save(ResumeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ResumeEntity entity) throws Exception;
 	
}

package com.document.version.service;
import com.document.version.entity.VersionEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface VersionServiceI extends CommonService{
	
 	public void delete(VersionEntity entity) throws Exception;
 	
 	public Serializable save(VersionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(VersionEntity entity) throws Exception;
 	
}

package com.sc.substitutemanager.service;
import com.sc.substitutemanager.entity.ScSubstituteManagerEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ScSubstituteManagerServiceI extends CommonService{
	
 	public void delete(ScSubstituteManagerEntity entity) throws Exception;
 	
 	public Serializable save(ScSubstituteManagerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ScSubstituteManagerEntity entity) throws Exception;
 	
}

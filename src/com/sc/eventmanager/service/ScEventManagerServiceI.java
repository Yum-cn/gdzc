package com.sc.eventmanager.service;
import com.sc.eventmanager.entity.ScEventManagerEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ScEventManagerServiceI extends CommonService{
	
 	public void delete(ScEventManagerEntity entity) throws Exception;
 	
 	public Serializable save(ScEventManagerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ScEventManagerEntity entity) throws Exception;
 	
}

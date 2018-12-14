package com.assets.allot.service;
import com.assets.allot.entity.AllotEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface AllotServiceI extends CommonService{
	
 	public void delete(AllotEntity entity) throws Exception;
 	
 	public Serializable save(AllotEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AllotEntity entity) throws Exception;
 	
}

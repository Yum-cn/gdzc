package com.assets.store.service;
import com.assets.store.entity.StoreEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface StoreServiceI extends CommonService{
	
 	public void delete(StoreEntity entity) throws Exception;
 	
 	public Serializable save(StoreEntity entity) throws Exception;
 	
 	public void saveOrUpdate(StoreEntity entity) throws Exception;
 	
}

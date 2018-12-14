package com.assets.scrap.service;
import com.assets.scrap.entity.ScrapEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ScrapServiceI extends CommonService{
	
 	public void delete(ScrapEntity entity) throws Exception;
 	
 	public Serializable save(ScrapEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ScrapEntity entity) throws Exception;
 	
}

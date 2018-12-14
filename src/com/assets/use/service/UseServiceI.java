package com.assets.use.service;
import com.assets.use.entity.UseEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface UseServiceI extends CommonService{
	
 	public void delete(UseEntity entity) throws Exception;
 	
 	public Serializable save(UseEntity entity) throws Exception;
 	
 	public void saveOrUpdate(UseEntity entity) throws Exception;
 	
}

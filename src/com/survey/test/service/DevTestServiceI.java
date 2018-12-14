package com.survey.test.service;
import com.survey.test.entity.DevTestEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DevTestServiceI extends CommonService{
	
 	public void delete(DevTestEntity entity) throws Exception;
 	
 	public Serializable save(DevTestEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DevTestEntity entity) throws Exception;
 	
}

package com.sc.schedulemanager.service;
import com.sc.schedulemanager.entity.ScheduleManagerEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ScheduleManagerServiceI extends CommonService{
	
 	public void delete(ScheduleManagerEntity entity) throws Exception;
 	
 	public Serializable save(ScheduleManagerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ScheduleManagerEntity entity) throws Exception;
 	
}

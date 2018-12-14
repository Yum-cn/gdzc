package com.document.schedule.service;
import com.document.schedule.entity.ScheduleEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ScheduleServiceI extends CommonService{
	
 	public void delete(ScheduleEntity entity) throws Exception;
 	
 	public Serializable save(ScheduleEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ScheduleEntity entity) throws Exception;
 	
}

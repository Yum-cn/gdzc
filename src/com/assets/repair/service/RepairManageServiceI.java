package com.assets.repair.service;
import com.assets.repair.entity.RepairManageEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface RepairManageServiceI extends CommonService{
	
 	public void delete(RepairManageEntity entity) throws Exception;
 	
 	public Serializable save(RepairManageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RepairManageEntity entity) throws Exception;
 	
}

package com.assets.returnstock.service;
import com.assets.returnstock.entity.ReturnStockEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface ReturnStockServiceI extends CommonService{
	
 	public void delete(ReturnStockEntity entity) throws Exception;
 	
 	public Serializable save(ReturnStockEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ReturnStockEntity entity) throws Exception;
 	
}

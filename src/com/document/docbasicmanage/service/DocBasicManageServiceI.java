package com.document.docbasicmanage.service;
import com.document.docbasicmanage.entity.DocBasicManageEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface DocBasicManageServiceI extends CommonService{
	
 	public void delete(DocBasicManageEntity entity) throws Exception;
 	
 	public Serializable save(DocBasicManageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DocBasicManageEntity entity) throws Exception;
 	
}

package com.document.message.service;
import com.document.message.entity.MessageEntity;
import org.qihuasoft.core.common.service.CommonService;

import java.io.Serializable;

public interface MessageServiceI extends CommonService{
	
 	public void delete(MessageEntity entity) throws Exception;
 	
 	public Serializable save(MessageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(MessageEntity entity) throws Exception;
 	
}

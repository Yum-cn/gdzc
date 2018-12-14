package org.qihuasoft.web.system.service;

import java.util.List;


import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.web.system.pojo.base.TSAttachment;

/**
 * 
 * @author  张代浩
 *
 */
public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}

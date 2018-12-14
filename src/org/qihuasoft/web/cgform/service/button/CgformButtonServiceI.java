package org.qihuasoft.web.cgform.service.button;

import java.util.List;


import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.web.cgform.entity.button.CgformButtonEntity;

/**
 * 
 * @author  张代浩
 *
 */
public interface CgformButtonServiceI extends CommonService{
	
	/**
	 * 查询按钮list
	 * @param formId
	 * @return
	 */
	public List<CgformButtonEntity> getCgformButtonListByFormId(String formId);

	/**
	 * 校验按钮唯一性
	 * @param cgformButtonEntity
	 * @return
	 */
	public List<CgformButtonEntity> checkCgformButton(CgformButtonEntity cgformButtonEntity);
	
	
}

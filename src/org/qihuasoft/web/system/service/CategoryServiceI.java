package org.qihuasoft.web.system.service;

import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.web.system.pojo.base.TSCategoryEntity;

public interface CategoryServiceI extends CommonService{
	/**
	 * 保存分类管理
	 * @param category
	 */
	void saveCategory(TSCategoryEntity category);

}

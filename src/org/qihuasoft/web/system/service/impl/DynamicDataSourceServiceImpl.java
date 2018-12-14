package org.qihuasoft.web.system.service.impl;

import java.util.List;

import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.web.system.pojo.base.DynamicDataSourceEntity;
import org.qihuasoft.web.system.service.DynamicDataSourceServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("dynamicDataSourceService")
@Transactional
public class DynamicDataSourceServiceImpl extends CommonServiceImpl implements DynamicDataSourceServiceI {

	/**初始化数据库信息，TOMCAT启动时直接加入到内存中**/
	public List<DynamicDataSourceEntity> initDynamicDataSource() {
		ResourceUtil.dynamicDataSourceMap.clear();

		List<DynamicDataSourceEntity> dynamicSourceEntityList = this.commonDao.loadAll(DynamicDataSourceEntity.class);

		for (DynamicDataSourceEntity dynamicSourceEntity : dynamicSourceEntityList) {
			ResourceUtil.dynamicDataSourceMap.put(dynamicSourceEntity.getDbKey(), dynamicSourceEntity);
		}
		return dynamicSourceEntityList;
	}

	public static DynamicDataSourceEntity getDbSourceEntityByKey(String dbKey) {
		DynamicDataSourceEntity dynamicDataSourceEntity = ResourceUtil.dynamicDataSourceMap.get(dbKey);

		return dynamicDataSourceEntity;
	}

	public void refleshCache() {
		initDynamicDataSource();
	}

	//add-begin--Author:luobaoli  Date:20150620 for：增加通过数据源Key获取数据源Type
	@Override

	public DynamicDataSourceEntity getDynamicDataSourceEntityForDbKey(String dbKey){
		List<DynamicDataSourceEntity> dynamicDataSourceEntitys = commonDao.findHql("from DynamicDataSourceEntity where dbKey = ?", dbKey);
		if(dynamicDataSourceEntitys.size()>0)
			return dynamicDataSourceEntitys.get(0);
		return null;
	}

	//add-end--Author:luobaoli  Date:20150620 for：增加通过数据源Key获取数据源Type

}
package org.qihuasoft.web.system.service.impl;

import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import org.qihuasoft.web.system.service.TimeTaskServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("timeTaskService")
@Transactional
public class TimeTaskServiceImpl extends CommonServiceImpl implements TimeTaskServiceI {
	
}
package org.qihuasoft.web.cgform.enhance;

import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.util.LogUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zzl_h on 2015/11/24.
 */
@Service("cgformJavaInterDemo")
public class CgformJavaInterDemo implements CgformEnhanceJavaInter {
    @Override
    public void execute(Map map) throws BusinessException {
    	LogUtil.info("============调用[java增强]成功!==============");
    }
}

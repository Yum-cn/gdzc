package org.jeecgframework.workflow.util;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * 类名：FlowUtil
 * 功能：流程实例运行中辅助类
 * 详细：此类暴露给流程，可在流程定义使用表达式来使用此类的方法，必须由Spring创建才有效
 * 
 * 对外暴露的名称 flowUtil
 * 
 * 作者：jeecg
 * 版本：1.0
 * 日期：2013-8-10 下午4:28:40
 *
 */
public class FlowUtil {

	public List stringToList(String content){
		String[] s = content.split(",");
		return Arrays.asList(s);
	}
}

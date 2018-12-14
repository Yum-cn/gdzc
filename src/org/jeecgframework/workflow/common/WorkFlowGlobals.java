package org.jeecgframework.workflow.common;



/**  
* 项目名称：jeecg
* 类名称：Globals   
* 类描述：  全局变量定义
* 创建人：zhoujf      
* @version    
*
 */
public final class WorkFlowGlobals {
	
	/**
	 * 人员类型
	 */
	public static Short User_Normal=1;//正常
	public static Short User_Forbidden=0;//禁用
	public static Short User_ADMIN=-1;//超级管理员
	
	/**
	 *日志级别定义
	 */
	public static Short Log_Leavel_INFO=1;
	public static Short Log_Leavel_WARRING=2;
	public static Short Log_Leavel_ERROR=3;
	 /**
	  * 日志类型
	  */
	 public static Short Log_Type_LOGIN=1; //登陆
	 public static Short Log_Type_EXIT=2;  //退出
	 public static Short Log_Type_INSERT=3; //插入
	 public static Short Log_Type_DEL=4; //删除
	 public static Short Log_Type_UPDATE=5; //更新
	 public static Short Log_Type_UPLOAD=6; //上传
	 public static Short Log_Type_OTHER=7; //其他

	 /**
	  * 流程发布状态
	  */
	public static Short Process_Deploy_NO=0;//未发布
	public static Short Process_Deploy_YES=1;//已发布
	 
	 /**
	  * 流程节点监听映射表状态
	  */
	public static Short Process_Listener_NO=0;//禁用
	public static Short Process_Listener_YES=1;//启用
	 
	 /**
	  * 监听状态值
	  */
	public static Short Listener_No=0;//禁用
	public static Short Listener_Yes=1;//启用
	 
	 /**
	  * 监听器类型
	  */
	public static Short Listener_Type_Task=2;//任务监听器
	public static Short Listener_Type_Excution=1;//执行监听器
	
	 /**
	  * 数据库表名字前缀
	  */
	public static String DataBase_Activiti="act";//引擎表
	public static String DataBase_Base="t_s";//系统基础表
	public static String DataBase_Bus="t_b";//业务表
	public static String DataBase_Process="t_p";//自定义引擎表
	 
	 /**
	  * 用户是否同步到引擎
	  */
	public static Short Activiti_Deploy_NO=0;//不同步
	public static Short Activiti_Deploy_YES=1;//同步
	
	/**
	  * 词典分组定义
	  */
	public static String TypeGroup_Database="database";//数据表分类
	
	public static String ProcNode_Start= "nodeStart";//流程起始节点名称
}

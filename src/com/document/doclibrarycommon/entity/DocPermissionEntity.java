package com.document.doclibrarycommon.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity  
 * @Description: 权限设置
 * @author onlineGenerator
 * @date 2017-02-09 12:12:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "doc_permission", schema = "")
@SuppressWarnings("serial")
public class DocPermissionEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**文件夹ID*/
	@Excel(name="文件夹ID")
	private java.lang.String packageId;
	/**人员ID*/
	@Excel(name="人员ID")
	private java.lang.String userId;
	/**人员姓名*/
	@Excel(name="人员姓名")
	private java.lang.String userName;
	/**权限 0编辑 1阅读 2上传 3打印*/
	@Excel(name="编辑权限")
	private java.lang.String permissionEdit;
	/**权限 0编辑 1阅读 2上传 3打印*/
	@Excel(name="阅读权限")
	private java.lang.String permissionRead;
	/**权限 0编辑 1阅读 2上传 3打印*/
	@Excel(name="上传权限")
	private java.lang.String permissionUpload;
	/**权限 0编辑 1阅读 2上传 3打印*/
	@Excel(name="打印权限")
	private java.lang.String permissionPrint;
	
	

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件夹ID
	 */
	@Column(name ="PACKAGE_ID",nullable=true,length=32)
	public java.lang.String getPackageId(){
		return this.packageId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件夹ID
	 */
	public void setPackageId(java.lang.String packageId){
		this.packageId = packageId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员ID
	 */
	@Column(name ="USER_ID",nullable=true,length=32)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员ID
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员姓名
	 */
	@Column(name ="USER_NAME",nullable=true,length=32)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员姓名
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编辑权限
	 */
	@Column(name ="PERMISSION_EDIT",nullable=true,length=8)
	public java.lang.String getPermissionEdit() {
		return permissionEdit;
	}

	public void setPermissionEdit(java.lang.String permissionEdit) {
		this.permissionEdit = permissionEdit;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  阅读权限
	 */
	@Column(name ="PERMISSION_READ",nullable=true,length=8)
	public java.lang.String getPermissionRead() {
		return permissionRead;
	}

	public void setPermissionRead(java.lang.String permissionRead) {
		this.permissionRead = permissionRead;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上传权限
	 */
	@Column(name ="PERMISSION_UPLOAD",nullable=true,length=8)
	public java.lang.String getPermissionUpload() {
		return permissionUpload;
	}

	public void setPermissionUpload(java.lang.String permissionUpload) {
		this.permissionUpload = permissionUpload;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打印权限
	 */
	@Column(name ="PERMISSION_PRINT",nullable=true,length=8)
	public java.lang.String getPermissionPrint() {
		return permissionPrint;
	}

	public void setPermissionPrint(java.lang.String permissionPrint) {
		this.permissionPrint = permissionPrint;
	}
	
}

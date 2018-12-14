package com.document.docmyfile.entity;


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
 * @Description: 我的文档
 * @author onlineGenerator
 * @date 2017-01-06 14:32:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "doc_my_file", schema = "")
@SuppressWarnings("serial")
public class DocMyFileEntity implements java.io.Serializable {
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
	/**所属部门*/
	
	/**
	 * 逻辑删除标记  0/1 0代表未删除 1代表删除 
	 * */
	private java.lang.String deleteFlag;
	@Column(name ="deleteFlag",nullable=true,length=10)
	public java.lang.String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(java.lang.String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**文件*/
	private java.lang.String filePath;
	@Column(name ="filePath",nullable=true,length=50)
	public java.lang.String getFilePath() {
		return filePath;
	}

	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	/**所属公司*/
	private java.lang.String fileType;
	@Column(name ="fileType",nullable=true,length=20)
	public java.lang.String getFileType() {
		return fileType;
	}

	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	/**流程状态*/
	private java.lang.String bpmStatus;
	/**文件名称*/
	@Excel(name="文件名称")
	private java.lang.String fileName;
	@Excel(name="文件名称")
	private java.lang.String folderName;
	@Column(name ="folderName",nullable=true,length=50)
	public java.lang.String getFolderName() {
		return folderName;
	}

	public void setFolderName(java.lang.String folderName) {
		this.folderName = folderName;
	}

	
	private java.lang.String isPackage;
	@Column(name ="isPackage",nullable=true,length=50)
	public java.lang.String getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(java.lang.String isPackage) {
		this.isPackage = isPackage;
	}

	/**父类ID*/
	@Excel(name="父类ID")
	private java.lang.String pId;
	@Column(name ="pId",nullable=true,length=50)
	public java.lang.String getpId() {
		return pId;
	}

	public void setpId(java.lang.String pId) {
		this.pId = pId;
	}

	/**文件大小*/
	@Excel(name="文件大小")
	private java.lang.String fileSize;
	/**上传时间*/
	@Excel(name="上传时间",format = "yyyy-MM-dd")
	private java.util.Date uploadTime;
	
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
	 *@return: java.lang.String  文件名称
	 */
	@Column(name ="FILE_NAME",nullable=true,length=32)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件名称
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件大小
	 */
	@Column(name ="FILE_SIZE",nullable=true,length=32)
	public java.lang.String getFileSize(){
		return this.fileSize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件大小
	 */
	public void setFileSize(java.lang.String fileSize){
		this.fileSize = fileSize;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上传时间
	 */
	@Column(name ="UPLOAD_TIME",nullable=true,length=32)
	public java.util.Date getUploadTime(){
		return this.uploadTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上传时间
	 */
	public void setUploadTime(java.util.Date uploadTime){
		this.uploadTime = uploadTime;
	}
}

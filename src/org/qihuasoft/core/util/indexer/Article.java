package org.qihuasoft.core.util.indexer;

import org.jeecgframework.poi.excel.annotation.Excel;

public class Article {
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
	/**
	 * 逻辑删除标记  0/1 0代表未删除 1代表删除 
	 * */
	private java.lang.String deleteFlag;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**文件*/
	private java.lang.String filePath;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**文件名称*/
	private java.lang.String fileName;
	/**文件名称*/
	private java.lang.String isPackage;
	private java.lang.String pId;
	private java.lang.String content;
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	public java.lang.String getSysOrgCode() {
		return sysOrgCode;
	}
	public void setSysOrgCode(java.lang.String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}
	public java.lang.String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(java.lang.String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public java.lang.String getSysCompanyCode() {
		return sysCompanyCode;
	}
	public void setSysCompanyCode(java.lang.String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}
	public java.lang.String getFilePath() {
		return filePath;
	}
	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}
	public java.lang.String getBpmStatus() {
		return bpmStatus;
	}
	public void setBpmStatus(java.lang.String bpmStatus) {
		this.bpmStatus = bpmStatus;
	}
	public java.lang.String getFileName() {
		return fileName;
	}
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}
	public java.lang.String getIsPackage() {
		return isPackage;
	}
	public void setIsPackage(java.lang.String isPackage) {
		this.isPackage = isPackage;
	}
	public java.lang.String getpId() {
		return pId;
	}
	public void setpId(java.lang.String pId) {
		this.pId = pId;
	}
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
    
}

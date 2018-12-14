package com.document.docnotice.entity;

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
 * @Description: 通知公告
 * @author onlineGenerator
 * @date 2017-02-13 12:05:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "doc_notice", schema = "")
@SuppressWarnings("serial")
public class DocNoticeEntity implements java.io.Serializable {
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
	/**标题*/
	@Excel(name="标题")
	private java.lang.String noticeTitle;
	/**发布人*/
	@Excel(name="发布人")
	private java.lang.String noticeSender;
	/**阅读范围*/
	@Excel(name="阅读范围")
	private java.lang.String noticeAudience;
	
	/**阅读范围人员ID*/
	@Excel(name="阅读范围人员ID")
	private java.lang.String audienceId;
	
	/**文档附件名字*/
	@Excel(name="文档附件名字")
	private java.lang.String fileName;
	
	@Excel(name="文档路径")
	private java.lang.String filePath;
	
	/**公告正文*/
	@Excel(name="公告正文")
	private java.lang.String noticeContent;
	
	/**过期时间*/
	private java.util.Date deadline;
	@Column(name ="deadline",nullable=true,length=50)
	public java.util.Date getDeadline() {
		return deadline;
	}

	public void setDeadline(java.util.Date deadline) {
		this.deadline = deadline;
	}

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
	 *@return: java.lang.String  标题
	 */
	@Column(name ="NOTICE_TITLE",nullable=true,length=32)
	public java.lang.String getNoticeTitle(){
		return this.noticeTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setNoticeTitle(java.lang.String noticeTitle){
		this.noticeTitle = noticeTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发布人
	 */
	@Column(name ="NOTICE_SENDER",nullable=true,length=32)
	public java.lang.String getNoticeSender(){
		return this.noticeSender;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发布人
	 */
	public void setNoticeSender(java.lang.String noticeSender){
		this.noticeSender = noticeSender;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  阅读范围
	 */
	@Column(name ="NOTICE_AUDIENCE",nullable=true,length=5000)
	public java.lang.String getNoticeAudience(){
		return this.noticeAudience;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  阅读范围
	 */
	public void setNoticeAudience(java.lang.String noticeAudience){
		this.noticeAudience = noticeAudience;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文档附件名字
	 */
	@Column(name ="FILE_NAME",nullable=true,length=32)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文档附件名字
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文档路径
	 */
	@Column(name ="FILE_PATH",nullable=true,length=32)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文档路径
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公告正文
	 */
	@Column(name ="NOTICE_CONTENT",nullable=true,length=32)
	public java.lang.String getNoticeContent(){
		return this.noticeContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公告正文
	 */
	public void setNoticeContent(java.lang.String noticeContent){
		this.noticeContent = noticeContent;
	}
	
	@Column(name ="audience_id",nullable=true,length=500)
	public java.lang.String getAudienceId() {
		return audienceId;
	}

	public void setAudienceId(java.lang.String audienceId) {
		this.audienceId = audienceId;
	}
	
}

package com.sc.substitutemanager.entity;

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
 * @Description: 代课分配管理
 * @author onlineGenerator
 * @date 2017-01-09 15:38:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "sc_substitute_manager", schema = "")
@SuppressWarnings("serial")
public class ScSubstituteManagerEntity implements java.io.Serializable {
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
	/**请假人*/
	@Excel(name="请假人")
	private java.lang.String leaveMan;
	/**请假人ID*/
//	@Excel(name="请假人ID")
	private java.lang.String leaveUserid;
	/**代课人*/
	@Excel(name="代课人")
	private java.lang.String substituteMan;
	/**代课人ID*/
//	@Excel(name="代课人ID")
	private java.lang.String substituteUserid;
	/**代课人OPENID*/
//	@Excel(name="代课人OPENID")
	private java.lang.String substituteOepnid;
	/**代课班级*/
	@Excel(name="代课班级")
	private java.lang.String substituteClass;
	/**代课节次*/
	@Excel(name="代课节次")
	private java.lang.String substituteSection;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	/**代课时间*/
	private java.util.Date substituteTime;
	@Excel(name="代课地点")
	private java.lang.String address;
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
	 *@return: java.lang.String  请假人
	 */
	@Column(name ="LEAVE_MAN",nullable=true,length=50)
	public java.lang.String getLeaveMan(){
		return this.leaveMan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假人
	 */
	public void setLeaveMan(java.lang.String leaveMan){
		this.leaveMan = leaveMan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假人ID
	 */
	@Column(name ="LEAVE_USERID",nullable=true,length=50)
	public java.lang.String getLeaveUserid(){
		return this.leaveUserid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假人ID
	 */
	public void setLeaveUserid(java.lang.String leaveUserid){
		this.leaveUserid = leaveUserid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代课人
	 */
	@Column(name ="SUBSTITUTE_MAN",nullable=true,length=50)
	public java.lang.String getSubstituteMan(){
		return this.substituteMan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代课人
	 */
	public void setSubstituteMan(java.lang.String substituteMan){
		this.substituteMan = substituteMan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代课人ID
	 */
	@Column(name ="SUBSTITUTE_USERID",nullable=true,length=50)
	public java.lang.String getSubstituteUserid(){
		return this.substituteUserid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代课人ID
	 */
	public void setSubstituteUserid(java.lang.String substituteUserid){
		this.substituteUserid = substituteUserid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代课人OPENID
	 */
	@Column(name ="SUBSTITUTE_OEPNID",nullable=true,length=50)
	public java.lang.String getSubstituteOepnid(){
		return this.substituteOepnid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代课人OPENID
	 */
	public void setSubstituteOepnid(java.lang.String substituteOepnid){
		this.substituteOepnid = substituteOepnid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代课班级
	 */
	@Column(name ="SUBSTITUTE_CLASS",nullable=true,length=255)
	public java.lang.String getSubstituteClass(){
		return this.substituteClass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代课班级
	 */
	public void setSubstituteClass(java.lang.String substituteClass){
		this.substituteClass = substituteClass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代课节次
	 */
	@Column(name ="SUBSTITUTE_SECTION",nullable=true,length=255)
	public java.lang.String getSubstituteSection(){
		return this.substituteSection;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代课节次
	 */
	public void setSubstituteSection(java.lang.String substituteSection){
		this.substituteSection = substituteSection;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=32)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	
	@Column(name ="substitute_time",nullable=true,length=255)
	public java.util.Date getSubstituteTime() {
		return substituteTime;
	}

	public void setSubstituteTime(java.util.Date substituteTime) {
		this.substituteTime = substituteTime;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}
}

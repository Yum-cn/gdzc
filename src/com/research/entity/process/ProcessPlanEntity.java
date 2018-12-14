package com.research.entity.process;

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

/**   
 * @Title: Entity
 * @Description: 工作计划
 * @author onlineGenerator
 * @date 2016-07-26 13:40:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_process_plan", schema = "")
@SuppressWarnings("serial")
public class ProcessPlanEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**开始时间*/
	private java.util.Date startDate;
	/**结束时间*/
	private java.util.Date endDate;
	/**研究阶段*/
	private java.lang.String yjjd;
	/**研究的主题*/
	private java.lang.String theme;
	/**负责人*/
	private java.lang.String responsibility;
	/**任务描述*/
	private java.lang.String taskDescription;
	/**课题ID*/
	private java.lang.String topId;
	/**创建人*/
	private java.lang.String createUser;
	/**创建单位*/
	private java.lang.String createOrg;
	/**创建时间*/
	private java.util.Date createDate;
	
	private java.lang.String attFile;
	private java.lang.String attFileName;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="START_DATE",nullable=true)
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="END_DATE",nullable=true)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  研究的主题
	 */
	@Column(name ="THEME",nullable=true,length=255)
	public java.lang.String getTheme(){
		return this.theme;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  研究的主题
	 */
	public void setTheme(java.lang.String theme){
		this.theme = theme;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人
	 */
	@Column(name ="RESPONSIBILITY",nullable=true,length=50)
	public java.lang.String getResponsibility(){
		return this.responsibility;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人
	 */
	public void setResponsibility(java.lang.String responsibility){
		this.responsibility = responsibility;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务描述
	 */
	@Column(name ="TASK_DESCRIPTION",nullable=true,length=255)
	public java.lang.String getTaskDescription(){
		return this.taskDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务描述
	 */
	public void setTaskDescription(java.lang.String taskDescription){
		this.taskDescription = taskDescription;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  课题ID
	 */
	@Column(name ="TOP_ID",nullable=true,length=50)
	public java.lang.String getTopId(){
		return this.topId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  课题ID
	 */
	public void setTopId(java.lang.String topId){
		this.topId = topId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_USER",nullable=true,length=50)
	public java.lang.String getCreateUser(){
		return this.createUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateUser(java.lang.String createUser){
		this.createUser = createUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建单位
	 */
	@Column(name ="CREATE_ORG",nullable=true,length=50)
	public java.lang.String getCreateOrg(){
		return this.createOrg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建单位
	 */
	public void setCreateOrg(java.lang.String createOrg){
		this.createOrg = createOrg;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}

	public java.lang.String getYjjd() {
		return yjjd;
	}

	public void setYjjd(java.lang.String yjjd) {
		this.yjjd = yjjd;
	}

	public java.lang.String getAttFile() {
		return attFile;
	}

	public void setAttFile(java.lang.String attFile) {
		this.attFile = attFile;
	}

	public java.lang.String getAttFileName() {
		return attFileName;
	}

	public void setAttFileName(java.lang.String attFileName) {
		this.attFileName = attFileName;
	}
	
}

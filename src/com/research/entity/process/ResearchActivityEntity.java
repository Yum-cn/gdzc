package com.research.entity.process;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.String;
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
 * @Description: 科研活动管理
 * @author onlineGenerator
 * @date 2016-07-27 12:38:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_research_activity", schema = "")
@SuppressWarnings("serial")
public class ResearchActivityEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**活动时间*/
	private java.util.Date activitiesDate;
	/**活动主题*/
	private java.lang.String title;
	/**参与人员*/
	private java.lang.String participant;
	/**活动优点*/
	private java.lang.String advantage;
	/**活动问题*/
	private java.lang.String problem;
	/**活动反思*/
	private java.lang.String reflect;
	/**活动内容记录*/
	private java.lang.String content;
	/**课题ID*/
	private java.lang.String topId;
	/**创建人*/
	private java.lang.String createUser;
	/**创建单位*/
	private java.lang.String createOrg;
	/**创建时间*/
	private java.util.Date createDate;
	
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
	 *@return: java.util.Date  活动时间
	 */
	@Column(name ="ACTIVITIES_DATE",nullable=true)
	public java.util.Date getActivitiesDate(){
		return this.activitiesDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  活动时间
	 */
	public void setActivitiesDate(java.util.Date activitiesDate){
		this.activitiesDate = activitiesDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动主题
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动主题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参与人员
	 */
	@Column(name ="PARTICIPANT",nullable=true,length=255)
	public java.lang.String getParticipant(){
		return this.participant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参与人员
	 */
	public void setParticipant(java.lang.String participant){
		this.participant = participant;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动优点
	 */
	@Column(name ="ADVANTAGE",nullable=true,length=255)
	public java.lang.String getAdvantage(){
		return this.advantage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动优点
	 */
	public void setAdvantage(java.lang.String advantage){
		this.advantage = advantage;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动问题
	 */
	@Column(name ="PROBLEM",nullable=true,length=255)
	public java.lang.String getProblem(){
		return this.problem;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动问题
	 */
	public void setProblem(java.lang.String problem){
		this.problem = problem;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动反思
	 */
	@Column(name ="REFLECT",nullable=true,length=255)
	public java.lang.String getReflect(){
		return this.reflect;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动反思
	 */
	public void setReflect(java.lang.String reflect){
		this.reflect = reflect;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动内容记录
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动内容记录
	 */
	public void setContent(java.lang.String content){
		this.content = content;
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
	@Column(name ="CREATE_DATE",nullable=true,length=50)
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
	@Column(name ="top_id",nullable=true,length=50)
	public java.lang.String getTopId() {
		return topId;
	}

	public void setTopId(java.lang.String topId) {
		this.topId = topId;
	}
	
}

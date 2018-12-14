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
 * @Description: 学习记录
 * @author onlineGenerator
 * @date 2016-08-14 10:42:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_learning_record", schema = "")
@SuppressWarnings("serial")
public class LearningRecordEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**讲座人*/
	private java.lang.String lecture;
	/**讲座日期*/
	private java.util.Date lectureDate;
	/**感想*/
	private java.lang.String feel;
	/**创建时间*/
	private java.util.Date createDate;
	/**创建单位*/
	private java.lang.String createOrg;
	/**创建人*/
	private java.lang.String createUser;
	/**课题ID*/
	private java.lang.String topId;
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
	 *@return: java.lang.String  讲座人
	 */
	@Column(name ="LECTURE",nullable=true,length=100)
	public java.lang.String getLecture(){
		return this.lecture;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  讲座人
	 */
	public void setLecture(java.lang.String lecture){
		this.lecture = lecture;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  讲座日期
	 */
	@Column(name ="LECTURE_DATE",nullable=true)
	public java.util.Date getLectureDate(){
		return this.lectureDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  讲座日期
	 */
	public void setLectureDate(java.util.Date lectureDate){
		this.lectureDate = lectureDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  感想
	 */
	@Column(name ="FEEL",nullable=true)
	public java.lang.String getFeel(){
		return this.feel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  感想
	 */
	public void setFeel(java.lang.String feel){
		this.feel = feel;
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
	@Column(name ="top_id",nullable=true,length=50)
	public java.lang.String getTopId() {
		return topId;
	}

	public void setTopId(java.lang.String topId) {
		this.topId = topId;
	}
	
}

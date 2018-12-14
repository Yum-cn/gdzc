package com.survey.entity.questionlibrary;

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
 * @Description: 题库主表信息
 * @author onlineGenerator
 * @date 2015-09-13 20:43:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "survey_question_library", schema = "")
@SuppressWarnings("serial")
public class QuestionLibraryEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**题库大类*/
	private java.lang.String type;
	/**题库小类*/
	private java.lang.String smallType;
	/**试题类型*/
	private java.lang.String style;
	/**题目标题*/
	private java.lang.String title;
	/**试题创建人*/
	private java.lang.String createuser;
	/**创建时间*/
	private java.util.Date createTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  题库大类
	 */
	@Column(name ="TYPE",nullable=true,length=50)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  题库大类
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  题库小类
	 */
	@Column(name ="SMALL_TYPE",nullable=true,length=255)
	public java.lang.String getSmallType(){
		return this.smallType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  题库小类
	 */
	public void setSmallType(java.lang.String smallType){
		this.smallType = smallType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试题类型
	 */
	@Column(name ="STYLE",nullable=true,length=20)
	public java.lang.String getStyle(){
		return this.style;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试题类型
	 */
	public void setStyle(java.lang.String style){
		this.style = style;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  题目标题
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  题目标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试题创建人
	 */
	@Column(name ="CREATEUSER",nullable=true,length=255)
	public java.lang.String getCreateuser(){
		return this.createuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试题创建人
	 */
	public void setCreateuser(java.lang.String createuser){
		this.createuser = createuser;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
}

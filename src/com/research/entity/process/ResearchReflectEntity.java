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
 * @Description: 研究反思
 * @author onlineGenerator
 * @date 2016-07-29 11:52:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_research_reflect", schema = "")
@SuppressWarnings("serial")
public class ResearchReflectEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**标题*/
	private java.lang.String title;
	/**反思内容*/
	private java.lang.String content;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  反思内容
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  反思内容
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建时间
	 */
	
	

	@Column(name ="top_id",nullable=true)
	public java.lang.String getTopId() {
		return topId;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}
	
	@Column(name ="CREATE_DATE",nullable=true)
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public void setTopId(java.lang.String topId) {
		this.topId = topId;
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

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
 * @Description: 参阅资料
 * @author onlineGenerator
 * @date 2016-07-27 13:41:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_see_resources", schema = "")
@SuppressWarnings("serial")
public class SeeResourcesEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**摘记日期*/
	private java.util.Date noteDate;
	/**资料名称*/
	private java.lang.String title;
	/**0书籍1网络*/
	private java.lang.String type;
	/**作者*/
	private java.lang.String author;
	/**出处*/
	private java.lang.String source;
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
	 *@return: java.util.Date  摘记日期
	 */
	@Column(name ="NOTE_DATE",nullable=true)
	public java.util.Date getNoteDate(){
		return this.noteDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  摘记日期
	 */
	public void setNoteDate(java.util.Date noteDate){
		this.noteDate = noteDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资料名称
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资料名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0书籍1网络
	 */
	@Column(name ="TYPE",nullable=true,length=10)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0书籍1网络
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作者
	 */
	@Column(name ="AUTHOR",nullable=true,length=50)
	public java.lang.String getAuthor(){
		return this.author;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作者
	 */
	public void setAuthor(java.lang.String author){
		this.author = author;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出处
	 */
	@Column(name ="SOURCE",nullable=true,length=255)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出处
	 */
	public void setSource(java.lang.String source){
		this.source = source;
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
	@Column(name ="top_id",nullable=true)
	public java.lang.String getTopId() {
		return topId;
	}

	public void setTopId(java.lang.String topId) {
		this.topId = topId;
	}
}

package com.research.entity.conclusion;

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
 * @Description: 结题申请
 * @author onlineGenerator
 * @date 2016-07-29 18:55:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_conclusion", schema = "")
@SuppressWarnings("serial")
public class ConclusionEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**成果名称*/
	private java.lang.String name;
	/**成果形式*/
	private java.lang.String shape;
	/**课题ID*/
	private java.lang.String topId;
	/**成果附件地址*/
	private java.lang.String filePath;
	/**成果附件真实名称*/
	private java.lang.String fileName;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成果名称
	 */
	@Column(name ="NAME",nullable=true,length=255)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成果名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成果形式
	 */
	@Column(name ="SHAPE",nullable=true,length=50)
	public java.lang.String getShape(){
		return this.shape;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成果形式
	 */
	public void setShape(java.lang.String shape){
		this.shape = shape;
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
	 *@return: java.lang.String  成果附件地址
	 */
	@Column(name ="FILE_PATH",nullable=true,length=500)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成果附件地址
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成果附件真实名称
	 */
	@Column(name ="FILE_NAME",nullable=true,length=255)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成果附件真实名称
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
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
}

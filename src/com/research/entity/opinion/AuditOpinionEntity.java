package com.research.entity.opinion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 审核意见表
 * @author onlineGenerator
 * @date 2016-07-31 20:02:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_audit_opinion", schema = "")
@SuppressWarnings("serial")
public class AuditOpinionEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**课题ID*/
	private java.lang.String topId;
	/**意见*/
	private java.lang.String opinion;
	/**0申报审核；1结题审核*/
	private java.lang.String type;
	/**0同意；1不同意*/
	private java.lang.String status;
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
	 *@return: java.lang.String  意见
	 */
	@Column(name ="OPINION",nullable=true)
	public java.lang.String getOpinion(){
		return this.opinion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  意见
	 */
	public void setOpinion(java.lang.String opinion){
		this.opinion = opinion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0申报审核1结题审核
	 */
	@Column(name ="TYPE",nullable=true,length=10)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0申报审核1结题审核
	 */
	public void setType(java.lang.String type){
		this.type = type;
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
	
	@Column(name ="status",nullable=true)
	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
}

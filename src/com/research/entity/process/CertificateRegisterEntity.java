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
 * @Description: 证书登记
 * @author onlineGenerator
 * @date 2016-08-14 16:54:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_certificate_register", schema = "")
@SuppressWarnings("serial")
public class CertificateRegisterEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String name;
	/**等级*/
	private java.lang.String grade;
	/**类型*/
	private java.lang.String type;
	/**获奖名称*/
	private java.lang.String awardName;
	/**获奖时间*/
	private java.util.Date awardTime;
	/**颁发单位*/
	private java.lang.String issuingUnit;
	/**创建人*/
	private java.lang.String createUser;
	/**创建时间*/
	private java.util.Date createTime;
	/**创建单位*/
	private java.lang.String createOrg;
	/**审核状态0：草稿，1：提交审核，2：审核通过，3：审核未通过*/
	private java.lang.String stauts;
	/**1：集体；2：教师；3：学生*/
	private java.lang.String funType;
	/**证书照片路径*/
	private java.lang.String photoPath;
	/**证书照片路径*/
	private java.lang.String photoName;
	/**奖品名称*/
	private java.lang.String prizeName;
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  等级
	 */
	@Column(name ="GRADE",nullable=true)
	public java.lang.String getGrade(){
		return this.grade;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  等级
	 */
	public void setGrade(java.lang.String grade){
		this.grade = grade;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="TYPE",nullable=true)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  获奖名称
	 */
	@Column(name ="AWARD_NAME",nullable=true)
	public java.lang.String getAwardName(){
		return this.awardName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  获奖名称
	 */
	public void setAwardName(java.lang.String awardName){
		this.awardName = awardName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  获奖时间
	 */
	@Column(name ="AWARD_TIME",nullable=true)
	public java.util.Date getAwardTime(){
		return this.awardTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  获奖时间
	 */
	public void setAwardTime(java.util.Date awardTime){
		this.awardTime = awardTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  颁发单位
	 */
	@Column(name ="ISSUING_UNIT",nullable=true)
	public java.lang.String getIssuingUnit(){
		return this.issuingUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  颁发单位
	 */
	public void setIssuingUnit(java.lang.String issuingUnit){
		this.issuingUnit = issuingUnit;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建人
	 */
	@Column(name ="CREATE_USER",nullable=true)
	public java.lang.String getCreateUser(){
		return this.createUser;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建人
	 */
	public void setCreateUser(java.lang.String createUser){
		this.createUser = createUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建单位
	 */
	@Column(name ="CREATE_ORG",nullable=true)
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
	@Column(name ="status",nullable=true)
	public java.lang.String getStauts() {
		return stauts;
	}

	public void setStauts(java.lang.String stauts) {
		this.stauts = stauts;
	}
	@Column(name ="fun_type",nullable=true)
	public java.lang.String getFunType() {
		return funType;
	}

	public void setFunType(java.lang.String funType) {
		this.funType = funType;
	}
	

	@Column(name ="prize_name",nullable=true)
	public java.lang.String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(java.lang.String prizeName) {
		this.prizeName = prizeName;
	}
	
	@Column(name ="photo_path",nullable=true)
	public java.lang.String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(java.lang.String photoPath) {
		this.photoPath = photoPath;
	}
	@Column(name ="photo_name",nullable=true)
	public java.lang.String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(java.lang.String photoName) {
		this.photoName = photoName;
	}


	
}

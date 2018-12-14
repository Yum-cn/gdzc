package com.research.entity.apply;

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
 * @Description: 校本课题申报表
 * @author onlineGenerator
 * @date 2016-07-24 21:33:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_apply_topic", schema = "")
@SuppressWarnings("serial")
public class ApplyTopicEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**课题名称*/
	private java.lang.String subjectName;
	/**学科分类*/
	private java.lang.String subjectType;
	/**课题负责人*/
	private java.lang.String subjectLeading;
	/**课题组成员*/
	private java.lang.String groupMember;
	/**课题组成员名称*/
	private java.lang.String groupMemberName;
	/**预期成果*/
	private java.lang.String expectedResults;
	/**预计完成时间*/
	private java.util.Date completionTime;
	/**联系电话*/
	private java.lang.String contactPhone;
	/**申报文件*/
	private java.lang.String applyFileDoc;
	/**申报文件名称*/
	private java.lang.String applyFileDocName;
	/**问题描述*/
	private java.lang.String significance;
	/**研究领域*/
	private java.lang.String research;
	/**研究领域小类*/
	private java.lang.String smallResearch;
	/**审核状态0草稿，1学校审核，2学校退回，3研究阶段,4结题审核中，5通过结题，6未通过结题*/
	private java.lang.String status;
	/**创建人*/
	private java.lang.String createUser;
	/**创建单位*/
	private java.lang.String createOrg;
	/**创建时间*/
	private java.util.Date createTime;
	
	/**成果主件地址*/
	private java.lang.String fruitMainFileDoc;
	/**成果主件名称*/
	private java.lang.String fruitMainFileDocName; 
	
	
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
	 *@return: java.lang.String  课题名称
	 */
	@Column(name ="SUBJECT_NAME",nullable=true,length=500)
	public java.lang.String getSubjectName(){
		return this.subjectName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  课题名称
	 */
	public void setSubjectName(java.lang.String subjectName){
		this.subjectName = subjectName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学科分类
	 */
	@Column(name ="SUBJECT_TYPE",nullable=true,length=100)
	public java.lang.String getSubjectType(){
		return this.subjectType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学科分类
	 */
	public void setSubjectType(java.lang.String subjectType){
		this.subjectType = subjectType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  课题负责人
	 */
	@Column(name ="SUBJECT_LEADING",nullable=true,length=100)
	public java.lang.String getSubjectLeading(){
		return this.subjectLeading;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  课题负责人
	 */
	public void setSubjectLeading(java.lang.String subjectLeading){
		this.subjectLeading = subjectLeading;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预期成果
	 */
	@Column(name ="EXPECTED_RESULTS",nullable=true,length=100)
	public java.lang.String getExpectedResults(){
		return this.expectedResults;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预期成果
	 */
	public void setExpectedResults(java.lang.String expectedResults){
		this.expectedResults = expectedResults;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预计完成时间
	 */
	@Column(name ="COMPLETION_TIME",nullable=true)
	public java.util.Date getCompletionTime(){
		return this.completionTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预计完成时间
	 */
	public void setCompletionTime(java.util.Date completionTime){
		this.completionTime = completionTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	@Column(name ="CONTACT_PHONE",nullable=true,length=20)
	public java.lang.String getContactPhone(){
		return this.contactPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setContactPhone(java.lang.String contactPhone){
		this.contactPhone = contactPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申报文件
	 */
	@Column(name ="APPLY_FILE_DOC",nullable=true,length=500)
	public java.lang.String getApplyFileDoc(){
		return this.applyFileDoc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申报文件
	 */
	public void setApplyFileDoc(java.lang.String applyFileDoc){
		this.applyFileDoc = applyFileDoc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核状态
	 */
	@Column(name ="STATUS",nullable=true,length=10)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
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
	@Column(name ="APPLY_FILE_DOC_NAME",nullable=true)
	public java.lang.String getApplyFileDocName() {
		return applyFileDocName;
	}

	public void setApplyFileDocName(java.lang.String applyFileDocName) {
		this.applyFileDocName = applyFileDocName;
	}
	@Column(name ="significance",nullable=true)
	public java.lang.String getSignificance() {
		return significance;
	}

	public void setSignificance(java.lang.String significance) {
		this.significance = significance;
	}
	@Column(name ="research",nullable=true)
	public java.lang.String getResearch() {
		return research;
	}

	public void setResearch(java.lang.String research) {
		this.research = research;
	}
	@Column(name ="fruit_main_file_doc",nullable=true)
	public java.lang.String getFruitMainFileDoc() {
		return fruitMainFileDoc;
	}

	public void setFruitMainFileDoc(java.lang.String fruitMainFileDoc) {
		this.fruitMainFileDoc = fruitMainFileDoc;
	}
	@Column(name ="fruit_main_file_doc_name",nullable=true)
	public java.lang.String getFruitMainFileDocName() {
		return fruitMainFileDocName;
	}

	public void setFruitMainFileDocName(java.lang.String fruitMainFileDocName) {
		this.fruitMainFileDocName = fruitMainFileDocName;
	}
	@Column(name ="small_research",nullable=true)
	public java.lang.String getSmallResearch() {
		return smallResearch;
	}

	public void setSmallResearch(java.lang.String smallResearch) {
		this.smallResearch = smallResearch;
	}
	@Column(name ="group_member",nullable=true)
	public java.lang.String getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(java.lang.String groupMember) {
		this.groupMember = groupMember;
	}
	@Column(name ="group_member_name",nullable=true)
	public java.lang.String getGroupMemberName() {
		return groupMemberName;
	}

	public void setGroupMemberName(java.lang.String groupMemberName) {
		this.groupMemberName = groupMemberName;
	}
	
}

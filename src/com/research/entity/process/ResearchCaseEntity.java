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
 * @Description: 案例管理
 * @author onlineGenerator
 * @date 2016-07-27 13:27:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_research_case", schema = "")
@SuppressWarnings("serial")
public class ResearchCaseEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**案例名称*/
	private java.lang.String title;
	/**摘要*/
	private java.lang.String abstractString;
	/**关键词*/
	private java.lang.String keyword;
	/**引用网址*/
	private java.lang.String referenceUrl;
	/**原作者*/
	private java.lang.String author;
	/**正文*/
	private java.lang.String content;
	private java.lang.String topId;
	/**创建人*/
	private java.lang.String createUser;
	/**创建单位*/
	private java.lang.String createOrg;
	/**创建时间*/
	private java.util.Date createTime;
	/**年级*/
	private java.lang.String grade;
	/**课程*/
	private java.lang.String course;
	/**册*/
	private java.lang.String book;
	/**章节*/
	private java.lang.String chapter;
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
	 *@return: java.lang.String  案例名称
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  案例名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  摘要
	 */
	@Column(name ="ABSTRACT",nullable=true,length=1000)
	public java.lang.String getAbstractString(){
		return this.abstractString;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  摘要
	 */
	public void setAbstractString(java.lang.String abstractString){
		this.abstractString = abstractString;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关键词
	 */
	@Column(name ="KEYWORD",nullable=true,length=255)
	public java.lang.String getKeyword(){
		return this.keyword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关键词
	 */
	public void setKeyword(java.lang.String keyword){
		this.keyword = keyword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  引用网址
	 */
	@Column(name ="REFERENCE_URL",nullable=true,length=255)
	public java.lang.String getReferenceUrl(){
		return this.referenceUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  引用网址
	 */
	public void setReferenceUrl(java.lang.String referenceUrl){
		this.referenceUrl = referenceUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原作者
	 */
	@Column(name ="AUTHOR",nullable=true,length=50)
	public java.lang.String getAuthor(){
		return this.author;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原作者
	 */
	public void setAuthor(java.lang.String author){
		this.author = author;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  正文
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  正文
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
	@Column(name ="CREATE_TIME",nullable=true,length=50)
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

	@Column(name ="top_id",nullable=true,length=50)
	public java.lang.String getTopId() {
		return topId;
	}

	public void setTopId(java.lang.String topId) {
		this.topId = topId;
	}
	@Column(name ="grade",nullable=true,length=50)
	public java.lang.String getGrade() {
		return grade;
	}

	public void setGrade(java.lang.String grade) {
		this.grade = grade;
	}
	@Column(name ="course",nullable=true,length=255)
	public java.lang.String getCourse() {
		return course;
	}

	public void setCourse(java.lang.String course) {
		this.course = course;
	}
	@Column(name ="book",nullable=true,length=255)
	public java.lang.String getBook() {
		return book;
	}

	public void setBook(java.lang.String book) {
		this.book = book;
	}
	@Column(name ="chapter",nullable=true,length=255)
	public java.lang.String getChapter() {
		return chapter;
	}

	public void setChapter(java.lang.String chapter) {
		this.chapter = chapter;
	}
	
}

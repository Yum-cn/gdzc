package com.survey.entity.questionqptionanswer;

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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 问卷答题记录表
 * @author onlineGenerator
 * @date 2015-09-18 19:33:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "survey_question_option_answer", schema = "")
@SuppressWarnings("serial")
public class QuestionOptionAnswerEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**问卷ID*/
	private java.lang.String questionnaireId;
	/**试题ID*/
	
	private java.lang.String questionId;
	/**选项ID*/
	
	private java.lang.String optionId;
	/**排序题的值*/
	private java.lang.String sortnum;
	/**答题时间*/
	private java.util.Date createTime;
	/**
	 * 简答题  填空题全文
	 */
	private java.lang.String fullText;
	

	private java.lang.String style;

	private java.lang.String questionIdName;

	private java.lang.String optionIdName;
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
	 *@return: java.lang.String  问卷ID
	 */
	@Column(name ="QUESTIONNAIRE_ID",nullable=true,length=50)
	public java.lang.String getQuestionnaireId(){
		return this.questionnaireId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  问卷ID
	 */
	public void setQuestionnaireId(java.lang.String questionnaireId){
		this.questionnaireId = questionnaireId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试题ID
	 */
	@Column(name ="QUESTION_ID",nullable=true,length=50)
	public java.lang.String getQuestionId(){
		return this.questionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试题ID
	 */
	public void setQuestionId(java.lang.String questionId){
		this.questionId = questionId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  选项ID
	 */
	@Column(name ="OPTION_ID",nullable=true,length=50)
	public java.lang.String getOptionId(){
		return this.optionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  选项ID
	 */
	public void setOptionId(java.lang.String optionId){
		this.optionId = optionId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  排序题的值
	 */
	@Column(name ="SORTNUM",nullable=true,length=20)
	public java.lang.String getSortnum(){
		return this.sortnum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  排序题的值
	 */
	public void setSortnum(java.lang.String sortnum){
		this.sortnum = sortnum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  答题时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  答题时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	@Column(name ="FULL_TEXT",nullable=true)
	public java.lang.String getFullText() {
		return fullText;
	}

	public void setFullText(java.lang.String fullText) {
		this.fullText = fullText;
	}
	@Transient
	public java.lang.String getQuestionIdName() {
		return questionIdName;
	}

	public void setQuestionIdName(java.lang.String questionIdName) {
		this.questionIdName = questionIdName;
	}
	
	@Transient
	public java.lang.String getOptionIdName() {
		return optionIdName;
	}

	public void setOptionIdName(java.lang.String optionIdName) {
		this.optionIdName = optionIdName;
	}
	@Transient
	public java.lang.String getStyle() {
		return style;
	}

	public void setStyle(java.lang.String style) {
		this.style = style;
	}
	
}

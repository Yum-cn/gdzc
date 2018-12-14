package com.survey.entity.questionnairequestion;

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
 * @Description: 问卷与题库关联表
 * @author onlineGenerator
 * @date 2015-09-14 19:20:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "survey_questionnaire_question", schema = "")
@SuppressWarnings("serial")
public class QuestionnaireQuestionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**问卷ID*/
	private java.lang.String questionnaireId;
	/**题库ID*/
	private java.lang.String questionId;
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
	 *@return: java.lang.String  题库ID
	 */
	@Column(name ="QUESTION_ID",nullable=true,length=50)
	public java.lang.String getQuestionId(){
		return this.questionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  题库ID
	 */
	public void setQuestionId(java.lang.String questionId){
		this.questionId = questionId;
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

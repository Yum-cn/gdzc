package com.survey.entity.questionoption;

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
 * @Description: 题库选项
 * @author onlineGenerator
 * @date 2015-09-13 22:15:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "survey_question_option", schema = "")
@SuppressWarnings("serial")
public class QuestionOptionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**题库ID*/
	private java.lang.String questionId;
	/**题目选项*/
	private java.lang.String optionName;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  题目选项
	 */
	@Column(name ="OPTION_NAME",nullable=true,length=500)
	public java.lang.String getOptionName(){
		return this.optionName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  题目选项
	 */
	public void setOptionName(java.lang.String optionName){
		this.optionName = optionName;
	}
}

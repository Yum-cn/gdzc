package com.assets.indicator.entity;

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
 * @Description: 指标
 * @author onlineGenerator
 * @date 2015-04-01 10:01:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "assets_indicator", schema = "")
@SuppressWarnings("serial")
public class IndicatorEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评价项目*/
	private java.lang.String pjdl;
	/**指标名称*/
	private java.lang.String name;
	/**指标描述*/
	private java.lang.String discription;
	private java.lang.String parentId;
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
	 *@return: java.lang.String  指标名称
	 */
	@Column(name ="NAME",nullable=false,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  指标名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  指标描述
	 */
	@Column(name ="DISCRIPTION",nullable=true,length=500)
	public java.lang.String getDiscription(){
		return this.discription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  指标描述
	 */
	public void setDiscription(java.lang.String discription){
		this.discription = discription;
	}

	public java.lang.String getPjdl() {
		return pjdl;
	}

	public void setPjdl(java.lang.String pjdl) {
		this.pjdl = pjdl;
	}
	@Column(name ="parent_id",nullable=true,length=50)
	public java.lang.String getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}
	
}

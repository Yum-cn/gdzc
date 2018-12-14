package com.sc.substitutemanager.entity;

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
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity  
 * @Description: 代课分配管理
 * @author onlineGenerator
 * @date 2017-01-09 15:38:46
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class TotalSubstituteManagerEntity implements java.io.Serializable {
	
	/**请假人*/
	@Excel(name="代课人",width=50)
	private java.lang.String leaveMan;
	/**请假人ID*/
	@Excel(name="代课总节数",width=50)
	private java.lang.String total;
	public java.lang.String getLeaveMan() {
		return leaveMan;
	}
	public void setLeaveMan(java.lang.String leaveMan) {
		this.leaveMan = leaveMan;
	}
	public java.lang.String getTotal() {
		return total;
	}
	public void setTotal(java.lang.String total) {
		this.total = total;
	}

	
}

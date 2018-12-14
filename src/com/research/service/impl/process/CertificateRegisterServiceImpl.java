package com.research.service.impl.process;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.research.entity.process.CertificateRegisterEntity;
import com.research.service.process.CertificateRegisterServiceI;

import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import java.util.UUID;
import java.io.Serializable;

@Service("certificateRegisterService")
@Transactional
public class CertificateRegisterServiceImpl extends CommonServiceImpl implements CertificateRegisterServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CertificateRegisterEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CertificateRegisterEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CertificateRegisterEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CertificateRegisterEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CertificateRegisterEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CertificateRegisterEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CertificateRegisterEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{grade}",String.valueOf(t.getGrade()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{award_name}",String.valueOf(t.getAwardName()));
 		sql  = sql.replace("#{award_time}",String.valueOf(t.getAwardTime()));
 		sql  = sql.replace("#{issuing_unit}",String.valueOf(t.getIssuingUnit()));
 		sql  = sql.replace("#{create_user}",String.valueOf(t.getCreateUser()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{create_org}",String.valueOf(t.getCreateOrg()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}
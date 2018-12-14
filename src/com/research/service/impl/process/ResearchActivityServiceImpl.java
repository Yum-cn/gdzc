package com.research.service.impl.process;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.research.entity.process.ResearchActivityEntity;
import com.research.service.process.ResearchActivityServiceI;

import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import java.util.UUID;
import java.io.Serializable;

@Service("researchActivityService")
@Transactional
public class ResearchActivityServiceImpl extends CommonServiceImpl implements ResearchActivityServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ResearchActivityEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((ResearchActivityEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((ResearchActivityEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ResearchActivityEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ResearchActivityEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ResearchActivityEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ResearchActivityEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{activities_date}",String.valueOf(t.getActivitiesDate()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{participant}",String.valueOf(t.getParticipant()));
 		sql  = sql.replace("#{advantage}",String.valueOf(t.getAdvantage()));
 		sql  = sql.replace("#{problem}",String.valueOf(t.getProblem()));
 		sql  = sql.replace("#{reflect}",String.valueOf(t.getReflect()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{create_user}",String.valueOf(t.getCreateUser()));
 		sql  = sql.replace("#{create_org}",String.valueOf(t.getCreateOrg()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}
package com.survey.service.impl.questionnairequestion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.survey.entity.questionnairequestion.QuestionnaireQuestionEntity;
import com.survey.service.questionnairequestion.QuestionnaireQuestionServiceI;

import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import java.util.UUID;
import java.io.Serializable;

@Service("questionnaireQuestionService")
@Transactional
public class QuestionnaireQuestionServiceImpl extends CommonServiceImpl implements QuestionnaireQuestionServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((QuestionnaireQuestionEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((QuestionnaireQuestionEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((QuestionnaireQuestionEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(QuestionnaireQuestionEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(QuestionnaireQuestionEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(QuestionnaireQuestionEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,QuestionnaireQuestionEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{questionnaire_id}",String.valueOf(t.getQuestionnaireId()));
 		sql  = sql.replace("#{question_id}",String.valueOf(t.getQuestionId()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}
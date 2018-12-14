package com.survey.service.impl.questionqptionanswer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.survey.entity.questionqptionanswer.QuestionOptionAnswerEntity;
import com.survey.service.questionqptionanswer.QuestionOptionAnswerServiceI;

import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import java.util.UUID;
import java.io.Serializable;

@Service("questionOptionAnswerService")
@Transactional
public class QuestionOptionAnswerServiceImpl extends CommonServiceImpl implements QuestionOptionAnswerServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((QuestionOptionAnswerEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((QuestionOptionAnswerEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((QuestionOptionAnswerEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(QuestionOptionAnswerEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(QuestionOptionAnswerEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(QuestionOptionAnswerEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,QuestionOptionAnswerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{questionnaire_id}",String.valueOf(t.getQuestionnaireId()));
 		sql  = sql.replace("#{question_id}",String.valueOf(t.getQuestionId()));
 		sql  = sql.replace("#{option_id}",String.valueOf(t.getOptionId()));
 		sql  = sql.replace("#{sortnum}",String.valueOf(t.getSortnum()));
 		sql  = sql.replace("#{create_time}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}
package com.survey.service.questionqptionanswer;

import org.qihuasoft.core.common.service.CommonService;

import com.survey.entity.questionqptionanswer.QuestionOptionAnswerEntity;

import java.io.Serializable;

public interface QuestionOptionAnswerServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(QuestionOptionAnswerEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(QuestionOptionAnswerEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(QuestionOptionAnswerEntity t);
}

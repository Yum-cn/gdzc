package com.sc.substitutemanager.service.impl;
import com.sc.substitutemanager.service.ScSubstituteManagerServiceI;
import org.qihuasoft.core.common.service.impl.CommonServiceImpl;
import com.sc.substitutemanager.entity.ScSubstituteManagerEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.qihuasoft.core.util.ApplicationContextUtil;
import org.qihuasoft.core.util.MyClassLoader;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("scSubstituteManagerService")
@Transactional
public class ScSubstituteManagerServiceImpl extends CommonServiceImpl implements ScSubstituteManagerServiceI {

	
 	public void delete(ScSubstituteManagerEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ScSubstituteManagerEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ScSubstituteManagerEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ScSubstituteManagerEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ScSubstituteManagerEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ScSubstituteManagerEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ScSubstituteManagerEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("leave_man", t.getLeaveMan());
		map.put("leave_userid", t.getLeaveUserid());
		map.put("substitute_man", t.getSubstituteMan());
		map.put("substitute_userid", t.getSubstituteUserid());
		map.put("substitute_oepnid", t.getSubstituteOepnid());
		map.put("substitute_class", t.getSubstituteClass());
		map.put("substitute_section", t.getSubstituteSection());
		map.put("remarks", t.getRemarks());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ScSubstituteManagerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{leave_man}",String.valueOf(t.getLeaveMan()));
 		sql  = sql.replace("#{leave_userid}",String.valueOf(t.getLeaveUserid()));
 		sql  = sql.replace("#{substitute_man}",String.valueOf(t.getSubstituteMan()));
 		sql  = sql.replace("#{substitute_userid}",String.valueOf(t.getSubstituteUserid()));
 		sql  = sql.replace("#{substitute_oepnid}",String.valueOf(t.getSubstituteOepnid()));
 		sql  = sql.replace("#{substitute_class}",String.valueOf(t.getSubstituteClass()));
 		sql  = sql.replace("#{substitute_section}",String.valueOf(t.getSubstituteSection()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}
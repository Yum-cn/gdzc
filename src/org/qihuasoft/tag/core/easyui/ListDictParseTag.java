package org.qihuasoft.tag.core.easyui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.qihuasoft.core.util.ApplicationContextUtil;
import org.qihuasoft.core.util.MutiLangUtil;
import org.qihuasoft.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @Title:AuthFilterTag
 * @description:列表字典解析回显
 * @author Rocky
 * @date Aug 24, 2013 7:46:57 PM
 * @version V1.0
 */
public class ListDictParseTag extends TagSupport{
	/**
	 * 
	 */
	protected static HashMap<String,String> dicCodeMap = new HashMap<String,String>();
	protected static HashMap<String,String> tableMap = new HashMap<String,String>();
	private static final long serialVersionUID = 1L;
	protected String parseId;
	protected String table;
	protected String id;
	protected String name;
	protected String style;
	protected String typecode;
	
	@Autowired
	private static SystemService systemService;
	
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		JspWriter out = null;
		try {
				out = this.pageContext.getOut();
				out.print(end().toString());
				out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
		
	}
	protected Object end() {
		StringBuilder out = new StringBuilder();
		getDictParse(out);
		return out;
	}
	/**
	 * 获取隐藏按钮的JS代码
	 * @param out
	 */
	@SuppressWarnings("unchecked")
	protected void getDictParse(StringBuilder out) {
		systemService = ApplicationContextUtil.getContext().getBean(
				SystemService.class);
		if("1".equals(style)){
			if(dicCodeMap.get(parseId)==null){//内存空，去数据库取
				String sql = "select typecode,typename from `t_s_type` where typegroupid in(select id from `t_s_typegroup` where typegroupcode='"+typecode+"')";
				List list = systemService.findListbySql(sql);
				if(list!=null && list.size()>0){
					
					for(int i=0;i<list.size();i++){
						Object[] obj = (Object[])list.get(i);
						dicCodeMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
						if(parseId.equals(obj[0]))
							out.append(MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(obj[1])));
					}
				}else{
					out.append(parseId);
				}
			}else{//取内存
				String value = String.valueOf(dicCodeMap.get(parseId));
				if(value!=null && !"".equals(value)){
					out.append(value);
				}else{
					out.append(parseId);
				}
			}
		}else{
			if(tableMap.get(parseId)==null){//内存空，去数据库取
				String sql = "select "+id+","+name+" from "+table+" where "+id+"='"+parseId+"'";
				List list = systemService.findListbySql(sql);
				if(list!=null && list.size()>0){
					Object[] obj = (Object[])list.get(0);
					tableMap.put(String.valueOf(obj[0]), MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(obj[1])));
					out.append(MutiLangUtil.getMutiLangInstance().getLang(String.valueOf(obj[1])));
				}else{
					out.append(parseId);
				}
			}else{//取内存
				//HashMap map = tableMap.get(parseId);
				String value = tableMap.get(parseId);
				if(value!=null && !"".equals(value)){
					out.append(value);
				}else{
					out.append(parseId);
				}
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getParseId() {
		return parseId;
	}

	public void setParseId(String parseId) {
		this.parseId = parseId;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}


	
}

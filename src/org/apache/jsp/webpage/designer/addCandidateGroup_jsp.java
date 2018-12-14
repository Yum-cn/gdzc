package org.apache.jsp.webpage.designer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ExpressionFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import org.apache.AnnotationProcessor;
import org.apache.jasper.runtime.HttpJspBase;
import org.apache.jasper.runtime.JspSourceDependent;
import org.apache.jasper.runtime.PageContextImpl;

public final class addCandidateGroup_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();
  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private ExpressionFactory _el_expressionfactory;
  private AnnotationProcessor _jsp_annotationprocessor;

  public Map<String, Long> getDependants()
  {
    return _jspx_dependants;
  }

  public void _jspInit() {
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy()
  {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;
    try
    {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);

      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n<script type=\"text/javascript\">\r\n<!--\r\n$(function(){\r\n\t_task_candidate_unselected_group_panel_obj=$('#_task_candidate_unselected_group_panel').panel({\r\n\t\t//height:600,\r\n\t\tborder:false,\r\n\t\tnoheader:true,\r\n\t\ttop:0,\r\n\t\tleft:0\r\n\t});\r\n\t_task_candidate_unselected_group_grid=$('#_task_candidate_unselected_group_table').datagrid({\r\n\t\ttitle:\"用户组\",\r\n\t\turl:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!searchCandidateGroup.action',\r\n\t\t//singleSelect:true,\r\n\t\tidField:'groupId',\r\n\t\theight:400,\r\n\t\tpagination:true,\r\n\t\tpageSize:15,\r\n\t\tpageNumber:1,\r\n\t\tpageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\tsortName:'name',\r\n\t    sortOrder:'asc',\r\n\t    striped:true,\r\n\t    onLoadSuccess:function(data){\r\n\t\t   \tvar rows = data.rows;\r\n\t\t    for(var i=0;i<rows.length;i++){\r\n\t\t\t    if(task.getCandidateGroup(rows[i].name)!=null){\r\n\t\t\t\t\t$(this).datagrid('selectRow',i);\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t},\r\n\t    toolbar:[{\r\n\t        text:\"保存\",\r\n\t        iconCls:'icon-save',\r\n\t        handler:function(){\r\n\t        \taddCandidateGroups();\r\n\t        }\r\n\t    }]\r\n\t});\r\n});\r\nfunction searchTaskCandidateUnselectedGroup(){\r\n\tvar name=document.getElementById(\"filter_LIKES_name\").value;\r\n\t_task_candidate_unselected_group_grid.datagrid('reload',{\r\n\t\tfilter_LIKES_name:name\r\n\t\t});\r\n}\r\nfunction addCandidateGroups(){\r\n\tvar rows = _task_candidate_unselected_group_grid.datagrid(\"getSelections\");\r\n\tfor(var i=0;i<rows.length;i++){\r\n\t\tvar group = rows[i];\r\n");
      out.write("\t\ttask.addCandidateGroup(group.name);\r\n\t}\r\n\tloadTaskCandidateGroups();\r\n\t_task_candidate_win.window('close');\r\n}\r\n//-->\r\n</script>\r\n<div id=\"_task_candidate_unselected_group_panel\" style=\"padding:5px;\">\r\n<div>\r\n\t<table border=\"0\">\r\n\t\t<tr>\r\n\t\t\t<td>名称:</td>\r\n\t\t\t<td><input type=\"text\" name=\"filter_LIKES_name\" value=\"\" size=\"9\"/></td>\r\n\t\t\t<td><a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"searchTaskCandidateUnselectedGroup();\">查询</a></td>\r\n\t\t</tr>\r\n\t</table>\r\n</div>\r\n<div>\r\n<table id=\"_task_candidate_unselected_group_table\">\r\n\t<thead>\r\n\t\t<tr>\r\n\t\t\t<th field=\"groupId\" align=\"middle\" checkbox=\"true\"></th>\r\n\t\t\t<th field=\"name\" width=\"100\" align=\"middle\" sortable=\"true\">名称</th>\r\n\t\t\t<th field=\"remark\" width=\"300\" align=\"middle\">备注</th>\r\n\t\t</tr>\r\n\t</thead>\r\n</table>\r\n</div>\r\n</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)) {
        out = _jspx_out;
        if ((out != null) && (out.getBufferSize() != 0)) try {
            out.clearBuffer(); } catch (IOException e) {
          } if (_jspx_page_context != null) _jspx_page_context.handlePageException(t); 
      }
    }
    finally { 
    	_jspxFactory.releasePageContext(_jspx_page_context);
    }

  }
}
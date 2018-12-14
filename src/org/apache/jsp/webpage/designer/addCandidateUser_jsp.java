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

public final class addCandidateUser_jsp extends HttpJspBase
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

      out.write("\r\n<script type=\"text/javascript\">\r\n<!--\r\n$(function(){\r\n\t/*\r\n\t$('#candidate-users-tree-list').treegrid({\r\n\t\t\ttitle:\"\",\r\n\t\t\t//width:980,\r\n\t\t\theight:450,\r\n\t\t\t//iconCls:'icon-save',\r\n\t\t\turl : '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/pm/group/group!getUserTreeOfGroup.action',\r\n\t\t\tanimate : true,\r\n\t\t\tnowrap : false,\r\n\t\t\trownumbers : true,\r\n\t\t\tcollapsible : true,\r\n\t\t\tidField : 'id',\r\n\t\t\ttreeField : 'id',\r\n\t\t\tfrozenColumns:[[\r\n\t\t\t                {field:'id',width:200,align:'center'},\r\n\t\t\t                {field:'text',width:200,align:'center'}\r\n\t\t\t]],\r\n\t\t\tcolumns:[[\r\n\t\t\t          {field:'name',title:\"name\",width:200,align:'center'}\r\n\t\t\t]],\r\n\t\t\ttoolbar:[\r\n\t\t\t         {\r\n\t\t\t        \ttext:\"<s:text name='button.common.save'></s:text>\",\r\n\t\t\t \t        iconCls:'icon-add',\r\n\t\t\t \t        handler:function(){\r\n\t\t\t \t    \t\t//return false;\r\n\t\t\t \t        }\r\n\t\t\t\t     }\t\r\n\t\t\t]\t\r\n\t\t});\r\n\t\r\n\t$('#candidate-users-tree').tree({\r\n\t\tcheckbox: true,\r\n\t\turl: '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/pm/group/group!getUserTreeOfGroup.action',\r\n\t\tonClick:function(node){\r\n\t\t\t$(this).tree('toggle', node.target);\r\n\t\t\t//alert('you dbclick '+node.text);\r\n\t\t}\r\n\t});\r\n\t*/\r\n\t_task_unselected_candidate_user_panel=$('#task-unselect-candidate-user-panel').panel({\r\n\t\tborder:false,\r\n\t\tnoheader:true,\r\n\t\ttop:0,\r\n\t\tleft:0\r\n\t\t//fit:true\r\n\t});\r\n\t_task_unselect_candidate_user_list=$('#task-unselect-candidate-user-list').datagrid({\r\n\t\ttitle:\"Users\",\r\n\t\turl:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!searchCandidateUser.action',\r\n\t\t//singleSelect:true,\r\n\t\t//width:900,\r\n\t\theight:400,\r\n\t\tidField:'userId',\r\n\t\tpagination:true,\r\n\t\tpageSize:15,\r\n\t\tpageNumber:1,\r\n\t\tpageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\tsortName:'userId',\r\n\t    sortOrder:'asc',\r\n\t    striped:true,\r\n\t    onLoadSuccess:function(data){\r\n\t\t   \tvar rows = data.rows;\r\n\t\t    for(var i=0;i<rows.length;i++){\r\n\t\t\t    if(task.getCandidateUser(rows[i].sso)!=null){\r\n\t\t\t\t\t$(this).datagrid('selectRow',i);\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t},\r\n\t    toolbar:[{\r\n        \ttext:\"保存\",\r\n \t        iconCls:'icon-save',\r\n \t        handler:function(){\r\n        \t\taddCandidateUsers();\r\n \t        }\r\n\t     }]\r\n\t});\r\n\t_task_unselect_candidate_user_group=$('#task-unselect-candidate-user-group').combobox({\r\n\t    url:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/pm/group/group!getAllGroups.action',\r\n\t    //editable:false,\r\n\t    valueField:'groupId',\r\n\t    textField:'name'\r\n\t});\r\n});\r\nfunction searchCandidateUser(){\r\n\tvar name=$(\"#task-unselect-candidate-user-userName\").val();\r\n\tvar sso=$(\"#task-unselect-candidate-user-sso\").val();\r\n\tvar group=_task_unselect_candidate_user_group.combobox('getValue');\r\n\t//alert(group);\r\n\tvar email=$(\"#task-unselect-candidate-user-email\").val();\r\n\t//alert(sso);\r\n\t_task_unselect_candidate_user_list.datagrid('reload',{\r\n\t\t\tfilter_LIKES_name:name,\r\n\t\t\tfilter_EQS_sso:sso,\r\n\t\t\tgroup_EQI_groupId:group,\r\n\t\t\tfilter_LIKES_email:email\r\n\t\t});\r\n}\r\nfunction addCandidateUsers(){\r\n\tvar rows = _task_unselect_candidate_user_list.datagrid(\"getSelections\");\r\n\tfor(var i=0;i<rows.length;i++){\r\n\t\tvar user = rows[i];\r\n\t\ttask.addCandidateUser({\r\n\t\t\t\tuserId:user.userId,\r\n\t\t\t\tsso:user.sso,\r\n\t\t\t\tname:user.name,\r\n\t\t\t\ttitle:user.title,\r\n\t\t\t\temail:user.email\r\n\t\t\t});\r\n\t}\r\n\tloadTaskCandidateUsers();\r\n\t_task_candidate_win.window('close');\r\n}\r\n//-->\r\n</script>\r\n<!--<table id=\"candidate-users-tree-list\">-->\r\n");
      out.write("<!--</table>-->\r\n<!--<ul id=\"candidate-users-tree\"></ul>-->\r\n<div id=\"task-unselect-candidate-user-panel\" style=\"padding:5px;\">\r\n<table border=\"0\">\r\n\t<tr>\r\n\t\t<td>Group:</td>\r\n\t\t<td><input type=\"text\" id=\"task-unselect-candidate-user-group\" name=\"task-unselect-candidate-user-group\" value=\"\" readonly=\"readonly\"/></td>\r\n\t\t<td>SSO:</td>\r\n\t\t<td><input type=\"text\" id=\"task-unselect-candidate-user-sso\" name=\"task-unselect-candidate-user-sso\" value=\"\" size=\"9\"/></td>\r\n\t\t<td>Name:</td>\r\n\t\t<td><input type=\"text\" id=\"task-unselect-candidate-user-userName\" name=\"task-unselect-candidate-user-userName\" value=\"\" size=\"9\"/></td>\r\n\t\t<td>Email:</td>\r\n\t\t<td><input type=\"text\" id=\"task-unselect-candidate-user-email\" name=\"task-unselect-candidate-user-email\" value=\"\" size=\"9\"/></td>\r\n\t\t<td><a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"searchCandidateUser();\">Search</a></td>\r\n\t</tr>\r\n</table>\r\n       <table id=\"task-unselect-candidate-user-list\">\r\n\t\t\t<thead>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<th field=\"userId\" align=\"middle\" checkbox=\"true\"></th>\r\n");
      out.write("\t\t\t\t\t<th field=\"sso\"  align=\"middle\" sortable=\"true\">SSO</th>\r\n\t\t\t\t\t<th field=\"name\"  align=\"middle\" sortable=\"true\">姓名</th>\r\n\t\t\t\t\t<th field=\"title\"  align=\"middle\" sortable=\"true\">Title</th>\r\n\t\t\t\t\t<th field=\"email\"  align=\"middle\">Email</th>\r\n\t\t\t\t\t<th field=\"userStatus\"  align=\"middle\">状态</th>\r\n\t\t\t\t</tr>\r\n\t\t\t</thead>\r\n\t\t</table>\r\n</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)) {
        out = _jspx_out;
        if ((out != null) && (out.getBufferSize() != 0)) try {
            out.clearBuffer(); } catch (IOException e) {
          } if (_jspx_page_context != null) _jspx_page_context.handlePageException(t); 
      }
    }
    finally { _jspxFactory.releasePageContext(_jspx_page_context); }

  }
}
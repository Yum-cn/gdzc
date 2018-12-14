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

public final class mailTaskProperties_jsp extends HttpJspBase
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

      out.write("\r\n<script type=\"text/javascript\">\r\n<!--\r\nvar tid = '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("';\r\nvar task = workflow.getFigure(tid);\r\n$(function(){\r\n\t$('#performerType').combobox({\r\n\t\t\teditable:false,\r\n\t\t\tonChange:function(newValue, oldValue){\r\n\t\t\t\tswitchTaskCandidatesList(newValue);\r\n\t\t\t}\r\n\t\t});\r\n\t_listener_win = $('#listener-win').window({\r\n\t\t//href:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!forTaskListenerConfig.action',   \r\n\t    closed:true,\r\n\t    //cache:false,\r\n\t    draggable:false,\r\n\t    collapsible:false,\r\n\t    minimizable:false,\r\n\t    maximizable:false,\r\n\t    modal:true,\r\n\t    shadow:true\r\n\t});\r\n\t$('#task-listeners-list').datagrid({\r\n\t\t//title:\"Listener\",\r\n\t\t//url:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!search.action',//加载表格数据的URL\r\n\t\tsingleSelect:true,\r\n\t\t//width:500,\r\n\t\theight:300,\r\n\t\t//idField:'id',\r\n\t\t//pagination:true,\r\n\t\t//pageSize:15,\r\n\t\t//pageNumber:1,\r\n\t\t//pageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\t//sortName:'id',\r\n\t    //sortOrder:'asc',\r\n\t    striped:true,\r\n\t    toolbar:[{\r\n\t        text:'New',\r\n\t        iconCls:'icon-add',\r\n\t        handler:function(){\r\n\t    \t\t_listener_win.window('open');\r\n\t    \t\t//_listener_frame.src=\"\";\r\n\t    \t\t_listener_win.window('refresh','activitiController.do?processProperties&turn=taskListenerConfig');\r\n\t    \t\t//alert(_listener_frame.document.body.innerHTML);\r\n\t        }\r\n\t    }]\r\n\t});\r\n\t$('#task-form-properties-list').datagrid({\r\n\t\t//title:\"Listener\",\r\n\t\t//url:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!search.action',//加载表格数据的URL\r\n\t\tsingleSelect:true,\r\n\t\t//width:500,\r\n\t\theight:300,\r\n\t\t//idField:'id',\r\n\t\t//pagination:true,\r\n\t\t//pageSize:15,\r\n\t\t//pageNumber:1,\r\n\t\t//pageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\t//sortName:'id',\r\n\t    //sortOrder:'asc',\r\n\t    striped:true,\r\n\t    toolbar:[{\r\n\t        text:'New',\r\n\t        iconCls:'icon-add',\r\n\t        handler:function(){\r\n\t        }\r\n\t    }]\r\n\t});\r\n\ttask_candidate_panel=$('#task-candidate-panel').panel({\r\n\t\tborder:false,\r\n\t\t//minimized:true,\r\n\t\theight:450\r\n\t\t//width:\r\n\t\t//fit:true\r\n\t});\r\n\tpopulateTaskProperites();\r\n\t//switchTaskCandidatesList($('#performerType').combobox('getValue'));\r\n});\r\nfunction switchTaskCandidatesList(performerType){\r\n\tif(performerType == 'candidateUsers'){\r\n\t\ttask_candidate_panel.panel(\"refresh\",\"activitiController.do?processProperties&turn=candidateUsersConfig\");\r\n\t}else if(performerType == 'candidateGroups'){\r\n\t\ttask_candidate_panel.panel(\"refresh\",\"activitiController.do?processProperties&turn=candidateGroupsConfig\");\r\n\t}\r\n");
      out.write("}\r\nfunction listenerActionBt(value,rowData,rowIndex){\r\n\tvar id = rowData.id;\r\n\tvar e = '<img onclick=\"editListener(\\''+id+'\\')\" src=\"plug-in/designer/img/edit.gif\" title=\"'+\"修改\"+'\" style=\"cursor:hand;\"/>';   \r\n    var d = '<img onclick=\"deleteListener(\\''+id+'\\')\" src=\"plug-in/designer/img/delete.gif\" title=\"'+\"删除\"+'\" style=\"cursor:hand;\"/>';\r\n\treturn e+'&nbsp;'+d;\r\n}\r\nfunction editListener(id){\r\n\t_listener_win.window('open');\r\n\t_listener_win.window('refresh','taskListenerConfig.html');\r\n}\r\nfunction deleteListener(id){\r\n\ttask.deleteListener(id);\r\n\tloadTaskListeners();\r\n}\r\nfunction formActionBt(value,rowData,rowIndex){\r\n\tvar id = rowData.id;\r\n\tvar e = '<img onclick=\"editForm('+id+')\" src=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/image/edit.gif\" title=\"'+\"修改\"+'\" style=\"cursor:hand;\"/>';   \r\n    var d = '<img onclick=\"deleteForm('+id+')\" src=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/image/delete.gif\" title=\"'+\"删除\"+'\" style=\"cursor:hand;\"/>';\r\n\treturn e+'&nbsp;'+d;\r\n}\r\nfunction editForm(id){\r\n\t\r\n}\r\nfunction deleteForm(id){\r\n\t\r\n}\r\nfunction saveTaskProperties(){\r\n\t//alert(tid);\r\n\ttask.taskId=$('#id').val();\r\n\ttask.toEmail=$('#toEmail').val();\r\n\t//task.setContent($('#name').val());\r\n\ttask.fromEmail=$('#fromEmail').val();\r\n\ttask.subjectEmail=$('#subjectEmail').val();\r\n\ttask.ccEmail=$('#ccEmail').val();\r\n\ttask.bccEmail=$('#bccEmail').val();\r\n\ttask.charsetEmail=$('#charsetEmail').val();\r\n\ttask.htmlEmail=$('#htmlEmail').val();\r\n\ttask.textEmail=$('#textEmail').val();\r\n}\r\nfunction populateTaskProperites(){\r\n\t$('#id').val(task.taskId);\r\n\t$('#toEmail').val(task.toEmail);\r\n\t$('#fromEmail').val(task.fromEmail);\r\n\t$('#subjectEmail').val(task.subjectEmail);\r\n\t$('#ccEmail').val(task.ccEmail);\r\n\t$('#bccEmail').val(task.bccEmail);\r\n\t$('#charsetEmail').val(task.charsetEmail);\r\n\t$('#htmlEmail').val(task.htmlEmail);\r\n\t$('#textEmail').val(task.textEmail);\r\n}\r\nfunction loadTaskListeners(){\r\n\tvar listeners = task.listeners;\r\n");
      out.write("\tvar listener_grid_rows=[];\r\n\tfor(var i=0;i<listeners.getSize();i++){\r\n\t\tvar listener = listeners.get(i);\r\n\t\tvar nlistener = {\r\n\t\t\t\t\tid:listener.getId(),\r\n\t\t\t\t\tlistenerImplimentation:listener.getServiceImplementation(),\r\n\t\t\t\t\ttype:listener.serviceType,\r\n\t\t\t\t\tevent:listener.event,\r\n\t\t\t\t\tfields:listener.getFieldsString(),\r\n\t\t\t\t\taction:''\r\n\t\t\t\t};\r\n\t\tlistener_grid_rows[i]=nlistener;\r\n\t};\r\n\tvar listener_grid_data={\r\n\t\t\ttotal:listeners.getSize(),\r\n\t\t\trows:listener_grid_rows\r\n\t};\r\n\t$('#task-listeners-list').datagrid('loadData',listener_grid_data);\r\n}\r\n//-->\r\n</script>\r\n<div id=\"task-properties-layout\" class=\"easyui-layout\" fit=\"true\">\r\n\t<div id=\"task-properties-toolbar-panel\" region=\"north\" border=\"false\" style=\"height:30px;background:#E1F0F2;\">\r\n\t\t<a href=\"##\" id=\"sb2\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"icon-save\" onclick=\"saveTaskProperties()\">保存</a>\r\n\t</div>\r\n\t<div id=\"task-properties-panel\" region=\"center\" border=\"true\">\r\n\t\t<div id=\"task-properties-accordion\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">\r\n");
      out.write("\t\t\t<div id=\"general\" title=\"主属性\" selected=\"true\" class=\"properties-menu\">\r\n\t\t\t\t<table id=\"general-properties\">\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">Id:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"id\" name=\"id\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">接收者:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"toEmail\" name=\"toEmail\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">发送者:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"fromEmail\" name=\"fromEmail\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">主题:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"subjectEmail\" name=\"subjectEmail\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">抄送:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"ccEmail\" name=\"ccEmail\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">密送:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"bccEmail\" name=\"bccEmail\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">字符集:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"charsetEmail\" name=\"charsetEmail\"  value=\"\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<td align=\"right\">html:</td>\r\n\t\t\t\t\t<td>\r\n\t\t\t\t\t<textarea id=\"htmlEmail\" name=\"htmlEmail\" cols=\"25\" rows=\"6\"></textarea>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<td align=\"right\">文本:</td>\r\n\t\t\t\t\t<td>\r\n\t\t\t\t\t<textarea id=\"textEmail\" name=\"textEmail\" cols=\"25\" rows=\"6\"></textarea>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>");
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
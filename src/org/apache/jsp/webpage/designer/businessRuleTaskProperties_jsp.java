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

public final class businessRuleTaskProperties_jsp extends HttpJspBase
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

      out.write("\r\n<script type=\"text/javascript\">\r\nvar tid = '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("';\r\nvar task = workflow.getFigure(tid);\r\npopulateTaskProperites();\r\nfunction saveTaskProperties(){\r\n\ttask.taskId=$('#id').val();\r\n\ttask.rulesInput=$('#rulesInput').val();\r\n\ttask.rulesOutputs=$('#rulesOutputs').val();\r\n\ttask.isclude=$('#isclude').combobox('getValue');\r\n\ttask.rules=$('#rules').val();\r\n\ttip(\"保存成功!\");\r\n}\r\nfunction populateTaskProperites(){\r\n\t$('#id').val(task.taskId);\r\n\t$('#rulesInput').val(task.rulesInput);\r\n\t$('#rulesOutputs').val(task.rulesOutputs);\r\n\t$('#isclude').combobox('setValue',task.isclude);\r\n\t$('#rules').val(task.rules);\r\n}\r\n\r\n</script>\r\n<div id=\"task-properties-layout\" class=\"easyui-layout\" fit=\"true\">\r\n\t<div id=\"task-properties-toolbar-panel\" region=\"north\" border=\"false\" style=\"height:30px;background:#E1F0F2;\">\r\n\t\t<a href=\"##\" id=\"sb2\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"icon-save\" onclick=\"saveTaskProperties()\">保存</a>\r\n\t</div>\r\n\t<div id=\"task-properties-panel\" region=\"center\" border=\"true\">\r\n\t\t<div id=\"task-properties-accordion\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">\r\n");
      out.write("\t\t\t<div id=\"general\" title=\"主属性\" selected=\"true\" class=\"properties-menu\">\r\n\t\t\t\t<table id=\"general-properties\">\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">Id:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"id\" name=\"id\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">输入变量:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"rulesInput\" name=\"rulesInput\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">输出变量:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"rulesOutputs\" name=\"rulesOutputs\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">选择规则:</td>\r\n\t\t\t\t\t\t<td >\r\n\t\t\t\t\t\t\t<select id=\"isclude\" name=\"isclude\">\r\n\t\t\t\t\t\t\t\t<option value=\"\">全部规则</option>\r\n\t\t\t\t\t\t\t\t<option value=\"include\">包含规则</option>\r\n\t\t\t\t\t\t\t\t<option value=\"exclude\">排除规则</option>\r\n\t\t\t\t\t\t\t</select>\r\n\t\t\t\t\t\t</td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">规则:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"rules\" name=\"rules\"  readonly=\"true\" value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>");
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
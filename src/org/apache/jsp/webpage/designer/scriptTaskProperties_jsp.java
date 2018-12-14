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

public final class scriptTaskProperties_jsp extends HttpJspBase
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
      out.write("';\r\nvar task = workflow.getFigure(tid);\r\npopulateTaskProperites();\r\nfunction saveTaskProperties(){\r\n\ttask.taskId=$('#id').val();\r\n\ttask.taskName=$('#name').val();\r\n\ttask.setContent($('#name').val());\r\n\ttask.expression=$('#expression').val();\r\n\ttask.documentation=$('#documentation').val();\r\n\ttask.scriptFormat=$('#scriptFormat').val();\r\n\ttask.resultVariable=$('#resultVariable').val();\r\n\ttip(\"保存成功 !\");\r\n}\r\nfunction populateTaskProperites(){\r\n\t$('#id').val(task.taskId);\r\n\t$('#name').val(task.taskName);\r\n\t$('#expression').val(task.expression);\r\n\t$('#documentation').val(task.documentation);\r\n\t$('#scriptFormat').val(task.scriptFormat);\r\n\t$('#resultVariable').val(task.resultVariable);\r\n}\r\n</script>\r\n<div id=\"task-properties-layout\" class=\"easyui-layout\" fit=\"true\">\r\n\t<div id=\"task-properties-toolbar-panel\" region=\"north\" border=\"false\" style=\"height:30px;background:#E1F0F2;\">\r\n\t\t<a href=\"##\" id=\"sb2\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"icon-save\" onclick=\"saveTaskProperties()\">保存</a>\r\n\t</div>\r\n\t<div id=\"task-properties-panel\" region=\"center\" border=\"true\">\r\n");
      out.write("\t\t<div id=\"task-properties-accordion\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">\r\n\t\t\t<div id=\"general\" title=\"主属性\" selected=\"true\" class=\"properties-menu\">\r\n\t\t\t\t<table id=\"general-properties\">\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">Id:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"id\" name=\"id\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">标签:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"name\" name=\"name\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">描述:</td>\r\n\t\t\t\t\t\t<td><textarea id=\"documentation\" name=\"documentation\" cols=\"17\" rows=\"3\"></textarea></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">脚本格式:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"scriptFormat\" name=\"scriptFormat\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=\"right\">返回变量:</td>\r\n\t\t\t\t\t\t<td><input type=\"text\" id=\"resultVariable\" name=\"resultVariable\"  value=\"\"/></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<td align=\"right\">脚本:</td>\r\n\t\t\t\t\t<td>\r\n\t\t\t\t\t<textarea id=\"expression\" name=\"expression\" cols=\"25\" rows=\"10\"></textarea>\r\n");
      out.write("\t\t\t\t\t</td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>");
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
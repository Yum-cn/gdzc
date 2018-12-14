package org.apache.jsp.webpage.designer;

import java.io.IOException;
import java.util.ArrayList;
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

public final class eventProperties_jsp extends HttpJspBase
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

      out.write(13);
      out.write(10);
      out.write("\r\n\r\n\r\n \r\n");

      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write(13);
      out.write(10);
      out.write("\r\n<script type=\"text/javascript\">\r\n//update-begin--Author:chenxu  Date:20130327 for：流程设计，开始节点，设置发起人表达式存在浏览器兼容问题，在google和360浏览器下，保存不了\r\n//将event修改为eventFigure\r\nvar eventFigure = workflow.getFigure(nodeid);\r\n//属性表格定义\r\n rows = [\r\n    \r\n            { \"name\": \"ID\", \"group\": \"节点属性\", \"value\": eventFigure.eventId,\"field\": \"eventId\", \"editor\": \"text\" },\r\n            { \"name\": \"名称\", \"group\": \"节点属性\", \"value\": eventFigure.eventName, \"field\": \"eventName\", \"editor\": \"text\" },\r\n            { \"name\": \"表达式\", \"group\": \"发起人\", \"value\": eventFigure.expression, \"field\": \"expression\", \"editor\": \"text\" }\r\n        ];\r\n //保存属性\r\nfunction saveEventProperties(){\r\n\teventFigure.eventId=rows[0].value;\r\n\teventFigure.eventName=rows[1].value;\r\n\teventFigure.expression=rows[2].value;\r\n}\r\n //构建属性表格数据\r\nfunction populateEventProperites(){\r\n\trows[0].value=eventFigure.eventId;\r\n\trows[1].value=eventFigure.eventName;\r\n\trows[2].value=eventFigure.expression;\r\n\tpropertygrid();\r\n} \r\n //加载属性表格数据\r\nfunction eventpropertygrid(){\r\n\t$('#event-properties').propertygrid('loadData',rows);\r\n");
      out.write("\t}\r\n$(function(){\r\n//创建属性表格\r\n$('#event-properties').propertygrid({\r\n  width: 'auto',\r\n  height: 'auto',\r\n  showGroup: true,\r\n  scrollbarSize: 0,\r\n  border:0,\r\n  columns: [[\r\n          { field: 'name', title: '属性名', width: 30, resizable: false },\r\n          { field: 'value', title: '属性值', width: 100, resizable: false }\r\n  ]],\r\n  onAfterEdit:function(){  \r\n  \tsaveEventProperties();//自动保存\r\n   }\r\n});\r\neventpropertygrid();\r\n});\r\n//update-end--Author:chenxu  Date:20130327 for：流程设计，开始节点，设置发起人表达式存在浏览器兼容问题，在google和360浏览器下，保存不了\r\n</script>\r\n<div id=\"event-properties-layout\" class=\"easyui-layout\" fit=\"true\">\r\n <div id=\"event-properties-panel\"  region=\"center\" border=\"true\">\r\n   <table id=\"event-properties\">\r\n   </table>\r\n </div>\r\n</div>");
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

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
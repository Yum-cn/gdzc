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
import org.apache.jasper.runtime.PageContextImpl;
import org.apache.jasper.runtime.TagHandlerPool;
import org.qihuasoft.tag.core.easyui.BaseTag;

public final class index_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody;
  private ExpressionFactory _el_expressionfactory;
  private AnnotationProcessor _jsp_annotationprocessor;

  public Map<String, Long> getDependants()
  {
    return _jspx_dependants;
  }
  public void _jspInit() {
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy() {
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
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
      out.write("\r\n<!DOCTYPE html>\r\n<html>\r\n <head>\r\n  <title>流程设计器</title>\r\n  ");
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\r\n  <script type=\"text/javascript\">\r\n\t$(function() {\r\n\t\ttry {\r\n\t\t\t_task_obj = $('#task');\r\n\t\t\t_task_context_menu = $('#task-context-menu').menu({});\r\n\t\t\t$('.easyui-linkbutton').draggable({\r\n\t\t\t\tproxy : function(source) {\r\n\t\t\t\t\tvar n = $('<div class=\"draggable-model-proxy\"></div>');\r\n\t\t\t\t\tn.html($(source).html()).appendTo('body');\r\n\t\t\t\t\treturn n;\r\n\t\t\t\t},\r\n\t\t\t\tdeltaX : 0,\r\n\t\t\t\tdeltaY : 0,\r\n\t\t\t\trevert : true,\r\n\t\t\t\tcursor : 'auto',\r\n\t\t\t\tonStartDrag : function() {\r\n\t\t\t\t\t$(this).draggable('options').cursor = 'not-allowed';\r\n\t\t\t\t},\r\n\t\t\t\tonStopDrag : function() {\r\n\t\t\t\t\t$(this).draggable('options').cursor = 'auto';\r\n\t\t\t\t}\r\n\t\t\t});\r\n\t\t\t$('#paintarea').droppable({\r\n\t\t\t\taccept : '.easyui-linkbutton',\r\n\t\t\t\tonDragEnter : function(e, source) {\r\n\t\t\t\t\t$(source).draggable('options').cursor = 'auto';\r\n\t\t\t\t},\r\n\t\t\t\tonDragLeave : function(e, source) {\r\n\t\t\t\t\t$(source).draggable('options').cursor = 'not-allowed';\r\n\t\t\t\t},\r\n\t\t\t\tonDrop : function(e, source) {\r\n\t\t\t\t\t$(this).removeClass('over');\r\n\t\t\t\t\tvar wfModel = $(source).attr('wfModel');\r\n");
      out.write("\t\t\t\t\tvar shape = $(source).attr('shape');\r\n\t\t\t\t\tif (wfModel) {\r\n\t\t\t\t\t\tvar x = $(source).draggable('proxy').offset().left;\r\n\t\t\t\t\t\tvar y = $(source).draggable('proxy').offset().top;\r\n\t\t\t\t\t\tvar xOffset = workflow.getAbsoluteX();\r\n\t\t\t\t\t\tvar yOffset = workflow.getAbsoluteY();\r\n\t\t\t\t\t\tvar scrollLeft = workflow.getScrollLeft();\r\n\t\t\t\t\t\tvar scrollTop = workflow.getScrollTop();\r\n\t\t\t\t\t\taddModel(wfModel, x - xOffset + scrollLeft, y - yOffset + scrollTop, shape);\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t});\r\n\t\t\t//$('#paintarea').bind('contextmenu',function(e){\r\n\t\t\t//alert(e.target.tagName);\r\n\t\t\t//});\r\n\r\n\t\t} catch (e) {\r\n\r\n\t\t}\r\n\t});\r\n//-->\r\n</script>\r\n </head>\r\n <body id=\"designer\" class=\"easyui-layout\">\r\n  <div region=\"west\" split=\"true\" iconCls=\"palette-icon\" title=\"流程元素\" style=\"width: 110px;\">\r\n   <div class=\"easyui-accordion\" fit=\"true\" border=\"false\">\r\n    <div id=\"event\" title=\"事件\" iconCls=\"palette-menu-icon\" class=\"palette-menu\">\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"start-event-icon\" wfModel=\"Start\">开始</a>\r\n     <br>\r\n");
      out.write("     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"end-event-icon\" wfModel=\"End\">结束</a>\r\n     <br>\r\n    </div>\r\n    <div id=\"task\" title=\"任务\" iconCls=\"palette-menu-icon\" selected=\"true\" class=\"palette-menu\">\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"user-task-icon\" wfModel=\"UserTask\">用户任务</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"manual-task-icon\" wfModel=\"ManualTask\">手动任务</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"service-task-icon\" wfModel=\"ServiceTask\">服务任务</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"script-task-icon\" wfModel=\"ScriptTask\">脚本任务</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"mail-task-icon\" wfModel=\"MailTask\">邮件任务</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"receive-task-icon\" wfModel=\"ReceiveTask\">接收任务</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"business-rule-task-icon\" wfModel=\"BusinessRuleTask\">业务规则</a>\r\n");
      out.write("     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"subprocess-icon\">子流程</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"callactivity-icon\" wfModel=\"CallActivity\">调用活动</a>\r\n     <br>\r\n    </div>\r\n    <div id=\"gateway\" title=\"网关\" iconCls=\"palette-menu-icon\" class=\"palette-menu\">\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"parallel-gateway-icon\" wfModel=\"ParallelGateway\">同步</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"exclusive-gateway-icon\" wfModel=\"ExclusiveGateway\">分支</a>\r\n     <br>\r\n    </div>\r\n    <div id=\"boundary-event\" title=\"边界事件\" iconCls=\"palette-menu-icon\" class=\"palette-menu\">\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"timer-boundary-event-icon\" wfModel=\"TimerBoundary\">时间边界</a>\r\n     <br>\r\n     <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"error-boundary-event-icon\" wfModel=\"ErrorBoundary\">错误边界</a>\r\n     <br>\r\n    </div>\r\n   </div>\r\n  </div>\r\n  <div id=\"process-panel\" region=\"center\" style=\"padding: 1px\" split=\"true\" iconCls=\"process-icon\" title=\"流程\">\r\n");
      out.write("   <div id=\"process-definition-tab\">\r\n    <div id=\"designer-area\" title=\"设计\" style=\"POSITION: absolute; width: 100%; height: 100%; padding: 0; border: none; overflow: auto;\">\r\n     <div id=\"paintarea\" style=\"POSITION: absolute; WIDTH: 2000px; HEIGHT: 2000px\"></div>\r\n    </div>\r\n    <div id=\"xml-area\" title=\"源码\" style=\"width: 100%; height: 100%; overflow: hidden; overflow-x: hidden; overflow-y: hidden;\">\r\n     <textarea id=\"descriptorarea\" rows=\"38\" style=\"width: 100%; height: 100%; padding: 0; border: none; font-size: 12px\" readonly=\"readonly\"></textarea>\r\n    </div>\r\n   </div>\r\n  </div>\r\n  <!-- toolbar -->\r\n  <!-- update-begin--Author:chenxu  Date:20130408 for：修改流程时，流程类型不能显示 -->\r\n  <div id=\"toolbar-panel\" region=\"north\" border=\"false\" style=\"height: 55px; background: #d8e4fe;\" title=\"工具栏\">\r\n   <input type=\"hidden\" name=\"processId\" id=\"processId\" value=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processid}", String.class, _jspx_page_context, null, false));
      out.write("\">\r\n   <img width=\"20\" height=\"18\" title=\"创建流程\" src=\"plug-in/designer/img/save.png\" onclick=\"saveProcessDef()\" class=\"buttonStyle\" />\r\n   <img width=\"20\" height=\"18\" title=\"上一步\" src=\"plug-in/designer/img/back.png\" onclick=\"undo()\" class=\"buttonStyle\" />\r\n   <img width=\"20\" height=\"18\" title=\"下一步\" src=\"plug-in/designer/img/next.png\" onclick=\"redo()\" class=\"buttonStyle\" />\r\n   <img width=\"20\" height=\"18\" title=\"导出\" src=\"plug-in/designer/img/printer.png\" onclick=\"exportProcessDef(this)\" class=\"buttonStyle\" />\r\n  </div>\r\n  <div region=\"east\" id=\"properties-panel\" href=\"processController.do?processProperties&turn=processProperties&processId=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processid }", String.class, _jspx_page_context, null, false));
      out.write("\" split=\"true\" iconCls=\"properties-icon\" title=\"流程属性\"  style=\"padding:1px;width: 280px;\">\r\n  </div>\r\n  <!-- update-end--Author:chenxu  Date:20130408 for：修改流程时，流程类型不能显示 -->\r\n  <!-- task context menu -->\r\n  <div id=\"task-context-menu\" class=\"easyui-menu\" style=\"width: 120px;\">\r\n   <div id=\"properties-task-context-menu\" iconCls=\"properties-icon\">\r\n    属性\r\n   </div>\r\n   <div id=\"delete-task-context-menu\" iconCls=\"icon-remove\">\r\n    删除\r\n   </div>\r\n  </div>\r\n  <!-- form configuration window -->\r\n  <div id=\"form-win\" title=\"表单配置\" style=\"width: 720px; height: 300px;\">\r\n  </div>\r\n  <!-- form configuration window -->\r\n  <div id=\"variable-win\" title=\"变量配置\" style=\"width: 720px; height: 300px;\">\r\n  </div>\r\n  <!-- listener configuration window -->\r\n  <div id=\"listener-win\" title=\"监听配置\" style=\"width: 720px; height: 300px;\">\r\n  </div>\r\n  <!-- candidate configuration window -->\r\n  <div id=\"task-candidate-win\" title=\"任务配置\" style=\"width: 720px; height: 300px;\">\r\n  </div>\r\n <script type=\"text/javascript\">\r\n\t$('#process-definition-tab').tabs({\r\n");
      out.write("\t\tfit : true,\r\n\t\tonSelect : function(title) {\r\n\t\t\tif (title == '设计') {\r\n\t\t\t} else if (title == '源码') {\r\n\t\t\t\t$('#descriptorarea').val(workflow.toXML());\r\n\r\n\t\t\t}\r\n\t\t}\r\n\t});\r\n</script>\r\n <script type=\"text/javascript\">\r\ncreateCanvas('");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processid}", String.class, _jspx_page_context, null, false));
      out.write("',false);\r\n</script>\r\n </body>\r\n</html>\r\n");
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

  private boolean _jspx_meth_t_005fbase_005f0(PageContext _jspx_page_context)
    throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    BaseTag _jspx_th_t_005fbase_005f0 = (BaseTag)this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.get(BaseTag.class);
    _jspx_th_t_005fbase_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fbase_005f0.setParent(null);

    _jspx_th_t_005fbase_005f0.setType("designer,tools");
    int _jspx_eval_t_005fbase_005f0 = _jspx_th_t_005fbase_005f0.doStartTag();
    if (_jspx_th_t_005fbase_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
    return false;
  }

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
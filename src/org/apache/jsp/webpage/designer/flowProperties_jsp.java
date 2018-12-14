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
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.Tag;
import org.apache.AnnotationProcessor;
import org.apache.jasper.runtime.HttpJspBase;
import org.apache.jasper.runtime.JspSourceDependent;
import org.apache.jasper.runtime.PageContextImpl;
import org.apache.jasper.runtime.TagHandlerPool;
import org.qihuasoft.tag.core.easyui.ChooseTag;
import org.qihuasoft.tag.core.easyui.DataGridColumnTag;
import org.qihuasoft.tag.core.easyui.DataGridDelOptTag;
import org.qihuasoft.tag.core.easyui.DataGridFunOptTag;
import org.qihuasoft.tag.core.easyui.DataGridTag;

public final class flowProperties_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005freplace_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody;
  private ExpressionFactory _el_expressionfactory;
  private AnnotationProcessor _jsp_annotationprocessor;

  public Map<String, Long> getDependants()
  {
    return _jspx_dependants;
  }

  public void _jspInit() {
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005freplace_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy() {
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005freplace_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.release();
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
      out.write("\r\n<script type=\"text/javascript\">\r\n//流程对象\r\nvar line = workflow.getLine(nodeid);\r\n//author:zhangdaihao date:20140802 for:去掉空格\r\nline.condition = line.condition.replace(/(^\\s*)|(\\s*$)/g,\"\");\r\n//属性表格定义\r\nrows = [\r\n         { \"name\": \"ID\", \"group\": \"节点属性\", \"value\": line.lineId,\"field\": \"id\", \"editor\": \"text\" },\r\n         { \"name\": \"Lable\", \"group\": \"节点属性\", \"value\": line.lineName, \"field\": \"name\", \"editor\": \"text\" },\r\n         { \"name\": \"表达式\", \"group\": \"分支条件\", \"value\": line.condition, \"field\": \"condition\", \"editor\": \"text\" }\r\n         \r\n       ];\r\n //保存属性\r\nfunction saveFlowProperties(){\r\n\tline.lineId=rows[0].value;\r\n\tline.lineName=rows[1].value;\r\n\tline.condition=rows[2].value;\r\n\tline.setLabel(rows[1].value);\r\n}\r\n //构建属性表格数据\r\nfunction populateFlowProperites(){\r\n\trows[0].value=line.lineId;\r\n\trows[1].value=line.lineName;\r\n\trows[2].value=line.condition;\r\n\tflowpropertygrid();\r\n} \r\n //加载属性表格数据\r\nfunction flowpropertygrid(){\r\n\t$('#flow-properties').propertygrid('loadData', rows);\r\n\t}\r\n$(function(){\r\n//创建属性表格\r\n$('#flow-properties').propertygrid({\r\n");
      out.write("  width: 'auto',\r\n  height: 'auto',\r\n  showGroup: true,\r\n  scrollbarSize: 0,\r\n  border:0,\r\n  columns: [[\r\n          { field: 'name', title: '属性名', width: 30, resizable: false },\r\n          { field: 'value', title: '属性值', width: 100, resizable: false }\r\n  ]],\r\n  onAfterEdit:function(){  \r\n  \tsaveFlowProperties();//自动保存\r\n   }\r\n});\r\nflowpropertygrid();\r\n});\r\n</script>\r\n<div id=\"flow-properties-layout\" class=\"easyui-layout\" fit=\"true\">\r\n <div id=\"flow-properties-panel\" region=\"center\" border=\"true\">\r\n  <div id=\"flow-properties-accordion\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">\r\n   <div id=\"flow\" style=\"padding: 1px;\" title=\"流程属性面板\" class=\"properties-menu\">\r\n    <table id=\"flow-properties\">\r\n    </table>\r\n   </div>\r\n   <div id=\"flowlisteners\" title=\"执行监听器\" style=\"overflow: hidden; padding: 1px;\">\r\n    ");
      if (_jspx_meth_t_005fdatagrid_005f0(_jspx_page_context))
        return;
      out.write("\r\n    <div id=\"flowlistenerListtb\" style=\"padding: 3px; height: 25px\">\r\n     <div style=\"float: left;\">\r\n      <div class=\"form\">\r\n       <input name=\"listenerid\" type=\"hidden\" value=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${listenerid}", String.class, _jspx_page_context, null, false));
      out.write("\" id=\"listenerid\">\r\n       ");
      if (_jspx_meth_t_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("\r\n      </div>\r\n     </div>\r\n    </div>\r\n   </div>\r\n  </div>\r\n </div>\r\n</div>\r\n<script type=\"text/javascript\">\r\n //保存监听\r\n function saveFlowListener() {\r\n  var listenerid = $('#listenerid').val();\r\n  $.ajax({\r\n   url : \"processController.do?saveProcessListener\",\r\n   type : 'POST',\r\n   data : {\r\n  \ttype:1,\r\n    processNode : '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("',\r\n    processkey : '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
      out.write("',\r\n    listenerid : listenerid\r\n   },\r\n   dataType : 'json',\r\n   success : function(data) {\r\n    if (data.success) {\r\n     $('#flowlistenerList').datagrid('reload');\r\n\r\n    }\r\n   }\r\n  });\r\n  \r\n\r\n }\r\n function setFlowListener(index)\r\n {\r\n  var row = $('#flowlistenerList').datagrid('getRows')[index];\r\n  $.ajax({\r\n   url : \"processController.do?setProcessListener\",\r\n   type : 'POST',\r\n   data : {\r\n    id :row.id\r\n   },\r\n   dataType : 'json',\r\n   success : function(data) {\r\n    if (data.success) {\r\n  \r\n     var listener =  new draw2d.DecoratedConnection.Listener();\r\n     listener.id=row.id;\r\n     listener.serviceType = row.TPListerer_listenertype;\r\n     if(row.TPListerer_listenertype==\"javaClass\")\r\n     {\r\n      listener.serviceClass= row.TPListerer_listenervalue;\r\n     }\r\n     else\r\n     {\r\n       listener.serviceExpression=row.TPListerer_listenervalue;\r\n     }\r\n     \r\n     line.listeners.add(listener);\r\n    }\r\n    else\r\n    {\r\n    \tline.deleteListener(row.id);\r\n    }\r\n    reloadflowlistenerList();\r\n   }\r\n  });\r\n");
      out.write("  \r\n }\r\n</script>\r\n");
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

  private boolean _jspx_meth_t_005fdatagrid_005f0(PageContext _jspx_page_context)
    throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridTag _jspx_th_t_005fdatagrid_005f0 = (DataGridTag)this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.get(DataGridTag.class);
    _jspx_th_t_005fdatagrid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdatagrid_005f0.setParent(null);

    _jspx_th_t_005fdatagrid_005f0.setName("flowlistenerList");

    _jspx_th_t_005fdatagrid_005f0.setActionUrl("processController.do?getNodelisteners&type=process&processNode=" + (String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false) + "&processId=" + (String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));

    _jspx_th_t_005fdatagrid_005f0.setIdField("id");

    _jspx_th_t_005fdatagrid_005f0.setPagination(false);
    int _jspx_eval_t_005fdatagrid_005f0 = _jspx_th_t_005fdatagrid_005f0.doStartTag();
    if (_jspx_eval_t_005fdatagrid_005f0 != 0) {
      while (true) {
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f0(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f1(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f2(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f3(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f4(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f5(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgCol_005f6(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgFunOpt_005f0(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgFunOpt_005f1(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n     ");
        if (_jspx_meth_t_005fdgDelOpt_005f0(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        int evalDoAfterBody = _jspx_th_t_005fdatagrid_005f0.doAfterBody();
        if (evalDoAfterBody != 2)
          break;
      }
    }
    if (_jspx_th_t_005fdatagrid_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.reuse(_jspx_th_t_005fdatagrid_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.reuse(_jspx_th_t_005fdatagrid_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f0(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f0 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f0.setTitle("id");

    _jspx_th_t_005fdgCol_005f0.setHidden(false);

    _jspx_th_t_005fdgCol_005f0.setField("id");
    int _jspx_eval_t_005fdgCol_005f0 = _jspx_th_t_005fdgCol_005f0.doStartTag();
    if (_jspx_th_t_005fdgCol_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f1(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f1 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f1.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f1.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f1.setTitle("名称");

    _jspx_th_t_005fdgCol_005f1.setField("TPListerer_listenername");

    _jspx_th_t_005fdgCol_005f1.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f1 = _jspx_th_t_005fdgCol_005f1.doStartTag();
    if (_jspx_th_t_005fdgCol_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f1);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f2(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f2 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005freplace_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f2.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f2.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f2.setTitle("状态");

    _jspx_th_t_005fdgCol_005f2.setField("status");

    _jspx_th_t_005fdgCol_005f2.setReplace("已启用_1,已禁用_0");

    _jspx_th_t_005fdgCol_005f2.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f2 = _jspx_th_t_005fdgCol_005f2.doStartTag();
    if (_jspx_th_t_005fdgCol_005f2.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005freplace_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f2);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005freplace_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f2);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f3(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f3 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f3.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f3.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f3.setTitle("事件");

    _jspx_th_t_005fdgCol_005f3.setHidden(false);

    _jspx_th_t_005fdgCol_005f3.setField("TPListerer_listenereven");

    _jspx_th_t_005fdgCol_005f3.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f3 = _jspx_th_t_005fdgCol_005f3.doStartTag();
    if (_jspx_th_t_005fdgCol_005f3.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f3);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f3);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f4(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f4 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f4.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f4.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f4.setTitle("类型");

    _jspx_th_t_005fdgCol_005f4.setField("TPListerer_listenertype");

    _jspx_th_t_005fdgCol_005f4.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f4 = _jspx_th_t_005fdgCol_005f4.doStartTag();
    if (_jspx_th_t_005fdgCol_005f4.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f4);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f4);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f5(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f5 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f5.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f5.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f5.setTitle("值");

    _jspx_th_t_005fdgCol_005f5.setHidden(false);

    _jspx_th_t_005fdgCol_005f5.setField("TPListerer_listenervalue");

    _jspx_th_t_005fdgCol_005f5.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f5 = _jspx_th_t_005fdgCol_005f5.doStartTag();
    if (_jspx_th_t_005fdgCol_005f5.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f5);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f5);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f6(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f6 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f6.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f6.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f6.setTitle("操作");

    _jspx_th_t_005fdgCol_005f6.setField("opt");

    _jspx_th_t_005fdgCol_005f6.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f6 = _jspx_th_t_005fdgCol_005f6.doStartTag();
    if (_jspx_th_t_005fdgCol_005f6.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f6);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f6);
    return false;
  }

  private boolean _jspx_meth_t_005fdgFunOpt_005f0(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridFunOptTag _jspx_th_t_005fdgFunOpt_005f0 = (DataGridFunOptTag)this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.get(DataGridFunOptTag.class);
    _jspx_th_t_005fdgFunOpt_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgFunOpt_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgFunOpt_005f0.setExp("status#eq#0");

    _jspx_th_t_005fdgFunOpt_005f0.setFunname("setFlowListener()");

    _jspx_th_t_005fdgFunOpt_005f0.setTitle("启用");
    int _jspx_eval_t_005fdgFunOpt_005f0 = _jspx_th_t_005fdgFunOpt_005f0.doStartTag();
    if (_jspx_th_t_005fdgFunOpt_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdgFunOpt_005f1(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridFunOptTag _jspx_th_t_005fdgFunOpt_005f1 = (DataGridFunOptTag)this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.get(DataGridFunOptTag.class);
    _jspx_th_t_005fdgFunOpt_005f1.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgFunOpt_005f1.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgFunOpt_005f1.setExp("status#eq#1");

    _jspx_th_t_005fdgFunOpt_005f1.setFunname("setFlowListener()");

    _jspx_th_t_005fdgFunOpt_005f1.setTitle("禁用");
    int _jspx_eval_t_005fdgFunOpt_005f1 = _jspx_th_t_005fdgFunOpt_005f1.doStartTag();
    if (_jspx_th_t_005fdgFunOpt_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f1);
    return false;
  }

  private boolean _jspx_meth_t_005fdgDelOpt_005f0(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridDelOptTag _jspx_th_t_005fdgDelOpt_005f0 = (DataGridDelOptTag)this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.get(DataGridDelOptTag.class);
    _jspx_th_t_005fdgDelOpt_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgDelOpt_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgDelOpt_005f0.setExp("status#eq#0");

    _jspx_th_t_005fdgDelOpt_005f0.setUrl("processController.do?delProcesListeren&id={id}");

    _jspx_th_t_005fdgDelOpt_005f0.setTitle("删除");
    int _jspx_eval_t_005fdgDelOpt_005f0 = _jspx_th_t_005fdgDelOpt_005f0.doStartTag();
    if (_jspx_th_t_005fdgDelOpt_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.reuse(_jspx_th_t_005fdgDelOpt_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.reuse(_jspx_th_t_005fdgDelOpt_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fchoose_005f0(PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    ChooseTag _jspx_th_t_005fchoose_005f0 = (ChooseTag)this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.get(ChooseTag.class);
    _jspx_th_t_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fchoose_005f0.setParent(null);

    _jspx_th_t_005fchoose_005f0.setHiddenName("listenerid");

    _jspx_th_t_005fchoose_005f0.setHiddenid("id");

    _jspx_th_t_005fchoose_005f0.setFun("saveFlowListener");

    _jspx_th_t_005fchoose_005f0.setUrl("processController.do?chooseListener&typeid=1");

    _jspx_th_t_005fchoose_005f0.setName("listenerList");

    _jspx_th_t_005fchoose_005f0.setIcon("icon-add");

    _jspx_th_t_005fchoose_005f0.setTitle("执行监听器");
    int _jspx_eval_t_005fchoose_005f0 = _jspx_th_t_005fchoose_005f0.doStartTag();
    if (_jspx_th_t_005fchoose_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.reuse(_jspx_th_t_005fchoose_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.reuse(_jspx_th_t_005fchoose_005f0);
    return false;
  }

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
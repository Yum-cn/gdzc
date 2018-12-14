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
import org.qihuasoft.tag.core.easyui.BaseTag;
import org.qihuasoft.tag.core.easyui.ChooseTag;
import org.qihuasoft.tag.core.easyui.DataGridColumnTag;
import org.qihuasoft.tag.core.easyui.DataGridTag;
import org.qihuasoft.tag.core.easyui.FormValidationTag;

public final class taskListenerConfig_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody;
  private ExpressionFactory _el_expressionfactory;
  private AnnotationProcessor _jsp_annotationprocessor;

  public Map<String, Long> getDependants()
  {
    return _jspx_dependants;
  }

  public void _jspInit() {
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy() {
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction.release();
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.release();
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
      out.write("\r\n<!DOCTYPE html>\r\n<html>\r\n <head>\r\n  <title>添加流程变量</title>\r\n  ");
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\r\n  <script type=\"text/javascript\">\r\n\tvar processNodeId = '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("';\r\n\tvar processId = '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
      out.write("';\r\n\tfunction xuanze() {\r\n\t\tvar ids = getlistenerListSelections(\"listenerid\") + \"\";\r\n\t\tvar events = getlistenerListSelections(\"listenereven\") + \"\";\r\n\t\tvar types = getlistenerListSelections(\"listenertype\") + \"\";\r\n\t\tvar values = getlistenerListSelections(\"listenervalue\") + \"\";\r\n\t\tvar len = (ids.split(\",\")).length;\r\n\t\tvar eventstmp = events.split(\",\");\r\n\t\tvar typestmp = types.split(\",\");\r\n\t\tvar valuestmp = values.split(\",\");\r\n\t\tfor ( var i = 0; i < len; i++) {\r\n\t\t\tvar listener = new draw2d.Task.Listener();\r\n\t\t\tlistener.event = eventstmp[i];\r\n\t\t\tlistener.serviceType = typestmp[i];\r\n\t\t\tif (typestmp[i] == 'javaClass') {\r\n\t\t\t\tlistener.serviceClass = valuestmp[i];\r\n\t\t\t} else if (typestmp[i] == 'expression') {\r\n\t\t\t\tlistener.serviceExpression = valuestmp[i];\r\n\t\t\t}\r\n\t\t\ttask.listeners.add(listener);\r\n\t\t}\r\n\t\tloadTaskListeners();\r\n\t\t_listener_win.window('close');\r\n\t}\r\n</script>\r\n </head>\r\n <body style=\"overflow-y: hidden\" scroll=\"no\">\r\n  ");
      if (_jspx_meth_t_005fformvalid_005f0(_jspx_page_context))
        return;
      out.write("\r\n  <!--  ");
      if (_jspx_meth_t_005fdatagrid_005f0(_jspx_page_context))
        return;
      out.write("\t\r\n\t-->\r\n </body>\r\n</html>\r\n");
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

    _jspx_th_t_005fbase_005f0.setType("jquery,easyui,tools");
    int _jspx_eval_t_005fbase_005f0 = _jspx_th_t_005fbase_005f0.doStartTag();
    if (_jspx_th_t_005fbase_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fformvalid_005f0(PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    FormValidationTag _jspx_th_t_005fformvalid_005f0 = (FormValidationTag)this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction.get(FormValidationTag.class);
    _jspx_th_t_005fformvalid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fformvalid_005f0.setParent(null);

    _jspx_th_t_005fformvalid_005f0.setFormid("formobj");

    _jspx_th_t_005fformvalid_005f0.setLayout("div");

    _jspx_th_t_005fformvalid_005f0.setDialog(false);

    _jspx_th_t_005fformvalid_005f0.setAction("userController.do?save");
    int _jspx_eval_t_005fformvalid_005f0 = _jspx_th_t_005fformvalid_005f0.doStartTag();
    if (_jspx_eval_t_005fformvalid_005f0 != 0) {
      while (true) {
        out.write("\r\n    <fieldset class=\"step\">\r\n     <legend>\r\n      基本信息\r\n     </legend>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       监听\r\n      </label>\r\n      <input name=\"listenerid\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${listenerid}", String.class, _jspx_page_context, null, false));
        out.write("\" id=\"listenerid\">\r\n      <input name=\"listenereven\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${listenereven }", String.class, _jspx_page_context, null, false));
        out.write("\" id=\"listenereven\" readonly=\"readonly\" />\r\n      ");
        if (_jspx_meth_t_005fchoose_005f0(_jspx_th_t_005fformvalid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n   </div>\r\n    </fieldset>\r\n  ");
        int evalDoAfterBody = _jspx_th_t_005fformvalid_005f0.doAfterBody();
        if (evalDoAfterBody != 2)
          break;
      }
    }
    if (_jspx_th_t_005fformvalid_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction.reuse(_jspx_th_t_005fformvalid_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction.reuse(_jspx_th_t_005fformvalid_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fchoose_005f0(JspTag _jspx_th_t_005fformvalid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    ChooseTag _jspx_th_t_005fchoose_005f0 = (ChooseTag)this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005fnobody.get(ChooseTag.class);
    _jspx_th_t_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fchoose_005f0.setParent((Tag)_jspx_th_t_005fformvalid_005f0);

    _jspx_th_t_005fchoose_005f0.setHiddenName("listenerid");

    _jspx_th_t_005fchoose_005f0.setHiddenid("id");

    _jspx_th_t_005fchoose_005f0.setUrl("processController.do?chooseListener");

    _jspx_th_t_005fchoose_005f0.setName("listenerList");

    _jspx_th_t_005fchoose_005f0.setIcon("icon-choose");

    _jspx_th_t_005fchoose_005f0.setTitle("监听列表");

    _jspx_th_t_005fchoose_005f0.setTextname("listenereven");
    int _jspx_eval_t_005fchoose_005f0 = _jspx_th_t_005fchoose_005f0.doStartTag();
    if (_jspx_th_t_005fchoose_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005fnobody.reuse(_jspx_th_t_005fchoose_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005fnobody.reuse(_jspx_th_t_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdatagrid_005f0(PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridTag _jspx_th_t_005fdatagrid_005f0 = (DataGridTag)this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl.get(DataGridTag.class);
    _jspx_th_t_005fdatagrid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdatagrid_005f0.setParent(null);

    _jspx_th_t_005fdatagrid_005f0.setCheckbox(true);

    _jspx_th_t_005fdatagrid_005f0.setName("listenerList");

    _jspx_th_t_005fdatagrid_005f0.setActionUrl("processController.do?getlisteners");

    _jspx_th_t_005fdatagrid_005f0.setIdField("listenerid");

    _jspx_th_t_005fdatagrid_005f0.setPagination(false);
    int _jspx_eval_t_005fdatagrid_005f0 = _jspx_th_t_005fdatagrid_005f0.doStartTag();
    if (_jspx_eval_t_005fdatagrid_005f0 != 0) {
      while (true) {
        out.write("\t \t\r\n\t\t");
        if (_jspx_meth_t_005fdgCol_005f0(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        if (_jspx_meth_t_005fdgCol_005f1(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        if (_jspx_meth_t_005fdgCol_005f2(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        if (_jspx_meth_t_005fdgCol_005f3(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write(13);
        out.write(10);
        out.write(9);
        int evalDoAfterBody = _jspx_th_t_005fdatagrid_005f0.doAfterBody();
        if (evalDoAfterBody != 2)
          break;
      }
    }
    if (_jspx_th_t_005fdatagrid_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl.reuse(_jspx_th_t_005fdatagrid_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl.reuse(_jspx_th_t_005fdatagrid_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f0(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f0 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f0.setTitle("listenerid");

    _jspx_th_t_005fdgCol_005f0.setHidden(false);

    _jspx_th_t_005fdgCol_005f0.setField("listenerid");
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

    _jspx_th_t_005fdgCol_005f1.setTitle("事件类型");

    _jspx_th_t_005fdgCol_005f1.setField("listenereven");

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

    DataGridColumnTag _jspx_th_t_005fdgCol_005f2 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f2.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f2.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f2.setTitle("监听类型");

    _jspx_th_t_005fdgCol_005f2.setField("listenertype");

    _jspx_th_t_005fdgCol_005f2.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f2 = _jspx_th_t_005fdgCol_005f2.doStartTag();
    if (_jspx_th_t_005fdgCol_005f2.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f2);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f2);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f3(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f3 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f3.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f3.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f3.setTitle("值");

    _jspx_th_t_005fdgCol_005f3.setField("listenervalue");

    _jspx_th_t_005fdgCol_005f3.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f3 = _jspx_th_t_005fdgCol_005f3.doStartTag();
    if (_jspx_th_t_005fdgCol_005f3.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f3);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f3);
    return false;
  }

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
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
import org.apache.taglibs.standard.tag.rt.core.IfTag;
import org.qihuasoft.tag.core.easyui.BaseTag;
import org.qihuasoft.tag.core.easyui.FormValidationTag;

public final class processpro_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction;
  private TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private ExpressionFactory _el_expressionfactory;
  private AnnotationProcessor _jsp_annotationprocessor;

  public Map<String, Long> getDependants()
  {
    return _jspx_dependants;
  }

  public void _jspInit() {
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy() {
    this._005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fformvalid_0026_005flayout_005fformid_005fdialog_005faction.release();
    this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
      out.write("\r\n </head>\r\n <body style=\"overflow-y: hidden\" scroll=\"no\">\r\n  ");
      if (_jspx_meth_t_005fformvalid_005f0(_jspx_page_context))
        return;
      out.write("\r\n </body>\r\n</html>\r\n");
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

    _jspx_th_t_005fformvalid_005f0.setDialog(true);

    _jspx_th_t_005fformvalid_005f0.setLayout("div");

    _jspx_th_t_005fformvalid_005f0.setAction("processController.do?saveVariable");
    int _jspx_eval_t_005fformvalid_005f0 = _jspx_th_t_005fformvalid_005f0.doStartTag();
    if (_jspx_eval_t_005fformvalid_005f0 != 0) {
      while (true) {
        out.write("\r\n    <input name=\"processproid\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processpro.processproid}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n    <input name=\"processId\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n    <input name=\"TProcess.processid\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processDefinitionId}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n    <input name=\"TProcessnode.processnodeid\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processnode.processnodeid}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n    <input name=\"procesnode\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processNode}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n    <input name=\"processDefinitionId\" type=\"hidden\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processDefinitionId}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n    <fieldset class=\"step\">\r\n     <legend>\r\n      流程变量\r\n     </legend>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       变量名称:\r\n      </label>\r\n      <input name=\"processprokey\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processpro.processprokey }", String.class, _jspx_page_context, null, false));
        out.write("\" datatype=\"s3-50\" errormsg=\"变量名称范围在3~50位字符!\">\r\n      <span class=\"Validform_checktip\">变量名称范围在3~50位字符,且不为空</span>\r\n     </div>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       变量类型:\r\n      </label>\r\n      <input name=\"processprotype\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processpro.processprotype }", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n     </div>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       变量值:\r\n      </label>\r\n      <input name=\"processproval\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processpro.processproval}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n     </div>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       变量值来源:\r\n      </label>\r\n      <select name=\"processprodatatype\">\r\n       <option value=\"database\" ");
        if (_jspx_meth_c_005fif_005f0(_jspx_th_t_005fformvalid_005f0, _jspx_page_context))
          return true;
        out.write(">\r\n        数据库\r\n       </option>\r\n       <option value=\"page\" ");
        if (_jspx_meth_c_005fif_005f1(_jspx_th_t_005fformvalid_005f0, _jspx_page_context))
          return true;
        out.write(">\r\n        页面\r\n       </option>\r\n      </select>\r\n     </div>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       变量表达式:\r\n      </label>\r\n      <input name=\"processproexp\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processpro.processproexp}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n     </div>\r\n     <div class=\"form\">\r\n      <label class=\"form\">\r\n       变量描述:\r\n      </label>\r\n      <input name=\"processproname\" value=\"");
        out.write((String)PageContextImpl.proprietaryEvaluate("${processpro.processproname}", String.class, _jspx_page_context, null, false));
        out.write("\">\r\n     </div>\r\n    </fieldset>\r\n  ");
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

  private boolean _jspx_meth_c_005fif_005f0(JspTag _jspx_th_t_005fformvalid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    IfTag _jspx_th_c_005fif_005f0 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((Tag)_jspx_th_t_005fformvalid_005f0);

    _jspx_th_c_005fif_005f0.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${processpro.processprodatatype=='database'}", Boolean.class, _jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != 0) {
      while (true) {
        out.write("selected=\"selected\"");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != 2)
          break;
      }
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(JspTag _jspx_th_t_005fformvalid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    IfTag _jspx_th_c_005fif_005f1 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((Tag)_jspx_th_t_005fformvalid_005f0);

    _jspx_th_c_005fif_005f1.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${processpro.processprodatatype=='page'}", Boolean.class, _jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != 0) {
      while (true) {
        out.write("selected=\"selected\"");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != 2)
          break;
      }
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
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
import org.qihuasoft.tag.core.easyui.DataGridColumnTag;
import org.qihuasoft.tag.core.easyui.DataGridTag;

public final class candidateUsersConfig_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody;
  private ExpressionFactory _el_expressionfactory;
  private AnnotationProcessor _jsp_annotationprocessor;

  public Map<String, Long> getDependants()
  {
    return _jspx_dependants;
  }

  public void _jspInit() {
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy() {
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.release();
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
      out.write(13);
      out.write(10);
      if (_jspx_meth_t_005fdatagrid_005f0(_jspx_page_context))
        return;
      out.write("\r\n<script type=\"text/javascript\">\r\nfunction xuanze(){\r\n\tvar ids=getuserListSelections(\"id\");\r\n\t$('#expression').val(ids);\r\n}\r\n</script>\r\n");
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

    DataGridTag _jspx_th_t_005fdatagrid_005f0 = (DataGridTag)this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005fcheckbox_005factionUrl.get(DataGridTag.class);
    _jspx_th_t_005fdatagrid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdatagrid_005f0.setParent(null);

    _jspx_th_t_005fdatagrid_005f0.setCheckbox(((Boolean)PageContextImpl.proprietaryEvaluate("${checkbox}", Boolean.class, _jspx_page_context, null, false)).booleanValue());

    _jspx_th_t_005fdatagrid_005f0.setName("userList");

    _jspx_th_t_005fdatagrid_005f0.setActionUrl("activitiController.do?getUsers");

    _jspx_th_t_005fdatagrid_005f0.setIdField("id");

    _jspx_th_t_005fdatagrid_005f0.setPagination(false);
    int _jspx_eval_t_005fdatagrid_005f0 = _jspx_th_t_005fdatagrid_005f0.doStartTag();
    if (_jspx_eval_t_005fdatagrid_005f0 != 0) {
      while (true) {
        out.write(13);
        out.write(10);
        out.write(32);
        if (_jspx_meth_t_005fdgCol_005f0(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write(13);
        out.write(10);
        out.write(32);
        if (_jspx_meth_t_005fdgCol_005f1(_jspx_th_t_005fdatagrid_005f0, _jspx_page_context))
          return true;
        out.write(13);
        out.write(10);
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

    DataGridColumnTag _jspx_th_t_005fdgCol_005f0 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f0.setTitle("用户名");

    _jspx_th_t_005fdgCol_005f0.setField("firstName");
    int _jspx_eval_t_005fdgCol_005f0 = _jspx_th_t_005fdgCol_005f0.doStartTag();
    if (_jspx_th_t_005fdgCol_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f1(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f1 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f1.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f1.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f1.setTitle("Id");

    _jspx_th_t_005fdgCol_005f1.setField("id");
    int _jspx_eval_t_005fdgCol_005f1 = _jspx_th_t_005fdgCol_005f1.doStartTag();
    if (_jspx_th_t_005fdgCol_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f1);
    return false;
  }

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
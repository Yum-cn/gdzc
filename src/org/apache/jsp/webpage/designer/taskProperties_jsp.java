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

public final class taskProperties_jsp extends HttpJspBase
  implements JspSourceDependent
{
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static Map<String, Long> _jspx_dependants = new HashMap(2);
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005freplace_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody;
  private TagHandlerPool _005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody;
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
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005freplace_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody = TagHandlerPool.getTagHandlerPool(getServletConfig());
    this._el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    this._jsp_annotationprocessor = ((AnnotationProcessor)getServletConfig().getServletContext().getAttribute(AnnotationProcessor.class.getName()));
  }

  public void _jspDestroy() {
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005freplace_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.release();
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.release();
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
      out.write("\r\n<script type=\"text/javascript\">\r\n<!--\r\nvar task = workflow.getFigure(nodeid);//当前节点对象\r\n//属性表格定义\r\nrows = [\r\n    \r\n            { \"name\": \"ID\", \"group\": \"任务属性\", \"value\": task.taskId,\"field\": \"taskId\", \"editor\": \"text\" },\r\n            { \"name\": \"名称\", \"group\": \"任务属性\", \"value\": task.taskName, \"field\": \"taskName\", \"editor\": \"text\" },\r\n            { \"name\": \"描述\", \"group\": \"任务属性\", \"value\": task.documentation, \"field\": \"documentation\", \"editor\": \"text\" },\r\n            { \"name\": \"表单Key\", \"group\": \"表单属性\", \"value\": task.formKey, \"field\": \"formKey\", \"editor\": \"text\" }\r\n           \r\n          \r\n        ];\r\n$(function(){\r\n\t$('#task_extend').val(task.task_extend);\r\n\t$('#performerType').combobox({\r\n\t\t\teditable:false,\r\n\t\t\tonChange:function(newValue, oldValue){\r\n\t\t\t\t$('#expression').val('');\r\n\t\t\t\tswitchTaskCandidatesList(newValue);\r\n\t\t\t}\r\n\t\t});\r\n\r\n\t\r\n\ttask_candidate_panel=$('#task-candidate-panel').panel({\r\n\t\tborder:false\r\n\t\t//minimized:true,\r\n\t});\r\n\tvar ptype='';\r\n\tif($('#performerType').combobox('getValue')!=''){\r\n\t\tptype=$('#performerType').combobox('getValue');\r\n");
      out.write("\t}\r\n\t$('#performerType').combobox('setValue',ptype);\r\n\tswitchTaskCandidatesList(ptype);\r\n});\r\nfunction switchTaskCandidatesList(performerType){\r\n\tif(performerType == 'candidateUsers'){\r\n\t\ttask_candidate_panel.panel(\"refresh\",\"processController.do?processProperties&turn=candidateUsersConfig&checkbox=true\");\r\n\t}else if(performerType == 'candidateGroups'){\r\n\t\ttask_candidate_panel.panel(\"refresh\",\"processController.do?processProperties&turn=candidateGroupsConfig&checkbox=true\");\r\n\t}else if(performerType == 'assignee'){\r\n\t\ttask_candidate_panel.panel(\"refresh\",\"processController.do?processProperties&turn=candidateUsersConfig&checkbox=false\");\r\n\t}\r\n}\r\n\r\n//保存\r\nfunction saveTaskProperties(){\r\n\ttask.taskId=$.trim(rows[0].value);\r\n\ttask.taskName=rows[1].value;\r\n\ttask.formKey=rows[3].value;\r\n\ttask.documentation=rows[2].value;\r\n\ttask.setId($.trim(rows[0].value));\r\n\ttask.setContent($.trim(rows[1].value));\r\n\ttask.performerType=$('#performerType').combobox('getValue');\r\n\ttask.expression=$.trim($('#expression').val());\r\n\ttask.isUseExpression=true;\r\n");
      out.write("\ttask.task_extend=$.trim($('#task_extend').val());\r\n}\r\n//加载变量\r\nfunction populateTaskProperites(){\r\n\t$('#performerType').combobox('setValue',task.performerType);\r\n\t$('#expression').val(task.expression);\r\n\trows[0].value=task.taskId;\r\n\trows[1].value=task.taskName;\r\n\trows[2].value=task.documentation;\r\n\trows[3].value=task.formKey;\r\n\t\r\n}\r\n\r\n//加载属性表格数据\r\nfunction propertygrid(){\r\n\t$('#task-propertygrid').propertygrid('loadData', rows);\r\n\tpopulateTaskProperites();\r\n\t}\r\n//创建属性表格\r\n$('#task-propertygrid').propertygrid({\r\n  width: 'auto',\r\n  height: 'auto',\r\n  showGroup: true,\r\n  scrollbarSize: 0,\r\n  border:0,\r\n  columns: [[\r\n          { field: 'name', title: '属性名', width: 30, resizable: false },\r\n          { field: 'value', title: '属性值', width: 100, resizable: false }\r\n  ]],\r\n  onAfterEdit:function(){  \r\n  \tsaveTaskProperties();//自动保存\r\n   }\r\n});\r\npropertygrid();\r\n\r\n//-->\r\n</script>\r\n<div id=\"task-properties-layout\" class=\"easyui-layout\" fit=\"true\">\r\n <div id=\"task-properties-toolbar-panel\" region=\"north\" border=\"false\" style=\"height:30px; background: #E1F0F2;\">\r\n");
      out.write("  <a href=\"##\" id=\"sb2\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"icon-save\" onclick=\"saveTaskProperties()\">保存</a>\r\n </div>\r\n <div id=\"task-properties-panel\" region=\"center\" border=\"true\">\r\n  <div id=\"task-properties-accordion\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">\r\n   <div id=\"task\" title=\"节点属性\" selected=\"true\" class=\"properties-menu\">\r\n    <table id=\"task-propertygrid\">\r\n    </table>\r\n   </div>\r\n   <div id=\"main-config\" title=\"人员配置\" class=\"properties-menu\" style=\"overflow:hidden;\">\r\n    <div class=\"datagrid-toolbar\" style=\"height:auto\">\r\n     <table id=\"main-properties\">\r\n      <tr>\r\n       <td align=\"right\">\r\n        类型:\r\n       </td>\r\n       <td>\r\n        <select id=\"performerType\" name=\"performerType\" style=\"width:100px;\">\r\n         <option value=\"assignee\">处理人</option>\r\n         <option value=\"candidateUsers\">备选人员\r\n         </option>\r\n         <option value=\"candidateGroups\">备选角色</option>\r\n        </select>\r\n        <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" iconCls=\"icon-search\" onclick=\"xuanze();\">选择</a>\r\n");
      out.write("       </td>\r\n      </tr>\r\n      <tr>\r\n       <td align=\"right\">\r\n        表达式:\r\n       </td>\r\n       <td>\r\n        <input type=\"text\" id=\"expression\" name=\"expression\" style=\"width:160px\" />\r\n       </td>\r\n      </tr>\r\n     </table>\r\n    </div>\r\n    <div id=\"task-candidate-panel\" class=\"easyui-panel\" style=\"overflow: hidden; width: 260px; height: 360px; padding:1px;\">\r\n    </div>\r\n   </div>\r\n \r\n  <div id=\"taskExtendProperties\" title=\"会签属性\" selected=\"true\" style=\"overflow: hidden;padding:1px;\">\r\n    <textarea style=\"margin: 2px; width: 257px; height: 189px;\" name=\"task_extend\" id=\"task_extend\"></textarea>\r\n   </div>\r\n   \r\n   <!--  author：zhangdaihao date:20140801 for:因为报错暂时注释掉\r\n   <div id=\"variableProperties\" title=\"流程变量\" style=\"overflow: hidden;padding:1px;\">\r\n    ");
      if (_jspx_meth_t_005fdatagrid_005f0(_jspx_page_context))
        return;
      out.write("\r\n    <div id=\"variableListtb\" style=\"padding:3px; height: 25px\">\r\n     <div style=\"float: left;\">\r\n      <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-add\" onclick=\"add('添加','processController.do?addOrupdateVariable&processNode=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id }", String.class, _jspx_page_context, null, false));
      out.write("&processId=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
      out.write("&processDefinitionId=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processDefinitionId}", String.class, _jspx_page_context, null, false));
      out.write("')\">添加</a>\r\n      <a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-add\" onclick=\"update('编辑','processController.do?addOrupdateVariable&processNode=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id }", String.class, _jspx_page_context, null, false));
      out.write("&processId=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
      out.write("&processDefinitionId=");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processDefinitionId}", String.class, _jspx_page_context, null, false));
      out.write("','processproid')\">编辑</a>\r\n     </div>\r\n    </div>\r\n   </div>\r\n   -->\r\n   \r\n   <div id=\"listeners\" title=\"任务监听器\" style=\"overflow: hidden;padding:1px;\">\r\n    ");
      if (_jspx_meth_t_005fdatagrid_005f1(_jspx_page_context))
        return;
      out.write("\r\n    <div id=\"listenerListtb\" style=\"padding:3px; height: 25px\">\r\n     <div style=\"float: left;\">\r\n      <div class=\"form\">\r\n       <input name=\"listenerid\" type=\"hidden\" value=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${listenerid}", String.class, _jspx_page_context, null, false));
      out.write("\" id=\"listenerid\">\r\n       ");
      if (_jspx_meth_t_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("\r\n      </div>\r\n     </div>\r\n    </div>\r\n   </div>\r\n  </div>\r\n </div>\r\n</div>\r\n<script type=\"text/javascript\">\r\n\t//保存监听\r\n\tfunction saveProcessListener() {\r\n\t\tvar listenerid = $('#listenerid').val();\r\n\t\t$.ajax({\r\n\t\t\turl : \"processController.do?saveProcessListener\",\r\n\t\t\ttype : 'POST',\r\n\t\t\tdata : {\r\n\t\t\t\ttype:2,\r\n\t\t\t\tprocessNode : '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("',\r\n\t\t\t\tprocesskey : '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
      out.write("',\r\n\t\t\t\tlistenerid : listenerid\r\n\t\t\t},\r\n\t\t\tdataType : 'json',\r\n\t\t\terror : function() {\r\n\t\t\t\treturn \"\";\r\n\t\t\t},\r\n\t\t\tsuccess : function(data) {\r\n\t\t\t\tif (data.success) {\r\n\t\t\t\t\t$('#listenerList').datagrid('reload');\r\n\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t});\r\n\t\t\r\n\r\n\t}\r\n\tfunction setProcessListener(index)\r\n\t{\r\n\t\tvar row = $('#listenerList').datagrid('getRows')[index];\r\n\t\t$.ajax({\r\n\t\t\turl : \"processController.do?setProcessListener\",\r\n\t\t\ttype : 'POST',\r\n\t\t\tdata : {\r\n\t\t\t\tid :row.id\r\n\t\t\t},\r\n\t\t\tdataType : 'json',\r\n\t\t\tsuccess : function(data) {\r\n\t\t\t\tif (data.success) {\r\n\t\t\t\t\tvar listener = new draw2d.Task.Listener();\r\n\t\t\t\t\tlistener.event=row.TPListerer_listenereven;\r\n\t\t\t\t\tlistener.id=row.id;\r\n\t\t\t\t\tlistener.serviceType = row.TPListerer_listenertype;\r\n\t\t\t\t\tif(row.TPListerer_listenertype==\"javaClass\")\r\n\t\t\t\t\t{\r\n\t\t\t\t\t\tlistener.serviceClass= row.TPListerer_listenervalue;\r\n\t\t\t\t\t}\r\n\t\t\t\t\telse\r\n\t\t\t\t\t{\r\n\t\t\t\t\t \tlistener.serviceExpression=row.TPListerer_listenervalue;\r\n\t\t\t\t\t}\r\n\t\t\t\t\t\r\n\t\t\t\t\ttask.listeners.add(listener);\r\n\t\t\t\t}\r\n\t\t\t\telse\r\n\t\t\t\t{\r\n\t\t\t\t\ttask.deleteListener(row.id);\r\n");
      out.write("\t\t\t\t}\r\n\t\t\t\treloadlistenerList();\r\n\t\t\t}\r\n\t\t});\r\n\t\t\r\n\t}\r\n</script>\r\n");
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

    _jspx_th_t_005fdatagrid_005f0.setName("variableList");

    _jspx_th_t_005fdatagrid_005f0.setActionUrl("processController.do?getVariables&processNode=" + (String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false) + "&processId=" + (String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));

    _jspx_th_t_005fdatagrid_005f0.setIdField("processproid");

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

    _jspx_th_t_005fdgCol_005f0.setTitle("Id");

    _jspx_th_t_005fdgCol_005f0.setHidden(false);

    _jspx_th_t_005fdgCol_005f0.setField("processproid");
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

    _jspx_th_t_005fdgCol_005f1.setTitle("ID");

    _jspx_th_t_005fdgCol_005f1.setField("processprokey");

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

    _jspx_th_t_005fdgCol_005f2.setTitle("名称");

    _jspx_th_t_005fdgCol_005f2.setField("processproname");

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

    _jspx_th_t_005fdgCol_005f3.setField("processproval");

    _jspx_th_t_005fdgCol_005f3.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f3 = _jspx_th_t_005fdgCol_005f3.doStartTag();
    if (_jspx_th_t_005fdgCol_005f3.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f3);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f3);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f4(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f4 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f4.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f4.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgCol_005f4.setTitle("操作");

    _jspx_th_t_005fdgCol_005f4.setField("opt");
    int _jspx_eval_t_005fdgCol_005f4 = _jspx_th_t_005fdgCol_005f4.doStartTag();
    if (_jspx_th_t_005fdgCol_005f4.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f4);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f4);
    return false;
  }

  private boolean _jspx_meth_t_005fdgDelOpt_005f0(JspTag _jspx_th_t_005fdatagrid_005f0, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridDelOptTag _jspx_th_t_005fdgDelOpt_005f0 = (DataGridDelOptTag)this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fnobody.get(DataGridDelOptTag.class);
    _jspx_th_t_005fdgDelOpt_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgDelOpt_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f0);

    _jspx_th_t_005fdgDelOpt_005f0.setUrl("processController.do?deleteVariable&variableId={processproid}");

    _jspx_th_t_005fdgDelOpt_005f0.setTitle("删除");
    int _jspx_eval_t_005fdgDelOpt_005f0 = _jspx_th_t_005fdgDelOpt_005f0.doStartTag();
    if (_jspx_th_t_005fdgDelOpt_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fnobody.reuse(_jspx_th_t_005fdgDelOpt_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fnobody.reuse(_jspx_th_t_005fdgDelOpt_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdatagrid_005f1(PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridTag _jspx_th_t_005fdatagrid_005f1 = (DataGridTag)this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.get(DataGridTag.class);
    _jspx_th_t_005fdatagrid_005f1.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdatagrid_005f1.setParent(null);

    _jspx_th_t_005fdatagrid_005f1.setName("listenerList");

    _jspx_th_t_005fdatagrid_005f1.setActionUrl("processController.do?getNodelisteners&type=task&processNode=" + (String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false) + "&processId=" + (String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));

    _jspx_th_t_005fdatagrid_005f1.setIdField("id");

    _jspx_th_t_005fdatagrid_005f1.setPagination(false);
    int _jspx_eval_t_005fdatagrid_005f1 = _jspx_th_t_005fdatagrid_005f1.doStartTag();
    if (_jspx_eval_t_005fdatagrid_005f1 != 0) {
      while (true) {
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f5(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f6(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f7(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f8(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f9(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f10(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgCol_005f11(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgFunOpt_005f0(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgFunOpt_005f1(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        if (_jspx_meth_t_005fdgDelOpt_005f1(_jspx_th_t_005fdatagrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n    ");
        int evalDoAfterBody = _jspx_th_t_005fdatagrid_005f1.doAfterBody();
        if (evalDoAfterBody != 2)
          break;
      }
    }
    if (_jspx_th_t_005fdatagrid_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.reuse(_jspx_th_t_005fdatagrid_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdatagrid_0026_005fpagination_005fname_005fidField_005factionUrl.reuse(_jspx_th_t_005fdatagrid_005f1);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f5(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f5 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f5.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f5.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f5.setTitle("id");

    _jspx_th_t_005fdgCol_005f5.setHidden(false);

    _jspx_th_t_005fdgCol_005f5.setField("id");
    int _jspx_eval_t_005fdgCol_005f5 = _jspx_th_t_005fdgCol_005f5.doStartTag();
    if (_jspx_th_t_005fdgCol_005f5.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f5);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f5);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f6(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f6 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f6.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f6.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f6.setTitle("名称");

    _jspx_th_t_005fdgCol_005f6.setField("TPListerer_listenername");

    _jspx_th_t_005fdgCol_005f6.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f6 = _jspx_th_t_005fdgCol_005f6.doStartTag();
    if (_jspx_th_t_005fdgCol_005f6.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f6);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f6);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f7(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f7 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005freplace_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f7.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f7.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f7.setTitle("状态");

    _jspx_th_t_005fdgCol_005f7.setField("status");

    _jspx_th_t_005fdgCol_005f7.setReplace("已启用_1,已禁用_0");
    int _jspx_eval_t_005fdgCol_005f7 = _jspx_th_t_005fdgCol_005f7.doStartTag();
    if (_jspx_th_t_005fdgCol_005f7.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005freplace_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f7);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005ftitle_005freplace_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f7);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f8(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f8 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f8.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f8.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f8.setTitle("事件");

    _jspx_th_t_005fdgCol_005f8.setHidden(false);

    _jspx_th_t_005fdgCol_005f8.setField("TPListerer_listenereven");

    _jspx_th_t_005fdgCol_005f8.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f8 = _jspx_th_t_005fdgCol_005f8.doStartTag();
    if (_jspx_th_t_005fdgCol_005f8.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f8);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f8);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f9(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f9 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f9.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f9.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f9.setTitle("类型");

    _jspx_th_t_005fdgCol_005f9.setField("TPListerer_listenertype");

    _jspx_th_t_005fdgCol_005f9.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f9 = _jspx_th_t_005fdgCol_005f9.doStartTag();
    if (_jspx_th_t_005fdgCol_005f9.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f9);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f9);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f10(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f10 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f10.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f10.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f10.setTitle("值");

    _jspx_th_t_005fdgCol_005f10.setHidden(false);

    _jspx_th_t_005fdgCol_005f10.setField("TPListerer_listenervalue");

    _jspx_th_t_005fdgCol_005f10.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f10 = _jspx_th_t_005fdgCol_005f10.doStartTag();
    if (_jspx_th_t_005fdgCol_005f10.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f10);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005fhidden_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f10);
    return false;
  }

  private boolean _jspx_meth_t_005fdgCol_005f11(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridColumnTag _jspx_th_t_005fdgCol_005f11 = (DataGridColumnTag)this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.get(DataGridColumnTag.class);
    _jspx_th_t_005fdgCol_005f11.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgCol_005f11.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgCol_005f11.setTitle("操作");

    _jspx_th_t_005fdgCol_005f11.setField("opt");

    _jspx_th_t_005fdgCol_005f11.setWidth(new Integer(40));
    int _jspx_eval_t_005fdgCol_005f11 = _jspx_th_t_005fdgCol_005f11.doStartTag();
    if (_jspx_th_t_005fdgCol_005f11.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f11);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgCol_0026_005fwidth_005ftitle_005ffield_005fnobody.reuse(_jspx_th_t_005fdgCol_005f11);
    return false;
  }

  private boolean _jspx_meth_t_005fdgFunOpt_005f0(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridFunOptTag _jspx_th_t_005fdgFunOpt_005f0 = (DataGridFunOptTag)this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.get(DataGridFunOptTag.class);
    _jspx_th_t_005fdgFunOpt_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgFunOpt_005f0.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgFunOpt_005f0.setExp("status#eq#0");

    _jspx_th_t_005fdgFunOpt_005f0.setFunname("setProcessListener()");

    _jspx_th_t_005fdgFunOpt_005f0.setTitle("启用");
    int _jspx_eval_t_005fdgFunOpt_005f0 = _jspx_th_t_005fdgFunOpt_005f0.doStartTag();
    if (_jspx_th_t_005fdgFunOpt_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fdgFunOpt_005f1(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridFunOptTag _jspx_th_t_005fdgFunOpt_005f1 = (DataGridFunOptTag)this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.get(DataGridFunOptTag.class);
    _jspx_th_t_005fdgFunOpt_005f1.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgFunOpt_005f1.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgFunOpt_005f1.setExp("status#eq#1");

    _jspx_th_t_005fdgFunOpt_005f1.setFunname("setProcessListener()");

    _jspx_th_t_005fdgFunOpt_005f1.setTitle("禁用");
    int _jspx_eval_t_005fdgFunOpt_005f1 = _jspx_th_t_005fdgFunOpt_005f1.doStartTag();
    if (_jspx_th_t_005fdgFunOpt_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgFunOpt_0026_005ftitle_005ffunname_005fexp_005fnobody.reuse(_jspx_th_t_005fdgFunOpt_005f1);
    return false;
  }

  private boolean _jspx_meth_t_005fdgDelOpt_005f1(JspTag _jspx_th_t_005fdatagrid_005f1, PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    DataGridDelOptTag _jspx_th_t_005fdgDelOpt_005f1 = (DataGridDelOptTag)this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.get(DataGridDelOptTag.class);
    _jspx_th_t_005fdgDelOpt_005f1.setPageContext(_jspx_page_context);
    _jspx_th_t_005fdgDelOpt_005f1.setParent((Tag)_jspx_th_t_005fdatagrid_005f1);

    _jspx_th_t_005fdgDelOpt_005f1.setExp("status#eq#0");

    _jspx_th_t_005fdgDelOpt_005f1.setUrl("processController.do?delProcesListeren&id={id}");

    _jspx_th_t_005fdgDelOpt_005f1.setTitle("删除");
    int _jspx_eval_t_005fdgDelOpt_005f1 = _jspx_th_t_005fdgDelOpt_005f1.doStartTag();
    if (_jspx_th_t_005fdgDelOpt_005f1.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.reuse(_jspx_th_t_005fdgDelOpt_005f1);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fdgDelOpt_0026_005furl_005ftitle_005fexp_005fnobody.reuse(_jspx_th_t_005fdgDelOpt_005f1);
    return false;
  }

  private boolean _jspx_meth_t_005fchoose_005f0(PageContext _jspx_page_context) throws Throwable
  {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();

    ChooseTag _jspx_th_t_005fchoose_005f0 = (ChooseTag)this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.get(ChooseTag.class);
    _jspx_th_t_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fchoose_005f0.setParent(null);

    _jspx_th_t_005fchoose_005f0.setHiddenName("listenerid");

    _jspx_th_t_005fchoose_005f0.setHiddenid("id");

    _jspx_th_t_005fchoose_005f0.setFun("saveProcessListener");

    _jspx_th_t_005fchoose_005f0.setUrl("processController.do?chooseListener&typeid=2");

    _jspx_th_t_005fchoose_005f0.setTextname("listenerList");

    _jspx_th_t_005fchoose_005f0.setName("listenerList");

    _jspx_th_t_005fchoose_005f0.setIcon("icon-add");

    _jspx_th_t_005fchoose_005f0.setTitle("监听列表");
    int _jspx_eval_t_005fchoose_005f0 = _jspx_th_t_005fchoose_005f0.doStartTag();
    if (_jspx_th_t_005fchoose_005f0.doEndTag() == 5) {
      this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.reuse(_jspx_th_t_005fchoose_005f0);
      return true;
    }
    this._005fjspx_005ftagPool_005ft_005fchoose_0026_005furl_005ftitle_005ftextname_005fname_005ficon_005fhiddenid_005fhiddenName_005ffun_005fnobody.reuse(_jspx_th_t_005fchoose_005f0);
    return false;
  }

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
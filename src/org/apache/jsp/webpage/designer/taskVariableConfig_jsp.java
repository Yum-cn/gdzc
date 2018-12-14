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

public final class taskVariableConfig_jsp extends HttpJspBase
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
      out.write(9);
      out.write("\r\n\r\n\r\n \r\n");

      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write(13);
      out.write(10);
      out.write(13);
      out.write(10);
      out.write(9);
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write(" \r\n<script type=\"text/javascript\">\r\n<!--\r\nvar variableFieldsEditCount = 0;\r\nvar variableId = '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("';\r\nvar processNode='");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processNode}", String.class, _jspx_page_context, null, false));
      out.write("';\r\nvar processId='");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processId}", String.class, _jspx_page_context, null, false));
      out.write("';\r\nvar processDefinitionId='");
      out.write((String)PageContextImpl.proprietaryEvaluate("${processDefinitionId}", String.class, _jspx_page_context, null, false));
      out.write("';\r\nvar files='processproid,processprokey,processprotype,processproval,processproexp,processproname,processprodatatype';\r\n$(function(){\r\n\t_task_variable_fields_dg=$('#task-variables-fields-list').datagrid({\r\n\t\t//title:\"Listener\",\r\n\t\turl:'activitiController.do?getVariable&processNode='+processNode+'&processId='+processId+'&field='+files+'&variableId='+variableId,//\r\n\t\tsingleSelect:true,\r\n\t\twidth:700,\r\n\t\theight:200,\r\n\t\ticonCls:'icon-edit',\r\n\t\t//fit:true,\r\n\t\t//idField:'id',\r\n\t\t//pagination:true,\r\n\t\t//pageSize:15,\r\n\t\t//pageNumber:1,\r\n\t\t//pageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\t//sortName:'id',\r\n\t    //sortOrder:'asc',\r\n\t    striped:true,\r\n\t    toolbar:[{\r\n\t        text:'New',\r\n\t        iconCls:'icon-add',\r\n\t        handler:function(){\r\n\t\t    \tif(variableFieldsEditCount>0){\r\n\t\t    \t\t$.messager.alert(\"error\",\"有可编辑的单元格，不能添加\",'error');\r\n\t\t\t\t\treturn;\r\n\t\t\t\t}\r\n\t\t\t\t$('#task-variables-fields-list').datagrid('appendRow',{\r\n\t\t\t\t\tprocessproid:'',\r\n\t\t\t\t\tprocessprokey:'',\r\n\t\t\t\t\tprocessprotype:'',\r\n\t\t\t\t\tprocessproval:'',\r\n\t\t\t\t\tprocessproexp:'',\r\n");
      out.write("\t\t\t\t\tprocessproname:'',\r\n\t\t\t\t\tprocessprodatatype:'',\r\n\t\t\t\t\taction:''\r\n\t\t\t\t});\r\n\t\t\t\tvar index = $('#task-variables-fields-list').datagrid('getRows').length-1;\r\n\t\t\t\t$('#task-variables-fields-list').datagrid('beginEdit', index);\r\n\t        }\r\n\t    }],\r\n\t\t\r\n\t\tonDblClickRow:function(rowIndex,rowData){\r\n\t\t\teditVariableField(rowIndex);\r\n\t\t},\r\n\t\t\r\n\t\tonBeforeEdit:function(index,row){\r\n\t\t\trow.editing = true;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tvariableFieldsEditCount++;\r\n\t\t},\r\n\t\tonAfterEdit:function(index,row){\r\n\t\t\trow.editing = false;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tvariableFieldsEditCount--;\r\n\t\t},\r\n\t\tonCancelEdit:function(index,row){\r\n\t\t\trow.editing = false;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tvariableFieldsEditCount--;\r\n\t\t}\r\n\t});\r\n\t$('#fieldSaveBt').linkbutton({\r\n\t\ticonCls:\"icon-save\"\r\n\t});\r\n\t$('#fieldCancelBt').linkbutton({\r\n\t\ticonCls:\"icon-cancel\"\r\n\t});\r\n});\r\n\r\nfunction variableFieldsActionFormatter(value,rowData,rowIndex){\r\n\tvar id = rowIndex;\r\n\tvar s='<img onclick=\"saveVariableField('+id+')\" src=\"plug-in/designer/img/ok.png\" title=\"'+\"确定\"+'\" style=\"cursor:hand;\"/>';\r\n");
      out.write("\tvar c='<img onclick=\"cancelVariableField('+id+')\" src=\"plug-in/designer/img/cancel.png\" title=\"'+\"取消\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar e='<img onclick=\"editVariableField('+id+')\" src=\"plug-in/designer/img/modify.png\" title=\"'+\"修改\"+'\" style=\"cursor:hand;\"/>';\r\n\tif(rowData.editing)\r\n\t\treturn s;\r\n\telse\r\n\t\treturn e;\r\n}\r\nfunction cancelVariableField(id){\r\n\t_task_variable_fields_dg.datagrid('cancelEdit', id);\r\n}\r\nfunction editVariableField(id){\r\n\t_task_variable_fields_dg.datagrid('beginEdit', id);\r\n}\r\nfunction saveVariableField(id){\r\n\t//alert(id);\r\n\t_task_variable_fields_dg.datagrid('endEdit', id);\r\n\t//alert(editcount);\r\n}\r\n\r\nfunction refreshAllVariableFields(){\r\n\tvar rs = _task_variable_fields_dg.datagrid('getRows');\r\n\tfor(var i=0;i<rs.length;i++){\r\n\t\tvar ri =_task_variable_fields_dg.datagrid('getRowIndex',rs[i]);\r\n\t\t_task_variable_fields_dg.datagrid('refreshRow',ri);\r\n\t}\r\n}\r\nfunction createNewVariable(){\r\n\t\r\n}\r\nfunction getExsitingForm(){\r\n\t\r\n}\r\nfunction getVariableFieldsGridChangeRows(){\r\n\tif(variableFieldsEditCount>0){\r\n");
      out.write("\t\t$.messager.alert(\"error\",\"\",'error');\r\n\t\treturn null;\r\n\t}\r\n    var insertRows = _task_variable_fields_dg.datagrid('getChanges','inserted');   \r\n    var updateRows = _task_variable_fields_dg.datagrid('getChanges','updated');   \r\n    var deleteRows = _task_variable_fields_dg.datagrid('getChanges','deleted');   \r\n    var changesRows = {   \r\n            inserted : [],   \r\n            updated : [],\r\n            deleted : []  \r\n            };   \r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {   \r\n            changesRows.inserted.push(insertRows[i]);\r\n        }   \r\n    }   \r\n\r\n    if (updateRows.length>0) {   \r\n        for (var k=0;k<updateRows.length;k++) {   \r\n            changesRows.updated.push(updateRows[k]);\r\n        }   \r\n    }   \r\n       \r\n    if (deleteRows.length>0) {   \r\n        for (var j=0;j<deleteRows.length;j++) {   \r\n            changesRows.deleted.push(deleteRows[j]);   \r\n        }   \r\n    }\r\n    return changesRows;\r\n}\r\nfunction saveVariableConfig(){\r\n\tif(variableId != \"\" && variableId != null && variableId!=\"null\"&&variableId!=\"NULL\"){\r\n");
      out.write("\t\tvar r = updateExistingVariable();\r\n\t\tif(!r)return;\t\t\r\n\t}else{\r\n\t\tvar r = insertNewVariable();\r\n\t\tif(!r)return;\r\n\t}\r\n\t_variable_win.window('close');\r\n\t\r\n}\r\nfunction insertNewVariable(){\r\n    var changesRows = getVariableFieldsGridChangeRows();\r\n    var params=\"\";\r\n    if(changesRows == null)return false;\r\n    var insertRows = changesRows['inserted'];   \r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {\r\n        \tvar id=insertRows[i].processproid;\r\n        \tvar name=insertRows[i].processprokey;\r\n        \tvar value=insertRows[i].processproval;\r\n        \tvar type=insertRows[i].processprotype;\r\n        \tvar exp=insertRows[i].processproexp;\r\n        \tvar remark=insertRows[i].processproname;\r\n        \tvar source=insertRows[i].processprodatatype;\r\n        \tparams=params+\"processId=\"+processId+\"###tid=\"+tid+\"###name=\"+name+\"###type=\"+type+\"###value=\"+value+\"###exp=\"+exp+\"###remark=\"+remark+\"###source=\"+source+\"###varibleid=\"+id+\"@@@\";\r\n       }   \r\n    }\r\n    \r\n    $.ajax({\r\n\t\turl : \"activitiController.do?saveProcessDescriptor\",\r\n");
      out.write("\t\ttype : 'POST',\r\n\t\tdata : {\r\n\t\t\tprocessDescriptor : '',\r\n\t\t\tprocessName : '',\r\n\t\t\tprocessId : processId,\r\n\t\t\tparams:params,\r\n\t\t\tnodes:'',\r\n\t\t\tprocessDefinitionId:processDefinitionId\r\n\t\t},\r\n\t\tdataType : 'json',\r\n\t\terror : function() {\r\n\t\t\treturn \"\";\r\n\t\t},\r\n\t\tsuccess : function(data) {\r\n\t\t\tif (data.success) {\r\n\t\t\t\t$.messager.alert('Info', '保存成功!', 'info');\r\n\t\t\t\t$('#task-variable-properties-list').datagrid('reload');\r\n\t\t\t} \r\n\t\t}\r\n\t});\r\n\treturn true;\r\n}\r\nfunction updateExistingVariable(){\r\n\tvar params=\"\";\r\n\tvar changesRows = getVariableFieldsGridChangeRows();\r\n    if(changesRows == null)return false;\r\n    var insertRows = changesRows['inserted'];   \r\n    var updateRows = changesRows['updated'];   \r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {  \r\n        \tvar id=insertRows[i].processproid;\r\n        \tvar name=insertRows[i].processprokey;\r\n        \tvar value=insertRows[i].processproval;\r\n        \tvar type=insertRows[i].processprotype;\r\n        \tvar exp=insertRows[i].processproexp;\r\n");
      out.write("        \tvar remark=insertRows[i].processproname;\r\n        \tvar source=insertRows[i].processprodatatype;\r\n        \tparams=params+\"processId=\"+processId+\"###tid=\"+tid+\"###name=\"+name+\"###type=\"+type+\"###value=\"+value+\"###exp=\"+exp+\"###remark=\"+remark+\"###source=\"+source+\"###varibleid=\"+id+\"@@@\";\r\n        }   \r\n    }   \r\n\r\n    if (updateRows.length>0) { \r\n        for (var k=0;k<updateRows.length;k++) { \r\n        \tvar id=updateRows[k].processproid;\r\n        \tvar name=updateRows[k].processprokey;\r\n        \tvar value=updateRows[k].processproval;\r\n        \tvar type=updateRows[k].processprotype;\r\n        \tvar exp=updateRows[k].processproexp;\r\n        \tvar remark=updateRows[k].processproname;\r\n        \tvar source=updateRows[k].processprodatatype;\r\n        \tparams=params+\"processId=\"+processId+\"###tid=\"+tid+\"###name=\"+name+\"###type=\"+type+\"###value=\"+value+\"###exp=\"+exp+\"###remark=\"+remark+\"###source=\"+source+\"###varibleid=\"+id+\"@@@\";\r\n        }   \r\n    }  \r\n    $.ajax({\r\n\t\turl : \"activitiController.do?saveProcessDescriptor\",\r\n");
      out.write("\t\ttype : 'POST',\r\n\t\tdata : {\r\n\t\t\tprocessDescriptor : '',\r\n\t\t\tprocessName : '',\r\n\t\t\tprocessId : processId,\r\n\t\t\tparams:params,\r\n\t\t\tnodes:'',\r\n\t\t\tprocessDefinitionId:processDefinitionId\r\n\t\t},\r\n\t\tdataType : 'json',\r\n\t\terror : function() {\r\n\t\t\treturn \"\";\r\n\t\t},\r\n\t\tsuccess : function(data) {\r\n\t\t\tif (data.success) {\r\n\t\t\t\t$.messager.alert('Info', '保存成功!', 'info');\r\n\t\t\t\t$('#task-variable-properties-list').datagrid('reload');\r\n\t\t\t} \r\n\t\t}\r\n\t});   \r\n    return true;\r\n}\r\n\r\n\r\nfunction closeTaskVariableWin(){\r\n\t_variable_win.window('close');\r\n}\r\n//-->\r\n</script>\r\n<table>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table id=\"task-variables-fields-list\">\r\n\t\t\t\t\t<thead>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<th field=\"processproid\" hidden=\"false\"></th>\r\n\t\t\t\t\t<th field=\"processprokey\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\trequired:true,\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">名称</th>\r\n\t\t\t\t\t<th field=\"processprotype\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'combobox',\r\n\t\t\t\t\t\toptions:{\r\n");
      out.write("\t\t\t\t\t\t\teditable:false,\r\n\t\t\t\t\t\t\tdata:[{id:'S',text:'字符',selected:true},{id:'I',text:'整型'},{id:'B',text:'布尔型'},{id:'F',text:'单精度浮点数'},{id:'L',text:'长整型'},{id:'D',text:'日期'},{id:'SD',text:'sql Date类型'},{id:'N',text:'双精度浮点数'}],\r\n\t\t\t\t\t\t\tvalueField:'id',\r\n\t\t\t\t\t\t\ttextField:'text'\r\n\t\t\t\t\t}}\">类型</th>\r\n\t\t\t\t\t<th field=\"processproval\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">值</th>\r\n\t\t\t\t\t<th field=\"processproexp\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">表达式</th>\r\n\t\t\t\t\t<th field=\"processproname\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">描述</th>\r\n\t\t\t\t\t<th field=\"processprodatatype\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'combobox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\t\teditable:false,\r\n\t\t\t\t\t\t\tdata:[{id:'database',text:'数据库'},{id:'page',text:'页面'}],\r\n");
      out.write("\t\t\t\t\t\t\tvalueField:'id',\r\n\t\t\t\t\t\t\ttextField:'text'\r\n\t\t\t\t\t}}\">来源</th>\r\n\t\t\t\t\t<th field=\"action\" width=\"80\" align=\"middle\" formatter=\"variableFieldsActionFormatter\">操作</th>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t</thead>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"center\">\r\n\t\t\t\t<a href=\"##\" id=\"fieldSaveBt\" onclick=\"saveVariableConfig()\">Save</a>\r\n\t\t\t\t<a href=\"##\" id=\"fieldCancelBt\" onclick=\"closeTaskVariableWin()\">Cancel</a>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n</table>");
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

  static
  {
	  _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1468918491864L));
  }
}
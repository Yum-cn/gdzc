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

public final class flowListenerConfig_jsp extends HttpJspBase
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

      out.write("\r\n<script type=\"text/javascript\">\r\n<!--\r\nvar listenerFieldsEditCount = 0;\r\nvar listenerId=\"\";\r\n$(function(){\r\n\t\r\n\t_listener_event_type=$('#listenerEventType').combobox({\r\n\t\teditable:false\r\n\t});\r\n\t\r\n\t_line_listener_fields_dg=$('#line-listeners-fields-list').datagrid({\r\n\t\t//title:\"Listener\",\r\n\t\t//url:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!search.action',//加载表格数据的URL\r\n\t\tsingleSelect:true,\r\n\t\twidth:600,\r\n\t\theight:300,\r\n\t\ticonCls:'icon-edit',\r\n\t\t//fit:true,\r\n\t\t//idField:'id',\r\n\t\t//pagination:true,\r\n\t\t//pageSize:15,\r\n\t\t//pageNumber:1,\r\n\t\t//pageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\t//sortName:'id',\r\n\t    //sortOrder:'asc',\r\n\t    striped:true,\r\n\t    toolbar:[{\r\n\t        text:'New',\r\n\t        iconCls:'icon-add',\r\n\t        handler:function(){\r\n\t\t    \tif(listenerFieldsEditCount>0){\r\n\t\t\t\t\t$.messager.alert(\"error\",\"有可编辑的单元格，不能添加\",'error');\r\n\t\t\t\t\treturn;\r\n\t\t\t\t}\r\n\t\t\t\t$('#line-listeners-fields-list').datagrid('appendRow',{\r\n\t\t\t\t\tid:'',\r\n\t\t\t\t\tfieldName:'',\r\n\t\t\t\t\ttype:'',\r\n\t\t\t\t\tvalue:'',\r\n\t\t\t\t\taction:''\r\n\t\t\t\t});\r\n\t\t\t\tvar index = $('#line-listeners-fields-list').datagrid('getRows').length-1;\r\n\t\t\t\t$('#line-listeners-fields-list').datagrid('beginEdit', index);\r\n\t        }\r\n\t    }],\r\n\t\t\r\n\t\tonDblClickRow:function(rowIndex,rowData){\r\n\t\t\teditListenerField(rowIndex);\r\n\t\t},\r\n\t\t\r\n\t\tonBeforeEdit:function(index,row){\r\n\t\t\trow.editing = true;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n");
      out.write("\t\t\tlistenerFieldsEditCount++;\r\n\t\t},\r\n\t\tonAfterEdit:function(index,row){\r\n\t\t\trow.editing = false;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tlistenerFieldsEditCount--;\r\n\t\t},\r\n\t\tonCancelEdit:function(index,row){\r\n\t\t\trow.editing = false;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tlistenerFieldsEditCount--;\r\n\t\t}\r\n\t});\r\n\t$('#fieldSaveBt').linkbutton({\r\n\t\ticonCls:\"icon-save\"\r\n\t});\r\n\t$('#fieldCancelBt').linkbutton({\r\n\t\ticonCls:\"icon-cancel\"\r\n\t});\r\n\t$('#selectListenerServiceClassBt').linkbutton({\r\n\t\ticonCls:\"icon-search\"\r\n\t});\r\n\tpopulateListenerProperties();\r\n});\r\nfunction changeListenerServiceType(obj){\r\n\tif(obj.value=='javaClass'){\r\n\t\t$('#listenerServiceLabel').html('Service Class:');\r\n\t\t$('#listenerServiceClass').show();\r\n\t\t$('#selectListenerServiceClassBt').show();\r\n\t\t$('#listenerServiceExpression').hide();\r\n\t}else if(obj.value=='expression'){\r\n\t\t$('#listenerServiceLabel').html('Expression:');\r\n\t\t$('#listenerServiceClass').hide();\r\n\t\t$('#selectListenerServiceClassBt').hide();\r\n\t\t$('#listenerServiceExpression').show();\r\n");
      out.write("\t}\r\n}\r\nfunction listenerFieldsActionFormatter(value,rowData,rowIndex){\r\n\tvar id = rowIndex;\r\n\tvar s='<img onclick=\"saveListenerField('+id+')\" src=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/image/ok.png\" title=\"'+\"确定\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar c='<img onclick=\"cancelListenerField('+id+')\" src=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/image/cancel.png\" title=\"'+\"取消\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar e='<img onclick=\"editListenerField('+id+')\" src=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/image/modify.png\" title=\"'+\"修改\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar d='<img onclick=\"deleteListenerField('+id+')\" src=\"");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/image/delete.gif\" title=\"'+\"删除\"+'\" style=\"cursor:hand;\"/>';\r\n\tif(rowData.editing)\r\n\t\treturn s;\r\n\telse\r\n\t\treturn e+'&nbsp;'+d;\r\n}\r\nfunction cancelListenerField(id){\r\n\t_line_listener_fields_dg.datagrid('cancelEdit', id);\r\n}\r\nfunction editListenerField(id){\r\n\t_line_listener_fields_dg.datagrid('beginEdit', id);\r\n}\r\nfunction saveListenerField(id){\r\n\t//alert(id);\r\n\t_line_listener_fields_dg.datagrid('endEdit', id);\r\n\t//alert(editcount);\r\n}\r\nfunction deleteListenerField(id){\r\n\t_line_listener_fields_dg.datagrid('deleteRow',id);\r\n\trefreshAllListenerFields();\r\n}\r\nfunction refreshAllListenerFields(){\r\n\tvar rs = _line_listener_fields_dg.datagrid('getRows');\r\n\tfor(var i=0;i<rs.length;i++){\r\n\t\tvar ri =_line_listener_fields_dg.datagrid('getRowIndex',rs[i]);\r\n\t\t_line_listener_fields_dg.datagrid('refreshRow',ri);\r\n\t}\r\n}\r\nfunction createNewListener(){\r\n\tvar newListener = new draw2d.Line.Listener();\r\n    return newListener;   \r\n}\r\nfunction getExsitingListener(){\r\n\tif(listenerId != \"\" && listenerId != null && listenerId!=\"null\"&&listenerId!=\"NULL\"){\r\n");
      out.write("\t\tvar listener = line.getListener(listenerId);\r\n\t\treturn listener;\r\n\t}\r\n}\r\nfunction getListenerFieldsGridChangeRows(){\r\n\tif(listenerFieldsEditCount>0){\r\n\t\t$.messager.alert(\"error\",\"\",'error');\r\n\t\treturn null;\r\n\t}\r\n    var insertRows = _line_listener_fields_dg.datagrid('getChanges','inserted');   \r\n    var updateRows = _line_listener_fields_dg.datagrid('getChanges','updated');   \r\n    var deleteRows = _line_listener_fields_dg.datagrid('getChanges','deleted');   \r\n    var changesRows = {   \r\n            inserted : [],   \r\n            updated : [],\r\n            deleted : []  \r\n            };   \r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {   \r\n            changesRows.inserted.push(insertRows[i]);\r\n        }   \r\n    }   \r\n\r\n    if (updateRows.length>0) {   \r\n        for (var k=0;k<updateRows.length;k++) {   \r\n            changesRows.updated.push(updateRows[k]);\r\n        }   \r\n    }   \r\n       \r\n    if (deleteRows.length>0) {   \r\n        for (var j=0;j<deleteRows.length;j++) {   \r\n");
      out.write("            changesRows.deleted.push(deleteRows[j]);   \r\n        }   \r\n    }\r\n    return changesRows;\r\n}\r\nfunction saveListenerConfig(){\r\n\tif(listenerId != \"\" && listenerId != null && listenerId!=\"null\"&&listenerId!=\"NULL\"){\r\n\t\tvar listener = getExsitingListener();\r\n\t\tvar r = updateExistingListener(listener);\r\n\t\tif(!r)return;\t\t\r\n\t}else{\r\n\t\tvar listener = createNewListener();\r\n\t\tvar r = insertNewListener(listener);\r\n\t\tif(!r)return;\r\n\t}\r\n\t_listener_win.window('close');\r\n}\r\nfunction insertNewListener(listener){\r\n\tlistener.event=_listener_event_type.combobox('getValue');\r\n\t$('input[name=\"listenerServiceType\"]').each(function(i){\r\n\t\tif(this.checked){\r\n\t\t\tlistener.serviceType=this.value;\r\n\t\t\treturn false;\r\n\t\t}\r\n\t});\r\n\tlistener.serviceClass=$('#listenerServiceClass').val();\r\n\tlistener.serviceExpression=$('#listenerServiceExpression').val();\r\n    var changesRows = getListenerFieldsGridChangeRows();\r\n    if(changesRows == null)return false;\r\n    var insertRows = changesRows['inserted'];   \r\n    if (insertRows.length>0) {   \r\n");
      out.write("        for (var i=0;i<insertRows.length;i++) {\r\n            var field = new draw2d.Line.Listener.Field();\r\n    \t\tfield.name=insertRows[i].fieldName;\r\n    \t\tfield.value=insertRows[i].value;\r\n    \t\tfield.type=insertRows[i].type;\r\n    \t\tlistener.fields.add(field);  \r\n        }   \r\n    }\r\n\tline.listeners.add(listener);\r\n\tloadLineListeners();\r\n\treturn true;\r\n}\r\nfunction updateExistingListener(listener){\r\n\tlistener.event=_listener_event_type.combobox('getValue');\r\n\t$('input[name=\"listenerServiceType\"]').each(function(i){\r\n\t\tif(this.checked){\r\n\t\t\tlistener.serviceType=this.value;\r\n\t\t\treturn false;\r\n\t\t}\r\n\t});\r\n\tlistener.serviceClass=$('#listenerServiceClass').val();\r\n\tlistener.serviceExpression=$('#listenerServiceExpression').val();\r\n\tvar changesRows = getListenerFieldsGridChangeRows();\r\n    if(changesRows == null)return false;\r\n    var insertRows = changesRows['inserted'];   \r\n    var updateRows = changesRows['updated'];   \r\n    var deleteRows = changesRows['deleted'];\r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {   \r\n");
      out.write("        \tvar field = new draw2d.Line.Listener.Field();\r\n    \t\tfield.name=insertRows[i].fieldName;\r\n    \t\tfield.value=insertRows[i].value;\r\n    \t\tfield.type=insertRows[i].type;\r\n    \t\tlistener.fields.add(field);\r\n        }   \r\n    }   \r\n\r\n    if (updateRows.length>0) {   \r\n        for (var k=0;k<updateRows.length;k++) {   \r\n        \tvar field = listener.getField(updateRows[k].id);\r\n    \t\tfield.name=updateRows[k].fieldName;\r\n    \t\tfield.value=updateRows[k].value;\r\n    \t\tfield.type=updateRows[k].type;\r\n        }   \r\n    }   \r\n       \r\n    if (deleteRows.length>0) {   \r\n        for (var j=0;j<deleteRows.length;j++) {   \r\n        \tlistener.deleteField(deleteRows[j].id);\r\n        }   \r\n    }\r\n    loadLineListeners();\r\n    return true;\r\n}\r\n\r\nfunction populateListenerProperties(){\r\n\tif(listenerId != \"\" && listenerId != null && listenerId!=\"null\"&&listenerId!=\"NULL\"){\r\n\t\tvar listener = line.getListener(listenerId);\r\n\t\t_listener_event_type.combobox('setValue',listener.event);\r\n\t\tvar serviceType = listener.serviceType;\r\n");
      out.write("\t\t$('input[name=\"listenerServiceType\"]').each(function(i){\r\n\t\t\tif(this.value==serviceType){\r\n\t\t\t\tthis.checked=true;\r\n\t\t\t\tchangeListenerServiceType(this);\r\n\t\t\t\tif(this.value=='javaClass'){\r\n\t\t\t\t\t$('#listenerServiceClass').val(listener.serviceClass);\r\n\t\t\t\t}else if(this.value=='expression'){\r\n\t\t\t\t\t$('#listenerServiceExpression').val(listener.serviceExpression);\r\n\t\t\t\t}\r\n\t\t\t\treturn false;\r\n\t\t\t}\r\n\t\t});\r\n\t\tvar fields = listener.fields;\r\n\t\tvar _listener_fields_grid_data=[];\r\n\t\tfor(var i=0;i<fields.getSize();i++){\r\n\t\t\tvar field = {\r\n\t\t\t\t\tid:fields.get(i).id,\r\n\t\t\t\t\tfieldName:fields.get(i).name,\r\n\t\t\t\t\ttype:fields.get(i).type,\r\n\t\t\t\t\tvalue:fields.get(i).value,\r\n\t\t\t\t\taction:''\r\n\t\t\t\t\t};\r\n\t\t\t_listener_fields_grid_data[i]=field;\r\n\t\t}\r\n\t\t_line_listener_fields_dg.datagrid('loadData',_listener_fields_grid_data);\r\n\t}\r\n}\r\nfunction closeLineListenerWin(){\r\n\t_listener_win.window('close');\r\n}\r\n//-->\r\n</script>\r\n<table>\r\n\t\t<tr>\r\n\t\t\t<td align=\"right\">Event:</td>\r\n\t\t\t<td>\r\n\t\t\t\t<select id=\"listenerEventType\" name=\"listenerEventType\">\r\n\t\t\t\t\t<option value=\"take\">Take</option>\r\n");
      out.write("\t\t\t\t</select>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td></td>\r\n\t\t\t<td align=\"left\">\r\n\t\t\t\t<input type=\"radio\" id=\"listenerServiceType\" name=\"listenerServiceType\" value=\"javaClass\" checked=\"checked\" onclick=\"changeListenerServiceType(this)\">Java Class\r\n\t\t\t\t<input type=\"radio\" id=\"listenerServiceType\" name=\"listenerServiceType\" value=\"expression\" onclick=\"changeListenerServiceType(this)\">Express\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td id=\"listenerServiceLabel\" align=\"right\">Service Class:</td>\r\n\t\t\t<td>\r\n\t\t\t\t<input type=\"text\" id=\"listenerServiceClass\" name=\"listenerServiceClass\" size=\"80\" value=\"\" readonly=\"readonly\"/>\r\n\t\t\t\t<input type=\"text\" id=\"listenerServiceExpression\" name=\"listenerServiceExpression\" size=\"80\" style=\"display: none;\" value=\"\"/>\r\n\t\t\t\t<a href=\"##\" id=\"selectListenerServiceClassBt\" onclick=\"\">Select</a>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"right\">Fields:</td>\r\n\t\t\t<td>\r\n\t\t\t\t<table id=\"line-listeners-fields-list\">\r\n\t\t\t\t\t<thead>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<th field=\"id\" hidden=\"false\"></th>\r\n\t\t\t\t\t<th field=\"fieldName\" width=\"200\" align=\"middle\" sortable=\"false\" editor=\"{\r\n");
      out.write("\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\trequired:true,\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">Field Name</th>\r\n\t\t\t\t\t<th field=\"type\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'combobox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\t\teditable:false,\r\n\t\t\t\t\t\t\tdata:[{id:'string',text:'String',selected:true},{id:'expression',text:'Expression'}],\r\n\t\t\t\t\t\t\tvalueField:'id',\r\n\t\t\t\t\t\t\ttextField:'text'\r\n\t\t\t\t\t}}\">Type</th>\r\n\t\t\t\t\t<th field=\"value\" width=\"200\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">Value</th>\r\n\t\t\t\t\t<th field=\"action\" width=\"80\" align=\"middle\" formatter=\"listenerFieldsActionFormatter\">Action</th>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t</thead>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"2\" align=\"center\">\r\n\t\t\t\t<a href=\"##\" id=\"fieldSaveBt\" onclick=\"saveListenerConfig()\">Save</a>\r\n\t\t\t\t<a href=\"##\" id=\"fieldCancelBt\" onclick=\"closeLineListenerWin()\">Cancel</a>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n</table>");
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
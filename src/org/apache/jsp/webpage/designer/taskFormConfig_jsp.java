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

public final class taskFormConfig_jsp extends HttpJspBase
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

      out.write("\r\n<script type=\"text/javascript\">\r\n<!--\r\nvar formFieldsEditCount = 0;\r\nvar formId = '");
      out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, _jspx_page_context, null, false));
      out.write("';\r\n$(function(){\r\n\t_task_form_fields_dg=$('#task-forms-fields-list').datagrid({\r\n\t\t//title:\"Listener\",\r\n\t\t//url:'");
      out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, _jspx_page_context, null, false));
      out.write("/wf/procdef/procdef!search.action',//\r\n\t\tsingleSelect:true,\r\n\t\twidth:700,\r\n\t\theight:300,\r\n\t\ticonCls:'icon-edit',\r\n\t\t//fit:true,\r\n\t\t//idField:'id',\r\n\t\t//pagination:true,\r\n\t\t//pageSize:15,\r\n\t\t//pageNumber:1,\r\n\t\t//pageList:[10,15],\r\n\t\trownumbers:true,\r\n\t\t//sortName:'id',\r\n\t    //sortOrder:'asc',\r\n\t    striped:true,\r\n\t    toolbar:[{\r\n\t        text:'New',\r\n\t        iconCls:'icon-add',\r\n\t        handler:function(){\r\n\t\t    \tif(formFieldsEditCount>0){\r\n\t\t    \t\t$.messager.alert(\"error\",\"有可编辑的单元格，不能添加\",'error');\r\n\t\t\t\t\treturn;\r\n\t\t\t\t}\r\n\t\t\t\t$('#task-forms-fields-list').datagrid('appendRow',{\r\n\t\t\t\t\tid:'',\r\n\t\t\t\t\tfieldName:'',\r\n\t\t\t\t\ttype:'',\r\n\t\t\t\t\tvalue:'',\r\n\t\t\t\t\texp:'',\r\n\t\t\t\t\tremark:'',\r\n\t\t\t\t\taction:''\r\n\t\t\t\t});\r\n\t\t\t\tvar index = $('#task-forms-fields-list').datagrid('getRows').length-1;\r\n\t\t\t\t$('#task-forms-fields-list').datagrid('beginEdit', index);\r\n\t        }\r\n\t    }],\r\n\t\t\r\n\t\tonDblClickRow:function(rowIndex,rowData){\r\n\t\t\teditFormField(rowIndex);\r\n\t\t},\r\n\t\t\r\n\t\tonBeforeEdit:function(index,row){\r\n\t\t\trow.editing = true;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n");
      out.write("\t\t\tformFieldsEditCount++;\r\n\t\t},\r\n\t\tonAfterEdit:function(index,row){\r\n\t\t\trow.editing = false;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tformFieldsEditCount--;\r\n\t\t},\r\n\t\tonCancelEdit:function(index,row){\r\n\t\t\trow.editing = false;\r\n\t\t\t$(this).datagrid('refreshRow', index);\r\n\t\t\tformFieldsEditCount--;\r\n\t\t}\r\n\t});\r\n\t$('#fieldSaveBt').linkbutton({\r\n\t\ticonCls:\"icon-save\"\r\n\t});\r\n\t$('#fieldCancelBt').linkbutton({\r\n\t\ticonCls:\"icon-cancel\"\r\n\t});\r\n\tpopulateFormProperties();\r\n});\r\n\r\nfunction formFieldsActionFormatter(value,rowData,rowIndex){\r\n\tvar id = rowIndex;\r\n\tvar s='<img onclick=\"saveFormField('+id+')\" src=\"plug-in/designer/img/ok.png\" title=\"'+\"确定\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar c='<img onclick=\"cancelFormField('+id+')\" src=\"plug-in/designer/img/cancel.png\" title=\"'+\"取消\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar e='<img onclick=\"editFormField('+id+')\" src=\"plug-in/designer/img/modify.png\" title=\"'+\"修改\"+'\" style=\"cursor:hand;\"/>';\r\n\tvar d='<img onclick=\"deleteFormField('+id+')\" src=\"plug-in/designer/img/delete.gif\" title=\"'+\"删除\"+'\" style=\"cursor:hand;\"/>';\r\n");
      out.write("\tif(rowData.editing)\r\n\t\treturn s;\r\n\telse\r\n\t\treturn e+'&nbsp;'+d;\r\n}\r\nfunction cancelFormField(id){\r\n\t_task_form_fields_dg.datagrid('cancelEdit', id);\r\n}\r\nfunction editFormField(id){\r\n\t_task_form_fields_dg.datagrid('beginEdit', id);\r\n}\r\nfunction saveFormField(id){\r\n\t//alert(id);\r\n\t_task_form_fields_dg.datagrid('endEdit', id);\r\n\t//alert(editcount);\r\n}\r\nfunction deleteFormField(id){\r\n\t_task_form_fields_dg.datagrid('deleteRow',id);\r\n\trefreshAllFormFields();\r\n}\r\nfunction refreshAllFormFields(){\r\n\tvar rs = _task_form_fields_dg.datagrid('getRows');\r\n\tfor(var i=0;i<rs.length;i++){\r\n\t\tvar ri =_task_form_fields_dg.datagrid('getRowIndex',rs[i]);\r\n\t\t_task_form_fields_dg.datagrid('refreshRow',ri);\r\n\t}\r\n}\r\nfunction createNewForm(){\r\n\tvar newForm = new draw2d.Task.Form();\r\n    return newForm;   \r\n}\r\nfunction getExsitingForm(){\r\n\tif(formId != \"\" && formId != null && formId!=\"null\"&&formId!=\"NULL\"){\r\n\t\tvar form = task.getForm(formId);\r\n\t\treturn form;\r\n\t}\r\n}\r\nfunction getFormFieldsGridChangeRows(){\r\n\tif(formFieldsEditCount>0){\r\n");
      out.write("\t\t$.messager.alert(\"error\",\"\",'error');\r\n\t\treturn null;\r\n\t}\r\n    var insertRows = _task_form_fields_dg.datagrid('getChanges','inserted');   \r\n    var updateRows = _task_form_fields_dg.datagrid('getChanges','updated');   \r\n    var deleteRows = _task_form_fields_dg.datagrid('getChanges','deleted');   \r\n    var changesRows = {   \r\n            inserted : [],   \r\n            updated : [],\r\n            deleted : []  \r\n            };   \r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {   \r\n            changesRows.inserted.push(insertRows[i]);\r\n        }   \r\n    }   \r\n\r\n    if (updateRows.length>0) {   \r\n        for (var k=0;k<updateRows.length;k++) {   \r\n            changesRows.updated.push(updateRows[k]);\r\n        }   \r\n    }   \r\n       \r\n    if (deleteRows.length>0) {   \r\n        for (var j=0;j<deleteRows.length;j++) {   \r\n            changesRows.deleted.push(deleteRows[j]);   \r\n        }   \r\n    }\r\n    return changesRows;\r\n}\r\nfunction saveFormConfig(){\r\n\tif(formId != \"\" && formId != null && formId!=\"null\"&&formId!=\"NULL\"){\r\n");
      out.write("\t\tvar form = getExsitingForm();\r\n\t\tvar r = updateExistingForm(form);\r\n\t\tif(!r)return;\t\t\r\n\t}else{\r\n\t\tvar r = insertNewForm();\r\n\t\tif(!r)return;\r\n\t}\r\n\t_form_win.window('close');\r\n}\r\nfunction insertNewForm(){\r\n    var changesRows = getFormFieldsGridChangeRows();\r\n    if(changesRows == null)return false;\r\n    var insertRows = changesRows['inserted'];   \r\n    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {\r\n        \tvar form = createNewForm();\r\n        \tform.name=insertRows[i].fieldName;\r\n        \tform.value=insertRows[i].value;\r\n        \tform.type=insertRows[i].type;\r\n        \tform.exp=insertRows[i].exp;\r\n        \tform.remark=insertRows[i].remark;\r\n        \ttask.forms.add(form);\r\n       }   \r\n    }\r\n\t\r\n\tloadTaskForms();\r\n\treturn true;\r\n}\r\nfunction updateExistingForm(form){\r\n\t\r\n\tvar changesRows = getFormFieldsGridChangeRows();\r\n    if(changesRows == null)return false;\r\n    var insertRows = changesRows['inserted'];   \r\n    var updateRows = changesRows['updated'];   \r\n    var deleteRows = changesRows['deleted'];\r\n");
      out.write("    if (insertRows.length>0) {   \r\n        for (var i=0;i<insertRows.length;i++) {  \r\n        \tvar formin = createNewForm();\r\n        \tformin.name=insertRows[i].fieldName;\r\n        \tformin.value=insertRows[i].value;\r\n        \tformin.type=insertRows[i].type;\r\n        \tform.exp=insertRows[i].exp;\r\n        \tform.remark=insertRows[i].remark;\r\n    \t\ttask.forms.add(formin);\r\n        }   \r\n    }   \r\n\r\n    if (updateRows.length>0) { \r\n        for (var k=0;k<updateRows.length;k++) { \r\n    \t\tform.name=updateRows[k].fieldName;\r\n    \t\tform.value=updateRows[k].value;\r\n    \t\tform.type=updateRows[k].type;\r\n    \t\tform.exp=updateRows[k].exp;\r\n        \tform.remark=updateRows[k].remark;\r\n        }   \r\n    }   \r\n       \r\n    if (deleteRows.length>0) {   \r\n    \ttask.deleteForm(form.id);\r\n  \t }\r\n    loadTaskForms();\r\n    return true;\r\n}\r\n\r\nfunction populateFormProperties(){\r\n\tif(formId != \"\" && formId != null && formId!=\"null\"&&formId!=\"NULL\"){\r\n\t\tvar form = task.getForm(formId);\r\n\t\tvar _form_fields_grid_data=[];\r\n\t\tif(form!=null){\r\n");
      out.write("\t\t\tvar field = {\r\n\t\t\t\t\tid:form.id,\r\n\t\t\t\t\tfieldName:form.name,\r\n\t\t\t\t\ttype:form.type,\r\n\t\t\t\t\tvalue:form.value,\r\n\t\t\t\t\texp:form.exp,\r\n\t\t        \tremark:form.remark,\r\n\t\t\t\t\taction:''\r\n\t\t\t\t\t};\r\n\t\t\t_form_fields_grid_data[0]=field;\r\n\t\t}\r\n\t\t_task_form_fields_dg.datagrid('loadData',_form_fields_grid_data);\r\n\t}\r\n}\r\nfunction closeTaskFormWin(){\r\n\t_form_win.window('close');\r\n}\r\n//-->\r\n</script>\r\n<table>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table id=\"task-forms-fields-list\">\r\n\t\t\t\t\t<thead>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<th field=\"id\" hidden=\"false\"></th>\r\n\t\t\t\t\t<th field=\"fieldName\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\trequired:true,\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">名称</th>\r\n\t\t\t\t\t<th field=\"type\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'combobox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\t\teditable:false,\r\n\t\t\t\t\t\t\tdata:[{id:'string',text:'String',selected:true},{id:'long',text:'Long'},{id:'boolean',text:'boolean'},{id:'date',text:'Date'},{id:'enum',text:'enum'}],\r\n\t\t\t\t\t\t\tvalueField:'id',\r\n");
      out.write("\t\t\t\t\t\t\ttextField:'text'\r\n\t\t\t\t\t}}\">类型</th>\r\n\t\t\t\t\t<th field=\"value\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">值</th>\r\n\t\t\t\t\t<th field=\"exp\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">表达式</th>\r\n\t\t\t\t\t<th field=\"remark\" width=\"100\" align=\"middle\" sortable=\"false\" editor=\"{\r\n\t\t\t\t\t\ttype:'validatebox',\r\n\t\t\t\t\t\toptions:{\r\n\t\t\t\t\t\tvalidType:'length[1,100]'\r\n\t\t\t\t\t}}\">描述</th>\r\n\t\t\t\t\t<th field=\"action\" width=\"80\" align=\"middle\" formatter=\"formFieldsActionFormatter\">操作</th>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t</thead>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"center\">\r\n\t\t\t\t<a href=\"##\" id=\"fieldSaveBt\" onclick=\"saveFormConfig()\">Save</a>\r\n\t\t\t\t<a href=\"##\" id=\"fieldCancelBt\" onclick=\"closeTaskFormWin()\">Cancel</a>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n</table>");
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
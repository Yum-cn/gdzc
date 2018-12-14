package org.qihuasoft.codegenerate.database;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.qihuasoft.codegenerate.pojo.Columnt;
import org.qihuasoft.codegenerate.pojo.TableConvert;
import org.qihuasoft.codegenerate.util.CodeResourceUtil;

public class JeecgReadTable
{
  private static final Log log = LogFactory.getLog(JeecgReadTable.class);
  private static final long serialVersionUID = -5324160085184088010L;
  private Connection conn;
  private Statement stmt;
  private String sql;
  private ResultSet rs;

  public static void main(String[] args)
    throws SQLException
  {
    try
    {
      List<Columnt> cls = new JeecgReadTable().readTableColumn("person");
      for (Columnt c : cls)
        System.out.println(c.getFieldName());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println(ArrayUtils.toString(new JeecgReadTable().readAllTableNames()));
  }

  public List<String> readAllTableNames()
    throws SQLException
  {
    List tableNames = new ArrayList(0);
    try {
      Class.forName(CodeResourceUtil.DIVER_NAME);
      this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
      this.stmt = this.conn.createStatement(1005, 
        1007);

      if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
        this.sql = MessageFormat.format("select distinct table_name from information_schema.columns where table_schema = {0}", new Object[] { TableConvert.getV(CodeResourceUtil.DATABASE_NAME) });
      }

      if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
        this.sql = " select distinct colstable.table_name as  table_name from user_tab_cols colstable";
      }

      if (CodeResourceUtil.DATABASE_TYPE.equals("postgresql")) {
        this.sql = "SELECT distinct c.relname AS  table_name FROM pg_class c";
      }

      if (CodeResourceUtil.DATABASE_TYPE.equals("sqlserver")) {
        this.sql = "select distinct c.name as  table_name from sys.objects c ";
      }

      this.rs = this.stmt.executeQuery(this.sql);
      while (this.rs.next()) {
        String tableName = this.rs.getString(1);
        tableNames.add(tableName);
      }
    } catch (Exception e) {
      e.printStackTrace();
      try
      {
        if (this.stmt != null) {
          this.stmt.close();
          this.stmt = null;
          System.gc();
        }
        if (this.conn != null) {
          this.conn.close();
          this.conn = null;
          System.gc();
        }
      } catch (SQLException e1) {
        throw e1;
      }
    }
    finally
    {
      try
      {
        if (this.stmt != null) {
          this.stmt.close();
          this.stmt = null;
          System.gc();
        }
        if (this.conn != null) {
          this.conn.close();
          this.conn = null;
          System.gc();
        }
      } catch (SQLException e) {
        throw e;
      }
    }
    return tableNames; } 
  // ERROR //
  public List<Columnt> readTableColumn(String tableName) throws Exception {
	return null; // Byte code:
    //   0: new 113	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 229	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: getstatic 118	org/jeecgframework/codegenerate/util/CodeResourceUtil:DIVER_NAME	Ljava/lang/String;
    //   11: invokestatic 123	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   14: pop
    //   15: aload_0
    //   16: getstatic 129	org/jeecgframework/codegenerate/util/CodeResourceUtil:URL	Ljava/lang/String;
    //   19: getstatic 132	org/jeecgframework/codegenerate/util/CodeResourceUtil:USERNAME	Ljava/lang/String;
    //   22: getstatic 135	org/jeecgframework/codegenerate/util/CodeResourceUtil:PASSWORD	Ljava/lang/String;
    //   25: invokestatic 138	java/sql/DriverManager:getConnection	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
    //   28: putfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   31: aload_0
    //   32: aload_0
    //   33: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   36: sipush 1005
    //   39: sipush 1007
    //   42: invokeinterface 146 3 0
    //   47: putfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   50: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   53: ldc 157
    //   55: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +35 -> 93
    //   61: aload_0
    //   62: ldc 230
    //   64: iconst_2
    //   65: anewarray 3	java/lang/Object
    //   68: dup
    //   69: iconst_0
    //   70: aload_1
    //   71: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   74: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: getstatic 167	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_NAME	Ljava/lang/String;
    //   83: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   86: aastore
    //   87: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   90: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   93: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   96: ldc 184
    //   98: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifeq +26 -> 127
    //   104: aload_0
    //   105: ldc 235
    //   107: iconst_1
    //   108: anewarray 3	java/lang/Object
    //   111: dup
    //   112: iconst_0
    //   113: aload_1
    //   114: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   117: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   120: aastore
    //   121: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   124: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   127: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   130: ldc 188
    //   132: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   135: ifeq +26 -> 161
    //   138: aload_0
    //   139: ldc 237
    //   141: iconst_1
    //   142: anewarray 3	java/lang/Object
    //   145: dup
    //   146: iconst_0
    //   147: aload_1
    //   148: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   151: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   154: aastore
    //   155: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   161: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   164: ldc 192
    //   166: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   169: ifeq +26 -> 195
    //   172: aload_0
    //   173: ldc 242
    //   175: iconst_1
    //   176: anewarray 3	java/lang/Object
    //   179: dup
    //   180: iconst_0
    //   181: aload_1
    //   182: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   185: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   188: aastore
    //   189: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   192: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   195: aload_0
    //   196: aload_0
    //   197: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   200: aload_0
    //   201: getfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   204: invokeinterface 196 2 0
    //   209: putfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   212: aload_0
    //   213: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   216: invokeinterface 244 1 0
    //   221: pop
    //   222: aload_0
    //   223: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   226: invokeinterface 247 1 0
    //   231: istore_3
    //   232: iload_3
    //   233: istore 4
    //   235: iload 4
    //   237: ifle +540 -> 777
    //   240: new 62	org/jeecgframework/codegenerate/pojo/Columnt
    //   243: dup
    //   244: invokespecial 251	org/jeecgframework/codegenerate/pojo/Columnt:<init>	()V
    //   247: astore 5
    //   249: getstatic 252	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_FILED_CONVERT	Z
    //   252: ifeq +27 -> 279
    //   255: aload 5
    //   257: aload_0
    //   258: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   261: iconst_1
    //   262: invokeinterface 204 2 0
    //   267: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   270: invokestatic 256	org/jeecgframework/codegenerate/database/JeecgReadTable:formatField	(Ljava/lang/String;)Ljava/lang/String;
    //   273: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   276: goto +21 -> 297
    //   279: aload 5
    //   281: aload_0
    //   282: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   285: iconst_1
    //   286: invokeinterface 204 2 0
    //   291: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   294: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   297: aload 5
    //   299: aload_0
    //   300: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   303: iconst_1
    //   304: invokeinterface 204 2 0
    //   309: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   312: invokevirtual 262	org/jeecgframework/codegenerate/pojo/Columnt:setFieldDbName	(Ljava/lang/String;)V
    //   315: aload 5
    //   317: aload_0
    //   318: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   321: iconst_2
    //   322: invokeinterface 204 2 0
    //   327: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   330: invokestatic 256	org/jeecgframework/codegenerate/database/JeecgReadTable:formatField	(Ljava/lang/String;)Ljava/lang/String;
    //   333: invokevirtual 265	org/jeecgframework/codegenerate/pojo/Columnt:setFieldType	(Ljava/lang/String;)V
    //   336: aload 5
    //   338: aload_0
    //   339: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   342: iconst_4
    //   343: invokeinterface 204 2 0
    //   348: invokevirtual 268	org/jeecgframework/codegenerate/pojo/Columnt:setPrecision	(Ljava/lang/String;)V
    //   351: aload 5
    //   353: aload_0
    //   354: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   357: iconst_5
    //   358: invokeinterface 204 2 0
    //   363: invokevirtual 271	org/jeecgframework/codegenerate/pojo/Columnt:setScale	(Ljava/lang/String;)V
    //   366: aload 5
    //   368: aload_0
    //   369: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   372: bipush 6
    //   374: invokeinterface 204 2 0
    //   379: invokevirtual 274	org/jeecgframework/codegenerate/pojo/Columnt:setCharmaxLength	(Ljava/lang/String;)V
    //   382: aload 5
    //   384: aload_0
    //   385: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   388: bipush 7
    //   390: invokeinterface 204 2 0
    //   395: invokestatic 277	org/jeecgframework/codegenerate/pojo/TableConvert:getNullAble	(Ljava/lang/String;)Ljava/lang/String;
    //   398: invokevirtual 280	org/jeecgframework/codegenerate/pojo/Columnt:setNullable	(Ljava/lang/String;)V
    //   401: aload_0
    //   402: aload 5
    //   404: invokespecial 283	org/jeecgframework/codegenerate/database/JeecgReadTable:formatFieldClassType	(Lorg/jeecgframework/codegenerate/pojo/Columnt;)V
    //   407: aload 5
    //   409: aload_0
    //   410: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   413: iconst_3
    //   414: invokeinterface 204 2 0
    //   419: invokestatic 287	org/apache/commons/lang/StringUtils:isBlank	(Ljava/lang/String;)Z
    //   422: ifeq +11 -> 433
    //   425: aload 5
    //   427: invokevirtual 70	org/jeecgframework/codegenerate/pojo/Columnt:getFieldName	()Ljava/lang/String;
    //   430: goto +13 -> 443
    //   433: aload_0
    //   434: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   437: iconst_3
    //   438: invokeinterface 204 2 0
    //   443: invokevirtual 293	org/jeecgframework/codegenerate/pojo/Columnt:setFiledComment	(Ljava/lang/String;)V
    //   446: iconst_0
    //   447: anewarray 160	java/lang/String
    //   450: astore 6
    //   452: getstatic 296	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_GENERATE_UI_FILTER_FIELDS	Ljava/lang/String;
    //   455: ifnull +17 -> 472
    //   458: getstatic 296	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_GENERATE_UI_FILTER_FIELDS	Ljava/lang/String;
    //   461: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   464: ldc_w 299
    //   467: invokevirtual 301	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   470: astore 6
    //   472: getstatic 305	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_GENERATE_TABLE_ID	Ljava/lang/String;
    //   475: aload 5
    //   477: invokevirtual 70	org/jeecgframework/codegenerate/pojo/Columnt:getFieldName	()Ljava/lang/String;
    //   480: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   483: ifne +279 -> 762
    //   486: aload 5
    //   488: invokevirtual 308	org/jeecgframework/codegenerate/pojo/Columnt:getFieldDbName	()Ljava/lang/String;
    //   491: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   494: aload 6
    //   496: invokestatic 311	org/jeecgframework/codegenerate/util/CodeStringUtils:isIn	(Ljava/lang/String;[Ljava/lang/String;)Z
    //   499: ifne +263 -> 762
    //   502: aload_2
    //   503: aload 5
    //   505: invokeinterface 210 2 0
    //   510: pop
    //   511: goto +251 -> 762
    //   514: new 62	org/jeecgframework/codegenerate/pojo/Columnt
    //   517: dup
    //   518: invokespecial 251	org/jeecgframework/codegenerate/pojo/Columnt:<init>	()V
    //   521: astore 7
    //   523: getstatic 252	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_FILED_CONVERT	Z
    //   526: ifeq +27 -> 553
    //   529: aload 7
    //   531: aload_0
    //   532: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   535: iconst_1
    //   536: invokeinterface 204 2 0
    //   541: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   544: invokestatic 256	org/jeecgframework/codegenerate/database/JeecgReadTable:formatField	(Ljava/lang/String;)Ljava/lang/String;
    //   547: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   550: goto +21 -> 571
    //   553: aload 7
    //   555: aload_0
    //   556: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   559: iconst_1
    //   560: invokeinterface 204 2 0
    //   565: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   568: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   571: aload 7
    //   573: aload_0
    //   574: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   577: iconst_1
    //   578: invokeinterface 204 2 0
    //   583: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   586: invokevirtual 262	org/jeecgframework/codegenerate/pojo/Columnt:setFieldDbName	(Ljava/lang/String;)V
    //   589: getstatic 305	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_GENERATE_TABLE_ID	Ljava/lang/String;
    //   592: aload 7
    //   594: invokevirtual 70	org/jeecgframework/codegenerate/pojo/Columnt:getFieldName	()Ljava/lang/String;
    //   597: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   600: ifne +162 -> 762
    //   603: aload 7
    //   605: invokevirtual 308	org/jeecgframework/codegenerate/pojo/Columnt:getFieldDbName	()Ljava/lang/String;
    //   608: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   611: aload 6
    //   613: invokestatic 311	org/jeecgframework/codegenerate/util/CodeStringUtils:isIn	(Ljava/lang/String;[Ljava/lang/String;)Z
    //   616: ifeq +6 -> 622
    //   619: goto +143 -> 762
    //   622: aload 7
    //   624: aload_0
    //   625: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   628: iconst_2
    //   629: invokeinterface 204 2 0
    //   634: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   637: invokestatic 256	org/jeecgframework/codegenerate/database/JeecgReadTable:formatField	(Ljava/lang/String;)Ljava/lang/String;
    //   640: invokevirtual 265	org/jeecgframework/codegenerate/pojo/Columnt:setFieldType	(Ljava/lang/String;)V
    //   643: aload 7
    //   645: aload_0
    //   646: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   649: iconst_4
    //   650: invokeinterface 204 2 0
    //   655: invokevirtual 268	org/jeecgframework/codegenerate/pojo/Columnt:setPrecision	(Ljava/lang/String;)V
    //   658: aload 7
    //   660: aload_0
    //   661: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   664: iconst_5
    //   665: invokeinterface 204 2 0
    //   670: invokevirtual 271	org/jeecgframework/codegenerate/pojo/Columnt:setScale	(Ljava/lang/String;)V
    //   673: aload 7
    //   675: aload_0
    //   676: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   679: bipush 6
    //   681: invokeinterface 204 2 0
    //   686: invokevirtual 274	org/jeecgframework/codegenerate/pojo/Columnt:setCharmaxLength	(Ljava/lang/String;)V
    //   689: aload 7
    //   691: aload_0
    //   692: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   695: bipush 7
    //   697: invokeinterface 204 2 0
    //   702: invokestatic 277	org/jeecgframework/codegenerate/pojo/TableConvert:getNullAble	(Ljava/lang/String;)Ljava/lang/String;
    //   705: invokevirtual 280	org/jeecgframework/codegenerate/pojo/Columnt:setNullable	(Ljava/lang/String;)V
    //   708: aload_0
    //   709: aload 7
    //   711: invokespecial 283	org/jeecgframework/codegenerate/database/JeecgReadTable:formatFieldClassType	(Lorg/jeecgframework/codegenerate/pojo/Columnt;)V
    //   714: aload 7
    //   716: aload_0
    //   717: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   720: iconst_3
    //   721: invokeinterface 204 2 0
    //   726: invokestatic 287	org/apache/commons/lang/StringUtils:isBlank	(Ljava/lang/String;)Z
    //   729: ifeq +11 -> 740
    //   732: aload 7
    //   734: invokevirtual 70	org/jeecgframework/codegenerate/pojo/Columnt:getFieldName	()Ljava/lang/String;
    //   737: goto +13 -> 750
    //   740: aload_0
    //   741: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   744: iconst_3
    //   745: invokeinterface 204 2 0
    //   750: invokevirtual 293	org/jeecgframework/codegenerate/pojo/Columnt:setFiledComment	(Ljava/lang/String;)V
    //   753: aload_2
    //   754: aload 7
    //   756: invokeinterface 210 2 0
    //   761: pop
    //   762: aload_0
    //   763: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   766: invokeinterface 317 1 0
    //   771: ifne -257 -> 514
    //   774: goto +81 -> 855
    //   777: new 85	java/lang/Exception
    //   780: dup
    //   781: ldc_w 320
    //   784: invokespecial 322	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   787: athrow
    //   788: astore_3
    //   789: aload_3
    //   790: athrow
    //   791: astore_3
    //   792: aload_3
    //   793: athrow
    //   794: astore 8
    //   796: aload_0
    //   797: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   800: ifnull +20 -> 820
    //   803: aload_0
    //   804: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   807: invokeinterface 215 1 0
    //   812: aload_0
    //   813: aconst_null
    //   814: putfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   817: invokestatic 218	java/lang/System:gc	()V
    //   820: aload_0
    //   821: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   824: ifnull +28 -> 852
    //   827: aload_0
    //   828: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   831: invokeinterface 221 1 0
    //   836: aload_0
    //   837: aconst_null
    //   838: putfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   841: invokestatic 218	java/lang/System:gc	()V
    //   844: goto +8 -> 852
    //   847: astore 9
    //   849: aload 9
    //   851: athrow
    //   852: aload 8
    //   854: athrow
    //   855: aload_0
    //   856: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   859: ifnull +20 -> 879
    //   862: aload_0
    //   863: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   866: invokeinterface 215 1 0
    //   871: aload_0
    //   872: aconst_null
    //   873: putfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   876: invokestatic 218	java/lang/System:gc	()V
    //   879: aload_0
    //   880: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   883: ifnull +28 -> 911
    //   886: aload_0
    //   887: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   890: invokeinterface 221 1 0
    //   895: aload_0
    //   896: aconst_null
    //   897: putfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   900: invokestatic 218	java/lang/System:gc	()V
    //   903: goto +8 -> 911
    //   906: astore 9
    //   908: aload 9
    //   910: athrow
    //   911: new 113	java/util/ArrayList
    //   914: dup
    //   915: invokespecial 229	java/util/ArrayList:<init>	()V
    //   918: astore_3
    //   919: aload_2
    //   920: invokeinterface 324 1 0
    //   925: iconst_1
    //   926: isub
    //   927: istore 4
    //   929: goto +28 -> 957
    //   932: aload_2
    //   933: iload 4
    //   935: invokeinterface 327 2 0
    //   940: checkcast 62	org/jeecgframework/codegenerate/pojo/Columnt
    //   943: astore 5
    //   945: aload_3
    //   946: aload 5
    //   948: invokeinterface 210 2 0
    //   953: pop
    //   954: iinc 4 255
    //   957: iload 4
    //   959: ifge -27 -> 932
    //   962: aload_3
    //   963: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	788	788	java/lang/ClassNotFoundException
    //   8	788	791	java/sql/SQLException
    //   8	794	794	finally
    //   796	844	847	java/sql/SQLException
    //   855	903	906	java/sql/SQLException } 
  }
  // ERROR //
  public List<Columnt> readOriginalTableColumn(String tableName) throws Exception {
	return null; // Byte code:
    //   0: new 113	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 229	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: getstatic 118	org/jeecgframework/codegenerate/util/CodeResourceUtil:DIVER_NAME	Ljava/lang/String;
    //   11: invokestatic 123	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   14: pop
    //   15: aload_0
    //   16: getstatic 129	org/jeecgframework/codegenerate/util/CodeResourceUtil:URL	Ljava/lang/String;
    //   19: getstatic 132	org/jeecgframework/codegenerate/util/CodeResourceUtil:USERNAME	Ljava/lang/String;
    //   22: getstatic 135	org/jeecgframework/codegenerate/util/CodeResourceUtil:PASSWORD	Ljava/lang/String;
    //   25: invokestatic 138	java/sql/DriverManager:getConnection	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
    //   28: putfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   31: aload_0
    //   32: aload_0
    //   33: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   36: sipush 1005
    //   39: sipush 1007
    //   42: invokeinterface 146 3 0
    //   47: putfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   50: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   53: ldc 157
    //   55: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +35 -> 93
    //   61: aload_0
    //   62: ldc 230
    //   64: iconst_2
    //   65: anewarray 3	java/lang/Object
    //   68: dup
    //   69: iconst_0
    //   70: aload_1
    //   71: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   74: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: getstatic 167	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_NAME	Ljava/lang/String;
    //   83: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   86: aastore
    //   87: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   90: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   93: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   96: ldc 184
    //   98: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifeq +26 -> 127
    //   104: aload_0
    //   105: ldc 235
    //   107: iconst_1
    //   108: anewarray 3	java/lang/Object
    //   111: dup
    //   112: iconst_0
    //   113: aload_1
    //   114: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   117: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   120: aastore
    //   121: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   124: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   127: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   130: ldc 188
    //   132: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   135: ifeq +26 -> 161
    //   138: aload_0
    //   139: ldc 237
    //   141: iconst_1
    //   142: anewarray 3	java/lang/Object
    //   145: dup
    //   146: iconst_0
    //   147: aload_1
    //   148: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   151: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   154: aastore
    //   155: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   158: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   161: getstatic 154	org/jeecgframework/codegenerate/util/CodeResourceUtil:DATABASE_TYPE	Ljava/lang/String;
    //   164: ldc 192
    //   166: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   169: ifeq +26 -> 195
    //   172: aload_0
    //   173: ldc 242
    //   175: iconst_1
    //   176: anewarray 3	java/lang/Object
    //   179: dup
    //   180: iconst_0
    //   181: aload_1
    //   182: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   185: invokestatic 170	org/jeecgframework/codegenerate/pojo/TableConvert:getV	(Ljava/lang/String;)Ljava/lang/String;
    //   188: aastore
    //   189: invokestatic 176	java/text/MessageFormat:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   192: putfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   195: aload_0
    //   196: aload_0
    //   197: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   200: aload_0
    //   201: getfield 182	org/jeecgframework/codegenerate/database/JeecgReadTable:sql	Ljava/lang/String;
    //   204: invokeinterface 196 2 0
    //   209: putfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   212: aload_0
    //   213: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   216: invokeinterface 244 1 0
    //   221: pop
    //   222: aload_0
    //   223: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   226: invokeinterface 247 1 0
    //   231: istore_3
    //   232: iload_3
    //   233: istore 4
    //   235: iload 4
    //   237: ifle +491 -> 728
    //   240: new 62	org/jeecgframework/codegenerate/pojo/Columnt
    //   243: dup
    //   244: invokespecial 251	org/jeecgframework/codegenerate/pojo/Columnt:<init>	()V
    //   247: astore 5
    //   249: getstatic 252	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_FILED_CONVERT	Z
    //   252: ifeq +27 -> 279
    //   255: aload 5
    //   257: aload_0
    //   258: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   261: iconst_1
    //   262: invokeinterface 204 2 0
    //   267: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   270: invokestatic 256	org/jeecgframework/codegenerate/database/JeecgReadTable:formatField	(Ljava/lang/String;)Ljava/lang/String;
    //   273: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   276: goto +21 -> 297
    //   279: aload 5
    //   281: aload_0
    //   282: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   285: iconst_1
    //   286: invokeinterface 204 2 0
    //   291: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   294: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   297: aload 5
    //   299: aload_0
    //   300: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   303: iconst_1
    //   304: invokeinterface 204 2 0
    //   309: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   312: invokevirtual 262	org/jeecgframework/codegenerate/pojo/Columnt:setFieldDbName	(Ljava/lang/String;)V
    //   315: aload 5
    //   317: aload_0
    //   318: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   321: iconst_4
    //   322: invokeinterface 204 2 0
    //   327: invokestatic 345	org/jeecgframework/codegenerate/pojo/TableConvert:getNullString	(Ljava/lang/String;)Ljava/lang/String;
    //   330: invokevirtual 268	org/jeecgframework/codegenerate/pojo/Columnt:setPrecision	(Ljava/lang/String;)V
    //   333: aload 5
    //   335: aload_0
    //   336: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   339: iconst_5
    //   340: invokeinterface 204 2 0
    //   345: invokestatic 345	org/jeecgframework/codegenerate/pojo/TableConvert:getNullString	(Ljava/lang/String;)Ljava/lang/String;
    //   348: invokevirtual 271	org/jeecgframework/codegenerate/pojo/Columnt:setScale	(Ljava/lang/String;)V
    //   351: aload 5
    //   353: aload_0
    //   354: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   357: bipush 6
    //   359: invokeinterface 204 2 0
    //   364: invokestatic 345	org/jeecgframework/codegenerate/pojo/TableConvert:getNullString	(Ljava/lang/String;)Ljava/lang/String;
    //   367: invokevirtual 274	org/jeecgframework/codegenerate/pojo/Columnt:setCharmaxLength	(Ljava/lang/String;)V
    //   370: aload 5
    //   372: aload_0
    //   373: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   376: bipush 7
    //   378: invokeinterface 204 2 0
    //   383: invokestatic 277	org/jeecgframework/codegenerate/pojo/TableConvert:getNullAble	(Ljava/lang/String;)Ljava/lang/String;
    //   386: invokevirtual 280	org/jeecgframework/codegenerate/pojo/Columnt:setNullable	(Ljava/lang/String;)V
    //   389: aload 5
    //   391: aload_0
    //   392: aload_0
    //   393: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   396: iconst_2
    //   397: invokeinterface 204 2 0
    //   402: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   405: aload 5
    //   407: invokevirtual 348	org/jeecgframework/codegenerate/pojo/Columnt:getPrecision	()Ljava/lang/String;
    //   410: aload 5
    //   412: invokevirtual 351	org/jeecgframework/codegenerate/pojo/Columnt:getScale	()Ljava/lang/String;
    //   415: invokespecial 354	org/jeecgframework/codegenerate/database/JeecgReadTable:formatDataType	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   418: invokevirtual 265	org/jeecgframework/codegenerate/pojo/Columnt:setFieldType	(Ljava/lang/String;)V
    //   421: aload_0
    //   422: aload 5
    //   424: invokespecial 283	org/jeecgframework/codegenerate/database/JeecgReadTable:formatFieldClassType	(Lorg/jeecgframework/codegenerate/pojo/Columnt;)V
    //   427: aload 5
    //   429: aload_0
    //   430: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   433: iconst_3
    //   434: invokeinterface 204 2 0
    //   439: invokestatic 287	org/apache/commons/lang/StringUtils:isBlank	(Ljava/lang/String;)Z
    //   442: ifeq +11 -> 453
    //   445: aload 5
    //   447: invokevirtual 70	org/jeecgframework/codegenerate/pojo/Columnt:getFieldName	()Ljava/lang/String;
    //   450: goto +13 -> 463
    //   453: aload_0
    //   454: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   457: iconst_3
    //   458: invokeinterface 204 2 0
    //   463: invokevirtual 293	org/jeecgframework/codegenerate/pojo/Columnt:setFiledComment	(Ljava/lang/String;)V
    //   466: aload_2
    //   467: aload 5
    //   469: invokeinterface 210 2 0
    //   474: pop
    //   475: goto +238 -> 713
    //   478: new 62	org/jeecgframework/codegenerate/pojo/Columnt
    //   481: dup
    //   482: invokespecial 251	org/jeecgframework/codegenerate/pojo/Columnt:<init>	()V
    //   485: astore 6
    //   487: getstatic 252	org/jeecgframework/codegenerate/util/CodeResourceUtil:JEECG_FILED_CONVERT	Z
    //   490: ifeq +27 -> 517
    //   493: aload 6
    //   495: aload_0
    //   496: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   499: iconst_1
    //   500: invokeinterface 204 2 0
    //   505: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   508: invokestatic 256	org/jeecgframework/codegenerate/database/JeecgReadTable:formatField	(Ljava/lang/String;)Ljava/lang/String;
    //   511: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   514: goto +21 -> 535
    //   517: aload 6
    //   519: aload_0
    //   520: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   523: iconst_1
    //   524: invokeinterface 204 2 0
    //   529: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   532: invokevirtual 259	org/jeecgframework/codegenerate/pojo/Columnt:setFieldName	(Ljava/lang/String;)V
    //   535: aload 6
    //   537: aload_0
    //   538: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   541: iconst_1
    //   542: invokeinterface 204 2 0
    //   547: invokevirtual 232	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   550: invokevirtual 262	org/jeecgframework/codegenerate/pojo/Columnt:setFieldDbName	(Ljava/lang/String;)V
    //   553: aload 6
    //   555: aload_0
    //   556: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   559: iconst_4
    //   560: invokeinterface 204 2 0
    //   565: invokestatic 345	org/jeecgframework/codegenerate/pojo/TableConvert:getNullString	(Ljava/lang/String;)Ljava/lang/String;
    //   568: invokevirtual 268	org/jeecgframework/codegenerate/pojo/Columnt:setPrecision	(Ljava/lang/String;)V
    //   571: aload 6
    //   573: aload_0
    //   574: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   577: iconst_5
    //   578: invokeinterface 204 2 0
    //   583: invokestatic 345	org/jeecgframework/codegenerate/pojo/TableConvert:getNullString	(Ljava/lang/String;)Ljava/lang/String;
    //   586: invokevirtual 271	org/jeecgframework/codegenerate/pojo/Columnt:setScale	(Ljava/lang/String;)V
    //   589: aload 6
    //   591: aload_0
    //   592: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   595: bipush 6
    //   597: invokeinterface 204 2 0
    //   602: invokestatic 345	org/jeecgframework/codegenerate/pojo/TableConvert:getNullString	(Ljava/lang/String;)Ljava/lang/String;
    //   605: invokevirtual 274	org/jeecgframework/codegenerate/pojo/Columnt:setCharmaxLength	(Ljava/lang/String;)V
    //   608: aload 6
    //   610: aload_0
    //   611: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   614: bipush 7
    //   616: invokeinterface 204 2 0
    //   621: invokestatic 277	org/jeecgframework/codegenerate/pojo/TableConvert:getNullAble	(Ljava/lang/String;)Ljava/lang/String;
    //   624: invokevirtual 280	org/jeecgframework/codegenerate/pojo/Columnt:setNullable	(Ljava/lang/String;)V
    //   627: aload 6
    //   629: aload_0
    //   630: aload_0
    //   631: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   634: iconst_2
    //   635: invokeinterface 204 2 0
    //   640: invokevirtual 239	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   643: aload 6
    //   645: invokevirtual 348	org/jeecgframework/codegenerate/pojo/Columnt:getPrecision	()Ljava/lang/String;
    //   648: aload 6
    //   650: invokevirtual 351	org/jeecgframework/codegenerate/pojo/Columnt:getScale	()Ljava/lang/String;
    //   653: invokespecial 354	org/jeecgframework/codegenerate/database/JeecgReadTable:formatDataType	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   656: invokevirtual 265	org/jeecgframework/codegenerate/pojo/Columnt:setFieldType	(Ljava/lang/String;)V
    //   659: aload_0
    //   660: aload 6
    //   662: invokespecial 283	org/jeecgframework/codegenerate/database/JeecgReadTable:formatFieldClassType	(Lorg/jeecgframework/codegenerate/pojo/Columnt;)V
    //   665: aload 6
    //   667: aload_0
    //   668: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   671: iconst_3
    //   672: invokeinterface 204 2 0
    //   677: invokestatic 287	org/apache/commons/lang/StringUtils:isBlank	(Ljava/lang/String;)Z
    //   680: ifeq +11 -> 691
    //   683: aload 6
    //   685: invokevirtual 70	org/jeecgframework/codegenerate/pojo/Columnt:getFieldName	()Ljava/lang/String;
    //   688: goto +13 -> 701
    //   691: aload_0
    //   692: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   695: iconst_3
    //   696: invokeinterface 204 2 0
    //   701: invokevirtual 293	org/jeecgframework/codegenerate/pojo/Columnt:setFiledComment	(Ljava/lang/String;)V
    //   704: aload_2
    //   705: aload 6
    //   707: invokeinterface 210 2 0
    //   712: pop
    //   713: aload_0
    //   714: getfield 202	org/jeecgframework/codegenerate/database/JeecgReadTable:rs	Ljava/sql/ResultSet;
    //   717: invokeinterface 317 1 0
    //   722: ifne -244 -> 478
    //   725: goto +81 -> 806
    //   728: new 85	java/lang/Exception
    //   731: dup
    //   732: ldc_w 320
    //   735: invokespecial 322	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   738: athrow
    //   739: astore_3
    //   740: aload_3
    //   741: athrow
    //   742: astore_3
    //   743: aload_3
    //   744: athrow
    //   745: astore 7
    //   747: aload_0
    //   748: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   751: ifnull +20 -> 771
    //   754: aload_0
    //   755: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   758: invokeinterface 215 1 0
    //   763: aload_0
    //   764: aconst_null
    //   765: putfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   768: invokestatic 218	java/lang/System:gc	()V
    //   771: aload_0
    //   772: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   775: ifnull +28 -> 803
    //   778: aload_0
    //   779: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   782: invokeinterface 221 1 0
    //   787: aload_0
    //   788: aconst_null
    //   789: putfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   792: invokestatic 218	java/lang/System:gc	()V
    //   795: goto +8 -> 803
    //   798: astore 8
    //   800: aload 8
    //   802: athrow
    //   803: aload 7
    //   805: athrow
    //   806: aload_0
    //   807: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   810: ifnull +20 -> 830
    //   813: aload_0
    //   814: getfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   817: invokeinterface 215 1 0
    //   822: aload_0
    //   823: aconst_null
    //   824: putfield 152	org/jeecgframework/codegenerate/database/JeecgReadTable:stmt	Ljava/sql/Statement;
    //   827: invokestatic 218	java/lang/System:gc	()V
    //   830: aload_0
    //   831: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   834: ifnull +28 -> 862
    //   837: aload_0
    //   838: getfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   841: invokeinterface 221 1 0
    //   846: aload_0
    //   847: aconst_null
    //   848: putfield 144	org/jeecgframework/codegenerate/database/JeecgReadTable:conn	Ljava/sql/Connection;
    //   851: invokestatic 218	java/lang/System:gc	()V
    //   854: goto +8 -> 862
    //   857: astore 8
    //   859: aload 8
    //   861: athrow
    //   862: new 113	java/util/ArrayList
    //   865: dup
    //   866: invokespecial 229	java/util/ArrayList:<init>	()V
    //   869: astore_3
    //   870: aload_2
    //   871: invokeinterface 324 1 0
    //   876: iconst_1
    //   877: isub
    //   878: istore 4
    //   880: goto +28 -> 908
    //   883: aload_2
    //   884: iload 4
    //   886: invokeinterface 327 2 0
    //   891: checkcast 62	org/jeecgframework/codegenerate/pojo/Columnt
    //   894: astore 5
    //   896: aload_3
    //   897: aload 5
    //   899: invokeinterface 210 2 0
    //   904: pop
    //   905: iinc 4 255
    //   908: iload 4
    //   910: ifge -27 -> 883
    //   913: aload_3
    //   914: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	739	739	java/lang/ClassNotFoundException
    //   8	739	742	java/sql/SQLException
    //   8	745	745	finally
    //   747	795	798	java/sql/SQLException
    //   806	854	857	java/sql/SQLException } 
  }
  public static String formatField(String field) { String[] strs = field.split("_");
    field = "";
    int m = 0; for (int length = strs.length; m < length; m++) {
      if (m > 0) {
        String tempStr = strs[m].toLowerCase();
        tempStr = tempStr.substring(0, 1).toUpperCase() + 
          tempStr.substring(1, tempStr.length());
        field = field + tempStr;
      } else {
        field = field + strs[m].toLowerCase();
      }
    }
    return field;
  }

  public static String formatFieldCapital(String field)
  {
    String[] strs = field.split("_");
    field = "";
    int m = 0; for (int length = strs.length; m < length; m++) {
      if (m > 0) {
        String tempStr = strs[m].toLowerCase();
        tempStr = tempStr.substring(0, 1).toUpperCase() + 
          tempStr.substring(1, tempStr.length());
        field = field + tempStr;
      } else {
        field = field + strs[m].toLowerCase();
      }
    }
    field = field.substring(0, 1).toUpperCase() + field.substring(1);
    return field;
  }

  public boolean checkTableExist(String tableName)
  {
    try
    {
      System.out.println("数据库驱动: " + CodeResourceUtil.DIVER_NAME);
      Class.forName(CodeResourceUtil.DIVER_NAME);
      this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
      this.stmt = this.conn.createStatement(1005, 
        1007);

      if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
        this.sql = 
          ("select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '" + 
          tableName.toUpperCase() + "'" + 
          " and table_schema = '" + CodeResourceUtil.DATABASE_NAME + "'");
      }

      if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
        this.sql = 
          ("select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = '" + 
          tableName.toUpperCase() + 
          "'");
      }

      if (CodeResourceUtil.DATABASE_TYPE.equals("postgresql")) {
        this.sql = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", new Object[] { TableConvert.getV(tableName.toLowerCase()) });
      }
      if (CodeResourceUtil.DATABASE_TYPE.equals("sqlserver")) {
        this.sql = MessageFormat.format("select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type='''U''' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}", new Object[] { TableConvert.getV(tableName.toLowerCase()) });
      }

      this.rs = this.stmt.executeQuery(this.sql);
      this.rs.last();
      int fieldNum = this.rs.getRow();
      if (fieldNum > 0)
        return true;
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }

  private void formatFieldClassType(Columnt columnt)
  {
    String fieldType = columnt.getFieldType();
    String scale = columnt.getScale();

    columnt.setClassType("inputxt");

    if ("N".equals(columnt.getNullable())) {
      columnt.setOptionType("*");
    }
    if (("datetime".equals(fieldType)) || (fieldType.contains("time")))
      columnt.setClassType("easyui-datetimebox");
    else if ("date".equals(fieldType))
      columnt.setClassType("easyui-datebox");
    else if (fieldType.contains("int"))
      columnt.setOptionType("n");
    else if ("number".equals(fieldType)) {
      if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0))
        columnt.setOptionType("d");
    }
    else if (("float".equals(fieldType)) || ("double".equals(fieldType)) || ("decimal".equals(fieldType)))
      columnt.setOptionType("d");
    else if ("numeric".equals(fieldType))
      columnt.setOptionType("d");
  }

  private String formatDataType(String dataType, String precision, String scale)
  {
    if (dataType.contains("char"))
      dataType = "java.lang.String";
    else if (dataType.contains("int"))
      dataType = "java.lang.Integer";
    else if (dataType.contains("float"))
      dataType = "java.lang.Float";
    else if (dataType.contains("double"))
      dataType = "java.lang.Double";
    else if (dataType.contains("number")) {
      if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0))
        dataType = "java.math.BigDecimal";
      else if ((StringUtils.isNotBlank(precision)) && (Integer.parseInt(precision) > 10))
        dataType = "java.lang.Long";
      else
        dataType = "java.lang.Integer";
    }
    else if (dataType.contains("decimal"))
      dataType = "BigDecimal";
    else if (dataType.contains("date"))
      dataType = "java.util.Date";
    else if (dataType.contains("time"))
    {
      dataType = "java.util.Date";
    } else if (dataType.contains("blob"))
      dataType = "byte[]";
    else if (dataType.contains("clob"))
      dataType = "java.sql.Clob";
    else if (dataType.contains("numeric"))
      dataType = "BigDecimal";
    else {
      dataType = "java.lang.Object";
    }
    return dataType;
  }
}
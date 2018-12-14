package org.qihuasoft.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class FileDeal
{
  public static boolean moveFile(String strPathFrom, String strPathTo, String[] name)
  {
    try
    {
      File[] file0 = new File[name.length];
      File[] file1 = new File[name.length];
      for (int i = 0; i < name.length; i++)
      {
        file0[i] = new File(strPathFrom, name[i]);
        file1[i] = new File(strPathTo, name[i]);
        if (file0[i].exists())
          file0[i].renameTo(file1[i]);
      }
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean moveFile(String strPathFrom, String strPathTo)
  {
    try
    {
      File pathfrom = new File(strPathFrom);
      if (!isExistDir(strPathTo)) {
        creatDir(strPathTo);
      }
      String[] name = pathfrom.list();
      File[] file0 = new File[name.length];
      File[] file1 = new File[name.length];
      for (int i = 0; i < name.length; i++)
      {
        file0[i] = new File(strPathFrom, name[i]);
        file1[i] = new File(strPathTo, name[i]);
        if (file0[i].isFile()) {
          file0[i].renameTo(file1[i]);
        }
        else if (file0[i].isDirectory())
          moveFile(strPathFrom + "/" + name[i], strPathTo + "/" + name[i]);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static void moveDirectory(String strPathFrom, String strPathTo)
  {
    moveFile(strPathFrom, strPathTo);
    deleteFile(strPathFrom);
  }

  public static boolean deleteFile(String strPath, String[] name)
  {
    try
    {
      for (int i = 0; i < name.length; i++)
      {
        File file = new File(strPath, name[i]);
        if (file.exists())
          file.delete();
      }
    }
    catch (Exception e)
    {
      return false;
    }
    return true;
  }

  public static void deleteFile(String fullpathname)
  {
    File tempFile = new File(fullpathname);
    if (tempFile.isFile()) {
      tempFile.delete();
    }
    else if (tempFile.isDirectory()) {
      String[] xmlfileName = tempFile.list();
      for (int i = 0; i < xmlfileName.length; i++) {
        String xmlFilePath = fullpathname + "/" + xmlfileName[i];
        deleteFile(xmlFilePath);
      }
      tempFile.delete();
    }
  }

  public static boolean isExistDir(String sDirName)
  {
    File fileDir = new File(sDirName);

    if (fileDir.exists()) {
      return true;
    }

    return false;
  }

  public static boolean creatDir(String sDirName)
  {
    File fileDir = new File(sDirName);

    if (fileDir == null) {
      return false;
    }

    if (fileDir.isFile()) {
      return false;
    }

    if (!fileDir.exists()) {
      if (fileDir.mkdirs()) {
        return true;
      }

      return false;
    }

    return false;
  }

  public static String getFileExt(String sFileName)
  {
    if ((sFileName == null) || ("".equals(sFileName))) {
      return "";
    }
    return sFileName.substring(sFileName.lastIndexOf(".") + 1, sFileName.length());
  }

  public static String getFileName(String sFileName)
  {
    if ((sFileName == null) || ("".equals(sFileName))) {
      return "";
    }
    return sFileName.substring(0, sFileName.lastIndexOf("."));
  }

  public static String getFullFileName(String sFileName)
  {
    if ((sFileName == null) || ("".equals(sFileName))) {
      return "";
    }
    return sFileName.substring(sFileName.lastIndexOf("/") + 1, sFileName.length());
  }

  public static boolean isContainsFileType(String sFileExt, String[] sFileType)
  {
    if (sFileExt == null) {
      return false;
    }

    String sExt = sFileExt.toLowerCase();
    for (int i = 0; i < sFileType.length; i++) {
      if (sExt.equals(sFileType[i].toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isContainsFileType(String sFileExt)
  {
    if (sFileExt == null) {
      return false;
    }

    String[] sFileType = { 
      "asf", "avi", "bmp", "chm", "css", "doc", "dot", "exe", "file", "fla", "gif", 
      "gsp", "htm", "html", "id", "jpg", "jpeg", "log", "mid", "midi", 
      "mp3", "mpg", "pdf", "pic", "ppt", "pot", "psd", "ra", "rar", "rm", "rtf", 
      "shtml", "swf", "txt", "wav", "wma", "wmv", "xls", "zip" };
    String sExt = sFileExt.toLowerCase();
    for (int i = 0; i < sFileType.length; i++) {
      if (sExt.equals(sFileType[i].toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public static Vector getSubFolders(String parentFolderPath)
  {
    File parent = new File(parentFolderPath);
    Vector data = new Vector();
    if (parent.isDirectory()) {
      String[] subfile = parent.list();
      for (int i = 0; i < subfile.length; i++) {
        File tempfile = new File(parentFolderPath + "\\" + subfile[i]);
        if (tempfile.isDirectory()) {
          data.addElement(subfile[i]);
        }
      }
    }
    return data;
  }

  public static Vector getSubFiles(String parentFolderPath)
  {
    File parent = new File(parentFolderPath);
    Vector data = new Vector();
    if (parent.isDirectory()) {
      String[] subfile = parent.list();
      for (int i = 0; i < subfile.length; i++) {
        File tempfile = new File(parentFolderPath + "\\" + subfile[i]);
        if (tempfile.isFile()) {
          data.addElement(subfile[i]);
        }
      }
    }
    return data;
  }

  public static int copyFile(String source_name, String dest_name, int type)
    throws IOException
  {
      File source_file = new File(source_name);
      File dest_file = new File(dest_name);
      String destination_fileName = (new StringBuilder(String.valueOf(dest_name.substring(0, dest_name.indexOf(dest_file.getName()))))).append(source_file.getName()).toString();
      File destination_file = new File(destination_fileName);
      FileInputStream source = null;
      FileOutputStream destination = null;
      int result = 0;
      try
      {
          if(!source_file.exists() || !source_file.isFile() || !source_file.canRead())
          {
              throw new IOException((new StringBuilder("\u6E90\u6587\u4EF6\u8BFB\u5199\u9519\u8BEF: ")).append(source_name).toString());
          }
          if(destination_file.exists())
          {
              if(destination_file.isFile())
              {
                  if(type == 1)
                  {
                      destination_file.delete();
                      result = 1;
                  } else
                  if(type == 2)
                  {
                      String NewDesc_Name = dest_name.substring(0, dest_name.length() - 4);
                      String EndName = dest_name.substring(dest_name.length() - 3);
                      NewDesc_Name = (new StringBuilder(String.valueOf(NewDesc_Name))).append((new SimpleDateFormat("yyyymmddssSSS")).format(new Date())).append(".").append(EndName).toString();
                      destination_file = new File(NewDesc_Name);
                      result = 2;
                  } else
                  {
                      result = 3;
                  }
              } else
              {
                  throw new IOException((new StringBuilder("\u76EE\u6807\u6587\u4EF6\u8BFB\u5199\u9519\u8BEF:")).append(dest_name).toString());
              }
          } else
          {
              File parentdir = parent(destination_file);
              if(!parentdir.exists() || !parentdir.canWrite())
              {
                  throw new IOException((new StringBuilder("\u76EE\u6807\u6587\u4EF6\u8BFB\u5199\u9519\u8BEF:")).append(dest_name).toString());
              }
          }
          source = new FileInputStream(source_file);
          destination = new FileOutputStream(destination_file);
          byte buffer[] = new byte[1024];
          do
          {
              int bytes_read = source.read(buffer);
              if(bytes_read == -1)
              {
                  break;
              }
              destination.write(buffer, 0, bytes_read);
          } while(true);
      }
      finally
      {
          if(source != null)
          {
              try
              {
                  source.close();
              }
              catch(IOException ioexception) { }
          }
          if(destination != null)
          {
              try
              {
                  destination.close();
              }
              catch(IOException ioexception1) { }
          }
          if(!dest_file.getPath().equals(destination_file.getPath()) && dest_file.exists())
          {
              dest_file.delete();
          }
          destination_file.renameTo(dest_file);
          return result;
      }
  }

  private static File parent(File f)
  {
    String dirname = f.getParent();
    if (dirname == null) {
      if (f.isAbsolute()) {
        return new File(File.separator);
      }

      return new File(System.getProperty("user.dir"));
    }

    return new File(dirname);
  }

  public static int getFileByte(String filepath)
  {
    File f = new File(filepath);
    try {
      FileInputStream fis = new FileInputStream(f);
      try {
        return fis.available();
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }
    }
    catch (FileNotFoundException e2) {
      e2.printStackTrace();
    }
    return 0;
  }

  public static void main(String[] args)
  {
    FileDeal fileDeal = new FileDeal();
    try {
      for (int i = 2; i < 10; i++)
      {
        copyFile("E:/film/00.rmvb", "d:/" + String.valueOf(i) + ".rmvb", 2);
      }
    }
    catch (Exception localException)
    {
    }
  }
}
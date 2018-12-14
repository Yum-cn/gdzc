package org.qihuasoft.core.aop;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

public class GZipFilter implements Filter
{
  private static final Logger logger = Logger.getLogger(GZipFilter.class);
  private static final String STATIC_TEMPLATE_SOURCE = "online/template";
  private static final String STATIC_TEMPLATE_SOURCE_2 = "clzcontext/template";
  private static final String STATIC_TEMPLATE_SOURCE_3 = "/content/";
  private static final String STATIC_TEMPLATE_SOURCE_4 = "/plug-in-ui/";
  private static final String NO_FILTER = ".do";
  private static final String DIAN = ".";

  public void destroy()
  {
  }

  public void init(FilterConfig arg0)
    throws ServletException
  {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletResponse resp = (HttpServletResponse)response;
    HttpServletRequest req = (HttpServletRequest)request;
    String url = req.getRequestURI();
    String path = req.getContextPath();
    String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;

    if (((req.getRequestURI().indexOf("online/template") != -1) || 
      (req.getRequestURI().indexOf("clzcontext/template") != -1) || 
      (req.getRequestURI().indexOf("/content/") != -1) || 
      (req.getRequestURI().indexOf("/plug-in-ui/") != -1)) && (req.getRequestURI().indexOf(".") != -1) && (req.getRequestURI().indexOf(".do") == -1)) {
      if (url.startsWith(req.getContextPath())) {
        url = url.replaceFirst(req.getContextPath(), "");
      }
      response.reset();
      String s = ResMime.get(url.substring(url.lastIndexOf(".")).replace(".", ""));
      if (s != null) response.setContentType(s);
      OutputStream os = null;
      InputStream is = null;
      try
      {
        is = getClass().getResourceAsStream(url);
        if (is != null) {
          os = response.getOutputStream();
          FileCopyUtils.copy(is, os);
        }
        else {
          chain.doFilter(request, response);
        }
      } catch (IOException e) {
        e.printStackTrace();

        if (os != null) {
          try {
            os.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
        if (is == null) return;
        try {
          is.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
      finally
      {
        if (os != null) {
          try {
            os.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        if (is != null)
          try {
            is.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
      try
      {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else
    {
      chain.doFilter(request, response);
    }
  }
}
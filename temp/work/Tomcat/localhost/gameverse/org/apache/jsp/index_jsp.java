/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.105
 * Generated at: 2025-07-11 05:38:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.openxava.naviox.util.OrganizationsCurrent;
import org.openxava.web.Browsers;
import org.openxava.util.Users;
import com.openxava.naviox.impl.SignInHelper;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("jar:file:/C:/Users/panch/Validacion_Gameverse/target/gameverse/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt-1_0.tld", Long.valueOf(1153403082000L));
    _jspx_dependants.put("/../xava/imports.jsp", Long.valueOf(1748045506000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1752209820857L));
    _jspx_dependants.put("/WEB-INF/openxava.tld", Long.valueOf(1748045506000L));
    _jspx_dependants.put("jar:file:/C:/Users/panch/Validacion_Gameverse/target/gameverse/WEB-INF/lib/jstl-1.2.jar!/META-INF/c-1_0.tld", Long.valueOf(1153403082000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(6);
    _jspx_imports_classes.add("org.openxava.web.Browsers");
    _jspx_imports_classes.add("org.openxava.util.Users");
    _jspx_imports_classes.add("com.openxava.naviox.util.OrganizationsCurrent");
    _jspx_imports_classes.add("com.openxava.naviox.impl.SignInHelper");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      com.openxava.naviox.Modules modules = null;
      synchronized (session) {
        modules = (com.openxava.naviox.Modules) _jspx_page_context.getAttribute("modules", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (modules == null){
          modules = new com.openxava.naviox.Modules();
          _jspx_page_context.setAttribute("modules", modules, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");

String signInURL = SignInHelper.getSignInURL(); 
if (Users.getCurrent() != null || OrganizationsCurrent.get(request) != null) {
	String module = Users.getCurrent() == null?"SignIn":modules.getCurrent(request);
	String url = Browsers.isMobile(request) && !"Index".equals(modules.getCurrent(request))?"phone":"m/" + module;
	if (signInURL != null && Users.getCurrent() == null && session.getAttribute("naviox.originalURL") == null) {
		session.setAttribute("naviox.originalURL", request.getContextPath());
		url = request.getContextPath() + signInURL;
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" ");
      if (_jspx_meth_xava_005fnonce_005f0(_jspx_page_context))
        return;
      out.write(">\r\n");
      out.write("window.location=\"");
      out.print(url);
      out.write("\";\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");

}
else {
	if (signInURL != null && session.getAttribute("naviox.originalURL") == null) {
		session.setAttribute("naviox.originalURL", request.getContextPath());
		
      out.write("\r\n");
      out.write("		<script type=\"text/javascript\" ");
      if (_jspx_meth_xava_005fnonce_005f1(_jspx_page_context))
        return;
      out.write(">\r\n");
      out.write("		window.location=\"");
      out.print(request.getContextPath());
      out.print(signInURL);
      out.write("\";\r\n");
      out.write("		</script>\r\n");
      out.write("		");
 
	}
	else {
		
      out.write("\r\n");
      out.write("		");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "naviox/welcome.jsp", out, false);
      out.write("\r\n");
      out.write("		");
	
	}
}

      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_xava_005fnonce_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  xava:nonce
    org.openxava.web.taglib.NonceTag _jspx_th_xava_005fnonce_005f0 = (org.openxava.web.taglib.NonceTag) _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody.get(org.openxava.web.taglib.NonceTag.class);
    _jspx_th_xava_005fnonce_005f0.setPageContext(_jspx_page_context);
    _jspx_th_xava_005fnonce_005f0.setParent(null);
    int _jspx_eval_xava_005fnonce_005f0 = _jspx_th_xava_005fnonce_005f0.doStartTag();
    if (_jspx_th_xava_005fnonce_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      return true;
    }
    _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody.reuse(_jspx_th_xava_005fnonce_005f0);
    return false;
  }

  private boolean _jspx_meth_xava_005fnonce_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  xava:nonce
    org.openxava.web.taglib.NonceTag _jspx_th_xava_005fnonce_005f1 = (org.openxava.web.taglib.NonceTag) _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody.get(org.openxava.web.taglib.NonceTag.class);
    _jspx_th_xava_005fnonce_005f1.setPageContext(_jspx_page_context);
    _jspx_th_xava_005fnonce_005f1.setParent(null);
    int _jspx_eval_xava_005fnonce_005f1 = _jspx_th_xava_005fnonce_005f1.doStartTag();
    if (_jspx_th_xava_005fnonce_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      return true;
    }
    _005fjspx_005ftagPool_005fxava_005fnonce_005fnobody.reuse(_jspx_th_xava_005fnonce_005f1);
    return false;
  }
}

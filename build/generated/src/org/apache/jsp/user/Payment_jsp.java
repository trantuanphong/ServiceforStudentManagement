package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Payment_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("        <style>\n");
      out.write("            .customtable{\n");
      out.write("                border-collapse: separate;\n");
      out.write("                border-spacing: 10px;\n");
      out.write("            }\n");
      out.write("            a {\n");
      out.write("                color: orange;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body >\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../header.jsp", out, false);
      out.write("\n");
      out.write("        <!--displaying content-->\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row d-flex align-items-center\">\n");
      out.write("                <!--payment-->\n");
      out.write("                <div class=\"col-sm-6\">\n");
      out.write("                    <div class=\"panel panel-primary\">\n");
      out.write("                        <!--heading-->\n");
      out.write("                        <div class=\"panel-heading\">\n");
      out.write("                            <ul class=\"nav nav-tabs\" role=\"tablist\">\n");
      out.write("                                <li class=\"navbar-brand\">Payment</li>\n");
      out.write("                                <li role=\"presentation\" class=\"active\">\n");
      out.write("                                    <a href=\"#topUp\" role=\"tab\" aria-controls=\"topUp\" data-toggle=\"tab\">Top-Up</a>\n");
      out.write("                                </li>\n");
      out.write("                                <li role=\"presentation\">\n");
      out.write("                                    <a href=\"#trade\" role=\"tab\" aria-controls=\"trade\" data-toggle=\"tab\">Trade</a>\n");
      out.write("                                </li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!--body-->\n");
      out.write("                        <div class=\"panel-body\">\n");
      out.write("                            <div class=\"tab-content\">\n");
      out.write("                                <!--top-up-->\n");
      out.write("                                <div role=\"tabpanel\" class=\"tab-pane active\" id=\"topUp\">\n");
      out.write("                                    <form action=\"\">\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"amount\">Amount:</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"amount\" placeholder=\"Enter amount\" name=\"amount\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"pwd\">Password:</label>\n");
      out.write("                                            <input type=\"password\" class=\"form-control\" id=\"pwd\" placeholder=\"Enter password\" name=\"pwd\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"rePwd\">Re-password:</label>\n");
      out.write("                                            <input type=\"password\" class=\"form-control\" id=\"rePwd\" placeholder=\"Re-enter password\" name=\"rePwd\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div style=\"text-align: center\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn-success\">Top-up</button>\n");
      out.write("                                            <button type=\"reset\" class=\"btn-warning\">Clear</button>\n");
      out.write("                                        </div>\n");
      out.write("                                    </form>\n");
      out.write("                                </div>\n");
      out.write("                                <!--trade-->\n");
      out.write("                                <div role=\"tabpanel\" class=\"tab-pane\" id=\"trade\">\n");
      out.write("                                    <form action=\"\">\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"receiver\">Receiver:</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"receiver\" placeholder=\"Enter receiver name\" name=\"receiver\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"amount\">Amount:</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"amount\" placeholder=\"Enter amount\" name=\"amount\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"content\">Content:</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"content\" placeholder=\"Enter content\" name=\"content\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"pwd\">Password:</label>\n");
      out.write("                                            <input type=\"password\" class=\"form-control\" id=\"pwd\" placeholder=\"Enter password\" name=\"pwd\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"rePwd\">Re-password:</label>\n");
      out.write("                                            <input type=\"password\" class=\"form-control\" id=\"rePwd\" placeholder=\"Re-enter password\" name=\"rePwd\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div style=\"text-align: center\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn-success\">Send</button>\n");
      out.write("                                            <button type=\"reset\" class=\"btn-warning\">Clear</button>\n");
      out.write("                                        </div>\n");
      out.write("                                    </form>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <!--history-->\n");
      out.write("                <div class=\"col-sm-6\">\n");
      out.write("                    <div class=\"panel panel-primary\">\n");
      out.write("                        <div class=\"panel-heading text-center\">\n");
      out.write("                            <h3>History</h3>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"panel-body\">\n");
      out.write("                            <table class=\"table table-hover\">\n");
      out.write("                                <thead>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <th>Number</th>\n");
      out.write("                                        <th>Amount</th>\n");
      out.write("                                        <th>Purpose</th>\n");
      out.write("                                        <th>Date</th>\n");
      out.write("                                    </tr>\n");
      out.write("                                </thead>\n");
      out.write("                                <tbody>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <td>1</td>\n");
      out.write("                                        <td>1000</td>\n");
      out.write("                                        <td>Top-up</td>\n");
      out.write("                                        <td>20/10/2010</td>\n");
      out.write("                                    </tr>\n");
      out.write("                                </tbody>\n");
      out.write("                            </table>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

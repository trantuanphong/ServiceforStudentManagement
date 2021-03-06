package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ListFeedBack_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Student Service Manager</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"container-fluid row\">\n");
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/functionBar.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"col-sm-10\">\n");
      out.write("                <!--Filter-->\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"navbar col-sm-5 navbar-right text-center\" style=\"padding-top: 7px;margin-right: 5px; background-color: #337ab7; color: white\">\n");
      out.write("                        <form class=\"form-inline\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"type\">Filter: </label>\n");
      out.write("                                <select class=\"form-control\" id=\"type\">\n");
      out.write("                                    <option>All</option>\n");
      out.write("                                    <option>Feedback</option>\n");
      out.write("                                    <option>Report</option>\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"input-group\" class=\"text-center\">\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Enter account here\">\n");
      out.write("                                <div class=\"input-group-btn\">\n");
      out.write("                                    <button class=\"btn btn-default\" type=\"submit\">\n");
      out.write("                                        <i class=\"glyphicon glyphicon-search\"></i>\n");
      out.write("                                    </button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <!--Table data-->\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"panel panel-primary\">\n");
      out.write("                        <div class=\"panel-heading\">\n");
      out.write("                            <h3 style=\"text-align: center\">FEEDBACK/REPORT LIST</h3>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"panel-body\">\n");
      out.write("                            <table class=\"table table-hover\">\n");
      out.write("                                <thead>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <th>Number</th>\n");
      out.write("                                        <th>Report ID</th>\n");
      out.write("                                        <th>Account ID</th>\n");
      out.write("                                        <th>Detail</th>                 \n");
      out.write("                                        <th style=\"text-align: center\">Action</th>\n");
      out.write("                                    </tr>\n");
      out.write("                                </thead>\n");
      out.write("                                <tbody>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <td>1</td>\n");
      out.write("                                        <td>R1</td>\n");
      out.write("                                        <td>AC1</td>\n");
      out.write("                                        <td>Thai do phuc vu cua nhan vien qua te</td>\n");
      out.write("                                        <td style=\"text-align: center\">\n");
      out.write("                                            <input type=\"submit\" class=\"btn btn-success\" value=\"Send\"></input>\n");
      out.write("                                            <input type=\"submit\" class=\"btn btn-danger\" value=\"Delete\"></input>\n");
      out.write("                                        </td>\n");
      out.write("                                    </tr>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <td>2</td>\n");
      out.write("                                        <td>R2</td>\n");
      out.write("                                        <td>AC2</td>\n");
      out.write("                                        <td>Gia ca qua cao</td>\n");
      out.write("                                        <td style=\"text-align: center\">\n");
      out.write("                                            <input type=\"submit\" class=\"btn btn-success\" value=\"Send\"></input>\n");
      out.write("                                            <input type=\"submit\" class=\"btn btn-danger\" value=\"Delete\"></input>\n");
      out.write("                                        </td>\n");
      out.write("                                    </tr>               \n");
      out.write("                                </tbody>\n");
      out.write("                            </table>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
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

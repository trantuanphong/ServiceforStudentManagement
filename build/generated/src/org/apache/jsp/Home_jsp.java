package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <style>\n");
      out.write("            img {\n");
      out.write("                width: 100%;\n");
      out.write("                height: 300px!important;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"container-fluid row\">\n");
      out.write("            <div class=\"col-sm-1\"></div>\n");
      out.write("            \n");
      out.write("            <div class=\"col-sm-7\">\n");
      out.write("                \n");
      out.write("                <!--image-->\n");
      out.write("                <div id=\"img-carousel\" data-ride=\"carousel\" class=\"carousel slide\">\n");
      out.write("                    <!-- Indicators-->\n");
      out.write("                    <ol class=\"carousel-indicators\">\n");
      out.write("                        <li data-target=\"#img-carousel\" data-slide-to=\"0\" class=\"active\"></li>\n");
      out.write("                        <li data-target=\"#img-carousel\" data-slide-to=\"1\"></li>\n");
      out.write("                        <li data-target=\"#img-carousel\" data-slide-to=\"2\"></li>\n");
      out.write("                    </ol>\n");
      out.write("                    <!-- Wrapper for slides-->\n");
      out.write("                    <div role=\"listbox\" class=\"carousel-inner\">\n");
      out.write("                        <div class=\"item active\">\n");
      out.write("                            <img src=\"src/image/1.png\" alt=\"\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"item\">\n");
      out.write("                            <img src=\"src/image/2.png\" alt=\"\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"item\">\n");
      out.write("                            <img src=\"src/image/3.png\" alt=\"\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!-- Controls--><a href=\"#img-carousel\" role=\"button\" data-slide=\"prev\" class=\"left carousel-control\"><i aria-hidden=\"true\" class=\"fa fa-chevron-left\"></i><span class=\"sr-only\">Previous</span></a><a href=\"#img-carousel\" role=\"button\" data-slide=\"next\" class=\"right carousel-control\"><i aria-hidden=\"true\" class=\"fa fa-chevron-right\"></i><span class=\"sr-only\">Next</span></a>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <br/>\n");
      out.write("                \n");
      out.write("                <!--text-->\n");
      out.write("                <div class=\"panel panel-default\">\n");
      out.write("                    <div class=\"panel-heading\">\n");
      out.write("                        <h4>ABOUT US</h4>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <p>Student management is the job of managing all processes and study of students in universities also to improve on information technology. Student management in the major universities is the management of the learning process, including all student records and grades during the course of study at the university are stored in the \"Student Management\". In management students have many points, there are many subjects and scores of multiple examinations.</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"panel panel-default\">\n");
      out.write("                    <div class=\"panel-heading\">\n");
      out.write("                        <h4>OUR BENEFIT</h4>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <p>Developing student management program to support management of name, date of birth, and score. The problem is that analyzing the design of the information system poses a problem as to why it must be managed. And how to manage the j and manage how to work effectively, saving time for employees.</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"panel panel-default\">\n");
      out.write("                    <div class=\"panel-heading\">\n");
      out.write("                        <h4>TESTIMONIAL</h4>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <p>Student management in the major universities is the management of the learning process, including all student records and grades during the course of study at the university are stored in the \"Student Management\".</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "informationBar.jsp", out, false);
      out.write("\n");
      out.write("            \n");
      out.write("        </div>           \n");
      out.write("        \n");
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

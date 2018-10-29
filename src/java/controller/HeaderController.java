/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import email.SendMail;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;

/**
 *
 * @author Phong
 */
public class HeaderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("action") != null) {
//                get action command
                switch (request.getParameter("action")) {
                    case "login": {
                        login(request, response);
                        break;
                    }
                    case "register": {
                        register(request, response);
                        break;
                    }
                    case "forgotPassword": {
                        forgotPassword(request, response);
                        break;
                    }
                }
            } else {
//                log out
                HttpSession session = request.getSession();
                session.setAttribute("account", null);
                response.sendRedirect("/ServiceforStudentManagement/Home.jsp");
            }

        } catch (Exception ex) {
            Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void forgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String mail = request.getParameter("txtEmailForgot");
        String username = request.getParameter("txtUsernameForgot");
        Account acc = new AccountDAO().selectAccountByName(username).get(0);
        if (acc.getEmail().equals(mail)) {
            new SendMail().sendMailChangePassword(mail, acc.getPassword());
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=successGetPassword");
        } else {
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=forgotError");
        }

    }

    void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //get data
            String username = request.getParameter("txtAccountNameRegis");
            if (new AccountDAO().selectAccountByName(username).size() == 0) {
                String roleNumber = request.getParameter("roleNumber");
                if (new AccountDAO().selectAccount("SELECT * FROM Account WHERE RoleNumber LIKE '" + roleNumber + "'").size() == 0) {
                    String password = request.getParameter("pwdRegis");
                    String fullname = request.getParameter("txtNameRegis");
                    String phone = request.getParameter("phoneRegis");
                    String date = request.getParameter("txtDateRegis");
                    String mail = request.getParameter("txtEmailRegis");
                    String address = request.getParameter("txtAddress");
                    new AccountDAO().insertAccount(username, password, mail, fullname, roleNumber, address, phone, date, 1);
                    response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=successRegis");
                    return;
                }
            }
        } catch (Exception ex) {
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=regisError");
            return;
        }
        response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=regisError");
    }

    void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //get data
            String username = request.getParameter("username");
            String password = request.getParameter("pwd");
            List<Account> list = new AccountDAO().selectAccountByName(username);
            //validate
            if (!list.isEmpty()) {
                Account acc = list.get(0);
                if (acc.getPassword().equals(password)
                        && acc.getStatus().equals("Actived")) {
                    //init session
                    HttpSession session = request.getSession();
                    session.setAttribute("account", acc);
                    //redirect current page
                    response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1]);
                    return;
                }
            }
            //redirect current link with param error
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=loginError");
            return;
        } catch (Exception ex) {
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=loginError");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

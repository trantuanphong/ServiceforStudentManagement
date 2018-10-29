/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Service;
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
import model.ServiceDAO;

/**
 *
 * @author Phong
 */
public class AdminController extends HttpServlet {

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
            if (request.getParameter("action") != null) {
                switch (request.getParameter("action")) {
                    case "newAccount": {
                        newAccount(request, response);
                        break;
                    }
                    case "changeStatusAccount": {
                        changeStatusAccount(request, response);
                        break;
                    }
                    case "deleteAccount": {
                        deleteAccount(request, response);
                        break;
                    }
                    case "resetPassword": {
                        resetPassword(request, response);
                        break;
                    }
                    case "acceptService": {
                        acceptService(request, response);
                        break;
                    }
                    case "clear": {
                        clear(request, response);
                        break;
                    }
                    case "changeStatusService": {
                        changeStatusService(request, response);
                        break;
                    }
                    case "rejectService": {
                        rejectService(request, response);
                        break;
                    }
                }
            }
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

    private void changeStatusService(HttpServletRequest request, HttpServletResponse response) {
        try {
            String serviceID = request.getParameter("serviceID");
            ServiceDAO sdao = new ServiceDAO();
            Service ser = sdao.selectServiceByID(Integer.parseInt(serviceID)).get(0);
            sdao.setServiceStatus(Integer.parseInt(serviceID), ser.getStatus().equals("Banned") ? "Actived" : "Banned");
            response.sendRedirect("/ServiceforStudentManagement/admin/ListService.jsp");
        } catch (Exception ex) {
        }
    }

    private void changeStatusAccount(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accountName = request.getParameter("accountName");
            AccountDAO adao = new AccountDAO();
            Account acc = adao.selectAccountByName(accountName).get(0);
            adao.setAccountStatusByName(accountName, acc.getStatus().equals("Banned") ? "Actived" : "Banned");
            response.sendRedirect("/ServiceforStudentManagement/admin/ListAccount.jsp");
        } catch (Exception ex) {
        }
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountName = request.getParameter("accountName");
        AccountDAO adao = new AccountDAO();
        adao.deleteAccount(accountName);
        response.sendRedirect("/ServiceforStudentManagement/admin/ListAccount.jsp");
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accountName = request.getParameter("accountName");
            AccountDAO adao = new AccountDAO();
            adao.setAccountPasswordByName(accountName, "123456");
            response.sendRedirect("/ServiceforStudentManagement/admin/ListAccount.jsp");
        } catch (Exception ex) {
        }
    }

    private Boolean validusername(String accname) throws Exception {
        if (accname.length() < 6) {
            return false;
        }
        AccountDAO adao = new AccountDAO();
        List<Account> all = adao.selectAllAccount();
        for (Account i : all) {
            if (i.getAccountName().equals(accname)) {
                return false;
            }
        }
        return true;
    }

    private boolean validrepassword(String pwd, String repwd) {
        if (pwd.equals(repwd)) {
            return true;
        }
        return false;
    }

    private boolean validpassword(String pwd) {
        if (pwd.length() >= 6) {
            return true;
        }
        return false;
    }

    private boolean validphone(String phone) {
        if (phone.equals("")) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean validinput(String a, String b, String c, String d) {
        return (!a.equals("") && !a.equals(null)
                && !b.equals("") && !b.equals(null)
                && !c.equals("") && !c.equals(null)
                && !d.equals("") && !d.equals(null));
    }

    private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("usernameerror");
        session.removeAttribute("passworderror");
        session.removeAttribute("repassworderror");
        session.removeAttribute("phoneerror");
        session.removeAttribute("emailerror");
        session.removeAttribute("addresserror");
        session.removeAttribute("un");
        session.removeAttribute("fn");
        session.removeAttribute("rn");
        session.removeAttribute("em");
        session.removeAttribute("ph");
        session.removeAttribute("da");
        session.removeAttribute("add");
        response.sendRedirect("/ServiceforStudentManagement/admin/NewAccount.jsp");
    }

    private void acceptService(HttpServletRequest request, HttpServletResponse response) {
        try {
            int serviceID = Integer.valueOf(request.getParameter("serviceID"));
            new ServiceDAO().setServiceStatus(serviceID, "Actived");
            response.sendRedirect("/ServiceforStudentManagement/admin/ListService.jsp");
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void newAccount(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("usernameerror");
            session.removeAttribute("passworderror");
            session.removeAttribute("repassworderror");
            session.removeAttribute("phoneerror");
            session.removeAttribute("emailerror");
            session.removeAttribute("addresserror");
            String accname = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String pwd = request.getParameter("pwd");
            String repwd = request.getParameter("repwd");
            String rolenum = request.getParameter("roleNumber");
            if ("".equals(rolenum)) {
                rolenum = null;
            }
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String date = request.getParameter("date");
            String type = request.getParameter("type");
            AccountDAO adao = new AccountDAO();
            int t = 2;
            if (type.equals("admin")) {
                t = 3;
            } else if (type.equals("student")) {
                t = 1;
            }
            if ((validusername(accname) && validpassword(pwd) && validrepassword(pwd, repwd) && validphone(phone))
                    && validinput(accname, fullname, email, address)) {

                adao.insertAccount(accname, pwd, email, fullname, rolenum, address, phone, date, t);
                
                session.removeAttribute("un");
                session.removeAttribute("fn");
                session.removeAttribute("rn");
                session.removeAttribute("em");
                session.removeAttribute("ph");
                session.removeAttribute("da");
                session.removeAttribute("add");
                response.sendRedirect("/ServiceforStudentManagement/admin/ListAccount.jsp");
            } else {
                if (!validusername(accname)) {
                    session.setAttribute("usernameerror", "User name không khả dụng");
                }
                if (!validpassword(pwd)) {
                    session.setAttribute("passworderror", "Password phải có trên 6 kí tự");
                }
                if (!validrepassword(pwd, repwd)) {
                    session.setAttribute("repassworderror", "Password không trùng khớp");
                }
                if (!validphone(phone)) {
                    session.setAttribute("phoneerror", "Số điện thoại không hợp lệ");
                }
                if (email.equals("")) {
                    session.setAttribute("emailerror", "Email không hợp lệ");
                }
                if (address.equals("")) {
                    session.setAttribute("addresserror", "Địa chỉ không hợp lệ");
                }
                session.setAttribute("un", accname);
                session.setAttribute("fn", fullname);
                session.setAttribute("rn", rolenum);
                session.setAttribute("em", email);
                session.setAttribute("ph", phone);
                session.setAttribute("add", address);
                session.setAttribute("da", date);
                response.sendRedirect("/ServiceforStudentManagement/admin/NewAccount.jsp");
            }
        } catch (Exception e) {
        }
    }

    private void rejectService(HttpServletRequest request, HttpServletResponse response) {
        try {
            int serviceID = Integer.valueOf(request.getParameter("serviceID"));
            new ServiceDAO().deleteService(serviceID);
            response.sendRedirect("/ServiceforStudentManagement/admin/ListService.jsp");
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Wallet;
import model.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BuyBalanceDAO;
import model.WalletDAO;

/**
 *
 * @author Phong
 */
public class AccountController extends HttpServlet {

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
                    case "changePassword": {
                        changePassword(request, response);
                        break;
                    }
                    case "topUp": {
                        topUp(request, response);
                        break;
                    }
                    case "updateInformation": {
                        updateInformation(request, response);
                        break;
                    }
                    case "payment": {
                        payment(request, response);
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

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String oldPass = request.getParameter("txtOldPwd");
        String newPass = request.getParameter("txtNewPwd");
        Account c = (Account) session.getAttribute("account");
        c = (Account) session.getAttribute("account");
        try {
            List<Account> a = new AccountDAO().selectAccount("select * from Account where AccountName = '" + c.getAccountName() + "'");
            if (!a.get(0).getPassword().equals(oldPass)) {
                response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=changePasswordError");
            }
            new AccountDAO().setAccountPasswordByName(a.get(0).getAccountName(), newPass);
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1]);
        } catch (Exception ex) {
            System.out.println("Change password error");
        }
    }

    private void topUp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Account c = (Account) session.getAttribute("account");
        c = (Account) session.getAttribute("account");
        String amount = request.getParameter("amount");
        String password = request.getParameter("pwd");
        String repass = request.getParameter("rePwd");
        Calendar cal = Calendar.getInstance();
        try {
            List<Account> a = new AccountDAO().selectAccount("select * from Account where AccountName = '" + c.getAccountName() + "'");
            if (!a.get(0).getPassword().equals(password)) {
                response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=transError");
                return;
            }
            new BuyBalanceDAO().insertBuyBalance(a.get(0).getAccountName(), a.get(0).getWalletID(), "Topup", Integer.parseInt(amount));
            List<Wallet> w = new WalletDAO().selectWallet("select * from Wallet where WalletID = " + a.get(0).getWalletID());
            new WalletDAO().setBalance(a.get(0).getWalletID(), w.get(0).getBalance() + Integer.parseInt(amount));
            response.sendRedirect("/ServiceforStudentManagement/user/MyProfile.jsp");
        } catch (Exception ex) {
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=transError");
            return;
        }
    }

    private void updateInformation(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accountName = ((Account) request.getSession().getAttribute("account")).getAccountName();
            String fullname = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            Date date = new SimpleDateFormat("yyyy-dd-MM").parse(request.getParameter("txtDOB"));
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            new AccountDAO().setAccountProfileByName(email, fullname, address, phone, date, accountName);
            Account acc = new AccountDAO().selectAccountByName(accountName).get(0);
            HttpSession session = request.getSession();
            session.setAttribute("account", acc);
            response.sendRedirect("/ServiceforStudentManagement/user/MyProfile.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void payment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account c = (Account) session.getAttribute("account");
        c = (Account) session.getAttribute("account");
        String receiveName = request.getParameter("receiver");
        String amount = request.getParameter("amount");
        String purpose = request.getParameter("content");
        String password = request.getParameter("pwd");
        try {
            List<Account> a = new AccountDAO().selectAccount("select * from Account where AccountName = '" + c.getAccountName() + "'");
            List<Account> b = new AccountDAO().selectAccount("select * from Account where AccountName = '" + receiveName + "'");
            if (!a.get(0).getPassword().equals(password)
                    || b.isEmpty()) {
                response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=transError");
                return;
            }
            new BuyBalanceDAO().insertBuyBalance(a.get(0).getAccountName(), a.get(0).getWalletID(), purpose, Integer.parseInt(amount));
            List<Wallet> w = new WalletDAO().selectWallet("select * from Wallet where WalletID = " + a.get(0).getWalletID());
            new WalletDAO().setBalance(a.get(0).getWalletID(), w.get(0).getBalance() - Integer.parseInt(amount));

            new BuyBalanceDAO().insertBuyBalance(b.get(0).getAccountName(), b.get(0).getWalletID(), purpose, Integer.parseInt(amount));
            List<Wallet> w1 = new WalletDAO().selectWallet("select * from Wallet where WalletID = " + b.get(0).getWalletID());
            new WalletDAO().setBalance(b.get(0).getWalletID(), w1.get(0).getBalance() + Integer.parseInt(amount));
            response.sendRedirect("/ServiceforStudentManagement/user/MyProfile.jsp");
        } catch (Exception ex) {
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=transError");
            return;
        }
    }

}

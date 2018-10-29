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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductDAO;
import model.ServiceDAO;

/**
 *
 * @author Phong
 */
public class ProviderController extends HttpServlet {

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
                    case "newProduct": {
                        newProduct(request, response);
                        break;
                    }
                    case "registerService": {
                        registerService(request, response);
                        break;
                    }
                    case "clear": {
                        clear(request, response);
                        break;
                    }
                    case "updateProduct": {
                        update(request, response);
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

    private void newProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("priceerror");
            session.removeAttribute("quantityerror");
            session.removeAttribute("uniterror");
            session.removeAttribute("productnameerror");
            session.removeAttribute("deserror");
            ServiceDAO sdao = new ServiceDAO();
            String productname = request.getParameter("txtProductName");
            String quantity = request.getParameter("txtQuantity");
            String price = request.getParameter("txtPrice");
            String unit = request.getParameter("txtUnit");
            String des = request.getParameter("txtDescription");
            String serviceid = request.getParameter("drServiceName");
            String providername = sdao.getProviderNameByServiceID(Integer.valueOf(serviceid));
            if (validnumber(quantity) && validnumber(price)
                    && validString(productname) && validString(unit) && validString(des)) {
                session.removeAttribute("proname");
                session.removeAttribute("proprice");
                session.removeAttribute("prounit");
                session.removeAttribute("proquantity");
                ProductDAO pdao = new ProductDAO();
                pdao.insertProduct(productname, Integer.valueOf(serviceid), providername, des, Integer.valueOf(quantity), Integer.valueOf(price), unit);
                response.sendRedirect("/ServiceforStudentManagement/provider/ListProduct.jsp");
            } else {
                if (!validnumber(price)) {
                    session.setAttribute("priceerror", "Giá tiền không hợp lệ");
                }
                if (!validnumber(quantity)) {
                    session.setAttribute("quantityerror", "Số lượng không hợp lệ");
                }
                if (!validString(unit)) {
                    session.setAttribute("uniterror", "Số lượng không hợp lệ");
                }
                if (!validString(productname)) {
                    session.setAttribute("productnameerror", "Tên sản phẩm không hợp lệ");
                }
                if (!validString(des)) {
                    session.setAttribute("deserror", "Chi tiết không hợp lệ");
                }
                session.setAttribute("proname", productname);
                session.setAttribute("proprice", price);
                session.setAttribute("prounit", unit);
                session.setAttribute("proquantity", quantity);
                response.sendRedirect("/ServiceforStudentManagement/provider/NewProduct.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registerService(HttpServletRequest request, HttpServletResponse response) {
        try {
            String serviceName = request.getParameter("txtServiceName");
            String description = request.getParameter("txtServiceDescription");
            if (new ServiceDAO().selectServiceByName(serviceName).size() > 0) {
                response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=registerError");
            } else {
                String providerName = ((Account) request.getSession().getAttribute("account")).getAccountName();
                new ServiceDAO().insertService(new Service(serviceName, providerName, description, new Date(), "Register"));
                response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1]);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProviderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validnumber(String s) {
        if (s.equals(null) || s.equals("") ) {
            return false;
        }
        try {
            int i = Integer.valueOf(s);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validString(String s) {
        if (s.equals(null) || s.equals("")) {
            return false;
        }
        return true;
    }

    private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("priceerror");
        session.removeAttribute("quantityerror");
        session.removeAttribute("uniterror");
        session.removeAttribute("productnameerror");
        session.removeAttribute("deserror");
        session.removeAttribute("proname");
        session.removeAttribute("proprice");
        session.removeAttribute("prounit");
        session.removeAttribute("proquantity");
        response.sendRedirect("/ServiceforStudentManagement/provider/NewProduct.jsp");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int productID = Integer.valueOf(request.getParameter("productID"));
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            int price = Integer.valueOf(request.getParameter("price"));
            new ProductDAO().updateProduct(productID, productName, price, quantity, description);
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1]);
        } catch (Exception ex) {
            response.sendRedirect("/ServiceforStudentManagement" + request.getParameter("link").split("ServiceforStudentManagement")[1] + "?error=updateError");
        }

    }
}

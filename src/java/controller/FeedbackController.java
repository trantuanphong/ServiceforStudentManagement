/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FeedbackDAO;

/**
 *
 * @author Phong
 */
public class FeedbackController extends HttpServlet {

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
                    case "newFeedback": {
                        newFeedback(request, response);
                        break;
                    }
                    case "deleteFeedback": {
                        deleteFeedback(request, response);
                        break;
                    }
                    case "seenFeedback": {
                        seenFeedback(request, response);
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void newFeedback(HttpServletRequest request, HttpServletResponse response) {
        try {
            String receiver = request.getParameter("receiver");
            if (receiver == null) {
                receiver = "pscruby0";
            }
            String sender = request.getParameter("sender");
            String subject = request.getParameter("subject");
            String detail = request.getParameter("detail");
            new FeedbackDAO().insertFeedback(new Feedback(receiver, sender, subject, detail, Calendar.getInstance().getTime(), "Not Seen"));
            response.sendRedirect("/ServiceforStudentManagement/Home.jsp");
        } catch (Exception ex) {
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int feedbackID = Integer.valueOf(request.getParameter("feedbackID"));
            new FeedbackDAO().deleteFeedback(feedbackID);
            response.sendRedirect("/ServiceforStudentManagement/providerAdmin/ListFeedBack.jsp");
        } catch (Exception ex) {
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seenFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int feedbackID = Integer.valueOf(request.getParameter("feedbackID"));
            new FeedbackDAO().setReportStatus(feedbackID, "Seen");
            response.sendRedirect("/ServiceforStudentManagement/providerAdmin/ListFeedBack.jsp");
        } catch (Exception ex) {
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

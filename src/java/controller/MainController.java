/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang Phat
 */
public class MainController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             String url = "index.jsp";
            String a = request.getParameter("action");
            if (a == null || a.isEmpty()) {
                url = "index.jsp";
            } else if (a.equals("1")) {
                url = "loginpage.jsp";
            } else if (a.equals("2")) {
                url = "FindServlet";
            } else if (a.equals("3")) {
                url = "LoginServlet";
            } else if (a.equals("4")) {
                url = "adminpage.jsp";
            } else if (a.equals("5")) {
                url = "userpage.jsp";
            } else if (a.equals("6")) {
                url = "LogoutServlet";
            } else if (a.equals("7")) {
                url = "AddToCartServlet";
            } else if (a.equals("8")) {
                url = "viewcart.jsp";
            } else if (a.equals("9")) {
                String remove = request.getParameter("btnremove");
                if (remove != null && remove.equals("remove")) {
                    url = "RemoveCartServlet";
                } else {
                    url = "UpdateCartServlet";
                }
            } else if (a.equals("10")) {
                url = "SaveOrderServlet";
            }
            request.getRequestDispatcher(url).forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
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

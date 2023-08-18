/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import basicobject.User;
import dbaccess.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Quang Phat
 */


public class LoginServlet extends HttpServlet {
   

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
            // lấy userid, password từ trang loginpage.html
            String userid = request.getParameter("txtuserid");
            String password = request.getParameter("txtpassword");
            User us = UserDao.getUser(userid);
            
            boolean flag = false;
            if (us != null) {
                if (us.getPassword().equals(password)) {
                    flag = true;
                    // lưu user này vào session memory của nó
                    HttpSession session = request.getSession();
                    session.setAttribute("loginedUser", us);
                  
                    // chuyển trang đúng với role của nó
                    if (us.getRole().equals("AD")) {
                        response.sendRedirect("MainController?action=4"); // mở admin
                    } else {
                        response.sendRedirect("MainController?action=5"); // mở trang user
                    }
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }
            if (!flag) {
                String msg = "invalid user id or password";
                request.setAttribute("ERROR", msg);
                request.getRequestDispatcher("MainController?action=1").forward(request, response);
            }
        } catch (Exception e) {
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

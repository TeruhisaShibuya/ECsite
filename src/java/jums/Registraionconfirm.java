/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shibuyateruhisa1
 */
public class Registraionconfirm extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registraionconfirm</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registraionconfirm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        
        
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
        //processRequest(request, response);
        //registration.jspからの処理
        request.setCharacterEncoding("UTF-8");
        String inputUsername = request.getParameter("username");
        String inputPass = request.getParameter("pass");
        String inputEmail = request.getParameter("email");
        String inputAddress = request.getParameter("address");
        
        //空欄チェック！
          int Erank = 0;
          String Emessage = "";
          
          if(inputUsername.equals("")){
            Emessage += "ユーザー名";
            Erank ++;
          }
          
          if(inputPass.equals("")){
            Emessage += " パスワード";
            Erank ++;
          }
          
          if(inputEmail.equals("")){
            Emessage += " メールアドレス";
            Erank ++;
          }
          
          if(inputAddress.equals("")){
            Emessage += " 住所";
            Erank ++;
          }
          
          if (0 < Erank){
            request.setAttribute("registrationEM", Emessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request,response);
          }
        
        UserDataBean udb = new UserDataBean();
        udb.setName(inputUsername);
        udb.setPassword(inputPass);
        udb.setEmail(inputEmail);
        udb.setAddress(inputAddress);
        
        //面倒なのでsessonスコープへ
        HttpSession session = request.getSession();
        session.setAttribute("RegistrationInfo", udb);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("registrationconfirm.jsp");
        dispatcher.forward(request,response);
        
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

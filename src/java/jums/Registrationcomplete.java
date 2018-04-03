/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Registrationcomplete extends HttpServlet {

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
            out.println("<title>Servlet Registrationcompleate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registrationcompleate at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        //registrationcofirm画面のボタンのvalueにより条件分岐
        request.setCharacterEncoding("UTF-8");
        String confirmValue = request.getParameter("btnConfirm");
        HttpSession session = request.getSession();
        
        if (confirmValue.equals("はい")){
          
          //まずはセッションから値の取り出しを行う
          UserDataBean udb = (UserDataBean)session.getAttribute("RegistrationInfo");
          
          UserDataDTO dto = new UserDataDTO();
          UserDataDTO CompleateDTO = dto.DTOmapForRegist(udb);
          
          //insertするためにDAOをインスタンス化
          UserDataDAO dao = new UserDataDAO();
            try {
              try {
                  dao.insert(CompleateDTO);
              } catch (SQLException ex) {
                  Logger.getLogger(Registrationcomplete.class.getName()).log(Level.SEVERE, null, ex);
              }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registrationcomplete.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Registrationcomplete.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Registrationcomplete.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          //compleate.jspに進む
          RequestDispatcher dispatcher = request.getRequestDispatcher("registrationcomplete.jsp");
          dispatcher.forward(request,response);
          
        }else{
          //いいえ　なら新規登録画面に戻る
          response.sendRedirect("registration.jsp");

        }
        
        
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

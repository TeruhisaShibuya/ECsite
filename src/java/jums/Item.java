/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author shibuyateruhisa1
 */
public class Item extends HttpServlet {

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
            out.println("<title>Servlet Item</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Item at " + request.getContextPath() + "</h1>");
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
        
        
        //search.jsp あるいは cart.jsp にて商品名リンクがクリックされた時の処理 
        request.setCharacterEncoding("UTF-8");
        String logout ="";
        logout += request.getParameter("logout");
        
        
        if(logout.equals("yes")){
          HttpSession hs = request.getSession();
          String SuserID = String.valueOf(hs.getAttribute("userID"));
          hs.removeAttribute("userInfo");
          hs.removeAttribute(SuserID+"UserCart");
       
          //ログアウトメッセージだけ追加
          String logoutMS = "ログアウトしました";
          request.setAttribute("logoutMS", logoutMS);
       
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
          dispatcher.forward(request,response);
        }
            
        
        String srbID = request.getParameter("srbs_instance_ID");
        String UserCartInstance = request.getParameter("UserCartInstance");
        
        //searchから来たか、cartから来たか？場合分け
        //1.searchから来た
        if (request.getParameter("UserCartInstance") == null){
          //検索結果のインスタンスをリクエストスコープに登録してitem.jspへ
          int instance_ID = Integer.parseInt(srbID);
          request.setAttribute("srbs_instance_ID", instance_ID);
          
          RequestDispatcher dispatcher = request.getRequestDispatcher("item.jsp");
          dispatcher.forward(request,response);
          
        }else{
          
          //UserCartのインスタンス番号をリクエストスコープに登録してitem.jspへ移動
          int IUserCartInstance = Integer.parseInt(UserCartInstance);
          request.setAttribute("UserCartinstance_ID", IUserCartInstance);
          RequestDispatcher dispatcher = request.getRequestDispatcher("item.jsp");
          dispatcher.forward(request,response);
        }
        
        
          
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

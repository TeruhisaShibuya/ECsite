/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author teruhisashibuya
 */
public class Buycomplete extends HttpServlet {

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
            out.println("<title>Servlet buycompleate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buycompleate at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        String btnValue = request.getParameter("btnsubmit");
        
        if (btnValue.equals("カートに戻る")){
          //カートへ戻るだけ〜
          RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
          dispatcher.forward(request,response);
          
        }else{
          
          //1.totalを更新するbuycompleteメソッドの準備
          int type = Integer.parseInt(request.getParameter("radiobutton"));
          int total = Integer.parseInt(btnValue);
          HttpSession hs = request.getSession();
          UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
          userInfo.setTotal(total);
          //ここまでで準備完了
          
          String SuserID = (String)hs.getAttribute("userID");
          ArrayList<SearchResultBeans> UserCart = (ArrayList<SearchResultBeans>)hs.getAttribute(SuserID+"UserCart");
          
            try {
              
              //購入メソッドと購入履歴登録メソッドの２つを実行
              UserDataDAO dao = new UserDataDAO();
              //user_tのTotalのみを更新するメソッド
              dao.BuyCompleate(userInfo);
              
              //buy_tの内容を更新するメソッド mappingした後に更新updateBuyHistry
              UserDataDTO dto = new UserDataDTO();
              ArrayList<UserDataDTO> ArrayForBuyHistroy = dto.DTOmapForbuy_t(userInfo, UserCart, type);
              dao.UpdateBuyHistory(ArrayForBuyHistroy);
              //カートセッションの中身をすべて削除して完了
              UserCart.clear();
              
            } catch (SQLException ex) {
                Logger.getLogger(Buycomplete.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Buycomplete.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Buycomplete.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Buycomplete.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          RequestDispatcher dispatcher = request.getRequestDispatcher("buycomplete.jsp");
          dispatcher.forward(request,response);  
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

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shibuyateruhisa1
 */
public class Add extends HttpServlet {

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
            out.println("<title>Servlet Add</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Add at " + request.getContextPath() + "</h1>");
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
        //以下、item.jspにて addボタン を押した後の処理
        //ログインしているか・していないか で作る名前のカートが異なる
        //すでにカートがあるか・ないか　でで対応が異なる
        
        //ログアウト押した時
        request.setCharacterEncoding("UTF-8");
        String logout ="";
        logout += request.getParameter("logout");
      
       if (logout.equals("yes")){
         HttpSession hs = request.getSession();
         String SuserID = String.valueOf(hs.getAttribute("userID"));
         hs.removeAttribute("userInfo");          //1個めのセッション
         hs.removeAttribute(SuserID+"UserCart");  //2個めのセッション
         hs.removeAttribute("SuserID");           //3個めのセッション 
       
         String logoutMS = "ログアウトしました";
         request.setAttribute("logoutMS", logoutMS);
       
         RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
         dispatcher.forward(request,response);
       }
        
        
        int instance_ID = Integer.parseInt(request.getParameter("instanceID"));
        
        //まずは検索結果10個をセッションから呼び出す
        HttpSession hs = request.getSession();
        ArrayList<SearchResultBeans> srbs0to9 = (ArrayList<SearchResultBeans>) hs.getAttribute("searchresult");
        
        //ログインしている→userCartBeansへ入力   ログインしていないなら→Sidcartへ代入
        if (hs.getAttribute("userInfo") == null ){
          
          //セッションカートへインスタンスを代入 sidカートに値を追加するところまでは問題無し！！
          String Sid = String.valueOf(hs.getAttribute("Sid"));
          ArrayList<SearchResultBeans> SidCart = (ArrayList<SearchResultBeans>)hs.getAttribute(Sid+"SidCartBeans");
          SidCart.add(srbs0to9.get(instance_ID));
          //追加した後、セッションオブジェクトを上書きするのを忘れずに
          hs.setAttribute(Sid+"SidCartBeans", SidCart);
          
          //add.jspへフォワード
          RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
          dispatcher.forward(request,response);
          
        }else{
           
          //ユーザーカートへインスタンスを代入(カート画面から来て「カートに入れる」を押した場合は場合はこっち)
          UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
          String userID = String.valueOf(userInfo.getUserID());
          ArrayList<SearchResultBeans> UserCart = (ArrayList<SearchResultBeans>)hs.getAttribute(userID+"UserCart");
          UserCart.add(srbs0to9.get(instance_ID));
          
          //add.jspへフォワード
          RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
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

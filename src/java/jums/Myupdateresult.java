/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Myupdateresult extends HttpServlet {

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
            out.println("<title>Servlet Myupdateresult</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Myupdateresult at " + request.getContextPath() + "</h1>");
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
        //アップデート画面にて送信が押されたら
        
        request.setCharacterEncoding("UTF-8");
        //String btnValue = request.getParameter("btnsubmit");
        
        //if(btnValue.equals("会員情報画面へ戻る")){
       
        String Uusername = request.getParameter("username");
        String Upassword = request.getParameter("password");
        String Umail = request.getParameter("mail");
        String Uaddress = request.getParameter("address");
          
          //空欄チェック！
          int Erank = 0;
          String Emessage = "";
          
          if(Uusername.equals("")){
            Emessage += "ユーザー名";
            Erank ++;
          }
          
          if(Upassword.equals("")){
            Emessage += " パスワード";
            Erank ++;
          }
          
          if(Umail.equals("")){
            Emessage += " メールアドレス";
            Erank ++;
          }
          
          if(Uaddress.equals("")){
            Emessage += " 住所";
            Erank ++;
          }
          
          if (0 < Erank){
            request.setAttribute("myupdateEM", Emessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("myupdate.jsp");
            dispatcher.forward(request,response);
          }
          
         
          //エラーなければDTOへ格納してDBアクセス開始
          UserDataDTO updateDTO = new UserDataDTO();
          HttpSession hs = request.getSession();
          int UuserID = Integer.parseInt((String)hs.getAttribute("userID"));
          updateDTO.setUserID(UuserID);
          updateDTO.setName(Uusername);
          updateDTO.setPassword(Upassword);
          updateDTO.setEmail(Umail);
          updateDTO.setAddress(Uaddress);
          
          //dbアップデートメソッドを呼ぶ 引数は updateDTO!
          UserDataDAO dao = new UserDataDAO();
            try {
                dao.updateUserInfo(updateDTO);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Myupdateresult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Myupdateresult.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Myupdateresult.class.getName()).log(Level.SEVERE, null, ex);
            }
          //セッション内の古いuserInfoを上書きしてフォワード
          
          UserDataDTO NEWuserInfo = (UserDataDTO)hs.getAttribute("userInfo");
          NEWuserInfo.setName(Uusername);
          NEWuserInfo.setPassword(Upassword);
          NEWuserInfo.setEmail(Umail);
          NEWuserInfo.setAddress(Uaddress);
          hs.setAttribute("userInfo",NEWuserInfo);
          
          //result画面へフォワード
          RequestDispatcher dispatcher = request.getRequestDispatcher("myupdateresult.jsp");
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

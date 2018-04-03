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
 * @author shibuyateruhisa1
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        
        request.setCharacterEncoding("UTF-8");
        //1.ログアウトが押された時の処理 login.jspへフォワード
        String logoutcheck = String.valueOf(request.getParameter("logoutcheck"));
        if (logoutcheck.equals("flg")){
          //各種セッションは破棄
          HttpSession hs = request.getSession();
          String SuserID = String.valueOf(hs.getAttribute("userID"));
          hs.removeAttribute("userInfo");          //1個めのセッション
          hs.removeAttribute(SuserID+"UserCart");  //2個めのセッション
          hs.removeAttribute("SuserID");           //3個めのセッション 
       
          //ログアウトメッセージだけ追加
          String logoutMS = "ログアウトしました";
          request.setAttribute("logoutMS", logoutMS);
          
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
          dispatcher.forward(request,response);
        }
        
        //2.各ページのログインが押された時の処理
        String lastpage = "";
        String WhereDidYouCome = request.getParameter("WhereDidYouCome");
        
        
        if (WhereDidYouCome.equals("top")){
          lastpage = "top";
          request.setAttribute("WhereDidYouCome", lastpage);
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
          dispatcher.forward(request,response);
          
        }else if(WhereDidYouCome.equals("search")) {
          lastpage = "search";
          request.setAttribute("WhereDidYouCome", lastpage); 
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
          dispatcher.forward(request,response);
          
        }else if(WhereDidYouCome.equals("item")) {
          lastpage = "item";
          request.setAttribute("WhereDidYouCome", lastpage);
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
          dispatcher.forward(request,response);
          
        }else if (WhereDidYouCome.equals("add")){
          lastpage = "add";
          request.setAttribute("WhereDidYouCome", lastpage);
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
          dispatcher.forward(request,response);
        
        }else{
          lastpage = "mydata";
          request.setAttribute("WhereDidYouCome", lastpage);
          RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
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
        
        try {
            //processRequest(request, response);
            //logoin.jspで入力した後の処理を下記に記載
            
            //ログイン可否判断のたえ、DBするのuser_tへアクセスする
            request.setCharacterEncoding("UTF-8");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String lastpage = request.getParameter("radiobutton");
            
            //検索メソッドの引数用に使うDTOを作成
            UserDataDTO udd_for_login = new UserDataDTO();
            udd_for_login.setEmail(email);
            udd_for_login.setPassword(pass);
            
            //2.データベースに同じ情報があるかアクセスして確認する
            UserDataDAO dao = new UserDataDAO();
            UserDataDTO userInfo =dao.search_for_login(udd_for_login);
            
            //emailとパスワードが入力値と一致するか?
            if (email.equals(userInfo.getEmail()) && pass.equals(userInfo.getPassword())){
              //一致した後は、さらにdeleteflgの値をチェック 1ならログイン画面へ戻ってメッセージ
              if (userInfo.getDeleteflg() == 1){
                String Cart_EM = "すでに削除されているユーザーのためログインできません";
                request.setAttribute("Cart_EM", Cart_EM);
                RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp"); 
                dispatcher.forward(request,response); 
              }    
              
              //flgが1でなければログインOK
              //SidCartBeansがあれば、中身をCartForUserへ移す
              HttpSession hs = request.getSession();
              String Sid = (String) hs.getAttribute("Sid");
              ArrayList<SearchResultBeans> SidCartBeans = (ArrayList<SearchResultBeans>)hs.getAttribute(Sid+"SidCartBeans");
              ArrayList<SearchResultBeans> UserCart = new ArrayList<SearchResultBeans>();
              
              if (SidCartBeans != null){
                //SidCartBeansの中身の数だけ移す
                for (int i = 0; i < SidCartBeans.size(); i++){
                  UserCart.add(SidCartBeans.get(i));
                }
              }
              //一意な名前をもったCartにするためuserInfoの中のuserIDを取得
              String SuserID = String.valueOf(userInfo.getUserID());
              hs.setAttribute("userID", SuserID);
              hs.setAttribute(SuserID+"UserCart", UserCart);
              hs.setAttribute("userInfo", userInfo);
              
              if (hs.getAttribute("Cart_EM") != null){
                hs.removeAttribute("Cart_EM");
                //RequestDispatcher dispatcher = request.getRequestDispatcher("Cart");
                //dispatcher.forward(request,response); 
              }
              
              //ラジオボタンの値によって、フォワード先を変更する switch〜case文
              switch (lastpage){
                case "top":
                  RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
                  dispatcher.forward(request,response); 
                  break;
                case "mydata":
                  RequestDispatcher dispatcher1 = request.getRequestDispatcher("Mydata");
                  dispatcher1.forward(request,response);
                  break;
                case "search":
                  RequestDispatcher dispatcher2 = request.getRequestDispatcher("search");
                  dispatcher2.forward(request,response);
                  break;
                case "item":
                  RequestDispatcher dispatcher3 = request.getRequestDispatcher("item");
                  dispatcher3.forward(request,response);
                  break;
                case "add":
                  RequestDispatcher dispatcher4 = request.getRequestDispatcher("add");
                  dispatcher4.forward(request,response);
                  break; 
              } 
              
            }else{
             //ログイン出来ない処理
             String EM = "メールアドレスかパスワードの入力が間違っています";
             request.setAttribute("EM", EM);
             RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp");
             dispatcher.forward(request,response);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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



//                RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
//                dispatcher.forward(request,response);    
//              }else if(lastpage.equals("item")){
//                RequestDispatcher dispatcher = request.getRequestDispatcher("item.jsp");
//                dispatcher.forward(request,response);
//              }else if(lastpage.equals("add")){
//                RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
//                dispatcher.forward(request,response);
//              }else{
//                RequestDispatcher dispatcher = request.getRequestDispatcher("Mydata");
//                dispatcher.forward(request,response);
//              }
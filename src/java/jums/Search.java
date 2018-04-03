package jums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author shibuyateruhisa1
 */
public class Search extends HttpServlet {

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
            out.println("<title>Servlet search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet search at " + request.getContextPath() + "</h1>");
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
        
        //Search.javaログアウトが押された時しか使用しない
        request.setCharacterEncoding("UTF-8");
        String logout = request.getParameter("logout");
        //ログアウト押した時の処理 →ログイン後に保持する3つのセッション情報を全て削除してtop.jspへ
         if (logout.equals("yes")){
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
        String inputValue = request.getParameter("search");
        
        if (inputValue.equals("")){
          String top_EM = "空欄はダメ、ぜったい (´・ω・｀)";
          request.setAttribute("top_EM", top_EM);
          RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
          dispatcher.forward(request,response);
        }
        else{
          
          //yahooAPIへつなぐ準備
          YahooAPI api = new YahooAPI();
          
          try {
            //APIにつなぐ
            Document doc = api.connectAPI(inputValue);
            
            //xmlファイルをパースして配列上のbeansにする
            ArrayList<SearchResultBeans> srbs = api.parseXML(doc);
            
            //srbの配列をセッションに入れる(しばらくもちあるくのでリクエストスコープではない)
            HttpSession session = request.getSession();
            session.setAttribute("searchresult", srbs);
            request.setAttribute("searchword", inputValue);
            
            //search.jspへフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
            dispatcher.forward(request,response);
            
            } catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }  
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

    private boolean getElementContent(Element e, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
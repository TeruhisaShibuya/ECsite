<%-- 
    Document   : rgistration_complete
    Created on : 2017/11/11, 22:26:00
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataBean"%>
<%
  HttpSession hs = request.getSession();
  String flgCheck = (String)hs.getAttribute("flg");
  
  if(flgCheck.equals("flg")){
    UserDataBean udb = (UserDataBean)session.getAttribute("RegistrationInfo");
  
    if (udb == null){
      String EM = "ログイン画面で登録するユーザー情報を入力してください";
      request.setAttribute("EM", EM);
      RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
      dispatcher.forward(request,response);
    }
  }else{
    String registrationEM = "指定のページへの移動は、新規会員登録をクリックしてください"; 
    request.setAttribute("registrationEM", registrationEM);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request,response);
  }
  
  
  hs.removeAttribute("RegistrationInfo");
  hs.removeAttribute("flg");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>会員登録完了画面</h1>
        <a href="login.jsp">ログイン画面へ</a><br>
        <p>会員情報をDBへ登録しました</p> 
        <a href="top.jsp">トップ画面(検索画面)へ</a><br>
        
    </body>
</html>

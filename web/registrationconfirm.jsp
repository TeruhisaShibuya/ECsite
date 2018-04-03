<%-- 
    Document   : registration_confirm
    Created on : 2017/11/11, 22:25:46
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataBean"%>
<%
  //sessionスコープから取り出し
  
  HttpSession hs = request.getSession();
  String flgCheck = (String)hs.getAttribute("flg");
  UserDataBean udb = (UserDataBean)session.getAttribute("RegistrationInfo");
  
  if(!flgCheck.equals("flg")){
    String registrationEM = "指定のページへの移動は、新規会員登録をクリックしてください"; 
    request.setAttribute("registrationEM", registrationEM);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request,response);
  }
    
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
        <h1>会員登録ー確認画面</h1>
        <a href="login.jsp">ログイン画面へ</a><br>
        <p>登録内容の確認</p>
        ユーザー名:<%= udb.getName() %><br>
        パスワード:<%= udb.getPassword() %><br>
        メールアドレス:<%= udb.getEmail() %><br>
        住所:<%= udb.getAddress() %><br>
        <p>上記の内容で登録しますがよろしいですか?</p>
        
        <form method="POST" action="Registrationcomplete">
          <input type="submit" name="btnConfirm" value="はい">
          <input type="submit" name="btnConfirm" value="いいえ">
        </form>
    </body>
</html>

<%-- 
    Document   : buycomplete
    Created on : 2017/11/11, 22:30:06
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.UserDataBean"%>
<%
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
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
          ようこそ<a href="mydata.jsp"><%out.print(userInfo.getName());%></a>さん!<br>
          <a href="Login?logoutcheck=flg">ログアウト</a><br>
          <a href="Cart">買い物かご</a><br>
          
        <h1>購入が完了しました！</h1>
        <h2>仕様コンプリート</h2>
        
        <a href="top.jsp">検索画面へ戻る</a><br>
        <a href="Myhistory">購入履歴を確認する</a><br> <!--サーブレットへ飛ぶ-->
        <a href="mydata.jsp">会員情報画面へ戻る</a><br> <!--サーブレットへ飛ぶ-->
        
    </body>
</html>

<%-- 
    Document   : myupdateresult
    Created on : 2018/01/07, 4:41:43
    Author     : teruhisashibuya
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="jums.UserDataBean"%>
<%
  //ダイレクトにURL入力でアクセスされるのを防ぐために下記対応
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
  
  //URL直接アクセス禁止
  if (userInfo == null){
    //Cart.javaと同じ対応でloginへ飛ばす
    String Cart_EM = "この画面への移動はログインが必要です";
    hs.setAttribute("Cart_EM", Cart_EM);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request,response);
  } 
  
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myupdateresult（会員情報 - 更新完了画面）</title>
        <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
      ようこそ<a href="mydata.jsp"><% out.print(userInfo.getName());%></a>さん!<br>
      <a href="Login?logoutcheck=flg">ログアウト</a><br>
      <a href="Cart">買い物かご</a><br>
          
      <h1>ユーザー情報更新完了画面</h1>
        以下の内容で更新しました。
        ユーザー名：<%userInfo.getName();%>
        パスワード：<%userInfo.getPassword();%>
        メールアドレス：<%userInfo.getEmail();%>
        住所：<%userInfo.getAddress();%>
        
        <a href="top.jsp">トップ画面へ</a><br>
        <a href="Mydata">ユーザー情報画面へ</a><br>
        <a href="Myhistory">購入履歴画面へ</a><br>
        <a href="Mydelete">情報の削除(退会)</a><br>
       
    </body>
</html>

<%-- 
    Document   : mydata
    Created on : 2017/11/11, 22:28:56
    Author     : shibuyateruhisa1
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
        <title>JSP Page</title>
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
          
        <h1>Mydata表示画面</h1>
        
        ユーザー名：<%out.print(userInfo.getName());%><br>
        パスワード：<%out.print(userInfo.getPassword());%><br>
        メールアドレス：<%out.print(userInfo.getEmail());%><br>
        住所：<%out.print(userInfo.getAddress());%><br>
        購入金額：<%out.print(userInfo.getTotal());%><br>
        登録に日時：<%out.print(userInfo.getNewdate());%><br>
        削除フラグ：<%out.print(userInfo.getDeleteflg());%><br>
        
        <a href="Cart">カート画面へ</a><br>
        <a href="Myhistory">購入履歴画面へ</a><br>
        <a href="Myupdate">登録情報を更新する</a><br>
        <a href="Mydelete">情報の削除(退会)</a><br>
        <a href="top.jsp">商品検索画面へ</a><br>
        
    </body>
</html>

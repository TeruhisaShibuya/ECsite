<%-- 
    Document   : mydeleteresult
    Created on : 2018/01/09, 14:01:41
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

  //ログイアウトと同じで、セッションオブジェクトを全て削除する
  hs.removeAttribute("userInfo");
  String SuserID = (String)hs.getAttribute("userInfo");
  hs.removeAttribute(SuserID+"UserCart");
  hs.removeAttribute("userID");
  
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
        
        <h1>mydeleteresult（会員情報 - 削除完了画面）</h1>
        ユーザー情報を削除しました。
        
        <a href="top.jsp">トップ画面へ</a><br>
        <a href="login.jsp">ログイン画面へ</a><br>
        
    </body>
</html>

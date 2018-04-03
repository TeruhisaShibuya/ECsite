<%-- 
    Document   : mydelete
    Created on : 2018/01/09, 13:48:28
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
          
        <h1>mydelete（会員情報 - 削除画面）</h1>
        削除するユーザー情報は以下の通りです<br>
        ユーザー名：<%=userInfo.getName()%><br>
        パスワード：<%=userInfo.getPassword()%><br>
        メールアドレス：<%=userInfo.getEmail()%><br>
        住所：<%=userInfo.getAddress()%><br>
        購入金額：<%=userInfo.getTotal()%><br>
        登録日時：<%=userInfo.getNewdate()%><br>
         
        このユーザーをマジで削除しますか?<br>
        
        <form method="POST" action="Mydeleteresult">
          <input type="submit" name="btnsubmit" value="はい"/>
          <input type="submit" name="btnsubmit" value="いいえ"/>
        </form>
        
    </body>
</html>

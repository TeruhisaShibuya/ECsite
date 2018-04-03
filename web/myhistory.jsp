<%-- 
    Document   : myhistory
    Created on : 2017/11/11, 22:29:13
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
  
  if (userInfo == null){
    //Cart.javaと同じ対応でloginへ飛ばす
    String Cart_EM = "この画面への移動はログインが必要です";
    hs.setAttribute("Cart_EM", Cart_EM);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request,response);
  } 

  ArrayList<UserDataDTO> MyHistory = (ArrayList<UserDataDTO>)request.getAttribute("MyHistory");
  

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>myhistory（会員情報 - 購入履歴）</title>
        <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
    </head>
    <body>z
        ようこそ<a href="mydata.jsp"><% out.print(userInfo.getName());%></a>さん!<br>
          <a href="Login?logoutcheck=flg">ログアウト</a><br>
          <a href="Cart">買い物かご</a><br>
        <h1>myhistory（会員情報 - 購入履歴）</h1>
        
        <% for(int i=0; i<MyHistory.size(); i++ ){%>
          購入ID:<%=MyHistory.get(i).getBuyID()%><br>
          ユーザーID:<%=MyHistory.get(i).getUserID()%><br>
          商品コード:<%=MyHistory.get(i).getItemCode()%><br>
          発送方法:<% if (MyHistory.get(i).getType() == 1){out.print("プライム便");}else{out.print("通常便");}%><br>
          購入日時:<%=MyHistory.get(i).getBuydate()%><br>
        <% } %>
        
    </body>
</html>

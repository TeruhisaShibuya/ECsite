<%-- 
    Document   : mydataupdate
    Created on : 2017/11/11, 22:29:34
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

  String myupdateEM = (String)request.getAttribute("myupdateEM");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>myupdate(会員情報ー更新画面)</title>
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
          
        <h1>ユーザー情報更新画面</h1>
        <%if(myupdateEM != null){out.print(myupdateEM+"が空欄でした。必ず値入力してください。");}%><br>
            
        <form method="POST" action="Myupdateresult">
          ユーザ名
          <input type="text" name="username" value="<%=userInfo.getName()%>"/><br>
          パスワード
          <input type="text" name="password" value="<%=userInfo.getPassword()%>"/><br>
          メールアドレス
          <input type="text" name="mail" value="<%=userInfo.getEmail()%>"/><br>
          住所
          <input type="text" name="address" value="<%=userInfo.getAddress()%>"/><br>
          
          <input type="submit" name="btnsubmit" value="送信"/>
          
        </form>
          
      <a href="mydata.jsp">ユーザー情報管理画面へ</a><br>
        
    </body>
</html>

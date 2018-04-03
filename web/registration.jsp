<%-- 
    Document   : registration
    Created on : 2017/11/11, 22:25:30
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataBean"
        import="javax.servlet.http.HttpSession"%>
<%
  //セッションスコープの中にRegistrationInfoがあれば、udbの取得を行う
  //if文の中で定義した変数はifの外で使えない よってい最初に初期値を宣言しておく
  //セッション変数.getAttribute("名前");しないと取得出来ないよ
  
  //ログイン画面のリンクからのみ来れる
  HttpSession hs = request.getSession();
  String flgCheck = (String)hs.getAttribute("flg");
  UserDataBean udb = null;
  udb = (UserDataBean)session.getAttribute("RegistrationInfo"); //udbに値をセット
  String registEM = (String)request.getAttribute("registrationEM");
  
  
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
        
        <h1>新規会員登録画面</h1>
        <a href="login.jsp">ログイン画面へ</a><br>
        <% if(registEM != null){%><font color="red"><%out.println(registEM+"が空欄です");%></font><%}%><br>
        <form action="Registraionconfirm" method="POST" >
          <p>ユーザー名を入力してください</p>
          <input type="text" name="username" value="<% if (udb != null){ out.print(udb.getName());} %>" /><br>
          <br>
          <br>
          <p>パスワードを入力してください</p>
          <input type="text" name="pass" value="<% if(udb != null){ out.print(udb.getPassword());} %>"/><br>
          <br>
          <br>
          <p>メールアドレスを入力してください</p>
          <input type="text" name="email" value="<% if(udb != null){ out.print(udb.getEmail());} %>"/><br>
          <br>
          <br>
          <p>住所を入力してください</p>
          <input type="text" name="address" value="<% if(udb != null){ out.print(udb.getAddress());} %>"/><br>
          <br>
          <br>
          <input type="submit" name="btnsubmit" value="登録内容確認画面へ"/>
        </form>
          
          
    </body>
</html>

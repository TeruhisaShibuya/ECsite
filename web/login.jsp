<%-- 
    Document   : login
    Created on : 2017/11/11, 22:25:06
    Author     : shibuyateruhisa1
--%>

<%@page import="jums.*"%>
<%
  HttpSession hs = request.getSession();
  String EM = (String)request.getAttribute("EM");
  String Cart_EM = (String)request.getAttribute("Cart_EM");
  String lastpage = String.valueOf(request.getAttribute("WhereDidYouCome"));
 
  //エラーはリクエストススコープの値
  String logoutMS = String.valueOf(request.getAttribute("logoutMS"));
  String registrationEM = String.valueOf(request.getAttribute("registrationEM"));
  
  if(hs.getAttribute("RegistrationInfo") != null){
    hs.removeAttribute("RegistrationInfo");  
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
        <h1 class="text-center">ログイン画面！</h1>
        <br>
        <% if(request.getAttribute("registrationEM") != null){%><font color="red"><% out.print(registrationEM); %></font><br><%}%>
        <% if(request.getAttribute("logoutMS") != null){%><font color="red"><% out.print("ログアウトしました"); %></font><br><%}%>
        
        <% if (EM != null) {%>
        <font color="red"><% out.print(EM); %></font><br>
        <% } %>
        
        <% if (Cart_EM != null) {%>
        <font color="red"><% out.print(Cart_EM); %></font><br>
        <% } %>
        
       <div class="text-center">
        
        <form method="POST" action="Login">
          emailを入力してください<br>
          <input type="text" name="email"/><br>
          <br>
          パスワードを入力してください<br>
          <input type="text" name="pass"/><br>
          
          <input type="radio" name="radiobutton" style="display:none" <% if(lastpage.equals("top")){%>checked=<%out.print("checked");}%> value="top"><br>
          <input type="radio" name="radiobutton" style="display:none" <% if(lastpage.equals("search")){%>checked=<%out.print("checked");}%> value="search">
          <input type="radio" name="radiobutton" style="display:none" <% if(lastpage.equals("item")){%>checked=<%out.print("checked");}%> value="item">
          <input type="radio" name="radiobutton" style="display:none" <% if(lastpage.equals("add")){%>checked=<%out.print("checked");}%> value="add">
          <input type="radio" name="radiobutton" style="display:none" <% if(lastpage.equals("null")){%>checked=<%out.print("checked");}%> value="mydata">
          
          <input type="submit" name="btnsubmit" value="ログイン"/>
          <br>
        </form>
       </div>
    <p class="text-center">
      <a href="Registration?token=flg">新規会員登録する</a><br>
      <a href="top.jsp">商品検索画面へ移動する</a>
    </p>
    </body>
</html>

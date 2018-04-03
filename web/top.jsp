<%-- 
    Document   : top
    Created on : 2017/11/11, 22:24:55
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.UserDataBean"%>
<%@page import="jums.SearchResultBeans"%>
<%@page import="java.util.ArrayList"%>
<%
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
  String CheckSid = (String)hs.getAttribute("Sid");
  String top_EM = (String)request.getAttribute("top_EM");
  
  
  //以下ログインしていない場合
  if (userInfo == null && CheckSid == null){
    //ArrayList AddedItems = (ArrayList)hs.getAttribute("SidCart");
    
      //セッションIDを名前に含む一意なカートオブジェクトを作成
      String Sid = hs.getId();
      ArrayList<SearchResultBeans> SidCart = new ArrayList<SearchResultBeans>();
      //一意なセッションIDを名前に追加したカートオブジェクトをセッションスコープへ登録する
      hs.setAttribute("Sid", Sid);
      hs.setAttribute(Sid+"SidCartBeans", SidCart);
    
  }
  //ログインしている状態でこのjspにアクセスした時は
  //login.javaにてすでにArrayList状のUserCartのが作られているので何もしない
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
        
        <% if(userInfo == null){ %>
          <a href="Login?WhereDidYouCome=top"><% out.print("ログイン");%></a><br>
        <% }else{ %>
          <% out.print("ようこそ");%><a href="mydata.jsp"><% out.print(userInfo.getName());%></a><%out.print("さん!");%><br>
          <a href="Top?logout=yes"><% out.print("ログアウト");%></a><br>
          <a href="Cart"><% out.print("買い物かご");%></a><br>
        <% } %>
        
        <h1>☆このサイトの説明☆</h1>
        このサイトはYahooショッピングAPIを利用しているため、買い物の疑似体験が出来るサイトです！<br>
        心ゆくまでお楽しみください！<br>
        
        <h2>☆検索ウィンドウで好きな商品を検索しよう☆</h2>
        <!--検索窓は input type="search"で作れる あとは「検索」ボタンも作ってお-->
        <!-- フォームの入力値は Serch.java の doPOST メソッドへ移動-->
        <% if (top_EM != null) {%>
        <font color="red"><% out.print(top_EM); %></font><br>
        <% } %>
        <form action="Search" method="POST" >
          <input type ="search" name="search" placeholder="♪キーワードを入力♪">
          <input type ="submit" name="submit" value="検索">
        </form>
        
    </body>
</html>

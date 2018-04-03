<%-- 
    Document   : serch
    Created on : 2017/11/11, 22:27:30
    Author     : shibuyateruhisa1
--%>

<%@page import="jums.UserDataDTO"%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.SearchResultBeans"%>
<%@page import="jums.UserDataBean"%>
<%@page import="jums.SearchResultBeans"%>
<%@page import="java.util.ArrayList"%>
<%
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
  String CheckSid = (String)hs.getAttribute("Sid");
  String logoutMS = (String)request.getAttribute("logoutMS");
  
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
  
   //検索ワードはリクエストスコープから取り出し キャスト
   String inputValue = (String) request.getAttribute("searchword");
   //検索結果はセッションスコープから値を取り出し 
   ArrayList<SearchResultBeans> srbs = (ArrayList<SearchResultBeans>) session.getAttribute("searchresult");
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
        <% if(logoutMS != null){%><font color="red"><% out.print("ログアウトしました"); %></font><br><%}%>
        <% if(userInfo == null){ %>
          <a href="Login?WhereDidYouCome=search"><% out.print("ログイン");%></a><br>
        <% }else{ %>
          <% out.print("ようこそ");%><a href="mydata.jsp"><% out.print(userInfo.getName());%></a><%out.print("さん!");%><br>
          <a href="top.jsp"><% out.print("ログアウト");%></a><br>
          <a href="cart.jsp"><% out.print("買い物かご");%></a><br>
        <% } %>
        <h1>検索結果画面 (Search画面)</h1>
        <h2>☆仕様☆</h2>
        <p>検索キーワード、検索結果数を表示</p>
        <p>縦のリスト型に表示。サムネイルと、その横に商品名、金額が載っている。クリックでitemへ</p>
        <p>結果は上位10件まで</p>
        
        検索ワード：☆<%= inputValue %>☆<br>
        検索検索結果数：<%= srbs.size() %>件<br>
        
        <% for (int i=0; i < srbs.size(); i++) {%>
        <!--画像にリンクを設定する-->
        <p>
        <!--アンカータグを用いで インスタンスIDをItemサーブレットへ渡す メソッドはGETです-->
        <a href="Item?srbs_instance_ID=<%=i%>"><img src="<%= srbs.get(i).getImageURL() %>" align="left" ></a>
        商品名：<a href="Item?srbs_instance_ID=<%=i%>"><%= srbs.get(i).getItemname() %></a><br>
        金額：<%= srbs.get(i).getPrice() %>円<br clear="left">
        </p>
        <% } %>
    </body>
</html>

<%-- 
    Document   : add.jsp
    Created on : 2017/12/06, 19:23:16
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
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(logoutMS != null){%><font color="red"><% out.print("ログアウトしました"); %></font><br><%}%>
        <% if(userInfo == null){ %>
          <a href="Login?WhereDidYouCome=add"><% out.print("ログイン");%></a><br>
        <% }else{ %>
          <% out.print("ようこそ");%><a href="mydata.jsp"><% out.print(userInfo.getName());%></a><%out.print("さん!");%><br>
          <a href="top.jsp"><% out.print("ログアウト");%></a><br>
          <a href="cart.jsp"><% out.print("買い物かご");%></a><br>
        <% } %>
        <h1>addページ</h1>
        <h2>☆仕様☆</h2>
        <p>1.商品の情報を受け取り、クッキーやセッションに追加する<br>
           2.画面には「カートに追加しました」という文言が出てくる<br>
        </p>
        
        <h2>☆-----------以下実装実装部分----------------☆</h2>
        <p>☆カートに商品を追加しました☆</p>
        
        <a href="top.jsp">トップへ戻る</a><br>
        <a href="search.jsp">検索結果画面へ</a><br>
        <a href="Cart">カート画面へ</a><br>
         <!--サ−ブレットへ飛ばす場合はサーブレット名のみの記入-->
    </body>
</html>

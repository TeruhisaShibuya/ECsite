<%-- 
    Document   : cart
    Created on : 2017/11/11, 22:29:42
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.SearchResultBeans"%>
<%@page import="jums.UserDataBean"
        import="java.util.ArrayList"%>
<%
  //ダイレクトにURL入力でアクセスされるのを防ぐために下記対応
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
  
  if (userInfo == null){
    //Cart.javaと同じ対応でloginへ飛ばす
    String Cart_EM = "カート画面への移動はログインが必要です";
    hs.setAttribute("Cart_EM", Cart_EM);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request,response);
  } 

  //表示するカートはArrayList状のSearchResultBeans型
  String SuserID = String.valueOf(hs.getAttribute("userID"));
  ArrayList<SearchResultBeans> UserCart = (ArrayList<SearchResultBeans>)hs.getAttribute(SuserID+"UserCart");

  //カートの合計金額
  int total = 0; 
  for (int i=0 ; i<UserCart.size();i++){
    total += Integer.parseInt(UserCart.get(i).getPrice());
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
          <a href="Search?logoutcheck=yes">ログアウト</a><br>
          <a href="Cart">買い物かご</a><br>
        <h1>Cart画面</h1>
         <h2>☆仕様☆</h2>
        <p>1.「カートに追加」でクッキーやセッションに保存された登録情報が登録古い順に表示される<br>
           2.商品の写真と名前(リンクつき)、金額を表示<br>
           3.画面下部に全額の合計金額を表示する<br>
           4.購入するボタンあり
           5.各商品には「削除」のリンクあり。このリンクをクリックすることで、カートから商品を削除する<br>
           6.カートの中身はユーザー毎に切り替えられる<br>
           7.ログインしていない状態ならばログインページに遷移<br>
           ログインに成功した場合、非ログイン状態で「カートに追加」操作をしていた商品はそのユーザー用のカートに移る
        </p>
        <h3>----------以下実装部分------------</h3>
        <!--カートの中身をforで回すところから-->
        <%if(0 < UserCart.size()){
          for(int i=0; i < UserCart.size() ; i++ ){ %>
        
            <img src="<%=UserCart.get(i).getImageURL() %>" align="left">
            商品名：<a href="Item?UserCartInstance=<%=i%>"><%=UserCart.get(i).getItemname() %></a><br>
            価格：<%=UserCart.get(i).getPrice()%>円<br>
            <a href="Cart?removeInstance=<%=String.valueOf(i)%>">カートから削除</a><br clear="left">
            <br>
          
          <%}%> 
        <%}%>
        
        カートの合計金額：<%=total%>円<br>
        
        <a href="Buyconfirm">商品を購入する</a><br>
        <a href="Myhistory">購入履歴画面へ</a><br>
        <a href="Mydata">ユーザー情報一覧画面へ</a><br>
        <a href="Myupdate">登録情報を更新する</a><br>
        <a href="Mydelete">情報の削除(退会)</a><br>
        
        <a href="top.jsp">商品検索画面へ</a><br>
    </body>
</html>

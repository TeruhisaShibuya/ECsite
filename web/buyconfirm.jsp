<%-- 
    Document   : buyconfirm
    Created on : 2017/11/11, 22:29:52
    Author     : shibuyateruhisa1
--%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.SearchResultBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.UserDataBean"%>
<%
  //ダイレクトにURL入力でアクセスされるのを防ぐために下記対応
  HttpSession hs = request.getSession();
  UserDataDTO userInfo = (UserDataDTO)hs.getAttribute("userInfo");
  
  if (userInfo == null){
    //Cart.javaと同じ対応でloginへ飛ばす
    String Cart_EM = "指定のページへ移動するにはログインが必要です";
    hs.setAttribute("Cart_EM", Cart_EM);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    dispatcher.forward(request,response);
  } 

  //表示するカートはArrayList状のSearchResultBeans型
  String SuserID = String.valueOf(hs.getAttribute("userID"));
  ArrayList<SearchResultBeans> UserCart = (ArrayList<SearchResultBeans>)hs.getAttribute(SuserID+"UserCart");
  int total = 0;
  String Stotal;
  for (int i =0; i< UserCart.size() ;i++){
    int price = Integer.parseInt(UserCart.get(i).getPrice());
    total += price;
  }  
  Stotal = String.valueOf(total);  
  

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
        
        <h1>ー仕様ー</h1>
        カートに追加順で商品の名前(リンクなし)、金額が表示される
        合計金額が表示され、その下に発送方法を選択するラジオボタンがある。
        「上記の内容で購入する」ボタンと「カートに戻る」ボタンがある。
        
        <h2>以下実装</h2>
        <%for (int i = 0 ; i< UserCart.size() ; i++){%>
          <%=UserCart.get(i).getItemname()%><br>
          <%=UserCart.get(i).getPrice()%>円<br>
          <br>
        <% } %>
        
        購入金額の合計は:<%out.print(total);%>円です<br>
         
        配送方法を選択してください
        <form method="POST" action="Buycomplete">
          <input type="radio" name="radiobutton" checked="checked" value="1">プライム便
          <input type="radio" name="radiobutton" value="2">普通便
          
          <button type="submit" name="btnsubmit" value="カートに戻る"/>カートに戻る</button>
          <button type="submit" name="btnsubmit" value="<%=Stotal%>"/>上記の内容で購入する</button>
        </form>
    </body>
</html>

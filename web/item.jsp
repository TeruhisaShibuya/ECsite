<%-- 
    Document   : item
    Created on : 2017/11/11, 22:28:44
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
  ArrayList<SearchResultBeans> Instances = new ArrayList<SearchResultBeans>();
  SearchResultBeans TheInstance = new SearchResultBeans();
  int instanceID = 0;
  String SinstanceID =""; 
  String srbs_instance_ID = request.getParameter("srbs_instance_ID");
  String UserCartInstance = request.getParameter("UserCartInstance");
  
  //ダイレクトアクセスは弾く
  if (srbs_instance_ID == null && UserCartInstance == null){
  //if (userInfo == null && CheckSid == null){
    RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
    dispatcher.forward(request,response);
   
    //セッションIDを名前に含む一意なカートオブジェクトを作成
    String Sid = hs.getId();
    ArrayList<SearchResultBeans> SidCart = new ArrayList<SearchResultBeans>();
    //一意なセッションIDを名前に追加したカートオブジェクトをセッションスコープへ登録する
    hs.setAttribute("Sid", Sid);
    hs.setAttribute(Sid+"SidCartBeans", SidCart);
  
  //1.ログインせずにsearch.jspから来るケース
  }else if(userInfo == null && srbs_instance_ID != null){
    
    Instances = (ArrayList<SearchResultBeans>)hs.getAttribute("searchresult");
    instanceID = (Integer)request.getAttribute("srbs_instance_ID");
    TheInstance = Instances.get(instanceID);
    SinstanceID += String.valueOf(instanceID);
  
  //2.ログインして後、cart画面から来るケース  
  }else if(userInfo != null && UserCartInstance != null){  
    
    String SuserID = String.valueOf(hs.getAttribute("userID"));
    Instances = (ArrayList<SearchResultBeans>)hs.getAttribute(SuserID+"UserCart");
    instanceID = (Integer)request.getAttribute("UserCartinstance_ID");
    TheInstance = Instances.get(instanceID);
    SinstanceID += String.valueOf(instanceID);
  
  //3.ログインした後に、search.jspからくるケース やることはケース1と同じ 
  }else{
    
    Instances = (ArrayList<SearchResultBeans>)hs.getAttribute("searchresult");
    instanceID = (Integer)request.getAttribute("srbs_instance_ID");
    TheInstance = Instances.get(instanceID);
    SinstanceID += String.valueOf(instanceID);
  
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
        
        <% if(logoutMS != null){%><font color="red"><% out.print("ログアウトしました"); %></font><br><%}%>
        <% if(userInfo == null){ %>
          <a href="Login?WhereDidYouCome=item"><% out.print("ログイン");%></a><br>
        <% }else{ %>
          <% out.print("ようこそ");%><a href="mydata.jsp"><% out.print(userInfo.getName());%></a><%out.print("さん!");%><br>
          <a href="top.jsp"><% out.print("ログアウト");%></a><br>
          <a href="cart.jsp"><% out.print("買い物かご");%></a><br>
        <% } %>
        
        <h1>Item.jspページ・商品詳細データ表示画面・表示は1件のみ</h1>
        <h2>☆仕様☆</h2>
        <p>1.searchあるいはcart画面から遷移可能<br>
           2.概要や評価値を表示する<br>
           3.カートに追加するボタンがあり、クリックするとaddに遷移<br>
        </p>
        
        <h2>☆-----------以下実装実装部分----------------☆</h2>
        <p>
        <!--srbsを表示するか、UserCartを表示するか? if で選択する-->    
        <img src="<%= TheInstance.getImageURL()%>" align="left" ></a>
        商品名：<%= TheInstance.getItemname() %><br>
        金額：<%= TheInstance.getPrice() %>円<br>
        評価：<%= TheInstance.getRate() %>　評価数:<%= TheInstance.getCount()%>件 <br clear="left">
        〜商品説明〜<br>
        <%= TheInstance.getDescription() %>
        </p>
        
        <p>
          <!--カートに入れるボタンはsearch.jspから来た時だけ cartから来た場合はcart画面へ戻るリンクを表示-->
          <%if(request.getAttribute("UserCartinstan_ID") == null){%>
            <a href="Add?instanceID=<%=SinstanceID%>"><input type="button" value="カートに入れる"></a>
          <% }else{ %>
            <a href="cart.jsp">カート画面へ戻る</a>
          <%}%>
        </p>
        <!--動作確認とフォーム値により別のjspに飛ばすところから-->
        
    </body>
</html>

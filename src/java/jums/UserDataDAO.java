package jums;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author shibuyateruhisa1
 */
//DB接続など、DTOオブジェクトをDBへ入力するためのクラス
public class UserDataDAO {
  
  public void insert(UserDataDTO dto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    Connection con = null;
    PreparedStatement st = null;
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ; 
      con = DriverManager.getConnection(url,user,pass);
      
      //auto incrementはinsert時に指定しなくて良い
      st =  con.prepareStatement("INSERT INTO user_t"
              + "(name,password,email,address,total,newDate,deleteFlg)"
              + " VALUES(?,?,?,?,?,?,?)");
      
      st.setString(1, dto.getName());
      st.setString(2, dto.getPassword());
      st.setString(3, dto.getEmail());
      st.setString(4, dto.getAddress());
      st.setInt(5, dto.getTotal());     //0が入る
      st.setTimestamp(6, new java.sql.Timestamp(dto.getNewdate().getTime()));  
      st.setInt(7, dto.getDeleteflg()); //0が入る
      st.executeUpdate();
      System.out.println("DBにデータが入りました"); //うまくいったか確認
      st.close();
      con.close();
              
    }catch(SQLException e){
      System.out.println(e.getMessage());
      throw new SQLException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
  
  //ログイン画面で入力されたユーザー名とemailからDB内の情報を検索するメソッド
  public UserDataDTO search_for_login(UserDataDTO udd_for_login) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ;
      con = DriverManager.getConnection(url,user,pass);
      
      String sql = "SELECT * FROM user_t "
                 + "WHERE email = ?"
                 + "AND password = ?";
      
      st = con.prepareStatement(sql);
      
      st.setString(1, udd_for_login.getEmail());
      st.setString(2, udd_for_login.getPassword());
      
      rs = st.executeQuery();
      
      //検索結果を新しいuddに入れるためにインスタンス化
      UserDataDTO resultudd = new UserDataDTO();
      while (rs.next()){
        //以下２つの値はは一意なカートの名前に使用するためため戻り値として設定→一意なuserIDをカートオブジェクトの名前に追加する
        resultudd.setUserID(rs.getInt(1));
        resultudd.setName(rs.getString(2));
        resultudd.setPassword(rs.getString(3));
        resultudd.setEmail(rs.getString(4));
        resultudd.setAddress(rs.getString(5));
        resultudd.setTotal(rs.getInt(6));
        resultudd.setNewdate(rs.getDate(7)); 
        resultudd.setDeleteflg(rs.getInt(8));
      }
      
      st.close();
      rs.close();
      
      return resultudd;
      
    }catch(SQLException e){
            System.out.println(e.getMessage());
    }
      return null;
  }
 
  
  
  
  //user_tのtotalに数値を加算するメソッド
  public void BuyCompleate(UserDataDTO userInfo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    Connection con = null;
    PreparedStatement st = null;
    try{    
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ; 
      con = DriverManager.getConnection(url,user,pass);
      
      //auto incrementはinsert時に指定しなくて良い
      st =  con.prepareStatement("UPDATE user_t set total = total + ? WHERE userID = ?");
      
      //それぞれ引数を割当
      st.setInt(1, userInfo.getTotal());
      st.setInt(2, userInfo.getUserID());
      
      st.executeUpdate();
      System.out.println("toralカラムの更新が完了しました。"); 
      st.close();
      con.close();
      
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }


  
  
  //購入履歴の更新メソッド 引数は3つ
  public void UpdateBuyHistory(ArrayList<UserDataDTO> ArrayDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    
    Connection con = null;
    PreparedStatement st = null;
                
    try{    
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ; 
      con = DriverManager.getConnection(url,user,pass);
      
      for (int i=0; i <ArrayDTO.size(); i++){ 
      //まずはfor分をstの上に記入してみる…
      st =  con.prepareStatement("INSERT INTO buy_t "
                + "(userID,itemCode,type,buyDate) "
                + "VALUES(?,?,?,?)");
      
        st.setInt(1, ArrayDTO.get(i).getUserID());
        st.setString(2, ArrayDTO.get(i).getItemCode());
        st.setInt(3, ArrayDTO.get(i).getType());
        st.setTimestamp(4, new java.sql.Timestamp(ArrayDTO.get(i).getBuydate().getTime())); 
        st.executeUpdate();
        System.out.println(i+"番目の購入処理終了");
      }
       
      st.close();
      con.close();
      
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }



  public void updateUserInfo(UserDataDTO updateDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    Connection con = null;
    PreparedStatement st = null;
    
            
    try{    
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ; 
      con = DriverManager.getConnection(url,user,pass);
      
      //とりあえすこれでやってみる！
      st =  con.prepareStatement("UPDATE user_t set "
                + "name=?, password=?, email=?, address=? "
                + "WHERE userID = ?");
      
      st.setString(1, updateDTO.getName());
      st.setString(2, updateDTO.getPassword());
      st.setString(3, updateDTO.getEmail());
      st.setString(4, updateDTO.getAddress());
      st.setInt(5, updateDTO.getUserID());
      st.executeUpdate();
      
      System.out.println("user_tの情報更新完了"); 
      st.close();
      con.close();
      
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
  
  
  //購入履歴表示メソッド userIDを使用
  public ArrayList<UserDataDTO> MyHistory(UserDataDTO dto) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
            
    try{    
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ; 
      con = DriverManager.getConnection(url,user,pass);
      
      st =  con.prepareStatement("SELECT * FROM buy_t WHERE userID = ? ");
                 
      st.setInt(1, dto.getUserID());
      rs = st.executeQuery();
      
      ArrayList<UserDataDTO> QueryHistory = new ArrayList<UserDataDTO>();
      
      while (rs.next()){
        UserDataDTO resultMHB = new UserDataDTO();
        resultMHB.setBuyID(rs.getInt(1));
        resultMHB.setUserID(rs.getInt(2));
        resultMHB.setItemCode(rs.getString(3));
        resultMHB.setType(rs.getInt(4));
        resultMHB.setBuydate(rs.getDate(5));
        QueryHistory.add(resultMHB);
      }
      
       
      System.out.println("but_tの取得完了"); 
      st.close();
      con.close();
      
      return QueryHistory;  
      
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
     return null; 
  }
  
  
  

  public void deleteUser(UserDataDTO dto) throws ClassNotFoundException, IllegalAccessException, InstantiationException{
    Connection con = null;
    PreparedStatement st = null;
            
    try{    
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost:8889/kagoyume_db" ;
      String user = "root" ;
      String pass = "root" ; 
      con = DriverManager.getConnection(url,user,pass);
      
      //とりあえすこれでやってみる！
      st =  con.prepareStatement("UPDATE user_t set deleteflg = ? WHERE userID = ? ");
                
                
      st.setInt(1, 1);
      st.setInt(2, dto.getUserID());
      st.executeUpdate();
      
      System.out.println("user_tの削除フラグを更新"); 
      st.close();
      con.close();
      
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  
  }

  
  
}//クラスのかっこ
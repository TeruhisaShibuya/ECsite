/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.util.Date;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//下記パッケージは 外部ダウンロードが必要となる なので Date型→DateTime型の手順で
//import org.joda.time.DateTime;

/**
 *
 * @author shibuyateruhisa1
 */

//Registration.jspでフォームに入力した情報を元に、データベースへ登録出来る形式にデータを調整するクラス
public class UserDataDTO {
  //結局ここはbeans形式になる
  //フィールド設定 DBと同じ要素
  private int userID;
  private String name;
  private String password;
  private String email;
  private String address;
  private int total;
  private Date newdate;  //マッピングする時に埋める処理
  private int deleteflg;
  
  //buy_t用の値
  private int buyID;         //updateBuyHistoryメソッドを呼ぶ前にフォーム入力値からsetterでセット
  private String itemCode;  //マッピングの時、引数のSearchResultBeansから取得する
  private int type;         //updateBuyHistoryメソッドを呼ぶ前にフォーム入力値からsetterでセット
  private Date buydate;     //マッピングする時に埋める処理


  //引数無しのコンストラクタ
//  public void UserDataDTO(){
//    this.name =  "";
//    this.password =  "";
//    this.email =  "";
//    this.address =  "";
//    this.total =  0;
//    this.newdate = new Date();
//    this.deleteFlg = 0;     
//  }
  
  //geterとsetter
  
  public int getUserID(){
    return userID; 
 }
  
  public void setUserID(int userID){
    this.userID = userID;
  }
  
  public String getName(){
    return name; 
 }
  
  public void setName(String name){
    this.name = name;
  }
  
  
  public String getPassword(){
    return password;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  
  public String getEmail(){
    return email;
  }
  
  public void setEmail(String email){
    this.email = email;
  } 
  
  
  public String getAddress(){
    return address;
  }
  
  public void setAddress(String address){
    this.address = address;
  }
  
  public int getTotal(){
    return this.total;
  }
  
  public void setTotal(int total){
    this.total = total;
  }
   
  public Date getNewdate(){
    return this.newdate;
  }
  
  public void setNewdate(Date newdate){
    this.newdate = newdate;  
  }
  
  public int getDeleteflg(){
    return this.deleteflg;
  }
  
  public void setDeleteflg(int deleteflg){
    this.deleteflg = deleteflg;
  }
  
  
  

//ここからbuy_用の値
 public int getBuyID(){
    return this.buyID;
 }
  
 public void setBuyID(int buyID){
    this.buyID = buyID;
 } 

  
 public String getItemCode(){
    return this.itemCode;
 }
  
 public void setItemCode(String itemCode){
    this.itemCode = itemCode;
 }
  
  public int getType(){
    return this.type;
  }
  
  public void setType(int type){
    this.type = type;
  }
   
    public Date getBuydate(){
    return this.buydate;
  }
  
  public void setBuydate(Date buydate){
    this.buydate = buydate;  
  }
 
  
  //会員登録時、DBアクセス前にDTOの中身を整える際に使用するメソッド
  
  public UserDataDTO DTOmapForRegist(UserDataBean udb){
    UserDataDTO dto = new UserDataDTO();
    //userIDはいらない autoincrementなので
    dto.setName(udb.getName());
    dto.setPassword(udb.getPassword());
    dto.setEmail(udb.getEmail());
    dto.setAddress(udb.getAddress());
    dto.setTotal(0);
    Date now = new Date();
    dto.setNewdate(now);
    dto.setDeleteflg(0);
    
    return dto;
  }
 
  
 
public ArrayList<UserDataDTO> DTOmapForbuy_t(UserDataDTO udd, ArrayList<SearchResultBeans> userCart, int type){
  ArrayList<UserDataDTO> array = new ArrayList<UserDataDTO>();
    for (int i=0 ; i < userCart.size(); i++){
      UserDataDTO dto = new UserDataDTO();  
      dto.setUserID(udd.getUserID());
      dto.setItemCode(userCart.get(i).getItemCode());
      dto.setType(type);
      Date now = new Date();
      dto.setBuydate(now);
      array.add(dto);
    }
  return array;
}
  
  
  
}//だいかっこ

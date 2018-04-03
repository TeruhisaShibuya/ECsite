/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.util.Date;

/**
 *
 * @author shibuyateruhisa1
 */
public class UserDataBean {
  
  //フィールド
  private int userID;
  private String name;
  private String password;
  private String email;
  private String address;
  private int total = 0; //指定しないと0かってに入る?
  private Date newdate;
  private int deleteflg;
  
  public int getUserID(){
    return userID;
  }
  
  public void setUserID(int inputuserID){
    this.userID = inputuserID; 
  }
  
  
  public String getName(){
    return name;
  }
  
  public void setName(String inputName){
    this.name = inputName;
  }
  
  public String getPassword(){
    return password;
  }
  
  public void setPassword(String inputPssword){
    this.password = inputPssword;
  }
  
  public String getEmail(){
    return email;
  }
  
  public void setEmail(String inputEmail){
    this.email = inputEmail;
  }
  
  public String getAddress(){
    return address;
  }
  
  public void setAddress(String inputAddress){
    this.address= inputAddress;
  }
  
  public int getTotal(){
    return total;
  }
  
  public void setTotal(int inputtotal){
    this.total= inputtotal;
  }
  
  public Date getNewdate(){
    return newdate;
  }
  
  public void setNewdate(Date inputdate){
    this.newdate= inputdate;
  }
  
  public int getDeleteflg(){
    return deleteflg;
  }
  
  public void setDeleteflg(int inputdeleteflg){
    this.deleteflg= inputdeleteflg;
  }
  
}

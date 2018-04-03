package jums;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shibuyateruhisa1
 */
public class SearchResultBeans {
  //beansの基礎
  //1.プライベートなフィ-ルドを持つ 
  private String itemname;
  private String itemcode;
  private String imageURL;
  private String price;
  private String description;
  private String rate;
  private String count;
  private String link;
  
  //2.引数無しのコンストラクタを持つ→
  //→今回は必ず外部からの入力(xmlファイルの解析結果)があるので持たなくても良い
  //xmlファイルに万が一問題があったらコンストラクタはあった方が良い
//  public SearchResulBeans(){
//    this.itemName = "";
//    this.price = 0;
//    this.imageURL = "";
//    this.overview = "";
//    this.evaluation = "";
//  }
//  
  
  //3.getterとsetterを持つ 大文字に注意
  public String getItemname(){
    return itemname;
  } 
  public void setItemname(String itemName){
    this.itemname = itemName;  
  }
  
  public String getItemCode(){
    return itemcode;
  } 
  public void setItemCode(String itemcode){
    this.itemcode = itemcode;  
  }
  
  public String getImageURL(){
    return imageURL;
  } 
  public void setImageURL(String imageURL){
    this.imageURL = imageURL;  
  }
  
  public String getPrice(){
    return price;
  } 
  public void setPrice(String price){
    this.price = price;  
  }
  
  public String getDescription(){
    return description;
  } 
  public void setDescription(String description){
    this.description = description;  
  }
  
  public String getRate(){
    return rate;
  } 
  public void setRate(String rate){
    this.rate = rate;  
  }
  
  public String getCount(){
    return count;
  } 
  public void setCount(String count){
    this.count = count;  
  }
  
  public String getLink(){
    return link;
  } 
  public void setLink(String link){
    this.link = link;  
  }
  
   
}

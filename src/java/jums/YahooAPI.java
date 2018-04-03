package jums;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shibuyateruhisa1
 */
import jums.SearchResultBeans;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
   

public class YahooAPI {
  //キーワードを元にAPIに接続する

    /**
     *
     * @param String
     * @throws Exception
     */
  public Document connectAPI(String inputValue) throws Exception{
      
      String Client_ID = "dj00aiZpPXJweGF5TWJiM0NtRyZzPWNvbnN1bWVyc2VjcmV0Jng9NDc-";
      //商品検索時に叩くAPIのリクエストURLを指定 今回はXML形式で結果をもらうためのアドレスを使う jsonpの時はまた別のURLとなる
      String Request_URL = "https://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch"; 
        
      URL url = new URL(Request_URL+"?appid="+Client_ID+"&query="+inputValue);
      
      HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
         //urlconn型のクラスの変数に対して setRequestMethod を使用する。。引数を"GET"にする事でGET通信する事を命令が実行可能
      urlconn.setRequestMethod("GET");
         //??なメソッド　後で調べる
      urlconn.setInstanceFollowRedirects(false);
      
      urlconn.connect();
      
      //xml形式のレスポンスをファイル形式にする
      InputStream in = urlconn.getInputStream();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder(); 
      Document doc = builder.parse(in);
      in.close();
      
      return doc;
   }

  
  public ArrayList<SearchResultBeans> parseXML(Document doc){
      ArrayList result_SRB_Array = new ArrayList();
      NodeList hits = doc.getElementsByTagName("Hit");
      //もしhitsの要素数が10個を超えるなら、10個目以上は対応しない
      if(10 < hits.getLength()){
          for(int i=0; i<10; i++){
              NodeList hits_Childrens = hits.item(i).getChildNodes();
              SearchResultBeans srb = new SearchResultBeans();  //最終的なbeansの数はHitsの数と同じ20個 よって2つ目のforの前でnewする
                for (int x=0; x<hits_Childrens.getLength(); x++){
                    Node one_of_hit_children = hits_Childrens.item(x);
                    
                    if (one_of_hit_children.getNodeName().equals("Name")){
                        srb.setItemname(one_of_hit_children.getTextContent());
                    }
                    
                    if (one_of_hit_children.getNodeName().equals("Code")){
                        srb.setItemCode(one_of_hit_children.getTextContent());
                    }
                    
                    if (one_of_hit_children.getNodeName().equals("Description")){
                        srb.setDescription(one_of_hit_children.getTextContent());
                    }
                    if (one_of_hit_children.getNodeName().equals("Image")){
                        NodeList deepChilds = one_of_hit_children.getChildNodes();
                        srb.setImageURL(deepChilds.item(2).getTextContent());
                    }
                    if (one_of_hit_children.getNodeName().equals("Price")){
                        srb.setPrice(one_of_hit_children.getTextContent());
                    }
                    if (one_of_hit_children.getNodeName().equals("Review")){
                        NodeList deepChilds = one_of_hit_children.getChildNodes();
                        srb.setRate(deepChilds.item(0).getTextContent());
                        srb.setCount(deepChilds.item(1).getTextContent());
                        srb.setLink(deepChilds.item(2).getTextContent());
                        result_SRB_Array.add(srb); //位置に注意
                    }   
                }
            }
        }else{  
          for (int i=0; i<hits.getLength(); i++){
            //System.out.print(hits.item(i).getTextContent());  hitsの要素数は20(0〜19)でコンテンツの中身も結果良好
            NodeList hits_Childrens = hits.item(i).getChildNodes();
            SearchResultBeans srb = new SearchResultBeans();  //最終的なbeansの数はHitsの数と同じ20個 よって2つ目のforの前でnewする
                for (int x=0; x<hits_Childrens.getLength(); x++){
                    Node one_of_hit_children = hits_Childrens.item(x);
                        if (one_of_hit_children.getNodeName().equals("Name")){
                          srb.setItemname(one_of_hit_children.getTextContent());    
                          //要素数20こで動作OK  result_SRB_Array.add(srb);   
                        }
                        if (one_of_hit_children.getNodeName().equals("Description")){
                          srb.setDescription(one_of_hit_children.getTextContent());
                        }
                        if (one_of_hit_children.getNodeName().equals("Image")){
                          NodeList deepChilds = one_of_hit_children.getChildNodes();
                          srb.setImageURL(deepChilds.item(2).getTextContent());
                        }
                        if (one_of_hit_children.getNodeName().equals("Review")){
                          NodeList deepChilds = one_of_hit_children.getChildNodes();
                          srb.setRate(deepChilds.item(0).getTextContent());
                          srb.setCount(deepChilds.item(1).getTextContent());
                          srb.setLink(deepChilds.item(2).getTextContent());
                          result_SRB_Array.add(srb); //位置に注意
                        }
                }  
          }
        }
      return result_SRB_Array;
    }
}  //このクラスの閉じ括弧
    

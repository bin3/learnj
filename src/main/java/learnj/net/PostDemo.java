/**
 * Copyright (C), 2014, Beijing Benbun Technology CO., LTD.
 * 
 * @author	bin3 <zhangwenbin@benbun.com>
 * @date	2014年10月23日
 */

package learnj.net;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class PostDemo {
	public static Logger log = LoggerFactory.getLogger(PostDemo.class);

  /**
   * @param args
   * @throws IOException 
   * @throws ClientProtocolException 
   */
  public static void main(String[] args) throws ClientProtocolException, IOException {
    final String URL = "http://114.215.130.61:8081/SendMT/SendMessage";
    
    CloseableHttpClient client = HttpClientBuilder.create().build();  
    
//    CloseableHttpResponse  response = client.execute(new HttpGet("http://www.baidu.com"));
    
    String username = "liweijia";
    String password = "123456";
//    String mobile = "18010150668";
    String mobile = "18701660917";
//    String mobile = "13911556061";
//    String mobile = "13691314342";
//    String mobile = "18910335487";
//    String mobile = "18010150668,18701660917,13911556061,13691314342,15710007125";
//    String content = "hello测试";
//    String content = "hello2";
    String content = "请输入验证码012999！【丽维家】";
    
//    content = URLEncoder.encode(content,"GBK");
    content = URLEncoder.encode(content,"GBK");
    
    HttpPost httpPost = new HttpPost(URL);
    ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(); 
    params.add(new BasicNameValuePair("UserName", username));
    params.add(new BasicNameValuePair("UserPass", password));
    params.add(new BasicNameValuePair("Mobile", mobile));
    params.add(new BasicNameValuePair("Content", content));
    httpPost.setEntity(new UrlEncodedFormEntity(params));

    CloseableHttpResponse response = client.execute(httpPost); 
    System.out.println(response);
    System.out.println(response.getStatusLine());
    System.out.println(response.getEntity());
    
    String result = EntityUtils.toString(response.getEntity());
    log.info(result);
      
  }

}

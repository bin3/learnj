/**
 * Copyright (C), 2014, Beijing Benbun Technology CO., LTD.
 * 
 * @author bin3 <zhangwenbin@benbun.com>
 * @date 2014年7月26日
 */

package learnj.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 */
public class PropertiesDemo {

  public static Properties read(String path) {
    try {
      Properties props = new Properties();
      InputStream in = new BufferedInputStream(new FileInputStream(path));
      props.load(in);
      return props;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(read("conf/test.properties"));
    System.out.println(read("conf/xxx.properties"));
  }

}

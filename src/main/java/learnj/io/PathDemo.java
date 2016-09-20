/**
 * Copyright (C), 2014, Beijing Benbun Technology CO., LTD.
 * 
 * @author	bin3 <zhangwenbin@benbun.com>
 * @date	2014年7月26日
 */

package learnj.io;

import java.io.File;
import java.io.IOException;

/**
 * 
 */
public class PathDemo {
  
  public static String getCurDir() {
    File dir = new File (".");
    try {
      return dir.getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
  
  public static String getCurDir2() {
    return System.getProperty("user.dir");
  }
  
  public static void curDir() {
    System.out.println(getCurDir());
    System.out.println(getCurDir2());
//    System.out.println(Thread.getContextClassLoader());
//    System.out.println(Thread.getContextClassLoader().getResource());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    curDir();
  }

}

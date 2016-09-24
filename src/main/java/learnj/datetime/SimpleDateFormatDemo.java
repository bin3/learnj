/**
 * Copyright (C), 2016, Beijing Benbun Technology CO., LTD.
 * 
 * @author bin3 <zhangwenbin@benbun.com>
 * @date 2016年9月24日
 */

package learnj.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 */
public class SimpleDateFormatDemo {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    System.out.println(fmt.format(date));
  }

}

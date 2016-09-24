/**
 * Copyright (C), 2016, Beijing Benbun Technology CO., LTD.
 * 
 * @author bin3 <zhangwenbin@benbun.com>
 * @date 2016年9月24日
 */

package learnj.datetime;

import java.util.Calendar;

/**
 * 
 */
public class CalendarDemo {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE);
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    System.out.println("year: " + year);
    System.out.println("month: " + month);
    System.out.println("day: " + day);
    System.out.println("hour: " + hour);
    System.out.println("minute: " + minute);
    System.out.println("second: " + second);
  }

}

/**
 * Copyright (C), 2014, Beijing Benbun Technology CO., LTD.
 * 
 * @author bin3 <zhangwenbin@benbun.com>
 * @date 2014年7月26日
 */

package learnj.system;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * 
 */
public class PropertyDemo {

  public static void printAllProperties() {
    Properties props = System.getProperties();
    TreeMap<Object, Object> map = new TreeMap<Object, Object>();
    {
      Iterator<Map.Entry<Object, Object>> it = props.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        map.put(key, value);
      }
    }
    {
      Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        System.out.println(key + "=" + value);
      }
    }


  }

  public static void run() {
    // System.out.println(System.getProperty("java.class.path"));
    // System.out.println(System.getProperties());
    printAllProperties();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    run();
  }

}

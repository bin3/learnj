/**
 * Copyright (C), 2014, Beijing Benbun Technology CO., LTD.
 * 
 * @author bensonzhang <zhangwenbin@benbun.com>
 * @date 2014年7月25日
 */

package learnj.json;

import learnj.json.bean.Group;
import learnj.json.bean.User;

import com.google.gson.Gson;

/**
 * 
 */
public class GsonDemo {
  
  public static void toJson() {
    Gson gson = new Gson();
    
    Group group = new Group();
    group.setId(0);
    group.setName("admin");

    User guestUser = new User();
    guestUser.setId(2);
    guestUser.setName("guest");

    User rootUser = new User();
    rootUser.setId(3);
    rootUser.setName("root");

    group.addUser(guestUser);
    group.addUser(rootUser);

    String str = gson.toJson(group);
    System.out.println(str);
  }
  
  public static void fromJson() {
    String str = "{\"name\":\"jack\",\"id\":20}";
    Gson gson = new Gson();
    User person = gson.fromJson(str, User.class);
    System.out.println(person);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    toJson();
    fromJson();
  }

}

/**
 * Copyright (C), 2014, Beijing Benbun Technology CO., LTD.
 * 
 * @author bensonzhang <zhangwenbin@benbun.com>
 * @date 2014年7月25日
 */

package learnj.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * 
 */
public class GsonDemo {
  public static class Person {

    private String name;
    private int age;

    /**
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
      return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return name + ":" + age;
    }
  }
  
  public static void toJson() {
    Gson gson = new Gson();
    List<Person> persons = new ArrayList<Person>();
    for (int i = 0; i < 10; i++) {
      Person p = new Person();
      p.setName("name" + i);
      p.setAge(i * 5);
      persons.add(p);
    }
    String str = gson.toJson(persons);
    System.out.println(str);
  }
  
  public static void fromJson() {
    String str = "{\"name\":\"name0\",\"age\":0}";
    Gson gson = new Gson();
    Person person = gson.fromJson(str, Person.class);
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

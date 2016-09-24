/**
 * Copyright (C), 2016, Beijing Benbun Technology CO., LTD.
 * 
 * @author bin3 <zhangwenbin@benbun.com>
 * @date 2016年9月23日
 */

package learnj.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public class JdbcDemo {

  public static void main(String[] args) throws Exception {
    String host = "devdb.benbun.com";
//    String host = "127.0.0.1";
    String port = "3306";
    String username = "root";
    String password = "7_CiRzkJmBicZpje";
//    String password = "";
    String database = "test";

    Connection conn = null;
    String sql;
    String url = String.format(
                "jdbc:mysql://%s:%s/%s?user=%s&password=%s&useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false",
                host, port, database, username, password);

    try {
      conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement();
      int result;
//      sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
//      result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
//      if (result != -1) {
//        System.out.println("创建数据表成功");
//      }

      sql = "insert into student(NO,name) values('2012001','陶伟基')";
      result = stmt.executeUpdate(sql);
      sql = "insert into student(NO,name) values('2012002','周小俊')";
      result = stmt.executeUpdate(sql);
      sql = "select * from student";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("学号\t姓名");
      while (rs.next()) {
        System.out.println(rs.getString(1) + "\t" + rs.getString(2));
      }
    } catch (SQLException e) {
      System.out.println("MySQL操作错误");
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.close();
    }

  }

}

/**
 * Copyright (C), 2016, Beijing Benbun Technology CO., LTD.
 * 
 * @author bin3 <zhangwenbin@benbun.com>
 * @date 2016年9月23日
 */

package learnj.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 */
public class JdbcDemo {

  public static String buildQuery(Map<String, String> params) {
    StringBuffer sb = new StringBuffer();
    for (Entry<String, String> entry : params.entrySet()) {
      if (sb.length() > 0) {
        sb.append("&");
      }
      sb.append(entry.getKey());
      sb.append("=");
      sb.append(entry.getValue());
    }
    return sb.toString();
  }

  public static Connection connect() {
    String host = "devdb.benbun.com";
    String port = "3306";
    String database = "test";

    Map<String, String> params = new HashMap<String, String>();
    params.put("user", "root");
    params.put("password", "7_CiRzkJmBicZpje");
    params.put("useUnicode", "true");
    params.put("characterEncoding", "UTF8");
    params.put("serverTimezone", "UTC");
    params.put("useSSL", "false");
    String query = buildQuery(params);

    String url = String.format("jdbc:mysql://%s:%s/%s?%s", host, port, database, query);

    Connection conn;
    try {
      conn = DriverManager.getConnection(url);
      return conn;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean create(Statement stmt) {
    String sql = "create table student(no char(20),name varchar(20),primary key(no))";
    try {
      int result = stmt.executeUpdate(sql);
      System.out.println("result: " + result);
      // create table成功返回0
      return result == 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static String genNo() {
    Date date = new Date();
    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
    return fmt.format(date);
  }

  public static String genName() {
    long id = Math.round(Math.random() * 10000);
    return "user" + id;
  }

  public static boolean insert(Statement stmt) {
    String no = genNo();
    String name = genName();
    String sql = String.format("insert into student(no,name) values('%s', '%s')", no, name);
    try {
      int result = stmt.executeUpdate(sql);
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean safeInsert(Connection conn) {
    try {
      String sql = "insert into student(no,name) values(?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, genNo());
      stmt.setString(2, genName());
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean select(Statement stmt) {
    String sql = "select * from student";
    try {
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("学号\t姓名");
      while (rs.next()) {
        System.out.println(rs.getString(1) + "\t" + rs.getString(2));
      }
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void main(String[] args) throws Exception {

    Connection conn = connect();
    Statement stmt = conn.createStatement();
    // create(stmt);
    insert(stmt);
    safeInsert(conn);
    select(stmt);
    conn.close();
  }

}

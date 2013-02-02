/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author	Binson Zhang <bin183cs@gmail.com>
 * @date	2013-2-2
 */
package learnj.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @brief H2Demo
 */
public class H2Demo {
	static final String DB_DIRVER = "org.h2.Driver"; // H2 Driver
	static final String DB_URL = "jdbc:h2:db/mydb";
	static final String DB_USER = "sa";
	static final String DB_PASS = "";
	
	public void runInsertDelete() {
		try {
			try {
				Class.forName(DB_DIRVER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE mytable(name VARCHAR(100),sex VARCHAR(10))");
			stmt.executeUpdate("INSERT INTO mytable VALUES('Steven Stander','male')");
			stmt.executeUpdate("INSERT INTO mytable VALUES('Elizabeth Eames','female')");
			stmt.executeUpdate("DELETE FROM mytable WHERE sex=\'male\'");
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}
	
	public void query(String SQL) {  
        try {  
            try {  
                Class.forName(DB_DIRVER);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  
            Statement stmt = conn.createStatement();  
            ResultSet rset = stmt.executeQuery(SQL);  
            while (rset.next()) {  
                System.out.println(rset.getString("name")+ "  "+rset.getString("sex"));  
            }  
            rset.close();  
            stmt.close();  
            conn.close();  
        } catch (SQLException sqle) {  
            System.err.println(sqle);  
        }  
    } 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		H2Demo demo = new H2Demo();
//		demo.runInsertDelete();
		demo.query("select * from mytable");
	}

}

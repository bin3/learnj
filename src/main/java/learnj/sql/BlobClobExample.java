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

/**
 * @brief BlobClobExample
 */
import java.io.*;
import java.sql.*;

class BlobClobExample {
	public static void main(String[] args) {
		try {
			String url = "jdbc:derby:clobberyclob;create=true";
			// Derby中的URL，后面是附加参数。表示数据库中没有此数据库时，会自动建一个
			// 其它数据库提供商的产品可以查阅其手册
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			// 加载驱动。关于加载驱动的细节，请阅读本博客的《JDBC中驱动加载的过程分析（上）》
			// 和《JDBC中驱动加载的过程分析（下）》
			// 在数据库Mysql的驱动加载时也建议在Class.forName()后，继续调用newInstance()方法
			// 没有本质区别
			Connection conn = DriverManager.getConnection(url); // 建立连接
			Statement s = conn.createStatement(); // 创建Statement对象
			s.executeUpdate("drop table documents");
			// 这句是为了避免再次创建表documents
			s.executeUpdate("CREATE TABLE documents(id INT, text CLOB(64 K) , photo Blob(1440 K))");
			// 以上就是创建包含Clob和Blob对象的表的过程
			// 和将一般的字段差不多，后面括号中代表该对象的大小，其语法为：
			// CLOB (length [{{K |M |G}]) 表示长度为K、M、G等，没有带表示比特
			conn.commit(); // 提交操作
			// 以下是将本地文件中，本程序的源文件加载为一个流，用于向数据库中写入Clob字段
			File file1 = new File("BlobClobExample.java");
			int len1 = (int) file1.length();
			InputStream fis1 = new java.io.FileInputStream(file1);
			// 以下是将当前源代码目录的子目录“11”下的“1.JPG”文件加载为一个流，
			// 用于后面向数据库中写入Blob字段
			File file2 = new File("c:\\11\\1.JPG");
			int len2 = (int) file2.length();
			InputStream fis2 = new FileInputStream(file2);
			// 以上两个简单吧！！
			// 创建一个PreparedStatement对象，用于批量插入内容
			// 使用PreparedStatement对象可以节省，数据库编译SQL指令的时间，
			// 因为在使用PreparedStatement对象时，该SQL语句是预先编译好了，
			// 对于某些变化的参数使用占位符（Place Holder）代替
			// 这对于以后将要执行多次的同一操作，该操作仅仅是参数不同，是极其有利
			// 比如在网页上输入客户信息时，用户要填入的数据基本是一样的（你可以填也可以不填）
			// 这时后台的数据库可以使用PreparedStatement对象，每次都是设置参数，执行操作
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO documents VALUES (?, ?,?)");
			ps.setInt(1, 250);
			ps.setAsciiStream(2, fis1, len1);
			ps.setBinaryStream(3, fis2, len2);
			// 以上三步是设置占位符的数值
			ps.execute(); // 执行操作
			// 以上四步可以重复执行。PreparedStatement就是为了适用于此用途
			conn.commit();
			// 以上完成了数据的写入
			// 以下是数据的读出
			ResultSet rs = s
					.executeQuery("SELECT text , photo FROM documents WHERE id = 250");
			while (rs.next()) {
				Clob aclob = rs.getClob(1); // 和提取一般对象一样
				InputStream is = rs.getAsciiStream(1); // 特殊的，对于与得到Clob的流
				// 这是得到Clob、Blob流的第一种方法

				// 以下是对流进行处理的过程。Clob本身是包含大字符的对象
				// 顺其自然，以下是使用java IO中读取字符流的方法读取它
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line = null;
				while (null != (line = br.readLine())) {
					System.out.println(line); // 将其输出至屏幕，实际你可以按照需要处理
				}
				is.close();
				java.sql.Blob ablob = rs.getBlob(2); // 和操作其它基本类型的字段一样
				System.out.println(ablob.length()); // 简单地操作Blob的实例
				// 实际你可以使用Clob的API对其进行任何它允许的操作，请查API
				InputStream bis = ablob.getBinaryStream(); // 得到Blob实例的字节流
				// 这是操作Clob、Blob等对象的第二种方法，也是最自然的用法
				OutputStream os = new FileOutputStream("11.jpg");
				// 用于将数据库中的Blob存放到目前目录的“11.jpg”文件中
				int b = bis.read(); // 以下就是象操作其它java字节流一样操作了
				while (b != -1) {
					os.write((char) b);
					b = bis.read();
				}
				os.flush();
				os.close();
				bis.close();
			}
		} catch (Exception e) {
			System.out.println("Error! " + e);
		}
	}
}

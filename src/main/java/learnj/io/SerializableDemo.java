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
 * @date	2013-1-26
 */
package learnj.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class Foo implements Serializable {
	private static final long serialVersionUID = -8869359977400146252L;
	private String name = "foo";
	private int size = 199;
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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		return String.format("[%s] name=%s, size=%d", getClass().getName() , name, size);
	}
}

/**
 * @brief SerializableDemo
 */
public class SerializableDemo {
	static final String FOO_SER = "target/foo.ser";
	
	static void write() {
		try {
			FileOutputStream fs = new FileOutputStream(FOO_SER);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			
			Foo obj = new Foo();
			os.writeObject(obj);
			os.close();
			System.out.println("write obj: " + obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void read() {
		try {
			FileInputStream fs = new FileInputStream(FOO_SER);
			ObjectInputStream os = new ObjectInputStream(fs);
			Object obj = os.readObject();
			os.close();
			System.out.println("read obj: " + obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("---SerializableDemo---");
		write();
		read();
	}

}

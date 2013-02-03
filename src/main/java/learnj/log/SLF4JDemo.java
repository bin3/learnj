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
 * @date	2013-2-3
 */
package learnj.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @brief SLF4JDemo
 */
public class SLF4JDemo {
	public static Logger log = LoggerFactory.getLogger(SLF4JDemo.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.debug("debug message");
		log.info("info message");
		log.error("error message");
	}

}

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
 * @date	2013-2-4
 */
package learnj.rpc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {

   public void startClient() {
       TTransport transport;
       try {
           transport = new TSocket("localhost", 1234);
           TProtocol protocol = new TBinaryProtocol(transport);
           Something.Client client = new Something.Client(protocol);
           transport.open();
           client.ping();
           transport.close();
       } catch (TTransportException e) {
           e.printStackTrace();
       } catch (TException e) {
           e.printStackTrace();
       }
   }

   public static void main(String[] args) {
       ThriftClient client = new ThriftClient();
       client.startClient();
   }
}
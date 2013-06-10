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
 * @date	2013-6-10
 */
package learnj.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * @brief LatchDemo
 */
public class LatchDemo {
  
  static public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
    final CountDownLatch startGate = new CountDownLatch(1);
    final CountDownLatch endGate = new CountDownLatch(nThreads);
    for (int i = 0; i < nThreads; ++i) {
      Thread t = new Thread() {
        public void run() {
          try {
            startGate.await();
            task.run();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            endGate.countDown();
          }
          
        }
      };
      t.start();
    }
    long start = System.nanoTime();
    startGate.countDown();
    endGate.await();
    long end = System.nanoTime();
    return end - start;
  }
  
  public static void run() {
    final int N = 1000000;
    final int nThreads = 8;
    Runnable task = new Runnable() {
      public void run() {
        int sum = 0;
        for (int i = 0; i < N; ++i) {
          sum += i;
        }
      }
    };
    try {
      long t = timeTasks(nThreads, task);
      System.out.println("N=" + N + ", nThreads=" + nThreads + ", time=" + t + 
        ", avg_time=" + (t / nThreads));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    run();
  }

}

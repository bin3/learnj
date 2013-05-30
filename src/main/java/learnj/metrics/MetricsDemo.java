/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * @author Binson Zhang <bin183cs@gmail.com>
 * @date 2013-5-30
 */
package learnj.metrics;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.*;

/**
 * @brief MetricsDemo
 */
public class MetricsDemo {
  final MetricRegistry metrics = new MetricRegistry();
  final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
      .convertRatesTo(TimeUnit.SECONDS)
      .convertDurationsTo(TimeUnit.MILLISECONDS)
      .build();

  private final Queue<String> queue = new LinkedList<String>();

  public MetricsDemo() {
    metrics.register(MetricRegistry.name(MetricsDemo.class, "queue", "size"), new Gauge<Integer>() {
      public Integer getValue() {
        return queue.size();
      }
    });
    reporter.start(2, TimeUnit.SECONDS);
  }

  private final Counter pendingJobs = metrics.counter(MetricRegistry.name(MetricsDemo.class,
      "pending-jobs"));

  public void addJob(String job) {
    pendingJobs.inc();
    queue.offer(job);
  }

  public String takeJob() {
    pendingJobs.dec();
    return queue.poll();
  }

  private final Meter requests = metrics.meter(MetricRegistry.name(MetricsDemo.class, "requests"));
  private final Histogram responseSizes = metrics.histogram(MetricRegistry.name(MetricsDemo.class,
      "response-sizes"));
  private final Timer responses = metrics
      .timer(MetricRegistry.name(MetricsDemo.class, "responses"));

  public void handleRequest(String request, String response) {
    final Timer.Context context = responses.time();
    try {
      requests.mark();
      long n = Math.round(Math.random() * 10);
      response = "";
      for (int i = 0; i < n; ++i) response += request;
      responseSizes.update(response.length());
      try {
        Thread.sleep(Math.round(Math.random() * 100));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } finally {
      context.stop();
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    final int N = 10;
    MetricsDemo demo = new MetricsDemo();
    String response = "";
    for (int i = 0; i < N; ++i) {
      System.out.println("Request#" + i);
      demo.addJob("Job" + i);
      demo.handleRequest("Request" + i, response);
      try {
        Thread.sleep(i * 500);
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
      if (Math.random() < 0.5) {
        demo.takeJob();
      }
    }
  }

}

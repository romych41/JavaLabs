package com.kpi.markushevskiy.lab1;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
		CouchbaseCluster cluster = CouchbaseCluster.create("127.0.0.1");
		cluster.authenticate("Administrator", "postgres");
		cluster.openBucket("entries");
	}

}

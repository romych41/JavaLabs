package com.kpi.markushevskiy.lab1.dao;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public abstract class AbstractDao {
    protected Bucket entries = CouchbaseCluster.create("127.0.0.1")
            .authenticate("Administrator", "postgres")
            .openBucket("entries");
}

package com.kpi.markushevskiy.lab1.dao;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.kpi.markushevskiy.lab1.model.EntryModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UploadDao {
    private Bucket entries = CouchbaseCluster.create("127.0.0.1")
            .authenticate("Administrator", "postgres")
            .openBucket("entries");

    public void addEntry(EntryModel entry){
        Map<String, Object> json = new HashMap<>();
        json.put("name", entry.getName());
        json.put("key", entry.getKey());
        JsonDocument document = JsonDocument.create(UUID.randomUUID().toString(), JsonObject.from(json));
        this.entries.insert(document);
    }
}

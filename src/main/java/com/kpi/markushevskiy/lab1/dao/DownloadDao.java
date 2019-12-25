package com.kpi.markushevskiy.lab1.dao;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.kpi.markushevskiy.lab1.model.EntryModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DownloadDao extends AbstractDao {
    public synchronized Integer getCountEntries(){
        N1qlQueryResult result = this.entries.query(N1qlQuery.simple("SELECT count(*) FROM entries"));
        N1qlQueryRow row = result.allRows().get(0);
        JsonObject json = row.value();
        return (int)json.get("$1");
    }

    public EntryModel getEntry(int offset){
        String query = "SELECT * FROM entries LIMIT 1 OFFSET $1";
        N1qlQueryResult result = this.entries.query(N1qlQuery.parameterized(query, JsonArray.from(offset)));
        if(result.allRows() == null && result.allRows().isEmpty())
            return null;
        JsonObject json = (JsonObject) result.allRows().get(0).value().get("entries");
        return new EntryModel(json.getString("name"), json.getInt("key"));
    }
}

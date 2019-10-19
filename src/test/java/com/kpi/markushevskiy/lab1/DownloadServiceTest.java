package com.kpi.markushevskiy.lab1;

import com.couchbase.client.java.document.json.JsonObject;
import com.kpi.markushevskiy.lab1.dao.DownloadDao;
import com.kpi.markushevskiy.lab1.model.EntryModel;
import com.kpi.markushevskiy.lab1.services.DownloadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

@TestComponent
@SpringBootTest
public class DownloadServiceTest {
    @Autowired
    private DownloadService downloadService;
    @Test
    public void testGetAllEntriesWithRXJava(){
        List<EntryModel> result = downloadService.getAllEntriesWithRXJava();
        int a=1;
    }
}

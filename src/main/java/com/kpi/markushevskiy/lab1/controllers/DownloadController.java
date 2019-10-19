package com.kpi.markushevskiy.lab1.controllers;

import com.couchbase.client.java.document.json.JsonObject;
import com.kpi.markushevskiy.lab1.dao.DownloadDao;
import com.kpi.markushevskiy.lab1.model.EntryModel;
import com.kpi.markushevskiy.lab1.services.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/download")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @GetMapping("/rxjava")
    public Map<String, ?> getWithRXJava(){
        Map<String, Object> modelMap = new HashMap<>();
        try {
            modelMap.put("data", downloadService.getAllEntriesWithRXJava());
            modelMap.put("succes", true);
        } catch (Exception e){
            modelMap.put("success", false);
        }
        return modelMap;
    }

    @GetMapping("/future")
    public Map<String, ?> getWithFuture(){
        Map<String, Object> modelMap = new HashMap<>();
        return modelMap;
    }

    @GetMapping("/synchronous")
    public Map<String, ?> getWithSynchronous(){
        Map<String, Object> modelMap = new HashMap<>();
        return modelMap;
    }

    @GetMapping("/singleThread")
    public Map<String, ?> getWithSingleThread(){
        Map<String, Object> modelMap = new HashMap<>();
        return modelMap;
    }
}

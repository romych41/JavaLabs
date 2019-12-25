package com.kpi.markushevskiy.lab1.controllers;

import com.kpi.markushevskiy.lab1.services.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        try {
            modelMap.put("data", downloadService.getAllEntriesWithFuture());
        } catch (Exception e) {
            modelMap.put("success", false);
        }
        return modelMap;
    }
}

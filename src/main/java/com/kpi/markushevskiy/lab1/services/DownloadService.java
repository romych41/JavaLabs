package com.kpi.markushevskiy.lab1.services;

import com.couchbase.client.java.document.json.JsonObject;
import com.kpi.markushevskiy.lab1.dao.DownloadDao;
import com.kpi.markushevskiy.lab1.model.EntryModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Service
public class DownloadService {
    private Logger logger = Logger.getLogger(DownloadService.class);
    @Autowired
    private DownloadDao downloadDao;
    public List<EntryModel> getAllEntriesWithRXJava(){
        List<EntryModel> result = new ArrayList<>();
        int count = downloadDao.getCountEntries();
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(count));
        Observable.range(0, count)
                .flatMap(integer -> Observable.just(integer)
                        .map(i -> {
                            logger.info("thread: " + Thread.currentThread().getName());
                            return downloadDao.getEntryForRXJava(i);})
                        .subscribeOn(scheduler))
                .blockingForEach(result::add);
        return result;
    }
}

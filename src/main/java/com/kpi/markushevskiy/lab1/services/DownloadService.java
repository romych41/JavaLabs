package com.kpi.markushevskiy.lab1.services;

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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class DownloadService {
    private Logger logger = Logger.getLogger(DownloadService.class);
    @Autowired
    private DownloadDao downloadDao;

    public List<EntryModel> getAllEntriesWithRXJava() {
        List<EntryModel> result = new ArrayList<>();
        int count = downloadDao.getCountEntries();
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(count));
        Observable.range(0, count)
                .flatMap(integer -> Observable.just(integer)
                        .map(i -> {
                            logger.info("thread: " + Thread.currentThread().getName());
                            return downloadDao.getEntry(i);
                        })
                        .subscribeOn(scheduler))
                .blockingForEach(result::add);
        return result;
    }
    public List<EntryModel> getAllEntriesWithFuture() throws Exception {
        List<EntryModel> result = new ArrayList<>();
        int count = downloadDao.getCountEntries();
        List<Integer> indexes = new ArrayList<>();
        List<CompletableFuture<EntryModel>> futures = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            indexes.add(i);
        }
        for (final int i : indexes) {
            CompletableFuture<EntryModel> future = CompletableFuture.supplyAsync(() -> {
                logger.info("thread: " + Thread.currentThread().getName());
                return downloadDao.getEntry(i);
            });
            future.thenAccept(entryModel -> {
                result.add(entryModel);
            });
            futures.add(future);
        }
        CompletableFuture[] array = new CompletableFuture[futures.size()];
        futures.toArray(array);
        futures.clear();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(array);
        CompletableFuture<List<EntryModel>> listCompletableFuture = voidCompletableFuture.thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
        result.addAll(listCompletableFuture.get());
        return result;
    }
}

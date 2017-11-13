package com.sysc3010.m7.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

public class ScheduleManager {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    public ScheduleManager() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("1: " + new java.util.Date());

        }, 0, 1, TimeUnit.SECONDS);
    }

    
    @PreDestroy
    public void shutDownScheduleManager() {
        scheduler.shutdownNow();
    }

}

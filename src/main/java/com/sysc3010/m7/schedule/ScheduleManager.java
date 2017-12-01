package com.sysc3010.m7.schedule;

import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysc3010.m7.udp.onePi;

@Service
public class ScheduleManager {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    @Autowired
    onePi sender;

    public ScheduleManager() {
        scheduler.scheduleAtFixedRate(() -> {

            System.out.println("1: " + new java.util.Date());
            try {
                sender.sendto("10.0.0.2");
            } catch (UnknownHostException e) {
                // TODO Handle udp errors. Should mark patient's dispenser as needing service
                e.printStackTrace();
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void shutDownScheduleManager() {
        scheduler.shutdownNow();
    }

}

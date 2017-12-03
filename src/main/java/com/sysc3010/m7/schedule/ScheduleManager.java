package com.sysc3010.m7.schedule;

import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysc3010.m7.service.DatabaseService;
import com.sysc3010.m7.udp.onePi;

import server.Medication;

@Service
public class ScheduleManager {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    @Autowired
    private onePi sender;
    @Autowired
    private DatabaseService databaseService;

    private String dispenserIp = "10.0.0.2";
    
    public ScheduleManager() {
        
        
        scheduler.scheduleAtFixedRate(() -> {

            System.out.println("1: " + new java.util.Date());
            try {
                List<Medication> meds = databaseService.getMedsToBeDispensed();
                if (meds.size() >= 1) {
                    sender.sendto(dispenserIp);
                }
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

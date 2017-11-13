package com.sysc3010.m7.config;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sysc3010.m7.schedule.ScheduleManager;

@Configuration
public class ScheduleConfig {

    
    
    @Bean
    public ScheduleManager scheduleManager() {
        return new ScheduleManager();
    }
    

}

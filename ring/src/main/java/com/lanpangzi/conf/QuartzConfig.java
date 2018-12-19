package com.lanpangzi.conf;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class QuartzConfig {
    @Scheduled(fixedRate = (5000))
    public void work(){
        System.out.println("timer");
    }
}

package com.guilherme.miguel.config;

import com.guilherme.miguel.service.SchedulerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Miguel Guilherme
 */
@Component
public class DataLoader {

    private SchedulerService schedulerService;

    public DataLoader(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostConstruct
    public void init() {
        schedulerService.add("XPTO1");
        schedulerService.add("XPTO2");
        schedulerService.add("XPTO3");
    }

}

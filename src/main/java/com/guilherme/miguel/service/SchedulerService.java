package com.guilherme.miguel.service;

import com.guilherme.miguel.domain.Action;
import com.guilherme.miguel.repository.ActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Miguel Guilherme
 */
@Service
public class SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ActionRepository actionRepository;

    private Map<String, ScheduledFuture> threadPool;

    public SchedulerService(ThreadPoolTaskScheduler threadPoolTaskScheduler, ActionRepository actionRepository) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.actionRepository = actionRepository;
        threadPool = new HashMap<>();
    }

    public boolean add(String name) {
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(
                () -> logger.info("Running " + name),
                5000);

        threadPool.put(name, scheduledFuture);
        actionRepository.save(new Action(name));
        return true;
    }

    public boolean remove(String name) {
        if (!threadPool.containsKey(name)) {
            logger.info(name + " not found...");
            return false;
        }

        ScheduledFuture scheduledFuture = threadPool.get(name);
        scheduledFuture.cancel(true);

        if (scheduledFuture.isCancelled()) {
            threadPool.remove(name);
            actionRepository.deleteByName(name);
            logger.info(name + " removed...");
            return true;
        }
        return false;
    }

}

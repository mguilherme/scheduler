package com.guilherme.miguel.controller;

import com.guilherme.miguel.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Miguel Guilherme
 */
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private SchedulerService schedulerService;

    public HomeController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @RequestMapping(value = "/add/{name}")
    public String add(@PathVariable String name) {
        boolean added = schedulerService.add(name);
        return name + (added ? "" : " not") + " added";
    }

    @RequestMapping(value = "/remove/{name}")
    public String remove(@PathVariable String name) {
        boolean removed = schedulerService.remove(name);
        return name + (removed ? "" : " not") + " removed";
    }

}

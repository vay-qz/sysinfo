package pers.vay.controller;

import pers.vay.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ThreadInfo;

/**
 * @author qiaozhe
 */
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("sys/application-info")
    public Object application() {
        return applicationService.getApplicationInfo();
    }

    @PutMapping("application-info")
    public void gc() {
        applicationService.gc();
    }

    @GetMapping("/application-info/thread/{threadids}/{depth}")
    public ThreadInfo[] threadInfo(
            @PathVariable("threadid")long[] threadid,
            @PathVariable("depth")int depth) {
        return applicationService.getThreadInfo(threadid, depth);
    }

}

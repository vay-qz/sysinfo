package pers.vay.controller;

import pers.vay.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaozhe
 */
@RestController
public class SpringInjectController {

    @Autowired
    private SpringService service;

    @GetMapping("sys/spring-info/pool/db")
    public Object dbPool() {
        return service.getDbPool();
    }

    @GetMapping("spring-info/properties")
    public Object properties() {
        return service.properties();
    }

}

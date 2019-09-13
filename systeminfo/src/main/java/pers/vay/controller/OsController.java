package pers.vay.controller;

import pers.vay.service.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaozhe
 */
@RestController
public class OsController {

    @Autowired
    private OsService osService;

    @GetMapping("sys/os-info")
    public Object os() {
        return osService.getOsInfo();
    }
}

package pers.vay.controller;

import pers.vay.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaozhe
 */
@RestController
public class NetController {

    @Autowired
    private NetworkService networkService;

    @GetMapping("sys/net-info")
    public Object networkInfo() {
        return networkService.getNetworkInfo();
    }

}

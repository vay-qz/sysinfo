package pers.vay.controller;

import pers.vay.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaozhe
 */
@RestController
public class DownloadController {

    @Autowired
    private DownloadService service;

    @GetMapping(value = "sys/download")
    public ResponseEntity<byte[]> download() {
        return service.download();
    }

}

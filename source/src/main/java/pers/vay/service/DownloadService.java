package pers.vay.service;

import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author qiaozhe
 */
@Service
public class DownloadService {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private OsService osService;
    @Autowired
    private SpringService springService;

    private static final String DIVIDING_BEFORE_LINE = "\r\n====================================================================================";
    private static final String DIVIDING_AFTER_LINE = "====================================================================================\r\n";

    private static final byte[][] NAMES = {
            (DIVIDING_BEFORE_LINE + "\r\n============================================application=============================\r\n" + DIVIDING_AFTER_LINE).getBytes(),
            (DIVIDING_BEFORE_LINE + "\r\n========================================network=====================================\r\n" + DIVIDING_AFTER_LINE).getBytes(),
            (DIVIDING_BEFORE_LINE + "\r\n========================================os==========================================\r\n" + DIVIDING_AFTER_LINE).getBytes(),
            (DIVIDING_BEFORE_LINE + "\r\n========================================spring======================================\r\n" + DIVIDING_AFTER_LINE).getBytes(),
            (DIVIDING_BEFORE_LINE + "\r\n========================================version=====================================\r\n" + DIVIDING_AFTER_LINE).getBytes()
    };

    private static final int NAMES_LENGTH;

    static {
        int tmp = 0;
        for(byte[] name : NAMES) {
            tmp += name.length;
        }
        NAMES_LENGTH = tmp;
    }


    public ResponseEntity<byte[]> download() {
        byte[] application = JsonUtils.prettyPrint(applicationService.getApplicationInfo().toString()).getBytes();
        byte[] network = JsonUtils.prettyPrint(networkService.getNetworkInfo().toString()).getBytes();
        byte[] os = JsonUtils.prettyPrint(osService.getOsInfo().toString()).getBytes();
        byte[] spring = JsonUtils.prettyPrint(springService.getDbPool().toString()).getBytes();

        byte[] bytes = mergeInfos(application, network, os, spring);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=" + UUID.randomUUID() + ".txt");
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    private byte[] mergeInfos(byte[]... bytes) {
        int sum = 0;
        for(byte[] bytes1 : bytes) {
            sum += bytes1.length;
        }
        sum += NAMES_LENGTH;
        byte[] res = new byte[sum];
        int index = 0;
        for(int i = 0; i < bytes.length; i++) {
            System.arraycopy(NAMES[i], 0, res, index, NAMES[i].length);
            index += NAMES[i].length;
            byte[] bytes1 = bytes[i];
            System.arraycopy(bytes1, 0, res, index, bytes1.length);
            index += bytes1.length;
        }
        return res;
    }

}

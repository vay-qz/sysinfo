package pers.vay.service;

import pers.vay.utils.NetworkInterfaceUtils;
import org.springframework.stereotype.Service;

/**
 * @author qiaozhe
 */
@Service
public class NetworkService {

    public Object getNetworkInfo() {
        return NetworkInterfaceUtils.getNetworkInfo();
    }

}

package pers.vay.utils;

import java.net.*;
import java.util.*;

/**
 * @author qiaozhe
 */
public class NetworkInterfaceUtils {

    public static Map<String, Map<String, Object>> getNetworkInfo() {
        Map<String, Map<String, Object>> networksInfo = new HashMap<>();
        Enumeration<NetworkInterface> interfaceEnumeration = null;
        try {
            interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
        while(interfaceEnumeration.hasMoreElements()) {
            Map<String, Object> tmp = new HashMap<>();
            NetworkInterface networkInterface = interfaceEnumeration.nextElement();
            try {
                if(networkInterface.getMTU() == -1) {
                    continue;
                }
            } catch (SocketException e) {
                return null;
            }
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while(addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if(!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
                    tmp.put("IP", ip.getHostAddress());
                    break;
                }
            }
            tmp.put("网络接口名称", networkInterface.getName());
            try {
                tmp.put("MAC地址", format(networkInterface.getHardwareAddress()));
                tmp.put("最大传输单元", networkInterface.getMTU());
            } catch (SocketException e) {
                return null;
            }
            networksInfo.put(networkInterface.getDisplayName(), tmp);
        }
        return networksInfo;
    }

    private static Object format(byte[] hardwareAddress) {
        if(hardwareAddress == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<hardwareAddress.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = hardwareAddress[i]&0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

}

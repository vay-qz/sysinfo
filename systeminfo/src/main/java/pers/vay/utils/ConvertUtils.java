package pers.vay.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author qiaozhe
 */
public class ConvertUtils {

    private static final double MB = 1024.0 * 1024.0;
    private static final double GB = 1024.0 * 1024.0 * 1024.0;


    public static String byte2MB(long b) {
        if(b == -1) {
            return "-1";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(b/MB) + "MB";
    }

    public static String byte2GB(long b) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(b/GB) + "GB";
    }

    public static String start2now(long start) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(start);
    }

    public static String ms2min(long ms) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(ms / 1000.0 / 60.0);
    }

    public static String decimal2percent(long d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d * 100.0) + "%";
    }

    public static String milli2second(long mi) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(mi/1000.0) + "s";
    }

    public static String substring(String src, int start_idx, int end_idx){
        byte[] b = src.getBytes();
        String tgt = "";
        for(int i=start_idx; i<=end_idx; i++){
            tgt +=(char)b[i];
        }
        return tgt;
    }

}

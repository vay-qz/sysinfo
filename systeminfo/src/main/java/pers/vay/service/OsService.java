package pers.vay.service;

import pers.vay.utils.ManagementFactoryUtils;
import org.springframework.stereotype.Service;
import pers.vay.utils.ConvertUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author qiaozhe
 */
@Service
public class OsService {

    private final int totleLength = 20;

    public Map<String, Object> getOsInfo() {
        Map<String, Object> res = new HashMap<>(16);
        res.put("操作系统", ManagementFactoryUtils.getOsInfo());
        res.put("环境变量", ManagementFactoryUtils.getSystemInfo());
        res.put("磁盘空间", getDiskInfo());
        if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
            res.put("CPU使用情况", getLinuxCPUInfo());
        }
        return res;
    }

    private Object getLinuxCPUInfo() {
        Map<String, Object> res = new HashMap<>();
        LinkedList<String> source = getSourceData("top -b -n 1");
        int pidPosi = 0;
        int userPosi = 1;
        int cpuPosi = 8;
        int commandPosi = 11;
        while (!source.isEmpty()) {
            String s = source.pop();
            if (s.length() == 0) {
                continue;
            }
            String[] strs = s.split(" ");
            if (strs.length == 0) {
                continue;
            }
            List<String> filter = filterEmpty(strs);
            if (filter.get(pidPosi).toLowerCase().equals("pid")) {
                StringBuilder builder = new StringBuilder();
                builder.append(filter.get(userPosi)).append(" ")
                        .append(filter.get(cpuPosi)).append(" ")
                        .append(filter.get(commandPosi)).append(" ");
                res.put(filter.get(pidPosi), builder);
                break;
            }
        }
        while (!source.isEmpty()) {
            String[] strs = source.pop().split(" ");
            List<String> filter = filterEmpty(strs);
            if (Double.parseDouble(filter.get(cpuPosi)) == 0) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append(filter.get(userPosi)).append(" ")
                    .append(filter.get(cpuPosi)).append(" ")
                    .append(filter.get(commandPosi)).append(" ");
            res.put(filter.get(pidPosi), builder.toString());
        }
        return res;
    }

    private List<String> filterEmpty(String[] strs) {
        List<String> strings = new ArrayList<>();
        for (String str : strs) {
            if (!str.equals("")) {
                strings.add(str);
            }
        }
        return strings;
    }


    private Object getDiskInfo() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            return getWindowsDiskInfo();
        }else if (System.getProperty("os.name").toLowerCase().startsWith("linux")){
            return getLinuxDiskInfo();
        }else {
            return "os unrecognized 未识别的系统";
        }
    }

    private Object getLinuxDiskInfo() {
        Map<String, Object> res = new HashMap<>();
        LinkedList<String> source = getSourceData("df -hl");
        if (source == null) {
            return null;
        }
        for (String str : source) {
            String[] t = str.split(" ");
            StringBuilder value = new StringBuilder();
            for (int i = 0; i < t.length - 1; i++) {
                value.append(t[i] + "-");
            }
            res.put(parse(t[t.length - 1]), value.toString());
        }
        return res;
    }

    private String parse(String s) {
        int suffix = totleLength - s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < suffix; i++) {
            builder.append("-");
        }
        builder.append(s);
        return builder.toString();
    }

    private LinkedList<String> getSourceData(String cmd) {
        Runtime run = Runtime.getRuntime();
        LinkedList<String> list = new LinkedList<>();
        try {
            Process process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            String result ;
            while ((result = bs.readLine()) != null) {
                list.add(result);
            }
            in.close();
            process.destroy();
            return list;
        }catch (Exception e) {
            return null;
        }
    }

    private Object getWindowsDiskInfo() {
        Map<String, Map<String, String>> disk = new HashMap<>();
        File[] files = File.listRoots();
        for(File file : files) {
            Map<String, String> info = new HashMap<>();
            info.put("总容量", ConvertUtils.byte2GB(file.getTotalSpace()));
            info.put("可使用容量", ConvertUtils.byte2GB(file.getUsableSpace()));
            disk.put(file.getPath(), info);
        }
        return disk;
    }


}

package pers.vay.service;

import pers.vay.utils.ManagementFactoryUtils;
import org.springframework.stereotype.Service;

import java.lang.management.ThreadInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaozhe
 */
@Service
public class ApplicationService {

    public Map<String, Object> getApplicationInfo() {
        Map<String, Object> res = new HashMap<>(16);
        res.put("编译信息", ManagementFactoryUtils.getCompilationInfo());
        res.put("类加载信息", ManagementFactoryUtils.getClassLoadingInfo());
        res.put("应用内存信息", ManagementFactoryUtils.getApplicationMemoryInfo());
        res.put("运行时系统信息", ManagementFactoryUtils.getRuntimeInfo());
        // todo 暂时不知道是干啥用的，先注释掉
//        res.put("log信息", ManagementFactoryUtils.getLogInfo());
        res.put("线程信息", ManagementFactoryUtils.getThreadInfo());
        res.put("垃圾回收信息", ManagementFactoryUtils.getGCInfo());
        res.put("堆内存信息", ManagementFactoryUtils.getMemoryPoolInfo());
        res.put("直接缓存池信息", ManagementFactoryUtils.getBufferPoolInfo());
        return res;
    }

    public void gc() {
        System.gc();
    }

    public ThreadInfo[] getThreadInfo(long[] infos, int depth) {
        return ManagementFactoryUtils.getThreadInfos(infos, depth);
    }

}

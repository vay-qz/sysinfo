package pers.vay.utils;

import io.micrometer.core.instrument.util.StringUtils;

import java.lang.management.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiaozhe
 */
public class ManagementFactoryUtils {

    public static Map<String, Object> getOsInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        obj.put("操作系统体系结构", operatingSystemMXBean.getArch());
        obj.put("可用核心数", operatingSystemMXBean.getAvailableProcessors());
        obj.put("系统名称", operatingSystemMXBean.getName());
        obj.put("操作系统版本", operatingSystemMXBean.getVersion());
        obj.put("已提交的虚拟内存", ConvertUtils.byte2MB(operatingSystemMXBean.getCommittedVirtualMemorySize()));
        obj.put("空闲内存", ConvertUtils.byte2MB(operatingSystemMXBean.getFreePhysicalMemorySize()));
        obj.put("空闲交换空间", ConvertUtils.byte2MB(operatingSystemMXBean.getFreeSwapSpaceSize()));
        obj.put("总内存", ConvertUtils.byte2GB(operatingSystemMXBean.getTotalPhysicalMemorySize()));
        obj.put("总交换空间", ConvertUtils.byte2GB(operatingSystemMXBean.getTotalSwapSpaceSize()));
        return obj;
    }

    public static Map<String, Object> getCompilationInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        if(compilationMXBean.isCompilationTimeMonitoringSupported()) {
            obj.put("编译时间(ms)", compilationMXBean.getTotalCompilationTime());
        }
        obj.put("JIT编译器", compilationMXBean.getName());
        return obj;
    }

    public static Map<String, Object> getClassLoadingInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        obj.put("JVM启动后的总加载类数量", classLoadingMXBean.getTotalLoadedClassCount());
        obj.put("当前加载类数量", classLoadingMXBean.getLoadedClassCount());
        obj.put("JVM启动后的总卸载类数量", classLoadingMXBean.getUnloadedClassCount());
        obj.put("是否启用类加载系统的详细输出", classLoadingMXBean.isVerbose());
        return obj;
    }

    public static Map<String, Object> getApplicationMemoryInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        obj.put("终止被挂起的对象的近似数目", memoryMXBean.getObjectPendingFinalizationCount());
        obj.put("verbose 输出是否已启用", memoryMXBean.isVerbose());

        Map<String, Object> heapUsage = new HashMap<>();
        MemoryUsage heap = memoryMXBean.getHeapMemoryUsage();
        heapUsage.put("初始化申请容量", ConvertUtils.byte2MB(heap.getInit()));
        heapUsage.put("已使用容量", ConvertUtils.byte2MB(heap.getUsed()));
        heapUsage.put("已提交容量", ConvertUtils.byte2MB(heap.getCommitted()));
        heapUsage.put("最大容量（-1代表未设置）", ConvertUtils.byte2MB(heap.getMax()));
        obj.put("堆内存使用情况", heapUsage);

        Map<String, Object> nonHeapUsage = new HashMap<>();
        MemoryUsage nonHeap = memoryMXBean.getNonHeapMemoryUsage();
        nonHeapUsage.put("初始化申请容量", ConvertUtils.byte2MB(nonHeap.getInit()));
        nonHeapUsage.put("已使用容量", ConvertUtils.byte2MB(nonHeap.getUsed()));
        nonHeapUsage.put("已提交容量", ConvertUtils.byte2MB(nonHeap.getCommitted()));
        nonHeapUsage.put("最大容量（-1代表未设置）", ConvertUtils.byte2MB(nonHeap.getMax()));
        obj.put("非堆内存使用情况", nonHeapUsage);

        return obj;
    }

    public static Map<String, Object> getRuntimeInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        obj.put("JVM名称", runtimeMXBean.getName());
        obj.put("JVM管理接口的规范版本", runtimeMXBean.getManagementSpecVersion());
        obj.put("JVM正常运行时间(min)", ConvertUtils.ms2min(runtimeMXBean.getUptime()));
        obj.put("JVM启动时间", ConvertUtils.start2now(runtimeMXBean.getStartTime()));
        if(runtimeMXBean.isBootClassPathSupported()) {
//            obj.put("引导类加载器加载路径", runtimeMXBean.getBootClassPath());
        }

        try {
            obj.put("JVM实现名称", runtimeMXBean.getVmName());
            obj.put("JVM实现供应商", runtimeMXBean.getVmVendor());
            obj.put("JVM实现版本", runtimeMXBean.getVmVersion());
            obj.put("JVM规范名称", runtimeMXBean.getSpecName());
            obj.put("JVM规范供应商", runtimeMXBean.getSpecVendor());
            obj.put("JVM规范版本", runtimeMXBean.getSpecVersion());
            obj.put("JVM入参", runtimeMXBean.getInputArguments());
            //TODO 打印信息过多，后续整理好格式再放开
//            obj.put("ClassPath", runtimeMXBean.getClassPath());
//            obj.put("JAVA库路径", runtimeMXBean.getLibraryPath());
//            obj.put("系统属性", runtimeMXBean.getSystemProperties());
        }catch (SecurityException e) {
            //do nothing
        }

        return obj;
    }

    public static Object getSystemInfo() {
        return System.getenv();
    }

    public static Map<String, Object> getLogInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        PlatformLoggingMXBean logging = ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);
        for(String name : logging.getLoggerNames()) {
            if(StringUtils.isNotBlank(logging.getLoggerLevel(name)) && StringUtils.isNotBlank(name)) {
                obj.put(name, logging.getLoggerLevel(name));
            }
        }
        return obj;
    }

    public static Map<String, Object> getThreadInfo() {
        Map<String, Object> obj = new HashMap<>(16);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        obj.put("守护线程数量", threadMXBean.getDaemonThreadCount());
        obj.put("峰值活动线程数", threadMXBean.getPeakThreadCount());
        obj.put("活动线程数", threadMXBean.getThreadCount());
        obj.put("自JVM启动以来的线程总数", threadMXBean.getTotalStartedThreadCount());
        try {
            if(threadMXBean.isSynchronizerUsageSupported()) {
                obj.put("死锁线程id（从死循环中获取资源）", threadMXBean.findDeadlockedThreads());
            }
            obj.put("死锁线程id（互相占据资源）", threadMXBean.findMonitorDeadlockedThreads());
        } catch (SecurityException e) {
            //do nothing
        }

        return obj;
    }

    public static ThreadInfo[] getThreadInfos(long[] infos, int depth) {
        try {
            return ManagementFactory.getThreadMXBean().getThreadInfo(infos, depth);
        }catch (SecurityException e) {
            return null;
        }
    }


    public static Map<String, Map<String, Object>> getGCInfo() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        Map<String, Map<String, Object>> infos = new HashMap<>();
        for(GarbageCollectorMXBean bean : garbageCollectorMXBeans) {
            Map<String, Object> info = new HashMap<>();
            info.put("垃圾回收次数", bean.getCollectionCount());
            info.put("垃圾回收时间(ms)", bean.getCollectionTime());
            info.put("内存管理名称", bean.getName());
            info.put("内存池名称", bean.getMemoryPoolNames());
            infos.put(bean.getName(), info);
        }
        return infos;
    }

    public static List<Map<String, Object>> getBufferPoolInfo() {
        List<BufferPoolMXBean> pools = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
        List<Map<String, Object>> infos = new ArrayList<>();
        for(BufferPoolMXBean bean : pools) {
            Map<String, Object> info = new HashMap<>();
            info.put("已使用內存", bean.getMemoryUsed());
            info.put("区域名称", bean.getName());
            info.put("总大小", bean.getTotalCapacity());
            info.put("数量", bean.getCount());
        }
        return infos;
    }

    public static Map<String, Map<String, Object>>  getMemoryPoolInfo() {
        List<MemoryPoolMXBean> mxBeans = ManagementFactory.getMemoryPoolMXBeans();
        Map<String, Map<String, Object>> infos = new HashMap<>();
        for(MemoryPoolMXBean bean : mxBeans) {
            Map<String, Object> info = new HashMap<>();
            info.put("使用情况", bean.getCollectionUsage());
            if(bean.isCollectionUsageThresholdSupported()) {
                info.put("回收使用量阈值", bean.getCollectionUsageThreshold());
                info.put("达到或超过回收使用量阈值的次数", bean.getCollectionUsageThresholdCount());
            }
            if(bean.isUsageThresholdSupported()) {
                info.put("内存池的使用量阈值", bean.getUsageThreshold());
                info.put("达到或超过内存池的使用量阈值", bean.getUsageThresholdCount());
            }
            info.put("峰值内存使用情况", bean.getPeakUsage());
            info.put("类型", bean.getType());
            infos.put(bean.getName(), info);
        }
        return infos;
    }

    public static void main(String[] args) {
        Map<String, String> s = System.getenv();
        for(Map.Entry<String, String> entry : s.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}

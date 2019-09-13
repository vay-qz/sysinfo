package pers.vay.dbpool;

import org.springframework.stereotype.Component;

/**
 * @param DruidPool
 * @author qiaozhe
 */
@Component
public class DruidPool extends AbstractPoolMethod {

    static {
        dbPoolMethodName.put("getUsername", "用户名");
        dbPoolMethodName.put("getUrl", "URL");
        dbPoolMethodName.put("getDriverClassName", "数据库驱动");

        dbPoolMethodName.put("getMinIdle", "最小空闲连接数");
        dbPoolMethodName.put("getInitialSize", "初始化连接数");
        dbPoolMethodName.put("getMaxActive", "最大连接数");
        dbPoolMethodName.put("getMaxWait", "最长等待时间");

        dbPoolMethodName.put("isPoolPreparedStatements", "预处理语句池是否生效(PoolPreparedStatements)");
        dbPoolMethodName.put("getMaxOpenPreparedStatements", "在语句池中同时分配的最大语句数（-1代表不限制）（MaxOpenPreparedStatements）");

        dbPoolMethodName.put("getActiveCount", "当前忙碌的连接数(ActiveCount)");
        dbPoolMethodName.put("getActivePeak", "ActivePeak");
        dbPoolMethodName.put("getActivePeakTime", "ActivePeakTime");
        dbPoolMethodName.put("getCloseCount", "已关闭的连接数（CloseCount）");
        dbPoolMethodName.put("getConnectCount", "当前连接数");
        dbPoolMethodName.put("getConnectErrorCount", "连接错误数(ConnectErrorCount)");
        dbPoolMethodName.put("getDestroyCount", "销毁连接数（DestroyCount）");
        dbPoolMethodName.put("getCreateCount", "创建连接数(CreateCount)");
        dbPoolMethodName.put("getDiscardCount", "抛弃连接数(DiscardCount)");
        dbPoolMethodName.put("getErrorCount", "产生错误的连接数(ErrorCount)");
        dbPoolMethodName.put("getPoolingCount", "PoolingCount");
        dbPoolMethodName.put("getRecycleCount", "回收连接数(RecycleCount)");
        dbPoolMethodName.put("getWaitThreadCount", "等待的线程数（WaitThreadCount）");
    }
}

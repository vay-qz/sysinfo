package pers.vay.dbpool;

import org.springframework.stereotype.Component;

/**
 * @param Dbcp2P00l
 * @author qiaozhe
 */
@Component
public class Dbcp2Pool extends AbstractPoolMethod {

    static {
        dbPoolMethodName.put("getUsername", "用户名");
        dbPoolMethodName.put("getUrl", "URL");
        dbPoolMethodName.put("getDriverClassName", "数据库驱动");

        dbPoolMethodName.put("getDefaultAutoCommit", "默认是否自动提交");
        dbPoolMethodName.put("getMinIdle", "最小空闲数（MinIdle）");
        dbPoolMethodName.put("getMaxWaitMillis", "最长等待时间(MaxWaitMillis)");
        dbPoolMethodName.put("getCacheState", "状态缓存标志（CacheState）");
        dbPoolMethodName.put("getDefaultCatalog", "默认目录(DefaultCatalog)");
        dbPoolMethodName.put("getDefaultQueryTimeout", "默认查询超时时间（s）（DefaultQueryTimeout）");
        dbPoolMethodName.put("getDefaultTransactionIsolation", "默认事务隔离状态（DefaultTransactionIsolation）");
        dbPoolMethodName.put("getAutoCommitOnReturn", "连接归还到池时，设置为自动提交(AutoCommitOnReturn)");
        dbPoolMethodName.put("getDefaultSchema", "默认schema");
        dbPoolMethodName.put("getEvictionPolicyClassName", "最长等待时间（EvictionPolicyClassName）");
        dbPoolMethodName.put("getFastFailValidation", "是否快速失败（FastFailValidation）");
        dbPoolMethodName.put("getInitialSize", "初始化大小");
        dbPoolMethodName.put("getLifo", "是否LIFO获取连接");
        dbPoolMethodName.put("getMaxConnLifetimeMillis", "连接存活时间（ms，-1代表不限制）");
        dbPoolMethodName.put("isPoolPreparedStatements", "预处理语句池是否生效（PoolPreparedStatements）");
        dbPoolMethodName.put("getMaxOpenPreparedStatements", "在语句池中同时分配的最大语句数（-1代表不限制）（MaxOpenPreparedStatements）");

        dbPoolMethodName.put("getNumActive", "当前连接数");
        dbPoolMethodName.put("getNumIdle", "当前空闲数");
    }

}

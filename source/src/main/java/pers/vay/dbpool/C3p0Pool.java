package pers.vay.dbpool;

import org.springframework.stereotype.Component;

/**
 * @author qiaozhe
 */
@Component
public class C3p0Pool extends AbstractPoolMethod{

    static {
        putMethod();
    }

    static void putMethod() {
        dbPoolMethodName.put("getUser", "用户名");
        dbPoolMethodName.put("getJdbcUrl", "URL");
        dbPoolMethodName.put("getDriverClass", "数据库驱动");

        dbPoolMethodName.put("getAcquireIncrement", "获得连接数增长量");
        dbPoolMethodName.put("getAcquireRetryAttempts", "获得连接数尝试次数");
        dbPoolMethodName.put("getAcquireRetryDelay", "获得连接数尝试间隔");
        dbPoolMethodName.put("getCheckoutTimeout", "检查超时时间");
        dbPoolMethodName.put("getIdleConnectionTestPeriod", "空闲连接测试间隔");
        dbPoolMethodName.put("getInitialPoolSize", "连接池初始化大小");
        dbPoolMethodName.put("getMaxIdleTime", "连接最大空闲时间");
        dbPoolMethodName.put("getMaxPoolSize", "连接池最大连接数");
        dbPoolMethodName.put("getMinPoolSize", "连接池最小连接数");
        dbPoolMethodName.put("getNumBusyConnections", "当前忙碌的连接数");
        dbPoolMethodName.put("getNumBusyConnectionsAllUsers", "当前所有用户忙碌的连接数");
        dbPoolMethodName.put("getNumConnections", "当前所有连接数");
        dbPoolMethodName.put("getNumConnectionsAllUsers", "当前所有用户的所有连接数");
        dbPoolMethodName.put("getNumIdleConnections", "当前空闲的连接数");
        dbPoolMethodName.put("getNumIdleConnectionsAllUsers", "当前所有用户空闲的连接数");
        dbPoolMethodName.put("isAutoCommitOnClose", "在关闭时是否自动提交");
        dbPoolMethodName.put("isBreakAfterAcquireFailure", "获取连接失败时是否中断");
        dbPoolMethodName.put("isForceIgnoreUnresolvedTransactions", "是否强制忽略未解决的事务");
    }

}

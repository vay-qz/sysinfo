package pers.vay.dbpool;

import org.springframework.stereotype.Component;

/**
 * @author qiaozhe
 */
@Component
public class HikaricpPool extends AbstractPoolMethod {

    static {
        dbPoolMethodName.put("getUsername", "用户名");
        dbPoolMethodName.put("getJdbcUrl", "URL");
        dbPoolMethodName.put("getDriverClassName", "数据库驱动");

        dbPoolMethodName.put("getMinimumIdle", "最小空闲连接数(MinimumIdle)");
        dbPoolMethodName.put("getMaximumPoolSize", "最大连接数(MaximumPoolSize)");
        dbPoolMethodName.put("getMaxLifetime", "最长生命周期(MaxLifetime)");
        dbPoolMethodName.put("getIdleTimeout", "最长空闲周期（IdleTimeout）");
        dbPoolMethodName.put("isAutoCommit", "是否自动提交");
        dbPoolMethodName.put("getConnectionTimeout", "连接池连接超时时间(ConnectionTimeout)");
    }

}

package pers.vay.service;

import pers.vay.dbpool.AbstractPoolMethod;
import pers.vay.utils.ConvertUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qiaozhe
 */
@Service
public class SpringService implements ApplicationContextAware {

    @Autowired
    private Environment environment;

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public Object getDbPool() {
        Map<String, Object> ress = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        DataSource obj = (DataSource) context.getBean("dataSource");
        if(obj == null) {
            return null;
        }
        Class cls = obj.getClass();
        res.put("连接池类型", cls.getName());
        Method[] methods = cls.getMethods();
        for(Method m : methods) {
            if(!AbstractPoolMethod.dbPoolMethodName.containsKey(m.getName())) {
                continue;
            }
            if (m.getParameterTypes() != null
                    && m.getParameterTypes().length > 0) {
                continue;
            }
            try {
                if(m.getName().equals("ActivePeakTime")) {
                    res.put(AbstractPoolMethod.dbPoolMethodName.get(m.getName()), ConvertUtils.start2now((long)m.invoke(obj, null)));
                }else {
                    res.put(AbstractPoolMethod.dbPoolMethodName.get(m.getName()), m.invoke(obj, null));
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        ress.put("数据库信息", res);

        return ress;
    }


    public Object properties() {
        Map<String, Object> res = new HashMap<>();
        StandardEnvironment env = (StandardEnvironment) environment;
        Iterator<PropertySource<?>> iterator = env.getPropertySources().iterator();
        while(iterator.hasNext()) {
            PropertySource<?> source = iterator.next();
            Map<String, String> m = new HashMap<>(128);
            String name = source.getName();
            Object o = source.getSource();
            if (o instanceof Map) {
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) o).entrySet()) {
                    String key = entry.getKey();
                    m.put(key, env.getProperty(key));
                }
            }
            res.put(name, m);
        }
        return res;
    }

}

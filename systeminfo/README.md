# SystemInfo

## 展示明细

- 应用信息
  - 垃圾回收
  - 编译
  - 运行时系统信息
  - 线程信息
  - 类加载信息
  - 应用内存信息
  - 堆内存信息
- 版本信息
- 网卡信息
- 操作系统信息
  - 磁盘空间
  - 环境变量
  - 系统参数
- spring注入类信息
  - 数据库连接池信息
- 上述信息一键下载
- springboot-actuator

## 支持工程类型

- SpringBoot

## 相关配置

```properties
# springboot-actuator ‘*’代表全部开启
management.endpoints.web.exposure.include=*

# jdbc相关，需要去查询version信息
spring.datasource.driver-class-name=driver-class-name
spring.datasource.url=jdbc:mysql://ip:port/applicationName
spring.datasource.username=username
spring.datasource.password=password
```

## 过滤设置

```java
web.ignoring()
    .antMatchers("/info/**")
    .antMatchers("/infos.html")
```


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
- 网卡信息
- 操作系统信息
  - 磁盘空间
  - CPU情况（Linux）
  - 环境变量
  - 系统参数
- spring注入类信息
  - 数据库连接池信息
- 上述信息一键下载
- springboot-actuator



## 支持工程类型

- SpringBoot



## 集成方式

源码打包集成，后续考虑上传到maven公有仓库

```shell
# 下载源码
git clone https://github.com/vay-qz/sysinfo.git

#在pom文件所在目录打包
mvn clean package

#引入target中打好的包
```



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



## 访问路径

- http://ip:port/sys/infos.html
- actuator原生访问方式



## 过滤设置

```java
web.ignoring()
    .antMatchers("/sys/**")
    .antMatchers("/infos.html")
```


package sysinfo.autoconfigure;

import pers.vay.controller.*;
import pers.vay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SysinfoProperties.class)
public class SysinfoAutoConfigure {

    @Autowired
    private SysinfoProperties properties;

    @Bean
    ApplicationService applicationService() {
        return new ApplicationService();
    }

    @Bean
    ApplicationController applicationController() {
        return new ApplicationController();
    }

    @Bean
    NetworkService networkService() {
        return new NetworkService();
    }

    @Bean
    NetController netController() {
        return new NetController();
    }

    @Bean
    OsService osService() {
        return new OsService();
    }

    @Bean
    OsController osController() {
        return new OsController();
    }

    @Bean
    SpringService springService() {
        return new SpringService();
    }

    @Bean
    SpringInjectController springInjectController() {
        return new SpringInjectController();
    }

}

package pers.vay.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiaozhe
 */
@Configuration
@EnableConfigurationProperties(StarterServiceProperties.class)
public class SystemInfoAutoConfigure {

    @Autowired
    private StarterServiceProperties properties;


}

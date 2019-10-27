package sysinfo.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class SysinfoProperties {

    private String url;
    private String username;
    private String password;
}

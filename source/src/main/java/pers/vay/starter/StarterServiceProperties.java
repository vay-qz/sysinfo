package pers.vay.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qiaozhe
 */
@Data
@ConfigurationProperties(prefix = "systeminfo")
public class StarterServiceProperties {
}

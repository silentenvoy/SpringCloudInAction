package wiki.cccp.zuulsvr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ServiceConfig {
    @Value("${signing.key}")
    private String signingKey;
}

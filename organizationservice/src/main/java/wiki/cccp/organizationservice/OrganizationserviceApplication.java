package wiki.cccp.organizationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@MapperScan(basePackages = "wiki.cccp.organizationservice.mapper")
@EnableDiscoveryClient
@EnableResourceServer
@EnableBinding(Source.class)
public class OrganizationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationserviceApplication.class, args);
    }

}

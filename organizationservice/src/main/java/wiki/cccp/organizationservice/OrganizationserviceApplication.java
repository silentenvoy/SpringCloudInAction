package wiki.cccp.organizationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "wiki.cccp.organizationservice.mapper")
@EnableDiscoveryClient
public class OrganizationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationserviceApplication.class, args);
    }

}

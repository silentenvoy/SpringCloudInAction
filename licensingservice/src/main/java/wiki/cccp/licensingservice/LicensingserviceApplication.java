package wiki.cccp.licensingservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@MapperScan(basePackages = {"wiki.cccp.licensingservice.mapper"})
@EnableFeignClients
@EnableHystrix
@EnableResourceServer
public class LicensingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensingserviceApplication.class, args);
    }

}

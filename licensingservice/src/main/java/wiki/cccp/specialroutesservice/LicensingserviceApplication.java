package wiki.cccp.specialroutesservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = {"wiki.cccp.licensingservice.mapper"})
//@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class LicensingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensingserviceApplication.class, args);
    }

}

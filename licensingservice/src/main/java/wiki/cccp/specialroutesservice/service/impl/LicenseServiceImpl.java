package wiki.cccp.specialroutesservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import wiki.cccp.specialroutesservice.clients.OrganizationClient;
import wiki.cccp.specialroutesservice.holder.UserContextHolder;
import wiki.cccp.specialroutesservice.mapper.LicenseMapper;
import wiki.cccp.specialroutesservice.model.License;
import wiki.cccp.specialroutesservice.model.Organization;
import wiki.cccp.specialroutesservice.service.LicenseService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class LicenseServiceImpl extends ServiceImpl<LicenseMapper, License> implements LicenseService {
    @Autowired
    @Qualifier("restTemplateClient")
    private OrganizationClient client;

    @Override
    public License getDetail(String licenseId) {
        License license = baseMapper.selectById(licenseId);
        Organization organization = client.getOrganization(license.getOrganizationId());
        license.setOrganization(organization);
        return license;
    }

    //            threadPoolKey = "listOrganLicenses",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "10"),
//                    @HystrixProperty(name = "maxQueueSize", value = "10")
//            }
    @Override
    @HystrixCommand(
            fallbackMethod = "fallbackListOrganLicenses",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            }
    )
    public List<License> listOrganLicenses(String organizationId) {
        System.out.println(String.format("LicenseService Correlation id : %s", UserContextHolder.getContext().getCorrelationId()));
        randomlyRunLong();
        return baseMapper.queryOrganLicenses(organizationId);
    }

    private List<License> fallbackListOrganLicenses(String organizationId) {
        License license = new License();
        license.setOrganizationId(organizationId);
        license.setId("000-000");
        license.setName("hello-license");
        return Arrays.asList(license);
    }

    private void randomlyRunLong() {
        Random random = new Random();
        if (random.nextInt(3) == 2) {
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

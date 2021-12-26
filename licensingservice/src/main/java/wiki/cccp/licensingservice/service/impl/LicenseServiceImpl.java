package wiki.cccp.licensingservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import wiki.cccp.licensingservice.clients.OrganizationClient;
import wiki.cccp.licensingservice.holder.UserContext;
import wiki.cccp.licensingservice.holder.UserContextHolder;
import wiki.cccp.licensingservice.mapper.LicenseMapper;
import wiki.cccp.licensingservice.model.License;
import wiki.cccp.licensingservice.model.Organization;
import wiki.cccp.licensingservice.service.LicenseService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class LicenseServiceImpl extends ServiceImpl<LicenseMapper, License> implements LicenseService {
    @Autowired
    @Qualifier("restTemplateClient")
    private OrganizationClient client;

    @Override
    @HystrixCommand(
            fallbackMethod = "fallbackDetails",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            }
    )
    public License getDetail(String licenseId) {
        try {
            System.out.println(String.format("%s 的当前线程号：%s", LicenseServiceImpl.class.getName(), Thread.currentThread().getId()));
            System.out.println(UserContextHolder.getContext().getAuthToken());
            License license = baseMapper.selectById(licenseId);
            Organization organization = client.getOrganization(license.getOrganizationId());
            license.setOrganization(organization);
            return license;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private License fallbackDetails(String licenseId) {
        return baseMapper.selectById(licenseId);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "fallbackListOrganLicenses",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            }
    )
    public List<License> listOrganLicenses(String organizationId) {

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

package wiki.cccp.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wiki.cccp.licensingservice.config.ServiceConfig;
import wiki.cccp.licensingservice.holder.UserContextHolder;
import wiki.cccp.licensingservice.model.License;
import wiki.cccp.licensingservice.service.LicenseService;

import java.util.List;

@RestController
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    private ServiceConfig config;
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicense(@PathVariable("licenseId") String licenseId) {
        System.out.println(String.format("%s 的当前线程号：%s", LicenseController.class.getName(), Thread.currentThread().getId()));
        System.out.println(UserContextHolder.getContext().getAuthToken());
        return licenseService.getDetail(licenseId);
    }

    @RequestMapping(value = "/organization/{organizationId}", method = RequestMethod.GET)
    public List<License> listLicenses(@PathVariable("organizationId") String organizationId) {
        System.out.println(String.format("LicenseController Correlation id : %s", UserContextHolder.getContext().getCorrelationId()));
        return licenseService.listOrganLicenses(organizationId);
    }
}

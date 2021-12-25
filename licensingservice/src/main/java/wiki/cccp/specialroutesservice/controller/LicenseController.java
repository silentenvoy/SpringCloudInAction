package wiki.cccp.specialroutesservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wiki.cccp.specialroutesservice.config.LicenseConfig;
import wiki.cccp.specialroutesservice.holder.UserContextHolder;
import wiki.cccp.specialroutesservice.model.License;
import wiki.cccp.specialroutesservice.service.LicenseService;

import java.util.List;

@RestController
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    private LicenseConfig config;
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicense(@PathVariable("licenseId") String licenseId) {
        System.out.printf(config.toString());
        return licenseService.getDetail(licenseId);
    }

    @RequestMapping(value = "/organization/{organizationId}", method = RequestMethod.GET)
    public List<License> listLicenses(@PathVariable("organizationId") String organizationId) {
        System.out.println(String.format("LicenseController Correlation id : %s", UserContextHolder.getContext().getCorrelationId()));
        return licenseService.listOrganLicenses(organizationId);
    }
}

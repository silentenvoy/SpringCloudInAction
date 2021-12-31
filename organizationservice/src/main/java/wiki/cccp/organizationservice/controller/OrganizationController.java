package wiki.cccp.organizationservice.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wiki.cccp.organizationservice.config.OrganizationConfig;
import wiki.cccp.organizationservice.model.Organization;
import wiki.cccp.organizationservice.service.OrganizationService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/organization/{organizationId}")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationConfig organizationConfig;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        System.out.println(organizationConfig.toString());
        System.out.println(Thread.currentThread().getId());
        return organizationService.getOrganization(organizationId);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Boolean deleteOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.removeById(organizationId);
    }

}

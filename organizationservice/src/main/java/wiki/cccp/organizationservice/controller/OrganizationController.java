package wiki.cccp.organizationservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wiki.cccp.organizationservice.model.Organization;
import wiki.cccp.organizationservice.service.OrganizationService;

@RestController
@RequestMapping(value = "/organization/{organizationId}")
@Slf4j
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        log.info("组织ID是{}", organizationId);
        System.out.println(Thread.currentThread().getId());
        return organizationService.getOrganization(organizationId);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Boolean deleteOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.deleteOrganization(organizationId);
    }

}

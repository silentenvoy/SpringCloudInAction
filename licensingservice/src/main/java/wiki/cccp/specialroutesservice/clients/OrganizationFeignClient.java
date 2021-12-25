package wiki.cccp.specialroutesservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wiki.cccp.specialroutesservice.model.Organization;

@FeignClient("organizationservice")
public interface OrganizationFeignClient {
    @RequestMapping(value = "/organization/{organizationId}/", method = RequestMethod.GET,consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}

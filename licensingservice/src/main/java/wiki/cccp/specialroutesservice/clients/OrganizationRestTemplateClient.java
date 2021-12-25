package wiki.cccp.specialroutesservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wiki.cccp.specialroutesservice.model.Organization;

@Component
@Qualifier("restTemplateClient")
public class OrganizationRestTemplateClient implements OrganizationClient{
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Organization getOrganization(String organizationId) {
        String serviceUri = String.format("http://organizationservice/organization/%s/", organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);
        return exchange.getBody();
    }
}

package wiki.cccp.specialroutesservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wiki.cccp.specialroutesservice.model.Organization;

import java.util.List;

@Component
@Qualifier("discoveryClient")
public class OrganizationDiscoveryClient implements OrganizationClient{
    @Autowired
    private DiscoveryClient discoveryClient;
    @Override
    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> organizationservices = discoveryClient.getInstances("organizationservice");
        if (organizationservices == null || organizationservices.isEmpty()) {
            return null;
        }
        String serviceUri = String.format("%s/organization/%s/", organizationservices.get(0).getUri().toString(), organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);
        return exchange.getBody();
    }
}

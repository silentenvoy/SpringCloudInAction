package wiki.cccp.licensingservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import wiki.cccp.licensingservice.model.Organization;

@Component
@Qualifier("restTemplateClient")
public class OrganizationRestTemplateClient implements OrganizationClient {
    @Autowired
    private OAuth2RestTemplate restTemplate;


    @Override
    public Organization getOrganization(String organizationId) {
        String serviceUri = String.format("http://zuulsvr/api/organizationservice/organization/%s/", organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);
        return exchange.getBody();
    }
}

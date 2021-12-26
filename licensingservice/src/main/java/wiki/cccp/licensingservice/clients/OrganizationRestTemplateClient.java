package wiki.cccp.licensingservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import wiki.cccp.licensingservice.model.Organization;

@Component
@Qualifier("restTemplateClient")
public class OrganizationRestTemplateClient implements OrganizationClient {
    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Override
    public Organization getOrganization(String organizationId) {
        System.out.println(String.format("%s 的当前线程号：%s", OrganizationRestTemplateClient.class.getName(), Thread.currentThread().getId()));
        String serviceUri = String.format("http://127.0.0.1:8280/api/organizationservice/organization/%s/", organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);
        return exchange.getBody();
    }
}

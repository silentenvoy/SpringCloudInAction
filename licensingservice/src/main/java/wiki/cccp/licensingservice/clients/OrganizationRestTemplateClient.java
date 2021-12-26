package wiki.cccp.licensingservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wiki.cccp.licensingservice.holder.UserContextHolder;
import wiki.cccp.licensingservice.model.Organization;

import java.util.HashMap;
import java.util.Map;

@Component
@Qualifier("restTemplateClient")
public class OrganizationRestTemplateClient implements OrganizationClient {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Organization getOrganization(String organizationId) {
        System.out.println(String.format("%s 的当前线程号：%s", OrganizationRestTemplateClient.class.getName(), Thread.currentThread().getId()));
        System.out.println(UserContextHolder.getContext().getAuthToken());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, UserContextHolder.getContext().getAuthToken());
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(new HashMap<>(), headers);
        String serviceUri = String.format("http://zuulsvr/organizationservice/organization/%s/", organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, httpEntity, Organization.class, organizationId);
        return exchange.getBody();
    }
}

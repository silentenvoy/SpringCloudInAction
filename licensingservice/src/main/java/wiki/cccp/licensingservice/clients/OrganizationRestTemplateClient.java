package wiki.cccp.licensingservice.clients;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wiki.cccp.licensingservice.holder.UserContextHolder;
import wiki.cccp.licensingservice.model.Organization;
import wiki.cccp.licensingservice.repository.OrganizationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Qualifier("restTemplateClient")
public class OrganizationRestTemplateClient implements OrganizationClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private Tracer tracer;
    /**
     * 从组织服务获取组织信息
     * @param organizationId
     * @return
     */
    public Organization getOrganizationFromServer(String organizationId) {
        System.out.println(String.format("%s 的当前线程号：%s", OrganizationRestTemplateClient.class.getName(), Thread.currentThread().getId()));
        System.out.println(UserContextHolder.getContext().getAuthToken());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, UserContextHolder.getContext().getAuthToken());
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(new HashMap<>(), headers);
        String serviceUri = String.format("http://zuulsvr/organizationservice/organization/%s/", organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(serviceUri, HttpMethod.GET, httpEntity, Organization.class, organizationId);
        return exchange.getBody();
    }


    @Override
    public Organization getOrganization(String organizationId) {
        Organization organization = checkRedisCache(organizationId);
        if (Objects.nonNull(organization)) {
            return organization;
        }
        Organization org = getOrganizationFromServer(organizationId);
        //缓存到本地
        organizationRepository.saveOrganization(org);
        return org;
    }



    /**
     * 检查缓存
     *
     * @param organizationId
     * @return
     */
    private Organization checkRedisCache(String organizationId) {
        //在当前的跟踪中添加新的span
        Span span = tracer.newChild(tracer.currentSpan().context()).name("readLicenseDataFromRedis").start();
        try {
            //在finally关闭跨度
            span.tag("peer.service", "redis").kind(Span.Kind.CLIENT);
            return organizationRepository.findOrganization(organizationId);
        }finally {
            span.finish();
        }

    }

    /**
     * 保存缓存
     *
     * @param organization
     */
    private void cacheOrganization(Organization organization) {
        try {
            organizationRepository.saveOrganization(organization);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

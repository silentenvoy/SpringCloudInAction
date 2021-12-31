package wiki.cccp.licensingservice.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import wiki.cccp.licensingservice.model.Organization;
import wiki.cccp.licensingservice.repository.OrganizationRepository;

import javax.annotation.PostConstruct;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {
    @Autowired
    private RedisTemplate redisTemplate;

    private HashOperations<String, String, Organization> hashOperations;

    public static final String HASH_NAME = "organization";

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void saveOrganization(Organization organization) {
        hashOperations.put(HASH_NAME, organization.getId(), organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        hashOperations.put(HASH_NAME, organization.getId(), organization);
    }

    @Override
    public void deleteOrganization(String organizationId) {
        hashOperations.delete(HASH_NAME, organizationId);
    }

    @Override
    public Organization findOrganization(String organizationId) {
        return hashOperations.get(HASH_NAME, organizationId);
    }
}

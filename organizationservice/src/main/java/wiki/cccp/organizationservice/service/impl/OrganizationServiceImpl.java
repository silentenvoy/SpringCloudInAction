package wiki.cccp.organizationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiki.cccp.organizationservice.mapper.OrganizationMapper;
import wiki.cccp.organizationservice.model.Organization;
import wiki.cccp.organizationservice.service.OrganizationService;
import wiki.cccp.organizationservice.stream.OrganizationChangePublisher;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
    @Autowired
    private OrganizationChangePublisher simpleSource;

    @Override
    public Organization getOrganization(String id) {
        System.out.println(Thread.currentThread().getId());
        return baseMapper.selectById(id);
    }

    @Override
    public void saveOrganization(Organization organization) {
        System.out.println(Thread.currentThread().getId());
        this.baseMapper.insert(organization);
        simpleSource.publicOrgChange(OrgAction.INSERT.toString(), organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        System.out.println(Thread.currentThread().getId());
        updateById(organization);
        simpleSource.publicOrgChange(OrgAction.UPDATE.toString(), organization);
    }

    @Override
    public Boolean deleteOrganization(String orgId) {
        try{
            System.out.println(Thread.currentThread().getId());
            return this.removeById(orgId);
        }finally {
            simpleSource.publicOrgChange(OrgAction.DELETE.toString(), Organization.builder().id(orgId).build());
        }
    }

    private enum OrgAction {
        UPDATE, INSERT, DELETE
    }
}

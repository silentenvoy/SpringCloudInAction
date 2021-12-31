package wiki.cccp.organizationservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wiki.cccp.organizationservice.model.Organization;

public interface OrganizationService extends IService<Organization> {
    public Organization getOrganization(String id);

    public void saveOrganization(Organization organization);

    public void updateOrganization(Organization organization);

    public Boolean deleteOrganization(String orgId);
}

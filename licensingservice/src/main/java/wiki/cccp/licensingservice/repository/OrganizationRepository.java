package wiki.cccp.licensingservice.repository;

import wiki.cccp.licensingservice.model.Organization;

public interface OrganizationRepository {

    void saveOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganization(String organizationId);

    Organization findOrganization(String organizationId);
}

package wiki.cccp.licensingservice.clients;

import wiki.cccp.licensingservice.model.Organization;

public interface OrganizationClient {
    public Organization getOrganization(String organizationId);
}

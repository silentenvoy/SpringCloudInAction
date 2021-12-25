package wiki.cccp.specialroutesservice.clients;

import wiki.cccp.specialroutesservice.model.Organization;

public interface OrganizationClient {
    public Organization getOrganization(String organizationId);
}

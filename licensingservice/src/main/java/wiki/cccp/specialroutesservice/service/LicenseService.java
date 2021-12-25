package wiki.cccp.specialroutesservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wiki.cccp.specialroutesservice.model.License;

import java.util.List;

public interface LicenseService extends IService<License> {
    public License getDetail(String licenseId);

    public List<License> listOrganLicenses(String organizationId);
}

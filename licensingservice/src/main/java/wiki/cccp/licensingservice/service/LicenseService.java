package wiki.cccp.licensingservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wiki.cccp.licensingservice.model.License;

import java.util.List;

public interface LicenseService extends IService<License> {
    public License getDetail(String licenseId);

    public List<License> listOrganLicenses(String organizationId);
}

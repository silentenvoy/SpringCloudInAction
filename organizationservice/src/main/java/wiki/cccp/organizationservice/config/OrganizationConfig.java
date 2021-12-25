package wiki.cccp.organizationservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Getter
@RefreshScope
public class OrganizationConfig {
    @Value("${wiki.cccp.name}")
    private String name;
    @Value("${wiki.cccp.version}")
    private String version;

    @Override
    public String toString() {
        return "LicenseConfig{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

package wiki.cccp.licensingservice.stream;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import wiki.cccp.licensingservice.model.Organization;
import wiki.cccp.licensingservice.repository.OrganizationRepository;

import java.util.Date;
import java.util.Locale;

@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {
    @Autowired
    private OrganizationRepository organizationRepository;

    @StreamListener(CustomChannels.CHANNEL_NAME)
    public void logOrganizationChange(OrgChangeModel model) {
        System.out.println(String.format("接收了一条 %s ,内容是 : %s", model.getAction(), JSON.toJSONString(model.toOrg())));
        try {
            OrgAction orgAction = OrgAction.valueOf(model.getAction().toUpperCase(Locale.ENGLISH));
            switch (orgAction) {
                case DELETE:
                    organizationRepository.deleteOrganization(model.getOrgId());
                    break;
                case INSERT:
                    organizationRepository.saveOrganization(model.toOrg());
                    break;
                case UPDATE:
                    organizationRepository.updateOrganization(model.toOrg());
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            return;
        }

    }

    private enum OrgAction {
        UPDATE, INSERT, DELETE;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OrgChangeModel {

        private String correlationId;

        private String orgId;

        private String action;

        private String name;

        private Date createTime;

        public Organization toOrg() {
            Organization org = new Organization();
            org.setCreateTime(createTime);
            org.setId(orgId);
            org.setName(name);
            return org;
        }
    }
}

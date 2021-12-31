package wiki.cccp.organizationservice.stream;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import wiki.cccp.organizationservice.holder.UserContextHolder;
import wiki.cccp.organizationservice.model.Organization;

import java.io.Serializable;
import java.util.Date;

@EnableBinding({ProductChannels.class})
public class OrganizationChangePublisher {
    @Autowired
    private ProductChannels productChannels;

    public void publicOrgChange(String action, Organization organization) {
        //要发布的数据
        OrgChangeModel model = OrgChangeModel.builder()
                .correlationId(UserContextHolder.getContext().getCorrelationId())
                .action(action)
                .orgId(organization.getId())
                .createTime(organization.getCreateTime())
                .name(organization.getName())
                .build();
        //发送数据
        productChannels.orgOutput().send(MessageBuilder.withPayload(model).build());
        System.out.println(String.format("发布了一条 %s ,内容是 : %s",action, JSON.toJSONString(organization)));
    }

    @Data
    @Builder
    public static class OrgChangeModel implements Serializable {

        private String correlationId;

        private String orgId;

        private String action;

        private String name;

        private Date createTime;

    }
}

package wiki.cccp.organizationservice.stream;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import wiki.cccp.organizationservice.holder.UserContext;
import wiki.cccp.organizationservice.holder.UserContextHolder;

@Component
public class SimpleSourceBean {
    @Autowired
    private Source source;

    public void publicOrgChange(String action, String orgId){
        //要发布的数据
        OrgChangeModel model = OrgChangeModel.builder()
                .correlationId(UserContextHolder.getContext().getCorrelationId())
                .action(action)
                .orgId(orgId)
                .build();
        //发送数据
        source.output().send(MessageBuilder.withPayload(model).build());
    }
    @Data
    @Builder
    public static class OrgChangeModel{

        private String correlationId;

        private String orgId;

        private String action;
    }
}

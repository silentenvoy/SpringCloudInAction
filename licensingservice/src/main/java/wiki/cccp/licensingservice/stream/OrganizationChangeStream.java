package wiki.cccp.licensingservice.stream;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class OrganizationChangeStream {
    @StreamListener(Sink.INPUT)
    public void logOrganizationChange(OrgChangeModel model){
        System.out.println(JSON.toJSONString(model));
    }

    @Data
    @Builder
    public static class OrgChangeModel{

        private String correlationId;

        private String orgId;

        private String action;
    }
}

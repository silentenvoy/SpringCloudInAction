package wiki.cccp.specialroutesservice.holder;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserContext {
    public static final String CORRELATION_ID = "correlation-id";
    public static final String AUTH_TOKEN = "auth-token";
    public static final String USER_ID = "user-id";
    public static final String ORG_ID = "org-id";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String orgId = new String();
}

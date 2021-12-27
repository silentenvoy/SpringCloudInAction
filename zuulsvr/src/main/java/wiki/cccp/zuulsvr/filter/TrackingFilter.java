package wiki.cccp.zuulsvr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.cccp.zuulsvr.config.ServiceConfig;
import wiki.cccp.zuulsvr.util.FilterUtils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/**
 * 构建前置过滤器
 */
@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;

    private static final boolean SHOULD_FILTER = true;
    @Autowired
    FilterUtils filterUtils;
    @Autowired
    private ServiceConfig serviceConfig;
    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        return Objects.nonNull(filterUtils.getCorrelationId());
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH).replace("-", "");
    }

    @Override
    public Object run() throws ZuulException {
        if (isCorrelationIdPresent()) {
            System.out.println(String.format("关联ID是 %s ", filterUtils.getCorrelationId()));
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
        }
        System.out.println(String.format("authservice的customInfo是%s", getAuthToken()));
        System.out.println(String.format("前置过滤器处理的路由URI是 {%s}, 关联ID是 {%s}", RequestContext.getCurrentContext().getRequest().getRequestURI(), filterUtils.getCorrelationId()));
        return null;
    }

    private String getAuthToken(){
        if (Objects.isNull(filterUtils.getAuthToken())){
            return new String();
        }
        String authToken = filterUtils.getAuthToken().replace("Bearer ", "");
        Claims body = Jwts.parser().setSigningKey(serviceConfig.getSigningKey().getBytes(StandardCharsets.UTF_8)).parseClaimsJws(authToken).getBody();
        return body.get("customInfo").toString();
    }
}

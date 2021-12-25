package wiki.cccp.zuulsvr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.cccp.zuulsvr.util.FilterUtils;

@Component
public class ResponseFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;

    private static final boolean SHOULD_FILTER = true;
    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();

        System.out.println(String.format("添加关联ID : {%s} 到头部", filterUtils.getCorrelationId()));

        currentContext.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());

        System.out.println(String.format("后置过滤器处理的路由URI是 {%s}, 关联ID是 {%s}", currentContext.getRequest().getRequestURI(), filterUtils.getCorrelationId()));

        return null;
    }
}

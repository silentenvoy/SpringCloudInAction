package wiki.cccp.zuulsvr.holder;

import org.springframework.util.Assert;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContexts = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext userContext = userContexts.get();
        if (null == userContext) {
            userContext = createEmptyUserContext();
            userContexts.set(userContext);
        }
        return userContexts.get();
    }

    public static final UserContext createEmptyUserContext() {
        return new UserContext();
    }


    public static final void setContext(UserContext context) {
        Assert.notNull(context, "上下文不能为空");
        userContexts.set(context);
    }
}

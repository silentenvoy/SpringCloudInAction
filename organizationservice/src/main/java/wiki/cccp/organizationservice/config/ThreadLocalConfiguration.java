package wiki.cccp.organizationservice.config;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import wiki.cccp.organizationservice.hystrix.ThreadLocalAwareStrategy;

import javax.annotation.PostConstruct;

@Configuration
public class ThreadLocalConfiguration {
    //获取已有的并发策略
    @Autowired(required = false)
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    @PostConstruct
    public void init() {
        //保存现有的插件引用
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
        //重新设置
        HystrixPlugins.reset();
        //重新注册
        //把自定义的并发策略注册到插件
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new ThreadLocalAwareStrategy(existingConcurrencyStrategy));
        //重新注册插件
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);

    }
}

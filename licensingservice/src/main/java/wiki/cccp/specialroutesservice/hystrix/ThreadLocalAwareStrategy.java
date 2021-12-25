package wiki.cccp.specialroutesservice.hystrix;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import wiki.cccp.specialroutesservice.holder.UserContextHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义并发策略，并出共享上下文
 */
public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {
    //已经存在的并发策略
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy existingConcurrencyStrategy) {
        this.existingConcurrencyStrategy = existingConcurrencyStrategy;
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return null == existingConcurrencyStrategy ? super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue) :
                existingConcurrencyStrategy.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return null == existingConcurrencyStrategy ? super.getBlockingQueue(maxQueueSize) : existingConcurrencyStrategy.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return null == existingConcurrencyStrategy ? super.wrapCallable(new DelegatingUserContextCallable<>(callable, UserContextHolder.getContext())) :
                existingConcurrencyStrategy.wrapCallable(new DelegatingUserContextCallable<>(callable, UserContextHolder.getContext()));
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return null == existingConcurrencyStrategy ? super.getRequestVariable(rv) : existingConcurrencyStrategy.getRequestVariable(rv);
    }

}

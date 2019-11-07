package com.pinux.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

/**
 * * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @ClassName AsyncTaskExecutor
 * @Description TODO
 * @Author pinux
 * @Date 2019/11/8 3:26
 * @Version 1.0
 */
@Configuration
public class AsyncTaskConfig {

    @Bean("asyncTaskExecutor")
    public Executor asyncTaskExecutor(){
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("asyncTaskExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

}

package com.pinux;

import com.pinux.config.listener.ApplicationPreparedEventListener;
import com.pinux.config.listener.ApplicationReadyEventListener;
import com.pinux.config.listener.ApplicationStartedEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
@MapperScan("com.pinux.mapper")
@EnableSwagger2
@EnableScheduling
@EnableAsync
public class SpringBootPinuxApplication {

    public static void main(String[] args) {
        /*ApplicationStartingEvent：在Spring最开始启动的时候触发
        ApplicationEnvironmentPreparedEvent：在Spring已经准备好上下文但是上下文尚未创建的时候触发
        ApplicationPreparedEvent：在Bean定义加载之后、刷新上下文之前触发
        ApplicationStartedEvent：在刷新上下文之后、调用application命令之前触发
        ApplicationReadyEvent：在调用applicaiton命令之后触发
        ApplicationFailedEvent：在启动Spring发生异常时触发*/
        SpringApplication springApplication = new SpringApplication(SpringBootPinuxApplication.class);
        springApplication.addListeners(new ApplicationPreparedEventListener());
        springApplication.addListeners(new ApplicationReadyEventListener());
        springApplication.addListeners(new ApplicationStartedEventListener());
        springApplication.run(args);
    }

}

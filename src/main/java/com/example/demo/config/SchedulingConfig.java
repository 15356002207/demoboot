package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Scheduled(fixedRate = 1 * 60 * 1000) //  一分钟一次
    //@Scheduled(cron = " */3 * * * * *")
    /*public void changeTxOrderStatus() {
        orderTxInfoService.changeTxOrderStatus();
    }*/

    //@Scheduled(cron = " */1 * * * * *")
    /*public void timeout() {
        orderTxInfoService.timeout();
    }*/

    //	 @Scheduled(cron="0/59 * * * * ? ") //每10秒执行一次
    //@Scheduled(cron = "0 0 2 * * ?") // 每天凌晨两点执行
    /*public void calcNav() {
    }*/

    //Check  balance.... only,should only start at
    //1@Scheduled(fixedRate = 10 * 60 * 1000)
    public void fetchBalance() {

    }

    //1@Scheduled(cron = "0 0 1 * * ?") // 每天凌晨1点执行
    public void syncTokenData() throws Exception {
    }

    @Bean
    public TaskScheduler scheduledExecutorService(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Method[] methods = BatchProperties.Job.class.getMethods();
        int defaultPoolSize = 10;
        int corePoolSize = 0;
        if(methods != null && methods.length>0){
            for(Method method:methods){
                Scheduled annotation=method.getAnnotation(Scheduled.class);
                if(annotation != null){
                    corePoolSize ++;
                }
            }
            if(defaultPoolSize > corePoolSize)
                corePoolSize = defaultPoolSize;
        }
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
    }
}

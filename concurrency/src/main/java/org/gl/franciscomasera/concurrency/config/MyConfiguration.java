package org.gl.franciscomasera.concurrency.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class MyConfiguration {

    @Bean
    @Qualifier("myExecutor")
    public Executor myExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("MyThread-");
        executor.setCorePoolSize(5);
        executor.initialize();
        return executor;
    }

}

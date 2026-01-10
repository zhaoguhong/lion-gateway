package com.zhaoguhong.lion.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

import java.util.concurrent.Executors;

/**
 * 虚拟线程配置
 * JDK 21 的杀手级特性，让同步代码也能达到响应式的性能
 * 
 * @author zhaoguhong
 * @date 2026/01/10
 */
@Configuration
public class VirtualThreadConfig {

    /**
     * 配置 Spring 的异步任务执行器使用虚拟线程
     * 这样所有 @Async 注解的方法都会在虚拟线程中执行
     */
    @Bean
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }
    
    /**
     * 提供一个虚拟线程执行器的 Bean，供业务代码直接使用
     */
    @Bean(name = "virtualThreadExecutor")
    public java.util.concurrent.ExecutorService virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}

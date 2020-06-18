package com.judgesystem.page;

import com.github.pagehelper.PageHelper;

import com.judgesystem.exception.ManageBisException;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(PageProperties.class)
public class PageAutoConfiguration {

    public MethodInterceptor pageInterceptor() {

        return (invocation) -> {
            // 分页查询
            Object[] args = invocation.getArguments();
            if (args != null && args.length > 1) {
                for (int i = args.length - 1; i >= 0; i--) {
                    Object arg = args[i];
                    if (arg instanceof IPage) {
                        IPage page = (IPage) arg;
                        log.info("start page {}", page);
                        try {
                            PageHelper.startPage(page);
                        } catch (Exception e) {
                            throw ManageBisException.of(e.getMessage());
                        }
                        break;
                    }
                }
            }
            try {
                return invocation.proceed();
            } catch (Exception e) {

                throw e;
            }
        };
    }

    @Bean
    @ConditionalOnProperty("system.page.pointcut")
    public Advisor pageAdvisor(PageProperties pageProperties) {
 //声明切点的面

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
 //声明和设置需要拦截的方法,用切点语言描写

        pointcut.setExpression(pageProperties.getPointcut());
 //设置切面=切点pointcut+通知TxAdvice

        return new DefaultPointcutAdvisor(pointcut, pageInterceptor());
    }

}

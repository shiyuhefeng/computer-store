package com.huige.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author huige
 * Create on 2022/4/16 15:27
 */
@Component // 将当前类的对象创建并交给Spring维护
@Aspect // 将当前类标记为切面类
public class TimerAspect {

    /**
     * ProceedingJoinPoint表示接口的连接点，目标方法的对象
     * @param pjp
     * @return
     */
    @Around("execution(* com.huige.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 先记录当前时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed(); // 调用目标方法:login
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        return result;
    }
}

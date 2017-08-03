package com.zx.sframe.util.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * 异常捕获类，打印错误日志
 * 
 * @author
 */
public class ThrowableManage implements ThrowsAdvice
{
    private static Logger log = LoggerFactory.getLogger(ThrowableManage.class);

    public void afterThrowing(Throwable ex)
    {
        log.error("syserror ", ex);
    }
}

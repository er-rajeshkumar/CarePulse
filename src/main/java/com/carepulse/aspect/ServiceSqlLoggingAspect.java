package com.carepulse.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Tags the current thread with an MDC value so that Hibernate SQL statements
 * executed while a
 * given *Service method runs are written to a dedicated log file, e.g.
 * DoctorService -> logs/LogsDoctor.log.
 * See logback-spring.xml for the appender that consumes the "logFileName" MDC
 * key.
 */
@Aspect
@Component
@Order(0)
public class ServiceSqlLoggingAspect {

    private static final String MDC_KEY = "logFileName";
    private static final String SERVICE_SUFFIX = "Service";

    @Around("execution(* com.carepulse.service..*Service.*(..))")
    public Object tagSqlLogFile(ProceedingJoinPoint joinPoint) throws Throwable {
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        String serviceName = simpleName.endsWith(SERVICE_SUFFIX)
                ? simpleName.substring(0, simpleName.length() - SERVICE_SUFFIX.length())
                : simpleName;

        String previous = MDC.get(MDC_KEY);
        MDC.put(MDC_KEY, "Logs" + serviceName);
        try {
            return joinPoint.proceed();
        } finally {
            if (previous != null) {
                MDC.put(MDC_KEY, previous);
            } else {
                MDC.remove(MDC_KEY);
            }
        }
    }
}

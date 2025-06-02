package com.sekretowicz.workload_service.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class TransactionLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TransactionLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String transactionId = request.getHeader("X-Transaction-Id");
        if (transactionId == null || transactionId.isEmpty()) {
            transactionId = UUID.randomUUID().toString();
        }

        MDC.put("transactionId", transactionId);
        log.info("[{}] Incoming request: {} {}", transactionId, request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        String transactionId = MDC.get("transactionId");
        log.info("[{}] Response status: {}", transactionId, response.getStatus());

        if (ex != null) {
            log.error("[{}] Exception: {}", transactionId, ex.getMessage(), ex);
        }

        MDC.clear();
    }
}
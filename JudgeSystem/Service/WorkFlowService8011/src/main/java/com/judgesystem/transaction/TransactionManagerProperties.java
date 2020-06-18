package com.judgesystem.transaction;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.judgesystem.transaction")
public class TransactionManagerProperties {

    /**
     * transaction point cut
     */
    private String pointcut;

    /**
     * transaction timeout
     */
    private int timeout = 5;
}

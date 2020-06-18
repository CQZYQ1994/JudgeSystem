package com.judgesystem.page;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("com.judgesystem.page")
public class PageProperties {

    /**
     * page point cut
     */
    private String pointcut;
}

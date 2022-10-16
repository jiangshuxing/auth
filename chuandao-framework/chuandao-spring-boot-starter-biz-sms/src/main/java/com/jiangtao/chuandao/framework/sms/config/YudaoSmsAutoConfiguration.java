package com.jiangtao.chuandao.framework.sms.config;

import com.jiangtao.chuandao.framework.sms.core.client.SmsClientFactory;
import com.jiangtao.chuandao.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信配置类
 *
 * @author 芋道源码
 */
@Configuration
public class YudaoSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}

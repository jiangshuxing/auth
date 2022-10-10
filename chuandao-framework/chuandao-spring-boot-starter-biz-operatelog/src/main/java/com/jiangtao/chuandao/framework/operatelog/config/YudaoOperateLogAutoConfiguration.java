package com.jiangtao.chuandao.framework.operatelog.config;


import com.jiangtao.chuandao.framework.operatelog.core.aop.OperateLogAspect;
import com.jiangtao.chuandao.framework.operatelog.core.service.OperateLogFrameworkService;
import com.jiangtao.chuandao.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.jiangtao.chuandao.module.system.api.logger.OperateLogApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YudaoOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}

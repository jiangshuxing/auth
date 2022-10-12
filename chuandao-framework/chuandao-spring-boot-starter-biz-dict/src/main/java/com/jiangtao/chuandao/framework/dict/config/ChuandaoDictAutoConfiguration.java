package com.jiangtao.chuandao.framework.dict.config;


import com.jiangtao.chuandao.framework.dict.core.util.DictFrameworkUtils;
import com.jiangtao.chuandao.module.system.api.dict.DictDataApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChuandaoDictAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataApi dictDataApi) {
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }

}

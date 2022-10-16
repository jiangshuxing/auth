package com.jiangtao.chuandao.module.system.mq.producer.dept;

import com.jiangtao.chuandao.framework.mq.core.RedisMQTemplate;
import com.jiangtao.chuandao.module.system.mq.message.dept.DeptRefreshMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Dept 部门相关消息的 Producer
 */
@Component
public class DeptProducer {

    @Resource
    private RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link DeptRefreshMessage} 消息
     */
    public void sendDeptRefreshMessage() {
        DeptRefreshMessage message = new DeptRefreshMessage();
        redisMQTemplate.send(message);
    }

}

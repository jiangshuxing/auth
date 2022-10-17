package com.jiangtao.chuandao.module.system.api.permission;

import com.jiangtao.chuandao.module.system.service.permission.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 角色 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class RoleApiImpl implements RoleApi {

    @Resource
    private RoleService roleService;

    @Override
    public void validRoles(Collection<Long> ids) {
        roleService.validRoles(ids);
    }
}

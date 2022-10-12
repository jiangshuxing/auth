package com.jiangtao.chuandao.module.system.convert.auth2;

import com.jiangtao.chuandao.framework.common.core.KeyValue;
import com.jiangtao.chuandao.framework.common.enums.UserTypeEnum;
import com.jiangtao.chuandao.framework.common.util.collection.CollectionUtils;
import com.jiangtao.chuandao.framework.security.core.util.SecurityFrameworkUtils;
import com.jiangtao.chuandao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.jiangtao.chuandao.module.system.dal.dataobject.oauth2.OAuth2ApproveDO;
import com.jiangtao.chuandao.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import com.jiangtao.chuandao.module.system.util.oauth2.OAuth2Utils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface OAuth2OpenConvert {

    OAuth2OpenConvert INSTANCE = Mappers.getMapper(OAuth2OpenConvert.class);

    default OAuth2OpenAccessTokenRespVO convert(OAuth2AccessTokenDO bean) {
        OAuth2OpenAccessTokenRespVO respVO = convert0(bean);
        respVO.setTokenType(SecurityFrameworkUtils.AUTHORIZATION_BEARER.toLowerCase());
        respVO.setExpiresIn(OAuth2Utils.getExpiresIn(bean.getExpiresTime()));
        respVO.setScope(OAuth2Utils.buildScopeStr(bean.getScopes()));
        return respVO;
    }
    OAuth2OpenAccessTokenRespVO convert0(OAuth2AccessTokenDO bean);

    default OAuth2OpenCheckTokenRespVO convert2(OAuth2AccessTokenDO bean) {
        OAuth2OpenCheckTokenRespVO respVO = convert3(bean);
        respVO.setExp(bean.getExpiresTime().getTime() / 1000L);
        respVO.setUserType(UserTypeEnum.ADMIN.getValue());
        return respVO;
    }
    OAuth2OpenCheckTokenRespVO convert3(OAuth2AccessTokenDO bean);

    default OAuth2OpenAuthorizeInfoRespVO convert(OAuth2ClientDO client, List<OAuth2ApproveDO> approves) {
        // 构建 scopes
        List<KeyValue<String, Boolean>> scopes = new ArrayList<>(client.getScopes().size());
        Map<String, OAuth2ApproveDO> approveMap = CollectionUtils.convertMap(approves, OAuth2ApproveDO::getScope);
        client.getScopes().forEach(scope -> {
            OAuth2ApproveDO approve = approveMap.get(scope);
            scopes.add(new KeyValue<>(scope, approve != null ? approve.getApproved() : false));
        });
        // 拼接返回
        return new OAuth2OpenAuthorizeInfoRespVO(
                new OAuth2OpenAuthorizeInfoRespVO.Client(client.getName(), client.getLogo()), scopes);
    }

}
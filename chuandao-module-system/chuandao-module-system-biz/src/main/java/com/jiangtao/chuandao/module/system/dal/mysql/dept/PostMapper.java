package com.jiangtao.chuandao.module.system.dal.mysql.dept;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiangtao.chuandao.framework.common.pojo.PageResult;
import com.jiangtao.chuandao.framework.mybatis.core.mapper.*;
import com.jiangtao.chuandao.framework.mybatis.core.query.QueryWrapperX;
import com.jiangtao.chuandao.module.system.controller.admin.dept.vo.post.PostExportReqVO;
import com.jiangtao.chuandao.module.system.controller.admin.dept.vo.post.PostPageReqVO;
import com.jiangtao.chuandao.module.system.dal.dataobject.dept.PostDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostMapper extends BaseMapperX<PostDO> {

    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new QueryWrapperX<PostDO>().inIfPresent("id", ids)
                .inIfPresent("status", statuses));
    }

    default PageResult<PostDO> selectPage(PostPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PostDO>()
                .likeIfPresent("code", reqVO.getCode())
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByDesc("id"));
    }

    default List<PostDO> selectList(PostExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PostDO>()
                .likeIfPresent("code", reqVO.getCode())
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("status", reqVO.getStatus()));
    }

    default PostDO selectByName(String name) {
        return selectOne(new QueryWrapper<PostDO>().eq("name", name));
    }

    default PostDO selectByCode(String code) {
        return selectOne(new QueryWrapper<PostDO>().eq("code", code));
    }

}

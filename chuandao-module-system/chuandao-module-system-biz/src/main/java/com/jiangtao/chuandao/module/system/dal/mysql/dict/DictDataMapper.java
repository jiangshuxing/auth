package com.jiangtao.chuandao.module.system.dal.mysql.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiangtao.chuandao.framework.common.pojo.PageResult;
import com.jiangtao.chuandao.framework.mybatis.core.mapper.BaseMapperX;
import com.jiangtao.chuandao.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.jiangtao.chuandao.module.system.controller.admin.dict.vo.data.DictDataExportReqVO;
import com.jiangtao.chuandao.module.system.controller.admin.dict.vo.data.DictDataPageReqVO;
import com.jiangtao.chuandao.module.system.dal.dataobject.dict.DictDataDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataDO> {

    default DictDataDO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .eq(DictDataDO::getValue, value));
    }

    default DictDataDO selectByDictTypeAndLabel(String dictType, String label) {
        return selectOne(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .eq(DictDataDO::getLabel, label));
    }

    default List<DictDataDO> selectByDictTypeAndValues(String dictType, Collection<String> values) {
        return selectList(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .in(DictDataDO::getValue, values));
    }

    default long selectCountByDictType(String dictType) {
        return selectCount(DictDataDO::getDictType, dictType);
    }

    default PageResult<DictDataDO> selectPage(DictDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictDataDO>()
                .likeIfPresent(DictDataDO::getLabel, reqVO.getLabel())
                .likeIfPresent(DictDataDO::getDictType, reqVO.getDictType())
                .eqIfPresent(DictDataDO::getStatus, reqVO.getStatus())
                .orderByDesc(Arrays.asList(DictDataDO::getDictType, DictDataDO::getSort)));
    }

    default List<DictDataDO> selectList(DictDataExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DictDataDO>().likeIfPresent(DictDataDO::getLabel, reqVO.getLabel())
                .likeIfPresent(DictDataDO::getDictType, reqVO.getDictType())
                .eqIfPresent(DictDataDO::getStatus, reqVO.getStatus()));
    }

}

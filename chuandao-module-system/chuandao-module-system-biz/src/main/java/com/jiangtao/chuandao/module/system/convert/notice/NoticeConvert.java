package com.jiangtao.chuandao.module.system.convert.notice;

import com.jiangtao.chuandao.framework.common.pojo.PageResult;
import com.jiangtao.chuandao.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import com.jiangtao.chuandao.module.system.controller.admin.notice.vo.NoticeRespVO;
import com.jiangtao.chuandao.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import com.jiangtao.chuandao.module.system.dal.dataobject.notice.NoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoticeConvert {

    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);

}

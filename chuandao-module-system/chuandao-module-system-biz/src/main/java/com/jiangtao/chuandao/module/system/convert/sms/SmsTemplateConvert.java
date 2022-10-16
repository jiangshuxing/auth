package com.jiangtao.chuandao.module.system.convert.sms;

import com.jiangtao.chuandao.framework.common.pojo.PageResult;
import com.jiangtao.chuandao.module.system.controller.admin.sms.vo.template.SmsTemplateCreateReqVO;
import com.jiangtao.chuandao.module.system.controller.admin.sms.vo.template.SmsTemplateExcelVO;
import com.jiangtao.chuandao.module.system.controller.admin.sms.vo.template.SmsTemplateRespVO;
import com.jiangtao.chuandao.module.system.controller.admin.sms.vo.template.SmsTemplateUpdateReqVO;
import com.jiangtao.chuandao.module.system.dal.dataobject.sms.SmsTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SmsTemplateConvert {

    SmsTemplateConvert INSTANCE = Mappers.getMapper(SmsTemplateConvert.class);

    SmsTemplateDO convert(SmsTemplateCreateReqVO bean);

    SmsTemplateDO convert(SmsTemplateUpdateReqVO bean);

    SmsTemplateRespVO convert(SmsTemplateDO bean);

    List<SmsTemplateRespVO> convertList(List<SmsTemplateDO> list);

    PageResult<SmsTemplateRespVO> convertPage(PageResult<SmsTemplateDO> page);

    List<SmsTemplateExcelVO> convertList02(List<SmsTemplateDO> list);

}

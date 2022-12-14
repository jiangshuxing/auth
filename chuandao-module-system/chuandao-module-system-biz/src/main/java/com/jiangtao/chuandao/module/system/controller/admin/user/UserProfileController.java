package com.jiangtao.chuandao.module.system.controller.admin.user;

import cn.hutool.core.collection.CollUtil;
import com.jiangtao.chuandao.framework.common.enums.UserTypeEnum;
import com.jiangtao.chuandao.framework.common.exception.util.ServiceExceptionUtil;
import com.jiangtao.chuandao.framework.common.pojo.CommonResult;
import com.jiangtao.chuandao.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import com.jiangtao.chuandao.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import com.jiangtao.chuandao.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import com.jiangtao.chuandao.module.system.convert.user.UserConvert;
import com.jiangtao.chuandao.module.system.dal.dataobject.dept.DeptDO;
import com.jiangtao.chuandao.module.system.dal.dataobject.dept.PostDO;
import com.jiangtao.chuandao.module.system.dal.dataobject.permission.RoleDO;
import com.jiangtao.chuandao.module.system.dal.dataobject.social.SocialUserDO;
import com.jiangtao.chuandao.module.system.dal.dataobject.user.AdminUserDO;
import com.jiangtao.chuandao.module.system.service.dept.DeptService;
import com.jiangtao.chuandao.module.system.service.dept.PostService;
import com.jiangtao.chuandao.module.system.service.permission.PermissionService;
import com.jiangtao.chuandao.module.system.service.permission.RoleService;
import com.jiangtao.chuandao.module.system.service.social.SocialUserService;
import com.jiangtao.chuandao.module.system.service.user.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.jiangtao.chuandao.framework.common.pojo.CommonResult.success;
import static com.jiangtao.chuandao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.jiangtao.chuandao.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;


@Api(tags = "???????????? - ??????????????????")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private SocialUserService socialService;

    @GetMapping("/get")
    @ApiOperation("????????????????????????")
    @DataPermission(enable = false) // ?????????????????????????????????????????????????????????????????????
    public CommonResult<UserProfileRespVO> profile() {
        // ????????????????????????
        AdminUserDO user = userService.getUser(getLoginUserId());
        UserProfileRespVO resp = UserConvert.INSTANCE.convert03(user);
        // ??????????????????
        List<RoleDO> userRoles = roleService.getRolesFromCache(permissionService.getUserRoleIdListByUserId(user.getId()));
        resp.setRoles(UserConvert.INSTANCE.convertList(userRoles));
        // ??????????????????
        if (user.getDeptId() != null) {
            DeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(UserConvert.INSTANCE.convert02(dept));
        }
        // ??????????????????
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<PostDO> posts = postService.getPosts(user.getPostIds());
            resp.setPosts(UserConvert.INSTANCE.convertList02(posts));
        }
        // ????????????????????????
        List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
        resp.setSocialUsers(UserConvert.INSTANCE.convertList03(socialUsers));
        return success(resp);
    }

    @PutMapping("/update")
    @ApiOperation("????????????????????????")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @ApiOperation("????????????????????????")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT}) // ?????? uni-app ????????? Put ?????????????????????
    @ApiOperation("????????????????????????")
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw ServiceExceptionUtil.exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }

}

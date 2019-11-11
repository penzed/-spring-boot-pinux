package com.pinux.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pinux.entity.role.Role;
import com.pinux.entity.role_user.RoleUser;
import com.pinux.entity.user.UserInfo;
import com.pinux.service.role.RoleService;
import com.pinux.service.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pinux
 * @since 2019-09-24
 */
@Api("用户操作接口")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserInfoService userService;

    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping(value = "/")
    public List<UserInfo> getAllList() {
        return userService.list();
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "创建用户", required = true, dataType = "UserInfo")
    @PostMapping(value = "/")
    public String addUser(@RequestBody UserInfo user) {
        userService.saveUserInfo(user,user.getRoles());
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @GetMapping(value = "/{id}")
    public UserInfo getUser(String id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(String id, @RequestBody UserInfo user) {
        UserInfo u = userService.getById(id);
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        userService.updateById(u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String",paramType = "query")
    @DeleteMapping(value = "/{id}")
    public String delUser(String id) {
        userService.removeById(id);
        return "success";
    }

    @GetMapping(value = "/testError")
    public String testError() throws Exception {
        throw new Exception("發生錯誤");
    }

}

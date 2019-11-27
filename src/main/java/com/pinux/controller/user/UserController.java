package com.pinux.controller.user;


import com.pinux.entity.user.User;
import com.pinux.service.user.UserService;
import com.pinux.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping(value = "/")
    @ResponseBody
    public List<User> getAllList() {
        return userService.list();
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "创建用户", required = true, dataType = "User")
    @PostMapping(value = "/")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        user.setPassword(MD5Utils.encrypt(user.getUserName(),user.getPassword()));
        userService.save(user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public User getUser(String id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(String id, @RequestBody User user) {
        User u = userService.getById(id);
        user.setPassword(MD5Utils.encrypt(user.getUserName(),user.getPassword()));
        userService.updateById(u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String",paramType = "query")
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String delUser(String id) {
        userService.removeById(id);
        return "success";
    }

    @GetMapping(value = "/testError")
    @ResponseBody
    public String testError() throws Exception {
        throw new Exception("發生錯誤");
    }

    @RequiresPermissions("user:user")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("value", "获取用户信息");
        return "user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String userAdd(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String userDelete(Model model) {
        model.addAttribute("value", "删除用户");
        return "user";
    }

}

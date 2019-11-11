package com.pinux.springbootpinux;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pinux.entity.role.Role;
import com.pinux.entity.user.UserInfo;
import com.pinux.service.role.RoleService;
import com.pinux.service.user.UserInfoService;
import com.pinux.util.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringBootPinuxApplicationTests {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;

    @Test
    void contextLoads() {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(UUIDGenerator.generate());
        userInfo.setName("user");
        userInfo.setPassword("123");
        List<Role> roles=roleService.list(new QueryWrapper<Role>().eq("name","normal"));
        userInfoService.saveUserInfo(userInfo,roles);
    }

}

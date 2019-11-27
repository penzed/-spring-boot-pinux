package com.pinux.springbootpinux;

import com.pinux.service.role.RoleService;
import com.pinux.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPinuxApplicationTests {
    @Autowired
    private UserService userInfoService;
    @Autowired
    private RoleService roleService;

    @Test
    void contextLoads() {
    }

}

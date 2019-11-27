package com.pinux.springbootpinux;

import com.pinux.entity.user.User;
import com.pinux.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
* ExamController Tester.
*
* @author <Authors name>
* @since <pre>ʮһ�� 5, 2019</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CacheTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCache() throws Exception{
        User users= userService.findByUserName("test");
        User jack= userService.findByUserName("test");
        System.out.println(cacheManager.getCacheNames().toString());
    }

}

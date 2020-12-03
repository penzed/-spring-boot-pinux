package com.pinux.springbootpinux;

import com.pinux.entity.user.User;
import com.pinux.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * ExamController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 5, 2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TransactionTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void transactionTest() throws Exception {
        User aa = new User();
        aa.setId("90");
        aa.setUsername("aa");
        aa.setPassword("a");
        aa.setStatus("1");
        aa.setCreateTime(new Date());
        User bb = new User();
        bb.setId("91");
        bb.setUsername("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        bb.setPassword("bb");
        bb.setStatus("1");
        bb.setCreateTime(new Date());
        User cc = new User();
        cc.setId("92");
        cc.setUsername("cc");
        cc.setPassword("cc");
        cc.setStatus("1");
        cc.setCreateTime(new Date());
        userService.saveUser(aa);
        //去掉try。。catch会自动回滚事务
        try {
            userService.saveUser(bb);
        } catch (Exception e) {
            System.out.println("失败");
        }
        userService.saveUser(cc);
    }

}

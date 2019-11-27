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

import java.util.Date;

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
public class TransactionTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void transactionTest() throws Exception{
        User aa=new User();
        aa.setId("90");
        aa.setUserName("aa");
        aa.setPassword("a");
        aa.setStatus("1");
        aa.setCreateTime(new Date());
        User bb=new User();
        bb.setId("91");
        bb.setUserName("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        bb.setPassword("bb");
        bb.setStatus("1");
        bb.setCreateTime(new Date());
        User cc=new User();
        cc.setId("92");
        cc.setUserName("cc");
        cc.setPassword("cc");
        cc.setStatus("1");
        cc.setCreateTime(new Date());
        userService.save(aa);
        userService.save(bb);
        userService.save(cc);
    }

}

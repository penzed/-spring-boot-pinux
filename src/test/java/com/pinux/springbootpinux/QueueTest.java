package com.pinux.springbootpinux;

import com.alibaba.fastjson.JSONObject;
import com.pinux.config.queue.Sender;
import com.pinux.entity.user.User;
import com.pinux.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class QueueTest {

    @Autowired
    private Sender sender;

    @Test
    public void testQueue() throws Exception{
        sender.send();
    }

}

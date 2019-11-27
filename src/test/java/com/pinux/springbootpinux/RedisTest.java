package com.pinux.springbootpinux;

import com.alibaba.fastjson.JSONObject;
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
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * @Author pinux
     * @Description 测试redis存取
     * @Date 2019/11/5 8:32
     *
     * @return void
     **/
    @Test
    public void testRedis() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
    }

    /**
     * @Author pinux
     * @Description 测试redis存取
     * @Date 2019/11/5 8:32
     *
     * @return void
     **/
    @Test
    public void testUserRedis(){
        User user=userService.getById("2");
        stringRedisTemplate.opsForValue().set(user.getUserName(), JSONObject.toJSONString(user));
        User u = JSONObject.parseObject(stringRedisTemplate.opsForValue().get(user.getUserName()),User.class);
        System.out.println(u.toString());

    }


}

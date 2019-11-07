package com.pinux.springbootpinux;

import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
public class MailTest {

    @Test
    public void testMail() throws Exception{
        MailUtil.send("1063440021@qq.com", "测试", "邮件来自Hutool测试", false);
    }

}

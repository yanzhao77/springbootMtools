package com.yz.javamail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.util.Date;


@SpringBootTest
class JavaMailApplicationTests {

    @Resource
    JavaMailSender javaMailSender;
    @Resource
    private ApplicationContext ioc;

    /**
     * 普通邮件发送
     */
    @Test
    public void sendSimpleMail() {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("756624050@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo("yanz@softroad.com.cn");
        // 设置邮件抄送人，可以有多个抄送人
        message.setCc("hemz@softroad.com.cn");
        // 设置隐秘抄送人，可以有多个
        message.setBcc("794070011@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("这是测试邮件的正文");

        // 发送邮件
        javaMailSender.send(message);
    }

    @Test
    public void test2() {
//        EMailProperties eMailProperties = ioc.getBean("EMailProperties", EMailProperties.class);
//        System.out.println(eMailProperties.getContent());
    }


}

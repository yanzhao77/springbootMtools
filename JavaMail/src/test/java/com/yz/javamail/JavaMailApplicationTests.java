package com.yz.javamail;

import com.yz.javamail.entity.EMailProperties;
import com.yz.javamail.utils.EmailSenderUtils;
import com.yz.toolscommon.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


@SpringBootTest
class JavaMailApplicationTests {

//    @Resource
//    JavaMailSender javaMailSender;

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
//        javaMailSender.send(message);
    }

    @Autowired
    EMailProperties properties;

    @Autowired
    EmailSenderUtils emailSenderUtils;

//    @Autowired
//    JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        System.out.println(properties);
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(properties.getHost());
        if (properties.getPort() != null) {
            sender.setPort(properties.getPort());
        }

        sender.setUsername(properties.getUsername());
        sender.setPassword(properties.getPassword());
        sender.setProtocol(properties.getProtocol());
        if (properties.getDefaultEncoding() != null) {
            sender.setDefaultEncoding(properties.getDefaultEncoding().name());
        }
        Properties javaMailProperties = new Properties();
        javaMailProperties.putAll(properties.getProperties());
        sender.setJavaMailProperties(javaMailProperties);


        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件222");
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


        sender.send(message);
        System.out.println(message);
    }

    @Test
    void Test2() {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件222");
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

        //发送邮件
//        javaMailSender.send(message);
        System.out.println(message);
    }

    @Test
    void test3() {
        String fromaddr = "mailtest61@srtest.cc";
        String toaddr = "yanz@softroad.com.cn";
        String title = "【测试标题】Testing Subject-myself-eml文件";
//        String title = "【测试标题】Testing Subject-myself-eml文件_含多个附件";
        String content = "【测试内容】Hello, this is sample for to check send email using JavaMailAPI ";
        String port = "587";
        String host = "smtp.qq.com";
        String userName = "756624050@qq.com";
//        String password = "cxtfolvvqdzjbegf";

        emailSenderUtils.sendMail(fromaddr, toaddr, port, host, null, null, null, null, new Date(), title, content);
    }

    @Test
    public void test4() {
        String rex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String path = "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLLE\\IBMSRCF\\QCLLESRC\\TP4100C.CLLE";
        List<String> contextList = StringUtil.siphonFileString(new File(path), Charset.forName("ms932"));

        for (String context : contextList) {
            if (context.contains("@")) {
                List<String> stringRex = StringUtil.getStringRex(context, rex);
                System.out.println(stringRex);
            }
        }
    }


}

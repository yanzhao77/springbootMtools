package com.yz.javamail.utils;

import com.yz.javamail.entity.EMailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailSenderUtils {

    @Autowired
    EMailProperties properties; //user默认发送参数

    JavaMailSender javaMailSender;//发送器

    private final Logger logger = LoggerFactory.getLogger(EmailSenderUtils.class);

    /**
     * 设置发送参数
     *
     * @param sendEmailUserAddress // 发送者邮箱
     * @param emailSMTPServerPort  //server 端口号
     * @param emailSMTPServerHost  //SMTPServer 服务器地址
     * @param authorizationCode    //SMTPServer  授权码
     */
    private JavaMailSenderImpl init(String sendEmailUserAddress, String emailSMTPServerPort, String emailSMTPServerHost, String authorizationCode) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        if (StringUtils.isEmpty(emailSMTPServerHost)) {
            sender.setHost(properties.getHost());
        } else {
            sender.setHost(emailSMTPServerHost);
        }

        if (StringUtils.isEmpty(emailSMTPServerPort)) {
            sender.setPort(properties.getPort());
        } else {
            sender.setPort(Integer.parseInt(emailSMTPServerPort));
        }

        if (StringUtils.isEmpty(sendEmailUserAddress)) {
            sender.setUsername(properties.getUsername());
        } else {
            sender.setUsername(sendEmailUserAddress);
        }

        if (StringUtils.isEmpty(authorizationCode)) {
            sender.setPassword(properties.getPassword());
        } else {
            sender.setPassword(authorizationCode);
        }

        sender.setProtocol(properties.getProtocol());
        if (properties.getDefaultEncoding() != null) {
            sender.setDefaultEncoding(properties.getDefaultEncoding().name());
        }
        Properties javaMailProperties = new Properties();
        javaMailProperties.putAll(properties.getProperties());
        sender.setJavaMailProperties(javaMailProperties);
        return sender;
    }


    /**
     * @param sendEmailUserAddress      // 发送者邮箱
     * @param recipientEmailUserAddress // 接收者邮箱
     * @param emailSMTPServerPort       //server 端口号
     * @param emailSMTPServerHost       //SMTPServer 服务器地址
     * @param authorizationCode         //SMTPServer  授权码
     * @param ccEmailUser               //邮件抄送人，可以有多个
     * @param bccEmailUser              //隐秘抄送人，可以有多个
     * @param attachmentPaths           // Email附件地址  可以有多个
     * @param sendEmailDate             //Email发送时间
     * @param emailSubject              // Email发送主题
     * @param emailContent              //Email发送正文
     */
    public void sendMail(String sendEmailUserAddress,  String emailSMTPServerPort, String emailSMTPServerHost, String authorizationCode,String[] recipientEmailUserAddress, String[] ccEmailUser, String[] bccEmailUser, String[] attachmentPaths, Date sendEmailDate, String emailSubject, String emailContent) {

        javaMailSender = init(sendEmailUserAddress, emailSMTPServerPort, emailSMTPServerHost, authorizationCode);
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sendEmailUserAddress);
            messageHelper.setTo(recipientEmailUserAddress);
            messageHelper.setSubject(emailSubject);
            messageHelper.setText(emailContent, true);
            //添加邮件抄送人
            if (!StringUtils.isEmpty(ccEmailUser)) {
                messageHelper.setCc(ccEmailUser);
            }
            //添加邮件隐秘抄送人
            if (!StringUtils.isEmpty(bccEmailUser)) {
                messageHelper.setBcc(bccEmailUser);
            }
            //携带附件
            if (!StringUtils.isEmpty(attachmentPaths)) {
                for (String attachmentPath : attachmentPaths) {
                    FileSystemResource file = new FileSystemResource(attachmentPath);
                    String fileName = attachmentPath.substring(attachmentPath.lastIndexOf(File.separator));
                    messageHelper.addAttachment(fileName, file);
                }
            }

            // 设置发送时间
            message.setSentDate(sendEmailDate);

            javaMailSender.send(message);
            logger.info("邮件加附件发送成功！");
        } catch (MessagingException e) {
            logger.error("发送失败：" + e);
        }
    }

    /**
     * @param sendEmailUserAddress      // 发送者邮箱
     * @param recipientEmailUserAddress // 接收者邮箱
     * @param emailSMTPServerPort       //server 端口号
     * @param emailSMTPServerHost       //SMTPServer 服务器地址
     * @param authorizationCode         //SMTPServer  授权码
     * @param sendEmailDate             //Email发送时间
     * @param emailSubject              // Email发送主题
     * @param emailContent              //Email发送正文
     */
    public void sendMail(String sendEmailUserAddress, String sendEmailUserName, String[] recipientEmailUserAddress, String emailSMTPServerPort, String emailSMTPServerHost, String authorizationCode, Date sendEmailDate, String emailSubject, String emailContent) {
        javaMailSender = init(sendEmailUserAddress, emailSMTPServerPort, emailSMTPServerHost, authorizationCode);

        {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper;
            try {
                messageHelper = new MimeMessageHelper(message, true);
                messageHelper.setFrom(sendEmailUserAddress);
                messageHelper.setTo(recipientEmailUserAddress);
                messageHelper.setSubject(emailSubject);
                messageHelper.setText(emailContent, true);

                // 设置发送时间
                message.setSentDate(sendEmailDate);
                //发送无附件的邮件
                javaMailSender.send(message);
                logger.info("邮件已经发送！");
            } catch (MessagingException e) {
                logger.error("发送邮件时发生异常：" + e);
            }
        }
    }


    public void sendSimpleMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(properties.getUsername());
            messageHelper.setTo(to);
            message.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            logger.info("邮件已经发送！");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常：" + e);
        }
    }

    /**
     * 附件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(properties.getUsername());
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            //携带附件
            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName, file);

            javaMailSender.send(message);
            logger.info("邮件加附件发送成功！");
        } catch (MessagingException e) {
            logger.error("发送失败：" + e);
        }
    }
}

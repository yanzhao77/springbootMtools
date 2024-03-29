package com.yz.javamail.config;

import com.yz.javamail.entity.EmailSendInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Address;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


public class EmailSender {

    public static void main(String[] args) {
        String fromaddr = "756624050@qq.com";
        String toaddr = "yanz@softroad.com.cn";
//        String title = "【测试标题】Testing Subject-myself-TEXT";
//        String title = "【测试标题】Testing Subject-myself-HTML";
        String title = "【测试标题】Testing Subject-myself-eml文件";
//        String title = "【测试标题】Testing Subject-myself-eml文件_含多个附件";
        String content = "【测试内容】Hello, this is sample for to check send email using JavaMailAPI ";
        String port = "25";
        String host = "smtp.qq.com";
        String userName = "756624050@qq.com";
        String password = "cxtfolvvqdzjbegf";

        EmailSendInfo mailInfo = new EmailSendInfo();
        mailInfo.setMailServerHost(host);
        mailInfo.setMailServerPort(port);
        mailInfo.setValidate(true);
        mailInfo.setUserName(userName);
        mailInfo.setPassword(password);
        mailInfo.setFromAddress(fromaddr);
        mailInfo.setToAddress(toaddr);
        mailInfo.setSubject(title);
        mailInfo.setContent(content);
//        mailInfo.setAttachFileNames(new String[]{"E:\\本地笔记文件\\CTFE-spd分析.txt", "E:\\poto\\aaa.jpg"});

        //发送文体格式邮件
//         EmailSender.sendTextMail(mailInfo);
        //发送html格式邮件
//         EmailSender.sendHtmlMail(mailInfo);
        //发送含附件的邮件
        if (mailInfo.getAttachFileNames() != null) {
            EmailSender.sendAttachmentMail(mailInfo);
        } else {
            EmailSender.sendTextMail(mailInfo);
//            EmailSender.sendHtmlMail(mailInfo);

        }

        //读取eml文件发送
//         File emailFile = new File("file/EML_myself-eml.eml");
//         File emailFile = new File("file/EML_reademl-eml文件_含文本附件.eml");
//         File emailFile = new File("file/EML_reademl-eml文件_含图片附件.eml");
//         File emailFile = new File("file/EML_reademl-eml文件_含多个附件.eml");
//         EmailSender.sendMail(mailInfo, emailFile);
    }

    public static boolean sendTextMail(EmailSendInfo mailInfo) {
        boolean sendStatus = false;//发送状态
        // 判断是否需要身份认证
        EmailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        //【调试时使用】开启Session的debug模式
        sendMailSession.setDebug(true);
        try {
            // 根据session创建一个邮件消息
            MimeMessage mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject(), "UTF-8");
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent, "UTF-8");
//            mailMessage.saveChanges();

            //生成邮件文件
            createEmailFile("file/EML_myself-TEXT.eml", mailMessage);

            // 发送邮件
            Transport.send(mailMessage);
            sendStatus = true;
        } catch (MessagingException ex) {
            //Log.error("以文本格式发送邮件出现异常", ex);
            return sendStatus;
        }
        return sendStatus;
    }

    public static boolean sendHtmlMail(EmailSendInfo mailInfo) {
        boolean sendStatus = false;//发送状态
        // 判断是否需要身份认证
        EmailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        //【调试时使用】开启Session的debug模式
        sendMailSession.setDebug(true);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件内容
            mailMessage.setContent(mailInfo.getContent(), "text/html;charset=utf-8");

            //生成邮件文件
            createEmailFile("file/EML_myself-HTML.eml", mailMessage);

            // 发送邮件
            Transport.send(mailMessage);
            sendStatus = true;
        } catch (MessagingException ex) {
            //Log.error("以HTML格式发送邮件出现异常", ex);
            return sendStatus;
        }
        return sendStatus;
    }

    public static boolean sendAttachmentMail(EmailSendInfo mailInfo) {
        boolean sendStatus = false;//发送状态
        // 判断是否需要身份认证
        EmailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        //【调试时使用】开启Session的debug模式
        sendMailSession.setDebug(true);
        try {
            // 根据session创建一个邮件消息
            MimeMessage mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject(), "UTF-8");
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart bodyPart = new MimeBodyPart();
            //设置TEXT内容
//            bodyPart.setText(mailInfo.getContent());
            // 设置HTML内容
            bodyPart.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(bodyPart);
            //设置附件
            String[] filenames = mailInfo.getAttachFileNames();
            for (int i = 0; i < filenames.length; i++) {
                // Part two is attachment
                bodyPart = new MimeBodyPart();
                String filename = filenames[i];//1.txt/sohu_mail.jpg
//                 DataSource source = new FileDataSource(filename);
                FileDataSource source = new FileDataSource(filename);
                bodyPart.setDataHandler(new DataHandler(source));
                bodyPart.setFileName(source.getName());
                mainPart.addBodyPart(bodyPart);
            }
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);

            //生成邮件文件
            createEmailFile("file/EML_reademl-eml文件_含多个附件.eml", mailMessage);

            // 发送邮件
            Transport.send(mailMessage);
            sendStatus = true;
        } catch (MessagingException ex) {
            //Log.error("以文本格式发送邮件出现异常", ex);
            return sendStatus;
        }
        return sendStatus;
    }

    public static void createEmailFile(String fileName, Message mailMessage) throws MessagingException {

        File f = new File(fileName);
        try {
            mailMessage.writeTo(new FileOutputStream(f));
        } catch (IOException e) {
            //Log.error("IOException", e);
        }
    }


}

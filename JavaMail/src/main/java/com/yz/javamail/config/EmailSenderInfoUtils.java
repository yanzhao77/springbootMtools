package com.yz.javamail.config;

import com.yz.javamail.entity.EmailSendInfo;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class EmailSenderInfoUtils {


    public static void main(String[] args) {
        String fromaddr = "756624050@qq.com";
        String toaddr = "yanz@softroad.com.cn";
        String title = "【测试标题】Testing Subject-myself-eml文件";
//        String title = "【测试标题】Testing Subject-myself-eml文件_含多个附件";
        String content = "【测试内容】Hello, this is sample for to check send email using JavaMailAPI ";
        String port = "587";
        String host = "smtp.qq.com";
        String userName = "756624050@qq.com";
        String password = "cxtfolvvqdzjbegf";


    }


    /**
     * @param sendEmailUserAddress      // 发送者邮箱
     * @param sendEmailUserName         // 发送者名字
     * @param recipientEmailUserAddress // 接收者邮箱
     * @param recipientEmailUserName    // 接收者名字
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
    public void sendMail(String sendEmailUserAddress, String sendEmailUserName,
                         String recipientEmailUserAddress, String recipientEmailUserName,
                         String emailSMTPServerPort, String emailSMTPServerHost, String authorizationCode,
                         Map<String, String> ccEmailUser, Map<String, String> bccEmailUser, String[] attachmentPaths, Date sendEmailDate,
                         String emailSubject, String emailContent) {

        EmailSendInfo mailInfo = new EmailSendInfo();

        mailInfo.setUserName(sendEmailUserAddress);
        mailInfo.setPassword(sendEmailUserAddress);
        mailInfo.setFromAddress(sendEmailUserAddress);
        mailInfo.setToAddress(recipientEmailUserAddress);
        mailInfo.setMailServerHost(emailSMTPServerHost);
        mailInfo.setMailServerPort(emailSMTPServerPort);
        mailInfo.setValidate(true);

        mailInfo.setSubject(emailSubject);
        mailInfo.setContent(emailContent);

        // Email附件地址
        mailInfo.setAttachFileNames(attachmentPaths);

        //发送含附件的邮件
        if (mailInfo.getAttachFileNames() != null) {
            EmailSenderInfoUtils.sendAttachmentMail(mailInfo, sendEmailDate);
        } else {
            EmailSenderInfoUtils.sendTextMail(mailInfo, sendEmailDate);
        }
    }

    /**
     * @param sendEmailUserAddress      // 发送者邮箱
     * @param sendEmailUserName         // 发送者名字
     * @param recipientEmailUserAddress // 接收者邮箱
     * @param recipientEmailUserName    // 接收者名字
     * @param emailSMTPServerPort       //server 端口号
     * @param emailSMTPServerHost       //SMTPServer 服务器地址
     * @param authorizationCode         //SMTPServer  授权码
     * @param sendEmailDate             //Email发送时间
     * @param emailSubject              // Email发送主题
     * @param emailContent              //Email发送正文
     */
    public void sendMail(String sendEmailUserAddress, String sendEmailUserName,
                         String recipientEmailUserAddress, String recipientEmailUserName,
                         String emailSMTPServerPort, String emailSMTPServerHost, String authorizationCode,
                         Date sendEmailDate, String emailSubject, String emailContent) {
        EmailSendInfo mailInfo = new EmailSendInfo();

        mailInfo.setUserName(sendEmailUserAddress);
        mailInfo.setPassword(sendEmailUserAddress);
        mailInfo.setFromAddress(sendEmailUserAddress);
        mailInfo.setToAddress(recipientEmailUserAddress);

        mailInfo.setMailServerHost(emailSMTPServerHost);
        mailInfo.setMailServerPort(emailSMTPServerPort);
        mailInfo.setValidate(true);

        mailInfo.setSubject(emailSubject);
        mailInfo.setContent(emailContent);

        //发送无附件的邮件
        EmailSenderInfoUtils.sendTextMail(mailInfo, sendEmailDate);
    }


    /**
     * 发送文本信息邮件
     *
     * @param mailInfo
     * @return
     */
    public static boolean sendTextMail(EmailSendInfo mailInfo, Date date) {
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
            mailMessage.setSentDate(date);
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


    /**
     * 添加自定义附件邮件发送
     *
     * @param mailInfo
     * @param emailFile
     * @return
     */
    public static boolean sendCloudMail(EmailSendInfo mailInfo, File emailFile) {
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
            InputStream source = new FileInputStream(emailFile);
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession, source);
            // 发送邮件
            Transport.send(mailMessage);
            //【重要】关闭输入流，否则会导致文件无法移动或删除
            source.close();
            sendStatus = true;
        } catch (MessagingException e) {
            //Log.error("以文本格式发送邮件出现异常", e);
            return sendStatus;
        } catch (FileNotFoundException e) {
            //Log.error("FileNotFoundException", e);
            return sendStatus;
        } catch (Exception e) {
            //Log.error("Exception", e);
            return sendStatus;
        }
        return sendStatus;
    }

    /**
     * 发送带附件的邮件
     *
     * @param mailInfo
     * @return
     */
    public static boolean sendAttachmentMail(EmailSendInfo mailInfo, Date date) {
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
            mailMessage.setSentDate(date);
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


    /**
     * 附件生成方法
     *
     * @param fileName
     * @param mailMessage
     * @throws MessagingException
     */
    public static void createEmailFile(String fileName, Message mailMessage)
            throws MessagingException {

        File f = new File(fileName);
        try {
            mailMessage.writeTo(new FileOutputStream(f));
        } catch (IOException e) {
            //Log.error("IOException", e);
        }
    }
}

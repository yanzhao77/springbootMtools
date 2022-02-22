package com.yz.javamail2.entity;


/**
 * Configs
 */
public class EmailSMTPConfig {


    private String emailSMTPServerHost; //server 端口号
    private String emailSMTPServerPort; //SMTPServer 服务器地址
    private String default_encoding; //SMTPServer  授权码
    private String sendEmailUserAddress; // 发送者邮箱
    private String authorizationCode;  //SMTPServer  授权码
    private String smtpSocketFactoryClass;
    private boolean sendEmailDebug; // #表示开启 DEBUG 模式，这样，邮件发送过程的日志会在控制台打印出来，方便排查错误
}
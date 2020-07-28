package com.dupake.system.service;

import javax.mail.MessagingException;

/**
 * @ClassName MailService
 * @Description 邮箱 service
 * @Author dupake
 * @Date 2020/6/3 16:23
 */
public interface MailService {

    /**
     * 简单文本邮件
     *
     * @param to      接收者邮件
     * @param subject 邮件主题
     * @param contnet 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String contnet);

    /**
     * HTML 文本邮件
     *
     * @param to      接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException;

    public void sendInlinkResourceMail(String to, String subject, String contnet,
                                       String rscPath, String rscId);

    public void sendAttachmentsMail(String to, String subject, String contnet,
                                    String filePath) throws MessagingException;
}

package com.dupake.system.service.impl;

import com.dupake.system.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @ClassName MailService
 * @Description 邮件 逻辑层
 * @Author dupake
 * @Date 2020/6/3 16:23
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {


    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    /**
     * 简单文本邮件
     *
     * @param to      接收者邮件
     * @param subject 邮件主题
     * @param contnet 邮件内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String contnet) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(contnet);
        message.setFrom(from);

        mailSender.send(message);
    }

    /**
     * HTML 文本邮件
     *
     * @param to      接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @throws MessagingException
     */
    @Override
    public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contnet, true);
        helper.setFrom(from);

        mailSender.send(message);
    }


    /**
     * 附件邮件
     *
     * @param to       接收者邮件
     * @param subject  邮件主题
     * @param contnet  HTML内容
     * @param filePath 附件路径
     * @throws MessagingException
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String contnet,
                                    String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contnet, true);
        helper.setFrom(from);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    /**
     * 图片邮件
     *
     * @param to      接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @param rscPath 图片路径
     * @param rscId   图片ID
     * @throws MessagingException
     */
    @Override
    public void sendInlinkResourceMail(String to, String subject, String contnet,
                                       String rscPath, String rscId) {
        log.info("发送静态邮件开始: {},{},{},{},{}", to, subject, contnet, rscPath, rscId);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {

            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            mailSender.send(message);
            log.info("发送静态邮件成功!");

        } catch (MessagingException e) {
            log.info("发送静态邮件失败: ", e);
        }

    }


}
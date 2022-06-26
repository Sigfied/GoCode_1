package com.example.demo.tools;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.lf5.util.Resource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author lenovo
 */
@Slf4j
public class MailUtils {
    //发送第二封验证码邮件



    public static void sendMail(String to, String vcode) throws Exception{

        //设置发送邮件的主机  smtp.qq.com

        String host = "smtp.qq.com";

        //1.创建连接对象，连接到邮箱服务器

        Properties props = System.getProperties();

        //Properties 用来设置服务器地址，主机名 。。 可以省略

        //设置邮件服务器

        props.setProperty("mail.smtp.host", host);

        props.put("mail.smtp.auth", "true");

        //SSL加密

        MailSSLSocketFactory sf = new MailSSLSocketFactory();

        sf.setTrustAllHosts(true);

        props.put("mail.smtp.ssl.enable","true");

        props.put("mail.smtp.ssl.socketFactory", sf);

        //props：用来设置服务器地址，主机名；Authenticator：认证信息

        Session session = Session.getDefaultInstance(props,new Authenticator() {

            @Override

            //通过密码认证信息

            protected PasswordAuthentication getPasswordAuthentication() {

                //new PasswordAuthentication(用户名, password);

                //这个用户名密码就可以登录到邮箱服务器了,用它给别人发送邮件

                return new PasswordAuthentication("2034689681@qq.com","mjolwzyowjwrbdgi");

            }

        });

        try {

            Message message = new MimeMessage(session);

            //2.1设置发件人：

            message.setFrom(new InternetAddress("2034689681@qq.com"));

            //2.2设置收件人 这个TO就是收件人

            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));

            //2.3邮件的主题

            message.setSubject("来自 GoCode 验证码邮件");

            //2.4设置邮件的正文 第一个参数是邮件的正文内容 第二个参数是：是文本还是html的连接

            message.setContent("<h1>来自 GoCode-JieJie的验证码邮件,请接收你的验证码：</h1><h3>你的验证码是："+vcode+"，请妥善保管好你的验证码！(GYJ)</h3>", "text/html;charset=UTF-8");

            //3.发送一封激活邮件

            Transport.send(message);



        }catch(MessagingException mex){

            mex.printStackTrace();

        }

    }


    /**
     * 读取邮件模板
     * 替换模板中的信息
     *
     * @param title 内容
     * @return
     */
    public String buildContent(String title) {
        //加载邮件html模板
        Resource resource = new Resource("mail.ftlh");
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.info("发送邮件读取模板失败{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), title);
    }

}

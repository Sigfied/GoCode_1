<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="email code">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>您正在通过验证</title>
</head>
<!--邮箱验证码模板-->
<body>
<div style="background-color:#ECECEC; padding: 35px;">
    <table cellpadding="0" align="center"
           style="width: 800px;height: 100%; margin: 0 auto; text-align: left; position: relative; border-radius: 5px;font-size: 14px; font-family:微软雅黑, 黑体,serif; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0 0 5px; border-collapse: collapse; background: #fff initial initial initial initial;">
        <tbody>
        <tr>
            <th valign="middle"
                style="height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(148,0,211); background-color: RGB(148,0,211); border-radius: 5px 5px 0 0;">
                <font face="微软雅黑" size="5" style="color: rgb(255, 255, 255); ">欢迎使用GoCode</font>
            </th>
        </tr>
        <tr>
            <td style="word-break:break-all">
                <div style="padding:25px 35px 40px; background-color:#fff;opacity:0.8;">

                    <h2 style="margin: 5px 0; ">
                        <font color="#333333" style="line-height: 20px; ">
                            <font style="line-height: 22px; " size="4">
                                尊敬的用户：</font>
                        </font>
                    </h2>
                    <!-- 中文 -->
                    <p>您好！感谢您使用GoCode，您的账号正在进行邮箱验证，验证码为：<span style="color: #ff8c00; " th:text="verificationCode"></span>,请尽快填写验证码完成验证且妥善保存您的验证码！</p><br>
                    <!-- 英文 -->
                    <h2 style="margin: 5px 0; ">
                        <span style="line-height: 20px;  color: #333333; ">
                            <font style="line-height: 22px; " size="4">
                                Dear user:</font>
                        </span>
                    </h2>
                    <p>Hello! Thanks for using GoCode, your account is being authenticated by email, the
                        verification code is:<span style="color: #ff8c00; " th:text="${verificationCode}"></span>, Please fill in the verification code as soon as
                        possible and keep your verification code!</p>
                    <div style="width:100%;margin:0 auto;">
                        <div style="padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;">
                            <p>GoCode团队</p>
                            <p>联系我们：2034689681@qq,com</p>
                            <br>
                            <p>此为系统邮件，请勿回复<br>
                                Please do not reply to this system email
                            </p>
                            <!--<p>©***</p>-->
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>


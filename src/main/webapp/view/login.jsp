<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2019/12/24
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SimDash : Widget</title>

    <%--CSS样式--%>
    <%@ include file="/include/link_css.jsp" %>
    <%--CSS样式--%>
</head>

<body class="bg-primary">

<div class="unix-login">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="login-content">
                    <div class="login-logo">
                        <a href="index.html"><span>程序员小铺</span></a>
                    </div>
                    <div class="login-form">
                        <h4>管理员登录</h4>
<!-- form -->               <form action="/project_jdbc/login.do" method="get">
                            <div class="form-group">
                                <label>电子邮件地址</label>
                                <input type="email" name="uname" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label>密码</label>
                                <input type="password" name="pwd" class="form-control" placeholder="Password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> 记住密码
                                </label>
                                <label class="pull-right">
                                    <a href="page-reset-password.html">忘记密码?</a>
                                </label>

                            </div>
                            <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">登录</button>

                        <%--<div class="social-login-content">--%>
                            <%--<div class="social-button">--%>
                                <%--<button type="button" class="btn btn-primary bg-facebook btn-flat btn-addon m-b-10"><i class="ti-facebook"></i>用 facebook 登录</button>--%>
                                <%--<button type="button" class="btn btn-primary bg-twitter btn-flat btn-addon m-t-10"><i class="ti-twitter"></i>用twitter登录</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                            <div class="register-link m-t-15 text-center">
                                <p>没有账号? <a href="page-register.html"> 在此注册</a></p>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
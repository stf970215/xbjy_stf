<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/form-elements.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <title>重置密码</title>
    <style>
        body {
            background: url("/static/img/bg.jpg") no-repeat fixed;
            background-size: cover;
            overflow: hidden;
        }

    </style>
</head>
<body>

<form action="/sys/user/forgetPassword" method="get">
账号：<input type="text" name="account" value=""><br><br>
新密码：<input type="text" name="password" value=""><br><br>
邮箱：<input type="text" id="email" value="">
<input type="button" id="btn-send" value="发送验证码"/> <span id="time"></span><br><br>
验证码：<input type="text" name="code" value=""><br><br>
<input type="submit" name="" value="修改">
</form>

<%--<div class="container myBox">--%>
    <%--<div class="col-xs-8 col-xs-offset-3 col-sm-6 form-box">--%>
        <%--<div class="form-top">--%>
            <%--<div class="form-top-left">--%>
                <%--<h3>重置密码</h3>--%>
            <%--</div>--%>
            <%--<div class="form-top-right">--%>
                <%--<i class="fa fa-key"></i>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-bottom">--%>
            <%--<form role="form" action="/sys/user/forgetPassword" method="get" class="login-form">--%>

                <%--<!--上面的输入框尽可能不需要外边距，使用row解决-->--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="text" name="account" placeholder="账号">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="password" name="password" placeholder="新密码">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <%--<div style="margin-bottom: 15px" class="form-inline">--%>
                        <%--<input type="text" placeholder="邮箱" id="email">--%>
                        <%--<input type="button" class="btn btn-primary" id="btn-send" value="发送验证码">--%>
                        <%--<span id="time"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="sr-only" for="code">验证码</label>--%>
                        <%--<input type="text" id="code" name="code" placeholder="验证码">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<!--上面的输入框尽可能不需要外边距，使用row包裹起来解决-->--%>

                <%--<button type="submit" class="btn">重置</button>--%>

                <%--<div class="row">--%>
                    <%--<div style="padding: 10px 25px">--%>
                        <%--<div style="display: inline-block;float: left" class="text-left"><a href="/index.jsp">返回登录</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

</body>
<!-- Javascript -->
<script src="${path}/static/js/jquery-1.11.1.js"></script>
<script src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src="${path}/static/js/jquery.backstretch.js"></script>
<script src="${path}/static/js/scripts.js"></script>
<script>

    var time = 60;
    //定时器
    var dsq;

    $(function () {
        //发送验证码
        $("#btn-send").click(function () {

            dsq = window.setInterval("changeTime()", 1000);
            $("#btn-send").attr("disabled", "disabled");

            var email = $("#email").val();
            $.ajax({
                url: "/sys/email/sendEmail",
                data: {email: email},
                type: "get",
                dataType: "text",
                success: function (data) {
                    alert(data);
                }
            });

        });
    });

    function changeTime() {
        --time;
        $("#time").text(time);
        if (time == 0) {
            $("#time").text("");
            time = 60;
            window.clearInterval(dsq);
            $("#btn-send").attr("disabled", null);
        }
    }

</script>

</html>

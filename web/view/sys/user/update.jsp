<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="${path}/static/jquery-validation-1.9.0/jquery.validate.js"></script>
</head>
<style>
    .error {
        color: red;
    }

</style>

<body>
<%@ include file="/view/comm_test/head.jsp" %>
<div>
    <%@ include file="/view/comm_test/menu.jsp" %>
    <div style="border: 1px solid red;width: 88%;height: 85%;float: right;">

        <form action="/sys/user/update" method="get" id="form-add">
            <input type="text" value="${user.id}" name="id" style="display: none">
            <div class="form-group">
                <label>部门</label>
                <select id="dept" name="deptId" class="form-control">

                </select>
            </div>

            <div class="form-group">
                <label>账号</label>
                <input type="text" value="${user.account}" class="form-control" id="account" name="account" placeholder="请输入账号">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="text" value="${user.password}" class="form-control" id="password" name="password" placeholder="请输入6位数字">
            </div>
            <div class="form-group">
                <label>姓名</label>
                <input type="text" value="${user.name}" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label>年龄</label>
                <input type="text" value="${user.age}" class="form-control" id="age" name="age">
            </div>
            <div class="form-group">
                <label>性别</label>
                <input type="radio" id="male" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if>>男
                <input type="radio" id="female" name="sex" value="1" <c:if test="${user.sex==0}">checked</c:if>>女
            </div>
            <div class="form-group">
                <label>邮箱</label>
                <input type="text" value="${user.email}" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label>出生日期</label>
                <input type="date" value="${user.birthDate}" class="form-control" id="birthDate" name="birthDate">
            </div>
            <!--居中-->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">修改</button>
                <button type="reset" class="btn btn-default">重置</button>
                <a href="/sys/user/list" class="btn btn-danger">返回</a>
            </div>
        </form>

    </div>
</div>
</body>
<script>
    $(function () {

        var deptid=${user.deptId};
        $.ajax({
            url: "/sys/dept/listAll",
            data: "",
            type: "get",
            dataType: "json",
            success: function (data) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id==deptid) {
                        html = html + '<option selected value="' + data[i].id + '">' + data[i].name + '</option>';
                    }else {
                        html = html + '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }

                }
                $("#dept").append(html);
            }
        });

        $.validator.addMethod("checkAccount", function (value, element, params) {
            var reg = /^[0-9a-zA-Z]{5,10}$/;
            if (reg.test(value)) {
                return true;
            } else {
                return false;
            }
        });
        $("#form-add").validate({
            rules: {
                account: {
                    checkAccount: ""
                }
            },
            messages: {
                account: {
                    checkAccount: "请输入5-10位的账号！"
                }
            }
        });

    })
    ;
</script>
</html>

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

        <form action="/sys/dept/add" method="get" id="form-add">
            <%--<div class="form-group">--%>
                <%--<label>部门</label>--%>
                <%--<select id="dept" name="deptId" class="form-control">--%>

                <%--</select>--%>
            <%--</div>--%>

            <div class="form-group">
                <label>部门名称</label>
                <input type="text" class="form-control" id="account" name="deptname" placeholder="请输入要创建的部门">
            </div>
            <!--居中-->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">保存</button>
                <button type="reset" class="btn btn-default">重置</button>
                <a href="/sys/dept/list" class="btn btn-danger">返回</a>
            </div>
        </form>

    </div>
</div>
</body>
<script>
    $(function () {
        $.ajax({
            url: "/sys/dept/listAll",
            data: "",
            type: "get",
            dataType: "json",
            success: function (data) {
                var html = '<option value="-1">请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html = html + '<option value="' + data[i].id + '">' + data[i].name + '</option>';
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

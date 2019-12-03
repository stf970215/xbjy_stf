<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/11/29
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script>
        $(function () {
            $.ajax({
                url: '/sys/menu',
                data: '',
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    var html = '';
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].type == 1) {
                            html = html + data[i].name;
                            html = html + '<ul>';
                            for (var j = 0; j < data.length; j++) {
                                if (data[i].id == data[j].pId) {
                                        if (data[j].type== 2) {
                                            html = html+'<li><a href="'+data[j].menuUrl+'">'+data[j].name+'</a></li>';
                                        }
                                }
                            }
                            html += '</ul>';
                        }
                    }
                    $("#div-menu").append(html);
                },
                error: function () {
                    console.log("失败");
                }
            })
        })
    </script>
</head>
<body>
<div id="div-menu" style="border: 1px solid red;width: 10%;height: 85%;float: left;">


</div>
</body>
</html>

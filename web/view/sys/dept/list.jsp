<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <%--<%@ include file="/taglib.jsp" %>--%>
</head>
<script>
    $(function () {
        <%--alert(${dept[0].user});--%>
    });
</script>

<body>
<%@ include file="/view/comm_test/head.jsp" %>
<div>
    <%@ include file="/view/comm_test/menu.jsp" %>
    <div style="border: 1px solid red;width: 88%;height: 85%;float: right;">



        <form action="/sys/dept/list" method="get">
            部门名：<input type="text" value="${bname}" name="bname">
            开始时间：<input type="date" value="" name="ks">
            结束时间：<input type="date" value="" name="js"><br>

            <input type="submit" value="查询" class="btn btn-primary">
        </form>

        <a href="/view/sys/dept/add.jsp" class="btn btn-success">添加</a>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>部门名称</th>
                <th>创建人</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${dept}" var="dept" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${dept.user}</td>
                    <td>${dept.name}</td>
                    <td>
                            <%--把日期类型字符串转换成日期对象--%>
                        <fmt:parseDate var="createTime" value="${dept.createTime}"
                                       pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                            <%--把日期对象转换成规定的字符串格式--%>
                        <fmt:formatDate value="${createTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                    </td>
                    <td>
                        <a href="/sys/dept/toUpdate?id=${dept.id}" class="btn btn-danger">修改</a>
                        <%--<input type="button" class="btn btn-danger" id="btn-sc" value="删除">--%>
                        <a href="/sys/dept/deleteById?id=${dept.id}" class="btn btn-danger">删除</a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>

        <a href="/sys/dept/list?bname=${bname}&page=1">首页</a>
        <a href="/sys/dept/list?bname=${bname}&page=${page.pageCurrent<=1 ? 1 : (page.pageCurrent-1)}">上一页</a>
        <a href="/sys/dept/list?bname=${bname}&page=${page.pageCurrent>=page.pageCount ? page.pageCount : (page.pageCurrent+1) }">下一页</a>
        <a href="/sys/dept/list?bname=${bname}&page=${page.pageCount}">末页</a>
        当前页：${page.pageCurrent},总页数：${page.pageCount}，总记录数：${page.count}

    </div>
</div>
</body>
<%--<script>--%>
    <%--$(function () {--%>
        <%--$("#btn-sc").click(function () {--%>
            <%--alert(${dept.user});--%>
            <%--alert(${dept.id});--%>
        <%--})--%>
    <%--})--%>
<%--</script>--%>
</html>

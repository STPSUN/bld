<%@ page import="java.lang.annotation.Target" %><%--
  Created by IntelliJ IDEA.
  User: SUN
  Date: 2017/3/20
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css"
          href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../js/jquery2.js"></script>
    <script type="text/javascript" src="../js/jquery2.sorted.js"></script>
    <script type="text/javascript" src="../js/bootstrap.js"></script>
    <script type="text/javascript" src="../js/ckform.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>

    <%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>


    <style type="text/css">
        body {font-size: 20px;
            padding-bottom: 40px;
            background-color: #e9e7ef;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media ( max-width : 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
</head>
<body>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>用户名</th>
        <th>昵称</th>
        <th>密码</th>
        <th>性别</th>
    </tr>
    </thead>

    <c:if test="${empty userList }">
        <tr>
            <td colspan="5" align="center">暂无记录</td>
        </tr>
    </c:if>
    <c:if test="${not empty userList }">
        <c:forEach var="item" items="${userList }">
    <form class="form" action="users">
        <input type="hidden" name="action" value="doUserList">
            <tr>
                <td>${item.userName}</td>
                <td>${item.nickname}</td>
                <td>${item.password}</td>
                <td>${item.sex}</td>
            </tr>
        </form>
        </c:forEach>
    </c:if>


</table>

</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: SUN
  Date: 2017/3/20
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登陆</title>

    <link rel="stylesheet" type="text/css" href="Css/styles.css">
    <script src="js2/login.js"></script>

</head>
<body>


<div class="wrapper">

    <div class="container">
        <h1>网上便利店 后台管理</h1>
        <form class="form" action="api/users?action=login">
            <input type="hidden" name="action" value="login">
            <input type="text" name="userName">
            <input type="password" name="password"><br>
            <%--<button type="submit" id="login-button" onclick="window.location.href='index.html';"><strong>登陆</strong></button>--%>
            <button type="submit" id="login"><strong>登陆</strong></button>
            <%--<input type="button" id="login" class="login" value="登陆">--%>

        </form>
    </div>

    <ul class="bg-bubbles">
        <li></li>
        <li></li>

    </ul>

</div>

</body>
</html>

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
<form class="form" action="product">
    <input type="hidden" name="action" value="addProduct">
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>类别</th>
        <th>名称</th>
        <th>价格</th>
        <th>库存</th>
        <th>详情</th>
        <th>产品图片</th>
        <th>状态</th>
        <th>类别图片</th>
    </tr>
    </thead>

    <tr>
        <%--<input type="text" name="categorys">--%>
        <%--<input type="text" name="productName">--%>
        <th><input type="text" name="categorys"></th>
        <th><input type="text" name="productName"></th>
        <th><input type="text" name="price"></th>
        <th><input type="text" name="productNumber"></th>
        <th><input type="text" name="productDetail"><br></th>
        <th><input type="text" name="productImg"><br></th>
        <th><input type="text" name="productState"><br></th>
        <th><input type="text" name="categorysImg"><br></th>


    </tr>

    <tr>
        <td> <button type="submit">确定</button></td>
    </tr>

    <%--<c:if test="${empty productList }">--%>
        <%--<tr>--%>
            <%--<td colspan="5" align="center">暂无记录</td>--%>
        <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:if test="${not empty productList }">--%>
        <%--<c:forEach var="item" items="${productList }">--%>
            <%--<form class="form" action="product">--%>
                <%--<input type="hidden" name="action" value="toAddProduct">--%>
                <%--<tr>--%>
                    <%--<td>${item.productID}</td>--%>
                    <%--<td>${item.categorys}</td>--%>
                    <%--<td>${item.productName}</td>--%>
                    <%--<td>${item.price}</td>--%>
                    <%--<td>${item.productNumber}</td>--%>
                    <%--<td>${item.productDetail}</td>--%>
                    <%--<td>${item.productImg}</td>--%>
                    <%--<td>${item.productState}</td>--%>
                    <%--<td>${item.categorysImg}</td>--%>
                    <%--<td> <button type="submit">添加</button></td>--%>
                <%--</tr>--%>
            <%--</form>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>


</table>
    </form>

</body>
</html>


<%@ page import="java.lang.annotation.Target" %>
<%--
  Created by IntelliJ IDEA.
  User: SUN
  Date: 2017/3/20
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    application.setAttribute("contestPath", path);
//    pageContext.setAttribute("contextPath", path);
%>
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

    <%--<script src="../js2/jquery-1.8.3.min.js"></script>--%>
    <%--<script src="../js2/product.js"></script>--%>

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

<form class="form-inline definewidth m20" action="product" >
    <input type="hidden" name="action" value="productByLikeNamePC">
    <font color="#777777"><strong>请输入产品名称：</strong></font>
    <input type="text" name="productName" id="menuname"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<form class="form-inline definewidth m20" action="product" >
    <input type="hidden" name="action" value="toAddProduct">
    <button type="submit" class="btn btn-primary"> 添加产品</button>
</form>

<form class="form" action="#" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>产品ID</th>
        <th>类别</th>
        <th>名称</th>
        <th>价格</th>
        <th>库存</th>
        <th>详情</th>
        <th>产品图片</th>
        <th>状态</th>
        <th>类别图片</th>
        <th>操作</th>
    </tr>
    </thead>

    <c:if test="${empty productList }">
        <tr>
            <td colspan="5" align="center">暂无记录</td>
        </tr>
    </c:if>
    <c:if test="${not empty productList }">
        <c:forEach var="item" items="${productList }">

                <%--<input type="hidden" name="action" value="toAddProduct">--%>
                <tr>
                    <td>${item.productID}</td>
                    <td>${item.categorys}</td>
                    <td>${item.productName}</td>
                    <td>${item.price}</td>
                    <td>${item.productNumber}</td>
                    <td>${item.productDetail}</td>
                    <td>${item.productImg}</td>
                    <td>${item.productState}</td>
                    <td>${item.categorysImg}</td>
                    <td><a href="${contestPath}/api/product?action=toModifyProductByID&productID=${item.productID}">修改</a> </td>
                    <td><a href="${contestPath}/api/product?action=deleteProductByID&productID=${item.productID}">删除</a> </td>
                </tr>

        </c:forEach>
    </c:if>


</table>
</form>
</body>
</html>


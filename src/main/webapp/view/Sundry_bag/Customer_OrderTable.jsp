<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2020/1/1
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>顾客信息表</title>

    <%--CSS样式--%>
    <%@ include file="/include/link_css.jsp" %>
    <%--CSS样式--%>
</head>

<body>


<%--sidebar侧边栏--%>
<%@ include file="/include/sidebar.jsp" %>
<%--sidebar侧边栏--%>

<%--header头部--%>
<%@ include file="/include/header.jsp" %>
<%--header头部--%>

<%--sidebar侧边栏2--%>
<%@ include file="/include/sidebar2.jsp" %>
<%--sidebar侧边栏2--%>


<div class="content-wrap">
    <div class="main">
        <div class="container-fluid">

            <%--仪表板    --%>
            <div class="row">
                <div class="col-lg-8 p-0">
                    <div class="page-header">
                        <div class="page-title">
                            <h1>信息表格</h1>
                        </div>
                    </div>
                </div><!-- /# column -->
                <div class="col-lg-4 p-0">
                    <div class="page-header">
                        <div class="page-title">
                            <ol class="breadcrumb text-right">
                                <li><a href="#">信息表格</a></li>
                                <li class="active">Home</li>
                            </ol>
                        </div>
                    </div>
                </div><!-- /# column -->
            </div><!-- /# row -->
            <%--仪表板    --%>

            <div class="main-content">



                <div class="row">
                    <div class="col-lg-10">
                        <div class="card alert">
                            <div class="card-header">
                                <h4 class="mb">
                                    <a href="/project_jdbc/queryOrderByPage/1"><i class="ti-arrow-circle-left"></i> </a>
                                    顾客信息表
                                </h4>
                                <div class="card-header-right-icon">
                                    <ul>
                                        <li class="card-close" data-dismiss="alert"><i class="ti-close"></i></li>
                                        <li class="card-option drop-menu"><i class="ti-settings" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" role="link"></i>
                                            <ul class="card-option-dropdown dropdown-menu">
                                                <li><a href="/project_jdbc/queryOrderByPage/1"><i class="ti-pulse"></i>订单表</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-responsive table-hover ">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center">顾客ID</th>
                                        <th style="text-align: center">顾客名字</th>
                                        <th style="text-align: center">顾客性别</th>
                                        <th style="text-align: center">顾客手机号</th>
                                        <th style="text-align: center">顾客收获地址</th>
                                        <th style="text-align: center">头像（微信头像）</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:if test="${!empty customer}">
                                        <tr>
                                            <td style="text-align: center;" class="color-success">${customer.customerId}</td>
                                            <td style="text-align: center;" class="color-success">${customer.customerName}</td>
                                            <td style="text-align: center;" class="color-success">${customer.customerSex}</td>
                                            <td style="text-align: center;" class="color-success">${customer.customerPhone}</td>
                                            <td style="text-align: center;" class="color-success">${customer.customerAddress}</td>
                                            <td style="text-align: center;width: 150px;height: 150px" class="color-success"><img style="width: 100%;height: 100%;" src="${customer.customerPicture}"></td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div><!-- /# column -->
                </div><!-- /# row -->

            </div>
        </div><!-- /# container-fluid -->
    </div><!-- /# main -->
</div><!-- /# content wrap -->

<%--JS样式--%>
<%@ include file="/include/link_js.jsp" %>
<%--JS样式--%>


</body>
</html>

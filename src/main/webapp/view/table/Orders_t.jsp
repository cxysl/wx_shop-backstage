<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2020/1/2
  Time: 10:39
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

    <title>订单信息表</title>

    <%--CSS样式--%>
    <%@ include file="/include/link_css.jsp" %>
    <%--CSS样式--%>
    <script>
        function forword(url) {
            window.location.replace(url);
        }
    </script>
</head>

<body>


<%--sidebar侧边栏--%>
<%@ include file="/include/sidebar.jsp" %>
<%--sidebar侧边栏--%>

<%--header头部--%>
<%@ include file="/include/header.jsp" %>
<%--header头部--%>

<%--sidebar侧边栏--%>
<%@ include file="/include/sidebar2.jsp" %>
<%--sidebar侧边栏--%>


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
                </div>  <!-- /# column -->
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
                                <h4>订单信息表</h4>
                                <div class="card-header-right-icon">
                                    <ul>
                                        <li class="card-close" data-dismiss="alert"><i class="ti-close"></i></li>
                                        <li class="card-option drop-menu"><i class="ti-settings" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" role="link"></i>
                                            <ul class="card-option-dropdown dropdown-menu">
                                                <li><a href="/project_jdbc/queryOrderByPage/1"><i class="ti-pulse"></i>订单</a></li>
                                                <li><a href="/project_jdbc/queryODByPage/1"><i class="ti-pulse"></i>订单明细</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-body">
                                <%--class="table table-responsive table-bordered"--%>
                                    <%--class="table table-responsive table-hover "--%>
                                <table class="table table-responsive table-hover ">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center">订单ID</th>
                                        <th style="text-align: center">顾客名字</th>
                                        <th style="text-align: center">顾客ID</th>
                                        <th style="text-align: center">订单总金额</th>
                                        <th style="text-align: center">下单时间</th>
                                        <th style="text-align: center">订单状态</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach var="p" items="${page.arrys}">
                                        <tr>
                                            <td style="text-align: center">${p.orderId}</td>
                                            <td style="text-align: center" class="color-success">${p.customerName}</td>
                                            <td style="text-align: center" class="color-success">${p.customerId}</td>
                                            <td style="text-align: center" class="color-success">${p.orderPrice}</td>
                                            <td style="text-align: center" class="color-success">${p.orderDate}</td>
                                            <td style="text-align: left; padding-left: 80px;">
                                                <button type="button" onclick="forword('${pageContext.request.contextPath }/updateOrderState.do?orderId1=${p.orderId}')" class="btn btn-info btn-outline btn-xs">${p.orderState}</button>
                                                <c:if test="${p.orderState.equals('已支付')}"><%--如果值为“已支付”就显示下面物流图标--%>
                                                   <a href="${pageContext.request.contextPath }/queryLogisticsByPage/0?orderId=${p.orderId}"><i class="ti-direction-alt"></i></a>
                                                </c:if>
                                            </td>
                                            <td style="text-align: center">
                                                <button type="button" onclick="forword('${pageContext.request.contextPath }/queryODOneByPage/1?orderId=${p.orderId}')" class="btn btn-info btn-outline btn-xs">查看订单明细</button>
                                                <button type="button" onclick="forword('${pageContext.request.contextPath }/queryCustomerByPage/0?customerId=${p.customerId}')" class="btn  btn-info btn-outline btn-xs">查看顾客信息</button>
                                                <button class="btn btn-danger btn-xs" onclick="forword('delOrder.do?orderId=${p.orderId}')">删除<i class="fa fa-trash-o "></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div style="margin: 30px 0;padding: 0 300px;">
                                </div>
                                <a href="/project_jdbc/queryOrderByPage/${page.firstpage}">首页</a>
                                <a href="/project_jdbc/queryOrderByPage/${page.prePage}">上一页</a>
                                <c:forEach var="index" items="${page.indexs}">
                                    &nbsp;&nbsp;<a href="/project_jdbc/queryOrderByPage/${index}">${index}</a>&nbsp;&nbsp;
                                </c:forEach>
                                <a href="/project_jdbc/queryOrderByPage/${page.nextpage}">下一页</a>
                                <a href="/project_jdbc/queryOrderByPage/${page.lastpage}">尾页</a>

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

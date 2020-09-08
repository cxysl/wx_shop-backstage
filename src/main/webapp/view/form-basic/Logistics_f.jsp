<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2020/1/2
  Time: 18:41
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

    <title>更新物流信息</title>

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
                            <h1>表单操作</h1>
                        </div>
                    </div>
                </div><!-- /# column -->
                <div class="col-lg-4 p-0">
                    <div class="page-header">
                        <div class="page-title">
                            <ol class="breadcrumb text-right">
                                <li><a href="#">表单操作</a></li>
                                <li class="active">Home</li>
                            </ol>
                        </div>
                    </div>
                </div><!-- /# column -->
            </div><!-- /# row -->
            <%--仪表板    --%>

            <div class="main-content">

                <div class="row">
                    <div class="col-lg-6">
                        <div class="card alert">
                            <div class="card-header">
                                <%--<h4>修改订单信息</h4>--%>
                                <div class="card-header-right-icon">
                                    <ul>
                                        <li class="card-close" data-dismiss="alert"><i class="ti-close"></i></li>
                                        <li class="doc-link"><a href="#"><i class="ti-link"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="input-states">
                                    <%--有对象说明修改操作--%>
                                    <c:if test="${!empty log}">
                                        <input type="hidden" name="expressId" value="${log.expressId}">
                                        <h4 class="mb">
                                            <a href="/project_jdbc/queryLogisticsByPage/1"><i class="ti-arrow-circle-left"></i> </a>
                                            更新物流信息
                                        </h4>
                                        <form action="${pageContext.request.contextPath }/updateLogisticsNowAddress.do?countgoods=2&expressId=${log.expressId}" method="post" class="form-horizontal">

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">当前物流地点</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="当前物流地点" name="logisticsAddress" value="${log.logisticsAddress}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div style="text-align: center">
                                                <button type="reset" class="btn btn-warning m-b-10 m-l-5" >重置</button>
                                                <button type="submit" class="btn btn-info m-b-10 m-l-5" >提交</button>
                                            </div>
                                        </form>
                                    </c:if>

                                    <%--我要的--%>
                                </div>


                            </div>
                        </div>
                    </div>
                </div><!-- / -->

            </div>
        </div><!-- /# container-fluid -->
    </div><!-- /# main -->
</div><!-- /# content wrap -->

<%--JS样式--%>
<%@ include file="/include/link_js.jsp" %>
<%--JS样式--%>


</body>
</html>

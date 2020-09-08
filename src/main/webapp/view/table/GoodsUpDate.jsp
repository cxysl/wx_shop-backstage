<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2020/1/1
  Time: 12:45
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

    <title>SimDash : Admin Kit</title>

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
                    <div class="col-lg-8">
                        <div class="card alert">
                            <div class="card-header">
                                <h4>已上架商品信息表</h4>
                                <div class="card-header-right-icon">
                                    <ul>
                                        <li class="card-close" data-dismiss="alert"><i class="ti-close"></i></li>
                                        <li class="card-option drop-menu"><i class="ti-settings" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" role="link"></i>
                                            <ul class="card-option-dropdown dropdown-menu">
                                                <li><a href="/project_jdbc/queryProByPage/1"><i class="ti-pulse"></i> 商品完整信息表</a></li>
                                                <li><a href="/project_jdbc/queryUpProByPage/1"><i class="ti-pulse"></i> 商品上架表 </a></li>
                                                <li><a href="/project_jdbc/queryTypeByPage/1"><i class="ti-pulse"></i>商品分类信息表</a></li>
                                                <li><a href="/project_jdbc/queryPictureByPage/1"><i class="ti-pulse"></i>商品图片表</a></li>
                                                <li><a href="/project_jdbc/queryPurByPage/1"><i class="ti-pulse"></i>商品采购表</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-responsive table-hover ">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center">商品名称</th>
                                        <th style="text-align: center">商品主图片</th>
                                        <th style="text-align: center">采购价格(￥)</th>
                                        <th style="text-align: center">销售价格(￥)</th>
                                        <th style="text-align: center">库存量</th>
                                        <th style="text-align: center">上架状态</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="p" items="${page.arrys}">
                                        <tr>
                                            <td style="text-align: center" class="color-success">${p.goodsName}</td>
                                            <td style="text-align: center;width: 120px;height: 120px"><img src="${p.goodsPicture}" style="width:100%;height: 100%"></td>
                                            <td style="text-align: center" class="color-success">${p.purchasePrice}</td>
                                            <td style="text-align: center" class="color-success">${p.goodsPrice}</td>
                                            <td style="text-align: center" class="color-success">${p.goodsCount}</td>
                                            <td style="text-align: center"><button type="button" onclick="forword('${pageContext.request.contextPath }/Update_GoodsUpdates.do?type=2&goodsId3=${p.goodsId}')" class="btn btn-info btn-outline m-b-10 m-l-5">${p.goodsUpdates}</button></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div style="margin: 30px 0;padding: 0 220px;">
                                    <button class="btn btn-block btn-success" onclick="forword('../view/form-basic/Goods_Form.jsp')">采购新商品<i class="fa fa-check"></i></button>
                                </div>
                                <a href="/project_jdbc/queryUpProByPage/${page.firstpage}">首页</a>
                                <a href="/project_jdbc/queryUpProByPage/${page.prePage}">上一页</a>
                                <c:forEach var="index" items="${page.indexs}">
                                    &nbsp;&nbsp;<a href="/project_jdbc/queryUpProByPage/${index}">${index}</a>&nbsp;&nbsp;
                                </c:forEach>
                                <a href="/project_jdbc/queryUpProByPage/${page.nextpage}">下一页</a>
                                <a href="/project_jdbc/queryUpProByPage/${page.lastpage}">尾页</a>

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

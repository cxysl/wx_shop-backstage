<%--
  Created by IntelliJ IDEA.
  User: 程世林
  Date: 2019/12/31
  Time: 13:59
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

    <title>商品信息</title>

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
                                    <%--没有对象说明添加操作--%>
                                    <c:if test="${empty goods}">
                                        <h4 class="mb">
                                            <a href="/project_jdbc/queryProByPage/1"><i class="ti-arrow-circle-left"></i> </a>
                                            商品信息添加
                                        </h4>
                                        <form action="${pageContext.request.contextPath }/addGoods.do" method="post" class="form-horizontal">

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">商品名称</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="商品名称" name="goodsName" value="${goods.goodsName}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">商品主图片地址</label>
                                                    <div class="col-sm-9">
                                                        <%--<input class="col-sm-3" type="file" name="ufile"/>--%>
                                                        <%--<img src="ufile">--%>
                                                        <input type="text" class="form-control" placeholder="商品主图片" name="goodsPicture" value="${goods.goodsPicture}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">采购价格</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="采购价格" name="purchasePrice" value="${goods.purchasePrice}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">销售价格</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="销售价格" name="goodsPrice" value="${goods.goodsPrice}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">库存量</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="库存量" name="goodsCount" value="${goods.goodsCount}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">商品描述信息</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="商品描述信息" name="goodsDescribe" value="${goods.goodsDescribe}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">标签</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="标签1" name="goodLabel1" value="${goods.goodLabel1}">
                                                        <input type="text" class="form-control" placeholder="标签2" name="goodLabel2" value="${goods.goodLabel2}">
                                                        <input type="text" class="form-control" placeholder="标签3" name="goodLabel3" value="${goods.goodLabel3}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">商品上(Y/y)下架</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="商品上(Y/y)下架" name="goodsUpdates" value="${goods.goodsUpdates}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group has-success">
                                                <div class="row">
                                                    <label class="col-sm-3 control-label">商品类别Id</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" placeholder="商品类别Id" name="typeId" value="${goods.typeId}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div style="text-align: center">
                                                <button type="reset" class="btn btn-warning m-b-10 m-l-5" >重置</button>
                                                <button type="submit" class="btn btn-info m-b-10 m-l-5" >提交</button>
                                            </div>

                                        </form>
                                    </c:if>

                                    <%--有对象说明修改操作--%>
                                        <c:if test="${!empty goods}">
                                            <input type="hidden" name="goodsId" value="${goods.goodsId}">
                                            <h4 class="mb">
                                                <a href="/project_jdbc/queryProByPage/1"><i class="ti-arrow-circle-left"></i> </a>
                                                商品信息修改
                                            </h4>
                                            <form action="${pageContext.request.contextPath }/updateGoods.do?countgoods=2&goodsId=${goods.goodsId}" method="post" class="form-horizontal">

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">商品名称</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="商品名称" name="goodsName" value="${goods.goodsName}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">商品主图片地址</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="商品主图片地址" name="goodsPicture" value="${goods.goodsPicture}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">销售价格</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="销售价格" name="goodsPrice" value="${goods.goodsPrice}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">库存量</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="库存量" name="goodsCount" value="${goods.goodsCount}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">商品描述信息</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="商品描述信息" name="goodsDescribe" value="${goods.goodsDescribe}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">标签</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="标签1" name="goodLabel1" value="${goods.goodLabel1}">
                                                            <input type="text" class="form-control" placeholder="标签2" name="goodLabel2" value="${goods.goodLabel2}">
                                                            <input type="text" class="form-control" placeholder="标签3" name="goodLabel3" value="${goods.goodLabel3}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">商品上(Y/y)下架</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="商品上(Y/y)下架" name="goodsUpdates" value="${goods.goodsUpdates}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group has-success">
                                                    <div class="row">
                                                        <label class="col-sm-3 control-label">商品类别Id</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" placeholder="商品类别Id" name="typeId" value="${goods.typeId}">
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
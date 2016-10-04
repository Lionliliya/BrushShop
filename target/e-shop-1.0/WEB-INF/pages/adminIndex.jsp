<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Liliya Yalovchenko">


    <title>
        BeautyTree
    </title>

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/owl.carousel.css" rel="stylesheet">
    <link href="/resources/css/owl.theme.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="/resources/css/style.violet.css" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="/resources/css/custom.css" rel="stylesheet">

    <script src="/resources/js/respond.min.js"></script>

    <link rel="shortcut icon" href="/resources/favicon.png">
    <script type="text/javascript">
        function AlertIt(id) {
            var answer = confirm("Вы собираетесь удалить заказ № " + id + ". Нажмите OK что бы продолжить.")
            if (answer)
                window.location = "http://localhost:8080/admin/order/remove/" + id + "";
        }
    </script>

</head>

<body>

<!-- *** TOPBAR ***
_________________________________________________________ -->
<div id="top">
    <div class="container">

    </div>
</div>

<!-- *** TOP BAR END *** -->

<!-- *** NAVBAR ***
 _________________________________________________________ -->

<div class="navbar navbar-default yamm" role="navigation" id="navbar">
    <div class="container">
        <div class="navbar-header">

            <a class="navbar-brand home" href="/admin/" data-animate-hover="bounce">
                <img src="/resources/img/logo.png" alt="Obaju logo" class="hidden-xs">
                <img src="/resources/img/logo-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">BeautyTree Admin panel</span>
            </a>

            <div class="navbar-buttons">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                    <span class="sr-only">Toggle navigation</span>
                    <i class="fa fa-align-justify"></i>
                </button>
            </div>

        </div>
        <!--/.navbar-header -->

        <div class="navbar-collapse collapse" id="navigation">

            <ul class="nav navbar-nav navbar-left">
                <li class="active">
                    <a href="/admin/">Главная</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/catalog">Каталог продукции</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/client">Клиенты</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/feedback">Отзывы</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/post">Статьи и новости</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/parameter">Настройки</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/logout">Выйти</a>
                </li>
            </ul>

        </div>
        <!--/.nav-collapse -->
    </div>
    <!-- /.container -->
</div>
<!-- /#navbar -->

<!-- *** NAVBAR END *** -->

<div id="all">

    <div id="content">
        <div class="container">

            <div class="col-md-10 col-md-offset-1" id="customer-orders">
                <div class="row">
                    <div class="box">
                        <h1>Все заказы</h1>

                        <div class="row">

                            <div class="col-sm-12">
                                <div class="products-sort-by">

                                    <div class="row">
                                        <div class="col-xs-3">
                                            <a href="/admin/order/sort/dateUp"><i class="fa fa-arrow-up"></i>Сначала
                                                новые</a>
                                        </div>
                                        <div class="col-xs-3">
                                            <a href="/admin/order/sort/dateDown"><i class="fa fa-arrow-down"></i>
                                                Сначала старые</a>
                                        </div>
                                        <div class="col-xs-3">
                                            <a href="/admin/order/sort/amount"><i class="fa fa-arrow-down"></i> Сумма
                                                заказа</a>
                                        </div>
                                        <div class="col-xs-3">
                                            <a href="#add"><i class="fa fa-plus-circle"></i> Создать новый заказ</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <hr>
                        <div class="row">
                            <c:if test="${orders.size() eq 0}">
                                <div class="col-md-12">
                                    <article class="art-head"><h2>У вас нет заказов</h2></article>
                                </div>
                            </c:if>
                            <c:if test="${orders.size() gt 0}">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>№</th>
                                            <th>Дата</th>
                                            <th>Сумма</th>
                                            <th>Клиент</th>
                                            <th>Статус</th>
                                            <th>Доставка</th>
                                            <th>Действие</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach items="${orders}" var="order">
                                            <tr>
                                                <td>10${order.id}</td>
                                                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                                                    value="${order.date}"/></td>
                                                <td>₴${order.totalAmount}</td>
                                                <td>${order.client.firstName}</td>
                                                <td><span class="label label-info">статус</span></td>
                                                <td>${order.delivery}</td>
                                                <td>
                                                    <a href="/admin/order/edit/${order.id}"
                                                       class="btn btn-primary btn-sm">Редактировать</a>
                                                    <a href="javascript:AlertIt(${order.id});"
                                                       class="btn btn-primary btn-sm">Удалить</a>
                                                    <a href="/admin/order/${order.id}" class="btn btn-primary btn-sm">Просмотреть</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                        </div>


                    </div>
                </div>
                <div class="row">
                    <div class="box" id="add">
                    <h3>Создать заказ</h3>

                    <form role="form" action="/admin/order/add" method="post">

                        <div class="form-group">
                            <label for="date">Дата в формате dd.mm.yyyy hh:mm Напр.: 22.05.2016 17:46</label>
                            <input type="text" id="date" class="form-control" name="date" required/>
                        </div>

                        <div class="form-group">
                            <label for="del">Доставка</label>
                            <select class="form-control" id="del" name="delivery" required>
                                <option value="Самовывоз">Самовывоз</option>
                                <option value="Новая почта">Новая почта</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="message">Коментарии к заказу</label>
                            <textarea id="message" class="form-control" name="comments"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="clientId">Выбирите клиента</label>
                            <select class="form-control" id="clientId" name="client" required>
                                <c:forEach items="${clients}" var="client">
                                    <option value="${client.id}">${client.firstName} ${client.email}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="product">Выбирите товары</label>
                            <select class="form-control" id="product" name="product" multiple required>
                                <c:forEach items="${products}" var="product">
                                    <option value="${product.id}">${product.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="quant">Количество</label>
                            <input type="number" id="quant" class="form-control" name="quantity" required="">
                        </div>

                        <div class="form-group">
                            <label for="cur">Валюта</label>
                            <select class="form-control" id="cur" name="currency" required>
                                <option value="UAH">UAH</option>
                                <option value="USD">USD</option>
                                <option value="EUR">EUR</option>
                            </select>
                        </div>

                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i>
                                    Создать заказ
                                </button>
                            </div>
                        </div>

                    </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->
    </div>

    <!-- /#content -->

</div>
<!-- /#all -->

<!-- *** SCRIPTS TO INCLUDE ***
_________________________________________________________ -->
<script src="/resources/js/jquery-1.11.0.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.cookie.js"></script>
<script src="/resources/js/waypoints.min.js"></script>
<script src="/resources/js/modernizr.js"></script>
<script src="/resources/js/bootstrap-hover-dropdown.js"></script>
<script src="/resources/js/owl.carousel.min.js"></script>
<script src="/resources/js/front.js"></script>

</body>
</html>
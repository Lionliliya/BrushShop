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

            <div class="col-md-8 col-md-offset-2" id="customer-order">
                <div class="row">
                    <div class="box">

                        <form role="form" action="/admin/order/save/${order.id}" method="post">
                            <div class="form-group">
                                <label for="menuId">№ заказа</label>
                                <input type="text" id="menuId" class="form-control" name="id" value="${order.id}"
                                       readonly>
                            </div>

                            <div class="form-group">
                                <label for="orderDate">Дата в формате dd.MM.yyyy, например: 02.06.2016</label>
                                <input type="text" id="orderDate" class="form-control" name="date"
                                       value="<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${order.date}"/>">
                            </div>

                            <div class="form-group">
                                <label for="delivery">Доставка</label>
                                <input type="text" id="delivery" class="form-control" name="delivery"
                                       value="${order.delivery}">
                            </div>

                            <div class="form-group">
                                <label for="comments">Коментарии</label>
                                <textarea id="comments" class="form-control" name="comments"
                                          value="${order.comments}"></textarea>
                            </div>

                            <div class="form-group">
                                <label for="amount">Общая сумма</label>
                                <input type="number" id="amount" class="form-control" name="totalAmount"
                                       value="${order.totalAmount}">
                            </div>

                            <button type="submit" class="btn btn-success">Сохранить</button>

                        </form>

                        <hr>
                        <form role="form" action="/admin/order/removeProduct/${order.id}" method="post">

                            <label for="table1">
                                Список товаров для заказа. Вибрете товар, что бы удалить его, и нажите кнопку "Удалить"
                            </label>

                            <div class="table-responsive" id="table1">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th colspan="2">Товар</th>
                                        <th>Количество</th>
                                        <th>Цена</th>
                                        <th>Скидка</th>
                                        <th>Всего</th>
                                        <th>Удалить</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${productList}" var="product">
                                        <tr>
                                            <td>
                                                <a href="/product/${product.product_id.id}">
                                                    <img src="/resources/${product.product_id.image1}"
                                                         alt="${product.name}">
                                                </a>
                                            </td>
                                            <td><a href="/product/${product.product_id.id}">${product.name}</a>
                                            <td>${product.quantity}</td>
                                            <td>₴${product.price}</td>
                                            <td>₴0.00</td>
                                            <td>₴${product.price * product.quantity}</td>
                                            <td><input type="radio" name="delete" value="${product.product_In_Cart_id}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->
                            </div>
                            <button type="submit" class="btn btn-success">Удалить</button>
                        </form>

                    </div>
                </div>

                <div class="row">
                    <div class="box">
                        <h3>Добавть товар к заказу</h3>
                        <form method="post" action="/admin/order/addProduct/${order.id}">
                        <c:if test="${fn:length(products) gt 0}">
                        <div class="table-responsive">
                            <table class="table">

                                <thead>
                                <tr>
                                    <th colspan="2">Товар</th>
                                    <th>Кол-во</th>
                                    <th>Добавить</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td>
                                            <a href="/product/${product.id}">
                                                <img src="/resources/${product.image2}" alt="${product.name}">
                                            </a>
                                        </td>
                                        <td>
                                            <a href="/product/${product.id}">${product.name}</a>
                                        </td>
                                        <td>
                                            <input type="number" name="quantity" class="form-control">
                                        </td>
                                        <td>
                                            <input type="radio" name="product" value="${product.id}">
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            </c:if>
                            <c:if test="${fn:length(products) eq 0}">
                                <p>На складе нет ни одного товара...</p>
                            </c:if>
                        </div>

                            <button type="submit" class="btn btn-success">Сохранить</button>

                        </form>
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
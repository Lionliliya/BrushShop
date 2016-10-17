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
            var answer = confirm("Вы собираетесь удалить отзыв № " + id + ". Нажмите OK что бы продолжить.")
            if (answer)
                window.location = "http://localhost:8080/admin/feedback/remove/" + id + "";
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
                <li class="yamm-fw">
                    <a href="/admin/">Главная</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/catalog">Каталог продукции</a>
                </li>
                <li class="yamm-fw">
                    <a href="/admin/client">Клиенты</a>
                </li>
                <li class="active">
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

            <div class="col-md-10 col-md-offset-1" id="customer-order">
                <div class="box">
                    <h1 class="text-center">Все отзывы</h1>

                    <div class="row">

                        <div class="col-sm-12">
                            <div class="products-sort-by">

                                <div class="row">
                                    <div class="col-xs-3">
                                        <a href="/admin/feedback/sort/dateUp"><i class="fa fa-arrow-up"></i></a>
                                        <a href="/admin/feedback/sort/dateDown"><i class="fa fa-arrow-down"></i></a>
                                        По дате
                                    </div>

                                    <div class="col-xs-3">
                                        <a href="/admin/feedback/sort/rateDown"><i class="fa fa-arrow-down"></i></a>
                                        <a href="/admin/feedback/sort/rateUp"><i class="fa fa-arrow-up"></i></a>
                                        По рейтингу
                                    </div>
                                    <div class="col-xs-6">
                                       <p class="text-right" style="color: purple;">
                                           <em>
                                            Чтобы добавить новый отзыв, перейдите на страницу клиентов,
                                            выбирите нужного клиента и создайте отзыв на его странице
                                           </em>
                                       </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>

                    <c:set var="feedbacks" value="${feedbacks}"/>
                    <c:if test="${fn:length(feedbacks) eq 0}">
                        <div class="col-md-12">
                            <article class="art-head"><h2>Нет отзывов...</h2></article>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(feedbacks) gt 0}">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>№</th>
                                    <th colspan="2">Товар</th>
                                    <th>Дата</th>
                                    <th>Клиент</th>
                                    <th>Оценка</th>
                                    <th>Отзыв</th>
                                    <th>Действя</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${feedbacks}" var="feedback">
                                    <tr>
                                        <td>${feedback.id}</td>
                                        <td>
                                            <a href="/product/${feedback.product.id}">
                                                <img src="/resources/${feedback.product.image1}"
                                                     alt="${feedback.product.name}">
                                            </a>
                                        </td>
                                        <td><a href="/product/${feedback.product.id}">${feedback.product.name}</a></td>
                                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                                            value="${feedback.date}"/></td>
                                        <td><a href="/admin/client/${feedback.client.id}">${feedback.client.firstName}
                                        </td>
                                        <td>${feedback.evaluation}</td>
                                        <td>${feedback.feedback}</td>
                                        <td>
                                            <p><a href="/admin/feedback/edit/${feedback.id}"
                                                  class="btn btn-primary btn-sm"><i class="fa fa-pencil-square"></i> Редактировать</a></p>

                                            <p><a href="javascript:AlertIt(${feedback.id});"
                                                  class="btn btn-danger  btn-sm"><i class="fa fa-scissors"></i> Удалить</a></p>
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
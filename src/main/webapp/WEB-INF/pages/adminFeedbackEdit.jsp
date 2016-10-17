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
                    <a href="/admin/article">Статьи и новости</a>
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
                <div class="box">

                    <form role="form" action="/admin/feedback/save/${feedback.id}" method="post">
                        <div class="form-group">
                            <label for="feedbackId">№ Отзыва</label>
                            <input type="text" id="feedbackId" class="form-control" name="id" value="${feedback.id}"
                                   readonly>
                        </div>

                        <div class="form-group">
                            <label for="productName">Товар</label>
                            <input type="text" id="productName" class="form-control" name="product"
                                   value="${feedback.product.name}" readonly/>
                        </div>

                        <div class="form-group">
                            <label for="date">Дата в формате 22.08.2016 21:16</label>
                            <input type="text" id="date" class="form-control" name="date" value="<fmt:formatDate type="both"
                      dateStyle="short" timeStyle="short" value="${feedback.date}"/>" required>
                        </div>

                        <div class="form-group">
                            <label for="client">Клиент</label>
                            <input type="text" id="client" class="form-control" name="client"
                                   value="${feedback.client.firstName}" readonly>
                        </div>

                        <div class="form-group">
                            <label for="rate">Оценка</label>
                            <input type="number" id="rate" class="form-control" name="evaluation"
                                   value="${feedback.evaluation}">
                        </div>

                        <div class="form-group">
                            <label for="feedback">Отзыв</label>
                            <textarea id="feedback" class="form-control" name="feedback">
                                ${feedback.feedback}
                            </textarea>
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
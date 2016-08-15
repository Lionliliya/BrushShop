<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${product.metaDescription}">
    <meta name="author" content="Liliya Yalovchenko">
    <meta name="keywords" content="${product.metaKeyWords}">

    <title>
        BeautyTree | ${product.metaTitle}
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
            <div class="col-md-6 offer" data-animate="fadeInDown">
                <a href="#" class="btn btn-success btn-sm" style="background-color: #f1e1ef; color: #777777; border-color: #f1e1ef;" data-animate-hover="shake">Предложение дня</a>
                <a href="#">Получи скидку 20% от заказа на сумму больше 1000 грн</a>
            </div>
            <div class="col-md-6" data-animate="fadeInDown">
                <ul class="menu">
                    <li><a href="#" data-toggle="modal" data-target="#login-modal">Войти</a>
                    </li>
                    <li><a href="#">Регистрация</a>
                    </li>
                    <li><a href="/contacts">Контакты</a>
                    </li>

                </ul>
            </div>
        </div>
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
            <div class="modal-dialog modal-sm">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="Login">Вход пользователя</h4>
                    </div>
                    <div class="modal-body">
                        <form action="#" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" id="email-modal" placeholder="Эл. адресс">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password-modal" placeholder="Пароль">
                            </div>

                            <p class="text-center">
                                <button class="btn btn-primary"><i class="fa fa-sign-in"></i> Войти</button>
                            </p>

                        </form>

                        <p class="text-center text-muted">Контакты</p>
                        <p class="text-center text-muted"><a href="register.html"><strong>Зарегистрируйся сейчас!</strong></a>
                            Легко и  за 1&nbsp;минуту ты получишь доступ к дополнительным скидкам и особым условиям!
                        </p>

                    </div>
                </div>
            </div>
        </div>
</div>

    <!-- *** TOP BAR END *** -->

    <!-- *** NAVBAR ***
 _________________________________________________________ -->

    <div class="navbar navbar-default yamm" role="navigation" id="navbar">
        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand home" href="/" data-animate-hover="bounce">
                    <img src="/resources/img/logo.png" alt="Obaju logo" class="hidden-xs">
                    <img src="/resources/img/logo-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">BeautyTree - go to homepage</span>
                </a>
                <div class="navbar-buttons">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-align-justify"></i>
                    </button>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                    <a class="btn btn-default navbar-toggle" href="/cart">
                        <i class="fa fa-shopping-cart"></i>  <span class="hidden-xs">3 items in cart</span>
                    </a>
                </div>
            </div>
            <!--/.navbar-header -->

            <div class="navbar-collapse collapse" id="navigation">

                <ul class="nav navbar-nav navbar-left">
                    <li class="active">
                        <a href="/">Главная</a>
                    </li>
                    <li class="yamm-fw">
                        <a href="/news">Советы и новости</a>
                    </li>
                    <li class="dropdown yamm-fw">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Каталог<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="yamm-content">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h5>Китсти</h5>
                                            <ul>
                                                <c:forEach items="${categories}" var="category">
                                                    <li><a href="/catalog/${category.id}">${category.name}</a></li>
                                                </c:forEach>

                                            </ul>
                                        </div>
                                        <div class="col-sm-3">
                                            <h5>Бренды</h5>
                                            <ul>
                                                <li><a href="#">Real Techniques</a>
                                                </li>
                                                <li><a href="#">Eco Tools</a>
                                                </li>
                                                <li><a href="#">JAF</a>
                                                </li>
                                                <li><a href="#">MSQ Professional</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.yamm-content -->
                            </li>
                        </ul>
                    </li>

                </ul>

            </div>
            <!--/.nav-collapse -->

            <div class="navbar-buttons">

                <div class="navbar-collapse collapse right" id="basket-overview">
                    <a href="/cart" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm">${cartSize} ед. в корзине</span></a>
                </div>
                <!--/.nav-collapse -->

                <div class="navbar-collapse collapse right" id="search-not-mobile">
                    <button type="button" class="btn navbar-btn btn-primary" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                </div>

            </div>

            <div class="collapse clearfix" id="search">

                <form class="navbar-form" role="search" method="post" action="/search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Поиск">
                        <span class="input-group-btn">

			<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>

		    </span>
                    </div>
                </form>

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

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="/">Главная</a>
                        </li>

                        <li><a href="/category/${product.productCategory.name}">${product.productCategory.name}</a>
                        </li>
                        <li>${product.name}</li>
                    </ul>

                </div>

                <div class="col-md-3">

                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Категории</h3>
                        </div>

                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked category-menu">
                                <c:set var="curCategory" scope="page" value="${product.productCategory}"/>
                                <c:forEach items="${categories}" var="category">
                                    <c:choose>
                                        <c:when test="${category.name eq curCategory.name}">
                                            <li class="active">
                                                <a href="/catalog/${category.id}">${category.name}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <a href="/catalog/${category.id}">${category.name}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Бренды <a class="btn btn-xs btn-danger pull-right" href="#"><i class="fa fa-times-circle"></i> Clear</a></h3>
                        </div>

                        <div class="panel-body">
                            <form>
                                <div class="form-group">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">Real Techniques
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">JAF
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">Eco Tools
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">MSQ Professional
                                        </label>
                                    </div>
                                </div>

                                <button class="btn btn-default btn-sm btn-primary"><i class="fa fa-pencil"></i> Применить</button>

                            </form>

                        </div>
                    </div>

                    <!-- *** MENUS AND FILTERS END *** -->

                    <div class="banner">
                        <a href="#">
                            <img src="/resources/img/banner-2.png" alt="Распродажа Киев" class="img-responsive">
                        </a>
                    </div>
                </div>

                <div class="col-md-9">

                    <div class="row" id="productMain">
                        <div class="col-sm-6">
                            <div id="mainImage">
                                <img src="/resources/${product.image1}" alt="${product.name}" class="img-responsive">
                            </div>


                        </div>
                        <div class="col-sm-6">
                            <div class="box">
                                <h1 class="text-center">${product.name}</h1>
                                <p class="goToDescription"><a href="#details" class="scroll-to">Перейти к детальному описанию товара</a>
                                </p>
                                <p class="price">${product.price} грн</p>
                                <form action="/cart" method="post">
                                    <input type=hidden name="id" value="${product.id}">
                                    <input type=hidden name="productCategory" value="${product.productCategory.name}">
                                    <input type=hidden name="smallimage" value="${product.smallimage}">
                                    <input type=hidden name="name" value="${product.name}">
                                    <input type=hidden name="price" value="${product.price}">
                                    <input type=hidden name="currency" value="${product.currency}">
                                <p class="text-center buttons">
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-shopping-cart"></i>Добавить в корзину</button>
                                </p>
                                </form>


                            </div>

                            <div class="row" id="thumbs">
                                <div class="col-xs-4">
                                    <a href="/resources/${product.image2}" class="thumb">
                                        <img src="/resources/${product.image2}" alt="${product.name}" class="img-responsive">
                                    </a>
                                </div>
                                <div class="col-xs-4">
                                    <a href="/resources/${product.image3}" class="thumb">
                                        <img src="/resources/${product.image3}" alt="${product.name}" class="img-responsive">
                                    </a>
                                </div>
                                <div class="col-xs-4">
                                    <a href="/resources/${product.image4}" class="thumb">
                                        <img src="/resources/${product.image4}" alt="${product.name}" class="img-responsive">
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="box" id="details">
                        <p>
                            <h4>Полное описание</h4>
                            <p>${product.description}</p>


                            <hr>
                            <div class="social">
                                <h4>Поделиться с друзьями</h4>
                                <p>
                                    <a href="#" class="external facebook" data-animate-hover="pulse"><i class="fa fa-facebook"></i></a>
                                    <a href="#" class="email" data-animate-hover="pulse"><i class="fa fa-envelope"></i></a>
                                </p>
                            </div>
                    </div>


                    </div><!-- /.col-md-9 -->
                </div>
            <!-- /.container -->
        </div>

        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
    <div id="footer" data-animate="fadeInUp">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <h4>Навигация</h4>

                    <ul>
                        <li><a href="/about">О нас</a>
                        </li>

                        <li><a href="/contacts">Связь с нами</a>
                        </li>
                    </ul>

                    <hr>

                    <h4>Для пользователя</h4>

                    <ul>
                        <li><a href="#" data-toggle="modal" data-target="#login-modal">Войти</a>
                        </li>
                        <li><a href="#">Регистрация</a>
                        </li>
                    </ul>

                    <hr class="hidden-md hidden-lg hidden-sm">

                </div>
                <!-- /.col-md-3 -->

                <div class="col-md-3 col-sm-6">

                    <h4>Категории</h4>

                    <ul>
                        <c:forEach items="${categories}" var="category">
                            <li><a href="/catalog/${category.id}">${category.name}</a></li>
                        </c:forEach>
                    </ul>



                </div>
                <!-- /.col-md-3 -->

                <div class="col-md-3 col-sm-6">

                    <h4>Где мы находимся</h4>

                    <p><strong>BeautyTree</strong>
                        <br>Тычины Павла проп-т
                        <br>Левый берег
                        <br>Березняки
                        <br>Киев
                        <br>
                        <strong>Украина</strong>
                    </p>

                    <a href="/contacts">Контакты</a>

                    <hr class="hidden-md hidden-lg">

                </div>
                <!-- /.col-md-3 -->



                <div class="col-md-3 col-sm-6">

                    <h4>Stay in touch</h4>

                    <p class="social">
                        <a href="#" class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>

                        <a href="#" class="instagram external" data-animate-hover="shake"><i class="fa fa-instagram"></i></a>

                        <a href="#" class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
                    </p>


                </div>
                <!-- /.col-md-3 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /#footer -->

        <!-- *** FOOTER END *** -->


    <div id="copyright">
        <div class="container">
            <div class="col-md-6">
                <p class="pull-left">© 2016 BeautyTree</p>

            </div>
            <div class="col-md-6">
                <p class="pull-right">Design by <a href="http://bootstrapious.com/e-commerce-templates">Bootstrapious</a> with support from <a href="https://kakusei.cz">Kakusei</a>
                    <!-- Not removing these links is part of the licence conditions of the template. Thanks for understanding :) -->
                </p>
            </div>
        </div>
    </div>
        <!-- *** COPYRIGHT END *** -->

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
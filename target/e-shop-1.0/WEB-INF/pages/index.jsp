<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Beauty Tree | Кисти для макияжа, качественно и недорого</title>
    <meta charset="utf-8">
    <meta name="description" content="Качественные кисти для макияжа купить в Киеве недорого Можно! У нас вы найдете
    красивые и стильные кисти, аксессуары по уходу за ними и многое другое.">
    <meta name="keywords" content="макияж, кисти, купить, недорого">
    <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
    <meta lang="ru">

    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/owl.carousel.css" rel="stylesheet">
    <link href="/resources/css/owl.theme.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="/resources/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

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
            <a href="#" class="btn btn-success btn-sm" data-animate-hover="shake">Предложение дня</a>  <a href="#">Получи скидку 20% от заказа на сумму больше 1000 грн</a>
        </div>
        <div class="col-md-6" data-animate="fadeInDown">
            <ul class="menu">
                <li><a href="#" data-toggle="modal" data-target="#login-modal">Войти</a>
                </li>
                <li><a href="#">Регистрация</a>
                </li>
                <li><a href="/contact">Контакты</a>
                </li>
                <li><a href="/cart">Корзина</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-sm">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="Login">Вход в личный кабинет</h4>
                </div>
                <div class="modal-body">
                    <form action="customer-orders.html" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" id="email-modal" placeholder="Эл. адрес">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password-modal" placeholder="Пароль">
                        </div>

                        <p class="text-center">
                            <button class="btn btn-primary"><i class="fa fa-sign-in"></i> Войти</button>
                        </p>

                    </form>

                    <p class="text-center text-muted">Еще не зарегистрированы?</p>
                    <p class="text-center text-muted"><a href="#"><strong>Зарегистрироваться</strong></a> Легко и  за 1&nbsp;минуту ты получишь доступ к дополнительным скидкам и особым условиям!</p>

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

            <a class="navbar-brand home" href="home.jsp" data-animate-hover="bounce">
                <img src="/resources/img/logo.png" alt="Beauty Tree logo" class="hidden-xs">
                <img src="/resources/img/logo-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">Obaju - go to homepage</span>
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
                    <i class="fa fa-shopping-cart"></i>  <span class="hidden-xs"> ${cartSize} товаров в корзине</span>
                </a>
            </div>
        </div>
        <!--/.navbar-header -->

        <div class="navbar-collapse collapse" id="navigation">

            <ul class="nav navbar-nav navbar-left">
                <li class="active"><a href="/home">Главная</a></li>
                <li class="yamm-fw"><a href="/home">Советы и новости</a></li>
                <li class="dropdown yamm-fw">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Каталог <b class="caret"></b></a>
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


                                </div>
                            </div>
                            <!-- /.yamm-content -->
                        </li>
                    </ul>
                </li>
                <li class="yamm-fw"><a href="/homes">Доставка и оплата</a></li>

            </ul>

        </div>
        <!--/.nav-collapse -->

        <div class="navbar-buttons">

            <div class="navbar-collapse collapse right" id="basket-overview">
                <a href="/cart" class="btn btn-primary navbar-btn"><i class="fa fa-shopping-cart"></i><span class="hidden-sm">${cartSize} товаров в корзине</span></a>
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

            <form class="navbar-form" action="/search" method="post" role="search">
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
                <div id="main-slider">
                    <div class="item">
                        <img src="/resources/img/main-slider1.jpg" alt="" class="img-responsive">
                    </div>
                    <div class="item">
                        <img class="img-responsive" src="/resources/img/main-slider2.jpg" alt="">
                    </div>
                    <div class="item">
                        <img class="img-responsive" src="/resources/img/main-slider3.jpg" alt="">
                    </div>
                    <div class="item">
                        <img class="img-responsive" src="/resources/img/main-slider4.jpg" alt="">
                    </div>
                </div>
                <!-- /#main-slider -->
            </div>
        </div>

        <!-- *** ADVANTAGES HOMEPAGE ***
_________________________________________________________ -->
        <div id="advantages">

            <div class="container">
                <div class="same-height-row">
                    <div class="col-sm-4">
                        <div class="box same-height clickable">
                            <div class="icon"><i class="fa fa-heart"></i>
                            </div>

                            <h3><a href="/packing">Мы любим наших клиентов</a></h3>
                            <p>Наш товар мы доставляем в прелестных пакетиках, а по Вашему желанию мы
                                сделаем для вас стильную упаковку.</p>
                        </div>
                    </div>

                    <div class="col-sm-4">
                        <div class="box same-height clickable">
                            <div class="icon"><i class="fa fa-tags"></i>
                            </div>

                            <h3><a href="/catalog">Лучшие цены</a></h3>
                            <p>Самые демократичные цены на люксовые мировые бренды и
                                на товары доступных производителей</p>
                        </div>
                    </div>

                    <div class="col-sm-4">
                        <div class="box same-height clickable">
                            <div class="icon"><i class="fa fa-thumbs-up"></i>
                            </div>

                            <h3><a href="#">Гарантированное 100% удовлетворение клиентов </a></h3>
                            <p>Бесплатный возврат в течени 14 дней</p>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

        </div>
        <!-- /#advantages -->

        <!-- *** ADVANTAGES END *** -->


        <!-- *** GET INSPIRED ***
_________________________________________________________ -->
        <div class="container" data-animate="fadeInUpBig">
            <div class="col-md-12">
                <div class="box slideshow">
                    <h3>Получи вдохновение</h3>
                    <p class="lead">Получи вдохновение для лучших образов</p>
                    <div id="get-inspired" class="owl-carousel owl-theme">
                        <div class="item">
                            <a href="#">
                                <img src="/resources/img/getinspired1.jpg" alt="Кисти для макияжа Киев" class="img-responsive">
                            </a>
                        </div>
                        <div class="item">
                            <a href="#">
                                <img src="/resources/img/getinspired2.jpg" alt="Кисти для макияжа Киев" class="img-responsive">
                            </a>
                        </div>
                        <div class="item">
                            <a href="#">
                                <img src="img/getinspired3.jpg" alt="Кисти для макияжа Киев" class="img-responsive">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- *** GET INSPIRED END *** -->

        <!-- *** BLOG HOMEPAGE ***
_________________________________________________________ -->

        <div class="box text-center" data-animate="fadeInUp">
            <div class="container">
                <div class="col-md-12">
                    <h3 class="text-uppercase">Из нашего блога</h3>

                    <p class="lead">Идеи для макяжа, хитрости и советы <a href="blog.html">Загляните в наш блог!</a>
                    </p>
                </div>
            </div>
        </div>

        <div class="container">

            <div class="col-md-12" data-animate="fadeInUp">

                <div id="blog-homepage" class="row">
                    <c:forEach items="articles" var="article">
                        <div class="col-sm-6">
                            <div class="post">
                                <h4><a href="/news/${article.id}">${article.title}</a></h4>
                                </p>
                                <hr>
                                <p class="intro">${article.shortDescription}</p>
                                <p class="read-more"><a href="/news/${article.id}" class="btn btn-primary">Читать дальше</a>
                                </p>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <!-- /#blog-homepage -->
            </div>
        </div>
        <!-- /.container -->

        <!-- *** BLOG HOMEPAGE END *** -->


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

                    <h4>User section</h4>

                    <ul>
                        <li><a href="#" data-toggle="modal" data-target="#login-modal">Войти</a>
                        </li>
                        <li><a href="register.html">Регистрация</a>
                        </li>
                    </ul>

                    <hr class="hidden-md hidden-lg hidden-sm">

                </div>
                <!-- /.col-md-3 -->

                <div class="col-md-3 col-sm-6">

                    <h4>Категории</h4>

                    <ul>
                        <c:forEach items="categories" var="category">
                            <li><a href="/catalog/${category.id}">${category.name}</a></li>
                        </c:forEach>
                    </ul>

                    <hr class="hidden-md hidden-lg">

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

                    <a href="/contacts">Перейти на страницу контактов</a>

                    <hr class="hidden-md hidden-lg">

                </div>
                <!-- /.col-md-3 -->



                <div class="col-md-3 col-sm-6">

                    <hr>

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




    <!-- *** COPYRIGHT ***
_________________________________________________________ -->
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

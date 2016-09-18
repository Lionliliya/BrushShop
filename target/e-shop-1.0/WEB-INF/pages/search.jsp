<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">

<head>

  <meta charset="utf-8">
  <meta name="robots" content="all,follow">
  <meta name="googlebot" content="index,follow,snippet,archive">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Качественные кисти для макияжа купить в Киеве недорого Можно! У нас вы найдете
    красивые и стильные кисти, аксессуары по уходу за ними и многое другое.">
  <meta name="author" content="Liliya Yalovchenko">
  <meta name="keywords" content="макияж, кисти, купить, недорого">

  <title>
    Beauty Tree | Результаты поиска
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
          <p class="text-center text-muted"><a href="#"><strong>Зарегистрируйся сейчас!</strong></a>
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
          <a href="/catalog" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="200">Каталог<b class="caret"></b></a>
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
                  <div class="col-sm-3">
                    <h5>Другое</h5>
                    <ul>
                      <li><a href="/catalog">Все товары</a></li>
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
          <input type="text" class="form-control" name="pattern" placeholder="Поиск">
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
          <li>Результаты поиска</li>
        </ul>
      </div>

      <div class="col-md-3">
        <!-- *** MENUS AND FILTERS ***
_________________________________________________________ -->
        <div class="panel panel-default sidebar-menu">

          <div class="panel-heading">
            <h3 class="panel-title">Категории</h3>
          </div>

          <div class="panel-body">
            <ul class="nav nav-pills nav-stacked category-menu">

              <c:forEach items="${categories}" var="category">
                <li>
                  <a href="/catalog/${category.id}">${category.name}</a>
                </li>
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

        <%--<div class="panel panel-default sidebar-menu">--%>

        <%--<div class="panel-heading">--%>
        <%--<h3 class="panel-title">Colours <a class="btn btn-xs btn-danger pull-right" href="#"><i class="fa fa-times-circle"></i> Clear</a></h3>--%>
        <%--</div>--%>

        <%--<div class="panel-body">--%>

        <%--<form>--%>
        <%--<div class="form-group">--%>
        <%--<div class="checkbox">--%>
        <%--<label>--%>
        <%--<input type="checkbox"> <span class="colour white"></span> White (14)--%>
        <%--</label>--%>
        <%--</div>--%>
        <%--<div class="checkbox">--%>
        <%--<label>--%>
        <%--<input type="checkbox"> <span class="colour blue"></span> Blue (10)--%>
        <%--</label>--%>
        <%--</div>--%>
        <%--<div class="checkbox">--%>
        <%--<label>--%>
        <%--<input type="checkbox"> <span class="colour green"></span> Green (20)--%>
        <%--</label>--%>
        <%--</div>--%>
        <%--<div class="checkbox">--%>
        <%--<label>--%>
        <%--<input type="checkbox"> <span class="colour yellow"></span> Yellow (13)--%>
        <%--</label>--%>
        <%--</div>--%>
        <%--<div class="checkbox">--%>
        <%--<label>--%>
        <%--<input type="checkbox"> <span class="colour red"></span> Red (10)--%>
        <%--</label>--%>
        <%--</div>--%>
        <%--</div>--%>

        <%--<button class="btn btn-default btn-sm btn-primary"><i class="fa fa-pencil"></i> Apply</button>--%>

        <%--</form>--%>

        <%--</div>--%>
        <%--</div>--%>

        <!-- *** MENUS AND FILTERS END *** -->

        <div class="banner">
          <a href="#">
            <img src="/resources/img/banner-2.png" alt="Распродажа Киев" class="img-responsive">
          </a>
        </div>
      </div>

      <div class="col-md-9">

        <div class="box info-bar">
          <div class="row">
            <%--<div class="col-sm-12 col-md-4 products-showing">--%>
            <%--Showing <strong>12</strong> of <strong>25</strong> products--%>
            <%--</div>--%>

            <div class="col-sm-12 col-md-8  products-number-sort">
              <div class="row">
                <form class="form-inline">
                  <%--<div class="col-md-6 col-sm-6">--%>
                  <%--<div class="products-number">--%>
                  <%--<strong>Show</strong>  <a href="#" class="btn btn-default btn-sm btn-primary">12</a>  <a href="#" class="btn btn-default btn-sm">24</a>  <a href="#" class="btn btn-default btn-sm">All</a> products--%>
                  <%--</div>--%>
                  <%--</div>--%>
                  <div class="col-md-6 col-sm-6">
                    <div class="products-sort-by">
                      <strong>Сортировать по</strong>
                      <select name="sort-by" class="form-control">
                        <option>Цена по возрастанию</option>
                        <option>Цена по убывынию</option>

                      </select>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <div class="row products">
          <c:choose>
            <c:when test="${size eq 0}">
              <div class="col-md-12 col-sm-6">
                <div class="product">
                  <div class="text">
                    <p class="price">Прости, но товаров не найдено по твоему запросу((( Попробуй еще раз!</p>
                    <p class="buttons">
                      <a href="/catalog" onclick="history.back()" class="btn btn-default">
                        Продолжить покупки &raquo;&raquo;
                      </a>
                    </p>
                  </div><!-- /.text -->
                </div>
                <!-- /.product -->
              </div>
            </c:when>
            <c:otherwise>
              <c:forEach items="${products}" var="product">
                <div class="col-md-4 col-sm-6">
                  <div class="product">
                    <form action="/cart" method="post">

                      <input type=hidden name="id" value="${product.id}">
                      <input type=hidden name="productCategory" value="${product.productCategory.name}">
                      <input type=hidden name="smallimage" value="${product.smallimage}">
                      <input type=hidden name="name" value="${product.name}">
                      <input type=hidden name="price" value="${product.price}">
                      <input type=hidden name="currency" value="${product.currency}">
                      <div class="flip-container">
                        <div class="flipper">
                          <div class="front">
                            <a href="/product/${product.id}">
                              <img src="/resources/${product.smallimage}" alt="${product.name} Киев" class="img-responsive">
                            </a>
                          </div>
                          <div class="back">
                            <a href="/product/${product.id}">
                              <img src="/resources/${product.smallimage1}" alt="${product.name} Киев" class="img-responsive">
                            </a>
                          </div>
                        </div>
                      </div>
                      <a href="/product/${product.id}" class="invisible">
                        <img src="/resources/${product.smallimage}" alt="${product.name} Киев" class="img-responsive">
                      </a>
                      <div class="text">
                        <h3><a href="/product/${product.id}">${product.name}</a></h3>
                        <p class="price">${product.price} грн</p>
                        <p class="buttons">
                          <a href="/product/${product.id}" class="btn btn-default">Подробнее</a>
                          <button type="submit" class="btn btn-primary"><i class="fa fa-shopping-cart"></i>Добавить в корзину</button>
                        </p>
                      </div>
                      <!-- /.text -->
                    </form>
                  </div>
                  <!-- /.product -->
                </div>  <!-- /.col-md-4 -->
              </c:forEach>
            </c:otherwise>
          </c:choose>

        </div>
        <!-- /.products -->

      </div>
      <!-- /.col-md-9 -->
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
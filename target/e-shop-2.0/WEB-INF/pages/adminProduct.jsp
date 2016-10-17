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
      var answer = confirm("Вы собираетесь удалить товар  № " + id + ". Нажмите OK что бы продолжить.")
      if (answer)
        window.location = "http://localhost:8080/admin/catalog/product/remove/" + id + "";
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
        <li class="active">
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

      <div class="col-md-8 col-md-offset-2" id="customer-orders">
        <div class="row">
          <div class="box">
            <div class="row">
              <div class="col-sm-6">
                <div id="mainImage">
                  <img src="/resources/${product.image1}" alt="${product.name}" class="img-responsive">
                </div>
              </div>
              <div class="col-sm-6">
                <h3>Товар #${product.id}</h3>
                <p><em>${product.name}</em></p>
                <div class="row">
                  <div class="col-xs-5">
                    <a href="/admin/catalog/product/edit/${product.id}" class="popover-title">Редактировать</a>
                  </div>
                  <div class="col-xs-5">
                    <a href="javascript:AlertIt(${product.id});" class="popover-title">Удалить</a>
                  </div>
                </div>
                <hr>

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
            <hr>

            <blockquote>
              <p><strong>Категория товара</strong></p>
              <p>${product.productCategory.name}</p>
              <hr>
              <p><strong>Кол-во в упаковке</strong></p>
              <p>${product.amount}</p>
              <hr>
              <p><strong>В наличии</strong></p>
              <p>${product.inStock}</p>
            </blockquote>

            <blockquote>
              <p><strong>Цена товара</strong></p>
              <p>${product.price}</p>
              <hr>
              <p><strong>Валюта</strong></p>
              <p>${product.currency}</p>
            </blockquote>

            <blockquote>
              <p><strong>Полное описание</strong></p>
              <p>${product.description}</p>
              <hr>
              <p><strong>Короткое описание</strong></p>
              <p>${product.shortDesc}</p>
            </blockquote>

            <blockquote>
              <p><strong>Новый товар</strong></p>
              <c:choose>
                <c:when test="${product.isNew eq true}">
                  <p>Да</p>
                </c:when>

                <c:otherwise>
                  <p>Нет</p>
                </c:otherwise>
              </c:choose>
              <hr>
              <p><strong>Скидка</strong></p>
              <p>${product.discount}</p>
            </blockquote>

            <blockquote>
              <p><strong>Meta Key Words через запятую</strong></p>
              <p>${product.metaKeyWords}</p>
              <hr>
              <p><strong>MetaDescription</strong></p>
              <p>${product.metaDescription}</p>
              <hr>
              <p><strong>MetaTitle</strong></p>
              <p>${product.metaTitle}</p>
            </blockquote>

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
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
        window.location = "http://localhost:8081/admin/catalog/product/remove/" + id + "";
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
              <div class="col-xs-6">
                <h3 class="text-left"> Категория: ${category.name}</h3>
              </div>
              <div class="col-xs-6">
                <a href="/admin/catalog/edit/${category.name}"><h3 class="text-right">Редактировать</h3></a>
              </div>
            </div>

            <div class="box">
              <h5><i class="fa fa-info-circle"></i> Описание</h5>
              <hr>
              <p>${category.info}</p>
            </div>

            <div class="box">
              <h5><i class="fa fa-tags"></i> Meta tags блок</h5>
              <hr>
              <p>Meta key words - ${category.metaKeyWords}</p>
              <p>Meta description - ${category.metaDescription}</p>
              <p>Meta title - ${category.metaTitle}</p>
            </div>


          </div>

          <div class="box">
            <h5><i class="fa fa-shopping-cart"></i> Товары в этой категории</h5>
            <hr>
            <c:if test="${fn:length(products) eq 0}">
              <p><em>В данной категории нет еще товаров</em></p>
            </c:if>
            <c:if test="${fn:length(products) gt 0}">
            <div class="table-responsive">
              <table class="table">
                <thead>
                <tr>
                  <th>№</th>
                  <th colspan="2">Товар</th>
                  <th>Цена</th>
                  <th>Количество в уп.</th>
                  <th>В наличии</th>
                  <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                  <tr>
                    <td>${product.id}</td>
                    <td>
                      <a href="/product/${product.id}">
                        <img src="/resources/${product.image1}" alt="${product.name}" width="50">
                      </a>
                    </td>
                    <td><a href="/product/${product.id}">${product.name}</a>
                    <td>₴${product.price}</td>
                    <td>${product.amount}</td>
                    <td>${product.inStock}</td>
                    <td>
                      <a href="/admin/catalog/product/edit/${product.id}" charset="utf-8" class="btn btn-primary btn-sm">Редактировать</a><br><br>
                      <a href="javascript:AlertIt(${product.id});" class="btn btn-primary btn-sm">Удалить</a><br><br>
                      <a href="/admin/catalog/product/${product.id}" class="btn btn-primary btn-sm">Просмотреть</a>
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
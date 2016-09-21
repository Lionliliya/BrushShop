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
          <a href="/admin/clients">Клиенты</a>
        </li>
        <li class="yamm-fw">
          <a href="/admin/feedbacks">Отзывы</a>
        </li>
        <li class="yamm-fw">
          <a href="/admin/articles">Статьи и новости</a>
        </li>
        <li class="yamm-fw">
          <a href="/admin/parameters">Настройки</a>
        </li>
        <li class="yamm-fw">
          <a href="/">Перейти в интернет-магазин</a>
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
        <div class="box">
          <h1>Все заказы</h1>
          <div class="row">

            <div class="col-sm-12">
              <div class="products-sort-by">

                <div class="row">
                  <div class="col-xs-3">
                    <a href="/admin/order/dateUp"><i class="fa fa-arrow-up"></i>Сначала новые</a>
                  </div>
                  <div class="col-xs-3">
                    <a href="/catalog/order/dateDown"><i class="fa fa-arrow-down"></i> Сначала старые</a>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <hr>
          <c:if test="${orders.size() eq 0}">
            <div class="col-md-12"><article class="art-head"><h2>У вас нет заказов</h2></article></div>
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
                  <td><fmt:formatDate type="date" dateStyle="short" timeStyle="short" value="${order.date}" /></td>
                  <td>₴${order.totalAmount}</td>
                  <td>${order.client.firstName}</td>
                  <td><span class="label label-info">статус</span></td>
                  <td>${order.delivery}</td>
                  <td>
                    <a href="/admin/order/edit/${order.id}" class="btn btn-primary btn-sm">Редактировать</a>
                    <a href="javascript:AlertIt(${order.id});" class="btn btn-primary btn-sm">Удалить</a>
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
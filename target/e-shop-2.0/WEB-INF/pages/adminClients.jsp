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
      var answer = confirm("Вы собираетесь удалить клиента  № " + id + ". Нажмите OK что бы продолжить.")
      if (answer)
        window.location = "http://localhost:8081/admin/client/remove/" + id + "";
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
        <li class="active">
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
        <div class="box">
          <h1>Все клиенты</h1>
          <div class="row">

            <div class="col-sm-12">
              <div class="products-sort-by">
                <div class="row">
                  <div class="col-xs-3">
                    <a href="/admin/client/sort/name"><i class="fa fa-arrow-up"></i>По имени</a>
                  </div>
                  <div class="col-xs-3">
                    <a href="/admin/client/sort/email"><i class="fa fa-arrow-down"></i> По эл.адресу</a>
                  </div>
                  <div class="col-xs-6">
                    <a href="#add"><p class="text-right"><i class="fa fa-plus-circle"></i> Создать нового клиента</p></a>
                  </div>
                </div>
              </div>
            </div>

          </div>

          <c:set var="clients" value="${clients}"/>
          <c:if test="${fn:length(clients) eq 0}">
            <div class="col-md-12"><article class="art-head"><h2>У вас нет клиентов</h2></article></div>
          </c:if>
          <c:if test="${fn:length(clients) gt 0}">
            <div class="table-responsive">
              <table class="table table-hover">
                <thead>
                <tr>
                  <th>№</th>
                  <th>Имя</th>
                  <th>Номер телефона</th>
                  <th>Эл. адресс</th>
                  <th>Действие</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${clients}" var="client">
                  <tr>
                    <td>${client.id}</td>
                    <td>${client.firstName}</td>
                    <td>${client.phoneNumber}</td>
                    <td>${client.email}</td>
                    <td>
                      <a href="/admin/client/edit/${client.id}" class="btn btn-primary btn-sm">Редактировать</a>
                      <a href="javascript:AlertIt(${client.id});" class="btn btn-primary btn-sm">Удалить</a>
                      <a href="/admin/client/${client.id}" class="btn btn-primary btn-sm">Просмотреть</a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </c:if>
        </div>

        <hr>


          <div class="box" id="add">
            <h3>Добавить клиента</h3>
            <form role = "form" action="/admin/client/add" method="post">

                  <div class="form-group">
                    <label for="clientName">Имя клиента</label>
                    <input type="text" id="clientName" class="form-control" name="firstName" required pattern="[A-Za-zА-Яа-яЁё-Іі-Її ]+"/>
                  </div>

                  <div class="form-group">
                    <label for="clientEmail">Ваша электронная почта</label>
                    <input class="form-control" id="clientEmail" name="email" type="text"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                  </div>

                  <div class="form-group">
                    <label for="phoneNumber">Телефон в формате 38-0xx-xxx-xx-xx</label>
                    <input type="text" id="phoneNumber" class="form-control" name="phoneNumber" pattern="38-0[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}" required>
                  </div>

                  <div class="row">
                    <div class="col-sm-12 text-center">
                      <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> Добавить клиента</button>
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
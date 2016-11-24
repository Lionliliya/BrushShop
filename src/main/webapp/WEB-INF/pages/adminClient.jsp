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
      var answer = confirm("Вы собираетесь удалить клиента № " + id + ". Нажмите OK что бы продолжить.")
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

      <div class="col-xs-10 col-xs-offset-1" id="customer-order">
        <div class="box">
          <c:set var="client" value="${client}"/>
          <h2>Клиент #${client.id}</h2>
          <a href="/admin/client/edit/${client.id}" class="btn btn-primary btn-sm">Редактировать</a>
          <a href="javascript:AlertIt(${client.id});" class="btn btn-primary btn-sm">Удалить</a>
          <a href="#add" class="btn btn-primary btn-sm">Добавить отзыв</a>
          <h3>Имя клиента - ${client.firstName}</h3>
          <h4>Контактный телефон -${client.phoneNumber} </h4>
          <h4>Эл. почта - ${client.email} </h4>

          <div class="row">
            <div class="box">
            <h3>Список заказов клиента</h3>
            <c:if test="${fn:length(orders) eq 0}">
              <h5>У клиента нет заказов</h5>
            </c:if>
            <c:if test="${fn:length(orders) gt 0}">
              <div class="table-responsive">
                <table class="table table-hover">
                  <thead>
                  <tr>
                    <th>№</th>
                    <th>Дата</th>
                    <th>Сумма</th>
                    <th>Статус</th>
                    <th>Доставка</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${orders}" var="order">
                    <tr>
                      <td><a href="/admin/order/${order.id}">10${order.id}</a></td>
                      <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${order.date}" /></td>
                      <td>₴${order.totalAmount}</td>
                      <td><span class="label label-info">статус</span></td>
                      <td>${order.delivery}</td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </c:if>
            </div>
          </div>

          <div class="row">
            <div class="box">
              <h3>Отзывы клиента</h3>
              <c:if test="${fn:length(feedBacks) eq 0}">
                <h5>У клиента нет отзывов</h5>
              </c:if>
              <c:if test="${fn:length(feedBacks) gt 0}">
                <div class="table-responsive">
                  <table class="table table-hover">
                    <thead>
                    <tr>
                      <th>№</th>
                      <th>Дата</th>
                      <th colspan="2">Товар</th>
                      <th>Оценка</th>
                      <th>Отзыв</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${feedBacks}" var="feedback">
                      <tr>
                        <td><a href="/admin/feedback/${feedback.id}">${feedback.id}</a></td>
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${feedback.date}" /></td>
                        <td>
                          <a href="/product/${feedback.product.id}">
                              ${feedback.product.name}
                          </a>
                        </td>
                        <td>
                          <a href="/admin/product/${feedback.product.id}">
                            <img src="/resources/${feedback.product.image1}" alt="${feedback.product.name}">
                          </a>
                        </td>
                        <td>${feedback.evaluation}</td>
                        <td>${feedback.feedback}</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </c:if>
            </div>
          </div>

          <!-- /.table-responsive -->
        <div class="row">
        <div class="box" id="add">
        <h3>Добавить отзыв</h3>
        <form role = "form" action="/admin/feedback/add/${client.id}" method="post">

          <div class="form-group">
            <label for="productId">Товар</label>
            <select  class="form-control" id="productId" name="product" required>
              <c:forEach items="${products}" var="product">
                <option value="${product.id}">${product.name}</option>
              </c:forEach>
            </select>
          </div>

          <div class="form-group">
            <label for="dateFeedBack">Дата в формате dd.mm.yyyy hh:mm Напр.: 22.05.2016 17:46</label>
            <input type="text" id="dateFeedBack" class="form-control" name="date" required/>
          </div>

          <div class="form-group">
            <label for="rate">Оценка от 1 до 5</label>
            <input type="number" id="rate" class="form-control" name="evaluation" required pattern="[0-5]{1}"/>
          </div>

          <div class="form-group">
            <label for="message">Напшите отзыв</label>
            <textarea id="message" class="form-control" name="feedback"></textarea>
          </div>
          <div class="row">
          <div class="col-sm-12 text-center">
            <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> Отправить отзыв</button>
          </div>
          </div>
            </div>
        </div>
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
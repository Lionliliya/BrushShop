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

      <div class="col-md-8 col-md-offset-2" id="customer-order">
        <div class="box">

          <form role="form" action="/admin/client/save/${client.id}" method="post">
            <div class="form-group">
              <label for="clientId">№ клиента</label>
              <input type="text" id="clientId" class="form-control" name="id" value="${client.id}" readonly>
            </div>

            <div class="form-group">
              <label for="clientName">Имя</label>
              <input type="text" id="clientName" class="form-control" name="firstName" pattern="[A-Za-zА-Яа-яЁё-Іі-Її ]+" value="${client.firstName}" required/>
            </div>

            <div class="form-group">
              <label for="phoneNumber">Телефон</label>
              <input type="text" id="phoneNumber" class="form-control" name="phoneNumber" value="${client.phoneNumber}" pattern="38-0[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}" required>
            </div>

            <div class="form-group">
              <label for="clientEmail">Эл. адресс</label>
              <input type="text" id="clientEmail" class="form-control" name="email" value="${client.email}"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
            </div>

            <button type="submit" class="btn btn-success">Сохранить</button>

          </form>

          <hr>
          <form role="form" action="/admin/client/removeFeedback/${client.id}" method="post">

            <label for="table1">
              Список отзывов. Вибрете отзыв, что бы удалить его, и нажите кнопку "Удалить"
            </label>

            <c:if test="${fn:length(feedBacks) eq 0}">
              <h5>У клиента нет отзывов</h5>
            </c:if>
            <c:if test="${fn:length(feedBacks) gt 0}">
              <div class="table-responsive">
                <table class="table table-hover" id="table1">
                  <thead>
                  <tr>
                    <th>№</th>
                    <th>Дата</th>
                    <th colspan="2">Товар</th>
                    <th>Оценка</th>
                    <th>Отзыв</th>
                    <th>Удалить</th>
                  </tr>
                  </thead>
                  <tbody>

                  <c:forEach items="${feedBacks}" var="feedback">
                    <tr>
                      <td>${feedback.id}</td>
                      <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${feedback.date}" /></td>
                      <td>
                        <a href="/admin/product/${feedback.product.id}">
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
                      <td><input type="radio" value="${feedback.id}" name="delete"></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </c:if>

            <button type="submit" class="btn btn-success">Удалить</button>
          </form>

        </div>
        <!-- /.table-responsive -->
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
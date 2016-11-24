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
      var answer = confirm("Вы собираетесь удалить пост  № " + id + ". Нажмите OK что бы продолжить.")
      if (answer)
        window.location = "http://localhost:8081/admin/post/remove/" + id + "";
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
        <li class="yamm-fw">
          <a href="/admin/client">Клиенты</a>
        </li>
        <li class="yamm-fw">
          <a href="/admin/feedback">Отзывы</a>
        </li>
        <li class="active">
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

      <div class="col-md-12 " id="customer-orders">
        <div class="row">
        <div class="box">
          <div class="row">
            <div class="col-xs-9">
              <h3>Настройки пользователей системы</h3>
            </div>

          </div>
          <div class="row">
            <div class="col-xs-12">
          <c:set var="users" value="${users}"/>
          <c:if test="${fn:length(users) eq 0}">
            <div class="col-md-12"><article class="art-head"><h2>У вас нет постов</h2></article></div>
          </c:if>
          <c:if test="${fn:length(users) gt 0}">
            <div class="table-responsive ">
              <table class="table table-hover">
                <thead>
                <tr>
                  <th>№</th>
                  <th>Username</th>
                  <th>Role</th>
                  <th>Номер телефона</th>
                  <th>Эмеил</th>
                  <th>Домен</th>
                  <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                  <tr>
                    <td>${user.id}</td>
                    <td><a href="/admin/user/${user.id}">${user.username}</a></td>
                    <td>${user.role}</td>
                    <td>
                      <p>${user.phoneNumber1}</p>
                      <p>${user.phoneNumber2}</p>
                    </td>
                    <td>${user.emailAddress}</td>
                    <td>${user.domainName}</td>
                    <td>
                      <p><a href="/admin/user/edit/${user.id}" class="btn btn-primary btn-sm"><i class="fa fa-pencil-square"></i> Редактировать</a></p>
                      <p><a href="javascript:AlertIt(${user.id});" class="btn btn-primary btn-sm"><i class="fa fa-scissors"></i> Удалить</a></p>
                      <p><a href="/admin/user/${user.id}" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> Просмотреть</a></p>
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
        <div class="row">
          <div class="box" id="add">
            <h3>Создать новго пользователя</h3>
            <form role = "form" action="/admin/user/add" method="post">
            <div class="table-responsive">
              <table class="table table-hover">
                <tbody>
                <tr>
                  <th>Username</th>
                  <th>
                    <input type="text" class="form-control" maxlength="100" name="username" required/>
                  </th>
                </tr>
                <tr>
                  <th>Role</th>
                  <th>
                    <input type="text" class="form-control" maxlength="100" name="role" required/>
                  </th>
                </tr>
                <tr>
                  <th>Пароль</th>
                  <th>
                    <input type="text" maxlength="60" class="form-control" name="password" required/>
                  </th>
                </tr>
                <tr>
                  <th>Номер телефона 1</th>
                  <th>
                    <input type="text" class="form-control" maxlength="60" name="phoneNumber1" required/>
                  </th>
                </tr>
                <tr>
                  <th>Номер телефона 2</th>
                  <th>
                    <input type="text" class="form-control" maxlength="60" name="phoneNumber2" required/>
                  </th>
                </tr>
                <tr>
                  <th>Эл. адрес</th>
                  <th>
                    <input type="text" maxlength="150" class="form-control" name="emailAddress" required>
                  </th>
                </tr>
                <tr>
                  <th>Домен</th>
                  <th>
                    <input type="text" maxlength="150" class="form-control" name="domainName" required>
                  </th>
                </tr>
                <tr>
                  <th>Сохранить</th>
                  <th>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> Сохранить изменения</button>
                  </th>
                </tr>
                </tbody>
              </table>
            </div>
            </form>
          </div>
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
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

</head>

<body>

<div id="all">

  <div id="content">
    <div class="container">

      <div class="col-md-12">
        <ul class="breadcrumb">
          <li>
            <a href="/admin/">Административная панель</a>
          </li>
          <li>
            <a href="/">Вернуться в магазин</a>
          </li>
          <li>Вход</li>
        </ul>

      </div>

      <div class="col-md-6 col-md-offset-3">
        <div class="box">
          <h1>Вход</h1>

          <hr>

          <form action="/admin/" method="post">
            <div class="form-group">
              <label for="login1">Логин</label>
              <input type="text" class="form-control" name="username" id="login1" pattern=".{5,}" title="Five or more characters">
            </div>
            <div class="form-group">
              <label for="password">Пароль</label>
              <input type="password" class="form-control" name="password" id="password"
                     pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Must contain at least one number
                      and one uppercase and lowercase letter, and at least 6 or more characters">
            </div>
            <div class="text-center">
              <button type="submit" class="btn btn-primary"><i class="fa fa-sign-in"></i> Войти</button>
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
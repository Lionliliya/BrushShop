<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
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
    function AlertIt(name) {
      var answer = confirm("Вы собираетесь удалить категорию и ВСЕ ТОВАРЫ В НЕЙ по  № " + id + ". Нажмите OK что бы продолжить.")
      if (answer)
        window.location = "http://localhost:8080/admin/catalog/remove/" + name + "";
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
        <li lass="yamm-fw">
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
          <a href="/admin/article">Статьи и новости</a>
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

      <div class="col-xs-12" id="customer-orders">
        <div class="row" id="productMain">
          <div class="box">
            <a href="#details"><i class="fa fa-plus"></i> Добавить товар</a>
            <a href="/admin/category/add" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i> Добавить категорию</a>
          </div>
        </div>

        <div class="row">
          <div class="box">
            <c:set var="categories" value="${categories}"/>
            <c:if test="${fn:length(categories) eq 0}">
              <div class="col-md-12"><article class="art-head"><h2>У вас нет категорий товаров</h2></article></div>
            </c:if>
            <c:if test="${fn:length(categories) gt 0}">
              <div class="table-responsive">
                <table class="table table-hover">
                  <thead>
                  <tr>
                    <th>№</th>
                    <th>Имя</th>
                    <th>Описание</th>
                    <th>Meta key words</th>
                    <th>Meta description</th>
                    <th>Meta title</th>
                    <th>Действие</th>
                  </tr>
                  </thead>
                  <tbody>

                  <c:forEach items="${categories}" var="category">
                    <tr>
                      <td>${category.id}</td>
                      <td>${category.name}</td>
                      <td>${category.info}</td>
                      <td>${category.metaKeyWords}</td>
                      <td>${category.metaDescription}</td>
                      <td>${category.metaTitle}</td>
                      <td>
                        <a href="/admin/catalog/edit/${category.name}" charset="utf-8" class="btn btn-primary btn-sm">Редактировать</a><br><br>
                        <a href="javascript:AlertIt(${category.name});" class="btn btn-primary btn-sm">Удалить</a><br><br>
                        <a href="/admin/catalog/${category.name}" class="btn btn-primary btn-sm">Просмотреть</a>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </c:if>

          </div>
        </div>

        <div class="row" id="details">
          <div class="box">
            <h3>Добавить товар</h3>
            <form role = "form" action="/admin/catalog/product/add" method="post">

              <div class="form-group">
                <label for="category">Категория товара</label>
                <select  class="form-control" id="category" name="productCategory" required>
                  <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                  </c:forEach>
                </select>
              </div>

              <div class="form-group">
                <label for="name">Имя товара</label>
                <input type="text" id="name" class="form-control" name="name" required/>
              </div>

              <div class="form-group">
                <label for="price">Цена товара</label>
                <input type="number" id="price" class="form-control" name="price" required/>
              </div>

              <div class="form-group">
                <label for="currency">Валюта</label>
                <input type="text" id="currency" class="form-control" name="currency" required/>
              </div>

              <div class="form-group">
                <label for="amount">Кол-во в упаковке</label>
                <input type="number" id="amount" class="form-control" name="amount" required/>
              </div>

              <div class="form-group">
                <label>В наличии</label>
                <div class="row">
                  <div class="col-sm-2 col-sm-offset-4">
                    <input type="radio" name="inStock" value="yes" required/> Да
                  </div>
                  <div class="col-sm-2">
                    <input type="radio" name="inStock" value="no" required/> Нет
                  </div>
                  </div>
                </div>


              <div class="form-group">
                <label for="description">Полное описание</label>
                <input type="text" id="description" class="form-control" name="description" required/>
              </div>

              <div class="form-group">
                <label for="shortDesc">Короткое описание</label>
                <input type="text" id="shortDesc" class="form-control" name="dshortDesc" required/>
              </div>

              <div class="form-group">
                <label for="smallimage">Путь к изображению мини-1</label>
                <input type="text" id="smallimage" class="form-control" name="smallimage" required/>
              </div>

              <div class="form-group">
                <label for="smallimage1">Путь к изображению мини-2</label>
                <input type="text" id="smallimage1" class="form-control" name="smallimage1" required/>
              </div>

              <div class="form-group">
                <label for="image1">Фото 1 </label>
                <input type="text" id="image1" class="form-control" name="image1" required/>
              </div>

              <div class="form-group">
                <label for="image2">Фото 2 </label>
                <input type="text" id="image2" class="form-control" name="image2" required/>
              </div>

              <div class="form-group">
                <label for="image3">Фото 3 </label>
                <input type="text" id="image3" class="form-control" name="image3" required/>
              </div>

              <div class="form-group">
                <label for="image4">Фото 4 </label>
                <input type="text" id="image4" class="form-control" name="image4" required/>
              </div>

              <div class="form-group">
                <label for="metaKeyWords">Meta Key Words через запятую</label>
                <input type="text" id="metaKeyWords" class="form-control" name="metaKeyWords" required/>
              </div>

              <div class="form-group">
                <label for="metaDescription">MetaDescription</label>
                <input type="text" id="metaDescription" class="form-control" name="metaDescription" required/>
              </div>

              <div class="form-group">
                <label for="metaTitle">MetaTitle</label>
                <input type="text" id="metaTitle" class="form-control" name="metaTitle" required/>
              </div>

              <div class="row">
                <div class="col-sm-12 text-center">
                  <button type="submit" class="btn btn-primary"> Отправить отзыв</button>
                </div>
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
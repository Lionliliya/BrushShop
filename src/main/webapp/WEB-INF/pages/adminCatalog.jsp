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
    function AlertIt(id) {
      var answer = confirm("Вы собираетесь удалить категорию и ВСЕ ТОВАРЫ В НЕЙ по  № " + id + ". Нажмите OK что бы продолжить.")
      if (answer)
        window.location = "http://localhost:8081/admin/catalog/remove/" + id + "";
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

      <div class="col-xs-12" id="customer-orders">
        <div class="row">
          <div class="box">
            <a href="#details1" class="popover-title"><i class="fa fa-plus"></i> Добавить товар</a>
            <a href="#details2" class="popover-title"><i class="fa fa-plus"></i> Добавить категорию</a>
            <a href="/admin/catalog/product" class="popover-title"> Все товары</a>
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
                        <a href="javascript:AlertIt(${category.id});" class="btn btn-primary btn-sm">Удалить</a><br><br>
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

        <div class="row">
        <div class="col-xs-6" id="details1">
          <div class="box">
            <h3>Добавить товар</h3>
            <form role = "form" action="/admin/catalog/product/add" method="post">

              <div class="form-group">
                <blockquote>
                <label for="category"><strong>Категория товара</strong></label>
                <select  class="form-control" id="category" name="id" required>
                  <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                  </c:forEach>
                </select>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="name"><strong>Имя товара</strong></label>
                <input type="text" id="name" class="form-control" name="name" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                  <label for="brand"><strong>Бренд</strong></label>
                  <input type="text" id="brand" class="form-control" maxlength="120" name="brand" required pattern="[A-Za-zА-Яа-яЁё-Іі-Її ]+"/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="price"><strong>Цена товара</strong></label>
                <input type="number" id="price" class="form-control" name="price" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="currency"><strong>Валюта</strong></label>
                <input type="text" id="currency" class="form-control" name="currency" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="amount"><strong>Кол-во в упаковке</strong></label>
                <input type="number" id="amount" class="form-control" name="amount" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label><strong>В наличии</strong></label>
                <div class="row">
                  <div class="col-sm-2 col-sm-offset-4">
                    <input type="radio" name="inStock" value="yes" required/> Да
                  </div>
                  <div class="col-sm-2">
                    <input type="radio" name="inStock" value="no" required/> Нет
                  </div>
                  </div>
                </blockquote>
                </div>


              <div class="form-group">
                <blockquote>
                <label for="description"><strong>Полное описание</strong></label>
                <input type="text" id="description" class="form-control" name="description" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="shortDesc"><strong>Короткое описание</strong></label>
                <input type="text" id="shortDesc" class="form-control" name="shortDesc" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                  <label><strong>Новый товар</strong></label>
                  <input type="radio" class="form-control" name="isNew"
                             value="${true}" required/> Да
                  <input type="radio" class="form-control active" name="isNew"
                             value="${false}" required/> Нет
                </blockquote>
              </div>
              <div class="form-group">
                <blockquote>
                  <label for="discount"><strong>Скидка, %</strong></label>
                  <input type="number" id="discount" class="form-control" name="discount"
                         required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="image1"><strong>Фото 1 </strong></label>
                <input type="text" id="image1" class="form-control" name="image1" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="image2"><strong>Фото 2</strong> </label>
                <input type="text" id="image2" class="form-control" name="image2" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="image3"><strong>Фото 3 </strong></label>
                <input type="text" id="image3" class="form-control" name="image3" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="image4"><strong>Фото 4</strong> </label>
                <input type="text" id="image4" class="form-control" name="image4" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="metaKeyWords"><strong>Meta Key Words через запятую</strong></label>
                <input type="text" id="metaKeyWords" class="form-control" name="metaKeyWords" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="metaDescription"><strong>MetaDescription</strong></label>
                <input type="text" id="metaDescription" class="form-control" name="metaDescription" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                <label for="metaTitle"><strong>MetaTitle</strong></label>
                <input type="text" id="metaTitle" class="form-control" name="metaTitle" required/>
                </blockquote>
              </div>

              <div class="row">
                <div class="col-sm-12 text-center">
                  <button type="submit" class="btn btn-primary">Создать</button>
                </div>
              </div>

            </form>
          </div>
        </div>

        <div class="col-xs-6" id="details2">
          <div class="box">
            <h3>Создать категорию</h3>
            <form role = "form" action="/admin/catalog/add" method="post">

              <div class="form-group">
                <blockquote>
                  <label for="nameCategory"><strong>Имя категории</strong></label>
                  <input type="text" id="nameCategory" class="form-control" name="name" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                  <label for="info"><strong>Описание категории</strong></label>
                  <input type="text" id="info" class="form-control" name="info" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                  <label for="metaKeyWordsCat"><strong>Meta Key Words через запятую</strong></label>
                  <input type="text" id="metaKeyWordsCat" class="form-control" name="metaKeyWords" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                  <label for="metaDescriptionCat"><strong>MetaDescription</strong></label>
                  <input type="text" id="metaDescriptionCat" class="form-control" name="metaDescription" required/>
                </blockquote>
              </div>

              <div class="form-group">
                <blockquote>
                  <label for="metaTitleCat"><strong>MetaTitle</strong></label>
                  <input type="text" id="metaTitleCat" class="form-control" name="metaTitle" required/>
                </blockquote>
              </div>

              <div class="row">
                <div class="col-sm-12 text-center">
                  <button type="submit" class="btn btn-primary"> Создать</button>
                </div>
              </div>

            </form>
          </div>
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
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
  <script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
  <script type="text/javascript">
    tinymce.init({
      selector: 'textarea',
      extended_valid_elements : 'img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name]',
      theme: 'modern',
      plugins: [
        'advlist autolink link image imagetools lists charmap print preview hr anchor pagebreak spellchecker',
        'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
        'save table contextmenu directionality emoticons template paste textcolor'
      ],
      toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons',
      content_css: '/resources/css/bootstrap.css',
      a_plugin_option: true,
      a_configuration_option: 400,
      browser_spellcheck: true,
      automatic_uploads: true
    });
  </script>
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

      <div class="col-md-10 col-md-offset-1" id="customer-orders">
        <div class="box">
          <h1>Все посты</h1>
          <div class="row">

            <div class="col-sm-12">
              <div class="products-sort-by">
                <div class="row">
                  <div class="col-xs-3">
                    <a href="/admin/post/sort/nameUp"><i class="fa fa-arrow-up"></i></a>
                    <a href="/admin/post/sort/nameDown"><i class="fa fa-arrow-down"></i></a>
                    По имени
                  </div>
                  <div class="col-xs-3">
                    <a href="/admin/post/sort/dateUp"><i class="fa fa-arrow-up"></i></a>
                    <a href="/admin/post/sort/dateDown"><i class="fa fa-arrow-down"></i></a>
                    По дате
                  </div>
                  <div class="col-xs-6">
                    <a href="#add"><p class="text-right"><i class="fa fa-plus-circle"></i> Добавить новый пост</p></a>
                  </div>
                </div>
              </div>
            </div>

          </div>

          <c:set var="posts" value="${posts}"/>
          <c:if test="${fn:length(posts) eq 0}">
            <div class="col-md-12"><article class="art-head"><h2>У вас нет постов</h2></article></div>
          </c:if>
          <c:if test="${fn:length(posts) gt 0}">
            <div class="table-responsive">
              <table class="table table-hover">
                <thead>
                <tr>
                  <th>№</th>
                  <th>Заголовок</th>
                  <th>Фото</th>
                  <th>Короткое описание</th>
                  <th>Дата</th>
                  <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${posts}" var="post">
                  <tr>
                    <td>${post.id}</td>
                    <td>
                      <a href="/admin/post/${post.id}">
                        <c:out value="${post.title}"/>
                      </a></td>
                    <td>
                      <a href="/admin/post/${post.id}">
                        <img src="/resources/${post.imagePath}" width="80">
                      </a>
                    </td>
                    <td>${post.shortDescription}</td>
                    <td><fmt:formatDate type="date" dateStyle="short" timeStyle="short" value="${post.dateOfPublication}" /></td>
                    <td>
                      <p><a href="/admin/post/edit/${post.id}" class="btn btn-primary btn-sm"><i class="fa fa-pencil-square"></i> Редактировать</a></p>
                      <p><a href="javascript:AlertIt(${post.id});" class="btn btn-primary btn-sm"><i class="fa fa-scissors"></i> Удалить</a></p>
                      <p><a href="/admin/post/${post.id}" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i>Просмотреть</a></p>
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
          <h1>Создать статью</h1>
          <form role = "form" name="addPost" action="/admin/post/add" method="post">

            <div class="form-group">
              <label for="title">Заголовок</label>
              <input type="text" id="title" maxlength="250" class="form-control" name="title" required/>
            </div>

            <div class="form-group">
              <label for="photo">Ссылка на фото</label>
              <input class="form-control" id="photo" maxlength="200" name="imagePath" type="text" required>
            </div>

            <div class="form-group">
              <label for="date">Дата в формате 26.09.2016</label>
              <input type="text" id="date" class="form-control" name="dateOfPublication" required>
            </div>

            <div class="form-group">
              <label for="button">Надпись на кнопке перехода к детальному чтению поста</label>
              <input type="text" id="button" maxlength="100" class="form-control" name="buttonText" required>
            </div>

            <div class="form-group">
              <label for="shortDescription">Короткое описание</label>
              <textarea id="shortDescription" maxlength="800" class="form-control" name="shortDescription"></textarea>
            </div>

            <div class="form-group">
              <label>Контент</label>
              <textarea class="form-control" name="content"></textarea>
            </div>

            <div class="form-group">
              <label for="metaTitle">MetaTitle</label>
              <input type="text" id="metaTitle" maxlength="70" class="form-control" name="metaTitle" required>
            </div>

            <div class="form-group">
              <label for="metaKeyWords">MetaKeyWords</label>
              <input type="text" id="metaKeyWords" maxlength="120" class="form-control" name="metaKeyWords" required>
            </div>

            <div class="form-group">
              <label for="metaDescription">MetaDescription</label>
              <input type="text" id="metaDescription" maxlength="160" class="form-control" name="metaDescription" required>
            </div>

            <div class="row">
              <div class="col-sm-12 text-center">
                <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> Добавить пост</button>
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
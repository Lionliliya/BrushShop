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

  <script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
  <script type="text/javascript">
    tinymce.init({
      selector: 'textarea',
      theme: 'modern',
      plugins: [
        'advlist autolink link image imagetools lists charmap print preview hr anchor pagebreak spellchecker',
        'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
        'save table contextmenu directionality emoticons template paste textcolor'
      ],
      toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons',
      content_css: [
        '/resources/css/bootstrap.min.css',
        '/resources/css/font-awesome.css'
              ],
      a_plugin_option: true,
      a_configuration_option: 400,
      browser_spellcheck: true,
      automatic_uploads: true
    });
  </script>

  <script type="text/javascript">
    function AlertIt(id) {
      var answer = confirm("Вы собираетесь удалить пост  № " + id + ". Нажмите OK что бы продолжить.")
      if (answer)
        window.location = "http://localhost:8080/admin/post/remove/" + id + "";
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
          <c:set value="${post}" var="post"/>

          <form role = "form" action="/admin/post/save/${post.id}" method="post">

            <div class="form-group">
              <blockquote>
                <label for="id"><strong>Номер поста</strong></label>
                <input type="text" id="id" class="form-control" value="${post.id}" name="id" readonly/>
              </blockquote>
            </div>

            <div class="form-group">
              <blockquote>
                <label for="title"><strong>Заголовок</strong></label>
                <input type="text" id="title" class="form-control" maxlength="250" name="title" value="${post.title}" required/>
              </blockquote>
            </div>

            <div class="form-group">
              <blockquote>
                <label for="imagePath"><strong>Ссылка на фото</strong></label>
                <input type="text" id="imagePath" maxlength="200" class="form-control" name="imagePath" value="${post.imagePath}" required/>
              </blockquote>
            </div>

            <div class="form-group">
              <label for="date">Дата в формате 26.06.2016</label>
              <input type="text" id="date" class="form-control" name="dateOfPublication" value="<fmt:formatDate type="date"
                      dateStyle="short" timeStyle="short" value="${post.dateOfPublication}"/>" required>
            </div>

            <div class="form-group">
              <label for="button">Надпись на кнопке перехода к детальному чтению поста</label>
              <input type="text" id="button" maxlength="100" class="form-control" value="${post.buttonText}" name="buttonText" required>
            </div>

            <div class="form-group">
              <label for="shortDescription">Короткое описание</label>
              <textarea type="text" id="shortDescription" maxlength="800" class="form-control" name="shortDescription">
                <c:out value="${post.shortDescription}"/>
              </textarea>
            </div>

            <div class="form-group">
              <label for="contentPost">Контент</label>
              <textarea type="text" id="contentPost" class="form-control" name="content">
                <c:out value="${post.content}"/>
              </textarea>
            </div>

            <div class="form-group">
              <label for="metaTitle">MetaTitle</label>
              <input type="text" id="metaTitle" maxlength="70" class="form-control" value="${post.metaTitle}" name="metaTitle" required>
            </div>

            <div class="form-group">
              <label for="metaKeyWords">MetaKeyWords</label>
              <input type="text" id="metaKeyWords" maxlength="120" class="form-control" value="${post.metaKeyWords}" name="metaKeyWords" required>
            </div>

            <div class="form-group">
              <label for="metaDescription">MetaDescription</label>
              <input type="text" id="metaDescription" maxlength="160" class="form-control" value="${post.metaDescription}" name="metaDescription" required>
            </div>

            <div class="row">
              <div class="col-sm-12 text-center">
                <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> Сохранить изменения</button>
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
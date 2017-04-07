<!DOCTYPE html>
<html class='no-js' lang='en'>
<head>
<#include './head.ftl'>
<title>后台管理</title>
<#if importMode == "online">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<#else>
    <link href="plugins/bootstrap/bootstrap-3.3.7.min.css" rel="stylesheet">
    <link href="plugins/bootstrap/bootstrap-theme-3.3.7.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</#if>
<link href="css/app.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    .embed-responsive.embed-responsive-16by9 {
        padding-bottom: 43%;
    }
    div#content {
        padding: 7px;
    }
</style>
</head>
<body class='main page'>
    <#include './navbar.ftl'>
    <div id='wrapper'>
      <#include './sidebar.ftl'>
      <!-- Tools -->
      <section id='tools'>
        <ul class='breadcrumb' id='breadcrumb'>
            <li class='title'>图片管理</li>
            <li class='active'>瀑布管理</li>
        </ul>
        <div id='toolbar'>
          <div class='btn-group'>
            <a class='btn' href='javascript:void(0)' onclick="refresh()">
              <i class='fa fa-refresh'></i>
            </a>
            <a class='btn' href='javascript:void(0)' onclick="openInNew()">
              <i class='fa fa-window-maximize'></i>
            </a>
          </div>
        </div>
      </section>
      <!-- Content -->
      <div id='content'>
        <div class="embed-responsive embed-responsive-16by9">
          <iframe id="fallFrame"  class="embed-responsive-item" src="images/fall.html"></iframe>
        </div>
      </div>
    </div>
    <script src="js/manage/manage.js"></script>
    <#include './footer.ftl'>
    <script src="js/manage/app.js" type="text/javascript"></script>
</body>
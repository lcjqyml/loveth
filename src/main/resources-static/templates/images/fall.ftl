<!DOCTYPE html>
<html>
<head>
<#include '../head.ftl'>
<title>图片瀑布</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<#if importMode == "online">
    <link href="//cdn.bootcss.com/normalize/5.0.0/normalize.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/imageviewer/0.5.1/viewer.min.css" rel="stylesheet">
<#else>
    <link href="css/normalize-5.0.0.min.css" rel="stylesheet">
    <link href="plugins/bootstrap/bootstrap-3.3.7.min.css" rel="stylesheet">
    <link href="plugins/bootstrap/bootstrap-theme-3.3.7.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="plugins/viewer/viewer-0.5.1.min.css" rel="stylesheet">
</#if>
<link rel="stylesheet" href="css/templatemo-style.css">
<style>

    h4 {
        color: black;
    }

</style>
</head>
<body>
<!--[if lt IE 7]>
    <p class="browsehappy">您正在使用已经<strong>过时的</strong>浏览器。 为了更好的体验网站效果，请升级您的浏览器。</p>
<![endif]-->
<div id="loader-wrapper">
    <div id="loader"></div>
</div>
<div class="content-bg"></div>
<div class="bg-overlay"></div>
<!-- SITE TOP -->
<div class="site-top">
    <div class="site-header clearfix">
        <div class="container">
            <div class="social-icons pull-right">
                <ul>
                    <#if mode == 'manage'>
                        <li><a href="javascript:void(0)" onclick="uploadImages()" class="fa fa-plus-circle"></a></li>
                    </#if>
                    <li><a href="javascript:void(0)" onclick="toViewerPage()" class="fa fa-search"></a></li>
                </ul>
            </div>
        </div>
    </div> <!-- .site-header -->
</div> <!-- .site-top -->
<!-- MAIN POSTS -->
<div class="main-posts">
    <div class="container">
        <div class="row">
            <div class="blog-masonry masonry-true viewerImgs">
            <#list imgList as pic>
                <div class="post-masonry col-md-4 col-sm-6">
                    <div class="post-thumb">
                        <img class="lazy" data-original="${pic.name}" alt="" width="${pic.width?c}" height="${pic.height?c}">
                        <div class="post-hover text-center">
                            <div class="inside">
                                <#if mode == 'manage'>
                                    <a class="fa fa-minus" href="javascript:void(0)" onclick="removeImg('${pic.name}')"></a>
                                    &nbsp;
                                </#if>
                                <a class="fa fa-search" href="javascript:void(0)" onclick="showViewer(${pic_index})"></a>
                            </div>
                        </div>
                    </div>
                </div> <!-- /.post-masonry -->
            </#list>
            </div>
        </div>
    </div>
</div>
<div style="display:none">
    <input id="imgUploadInput" type="file" accept="image/*" multiple/>
</div>
<#include '../footer.ftl'>
<script src="js/fall/plugins.min.js"></script>
<script src="js/fall/main.min.js"></script>
<script src="js/lrz.bundle.js"></script>
<script src="js/fall/fall.js"></script>
<!-- Preloader -->
<script type="text/javascript">
    resizeImgs();
    $("img.lazy").lazyload();
    //<![CDATA[
    $(document).ready(function() { // makes sure the whole site is loaded
        $('#loader').fadeOut(); // will first fade out the loading animation
        $('#loader-wrapper').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
        $('body').delay(350).css({'overflow-y':'visible'});
    })
    //]]>
</script>
</body>
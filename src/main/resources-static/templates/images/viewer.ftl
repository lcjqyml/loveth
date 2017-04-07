<!DOCTYPE html>
<html>
<head>
<#include '../head.ftl'>
<title>图片viewer</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<#if importMode == "online">
    <link href="//cdn.bootcss.com/imageviewer/0.5.1/viewer.min.css" rel="stylesheet">
<#else>
    <link href="plugins/viewer/viewer-0.5.1.min.css" rel="stylesheet">
</#if>
</head>
<body style="background-color: black">
<!--[if lt IE 7]>
    <p class="browsehappy">您正在使用已经<strong>过时的</strong>浏览器。 为了更好的体验网站效果，请升级您的浏览器。</p>
<![endif]-->
<div>
    <ul class="viewerImgs" style="display:none">
        <#list imgList as pic>
            <li><img data-original="${pic.name}" alt=""></li>
        </#list>
    </ul>
</div>
<#if importMode == "online">
    <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/imageviewer/0.5.1/viewer.min.js"></script>
<#else>
    <script src="plugins/jquery/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="plugins/viewer/viewer-0.5.1.min.js"></script>
</#if>
<script src="js/common.js"></script>
<script>
    var totalSize = ${imgList?size};
    $(".viewerImgs").viewer({
        hidden: function() {
            $(".viewerImgs").on({
                'shown.viewer':  function (e) {
                   $(".viewerImgs").viewer('view', randNum(0, totalSize - 1));
                }
            }).viewer('show');
        },
        url: function() {
            return $(this).attr('data-original');
        }
    });
    $(".viewerImgs").viewer('show');
</script>
</body>
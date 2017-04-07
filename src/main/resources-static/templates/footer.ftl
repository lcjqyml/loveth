<!-- Javascripts -->
<#if importMode == "online">
    <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/jquery_lazyload/1.9.7/jquery.lazyload.min.js"></script>
    <script src="//cdn.bootcss.com/jqueryui/1.10.3/jquery-ui.min.js"></script>
    <script src="//cdn.bootcss.com/modernizr/2.6.2/modernizr.min.js"></script>
    <script src="//cdn.bootcss.com/imageviewer/0.5.1/viewer.min.js"></script>
<#else>
    <script src="plugins/jquery/jquery-3.1.1.min.js"></script>
    <script src="plugins/jquery/jquery-lazyload-1.9.7.min.js"></script>
    <script src="plugins/jquery/jquery-ui-1.10.3.min.js"></script>
    <script src="js/modernizr-2.6.2.min.js"></script>
    <script src="plugins/viewer/viewer-0.5.1.min.js"></script>
</#if>
<script src="js/common.js"></script>
<#if importMode == "online">
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<#else>
    <script src="plugins/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="plugins/bootstrap/bootbox-4.4.0.min.js"></script>
</#if>

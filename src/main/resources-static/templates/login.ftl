<!DOCTYPE html>
<html class='no-js' lang='en'>
  <head>
    <#include './head.ftl'>
    <title>后台登录</title>
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
        .fa-lg {
            vertical-align: middle;
        }
    </style>

  </head>
  <body class='login'>
    <div class='wrapper'>
      <div class='row'>
        <div class='col-lg-12'>
          <div class='brand text-center'>
            <h1>
              <div class='logo-icon'>
                <a class='fa fa-beer fa-lg' style="color:#1abc9c" href="javascript:void(0)" onclick="window.location.href='/'"></a>
              </div>
              后台登录
            </h1>
          </div>
        </div>
      </div>
      <div class='row'>
        <div class='col-lg-12'>
          <form>
            <fieldset class='text-center'>
              <legend>登录您的账号</legend>
              <div class='form-group'>
                <input class='form-control' placeholder='账号' type='text' id="username" name="username">
              </div>
              <div class='form-group'>
                <input class='form-control' placeholder='密码' type='password' id="password" name="password">
              </div>
              <div class='text-center'>
                <a class="btn btn-default" href="javascript:void(0)" onclick="login()">登录</a>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
    <#include './footer.ftl'>
    <script>
        function login() {
            var username = $("#username").val();
            var password = $("#password").val();
            if (username == "" || password == "") {
                bootbox.alert("用户名或密码不能为空！");
                return;
            }
            showLoading("登录中...");
            $.ajax({
                url: 'loginCheck.do',
                data: {
                    username: username,
                    password: password
                },
                type: "post",
                success: function(data) {
                    if (data == 'failed') {
                        bootbox.alert("用户名或密码错误！");
                    } else if (data == 'success') {
                        window.location.href="/manage.html";
                    } else {
                        bootbox.alert("异常！请联系管理员！");
                    }
                    closeLoading();
                }
            });
        }
        $(document).bind("keyup", function(e) {
            var key = event.which;
            if (key == 13) {
                login();
            }
        });
    </script>
  </body>
</html>

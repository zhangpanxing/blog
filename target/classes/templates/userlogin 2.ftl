<!DOCTYPE html>
<html lang="zh">

<head>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title!'全栈之路-登录'}</title>

    <!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
<script src="vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap core JavaScript-->
<div class="container">
<#if msg??>
<#-- s 提示消息（用户名或密码错误） -->
    <div class="alert alert-danger alert-dismissible fade show card-login mt-5 mx-auto" role="alert">
        ${msg!""}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</#if>
<#-- e 提示消息（用户名或密码错误） -->
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Full-Stack</div>
        <div class="card-body">
            <form id="id_form_login">
                <div class="form-group">
                    <label for="id_username">用户名</label>
                    <input class="form-control" id="id_username" type="text" aria-describedby="emailHelp"
                           placeholder="Username" name="username">
                </div>
                <div class="form-group">
                    <label for="id_password">密码</label>
                    <input class="form-control" id="id_password" type="password" placeholder="Password" name="password">
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" name="rememberme"> 记住密码 </label>
                    </div>
                </div>
                <a id="id_user_login_submit" class="btn btn-primary btn-block" href="javascript:void(0);">Login</a>
            </form>
            <div class="container text-center mt-3">
                <div class="row">
                    <a class="d-block small col-sm offset-6" href="userregister">现在注册</a>
                    <a class="d-block small col-sm" href="#">忘记密码</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<#-- 自定义js -->
<script src="js/f_userlogin.js"></script>
</body>

</html>

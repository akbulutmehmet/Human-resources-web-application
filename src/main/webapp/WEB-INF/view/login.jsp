<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>İnsanKaynakları | Giriş Paneli</title>
    <%@include file="includes/style.jsp"%>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#"><b>İnsan</b>Kaynakları Giriş</a>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">

            <form action="#" method="post">
                <div class="input-group mb-3">
                    <input type="email" class="form-control" placeholder="Email" id="email">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Password" id="password">
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">

                    <!-- /.col -->
                    <div class="col-4">
                        <button type="button"  class="btn btn-primary btn-block" id="loginbtn">Sign In</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>


        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->
<%@include file="includes/script.jsp"%>

<script>
    $("#loginbtn").click(function () {
        let url = "loginKontrol";
        let email = $("#email").val();
        let password = $("#password").val();
        //  console.log(email + password);
        $.ajax({
            type: "POST",
            url: url,
            data: {
                email : email,
                password : password
            },
            success: function (response) {
                Swal.fire({
                    position: 'center-center',
                    icon: response.icon,
                    title: response.title,
                    showConfirmButton: false,
                    timer: 10000
                });
                if(response.exists) {

                    window.location.href = "personelListele";

                }
            },
            dataType: "json"
        });
    });

</script></body>
</html>


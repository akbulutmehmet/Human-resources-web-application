<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Aday | Giriş Paneli</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/icon.png">

    <%@include file="includes/style.jsp"%>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#"><b>Aday</b> Giriş</a>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">

            <form action="javascript:void(0)">
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
                <div class="row mb-2">

                    <!-- /.col -->
                    <div class="col-6">
                        <button type="button"  class="btn btn-primary btn-block" id="loginbtn">Giriş YAP</button>
                    </div>
                    <!-- /.col -->
                    <!-- /.col -->
                    <div class="col-6">
                        <a href="${pageContext.request.contextPath}/aday/register"  class="btn btn-success btn-block" >Üye OL</a>
                    </div>
                    <!-- /.col -->

                </div>
                <div class="row">

                </div>
            </form>


        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->

<script src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="${pageContext.request.contextPath}/resources/js/aday.js"></script>
<script>
    $("#loginbtn").click(function () {
        let url = "loginKontrol";
        let email = $("#email").val();
        let password = $("#password").val();
        if(email &&password) {
            $.ajax({
                type: "POST",
                url: url,
                data: {
                    email : email,
                    password : password
                },
                success: function (response) {
                    Swal.fire({
                        position: 'center',
                        icon: response.icon,
                        title: response.title,
                        showConfirmButton: true,
                        timer: 3000
                    });
                    setTimeout(function () {
                        if(response.exists) {

                            window.location.href = "${pageContext.request.contextPath}/aday/adayIslemleri";

                        }
                    },1000)
                },
                dataType: "json"
            });
        }
        else {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Zorunlu alanları doldurunuz',
                showConfirmButton: true,
                timer: 3000
            });
        }
    });

</script></body>
</html>


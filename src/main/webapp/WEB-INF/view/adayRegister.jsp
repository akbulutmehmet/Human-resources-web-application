<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Aday | Giriş Paneli</title>
    <%@include file="includes/style.jsp"%>
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="#"><b>Aday</b>Kayıt OL</a>
    </div>

    <div class="card">
        <div class="card-body register-card-body">
            <form action="javascript:void(0)">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="adayAd" name="adayAd" placeholder="Ad" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="adaySoyad" name="adaySoyad" placeholder="Soyad" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="number" class="form-control" id="adayTc" name="adaySoyad" placeholder="Tc" onfocusout="adayTcKontrol(this.value)" data-url="${pageContext.request.contextPath}/aday/adayTcKontrol" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>

                <div class="input-group mb-3">
                    <select name="adayCinsiyet" id="adayCinsiyet" class="form-control" required>
                        <option value="0">KADIN</option>
                        <option value="1">ERKEK</option>
                    </select>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fa fa-user"></span>
                        </div>
                    </div>
                </div>
                <p>Doğum Tarihiniz</p>
                <div class="input-group mb-3">

                    <input type="date" class="form-control" id="adayDogumTarihi" name="adayDogumTarihi" placeholder="Doğum Tarihiniz" required>

                </div>
                <div class="input-group mb-3">
                    <input type="email" class="form-control" id="adayEposta" name="adayEposta" placeholder="Email" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" id="adaySifre" name="adaySifre" placeholder="Şifre" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- /.col -->
                    <div class="col-6">
                        <button type="button" id="btnAdayRegister" class="btn btn-success btn-block">Kayıt OL</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>
        </div>
        <!-- /.form-box -->
    </div><!-- /.card -->
</div>
<!-- /.register-box -->

<script src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="${pageContext.request.contextPath}/resources/js/aday.js"></script>
<script>

    $("#btnAdayRegister").click(function () {
        let url = "${pageContext.request.contextPath}/aday/adayRegister";
        let adayAd = $("#adayAd").val();
        let adaySoyad = $("#adaySoyad").val();
        let adayEposta = $("#adayEposta").val();
        let adaySifre = $("#adaySifre").val();
        let adayCinsiyet = $("#adayCinsiyet").val();
        let adayTc = $("#adayTc").val();
        let adayDogumTarihi = $("#adayDogumTarihi").val();
        if(adayAd &&adaySoyad && adayEposta && adaySifre && adayCinsiyet && adayTc && adayDogumTarihi) {
            $.ajax({
                type: "POST",
                url: url,
                data: {
                    adayAd : adayAd,
                    adaySoyad : adaySoyad,
                    adayEposta : adayEposta,
                    adaySifre : adaySifre,
                    adayCinsiyet : adayCinsiyet,
                    adayTc:adayTc,
                    adayDogumTarihi:adayDogumTarihi
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
                        if(response.exist) {

                            window.location.href = "${pageContext.request.contextPath}/aday/";

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


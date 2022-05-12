<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 Error</title>
    <%@include file="includes/style.jsp"%>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2">

        </div>
        <div class="col-md-8">
            <img src="${pageContext.request.contextPath}/resources/img/404.png" alt="404 Görsel" class="img-fluid"
            >
            <h6 class="display-4 custom_title">Görüntülemeye Çalıştığınız Sayfa Bulunamadı.
            </h6>
            <p>
                Sayfa yayından kaldırılmış olabilir veya erişmeye çalıştığınız sayfanın adresini hatalı yazmış olabilirsiniz.
            </p>
        </div>
        <div class="col-md-2">

        </div>
    </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header_aday.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <div class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1 class="m-0">${title}</h1>
        </div><!-- /.col -->
        <div class="col-sm-6">

        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.container-fluid -->
  </div>
  <!-- /.content-header -->

  <!-- Main content -->
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-8">
          <div class="card">
           <div class="card-body">
             <form action="${pageContext.request.contextPath}/aday/adaySerftifikaKaydet" method="post" enctype="multipart/form-data">
               <input type="hidden" name="adaySertifikaId" value="${adaySerftifika.adaySertifikaId}">
               <div class="form-gruop mb-1">
                 <label for="adaySerftifikaAd">Serftika Adını Giriniz</label>
                 <input class="form-control" type="text" name="adaySerftifikaAd" id="adaySerftifikaAd"  placeholder="Serftika Adını Giriniz" value="${adaySerftifika.adaySerftifikaAd}" required>
               </div>

               <div class="form-gruop mb-1">
                 <label for="adaySerftifikaUrl">Serftika Doğrulama Adresi</label>
                 <input class="form-control" type="url" name="adaySerftifikaUrl" id="adaySerftifikaUrl"  placeholder="Serftika Doğrulama Adresi" value="${adaySerftifika.adaySerftifikaUrl}" required>
               </div>


               <div class="form-gruop mb-1">
                 <label for="adaySerftifikaTarih">Serftifika Tarihini Giriniz</label>
                 <input class="form-control" type="date" name="adaySerftifikaTarih" id="adaySerftifikaTarih" max="" value="${adaySerftifika.adaySerftifikaTarihi}"  required>
               </div>

               <div class="form-gruop mb-1">
                 <label for="serftifikaFile">Serftika</label>
                 <input class="form-control" type="file" accept="application/pdf" name="serftifikaFile" id="serftifikaFile"  >
               </div>

               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" type="submit" >GÜNCELLE</button>
               </div>
             </form>
           </div>
          </div>
        </div>
      </div>
      <!-- /.row -->
    </div><!-- /.container-fluid -->
  </div>
  <!-- /.content -->
</div>
<!-- /.content-wrapper -->


<%@include file="includes/aday_footer.jsp"%>



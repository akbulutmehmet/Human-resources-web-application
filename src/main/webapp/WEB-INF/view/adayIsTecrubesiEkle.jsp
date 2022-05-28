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
             <form action="javascript:void(0)">
               <div class="form-gruop mb-1">
                 <label for="isyeriAdi">İşyeri Adını Giriniz</label>
                 <input class="form-control" type="text" name="isyeriAdi" id="isyeriAdi"  placeholder="İşyeri Adını Giriniz">
               </div>
               <div class="form-gruop mb-1">
                 <label for="gorevAdi">Görev Adını Giriniz</label>
                 <input class="form-control" type="text" name="gorevAdi" id="gorevAdi"  placeholder="Görev Adını Giriniz">
               </div>
               <div class="form-gruop mb-1">
                 <label for="isBaslangicTarihi">İş Başlangıç Tarihini Giriniz</label>
                 <input class="form-control" type="date" name="isBaslangicTarihi" id="isBaslangicTarihi" max="" >
               </div>

               <div class="form-gruop mb-1">
                 <label for="isBitisTarihi">İş Bitiş Tarihini Giriniz</label>
                 <input class="form-control" type="date" name="isBitisTarihi" id="isBitisTarihi" >
               </div>
               <div class="form-gruop mb-1">
                 <label for="gorevliAdi">Yetkilinin İsmini Giriniz</label>
                 <input class="form-control" type="text" name="gorevliAdi" id="gorevliAdi" placeholder="Yetkilinin İsmini Giriniz" >
               </div>

               <div class="form-gruop mb-1">
                 <label for="gorevliUnvan">Yetkilinin Unvanını Giriniz</label>
                 <input class="form-control" type="text" name="gorevliUnvan" id="gorevliUnvan" placeholder="Yetkilinin Unvanını Giriniz" >
               </div>
               <div class="form-gruop mb-1">
                 <label for="gorevliTelefon">Yetkilinin Telefonunu Giriniz</label>
                 <input class="form-control" oninput="maxLengthCheck(this)" type="number" maxlength="11" name="gorevliTelefon" id="gorevliTelefon" placeholder="Yetkilinin Telefonunu Giriniz" >
               </div>
               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" type="button" id="btnAdayIsTecrubesiEkle">EKLE</button>
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



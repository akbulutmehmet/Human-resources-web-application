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
             <form action="${pageContext.request.contextPath}/aday/adayEgitimKaydet" method="post" enctype="multipart/form-data">
               <div class="form-gruop mb-1" class="form-control" >
                 <label for="egitimTuru">Eğitim Türünü Seçiniz</label>
                 <select class="form-control" name="egitimTuru" id="egitimTuru" required>
                   <option value="YÜKSEKLİSANS">YÜKSEKLİSANS</option>
                   <option value="ÜNİVERSİTE">ÜNİVERSİTE</option>
                   <option value="LİSE">LİSE</option>
                   <option value="İLKOKUL">İLKOKUL</option>
                 </select>
               </div>
               <div class="form-gruop mb-1">
                 <label for="okulAdi">Okul Adını Giriniz</label>
                 <input class="form-control" type="text" name="okulAdi" id="okulAdi"  placeholder="Okul Adını Giriniz" required>
               </div>

               <div class="form-gruop mb-1">
                 <label for="bolumAdi">Bölüm Adını Giriniz</label>
                 <input class="form-control" type="text" name="bolumAdi" id="bolumAdi"  placeholder="Bölüm Adını Giriniz" required>
               </div>


               <div class="form-gruop mb-1">
                 <label for="baslangicTarihi">Okul Başlangıç Tarihini Giriniz</label>
                 <input class="form-control" type="date" name="baslangicTarihi" id="baslangicTarihi" max=""  required>
               </div>

               <div class="form-gruop mb-1">
                 <label for="bitisTarihi">Okul Bitiş Tarihini Giriniz</label>
                 <input class="form-control" type="date" name="bitisTarihi" id="bitisTarihi" >
               </div>
               <div class="form-gruop mb-1">
                 <label for="diplomaFile">Diploma</label>
                 <input class="form-control" type="file" accept="application/pdf" name="diplomaFile" id="diplomaFile" >
               </div>

               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" type="submit" >EKLE</button>
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



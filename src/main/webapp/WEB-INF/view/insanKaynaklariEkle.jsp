<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header.jsp"%>

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
                 <label for="isim">İK İsmini Giriniz</label>
                 <input class="form-control" type="text" name="isim" id="isim" placeholder="İK İsmini Giriniz" required>
               </div>
               <div class="form-gruop mt-1">
                 <label for="soyisim">İK Soyisim Giriniz</label>
                 <input class="form-control" type="text" name="soyisim" id="soyisim" placeholder="İK Soyisim Giriniz" required>
               </div>
               <div class="form-gruop mt-1">
                 <label for="email">İK Email Giriniz</label>
                 <input class="form-control" type="email" name="email" id="email" placeholder="İK Email Giriniz" required>
               </div>
               <div class="form-gruop mt-1">
                 <label for="password">İK Şifre Giriniz</label>
                 <input class="form-control" type="password" minlength="6" name="password" id="password" placeholder="İK Şifre Giriniz" required >
               </div>
               <div class="form-gruop mt-1">
                 <button type="button" id="btnIkKaydet" class="btn btn-outline-success">EKLE</button>
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


<%@include file="includes/footer.jsp"%>



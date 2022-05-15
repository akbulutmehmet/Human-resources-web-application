<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
               <div class="form-gruop mt-1">
                 <label for="personelDepartman">Personel Departman Seçiniz</label>
                 <select name="personelDepartman" id="personelDepartman" class="form-control"  data-url="${pageContext.request.contextPath}" onchange="gorevGetir(this.value)">
                   <option value="0" selected id="bosDepartman">LÜTFEN DEPARTMAN SEÇİMİ YAPINIZ</option>
                   <c:forEach var="departman" items="${departmanList}" >

                       <option value="${departman.departmanId}">${departman.departmanAdi}</option>

                   </c:forEach>
                 </select>
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelGorevId">Personel Görev Seçiniz</label>
                 <select name="personelGorevId" id="personelGorevId" class="form-control" data-url="${pageContext.request.contextPath}" onchange="personelGetir(this.value)">
                      <option value="0" id="bosGorev" selected>LÜTFEN GÖREV SEÇİMİ YAPINIZ</option>
                 </select>
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelId">Personel Seçiniz</label>
                 <select name="personelId" id="personelId" class="form-control">
                   <option value="0" id="bosPersonel" selected>LÜTFEN PERSONEL SEÇİMİ YAPINIZ</option>
                 </select>
               </div>
               <div class="form-gruop mt-1">
                 <label for="izinBaslangicTarihi">Personel İzin Başlangıç Tarihi</label>
                 <input type="date" name="izinBaslangicTarihi" id="izinBaslangicTarihi" class="form-control">
               </div>
               <div class="form-gruop mt-1">
                 <label for="izinBitisTarihi">Personel İzin Bitiş Tarihi</label>
                 <input type="date" name="izinBitisTarihi" id="izinBitisTarihi" class="form-control">
               </div>
               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" id="btnIzinliPersonelEkle" type="button">EKLE</button>
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



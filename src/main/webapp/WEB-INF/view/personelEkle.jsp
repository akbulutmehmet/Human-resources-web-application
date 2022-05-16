<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                 <label for="personelAd">Personel İsmini Giriniz</label>
                 <input class="form-control" type="text" name="personelAd" id="personelAd" placeholder="Personel İsmini Giriniz">
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelSoyad">Personel Soyisim Giriniz</label>
                 <input class="form-control" type="text" name="personelSoyad" id="personelSoyad" placeholder="Personel Soyisim Giriniz">
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelTc">Personel TC Giriniz</label>
                 <input class="form-control" type="number" name="personelTc" id="personelTc" placeholder="Personel TC Giriniz" maxlength="11">
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelMaas">Personel Maaş Giriniz</label>
                 <input class="form-control" type="number" name="tc" id="personelMaas" placeholder="Personel Maaş Giriniz">
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelCinsiyet">Personel Cinsiyet Seçiniz</label>
                 <select name="personelCinsiyet" id="personelCinsiyet" class="form-control">
                   <option value="1">ERKEK</option>
                   <option value="0">KADIN</option>
                 </select>
               </div>
               <div class="form-gruop mt-1">
                 <label for="isBaslangicTarihi">Personel İş Başlangıç Tarihi</label>
                 <input type="date" name="isBaslangicTarihi" id="isBaslangicTarihi" class="form-control" data-date="" data-date-format="DD MMMM YYYY"   >
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelDepartman">Personel Departman Seçiniz</label>

                 <select name="personelDepartman" id="personelDepartman" class="form-control" data-url="${pageContext.request.contextPath}"   onchange="gorevGetir(this.value)">
                   <c:forEach var="departman" items="${departmanList}" >
                       <option value="${departman.departmanId}" selected>${departman.departmanAdi}</option>

                   </c:forEach>

                 </select>
               </div>
               <div class="form-gruop mt-1">
                 <label for="personelGorevId">Personel Görev Seçiniz</label>
                 <select name="personelGorevId" id="personelGorevId" class="form-control">
                   <option value="0" selected >Lütfen Görev Seçimi Yapınız</option>
                 </select>
               </div>
               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" type="button" id="btnPersonelKaydet">EKLE</button>
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



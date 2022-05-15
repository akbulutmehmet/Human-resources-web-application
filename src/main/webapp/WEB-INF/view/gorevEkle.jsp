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

               <div class="form-gruop mb-1">
                 <label for="departmanId">Departman Seçimini Yapınız</label>
                 <select name="departmanId" id="departmanId" class="form-control">
                   <c:forEach var="departman" items="${departmanList}" >

                     <option value="${departman.departmanId}">${departman.departmanAdi}</option>

                   </c:forEach>

                 </select>
               </div>
               <div class="form-gruop mb-1">
                 <label for="gorevAdi">Görev İsmini Giriniz</label>
                 <input type="text" name="gorevAdi" id="gorevAdi" title="gorevAdi" class="form-control">
               </div>
               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" type="button" id="btnGorevKaydet">EKLE</button>
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



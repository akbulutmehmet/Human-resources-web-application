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
               <input type="hidden" name="departmanId" id="departmanId" value="${departman.departmanId}">
               <input type="hidden" name="dataUrl" id="dataUrl" value="${pageContext.request.contextPath}/departmanUpdate">
               <input type="hidden" name="redirectUrl" id="redirectUrl" value="${pageContext.request.contextPath}/departmanListele">
               <div class="form-gruop mb-1">
                 <label for="departmanAdi">Departman İsmini Giriniz</label>
                 <input class="form-control" type="text" name="departmanAdi" id="departmanAdi" value="${departman.departmanAdi}" placeholder="Departman İsmini Giriniz">
               </div>

               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" data-id="${departman.departmanId}" id="btnDepartmanGuncelle" type="button">GÜNCELLE</button>
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



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
               <input type="hidden" name="gorevId" id="gorevId" value="${gorev.gorevId}">
               <input type="hidden" name="dataUrl" id="dataUrl" value="${pageContext.request.contextPath}/gorevUpdate">
               <input type="hidden" name="redirectUrl" id="redirectUrl" value="${pageContext.request.contextPath}/gorevListele">
               <div class="form-gruop mb-1">
                 <label for="departmanId">Departman Seçimini Yapınız</label>
                 <select name="departmanId" id="departmanId" class="form-control">

                   <c:forEach var="departman" items="${departmanList}" >
                    <c:if test="${gorev.departman.departmanId == departman.departmanId}" >
                      <option value="${gorev.departman.departmanId}" selected>${gorev.departman.departmanAdi}</option>
                    </c:if>
                     <c:if test="${gorev.departman.departmanId != departman.departmanId}" >
                       <option value="${departman.departmanId}">${departman.departmanAdi}</option>
                     </c:if>


                   </c:forEach>


                 </select>
               </div>
               <div class="form-gruop mb-1">
                 <label for="gorevAdi">Görev İsmini Giriniz</label>
                 <input value="${gorev.gorevAdi}" type="text" name="gorevAdi" title="gorevAdi" id="gorevAdi" class="form-control">
               </div>
               <div class="form-gruop mt-1">
                 <button class="btn btn-outline-success" type="button" id="btnGorevUpdate">GÜNCELLE</button>
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



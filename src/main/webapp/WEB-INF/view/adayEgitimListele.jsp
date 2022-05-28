<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="includes/header_aday.jsp"%>
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
                       <div class="row">
                           <div class="col-6">

                           </div>
                           <div class="col-6">
                               <a href="${pageContext.request.contextPath}/aday/adayEgitimEkle" class="btn btn-outline-info m-auto ">
                                   <i class="fa fa-plus"></i>
                                   YENİ EĞİTİM EKLE</a>
                           </div>
                       </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                   <div class="col-md-12">
                    <div class="card">
                      <div class="card-body">
                          <table class="table table-hover table-bordered" id="dataTables">
                              <thead>
                              <tr>
                                  <th>Okul Ad</th>
                                  <th>Eğitim Türü</th>
                                  <th>Başlangıç Tarihi</th>
                                  <th>Bitiş Tarihi</th>
                                  <th>İşlemler</th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach var="adayEgitim" items="${adayEgitimList}" >
                                 <tr>
                                     <td>
                                         <c:out value=" ${adayEgitim.okulAdi}"></c:out>
                                     </td>
                                     <td>
                                         <c:out value=" ${adayEgitim.egitimTuru}"></c:out>
                                     </td>
                                     <td>
                                         <c:out value=" ${adayEgitim.baslangicTarihi}"></c:out>
                                     </td>
                                     <td>
                                         <c:out value=" ${adayEgitim.bitisTarihi}"></c:out>
                                     </td>
                                     <td>
                                         <a href="${pageContext.request.contextPath}/aday/adayEgitimGuncelle/${adayEgitim.adayEgitimId}" class="btn  btn-info" data-id="#1">GÜNCELLE</a>
                                         <button class="btn  btn-blok btn-danger btnAdayEgitimSil" type="button"   data-id="${adayEgitim.adayEgitimId}">SİL</button>
                                     </td>
                                 </tr>


                              </c:forEach>

                              </tbody>
                          </table>
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



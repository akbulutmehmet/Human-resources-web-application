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
                       <div class="row">
                           <div class="col-6">

                           </div>
                           <div class="col-6">
                               <a href="${pageContext.request.contextPath}/personelEkle" class="btn btn-outline-info m-auto ">
                                   <i class="fa fa-plus"></i>
                                   YENİ PERSONEL EKLE</a>
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
                                  <th>İD</th>
                                  <th>Ad</th>
                                  <th>Soyad</th>
                                  <th>TC</th>
                                  <th>Cinsiyet</th>
                                  <th>İş Başlangıç Tarihi</th>
                                  <th>Departman</th>
                                  <th>Görev</th>
                                  <th>İşlemler</th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach var="personel" items="${personelList}" >
                                  <tr>
                                      <td><c:out value=" ${personel.personelId}"></c:out></td>
                                      <td><c:out value=" ${personel.personelAd}"></c:out></td>
                                      <td><c:out value=" ${personel.personelSoyad}"></c:out></td>
                                      <td><c:out value=" ${personel.personelTc}"></c:out></td>
                                      <td>

                                          <c:if test="${personel.personelCinsiyet == 1}" >
                                              ERKEK
                                          </c:if>
                                          <c:if test="${personel.personelCinsiyet == 0}" >
                                              KADIN
                                          </c:if>

                                      </td>
                                      <td><c:out value=" ${personel.isBaslangicTarihi}"></c:out></td>
                                      <td><c:out value=" ${personel.gorev.departman.departmanAdi}"></c:out></td>
                                      <td><c:out value=" ${personel.gorev.gorevAdi}"></c:out></td>
                                      <td>
                                          <a href="${pageContext.request.contextPath}/personelGuncelle/${personel.personelId}" class="btn  btn-info" data-id="#1">GÜNCELLE</a>
                                          <button class="btn  btn-blok btn-danger btnPersonelSil" type="button"  data-id="${personel.personelId}">SİL</button>
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


    <%@include file="includes/footer.jsp"%>



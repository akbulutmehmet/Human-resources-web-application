<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                       <div class="row">
                           <div class="col-6">
                           </div>
                           <div class="col-6">

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
                          <form action="javascript:void(0)">
                              <div class="form-gruop mb-1">
                                  <label for="adayAd">Adınız: </label>
                                  <input class="form-control" type="text" name="adayAd" id="adayAd"  placeholder="Adınız" value="${aday.adayAd}">
                              </div>
                              <div class="form-gruop mb-1">
                                  <label for="adaySoyad">Soyadınız: </label>
                                  <input class="form-control" type="text" name="adaySoyad" id="adaySoyad"  placeholder="Soyadınız:" value="${aday.adaySoyad}">
                              </div>
                              <div class="form-gruop mb-1">
                                  <label for="adayCinsiyet">Cinsiyetiniz: </label>
                                  <select name="adayCinsiyet" id="adayCinsiyet" class="form-control">
                                      <c:set var="cinsiyetErkek" value="1"></c:set>
                                      <c:if test="${aday.adayCinsiyet == cinsiyetErkek}" >
                                          <option value="1" selected>ERKEK</option>
                                          <option value="0">KADIN</option>
                                      </c:if>
                                      <c:if test="${aday.adayCinsiyet != cinsiyetErkek}" >
                                          <option value="1" >ERKEK</option>
                                          <option value="0" selected>KADIN</option>
                                      </c:if>

                                  </select>
                              </div>
                              <div class="form-gruop mb-1">
                                  <label for="adayDogumTarihi">Doğum Tarihiniz: </label>
                                  <input type="date" class="form-control" id="adayDogumTarihi" name="adayDogumTarihi" placeholder="Doğum Tarihiniz" value="${aday.adayDogumTarihi}" required>
                              </div>
                              <div class="form-gruop mb-1">
                                  <label for="adayEposta">Eposta Adresiniz: </label>
                                  <input type="text" class="form-control" id="adayEposta" name="adayEposta" placeholder="Doğum Tarihiniz" value="${aday.adayEposta}" required>
                              </div>
                              <div class="form-gruop mb-1">
                                  <label for="adaySifre">Şifreniz: </label>
                                  <input type="password" class="form-control" id="adaySifre" name="adaySifre" placeholder="Şifreniz"  required>
                              </div>
                              <div class="form-gruop mt-1">
                                  <button class="btn btn-outline-success" type="button" data-id="${aday.adayId}" id="btnAdayGuncelle">GÜNCELLE</button>
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



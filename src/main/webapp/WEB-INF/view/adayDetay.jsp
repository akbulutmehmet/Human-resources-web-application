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
                        <div class="row">
                            <div class="col-md-12">
                                <h6  class="display-4 custom-headline-30">Kişisel Bilgiler</h6>
                            </div>
                            <div class="col-md-12">
                                <table class="table table-bordered table-hover ">
                                    <thead>
                                        <tr>
                                            <th>Ad</th>
                                            <th>Soyad</th>
                                            <th>Cinsiyet</th>
                                            <th>Tc</th>
                                            <th>Dogum Tarihi</th>
                                            <th>Eposta</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>${aday.adayAd}</td>
                                            <td>${aday.adaySoyad}</td>
                                            <td>
                                                <c:if test="${aday.adayCinsiyet == 1}" >
                                                ERKEK
                                                </c:if>
                                                <c:if test="${aday.adayCinsiyet == 0}" >
                                                    KADIN
                                                </c:if>
                                            </td>

                                            <td>
                                                ${aday.adayTc}
                                            </td>
                                            <td>
                                                ${aday.adayDogumTarihi}
                                            </td>
                                            <td>
                                                ${aday.adayEposta}
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                          <br>
                        <div class="row">
                              <div class="col-md-12">
                                  <h6  class="display-4 custom-headline-30">İş Tecrübe Bilgileri</h6>
                              </div>
                              <div class="col-md-12">
                                  <table class="table table-bordered table-hover ">
                                      <thead>
                                      <tr>
                                          <th>İşyeri Adı</th>
                                          <th>Görev Adi</th>
                                          <th>İş Başlangıç Tarihi</th>
                                          <th>İş Bitiş Tarihi</th>
                                          <th>Sorumlu Adı</th>
                                          <th>Sorumlu Ünvanı</th>
                                          <th>Sorumlu Telefonu</th>
                                      </tr>
                                      </thead>
                                      <tbody>
                                      <c:forEach var="tecrube" items="${adayIsTecrubeList}" >
                                          <tr>
                                              <td>
                                                  <c:out value=" ${tecrube.isyeriAdi}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${tecrube.gorevAdi}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${tecrube.isBaslangicTarihi}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${tecrube.isBitisTarihi}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${tecrube.gorevliAdi}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${tecrube.gorevliUnvan}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${tecrube.gorevliTelefon}"></c:out>
                                              </td>
                                          </tr>


                                      </c:forEach>


                                      </tbody>
                                  </table>
                              </div>
                          </div>
                          <br>
                          <div class="row">
                              <div class="col-md-12">
                                  <h6  class="display-4 custom-headline-30">Eğitim Bilgileri</h6>
                              </div>
                              <div class="col-md-12">
                                  <table class="table table-bordered table-hover ">
                                      <thead>
                                      <tr>
                                          <th>Okul Adı</th>
                                          <th>Bolüm Adı</th>
                                          <th>Eğitim Türü</th>
                                          <th>Başlangıç Tarihi</th>
                                          <th>Bitiş Tarihi</th>
                                          <th>Diploma</th>
                                      </tr>
                                      </thead>
                                      <tbody>

                                      <c:forEach var="adayEgitim" items="${adayEgitimList}" >
                                          <tr>
                                              <td>
                                                  <c:out value=" ${adayEgitim.okulAdi}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${adayEgitim.bolumAdi}"></c:out>

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
                                                  <c:if test="${adayEgitim.fileName != null}">
                                                      <a href="${pageContext.request.contextPath}/files/diplomalar/${adayEgitim.fileName}">Diploma</a>
                                                  </c:if>
                                              </td>
                                          </tr>


                                      </c:forEach>


                                      </tbody>
                                  </table>
                              </div>
                          </div>
                          <br>
                          <div class="row">
                              <div class="col-md-12">
                                  <h6  class="display-4 custom-headline-30">Serftifika Bilgileri</h6>
                              </div>
                              <div class="col-md-12">
                                  <table class="table table-bordered table-hover ">
                                      <thead>
                                      <tr>
                                          <th>Serftifika Ad</th>
                                          <th>Serftifika Url</th>
                                          <th>Serftifika Tarihi</th>
                                          <th>Serftifika</th>
                                      </tr>
                                      </thead>
                                      <tbody>

                                      <c:forEach var="adaySerftifika" items="${adaySerftifikaList}" >
                                          <tr>
                                              <td>
                                                  <c:out value=" ${adaySerftifika.adaySerftifikaAd}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${adaySerftifika.adaySerftifikaUrl}"></c:out>
                                              </td>
                                              <td>
                                                  <c:out value=" ${adaySerftifika.adaySerftifikaTarihi}"></c:out>
                                              </td>

                                              <td>
                                                  <c:if test="${adaySerftifika.fileName != null}">
                                                      <a href="${pageContext.request.contextPath}/files/serftifikalar/${adaySerftifika.fileName}">Serftifika</a>
                                                  </c:if>                                              </td>
                                          </tr>


                                      </c:forEach>


                                      </tbody>
                                  </table>
                              </div>
                          </div>
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



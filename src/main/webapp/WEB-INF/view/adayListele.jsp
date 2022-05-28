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
                                  <th>Tc</th>
                                  <th>Cinsiyet</th>
                                  <th>Eposta</th>
                                  <th>Doğum Tarihi</th>
                                  <th>İşlemler</th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach var="aday" items="${adayList}" >
                                  <tr>
                                      <td><c:out value=" ${aday.adayId}"></c:out></td>
                                      <td><c:out value=" ${aday.adayAd}"></c:out></td>
                                      <td><c:out value=" ${aday.adaySoyad}"></c:out></td>
                                      <td><c:out value=" ${aday.adayTc}"></c:out></td>
                                      <td>
                                          <c:if test="${aday.adayCinsiyet == 1}">
                                              ERKEK
                                          </c:if>

                                          <c:if test="${aday.adayCinsiyet == 0}">
                                              KADIN
                                          </c:if>
                                      </td>
                                      <td><c:out value=" ${aday.adayEposta}"></c:out></td>
                                      <td><c:out value=" ${aday.adayDogumTarihi}"></c:out></td>

                                      <td>
                                          <a href="${pageContext.request.contextPath}/adayDetay/${aday.adayId}" class="btn  btn-info" data-id="#1">DETAY</a>
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



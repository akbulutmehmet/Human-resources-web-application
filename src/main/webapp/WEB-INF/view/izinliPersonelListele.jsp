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
                               <a href="${pageContext.request.contextPath}/izinliPersonelEkle" class="btn btn-outline-info m-auto ">
                                   <i class="fa fa-plus"></i>
                                   YENİ İZİN EKLE</a>
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
                                  <th>Departman</th>
                                  <th>Görev</th>
                                  <th>İzin Başlangıç Tarihi</th>
                                  <th>İzin Bitiş Tarihi</th>
                                  <th>İşlemler</th>
                              </tr>
                              </thead>
                              <tbody>
                              <tr>
                                  <td>#1</td>
                                  <td>Mehmet</td>
                                  <td>Akbulut</td>
                                  <td>Boş İşler</td>
                                  <td>Boş İşler Müdürü</th>
                                  <th>14.06.2022</th>
                                  <th>30.06.2022</th>
                                  <td>
                                      <a href="${pageContext.request.contextPath}/izinliPersonelGuncelle/${"1"}" class="btn btn-md btn-info mb-1" data-id="#1">GÜNCELLE</a>
                                      <button class="btn btn-md btn-danger mt-1" data-id="#1">SİL</button>
                                  </td>
                              </tr>
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



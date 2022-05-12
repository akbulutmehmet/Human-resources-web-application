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
                            <form action="">
                                <div class="form-gruop mt-1">
                                    <label for="departman">Personel Departman Seçiniz</label>
                                    <select name="departman" id="departman" class="form-control">
                                        <option value="1">Yazılım</option>
                                        <option value="2">Temizlik</option>
                                    </select>
                                </div>
                                <div class="form-gruop mt-1">
                                    <label for="personel_id">Personel Seçiniz</label>
                                    <select name="personel_id" id="personel_id" class="form-control">
                                        <option value="1">Mehmet Akbulut</option>
                                    </select>
                                </div>
                                <div class="form-gruop mt-1">
                                    <label for="izinbaslangic">Personel İzin Başlangıç Tarihi</label>
                                    <input type="date" name="izinbaslangic" id="izinbaslangic" class="form-control">
                                </div>
                                <div class="form-gruop mt-1">
                                    <label for="izinbitis">Personel İzin Bitiş Tarihi</label>
                                    <input type="date" name="izinbitis" id="izinbitis" class="form-control">
                                </div>
                                <div class="form-gruop mt-1">
                                    <button class="btn btn-outline-success">EKLE</button>
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



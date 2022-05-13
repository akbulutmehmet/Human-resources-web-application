<%@ page import="com.bm470.model.InsanKaynaklari" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="tr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${title}</title>

  <%@include file="style.jsp"%>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">

    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <a href="${pageContext.request.contextPath}/logout" class="btn  btn-outline-danger"><i class="fa fa-sign-out-alt"></i>ÇIKIŞ YAP</a>

    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${pageContext.request.contextPath}" class="brand-link">
      <span class="brand-text font-weight-light">İnsan Kaynakları</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
        </div>
        <div class="info">


          <a href="${pageContext.request.contextPath}" class="d-block"><%= session.getAttribute("adsoyad")%></a>
        </div>
      </div>

      <%@include file="left_menu.jsp"%>
    </div>
    <!-- /.sidebar -->
  </aside>
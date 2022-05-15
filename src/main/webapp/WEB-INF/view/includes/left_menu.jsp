<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="mt-2">
  <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/personelListele" class="nav-link">
        <i class="nav-icon fas fa-people-arrows"></i>
        <p>
          Personel İşlemleri
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/izinliPersonelListele" class="nav-link"

      >
        <i class="nav-icon fas fa-people-carry"></i>
        <p>
          İzinli Personel İşlemleri
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/departmanListele" class="nav-link ">
        <i class="nav-icon fas fa-tachometer-alt"></i>
        <p>
          Departman İşlemleri
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/gorevListele" class="nav-link ">
        <i class="nav-icon fas fa-user-md"></i>
        <p>
          Görev İşlemleri
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/insanKaynaklariListele" class="nav-link ">
        <i class="nav-icon fas fa-user-alt"></i>
        <p>
          İnsan Kaynakları İşlemleri
        </p>
      </a>

    </li>
  </ul>
</nav>

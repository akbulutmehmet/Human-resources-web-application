<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="mt-2">
  <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/aday/adayIslemleri" class="nav-link">
        <i class="nav-icon fas fa-people-arrows"></i>
        <p>
          Kişisel Bilgilerim
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/aday/adayIsTecrubeListele" class="nav-link"

      >
        <i class="nav-icon fas fa-people-carry"></i>
        <p>
          İş Tecrübelerim
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/aday/adayEgitimListele" class="nav-link ">
        <i class="nav-icon fas fa-tachometer-alt"></i>
        <p>
          Eğitim Bilgilerim
        </p>
      </a>

    </li>
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/aday/serftikaBilgileri" class="nav-link ">
        <i class="nav-icon fas fa-user-md"></i>
        <p>
          Serftika Bilgilerim
        </p>
      </a>

    </li>

  </ul>
</nav>

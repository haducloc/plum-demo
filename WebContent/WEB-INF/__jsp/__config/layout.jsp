<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fx" uri="http://www.appslandia.com/jstl/functions"%>
<!doctype html>
<html lang="${ctx.languageId}">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Loc Ha">
<title>@{page.title}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/css/app.css">
</head>
<body>

  <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
      <t:actionLink clazz="navbar-brand" action="index" controller="main">${ctx.escCt('app.name')}</t:actionLink>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="adminDrp" data-bs-toggle="dropdown" aria-expanded="false">${ctx.escCt('nav.administrations')}</a>
            <ul class="dropdown-menu" aria-labelledby="adminDrp">
              <li>
                <t:actionLink clazz="dropdown-item" action="index" controller="user">${ctx.escCt('nav.manage_users')}</t:actionLink>
              </li>
            </ul>
          </li>
        </ul>

        <ul class="navbar-nav d-flex">
          <c:if test="${empty pageContext.request.userPrincipal}">
            <li class="nav-item">
              <t:actionLink clazz="nav-link" action="login" controller="auth">${ctx.escCt('label.login')}</t:actionLink>
            </li>
          </c:if>

          <c:if test="${not empty pageContext.request.userPrincipal}">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="authDrp" data-bs-toggle="dropdown" aria-expanded="false">
                <t:displayName />
              </a>
              <ul class="dropdown-menu" aria-labelledby="authDrp">
                <li>
                  <t:actionLink clazz="dropdown-item" action="logout" controller="auth">${ctx.escCt('label.logout')}</t:actionLink>
                </li>
                <li>
                  <t:actionLink clazz="dropdown-item" action="changepwd" controller="auth">${ctx.escCt('label.change_password')}</t:actionLink>
                </li>
              </ul>
            </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>

  <main role="main">

    <div class="container my-5">
      <div class="row">
        <div class="col text-center">
          <h1 class="fs-2">@{page.title}</h1>
        </div>
      </div>
    </div>

    <c:if test="${empty requestScope['jakarta.servlet.error.servlet_name'] and not empty messages}">
      <div class="container mb-4">
        <div class="messages-wrapper">
          <t:messages clazz="mb-0 px-4 py-2 rounded" />
        </div>
      </div>
    </c:if>

    <!-- @doBody -->
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/static/js/app.js"></script>

  <script type="text/javascript">
      initBrowserFeatures();
    </script>

  <!-- @jsSection? -->
</body>
</html>

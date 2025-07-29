<%@ page language="java" contentType="text/html; charset=utf-8" session="false" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>
<!doctype html>
<html lang="${ctx.languageId}">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="Appslandia Plum MVC Framework Demo">
<meta name="author" content="Loc Ha" />
<title>${fn:res(ctx, "page.login_page")}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="${ctx.contextPath}/static/css/app.css" rel="stylesheet" />

</head>
<body>

  <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
      <t:actionLink clazz="navbar-brand" action="index" controller="main" labelKey="app.name" />
    </div>
  </nav>

  <main role="main">
    <div class="container my-4">
      <div class="row text-center py-5">
        <h1 class="fs-3 mb-0" style="text-transform: uppercase;">${fn:res(ctx, "page.login_page")}</h1>
      </div>

      <div class="row">
        <div class="col mb-4">
          <t:messages clazz="alert alert-danger" listClass="mb-0" type="error" />
          <t:messages clazz="alert alert-warning" listClass="mb-0" type="warn" />
          <t:messages clazz="alert alert-secondary" listClass="mb-0" type="notice" />
          <t:messages clazz="alert alert-info" listClass="mb-0" type="info" />
          <t:messages clazz="alert alert-success" listClass="mb-0" type="success" />
        </div>
      </div>

      <!-- @doBody begin -->
      <%@ include file="login_inc.jsp" %>
      <!-- @doBody end -->
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.min.js"></script>
  <script src="${ctx.contextPath}/static/js/app.js"></script>

<!-- @jsSection begin -->
<script type="text/javascript">
  document.addEventListener("DOMContentLoaded", function() {
    // TODO
  });
</script>
<!-- @jsSection end -->
</body>
</html>
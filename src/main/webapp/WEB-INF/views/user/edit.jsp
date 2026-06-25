<%@ page language="java" contentType="text/html; charset=utf-8" session="false" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>
<!DOCTYPE html>
<html lang="${ctx.languageId}">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="Appslandia MVC Framework" />
<meta name="author" content="Loc Ha" />
<title>${fn:res(ctx, "page.user_edit")}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous" />
<link href="${ctx.contextPath}/static/css/app.css" rel="stylesheet" />

</head>
<body>

  <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
      <t:actionLink action="index" controller="main" labelKey="app.name" clazz="navbar-brand" />

      <button type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
        aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item"><t:actionLink action="index" controller="user" labelKey="nav.user_index" clazz="nav-link" /></li>
          <li class="nav-item"><t:actionLink action="edit" controller="user" labelKey="nav.user_edit" clazz="nav-link" /></li>
        </ul>

        <ul class="navbar-nav">
          <t:ifUnauth>
            <li class="nav-item"><t:actionLink action="login" controller="auth" labelKey="nav.login" clazz="nav-link" /></li>
          </t:ifUnauth>

          <t:ifAuth>
            <li class="nav-item dropdown"><a href="#" id="authDrp" data-bs-toggle="dropdown" aria-expanded="false" class="nav-link dropdown-toggle"> <t:userName /></a>
              <ul aria-labelledby="authDrp" class="dropdown-menu dropdown-menu-end">
                <li><t:actionLink action="logout" controller="auth" labelKey="nav.logout" clazz="dropdown-item" /></li>
                <li><t:actionLink action="changepwd" controller="auth" labelKey="nav.changepwd" clazz="dropdown-item" /></li>
              </ul>
            </li>
          </t:ifAuth>
        </ul>
      </div>

    </div>
  </nav>

  <main>
    <div class="container bg-white shadow rounded my-4">
      <div class="row text-center py-5">
        <h1 class="fs-3 mb-0">${fn:res(ctx, "page.user_edit")}</h1>
      </div>

      <div class="row">
        <div class="col mb-4">
          <t:messages value="${__messages}" role="alert" type="error" clazz="alert alert-danger mb-0 p-2" listClass="mb-0" />
          <t:messages value="${__messages}" role="alert" type="warn" clazz="alert alert-warning mb-0 p-2" listClass="mb-0" />
          <t:messages value="${__messages}" role="status" type="notice" clazz="alert alert-secondary mb-0 p-2" listClass="mb-0" />
          <t:messages value="${__messages}" role="status" type="info" clazz="alert alert-info mb-0 p-2" listClass="mb-0" />
        </div>
      </div>

      <!-- @doBody begin -->
      <%@ include file="edit_inc.jsp" %>
      <!-- @doBody end -->
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  <script src="${ctx.contextPath}/static/js/app.js"></script>

<!-- @jsSection begin -->
<script type="text/javascript" nonce="${ctx.nonce}">
  document.addEventListener("DOMContentLoaded", function() {

    document.querySelectorAll("button.form-action").forEach(function(button) {
      button.addEventListener("click", function(event) {

        const actionValue = this.getAttribute("data-action");
        const formAction = this.form.querySelector("#__form_action");

        if (formAction) {
          formAction.value = actionValue || "";
        }
        this.form.submit();
      });
    });
  });
</script>
<!-- @jsSection end -->
</body>
</html>
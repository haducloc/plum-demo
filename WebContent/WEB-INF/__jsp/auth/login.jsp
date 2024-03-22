<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fx" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${ctx.escXml('page.login_page')}
  __layout=layout
 -->

<div class="container mb-4">
  <div class="row justify-content-md-center">

    <div class="col-md-4">
      <div class="card">
        <div class="card-body">

          <t:errors divClass="alert alert-danger" listClass="mb-0" fieldOrders="username,password" />

          <t:form action="login" controller="auth" method="post" __returnUrl="${param.returnUrl}">
            <div class="mb-3">
              <t:label fieldName="username" labelKey="authLoginModel.username" clazz="form-label" required="true" />
              <t:input type="text" path="model.username" clazz="form-control" readonly="${not empty pageContext.request.remoteUser}" />
            </div>

            <div class="mb-3">
              <t:label fieldName="password" labelKey="authLoginModel.password" clazz="form-label" required="true" />
              <t:input type="password" path="model.password" clazz="form-control" />
            </div>

            <div class="mb-3 form-check">
              <t:checkbox path="model.rememberMe" codeValue="true" clazz="form-check-input" readonly="${not empty pageContext.request.remoteUser}" />
              <t:label fieldName="rememberMe" labelKey="authLoginModel.rememberme" clazz="form-check-label" />
            </div>

            <t:button id="btnLogin" type="submit" labelKey="label.login" clazz="btn btn-primary" />
          </t:form>
        </div>
      </div>

    </div>
  </div>
</div>

<!-- @jsSection begin -->
<script type="text/javascript">

  document.addEventListener("readystatechange", (event) => {
    if (event.target.readyState === "complete") {
      // DOM ready
    }
  });
  
</script>
<!-- @jsSection end -->

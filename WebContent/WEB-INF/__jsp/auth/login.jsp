<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fx" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${ctx.esc('page.login_page')}
  __layout=layout
 -->

<div class="container mb-4">
  <div class="row justify-content-md-center">

    <div class="col-md-4">
      <div class="card">
        <div class="card-body">

          <t:formErrors clazz="px-4 py-2 rounded" modelLevelOnly="false">
            <t:fieldOrders>username,password</t:fieldOrders>
          </t:formErrors>

          <t:form action="login" controller="auth" __returnUrl="${param.returnUrl}">
            <div class="mb-3">
              <t:fieldLabel field="username" labelKey="authLoginModel.username" clazz="form-label" required="true" />
              <t:textBox type="text" path="model.username" clazz="form-control" readonly="${not empty pageContext.request.remoteUser}" />
            </div>

            <div class="mb-3">
              <t:fieldLabel field="password" labelKey="authLoginModel.password" clazz="form-label" required="true" />
              <t:textBox type="password" path="model.password" clazz="form-control" />
            </div>

            <div class="mb-3 form-check">
              <t:checkbox path="model.rememberMe" submitValue="true" clazz="form-check-input" readonly="${not empty pageContext.request.remoteUser}" />
              <t:fieldLabel field="rememberMe" labelKey="authLoginModel.rememberme" clazz="form-check-label" />
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

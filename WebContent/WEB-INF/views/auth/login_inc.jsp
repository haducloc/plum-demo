<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>

<div class="row justify-content-center">
  <div class="col-md-8 col-lg-4">

    <div class="bg-white shadow rounded px-3 py-5">

      <t:form action="login" controller="auth" method="post" ____return_url="${param.__return_url}" ____reauthentication="${param.__reauthentication}">
        <t:csrfId />
        <t:formErrors modelOnly="true" fieldOrder="username,password" clazz="alert alert-danger" listClass="mb-0" />

        <div class="mb-3">
          <t:label path="username" labelKey="auth_login_model.username" clazz="form-label" required="true" />
          <t:input type="text" path="username" clazz="form-control" />
        </div>

        <div class="mb-3">
          <t:label path="password" labelKey="auth_login_model.password" clazz="form-label" required="true" />
          <t:input type="password" path="password" clazz="form-control" />
        </div>

        <div class="mb-3">
          <t:choiceBox path="rememberMe" class="form-check">
            <t:choiceInput value="true" clazz="form-check-input" checkbox="true" />
            <t:choiceLabel value="true" clazz="form-check-label" labelKey="auth_login_model.rememberme" />
          </t:choiceBox>
        </div>

        <t:button id="btnLogin" type="submit" labelKey="label.login" clazz="btn btn-primary" />
      </t:form>
    </div>
  </div>
</div>

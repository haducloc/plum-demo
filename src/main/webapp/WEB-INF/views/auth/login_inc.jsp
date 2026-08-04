<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>

<div class="row justify-content-center">
  <div class="col-md-8 col-lg-4">

    <div class="bg-white shadow rounded px-3 py-5">

      <t:form action="login" controller="auth" method="post" ____return_url="${param.__return_url}" ____reauthentication="${param.__reauthentication}">
        <t:csrfId />

        <div class="mb-3">
          <t:formErrors modelOnly="false" fieldOrder="username,password" role="alert" clazz="alert alert-danger mb-0 p-2" listClass="mb-0" />
        </div>

        <div class="mb-3">
          <t:label path="username" labelKey="auth_login_model.username" clazz="form-label" required="true" />
          <t:input type="text" path="username" clazz="form-control" hasFieldError="true" />
          <t:fieldError path="username" role="alert" />
        </div>

        <div class="mb-3">
          <t:label path="password" labelKey="auth_login_model.password" clazz="form-label" required="true" />
          <t:input type="password" path="password" clazz="form-control" hasFieldError="true" />
          <t:fieldError path="password" role="alert" />
        </div>

        <div class="mb-3">
          <t:choiceGroup path="rememberMe" clazz="form-choice-group" type="checkbox">
            <div class="form-check">
              <t:choiceInput value="true" clazz="form-check-input" hasFieldError="true" />
              <t:choiceLabel value="true" clazz="form-check-label" labelKey="auth_login_model.remember_me" />
            </div>
          </t:choiceGroup>
          <t:fieldError path="rememberMe" role="alert" />
        </div>

        <t:button id="btnLogin" type="submit" labelKey="label.login" clazz="btn btn-primary" />
      </t:form>
    </div>
  </div>
</div>

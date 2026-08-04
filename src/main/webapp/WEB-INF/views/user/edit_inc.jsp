<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>

<div class="row">
  <div class="col mb-4">

    <div class="card shadow-sm">
      <div class="card-body">

        <t:form id="form1" action="edit" method="post" __userId="${model.userId}" clazz="row g-3">
          <t:csrfId />
          <t:formAction />

          <div class="col-12">
            <t:formErrors modelOnly="false" fieldOrder="username,password,dob,active,divisionId,roles,notes" role="alert" clazz="alert alert-danger mb-0 p-2"
              listClass="mb-0" />
          </div>

          <div class="col-md-6">
            <t:label path="username" labelKey="user.username" required="true" clazz="form-label" />
            <t:input path="username" readonly="${not empty model.userId}" clazz="form-control" hasFieldError="true" />
            <t:fieldError path="username" role="alert" />
          </div>

          <div class="col-md-6">
            <t:label path="password" labelKey="user.password" required="true" asDiv="${not empty model.userId}" clazz="form-label" />
            <t:if test="${empty model.userId}">
              <t:input path="password" type="password" clazz="form-control" hasFieldError="true" />
            </t:if>
            <t:if test="${not empty model.userId}">
              <t:actionLink action="resetpwd" controller="user" labelKey="label.reset_password" clazz="link-secondary d-block" />
            </t:if>
            <t:fieldError path="password" role="alert" />
          </div>

          <div class="col-md-6">
            <t:label path="dob" labelKey="user.dob" clazz="form-label" />
            <t:input path="dob" hasFieldError="true" type="date" clazz="form-control" />
            <t:fieldError path="dob" role="alert" />
          </div>

          <div class="col-md-6">
            <t:label path="active" labelKey="user.active" asDiv="true" clazz="form-label" />
            <t:choiceGroup path="active" hasFieldError="true" type="checkbox" role="group" labelledby="lbl_active" clazz="form-choice-group">
              <div class="form-check">
                <t:choiceInput value="true" clazz="form-check-input" />
                <t:choiceLabel value="true" labelKey="user.active" clazz="form-check-label" />
              </div>
            </t:choiceGroup>
            <t:fieldError path="active" role="alert" />
          </div>

          <div class="col-md-6">
            <t:label path="divisionId" labelKey="user.division_id" required="true" clazz="form-label" />
            <t:select path="divisionId" items="${divisionDS}" clazz="form-select" hasFieldError="true" />
            <t:fieldError path="divisionId" role="alert" />
          </div>

          <div class="col-md-6">
            <t:label path="roles" labelKey="user.roles" asDiv="true" clazz="form-label" />
            <t:choiceGroup path="roles" hasFieldError="true" multi="true" type="checkbox" role="group" labelledby="lbl_roles" clazz="form-choice-group">

              <t:iterate items="${roleDS}" var="item">
                <div class="form-check form-check-inline">
                  <t:choiceInput value="${item.value}" clazz="form-check-input" />
                  <t:choiceLabel value="${item.value}" clazz="form-check-label">${fn:esc(item.displayName)}</t:choiceLabel>
                </div>
              </t:iterate>
            </t:choiceGroup>
            <t:fieldError path="roles" role="alert" />
          </div>

          <div class="col-12">
            <t:label path="notes" labelKey="user.notes" clazz="form-label" />
            <t:textarea path="notes" rows="5" clazz="form-control" hasFieldError="true" />
            <t:fieldError path="notes" role="alert" />
          </div>

          <div class="col-12 d-flex gap-3">
            <t:button id="btnSave" type="button" labelKey="label.save" clazz="btn btn-primary form-action px-4" />
            <t:button id="btnRemove" type="button" labelKey="label.remove" data-action="remove" clazz="btn btn-danger form-action px-4"
              rendered="${not empty model.userId}" />
          </div>

        </t:form>

      </div>
    </div>

  </div>
</div>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fx" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${ctx.esc('page.user_edit')}
  __layout=layout
 -->

<div class="container mb-4">
  <div class="row">
    <div class="col">

      <div class="card">
        <div class="card-body">

          <t:formErrors clazz="px-4 py-2 rounded" modelLevelOnly="false">
            <t:fieldOrders>username,password,roles,active</t:fieldOrders>
          </t:formErrors>

          <t:form id="form1" action="edit" __userId="${model.userId}" autocomplete="off">
            <t:formErrors clazz="px-4 py-2" modelLevelOnly="true" />

            <input type="hidden" id="formAction" name="formAction" />

            <div class="mb-3">
              <t:fieldLabel field="username" labelKey="user.username" clazz="form-label" required="true" />
              <t:textBox path="model.username" clazz="form-control" readonly="${not empty model.userId}" />
            </div>

            <div class="mb-3">
              <t:fieldLabel field="password" labelKey="user.password" clazz="form-label" required="true" />

              <c:if test="${empty model.userId}">
                <t:textBox type="password" path="model.password" clazz="form-control" />
              </c:if>

              <c:if test="${not empty model.userId}">
                <t:actionLink action="resetpwd" controller="user" clazz="link-secondary d-block">
                     ${ctx.escCt('label.reset_password')}
                  </t:actionLink>
              </c:if>

            </div>

            <div class="mb-3">
              <t:fieldLabel field="roles" labelKey="user.roles" clazz="form-label" />
              <t:textBox path="model.roles" clazz="form-control" />
            </div>

            <div class="mb-3 form-check">
              <t:checkbox submitValue="true" path="model.active" clazz="form-check-input mr-2" />
              <label class="form-check-label" for="active"> ${ctx.escCt('user.active')} </label>
            </div>

            <t:button type="submit" id="btnSave" clazz="btn btn-primary px-4" labelKey="label.save"></t:button>&nbsp;
            <t:button type="button" id="btnRemove" clazz="btn btn-danger px-4" labelKey="label.remove" onclick="onRemoveClick()"
              render="${not empty model.userId and model.username ne pageContext.request.remoteUser}"></t:button>
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

  function onRemoveClick() {
    document.getElementById("formAction").value = "remove";
    document.getElementById("form1").submit();    
  }
  
</script>
<!-- @jsSection end -->

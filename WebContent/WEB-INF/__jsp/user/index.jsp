<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fx" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${ctx.escXml('page.user_index')}
  __layout=layout
 -->

<div class="container mb-4">
  <div class="row">
    <div class="col">

      <div class="table-responsive mb-4">
        <table class="table table-sm table-bordered table-striped table-hover">
          <thead>
            <tr>
              <th scope="col" colspan="3"><t:actionLink action="edit" class="btn btn-sm btn-secondary fw-semibold">&plus;</t:actionLink></th>
            </tr>
            <tr>
              <th scope="col">${ctx.escXml('user.username')}</th>
              <th scope="col">${ctx.escXml('user.roles')}</th>
              <th scope="col">${ctx.escXml('user.active')}</th>
              <th scope="col">${ctx.escXml('user.dob')}</th>
              <th scope="col">${ctx.escXml('user.salary')}</th>
            </tr>
          </thead>
          <tbody>
            <t:iterate items="${users}" var="item">
              <tr>
                <td>
                  <t:actionLink action="edit" __userId="${item.userId}">
                     ${fx:escXml(item.username)}
                  </t:actionLink>
                </td>
                <td>${item.roles}</td>
                <td>
                  <c:if test="${item.active}">
                    &check;
                  </c:if>
                </td>
                <td>${item.dob}</td>
                <td>${item.salary}</td>
              </tr>
            </t:iterate>
          </tbody>
        </table>
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

<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>

<div class="row mb-4">
  <ul class="mb-0 px-4 py-2 rounded messages-error">
    <li>${requestScope["com.appslandia.plum.base.Problem"].title}</li>
  </ul>
</div>

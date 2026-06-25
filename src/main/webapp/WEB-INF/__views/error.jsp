<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${fn:res(ctx, "error_page.page_title")}
    __layout=layout
-->

<div class="row">
  <div class="col mb-4">
  
    <div role="alert" class="alert alert-danger mb-0 p-2">
      <ul class="mb-0">
        <li>${requestScope["com.appslandia.plum.base.Problem"].title}</li>
      </ul>
    </div>
  </div>
</div>

<!-- @jsSection begin -->
<script type="text/javascript" nonce="${ctx.nonce}">
  document.addEventListener("DOMContentLoaded", function() {
    // TODO
  });
</script>
<!-- @jsSection end -->
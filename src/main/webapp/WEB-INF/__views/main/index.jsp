<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fn" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${fn:res(ctx, "page.main_index")}
  __layout=layout
-->
 
<div class="row">
  <div class="col">
    <p>Welcome to AppsLandia Plum Demo!</p>
  </div>
</div>

<!-- @jsSection begin -->
<script type="text/javascript" nonce="${ctx.nonce}">
  document.addEventListener("DOMContentLoaded", function() {
    // TODO
  });
</script>
<!-- @jsSection end -->

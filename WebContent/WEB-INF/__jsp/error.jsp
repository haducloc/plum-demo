<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="http://www.appslandia.com/jstl/tags"%>
<%@ taglib prefix="fx" uri="http://www.appslandia.com/jstl/functions"%>

<!-- @variables
  page.title=${ctx.esc('error_page.page_title')}
    __layout=layout
 -->

<div class="container mb-4">
  <ul class="mb-0 px-4 py-2 rounded messages-error">
    <li>${requestScope['com.appslandia.plum.base.Problem'].title}</li>
  </ul>
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
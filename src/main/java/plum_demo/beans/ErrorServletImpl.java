package plum_demo.beans;

import java.io.IOException;

import com.appslandia.plum.base.ErrorServlet;
import com.appslandia.plum.base.RequestContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Loc Ha
 *
 */
@WebServlet(name = "ErrorServlet", urlPatterns = "/error")
public class ErrorServletImpl extends ErrorServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected String getErrorViewPath(HttpServletRequest request, RequestContext requestContext) {
    return "/error";
  }

  @Override
  protected void writeErrorDev(HttpServletRequest request, HttpServletResponse response, RequestContext requestContext)
      throws ServletException, IOException {
    super.writeErrorProd(request, response, requestContext);
  }
}

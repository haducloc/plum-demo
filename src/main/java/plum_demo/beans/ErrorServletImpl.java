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
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@WebServlet(name = "ErrorServlet", urlPatterns = "/error")
public class ErrorServletImpl extends ErrorServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected String getErrorJsp(HttpServletRequest request, RequestContext requestContext) {

    // Can return JSP error page based on the current module:
    // requestContext.getModule()

    return "/error.jsp";
  }

  @Override
  protected void writeErrorDev(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // super.writeErrorDev(request, response);

    super.writeErrorProd(request, response);
  }
}

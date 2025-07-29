package plum_demo.controllers;

import com.appslandia.common.base.AppLogger;
import com.appslandia.common.base.Out;
import com.appslandia.common.utils.Asserts;
import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.AuthContext;
import com.appslandia.plum.base.AuthParameters;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.EnableCsrf;
import com.appslandia.plum.base.ExceptionHandler;
import com.appslandia.plum.base.FormLogin;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpGetPost;
import com.appslandia.plum.base.InvalidAuth;
import com.appslandia.plum.base.ModelBinder;
import com.appslandia.plum.base.RequestWrapper;
import com.appslandia.plum.results.RedirectResult;
import com.appslandia.plum.results.ViewResult;
import com.appslandia.plum.utils.ServletUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.CredentialValidationResult.Status;
import jakarta.servlet.http.HttpServletResponse;
import plum_demo.models.AuthLoginModel;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Controller
public class AuthController {

  @Inject
  protected AppLogger logger;

  @Inject
  protected ModelBinder modelBinder;

  @Inject
  protected ExceptionHandler exceptionHandler;

  @Inject
  protected AuthContext authContext;

  @HttpGetPost
  @FormLogin
  @EnableCsrf
  public ActionResult login(RequestWrapper request, HttpServletResponse response) throws Exception {
    var model = new AuthLoginModel();

    // If the request has a principal but its module does not match the current module,
    // redirect the request to the home page.

    if (request.getUserPrincipal() != null) {
      if (!request.getModule().equals(request.getUserPrincipal().getModule())) {
        return new RedirectResult("index", "main");
      }
    }

    // GET
    if (request.isGetOrHead()) {

      if (request.getUserPrincipal() == null) {
        model.setUsername("admin");
        model.setRememberMe(true);

      } else {
        model.setUsername(request.getUserPrincipal().getName());
        model.setRememberMe(request.getUserPrincipal().isRememberMe());
      }

      request.storeModel(model);
      return ViewResult.DEFAULT;
    }

    // POST
    modelBinder.bindModel(request, model);

    // Model invalid?
    if (!request.getModelState().isValid()) {
      request.storeModel(model);
      return ViewResult.DEFAULT;
    }

    // Login
    try {
      var appCredential = new UsernamePasswordCredential(model.getUsername(), model.getPassword());
      CredentialValidationResult authResult = null;
      var reauthentication = false;

      // Already authenticated?
      if (request.getUserPrincipal() != null) {
        authResult = this.authContext.validate(appCredential, request.getModule());
        Asserts.isTrue(authResult.getStatus() != Status.NOT_VALIDATED);

        if (authResult.getStatus() == Status.VALID) {
          reauthentication = request.getBoolParam(ServletUtils.PARAM_REAUTHENTICATION);
          request.logout();
        }
      }

      // Authenticate
      if (authResult == null || authResult.getStatus() == Status.VALID) {
        var invalidCode = new Out<String>();
        var authParams = new AuthParameters().module(request.getModule()).credential(appCredential)
            .rememberMe(model.isRememberMe()).reauthentication(reauthentication);

        var authSuccess = this.authContext.authenticate(request, response, authParams, invalidCode);
        if (authSuccess) {
          var returnUrl = request.getParamOrNull(ServletUtils.PARAM_RETURN_URL);
          if (returnUrl != null) {

            ServletUtils.sendRedirect(response, returnUrl);
            return ActionResult.EMPTY;
          } else {
            return new RedirectResult("index", "user");
          }

        } else {
          request.getModelState().addError(request.res(InvalidAuth.getResKey(invalidCode.get())));
          request.storeModel(model);
          return ViewResult.DEFAULT;
        }
      } else {
        // authResult.getStatus() == Status.INVALID
        request.getModelState().addError(request.res(InvalidAuth.getInvalidCode(authResult)));
        request.storeModel(model);
        return ViewResult.DEFAULT;
      }

    } catch (Exception ex) {
      logger.error(ex);
      request.getModelState().addError(this.exceptionHandler.getProblem(request, ex).getTitle());

      request.storeModel(model);
      return ViewResult.DEFAULT;
    }
  }

  @HttpGet
  public ActionResult logout(RequestWrapper request, HttpServletResponse response) throws Exception {
    if (request.getUserPrincipal() != null) {
      request.logout();
    }
    if (request.getSession(false) != null) {
      request.getSession(false).invalidate();
    }
    return new RedirectResult("index", "user");
  }

  @HttpGet
  @Authorize(reauth = true)
  public ActionResult changepwd(RequestWrapper request, HttpServletResponse response) throws Exception {
    return ViewResult.DEFAULT;
  }
}

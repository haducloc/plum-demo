package plum_demo.controllers;

import com.appslandia.common.base.Out;
import com.appslandia.common.utils.Asserts;
import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.AppLogger;
import com.appslandia.plum.base.AuthContext;
import com.appslandia.plum.base.AuthParameters;
import com.appslandia.plum.base.AuthResult;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.EnableCompress;
import com.appslandia.plum.base.EnableCsrf;
import com.appslandia.plum.base.ExceptionHandler;
import com.appslandia.plum.base.FormLogin;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpGetPost;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.base.ModelBinder;
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
@EnableCompress
public class AuthController extends ControllerBase {

  @Inject
  protected AppLogger logger;

  @Inject
  protected ModelBinder modelBinder;

  @Inject
  protected ExceptionHandler exceptionHandler;

  @Inject
  protected AuthContext authContext;

  @FormLogin
  @EnableCsrf
  @HttpGetPost
  public ActionResult login(HttpRequestFacade request, HttpServletResponse response) throws Exception {
    var model = new AuthLoginModel();

    // GET
    if (request.isGetOrHead()) {

      if (request.getUserPrincipal() == null) {
        model.setUsername("admin");
        model.setRememberMe(true);
      }
      // Same module?
      else if (request.getModule().equals(request.getUserPrincipal().getModule())) {

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

    try {
      var appCredential = new UsernamePasswordCredential(model.getUsername(), model.getPassword());
      CredentialValidationResult authResult = null;
      var reauthentication = false;

      // If authenticated, validate credentials before calling logout().
      if (request.getUserPrincipal() != null) {

        authResult = this.authContext.validate(appCredential, request.getModule());
        Asserts.isTrue(authResult.getStatus() != Status.NOT_VALIDATED);

        if (authResult.getStatus() == Status.VALID) {
          reauthentication = request.isParamTrue(ServletUtils.PARAM_REAUTHENTICATION);
          request.logout();
        }
      }

      // Proceed with the actual authentication?
      if (authResult == null || authResult.getStatus() == Status.VALID) {
        var invalidCode = new Out<String>();

        var authParams = new AuthParameters().module(request.getModule()).credential(appCredential)
            .rememberMe(model.isRememberMe()).reauthentication(reauthentication);

        // Call container authenticate
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
          request.addError(request.res(AuthResult.getResKey(invalidCode.get())));

          request.storeModel(model);
          return ViewResult.DEFAULT;
        }

      } else {
        // authResult.getStatus() == Status.INVALID
        request.addError(request.res(AuthResult.getResKey(authResult)));

        request.storeModel(model);
        return ViewResult.DEFAULT;
      }

    } catch (Exception ex) {
      logger.error(ex);
      request.addError(this.exceptionHandler.getProblem(request, ex).getTitle());

      request.storeModel(model);
      return ViewResult.DEFAULT;
    }
  }

  @HttpGet
  public ActionResult logout(HttpRequestFacade request, HttpServletResponse response) throws Exception {
    ServletUtils.logoutSession(request);

    return new RedirectResult("index", "user");
  }

  @HttpGet
  @Authorize(reauth = true)
  public ActionResult changepwd(HttpRequestFacade request, HttpServletResponse response) throws Exception {
    return ViewResult.DEFAULT;
  }
}

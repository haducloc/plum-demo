package plum_demo.controllers;

import com.appslandia.common.base.Out;
import com.appslandia.common.logging.AppLogger;
import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.AuthContext;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.ExceptionHandler;
import com.appslandia.plum.base.FormLogin;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpGetPost;
import com.appslandia.plum.base.ModelBinder;
import com.appslandia.plum.base.RequestAccessor;
import com.appslandia.plum.results.JspResult;
import com.appslandia.plum.results.RedirectResult;
import com.appslandia.plum.utils.ServletUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import plum_demo.auth.AppCredential;
import plum_demo.models.AuthLoginModel;
import plum_demo.services.UserService;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
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

  @Inject
  protected UserService userService;

  @HttpGetPost
  @FormLogin
  public ActionResult login(RequestAccessor request, HttpServletResponse response) throws Exception {
    AuthLoginModel model = new AuthLoginModel();

    // GET
    if (request.isGetOrHead()) {

      // Authenticated?
      if (request.isModuleAuthenticated()) {

        model.setUsername(request.getRemoteUser());
        model.setRememberMe(request.getUserPrincipal().isRememberMe());

      } else {

        // DEMO only
        model.setUsername("admin");
        model.setPassword("password");
        model.setRememberMe(true);
      }

      request.storeModel(model);
      return JspResult.DEFAULT;
    }

    // POST
    modelBinder.bindModel(request, model);

    // Model invalid?
    if (!request.getModelState().isValid()) {
      request.storeModel(model);
      return JspResult.DEFAULT;
    }

    // Authenticated?
    if (request.isModuleAuthenticated()) {
      model.setUsername(request.getRemoteUser());
      model.setRememberMe(request.getUserPrincipal().isRememberMe());
    }

    try {
      // AppCredential
      AppCredential appCredential = new AppCredential(model.getUsername(), model.getPassword());
      Out<String> invalidCode = new Out<>();

      // Authenticate
      if (!this.authContext.authenticate(request, response, appCredential, model.isRememberMe(), invalidCode)) {
        request.getModelState().addError(request.res(getMsgKey(invalidCode.val())));

        request.storeModel(model);
        return JspResult.DEFAULT;
      }

    } catch (Exception ex) {
      logger.error(ex);
      request.getModelState().addError(this.exceptionHandler.getProblem(request, ex).getTitle());

      request.storeModel(model);
      return JspResult.DEFAULT;
    }

    // returnUrl
    String returnUrl = request.getParamOrNull(ServletUtils.PARAM_RETURN_URL);
    if (returnUrl != null) {
      ServletUtils.sendRedirect(response, returnUrl);
      return ActionResult.EMPTY;

    } else {
      return new RedirectResult("index", "main");
    }
  }

  @HttpGet
  public ActionResult logout(RequestAccessor request, HttpServletResponse response) throws Exception {
    if (request.getUserPrincipal() != null) {
      request.logout();
    }
    return new RedirectResult("index", "main");
  }

  @HttpGet
  @Authorize
  public ActionResult changepwd(RequestAccessor request, HttpServletResponse response) throws Exception {
    return JspResult.DEFAULT;
  }

  // Build message key from the given invalidCode

  static String getMsgKey(String invalidCode) {
    return "auth." + invalidCode;
  }
}

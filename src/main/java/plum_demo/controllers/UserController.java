package plum_demo.controllers;

import com.appslandia.common.logging.AppLogger;
import com.appslandia.common.utils.ModelUtils;
import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.ExceptionHandler;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpGetPost;
import com.appslandia.plum.base.ModelBinder;
import com.appslandia.plum.base.RequestAccessor;
import com.appslandia.plum.results.JspResult;
import com.appslandia.plum.results.RedirectResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import plum_demo.entities.User;
import plum_demo.models.UserEditModel;
import plum_demo.services.UserService;
import plum_demo.utils.PasswordUtils;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
@Controller
@Authorize(roles = { "ADMIN" })
public class UserController {

  @Inject
  protected UserService userService;

  @Inject
  protected AppLogger logger;

  @Inject
  protected ModelBinder modelBinder;

  @Inject
  protected ExceptionHandler exceptionHandler;

  @HttpGet
  public ActionResult index(RequestAccessor request) throws Exception {
    request.store("users", userService.getAll());
    return JspResult.DEFAULT;
  }

  @HttpGetPost
  public ActionResult edit(RequestAccessor request, HttpServletResponse response, Integer userId) throws Exception {
    // UserEditModel
    UserEditModel model = new UserEditModel();

    // GET
    if (request.isGetOrHead()) {
      if (userId == null) {
        model.setActive(true);

      } else {
        User user = this.userService.getByPk(userId);
        request.assertNotNull(user);

        ModelUtils.copyProps(model, user, "userId", "username", "roles", "active", "dob", "salary");
      }

      request.storeModel(model);
      return JspResult.DEFAULT;
    }

    // POST
    modelBinder.bindModel(request, model);

    // Validate password
    if (model.getUserId() == null && model.getPassword() == null) {
      request.getModelState().addError("password", request.res("errors.field_required", request.res("user.password")));
    }

    if (model.getUserId() == null && request.getModelState().isValid("username")) {

      if (userService.getByUsername(model.getUsername()) != null) {
        request.getModelState().addError("username",
            request.res("user_edit.username_already_exists", model.getUsername()));
      }
    }

    // Model invalid?
    if (!request.getModelState().isValid()) {
      request.storeModel(model);
      return JspResult.DEFAULT;
    }

    try {
      // Remove Action
      if (request.isFormAction("remove")) {
        this.userService.remove(model.getUserId());

        request.getMessages().addNotice(request.res("entity.removed_successfully", request.res("user")));
      }
      // Save Action
      else {
        User user = new User();
        ModelUtils.copyProps(user, model, "userId", "username", "password", "roles", "active", "dob", "salary");

        // Hash password
        if (user.getUserId() == null) {
          user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        }
        this.userService.save(user);

        request.getMessages().addNotice(request.res("entity.saved_successfully", request.res("user")));
      }

      return new RedirectResult("index");

    } catch (Exception ex) {
      logger.error(ex);
      request.getMessages().addError(this.exceptionHandler.getProblem(request, ex).getTitle());

      request.storeModel(model);
      return JspResult.DEFAULT;
    }
  }

  @HttpGet
  public ActionResult resetpwd(RequestAccessor request, HttpServletResponse response) throws Exception {
    return JspResult.DEFAULT;
  }
}

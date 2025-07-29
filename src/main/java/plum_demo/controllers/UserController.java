package plum_demo.controllers;

import com.appslandia.common.base.AppLogger;
import com.appslandia.common.utils.ModelUtils;
import com.appslandia.common.utils.ValueUtils;
import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.BindModel;
import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.EnableCsrf;
import com.appslandia.plum.base.ExceptionHandler;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpGetPost;
import com.appslandia.plum.base.ModelBinder;
import com.appslandia.plum.base.ModelState;
import com.appslandia.plum.base.PagerModel;
import com.appslandia.plum.base.RequestWrapper;
import com.appslandia.plum.results.RedirectResult;
import com.appslandia.plum.results.ViewResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import plum_demo.entities.User;
import plum_demo.models.UserEditModel;
import plum_demo.models.UserIndexModel;
import plum_demo.services.UserService;
import plum_demo.utils.PasswordUtils;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Controller
@Authorize(roles = { UserUtils.ROLE_ADMIN })
public class UserController {

  private static final int PAGE_SIZE = 10;

  @Inject
  protected UserService userService;

  @Inject
  protected AppLogger logger;

  @Inject
  protected ModelBinder modelBinder;

  @Inject
  protected ExceptionHandler exceptionHandler;

  @HttpGet
  public ActionResult index(RequestWrapper request, @BindModel UserIndexModel model, ModelState modelState)
      throws Exception {

    modelState.clearErrors();
    model.setPageIndex(ValueUtils.valueOrMin(model.getPageIndex(), 1));

    if (model.getRecordCount() == null) {
      var recordCount = userService.getUsersCount(model.isInclInactive(), model.getUsername());
      model.setRecordCount(recordCount);
    }
    var pagerModel = new PagerModel(model.getPageIndex(), model.getRecordCount(), PAGE_SIZE);

    var users = userService.queryUsers(model.isInclInactive(), model.getUsername(), model.getPageIndex(), PAGE_SIZE);
    request.store("users", users);

    request.storePagerModel(pagerModel);
    request.storeModel(model);

    return ViewResult.DEFAULT;
  }

  private void prepareEdit(RequestWrapper request) throws Exception {
    request.store("rolesDS", UserUtils.USER_ROLES);
  }

  @HttpGetPost
  @EnableCsrf
  public ActionResult edit(RequestWrapper request, HttpServletResponse response, Integer userId) throws Exception {
    // Model
    var model = new UserEditModel();

    // GET
    if (request.isGetOrHead()) {
      if (userId == null) {
        model.setActive(true);

      } else {
        var user = this.userService.getByPk(userId);
        request.assertNotNull(user);

        ModelUtils.copyProps(model, user, "userId", "username", "roles", "active", "dob");
      }
      request.storeModel(model);

      prepareEdit(request);
      return ViewResult.DEFAULT;
    }

    // POST
    modelBinder.bindModel(request, model);

    // Validate
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

      prepareEdit(request);
      return ViewResult.DEFAULT;
    }

    try {
      // Remove Action
      if (request.isFormAction("remove")) {
        this.userService.remove(model.getUserId());

        request.getMessages().addSuccess(request.res("entity.removed_successfully", request.res("user")));
      }
      // Save Action
      else {
        var user = new User();
        ModelUtils.copyProps(user, model, "userId", "username", "password", "roles", "active", "dob");

        // Hash password
        if (user.getUserId() == null) {
          user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        }
        this.userService.save(user);

        request.getMessages().addSuccess(request.res("entity.saved_successfully", request.res("user")));
      }

      return new RedirectResult("index");

    } catch (Exception ex) {
      logger.error(ex);
      request.getMessages().addError(this.exceptionHandler.getProblem(request, ex).getTitle());
      request.storeModel(model);

      prepareEdit(request);
      return ViewResult.DEFAULT;
    }
  }

  @HttpGet
  public ActionResult resetpwd(RequestWrapper request, HttpServletResponse response) throws Exception {
    return ViewResult.DEFAULT;
  }
}

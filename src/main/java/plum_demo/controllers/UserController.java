package plum_demo.controllers;

import com.appslandia.common.base.Out;
import com.appslandia.common.utils.ModelUtils;
import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.AppLogger;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.BindModel;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.EnableCompress;
import com.appslandia.plum.base.EnableCsrf;
import com.appslandia.plum.base.ExceptionHandler;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpGetPost;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.base.ModelBinder;
import com.appslandia.plum.base.ModelState;
import com.appslandia.plum.base.PagerModel;
import com.appslandia.plum.base.PagerState;
import com.appslandia.plum.base.SortConfig;
import com.appslandia.plum.base.SortModel;
import com.appslandia.plum.results.RedirectResult;
import com.appslandia.plum.results.ViewResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import plum_demo.entities.User;
import plum_demo.models.UserEditModel;
import plum_demo.models.UserIndexModel;
import plum_demo.services.UserService;
import plum_demo.utils.DivisionUtils;
import plum_demo.utils.PasswordUtils;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Authorize(roles = { UserUtils.ROLE_ADMIN })
@EnableCompress
public class UserController extends ControllerBase {

  @Inject
  protected UserService userService;

  @Inject
  protected AppLogger logger;

  @Inject
  protected ModelBinder modelBinder;

  @Inject
  protected ExceptionHandler exceptionHandler;

  private void prepareIndex(HttpRequestFacade request) throws Exception {
    request.store("divisionDS", DivisionUtils.LIST_IDS);
  }

  static final SortConfig UserSortConfig = new SortConfig().asc("username").asc("dob");

  @HttpGet
  public ActionResult index(HttpRequestFacade request, @BindModel UserIndexModel model, ModelState modelState)
      throws Exception {
    modelState.clearErrors();

    // pageSize=10
    var pagerState = new PagerState(model.getPageIndex(), 10);
    var sortModel = new SortModel(UserSortConfig).setState(model.getSortBy(), model.getSortAsc());
    var recordCount = new Out<Integer>(model.getRecordCount());

    var users = userService.queryUsers(model.getDivisionId(), model.getUsername(), pagerState, sortModel.getState(),
        recordCount);
    request.store("users", users);

    var pagerModel = new PagerModel(recordCount.get(), pagerState);
    request.storePagerModel(pagerModel);

    request.storeModel(model);
    request.storeSortModel(sortModel);

    prepareIndex(request);
    return ViewResult.DEFAULT;
  }

  private void prepareEdit(HttpRequestFacade request) throws Exception {
    request.store("roleDS", UserUtils.ROLE_LIST);
    request.store("divisionDS", DivisionUtils.LIST_IDS);
  }

  @EnableCsrf
  @HttpGetPost
  public ActionResult edit(HttpRequestFacade request, HttpServletResponse response, Integer userId) throws Exception {
    // Model
    var model = new UserEditModel();

    // GET
    if (request.isGetOrHead()) {
      if (userId == null) {
        model.setActive(true);

      } else {
        var user = this.userService.getByPk(userId);
        request.assertNotNull(user);

        ModelUtils.copyProps(model, user, "userId", "username", "divisionId", "roles", "active", "dob", "notes");
      }
      request.storeModel(model);

      prepareEdit(request);
      return ViewResult.DEFAULT;
    }

    // POST
    modelBinder.bindModel(request, model);

    // Validate
    if (model.getUserId() == null && model.getPassword() == null) {
      request.addFieldError("password", request.res("errors.field_required", request.res("user.password")));
    }

    if (model.getUserId() == null && request.getModelState().isValid("username")) {
      if (userService.getByUsername(model.getUsername()) != null) {

        request.addFieldError("username", request.res("user_edit.username_already_exists", model.getUsername()));
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

        request.addInfo(request.res("entity.removed_successfully", request.res("user")));
      }
      // Save Action
      else {
        var user = new User();
        ModelUtils.copyProps(user, model, "userId", "username", "password", "divisionId", "roles", "active", "dob",
            "notes");

        // Hash password
        if (user.getUserId() == null) {
          user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        }
        this.userService.save(user);

        request.addInfo(request.res("entity.saved_successfully", request.res("user")));
      }

      return new RedirectResult("index");

    } catch (Exception ex) {
      logger.error(ex);
      request.addError(this.exceptionHandler.getProblem(request, ex).getTitle());
      request.storeModel(model);

      prepareEdit(request);
      return ViewResult.DEFAULT;
    }
  }

  @HttpGet
  public ActionResult resetpwd(HttpRequestFacade request, HttpServletResponse response) throws Exception {
    return ViewResult.DEFAULT;
  }
}

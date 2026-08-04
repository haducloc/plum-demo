package plum_demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.appslandia.common.jose.JwtSigner;
import com.appslandia.common.jose.JwtToken;
import com.appslandia.common.utils.ParseUtils;
import com.appslandia.common.utils.STR;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.EnableCompress;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.base.Module;
import com.appslandia.plum.base.SelectItem;
import com.appslandia.plum.results.ContentResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import plum_demo.services.UserService;
import plum_demo.utils.Modules;
import plum_demo.utils.PasswordUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Module(Modules.API)
public class ApiController extends ControllerBase {

  @Inject
  protected UserService userService;

  @Inject
  protected JwtSigner jwtSigner;

  @HttpGet
  public ContentResult login(HttpRequestFacade request, HttpServletResponse response, @NotNull String userName,
      @NotNull String password) throws Exception {

    var user = userService.getByUsername(userName);
    if ((user == null) || !PasswordUtils.verifyPassword(password, user.getPassword())) {
      return new ContentResult("invalid");
    }

    var header = jwtSigner.newHeader();
    var payload = jwtSigner.newPayload().setExp(1, TimeUnit.DAYS).setIatNow();

    // sub is required
    payload.setSub(Integer.toString(user.getUserId()));

    var token = jwtSigner.sign(new JwtToken(header, payload));

    var protectedApi = "http://localhost:8080/plum-demo/api/users";
    var curl = STR.fmt("curl -X GET \"{}\" -H \"Authorization: Bearer {}\"", protectedApi, token);
    return new ContentResult(curl);
  }

  @HttpGet
  @Authorize
  @EnableCompress
  public List<SelectItem> users(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  protected List<SelectItem> createList(HttpRequestFacade request) {
    var listSize = request.getParameter("listSize");
    var size = ParseUtils.parseInt(listSize, 5000);

    var list = new ArrayList<SelectItem>(size);
    for (int i = 1; i <= size; i++) {
      list.add(new SelectItem(i, "Select Item-" + i));
    }
    return list;
  }
}

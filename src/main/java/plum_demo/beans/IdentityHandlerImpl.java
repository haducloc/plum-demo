package plum_demo.beans;

import com.appslandia.common.base.Out;
import com.appslandia.plum.base.InvalidAuth;
import com.appslandia.plum.base.Modules;
import com.appslandia.plum.base.RolesPrincipal;
import com.appslandia.plum.defaults.MemUserIdentityHandler;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import plum_demo.auth.AppPrincipal;
import plum_demo.services.UserService;
import plum_demo.utils.PasswordUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class IdentityHandlerImpl extends MemUserIdentityHandler {

  @Inject
  protected UserService userService;

  @Override
  public RolesPrincipal validateIdentity(String module, String identity, Out<String> invalidCode) {
    if (Modules.DEFAULT.equals(module)) {
      // Find user
      var user = userService.getByUsername(identity);

      if (user == null) {
        invalidCode.value = InvalidAuth.CREDENTIAL_INVALID.getCode();
        return null;
      }
      return new RolesPrincipal(new AppPrincipal(user.getUserId(), user.getUsername()), user.getRoles());
    }
    return super.validateIdentity(module, identity, invalidCode);
  }

  @Override
  public RolesPrincipal validateCredentials(String module, String username, String password, Out<String> invalidCode) {
    if (Modules.DEFAULT.equals(module)) {
      // Find user
      var user = userService.getByUsername(username);

      // Validate credential?
      if ((user == null) || user == null || !PasswordUtils.verifyPassword(password, user.getPassword())) {
        invalidCode.value = InvalidAuth.CREDENTIAL_INVALID.getCode();
        return null;
      }
      return new RolesPrincipal(new AppPrincipal(user.getUserId(), user.getUsername()), user.getRoles());
    }
    return super.validateCredentials(module, username, password, invalidCode);
  }
}

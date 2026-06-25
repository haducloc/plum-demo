package plum_demo.auth;

import com.appslandia.plum.base.AuthPrincipal;
import com.appslandia.plum.base.AuthResult;
import com.appslandia.plum.base.Modules;
import com.appslandia.plum.base.RemMeTokenIdentityStore;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import plum_demo.services.UserService;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class AppRemMeTokenIdentityStore extends RemMeTokenIdentityStore {

  @Inject
  protected UserService userService;

  @Override
  protected AuthResult doValidate(String module, String identity) {
    if (Modules.DEFAULT.equals(module)) {
      var userId = Integer.parseInt(identity);

      // Find user
      var user = userService.getByPk(userId);

      // Validate credential?
      if (user == null || !user.isActive()) {
        return AuthResult.toInvalidResult(AuthResult.CREDENTIAL_INVALID);
      }

      var authPrincipal = new AuthPrincipal(null, module, user.getUsername(), UserUtils.toPrincipalAttributes(user));
      return new AuthResult(authPrincipal, user.getRoles());
    }

    return AuthResult.NOT_VALIDATED_RESULT;
  }
}

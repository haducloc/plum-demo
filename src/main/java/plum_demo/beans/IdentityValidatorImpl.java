package plum_demo.beans;

import com.appslandia.common.base.Out;
import com.appslandia.plum.base.IdentityValidator;
import com.appslandia.plum.base.InvalidAuthResult;
import com.appslandia.plum.base.PrincipalRoles;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import plum_demo.auth.AppPrincipal;
import plum_demo.entities.User;
import plum_demo.services.UserService;
import plum_demo.utils.PasswordUtils;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class IdentityValidatorImpl implements IdentityValidator {

  @Inject
  protected UserService userService;

  @Override
  public PrincipalRoles validate(String module, String identity, Out<String> invalidCode) {
    // Find user
    User user = userService.getByUsername(identity);

    if (user == null) {
      invalidCode.value = InvalidAuthResult.CREDENTIAL_INVALID.getCode();
      return null;
    }

    return new PrincipalRoles(new AppPrincipal(user.getUserId(), user.getUsername()), user.getRoles());
  }

  @Override
  public PrincipalRoles validate(String module, String username, String password, Out<String> invalidCode) {
    // Find user
    User user = userService.getByUsername(username);

    if (user == null) {
      invalidCode.value = InvalidAuthResult.CREDENTIAL_INVALID.getCode();
      return null;
    }

    // Validate credential?
    if (user == null || !PasswordUtils.verifyPassword(password, user.getPassword())) {
      invalidCode.value = InvalidAuthResult.CREDENTIAL_INVALID.getCode();
      return null;
    }
    return new PrincipalRoles(new AppPrincipal(user.getUserId(), user.getUsername()), user.getRoles());
  }
}

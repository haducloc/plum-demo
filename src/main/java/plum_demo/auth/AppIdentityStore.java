package plum_demo.auth;

import java.util.Set;

import com.appslandia.plum.base.AuthPrincipal;
import com.appslandia.plum.base.AuthResult;
import com.appslandia.plum.base.IdentityStoreBase;
import com.appslandia.plum.base.Modules;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import plum_demo.services.UserService;
import plum_demo.utils.PasswordUtils;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class AppIdentityStore extends IdentityStoreBase {

  private static final Set<String> VALIDATION_MODULES = Set.of(Modules.DEFAULT);

  @Inject
  protected UserService userService;

  @Override
  public Set<String> validationModules() {
    return VALIDATION_MODULES;
  }

  @Override
  protected AuthResult doValidate(String module, Credential credential) {
    if (!(credential instanceof UsernamePasswordCredential userPassCred)) {
      return AuthResult.NOT_VALIDATED_RESULT;
    }

    // Find user
    var user = userService.getByUsername(userPassCred.getCaller());

    // Validate credential?
    if ((user == null) || !PasswordUtils.verifyPassword(userPassCred.getPasswordAsString(), user.getPassword())) {
      return AuthResult.toInvalidResult(AuthResult.CREDENTIAL_INVALID);
    }

    var authPrincipal = new AuthPrincipal(storeId(), module, user.getUsername(), UserUtils.toPrincipalAttributes(user));
    return new AuthResult(authPrincipal, user.getRoles());
  }
}

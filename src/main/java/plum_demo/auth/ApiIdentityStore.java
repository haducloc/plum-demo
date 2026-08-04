package plum_demo.auth;

import java.util.Set;

import com.appslandia.common.jose.JwtSigner;
import com.appslandia.common.jose.JwtToken;
import com.appslandia.plum.base.JwtIdentityStore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import plum_demo.utils.Modules;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class ApiIdentityStore extends JwtIdentityStore {

  private static final Set<String> VALIDATION_MODULES = Set.of(Modules.API);

  @Inject
  protected JwtSigner jwtSigner;

  @Override
  public Set<String> validationModules() {
    return VALIDATION_MODULES;
  }

  @Override
  protected JwtToken parseJwtToken(String module, String credential) {
    var token = jwtSigner.parse(credential);
    jwtSigner.verify(token);
    return token;
  }
}

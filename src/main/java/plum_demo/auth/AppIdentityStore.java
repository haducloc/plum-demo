package plum_demo.auth;

import java.util.Set;

import com.appslandia.plum.base.Modules;
import com.appslandia.plum.base.UserPassIdentityStore;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class AppIdentityStore extends UserPassIdentityStore {

  private static final Set<String> VALIDATION_MODULES = Set.of(Modules.DEFAULT);

  @Override
  protected Set<String> validationModules() {
    return VALIDATION_MODULES;
  }
}

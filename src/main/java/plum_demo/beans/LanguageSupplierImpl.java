package plum_demo.beans;

import com.appslandia.common.base.Language;
import com.appslandia.plum.base.LanguageSupplier;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class LanguageSupplierImpl implements LanguageSupplier {

  @Override
  public Language[] get() {
    return new Language[] { Language.EN_US };
  }
}

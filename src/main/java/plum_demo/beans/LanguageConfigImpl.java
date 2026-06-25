package plum_demo.beans;

import com.appslandia.common.base.Language;
import com.appslandia.plum.base.LanguageConfig;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class LanguageConfigImpl implements LanguageConfig {

  @Override
  public Language[] getLanguages() {
    return new Language[] { Language.EN_US, Language.VI_VN };
  }
}

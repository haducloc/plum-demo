package plum_demo.beans;

import com.appslandia.common.base.Language;
import com.appslandia.plum.base.DynHandlerRegister;

/**
 * This is NOT CDI bean
 * 
 * @author Loc Ha
 *
 */
public class DynHandlerRegisterImpl implements DynHandlerRegister {

  @Override
  public Language[] getSupportedLanguages() {
    return new Language[] { Language.EN_US, Language.VI_VN };
  }

  @Override
  public String getSupportedViews() {
    return "*.jsp, *.xhtml";
  }
}

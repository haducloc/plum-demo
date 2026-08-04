package plum_demo.beans;

import com.appslandia.plum.base.DynHandlerRegister;
import com.appslandia.plum.base.ModuleConfigProvider;

/**
 * This is NOT CDI bean
 * 
 * @author Loc Ha
 *
 */
public class DynHandlerRegisterImpl implements DynHandlerRegister {

  @Override
  public ModuleConfigProvider getModuleConfigProvider() {
    return new ModuleConfigProviderImpl();
  }

  @Override
  public String getSupportedViews() {
    return "*.jsp, *.xhtml";
  }
}

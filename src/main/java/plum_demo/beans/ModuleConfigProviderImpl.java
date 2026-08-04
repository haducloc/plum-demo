package plum_demo.beans;

import com.appslandia.common.base.Language;
import com.appslandia.plum.base.ModuleConfig;
import com.appslandia.plum.base.ModuleConfigProvider;

import jakarta.enterprise.context.ApplicationScoped;
import plum_demo.utils.Modules;

/**
 * 
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class ModuleConfigProviderImpl extends ModuleConfigProvider {

  @Override
  protected void init() throws Exception {

    registerModuleConfig(Modules.ADMIN, new ModuleConfig().registerLanguages(Language.EN_US));

    registerModuleConfig(Modules.API, new ModuleConfig().registerLanguages(Language.EN_US));

    // setFallbackModule(ModuleConfig.MODULE_NONE);
    setFallbackModule(Modules.ADMIN);
    super.init();
  }

}

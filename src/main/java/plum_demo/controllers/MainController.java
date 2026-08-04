package plum_demo.controllers;

import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.AppLogger;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.EnableCompress;
import com.appslandia.plum.base.Home;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.base.Module;
import com.appslandia.plum.results.ViewResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import plum_demo.utils.Modules;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Home
@Module(Modules.ADMIN)
public class MainController extends ControllerBase {

  @Inject
  protected AppLogger appLogger;

  @HttpGet
  @EnableCompress
  public ActionResult index(HttpRequestFacade request) throws Exception {
    return ViewResult.DEFAULT;
  }
}

package plum_demo.controllers;

import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.EnableAsync;
import com.appslandia.plum.base.EnableCompress;
import com.appslandia.plum.base.Home;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.results.ViewResult;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Home
@EnableCompress
public class MainController extends ControllerBase {

  @HttpGet
  @EnableAsync(dispatchToView = true)
  public ActionResult index(HttpRequestFacade request) throws Exception {
    return ViewResult.DEFAULT;
  }
}

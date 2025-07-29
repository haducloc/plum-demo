package plum_demo.controllers;

import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.Home;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.RequestWrapper;
import com.appslandia.plum.results.ViewResult;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Controller
@Home
public class MainController {

  @HttpGet
  public ActionResult index(RequestWrapper request) throws Exception {
    return ViewResult.DEFAULT;
  }
}

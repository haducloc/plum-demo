package plum_demo.controllers;

import java.io.StringWriter;

import com.appslandia.plum.base.AppLogger;
import com.appslandia.plum.base.ConsumeType;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.HttpPost;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.base.Module;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import plum_demo.utils.Modules;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Module(Modules.ADMIN)
public class CspReportController extends ControllerBase {

  @Inject
  protected AppLogger appLogger;

  @HttpPost
  @ConsumeType("application/csp-report")
  public void index(HttpRequestFacade request, HttpServletResponse response) throws Exception {
    var out = new StringWriter(512);
    request.getReader().transferTo(out);

    appLogger.warn(out.toString());
  }
}

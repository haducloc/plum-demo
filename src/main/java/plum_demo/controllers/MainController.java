package plum_demo.controllers;

import com.appslandia.plum.base.ActionResult;
import com.appslandia.plum.base.Authorize;
import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.Home;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.RequestAccessor;
import com.appslandia.plum.results.JspResult;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
@Controller
@Authorize
@Home
public class MainController {

    @HttpGet
    public ActionResult index(RequestAccessor request) throws Exception {
	return JspResult.DEFAULT;
    }
}

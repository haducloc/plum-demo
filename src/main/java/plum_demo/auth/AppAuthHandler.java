package plum_demo.auth;

import com.appslandia.common.base.MappedID;
import com.appslandia.plum.base.FormAuthHandler;
import com.appslandia.plum.base.Modules;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
@MappedID(Modules.DEFAULT)
public class AppAuthHandler extends FormAuthHandler {
}

package plum_demo.auth;

import com.appslandia.common.base.MappedID;
import com.appslandia.plum.base.JwtHttpAuthMechanism;

import jakarta.enterprise.context.ApplicationScoped;
import plum_demo.utils.Modules;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@MappedID(Modules.API)
public class ApiHttpAuthMechanism extends JwtHttpAuthMechanism {
}

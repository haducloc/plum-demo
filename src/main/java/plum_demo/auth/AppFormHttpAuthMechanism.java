package plum_demo.auth;

import com.appslandia.common.base.MappedID;
import com.appslandia.plum.base.FormHttpAuthMechanism;
import com.appslandia.plum.base.Modules;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.RememberMe;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)

@MappedID(Modules.DEFAULT)
@AutoApplySession

//@formatter:off
@RememberMe(
  isRememberMeExpression = "#{self.isRememberMe(httpMessageContext)}",
  cookieName = "#{self.remMeCookieName()}",
  cookieMaxAgeSecondsExpression = "#{self.remMeCookieAge()}",
  cookieSecureOnlyExpression="#{self.remMeCookieSecure()}",
  cookieHttpOnlyExpression="#{self.remMeCookieHttpOnly()}"
)
//@formatter:on
public class AppFormHttpAuthMechanism extends FormHttpAuthMechanism {
}

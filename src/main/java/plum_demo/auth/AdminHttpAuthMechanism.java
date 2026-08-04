package plum_demo.auth;

import com.appslandia.common.base.MappedID;
import com.appslandia.plum.base.FormHttpAuthMechanism;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.RememberMe;
import plum_demo.utils.Modules;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@MappedID(Modules.ADMIN)
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
public class AdminHttpAuthMechanism extends FormHttpAuthMechanism {
}

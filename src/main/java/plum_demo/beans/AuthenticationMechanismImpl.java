package plum_demo.beans;

import com.appslandia.plum.base.HttpAuthenticationMechanismBase;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.RememberMe;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@AutoApplySession

//@formatter:off
@RememberMe(
	isRememberMeExpression = "#{self.rememberMe(httpMessageContext)}",
	cookieName = "#{self.rememberMeCookieName()}",
	cookieMaxAgeSecondsExpression = "#{self.rememberMeCookieAge()}",
	cookieSecureOnlyExpression="#{self.rememberMeCookieSecure()}",
	cookieHttpOnlyExpression="#{self.rememberMeCookieHttpOnly()}"
)
//@formatter:on
public class AuthenticationMechanismImpl extends HttpAuthenticationMechanismBase {
}

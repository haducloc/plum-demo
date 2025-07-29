package plum_demo.beans;

import com.appslandia.plum.base.AppContextInitializer;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.interceptor.Interceptor;
import jakarta.servlet.ServletContext;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class AppContextInitializerEnsurer {

  // ISSUE: https://github.com/payara/Payara/issues/5968

  // AppContextInitializer won't get executed if PAYARA used
  // Reason: PAYARA doesn't execute @Observes
  // @Initialized(ApplicationScoped.class) ServletContext that included inside
  // Jar/library files

  // AppContextInitializer will get executed if WIFDFLY used
  // so, we don't need this AppContextInitializerEnsurer for WILDFLY

  public void contextInitialized(
      @Observes @Initialized(ApplicationScoped.class) @Priority(Interceptor.Priority.LIBRARY_BEFORE
          - 501) ServletContext sc) {

    new AppContextInitializer().contextInitialized(sc);
  }

  public void contextDestroyed(
      @Observes @Destroyed(ApplicationScoped.class) @Priority(Interceptor.Priority.LIBRARY_BEFORE
          + 501) ServletContext sc) {

    new AppContextInitializer().contextDestroyed(sc);
  }
}

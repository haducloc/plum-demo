package plum_demo.beans;

import com.appslandia.common.logging.AppLogger;
import com.appslandia.plum.base.BeanInstanceContextListener;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
public class BeanInstanceContextListenerEnsurer {

    @Inject
    protected AppLogger appLogger;

    // ISSUE: https://github.com/payara/Payara/issues/5968

    // BeanInstanceContextListener won't get executed if PAYARA used
    // Reason: PAYARA doesn't execute @Observes @Initialized(ApplicationScoped.class) ServletContext that included inside
    // Jar/library files

    // BeanInstanceContextListener will get executed if WIFDFLY used
    // so, we don't need this BeanInstanceContextListenerEnsurer for WILDFLY

    public void contextDestroyed(@Observes @Destroyed(ApplicationScoped.class) ServletContext sc) {
	BeanInstanceContextListener.destroyBeanInstances(sc);
    }
}

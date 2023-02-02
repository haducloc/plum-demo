package plum_demo.beans;

import com.appslandia.common.crypto.PasswordDigester;
import com.appslandia.common.logging.AppLogger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.ServletContext;
import plum_demo.entities.User;
import plum_demo.utils.DbUtils;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
public class DatabaseInitializer {

    @PersistenceUnit(unitName = DbUtils.PU_MYDB)
    protected EntityManagerFactory emf;

    @Inject
    protected AppLogger appLogger;

    public void loadData(@Observes @Initialized(ApplicationScoped.class) ServletContext ctx) {
	try (EntityManager em = emf.createEntityManager()) {
	    EntityTransaction tx = null;

	    try {
		tx = em.getTransaction();
		tx.begin();

		// Add ADMIN user with ADMIN role
		em.persist(new User(DbUtils.USER_ADMIN, PasswordDigester.DEFAULT.digest("password"), "ADMIN", true));

		tx.commit();

	    } catch (Exception ex) {
		appLogger.error(ex);

		if (tx != null && tx.isActive())
		    tx.rollback();
	    }
	}
    }
}

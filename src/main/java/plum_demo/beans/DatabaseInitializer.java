package plum_demo.beans;

import java.time.LocalDate;

import com.appslandia.common.base.AppLogger;
import com.appslandia.common.jpa.JpaEntityManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import plum_demo.entities.User;
import plum_demo.utils.PasswordUtils;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@Dependent
public class DatabaseInitializer {

  @Inject
  private JpaEntityManager em;

  @Inject
  protected AppLogger appLogger;

  @Transactional
  public void loadData(@Observes @Initialized(ApplicationScoped.class) ServletContext ctx) {
    appLogger.info("Initializing admin and user accounts.");
    try {
      var user = new User(UserUtils.USER_ADMIN, PasswordUtils.hashPassword("password"), UserUtils.ROLE_ADMIN);
      user.setDob(LocalDate.of(2000, 1, 1));
      em.persist(user);

      user = new User("locha", PasswordUtils.hashPassword("password"), UserUtils.ROLE_MANAGER);
      user.setDob(LocalDate.of(2000, 1, 2));
      em.persist(user);

      for (var i = 1; i <= 100; i++) {
        user = new User("user" + i, PasswordUtils.hashPassword("password"), UserUtils.ROLE_MANAGER);
        user.setDob(LocalDate.of(2000, 1, (i - 1) % 30 + 1));

        if (i % 3 == 0) {
          user.setActive(false);
        }
        em.persist(user);
      }

      // em.flush();
    } catch (Exception ex) {
      appLogger.error(ex);
    }
  }
}

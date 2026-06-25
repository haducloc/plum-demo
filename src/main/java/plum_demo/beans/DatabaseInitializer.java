package plum_demo.beans;

import java.time.LocalDate;

import com.appslandia.common.utils.RandomUtils;
import com.appslandia.common.utils.SecureRand;
import com.appslandia.plum.base.AppLogger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import plum_demo.entities.User;
import plum_demo.services.UserService;
import plum_demo.utils.DivisionUtils;
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
  private UserService userService;

  @Inject
  protected AppLogger appLogger;

  public void loadData(@Observes @Initialized(ApplicationScoped.class) ServletContext ctx) {
    appLogger.info("Initializing admin and user accounts.");

    try {
      var user = new User("admin", PasswordUtils.hashPassword("password"), DivisionUtils.DIVISION_IT,
          UserUtils.ROLE_ADMIN);

      user.setDob(LocalDate.of(2000, 1, 1));
      userService.save(user);

      user = new User("locha", PasswordUtils.hashPassword("password"), DivisionUtils.DIVISION_IT,
          UserUtils.ROLE_MANAGER);

      user.setDob(LocalDate.of(2000, 1, 2));
      userService.save(user);

      for (var i = 1; i <= 100; i++) {

        int divisionId = RandomUtils.nextInt(1, 3, SecureRand.getInstance());

        user = new User("user" + i, PasswordUtils.hashPassword("password"), divisionId, UserUtils.ROLE_MANAGER);
        user.setDob(LocalDate.of(2000, 1, (i - 1) % 30 + 1));

        if (i % 3 == 0) {
          user.setActive(false);
        }
        userService.save(user);
      }

    } catch (Exception ex) {
      appLogger.error(ex);
    }
  }
}

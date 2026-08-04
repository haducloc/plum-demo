package plum_demo.services;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.appslandia.common.base.Out;
import com.appslandia.common.utils.Asserts;
import com.appslandia.common.utils.ModelUtils;
import com.appslandia.plum.base.PagerState;
import com.appslandia.plum.base.SortState;

import jakarta.enterprise.context.ApplicationScoped;
import plum_demo.entities.User;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class UserService {

  private static final Map<Integer, User> USERS = new LinkedHashMap<>();
  private static final AtomicInteger USER_ID_SEQ = new AtomicInteger(0);

  public User getByPk(int userId) {
    synchronized (USERS) {
      return copy(USERS.get(userId));
    }
  }

  public User getByUsername(String username) {
    synchronized (USERS) {
      return USERS.values().stream().filter(e -> e.getUsername().equalsIgnoreCase(username)).map(this::copy).findFirst()
          .orElse(null);
    }
  }

  public List<User> queryUsers(Integer divisionId, String username, PagerState pagerState, SortState sortState,
      Out<Integer> recordCount) {

    synchronized (USERS) {

      // recordCount
      if (recordCount.value == null || recordCount.value < 0) {
        recordCount.value = (int) USERS.values().stream()
            .filter(e -> divisionId == null || e.getDivisionId() == divisionId)
            .filter(e -> username == null || e.getUsername().toLowerCase().contains(username.toLowerCase())).count();
      }

      var query = USERS.values().stream().filter(e -> divisionId == null || e.getDivisionId() == divisionId)
          .filter(e -> username == null || e.getUsername().toLowerCase().contains(username.toLowerCase()));

      // Sorting
      if (sortState.isForField("username")) {
        query = sortState.isSortAsc() ? query.sorted(Comparator.comparing(User::getUsername))
            : query.sorted(Comparator.comparing(User::getUsername).reversed());

      } else if (sortState.isForField("dob")) {
        query = sortState.isSortAsc() ? query.sorted(Comparator.comparing(User::getDob))
            : query.sorted(Comparator.comparing(User::getDob).reversed());
      } else {

        query = query.sorted(Comparator.comparing(User::getUsername));
      }

      return query.skip(pagerState.getSkipCount()).limit(pagerState.getPageSize()).map(this::copy).toList();
    }
  }

  public int save(User user) {
    synchronized (USERS) {
      if (user.getUserId() == null) {

        var dbUser = copy(user);
        dbUser.setUserId(USER_ID_SEQ.incrementAndGet());

        USERS.put(dbUser.getUserId(), dbUser);
        return dbUser.getUserId();
      }

      var dbUser = USERS.get(user.getUserId());

      Asserts.notNull(dbUser);
      Asserts.isTrue(!"admin".equalsIgnoreCase(dbUser.getUsername()), "The 'admin' user cannot be modified.");

      ModelUtils.copyProps(dbUser, user, "roles", "active", "dob", "divisionId", "notes");

      return dbUser.getUserId();
    }
  }

  public void remove(int userId) {
    synchronized (USERS) {
      var dbUser = USERS.get(userId);

      Asserts.notNull(dbUser);
      Asserts.isTrue(!"admin".equalsIgnoreCase(dbUser.getUsername()), "The 'admin' user cannot be modified.");

      USERS.remove(userId);
    }
  }

  private User copy(User src) {
    if (src == null) {
      return null;
    }

    var user = new User();
    user.setUserId(src.getUserId());
    user.setUsername(src.getUsername());
    user.setDivisionId(src.getDivisionId());
    user.setPassword(src.getPassword());
    user.setRoles(src.getRoles());
    user.setActive(src.isActive());
    user.setDob(src.getDob());
    user.setNotes(src.getNotes());

    return user;
  }
}
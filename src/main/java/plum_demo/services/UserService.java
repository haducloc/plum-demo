package plum_demo.services;

import java.util.List;

import com.appslandia.common.jpa.JpaEntityManager;
import com.appslandia.common.jpa.JpaQuery;
import com.appslandia.common.utils.Asserts;
import com.appslandia.common.utils.ModelUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import plum_demo.entities.User;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class UserService {

  @Inject
  protected JpaEntityManager em;

  public User getByPk(int userId) {
    return em.find(User.class, userId);
  }

  public User getByUsername(String username) {
    var q = em.createQuery("SELECT e FROM User e WHERE e.username = :username", User.class);
    q.setParameter("username", username);

    try {
      return q.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  private static final JpaQuery JQL_GET_USERS_COUNT = new JpaQuery("""
        SELECT COUNT(e) FROM User e
        WHERE (:inclInactive = true OR e.active = true)
        AND (:username IS NULL OR e.username LIKE :username)
      """);

  public int getUsersCount(boolean inclInactive, String username) {
    var q = em.createQuery(JQL_GET_USERS_COUNT, Long.class);

    q.setParameter("inclInactive", inclInactive);
    q.setLike("username", username);

    return q.getSingleResult().intValue();
  }

  private static final JpaQuery JQL_QUERY_USERS = new JpaQuery("""
        SELECT e FROM User e
        WHERE (:inclInactive = true OR e.active = true)
        AND (:username IS NULL OR e.username LIKE :username)
      """);

  public List<User> queryUsers(boolean inclInactive, String username, int pageIndex, int pageSize) {
    var q = em.createQuery(JQL_QUERY_USERS, User.class).setFirstResult((pageIndex - 1) * pageSize)
        .setMaxResults(pageSize);

    q.setParameter("inclInactive", inclInactive);
    q.setLike("username", username);

    return q.getResultList();
  }

  @Transactional
  public int save(User user) {
    if (user.getUserId() == null) {
      em.persist(user);
    } else {
      var dbUser = em.find(User.class, user.getUserId());

      Asserts.notNull(dbUser);
      Asserts.isTrue(!UserUtils.USER_ADMIN.equalsIgnoreCase(dbUser.getUsername()),
          "The 'admin' user cannot be modified.");

      ModelUtils.copyProps(dbUser, user, "roles", "active", "dob", "salary");
    }
    return user.getUserId();
  }

  @Transactional
  public void remove(int userId) {
    var dbUser = em.getReference(User.class, userId);
    Asserts.isTrue(!UserUtils.USER_ADMIN.equalsIgnoreCase(dbUser.getUsername()),
        "The 'admin' user cannot be modified.");
    em.remove(dbUser);
  }
}

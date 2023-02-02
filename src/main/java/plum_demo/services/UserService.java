package plum_demo.services;

import java.util.List;

import com.appslandia.common.utils.AssertUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import plum_demo.entities.User;
import plum_demo.utils.DbUtils;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
public class UserService {

    @PersistenceUnit(unitName = DbUtils.PU_MYDB)
    protected EntityManagerFactory emf;

    public User getByPk(int userId) {
	try (EntityManager em = emf.createEntityManager()) {
	    return em.find(User.class, userId);
	}
    }

    public User getByUsername(String username) {
	try (EntityManager em = emf.createEntityManager()) {
	    TypedQuery<User> q = em.createQuery("SELECT e FROM User e WHERE e.username=:username", User.class);

	    try {
		return q.setParameter("username", username).getSingleResult();
	    } catch (NoResultException ex) {
		return null;
	    }
	}
    }

    public List<User> getAll() {
	try (EntityManager em = emf.createEntityManager()) {
	    TypedQuery<User> q = em.createQuery("SELECT e FROM User e", User.class);

	    return q.getResultList();
	}
    }

    public int save(User user) {
	try (EntityManager em = emf.createEntityManager()) {
	    EntityTransaction tx = null;

	    try {
		tx = em.getTransaction();
		tx.begin();

		if (user.getUserId() == null) {
		    em.persist(user);

		} else {
		    User dbUser = em.find(User.class, user.getUserId());
		    AssertUtils.assertStateNotNull(dbUser);
		    AssertUtils.assertTrue(!DbUtils.USER_ADMIN.equalsIgnoreCase(dbUser.getUsername()), "Unallowed to modify the ADMIN user.");

		    // Update roles & active only
		    dbUser.setRoles(user.getRoles());
		    dbUser.setActive(user.isActive());
		}
		tx.commit();

	    } catch (Exception ex) {
		if (tx != null && tx.isActive())
		    tx.rollback();

		throw ex;
	    }
	}
	return user.getUserId();
    }

    public void remove(int userId) {
	try (EntityManager em = emf.createEntityManager()) {
	    EntityTransaction tx = null;

	    try {
		tx = em.getTransaction();
		tx.begin();

		User dbUser = em.getReference(User.class, userId);

		// Business rules
		AssertUtils.assertTrue(!DbUtils.USER_ADMIN.equalsIgnoreCase(dbUser.getUsername()), "Unallowed to remove the ADMIN user.");

		em.remove(dbUser);

		tx.commit();

	    } catch (Exception ex) {
		if (tx != null && tx.isActive())
		    tx.rollback();

		throw ex;
	    }
	}
    }
}

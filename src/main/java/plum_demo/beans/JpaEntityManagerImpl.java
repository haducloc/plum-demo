package plum_demo.beans;

import com.appslandia.common.jpa.JpaEntityManager;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Typed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Loc Ha
 *
 */
@Dependent
@Typed(JpaEntityManager.class)
public class JpaEntityManagerImpl extends JpaEntityManager {

  @PersistenceContext(unitName = "myDbPU")
  private EntityManager em;

  @Override
  protected EntityManager em() {
    return this.em;
  }
}

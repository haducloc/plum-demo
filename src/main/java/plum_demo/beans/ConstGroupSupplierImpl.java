package plum_demo.beans;

import java.util.Set;

import com.appslandia.common.cdi.CDISupplier;
import com.appslandia.common.cdi.Supplier;
import com.appslandia.plum.base.ConstGroup;

import jakarta.enterprise.context.ApplicationScoped;
import plum_demo.utils.DivisionUtils;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Supplier(ConstGroup.class)
public class ConstGroupSupplierImpl implements CDISupplier {

  @Override
  public Set<Class<?>> get() {
    return Set.of(DivisionUtils.class);
  }
}

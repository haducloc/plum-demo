package plum_demo.utils;

import java.util.List;

import com.appslandia.common.utils.CollectionUtils;
import com.appslandia.plum.base.SelectItem;

/**
 *
 * @author Loc Ha
 *
 */
public class UserUtils {

  public static final String USER_ADMIN = "admin";

  public static final String ROLE_ADMIN = "admin";
  public static final String ROLE_MANAGER = "manager";

  public static final List<SelectItem> USER_ROLES = CollectionUtils
      .unmodifiableList(new SelectItem(ROLE_ADMIN, "admin"), new SelectItem(ROLE_MANAGER, "manager"));
}

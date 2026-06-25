package plum_demo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appslandia.common.utils.CollectionUtils;
import com.appslandia.plum.base.AuthPrincipal;
import com.appslandia.plum.base.SelectItem;

import plum_demo.entities.User;

/**
 *
 * @author Loc Ha
 *
 */
public class UserUtils {

  public static final String ROLE_ADMIN = "admin";
  public static final String ROLE_MANAGER = "manager";

  public static final List<SelectItem> ROLE_LIST = CollectionUtils.unmodifiableList(new SelectItem(ROLE_ADMIN, "admin"),
      new SelectItem(ROLE_MANAGER, "manager"));

  public static Map<String, Object> toPrincipalAttributes(User user) {
    Map<String, Object> map = new HashMap<>();

    map.put(AuthPrincipal.ATTRIBUTE_SUB, user.getUserId());
    map.put(AuthPrincipal.ATTRIBUTE_DISPLAY_NAME, user.getUsername());
    return map;
  }
}

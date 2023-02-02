package plum_demo.auth;

import java.util.HashMap;
import java.util.Map;

import com.appslandia.plum.base.UserPrincipal;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
public class AppPrincipal extends UserPrincipal {
    private static final long serialVersionUID = 1L;

    public AppPrincipal(int userId, String username) {
	super(username, createAttributes(userId, username));
    }

    static Map<String, Object> createAttributes(int userId, String username) {
	Map<String, Object> map = new HashMap<>();

	map.put(USER_ID, userId);
	map.put(USER_NAME, username);

	return map;
    }
}

package plum_demo.utils;

import com.appslandia.common.crypto.PasswordDigester;
import com.appslandia.common.crypto.PasswordDigesterImpl;

/**
 *
 * @author Loc Ha
 *
 */
public class PasswordUtils {

  static final PasswordDigester PASSWORD_DIGESTER = new PasswordDigesterImpl();

  public static String hashPassword(String password) {
    return PASSWORD_DIGESTER.digest(password);
  }

  public static boolean verifyPassword(String password, String hashedPassword) {
    return PASSWORD_DIGESTER.verify(password, hashedPassword);
  }
}

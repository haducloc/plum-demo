package plum_demo.auth;

import jakarta.security.enterprise.credential.UsernamePasswordCredential;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
public class AppCredential extends UsernamePasswordCredential {

    public AppCredential(String username, String password) {
	super(username, password);
    }
}

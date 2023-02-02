package plum_demo.auth;

import com.appslandia.plum.base.UsernamePasswordIdentityStore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
public class AppIdentityStore extends UsernamePasswordIdentityStore {

    @Override
    public Class<? extends Credential> getAcceptedCredentialType() {

	// This IdentityStore only accepts AppCredential type.
	return AppCredential.class;
    }
}

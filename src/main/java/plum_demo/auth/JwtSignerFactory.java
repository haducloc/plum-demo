// Licensed under the MIT License.
// See LICENSE file in the project root for details.

package plum_demo.auth;

import com.appslandia.common.cdi.CDIFactory;
import com.appslandia.common.jose.HsJwtSigner;
import com.appslandia.common.jose.JwtSigner;
import com.appslandia.common.json.JsonProcessor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class JwtSignerFactory implements CDIFactory<JwtSigner> {

  @Inject
  protected JsonProcessor jsonProcessor;

  @Produces
  @ApplicationScoped
  @Override
  public JwtSigner produce() {
    var impl = HsJwtSigner.HS256().setJsonProcessor(jsonProcessor).setSecret("secret".getBytes()).setIss("Issuer1")
        .build();
    return impl;
  }

  @Override
  public void dispose(@Disposes JwtSigner impl) {
  }
}

package plum_demo.beans;

import com.appslandia.common.base.DeployEnv;
import com.appslandia.common.cdi.CDIFactory;
import com.appslandia.plum.base.CspPolicy;
import com.appslandia.plum.base.HeaderPolicy;
import com.appslandia.plum.base.HeaderPolicyProvider;
import com.appslandia.plum.base.HtmlHeaderPolicy;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.interceptor.Interceptor;

/**
 * 
 * @author Loc Ha
 *
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class AppPolicyProviderImpl implements CDIFactory<HeaderPolicyProvider> {

  @Produces
  @ApplicationScoped
  @Override
  public HeaderPolicyProvider produce() {
    var impl = new HeaderPolicyProvider();

    // Apply to all resources
    impl.registerHttpPolicy(new HeaderPolicy().includeAll().setHeaders("""
        X-Content-Type-Options: nosniff
        X-DNS-Prefetch-Control: off
        Cross-Origin-Resource-Policy: same-origin
        Cache-Control: no-store
        ENV.Production.Strict-Transport-Security: max-age=31536000; includeSubDomains; preload

        """));

    // Content Security Policy (CSP)
    var cspPolicy = new CspPolicy().setCsp("""
        default-src 'self'
        script-src 'self' 'nonce-${__nonce}' https://cdn.jsdelivr.net https://unpkg.com
        style-src 'self' 'nonce-${__nonce}' https://cdn.jsdelivr.net https://unpkg.com
        font-src 'self' https://cdn.jsdelivr.net https://unpkg.com

        img-src 'self' data: blob:

        // ENV parameters
        connect-src ${__CONNECT_SRC}

        worker-src 'self' blob:
        frame-ancestors 'none'
        form-action 'self'
        object-src 'none'
        base-uri 'self'
        upgrade-insecure-requests

        // CSP reporting
        report-to csp-endpoint

        """).setEnvParams(DeployEnv.PRODUCTION.getName(), """
        __CONNECT_SRC: 'self' https://cdn.jsdelivr.net https://unpkg.com

        """)
        .setEnvParams(DeployEnv.DEVELOPMENT.getName(),
            """
                __CONNECT_SRC: 'self' https://cdn.jsdelivr.net https://unpkg.com http://localhost:* https://localhost:* ws://localhost:* wss://localhost:*

                """)
        .setReportingEndpoints("""
            csp-endpoint="${__mvc_base_url}/csp-report"

            """);

    // HTML pages
    impl.registerHttpPolicy(new HtmlHeaderPolicy().setIncludePaths("""
        /
        /main/**
        /auth/**
        /user/**

        """).setHeaders("""
        Permissions-Policy: geolocation=(), microphone=(), camera=(), fullscreen=(self)
        Referrer-Policy: strict-origin-when-cross-origin
        X-Frame-Options: DENY
        X-XSS-Protection: 0

        """).setCspPolicy(cspPolicy));

    // Static JS/CSS (mutable): 2 weeks
    impl.registerHttpPolicy(new HeaderPolicy().setIncludePaths("""
        /static/**

        """).setHeaders("""
        Cache-Control: public, max-age=1209600, must-revalidate
        """));

    // API (mutable): 1 day
    impl.registerHttpPolicy(new HeaderPolicy().setIncludePaths("""
        /api/users

        """).setHeaders("""
        Cache-Control: private, max-age=86400, must-revalidate
        """));

    return impl;
  }

  @Override
  public void dispose(@Disposes HeaderPolicyProvider impl) {
  }
}

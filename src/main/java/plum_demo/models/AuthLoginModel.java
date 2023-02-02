package plum_demo.models;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
public class AuthLoginModel {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private boolean rememberMe;

    public String getUsername() {
	return this.username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public boolean isRememberMe() {
	return this.rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
	this.rememberMe = rememberMe;
    }
}

package plum_demo.models;

import java.time.LocalDate;

import com.appslandia.common.converters.Converter;
import com.appslandia.common.validators.CheckValue;
import com.appslandia.plum.base.BindValue;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Loc Ha
 *
 */
public class UserEditModel {

  private Integer userId;

  @NotNull
  // @Username
  private String username;

  // @Password
  private String password;

  @BindValue(conv = Converter.USER_ROLES)
  @NotNull
  @CheckValue("['admin', 'manager'].contains(value)")
  // @MultiValues({ "admin", "manager" })
  private String roles;

  private boolean active;

  private LocalDate dob;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }
}

package plum_demo.models;

import java.time.LocalDate;

import com.appslandia.common.validators.MaxLength;
import com.appslandia.common.validators.MultiString;
import com.appslandia.plum.base.ResourcePrefix;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Loc Ha
 *
 */
@ResourcePrefix("user")
public class UserEditModel {

  private Integer userId;

  @NotNull
  private String username;

  private String password;

  @NotNull
  private Integer divisionId;

  @NotNull
  @MultiString({ "admin", "manager" })
  private String roles;

  private boolean active = false;

  // Optional
  private LocalDate dob;

  // Optional
  @MaxLength(4000)
  private String Notes;

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

  public Integer getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(Integer divisionId) {
    this.divisionId = divisionId;
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

  public String getNotes() {
    return Notes;
  }

  public void setNotes(String notes) {
    Notes = notes;
  }
}

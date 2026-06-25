package plum_demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.appslandia.common.validators.MaxLength;
import com.appslandia.common.validators.MultiString;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Loc Ha
 *
 */
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  // PK
  private Integer userId;

  @NotNull
  // @Username
  private String username;

  @NotNull
  private String password;

  @NotNull
  private Integer divisionId;

  @NotNull
  @MultiString({ "admin", "manager" })
  private String roles;

  private boolean active = true;

  private LocalDate dob;

  @MaxLength(4000)
  private String notes;

  public User() {
  }

  public User(String username, String password, int divisionId, String roles) {
    this.username = username;
    this.password = password;
    this.divisionId = divisionId;
    this.roles = roles;
  }

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
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}

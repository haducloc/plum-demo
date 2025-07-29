package plum_demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.appslandia.common.validators.MultiValues;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import plum_demo.utils.UserUtils;

/**
 *
 * @author Loc Ha
 *
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;

  @NotNull
  // @Username
  @Column(length = 128, nullable = false, unique = true)
  private String username;

  @NotNull
  @Column(nullable = false)
  private String password;

  @NotNull
  @MultiValues({ UserUtils.ROLE_ADMIN, UserUtils.ROLE_MANAGER })
  private String roles;

  private boolean active = true;

  private LocalDate dob;

  public User() {
  }

  public User(String username, String password, String roles) {
    this.username = username;
    this.password = password;
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

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean isActive) {
    this.active = isActive;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }
}

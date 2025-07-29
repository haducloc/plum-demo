package plum_demo.models;

import com.appslandia.plum.base.BindValue;

/**
 *
 * @author Loc Ha
 *
 */
public class UserIndexModel {

  private String username;

  private boolean inclInactive;

  @BindValue(name = "__page_index")
  private Integer pageIndex;

  @BindValue(name = "__record_count")
  private Integer recordCount;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isInclInactive() {
    return inclInactive;
  }

  public void setInclInactive(boolean inclInactive) {
    this.inclInactive = inclInactive;
  }

  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public Integer getRecordCount() {
    return recordCount;
  }

  public void setRecordCount(Integer recordCount) {
    this.recordCount = recordCount;
  }
}

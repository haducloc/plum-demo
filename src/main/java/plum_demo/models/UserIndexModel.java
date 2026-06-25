package plum_demo.models;

import com.appslandia.plum.base.BindValue;

/**
 *
 * @author Loc Ha
 *
 */
public class UserIndexModel {

  private String username;

  private Integer divisionId;

  @BindValue(name = "__page_index")
  private Integer pageIndex;

  @BindValue(name = "__record_count")
  private Integer recordCount;

  @BindValue(name = "__sort_by")
  private String sortBy;

  @BindValue(name = "__sort_asc")
  private Boolean sortAsc;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(Integer divisionId) {
    this.divisionId = divisionId;
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

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public Boolean getSortAsc() {
    return sortAsc;
  }

  public void setSortAsc(Boolean sortAsc) {
    this.sortAsc = sortAsc;
  }
}

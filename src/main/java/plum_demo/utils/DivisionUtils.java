package plum_demo.utils;

import java.util.List;

import com.appslandia.common.utils.CollectionUtils;
import com.appslandia.plum.base.ConstGroup;
import com.appslandia.plum.base.SelectItem;

/**
 *
 * @author Loc Ha
 *
 */
public class DivisionUtils {

  @ConstGroup("divisions")
  public static final int DIVISION_IT = 1;

  @ConstGroup("divisions")
  public static final int DIVISION_HR = 2;

  @ConstGroup("divisions")
  public static final int DIVISION_WL = 3;

  public static final List<SelectItem> LIST_IDS = CollectionUtils.unmodifiableList(new SelectItem(DIVISION_IT, "IT"),
      new SelectItem(DIVISION_HR, "HR"), new SelectItem(DIVISION_WL, "Wildlife"));
}

package plum_demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.appslandia.common.utils.ParseUtils;
import com.appslandia.plum.base.ControllerBase;
import com.appslandia.plum.base.EnableAsync;
import com.appslandia.plum.base.EnableCompress;
import com.appslandia.plum.base.EnableEtag;
import com.appslandia.plum.base.HttpGet;
import com.appslandia.plum.base.HttpRequestFacade;
import com.appslandia.plum.base.SelectItem;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Loc Ha
 *
 */
@ApplicationScoped
public class TestController extends ControllerBase {

  @HttpGet
  public List<SelectItem> list1(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableCompress
  public List<SelectItem> list2(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableEtag
  public List<SelectItem> list3(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableEtag
  @EnableCompress
  public List<SelectItem> list4(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableAsync
  public List<SelectItem> list11(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableCompress
  @EnableAsync
  public List<SelectItem> list21(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableEtag
  @EnableAsync
  public List<SelectItem> list31(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  @HttpGet
  @EnableEtag
  @EnableCompress
  @EnableAsync
  public List<SelectItem> list41(HttpRequestFacade request) throws Exception {
    return createList(request);
  }

  protected List<SelectItem> createList(HttpRequestFacade request) {
    var listSize = request.getParameter("listSize");
    var size = ParseUtils.parseInt(listSize, 5000);

    var list = new ArrayList<SelectItem>(size);
    for (int i = 1; i <= size; i++) {
      list.add(new SelectItem(i, "Select Item-" + i));
    }
    return list;
  }
}

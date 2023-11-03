package plum_demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.appslandia.plum.base.Controller;
import com.appslandia.plum.base.EnableEtag;
import com.appslandia.plum.base.EnableGzip;
import com.appslandia.plum.base.HttpGet;

import jakarta.enterprise.context.ApplicationScoped;
import plum_demo.entities.User;

/**
 *
 * @author <a href="mailto:haducloc13@gmail.com">Loc Ha</a>
 *
 */
@ApplicationScoped
@Controller
public class TestController {

    @HttpGet
    public List<User> index() throws Exception {
	return createUsers();
    }

    @HttpGet
    @EnableGzip
    public List<User> gzip() throws Exception {
	return createUsers();
    }

    @HttpGet
    @EnableEtag
    public List<User> etag() throws Exception {
	return createUsers();
    }

    @HttpGet
    @EnableGzip
    @EnableEtag
    public List<User> gzip_etag() throws Exception {
	return createUsers();
    }

    protected List<User> createUsers() {
	List<User> list = new ArrayList<>(5000);

	for (int i = 0; i < 5000; i++) {
	    list.add(new User("username-" + i, "password-" + i, "userroles-" + i));
	}
	return list;
    }
}

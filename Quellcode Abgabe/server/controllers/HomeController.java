package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Test Controller
 * @author Ahmed Salame
 *
 */
public class HomeController extends Controller {

	public Result test() {
		return ok("Einfacher Text").as("text/plain");
	}
}

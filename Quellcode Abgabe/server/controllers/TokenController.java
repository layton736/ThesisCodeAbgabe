package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class TokenController extends Controller {

	public Result authorize() {

		return ok();
	}

	public Result isValid() {
		
		
		return ok("");

	}

	public Result createToken() {

		return ok("");
	}

}

package eagermodul;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import token.TokenService;

public class ControllerUtil extends Controller {

	private static boolean DEBUG = true;

	private static boolean checkJsonFields(JsonNode node, String... fields) {

		for (int i = 0; i < fields.length; i++) {

			if (DEBUG) {
				
			} else {
				if (node.has(fields[i])) {
					return true;
				}
			}

		}
		return false;

	}

	public static Result check(JsonNode node, String... fields) {

		if (node == null) {
			return badRequest("Expecting Json data");
		}
		if (checkJsonFields(node, fields)) {
			return badRequest("Missing parameters");
		}
		return null;
	}

}

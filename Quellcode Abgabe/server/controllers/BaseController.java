package controllers;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import token.TokenService;

public class BaseController extends Controller {
	public Result getBaseObjects() {

		ObjectNode json = Json.newObject();
		JsonNode req = request().body().asJson();

		Result result = ControllerUtil.check(req, "token");
		if (result != null) {
			return result;
		}

		String token = req.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		json.put("status", "ok");
		json.put("message", "list of bases");
		json.setAll(StartUpService.getServerGameObject().getAllBasesAsJSON("result"));

		return ok(json);
	}

	public Result spawnBaseGameObject() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "gamekey", "basename", "team");
		if (result != null) {
			return result;
		}

		String gamekey = json.findPath("gamekey").asText();
		String basename = json.findPath("basename").asText();
		String teamname = json.findPath("team").asText();

		StartUpService.getServerGameObject().createBaseGameObject(new Vector2(), basename, teamname);

		return ok("Spawn Base");
	}
}

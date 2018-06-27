package controllers;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import game.network.ServerGameObject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import token.TokenService;

public class ContainerController extends Controller {

	public Result getContainerList() {

		ServerGameObject serverGameObject = StartUpService.getServerGameObject();
		ObjectNode json = Json.newObject();

		Result result = ControllerUtil.check(json, "token");
		if (result != null) {
			return result;
		}
		String token = json.path("token").asText();

		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		json.put("status", "ok");
		json.put("message", "list of carriers");
		json.setAll(serverGameObject.getAllContainerAsJSON("result"));

		return ok(json);

	}

	public Result destroyContainer() {
		ServerGameObject serverGameObject = StartUpService.getServerGameObject();
		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "container");
		if (result != null) {
			return result;
		}

		String token = json.path("token").asText();

		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String container = json.findPath("container").textValue();

		serverGameObject.removeGameObject(container);

		return ok();
	}

	public Result dissolveContainer() {

		JsonNode json = request().body().asJson();
		
		Result result = ControllerUtil.check(json, "token","container");
		if (result != null) {
			return result;
		}
		String token = json.path("token").asText();

		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}
		
		String container = json.path("container").textValue();
		
		StartUpService.getServerGameObject().dissolveContainer(container);
		
		return ok();
	}
}

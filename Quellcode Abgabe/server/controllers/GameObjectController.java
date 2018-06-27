package controllers;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import game.network.ServerGameObject;
import play.mvc.Controller;
import play.mvc.Result;
import token.TokenService;

public class GameObjectController extends Controller {

	public Result spawnGameObjectMobile() {
		// TODO Gamekey?
		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "gamekey", "posx", "posy", "team", "gameobject");
		if (result != null) {
			return result;
		}

		String gamekey = json.findPath("gamekey").textValue();
		float posx = json.findPath("posx").floatValue();
		float posy = json.findPath("posy").floatValue();
		String team = json.findPath("team").textValue();
		String gameobject = json.findPath("gameobject").textValue();

		StartUpService.getServerGameObject().createMobileGameObject(new Vector2(posx, posy), team, gameobject);

		return ok("ok");
	}

	public Result getGameObjectList() {
		ServerGameObject serverGameObject = StartUpService.getServerGameObject();
		ObjectNode json = (ObjectNode) request().body().asJson();

		Result result = ControllerUtil.check(json, "token");
		if (result != null) {
			return result;
		}
		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		json.put("status", "ok");
		json.put("message", "list of game objects");
		json.setAll(serverGameObject.getAllMobileAsJSON());
		json.setAll(serverGameObject.getAllBasesAsJSON("bases"));
		json.setAll(serverGameObject.getAllRessourcesAsJSON("ressources"));

		return ok(json);
	}

	public Result updateGameObjectPosition() {
		// TODO Test
		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "x", "y", "gameobject");
		if (result != null) {
			return result;
		}

		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		float xPos = json.findPath("x").floatValue();
		float yPos = json.findPath("y").floatValue();
		String gameobjectName = json.findPath("gameobject").asText();

		StartUpService.getServerGameObject().updateGameObjectPosition(xPos, yPos, gameobjectName);

		return ok();
	}

	public Result repairGameObject() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "gameobject");
		if (result != null) {
			return result;
		}
		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String gameobjectid = json.findPath("gameobject").textValue();

		StartUpService.getServerGameObject().repairGameObject(gameobjectid);

		return ok();

	}

	public Result setIdle() {
		//TODO Methode in SetToTidle ist nicht fertig
		
		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "gameobject");
		if (result != null) {
			return result;
		}
		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String gameobject = json.path("gameobject").textValue();
		
		StartUpService.getServerGameObject().setToIdle(gameobject);
		
		return ok();
	}

}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import game.network.ServerGameObject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import token.TokenService;

public class CarrierController extends Controller {
	public Result getCarrierList() {

		ServerGameObject serverGameObject = StartUpService.getServerGameObject();
		ObjectNode json = Json.newObject();
		JsonNode req = request().body().asJson();

		Result result = ControllerUtil.check(req, "token");
		if (result != null) {
			return result;
		}

		String token = req.path("token").asText();

		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		json.put("status", "ok");
		json.put("message", "list of carriers");
		json.setAll(serverGameObject.getAllCarrierAsJSON("result"));

		return ok(json);
	}

	public Result loadCarrier() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "carrier", "gameobject");
		if (result != null) {
			return result;
		}
		String token = json.path("token").asText();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String carrier = json.findPath("carrier").asText();

		String gameobject = json.findPath("gameobject").asText();

		StartUpService.getServerGameObject().loadCarrierTransportObject(carrier, gameobject);

		return ok();
	}

	public Result releaseCarrier() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "carrier");
		if (result != null) {
			return result;
		}
		String token = json.path("token").asText();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String carrier = json.findPath("carrier").asText();

		StartUpService.getServerGameObject().releaseCarrierTransportObject(carrier);

		return ok();
	}

	public Result claimCarrier() {

		// TODO Nutzer aus Token auslesen und übergeben

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "carrier");
		if (result != null) {
			return result;
		}
		String token = json.path("token").asText();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String carrier = json.findPath("carrier").asText();
		String user = "Textname"; // tauschen durch name von token

		StartUpService.getServerGameObject().claimCarrier(carrier, user);

		return ok();
	}

	public Result spawnCarrierGameObject() {

		JsonNode json = request().body().asJson();
		Result result = ControllerUtil.check(json);
		if (result != null) {
			return result;
		}
		ServerGameObject serverGameObject = StartUpService.getServerGameObject();
		ObjectNode objode = Json.newObject();

		objode.put("status", "ok");
		objode.put("message", "list of game objects");
		objode.setAll(serverGameObject.getAllMobileAsJSON());

		return ok("Spawn Carrier");

	}
}

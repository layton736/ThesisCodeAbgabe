package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import token.TokenService;

public class HarversterController extends Controller {

	public Result getHarvesterList() {

		ObjectNode json = Json.newObject();

		Result result = ControllerUtil.check(json, "token");
		if (result != null) {
			return result;
		}

		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		json.put("status", "ok");
		json.put("message", "list of bases");
		json.setAll(StartUpService.getServerGameObject().getAllBasesAsJSON("result"));

		return ok(json);
	}

	public Result setRessourceToHarvest() {
		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "harvester", "ressource");
		if (result != null) {
			return result;
		}

		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String ressource = json.path("ressource").textValue();
		String harvester = json.path("harvester").textValue();

		StartUpService.getServerGameObject().setRessourceOfHarvest(ressource, harvester);

		return ok();
	}

	public Result setharvestinginto() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "token", "container", "ressource");
		if (result != null) {
			return result;
		}

		String token = json.findPath("token").textValue();
		if (!TokenService.isValid(token)) {
			return badRequest("Token is not valid");
		}

		String container = json.path("container").textValue();
		String harvester = json.path("harvester").textValue();

		StartUpService.getServerGameObject().setContainerOfHarvest(container, harvester);

		return ok();
	}

}

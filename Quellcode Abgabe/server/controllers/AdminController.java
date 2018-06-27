package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import play.mvc.Controller;
import play.mvc.Result;

public class AdminController extends Controller {

	public Result set1() {
		// TODO

		return ok("");
	}

	public Result clearDynamicGameData() {

		StartUpService.getServerGameObject().clearAllGameObjects();

		return ok("");
	}

	public Result clearCredentials() {
		// TODO

		return ok();
	}

	public Result clearGame() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "gamekey");
		if (result != null) {
			return result;
		}

		StartUpService.getServerGameObject().resetGame();
		return ok();
	}

	public Result setVisible() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "gamekey", "gameobject", "visible");
		if (result != null) {
			return result;
		}

		String gameobject = json.path("gameobject").textValue();
		boolean visible = json.path("visible").booleanValue();

		StartUpService.getServerGameObject().setVisibleOfGameObject(gameobject, visible);
		return ok();
	}

	public Result assignPlayerToTeam() {

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "gamekey", "team", "user");
		if (result != null) {
			return result;
		}

		String user = json.path("user").textValue();
		String team = json.path("team").textValue();

		StartUpService.getServerTeamObject().assignPlayerToTeam(user, team);
		return ok();
	}

}

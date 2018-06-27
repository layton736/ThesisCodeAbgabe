package controllers;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import eagermodul.ControllerUtil;
import eagermodul.StartUpService;
import game.network.ServerGameObject;
import play.mvc.Controller;
import play.mvc.Result;

public class RessourceController extends Controller {

	public Result getRessourceList() {

		ObjectNode json = StartUpService.getServerGameObject().getAllRessourcesAsJSON("result");
		json.put("status", "ok");
		json.put("message", "list of ressources");
		return ok(json);
	}

	public Result spawnRessource() {
		// gamekey ein gameadmin gamekey
		// type ein element aus {"helium3","oil","plant"}
		// posx
		// posy
		// amount (optional, standard ist 1)
		// visible (option
		ServerGameObject serverGameObject = StartUpService.getServerGameObject();

		JsonNode json = request().body().asJson();

		Result result = ControllerUtil.check(json, "gamekey", "type", "posx", "posy");
		if (result != null) {
			return result;
		}

		String type = json.path("type").asText();
		float posx = json.path("posx").floatValue();
		float posy = json.path("posy").floatValue();

		float amount = 1;
		boolean visible = true;

		if (json.has("amount")) {
			amount = json.path("amount").floatValue();
		}
		if (json.has("visible")) {
			visible = json.path("amount").asBoolean();
		}

		serverGameObject.createRessource(new Vector2(posx, posy), type, amount, visible);

		return ok();
	}
}

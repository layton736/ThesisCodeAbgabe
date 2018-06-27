package game.gameobjects.manager;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import game.gameobjects.ressource.Ressource;

public class RessourcenManager extends Manager<Ressource> {

	@Override
	public void update(float deltaTime) {

	}

	public JsonNode getAllRessourceAsJson() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		for (Iterator<Ressource> iterator = list.iterator(); iterator.hasNext();) {
			Ressource base = (Ressource) iterator.next();
			arrayNode.add(base.asJSON());
		}

		return arrayNode;
	}

	public void createRessource(Vector2 position, String type, float amount, boolean visible) {

		addGameobject(new Ressource(position, type, amount, visible));

	}

}

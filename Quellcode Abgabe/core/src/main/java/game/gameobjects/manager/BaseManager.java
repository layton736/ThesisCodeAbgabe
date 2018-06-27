package game.gameobjects.manager;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import game.gameobjects.base.Base;
import game.gameobjects.team.Team;

public class BaseManager extends Manager<Base> {

	@Override
	public void update(float deltaTime) {

	}

	public void createBase(Vector2 position, String basename, Team team) {

		list.add(new Base(position, basename, team));
		
	}

	public JsonNode getAllBaseAsJson() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		for (Iterator<Base> iterator = list.iterator(); iterator.hasNext();) {
			Base base = (Base) iterator.next();
			arrayNode.add(base.asJSON());
		}

		return arrayNode;
	}

}

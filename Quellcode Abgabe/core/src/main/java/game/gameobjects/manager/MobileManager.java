package game.gameobjects.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import game.gameobjects.mobile.Carrier;
import game.gameobjects.mobile.Container;
import game.gameobjects.mobile.Harvester;
import game.gameobjects.mobile.Mobile;
import game.gameobjects.team.Team;

public class MobileManager extends Manager<Mobile> {

	public <T> List<Mobile> getGameObjectList(Class<T> clazz) {

		List<Mobile> newlist = new ArrayList<>();

		for (Iterator<Mobile> iterator = list.iterator(); iterator.hasNext();) {
			Mobile mobile = iterator.next();

			if (clazz.isInstance(mobile)) {

				newlist.add(mobile);
			}
		}

		return newlist;
	}

	/**
	 * Liefert alle Json nodes, vom typ der Klasse, welches als parameter übergeben
	 * wurde.
	 */
	private <T> JsonNode getGameobjectListAsJSON(Class<T> clazz) {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();

		for (Iterator<Mobile> iterator = getGameObjectList(clazz).iterator(); iterator.hasNext();) {
			Mobile mobile = (Mobile) iterator.next();
			arrayNode.add(mobile.asJSON());
		}

		return arrayNode;
	}

	public Harvester createHarvester(Vector2 position, Team team) {

		Harvester obj = new Harvester(position, team);

		this.addGameobject(obj);

		return obj;

	}
	
	public Container createContainer(Vector2 position, Team team) {

		Container obj = new Container(position, team);

		this.addGameobject(obj);

		return obj;

	}
	
	public Carrier createCarrier(Vector2 position, Team team) {

		Carrier obj = new Carrier(position, team);

		this.addGameobject(obj);

		return obj;

	}

	public JsonNode getHaversterListAsJSON() {

		return getGameobjectListAsJSON(Harvester.class);
	}

	public JsonNode getContainerListAsJSON() {

		return getGameobjectListAsJSON(Container.class);
	}

	public JsonNode getCarrierListAsJSON() {

		return getGameobjectListAsJSON(Carrier.class);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}

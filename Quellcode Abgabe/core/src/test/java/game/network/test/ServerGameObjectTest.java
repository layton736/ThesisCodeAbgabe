package game.network.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.Main;
import game.gameobjects.manager.TeamManager;
import game.gameobjects.team.Team;
import game.network.ServerGameObject;

public class ServerGameObjectTest {

	ServerGameObject serverobj;
	Main main;
	ObjectMapper mapper;

	@Before
	public void initClass() {

		mapper = new ObjectMapper();

		main = new Main();
		main.gameStart();
		serverobj = main.getServerGameObject();

		TeamManager teamManager = main.getGameObject().getTeamManager();

		Team team = new Team(new Vector2(2, 2), "team1", 1);

		teamManager.addGameobject(team);
		serverobj.createMobileGameObject(new Vector2(123, 123), "team1", "harvester");
		serverobj.createMobileGameObject(new Vector2(123, 123), "team1", "container");
		serverobj.createMobileGameObject(new Vector2(123, 123), "team1", "container");
		serverobj.createMobileGameObject(new Vector2(123, 123), "team1", "carrier");
		serverobj.createMobileGameObject(new Vector2(123, 123), "team2", "carrier");

		serverobj.createBaseGameObject(new Vector2(123, 123), "base1", team.getTeamName());
	}

	 @Test
	public void testGetAllBasesJson() {
		System.out.println("=== All Bases =====");

		try {

			String pretty = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(serverobj.getAllBasesAsJSON("result"));
			System.out.println(pretty);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assertNotNull(serverobj.getAllMobileAsJSON());
		assertEquals(4, main.getGameObject().getMobileManger().size());
	}

	 @Test
	public void testGetAllGameObjectsJson() {
		System.out.println("=== All GameObjects =====");
		try {

			String pretty = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(serverobj.getAllGameObjectAsJSON());
			System.out.println(pretty);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assertNotNull(serverobj.getAllMobileAsJSON());
		assertEquals(4, main.getGameObject().getMobileManger().size());
	}

	 @Test
	public void testGetAllMobileJson() {
		System.out.println("=== All Mobile =====");
		try {

			String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serverobj.getAllMobileAsJSON());
			System.out.println(pretty);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assertNotNull(serverobj.getAllMobileAsJSON());
		assertEquals(4, main.getGameObject().getMobileManger().size());
	}

	 @Test
	public void testGetAllHarvesterJson() {
		System.out.println("=== All Harvester =====");

		try {

			String pretty = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(serverobj.getAllHarversterAsJSON("result"));
			System.out.println(pretty);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assertEquals(4, main.getGameObject().getMobileManger().size());
	}

	 @Test
	public void testGetAllCarrierJson() {
		System.out.println("=== All Carrier =====");
		try {

			String pretty = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(serverobj.getAllCarrierAsJSON("result"));
			System.out.println(pretty);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assertEquals(4, main.getGameObject().getMobileManger().size());
	}

	 @Test
	public void testGetAllContainerJson() {
		System.out.println("=== All Container =====");
		try {

			String pretty = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(serverobj.getAllContainerAsJSON("result"));
			System.out.println(pretty);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assertEquals(4, main.getGameObject().getMobileManger().size());
	}

	 @Test
	public void testUpdatePositionJson() {
		System.out.println("=== Update Position =====");

		try {
			ObjectNode allContainer = serverobj.getAllContainerAsJSON("result");
			String prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allContainer);

			System.out.println("Before");
			System.out.println(prettyPrint);

			ArrayNode arr = (ArrayNode) allContainer.get("result");

			String id = arr.get(0).findPath("name").textValue();

			// Change Position

			serverobj.updateGameObjectPosition(100, 50.0123f, id);

			ObjectNode gameObjectByID = serverobj.getGameObjectByID(id);

			prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameObjectByID);

			System.out.println("After");
			System.out.println(prettyPrint);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRepair() {
		System.out.println("=== Repair GameObject =====");

		try {

			// Damage
			System.out.println("Damage");
			main.getGameObject().damage();
			ObjectNode allobj = serverobj.getAllGameObjectAsJSON();
			String prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allobj);

			System.out.println("Before");
			System.out.println(prettyPrint);

			ArrayNode arr = (ArrayNode) allobj.get("harvester");

			String id = arr.get(0).findPath("name").textValue();

			// Repair

			System.out.println("Repair");
			serverobj.repairGameObject(id);
			ObjectNode gameObjectByID = serverobj.getGameObjectByID(id);
			prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameObjectByID);
			System.out.println(prettyPrint);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}

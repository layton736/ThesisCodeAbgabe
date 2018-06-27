package game.network;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.Main;
import game.gameobjects.GameObject;
import game.gameobjects.manager.BaseManager;
import game.gameobjects.manager.MobileManager;
import game.gameobjects.manager.RessourcenManager;
import game.gameobjects.manager.TeamManager;
import game.gameobjects.mobile.Carrier;
import game.gameobjects.mobile.Container;
import game.gameobjects.mobile.Harvester;
import game.gameobjects.mobile.Mobile;
import game.gameobjects.ressource.Ressource;
import game.gameobjects.team.Player;
import game.gameobjects.team.Team;
import game.states.HarvesterState;

/**
 * Schnittstellen, auf die der Server Zugriff hat.
 * 
 * @author yangus
 *
 */
public class ServerGameObject {

	Main main;

	ServerGameObject(Main main) {
		this.main = main;
	}

	private MobileManager getMobileManager() {

		return main.getGameObject().getMobileManger();
	}

	private RessourcenManager getRessourceManager() {

		return main.getGameObject().getRessourceManger();
	}

	private TeamManager getTeamManager() {

		return main.getGameObject().getTeamManager();
	}

	private BaseManager getBaseManager() {

		return main.getGameObject().getBaseManager();
	}

	public ObjectNode getAllGameObjectAsJSON() {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		objnode.setAll(getAllMobileAsJSON());
		objnode.setAll(getAllBasesAsJSON("bases"));
		objnode.setAll(getAllRessourcesAsJSON("ressources"));
		return objnode;

	}

	public ObjectNode getAllMobileAsJSON() {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		MobileManager mmanager = main.getGameObject().getMobileManger();

		objnode.setAll(getAllHarversterAsJSON("harvester"));
		objnode.setAll(getAllContainerAsJSON("container"));
		objnode.setAll(getAllCarrierAsJSON("carrier"));

		return objnode;
	}

	public ObjectNode getAllHarversterAsJSON(String accessname) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		MobileManager mManager = getMobileManager();
		objnode.set(accessname, mManager.getHaversterListAsJSON());
		return objnode;
	}

	public ObjectNode getAllContainerAsJSON(String accessname) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		MobileManager mManager = getMobileManager();
		objnode.set(accessname, mManager.getContainerListAsJSON());
		return objnode;
	}

	public ObjectNode getAllCarrierAsJSON(String accessname) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		MobileManager mManager = getMobileManager();
		objnode.set(accessname, mManager.getCarrierListAsJSON());
		return objnode;
	}

	public ObjectNode getAllBasesAsJSON(String accessname) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		BaseManager bManager = getBaseManager();
		objnode.set(accessname, bManager.getAllBaseAsJson());
		return objnode;
	}

	public ObjectNode getAllRessourcesAsJSON(String accessname) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objnode = mapper.createObjectNode();

		RessourcenManager rManager = main.getGameObject().getRessourceManger();
		objnode.set(accessname, rManager.getAllRessourceAsJson());
		return objnode;
	}

	public void repairGameObject(String gameObjectID) {
		if (!getMobileManager().repair(gameObjectID)) {
			if (!getRessourceManager().repair(gameObjectID)) {
				getRessourceManager().repair(gameObjectID);
			}
		}
	}

	/**
	 * @param position
	 * @param teamName
	 * @param gameobjectName
	 *            Zulässige Namen sind harvester,container und carrier
	 */
	public void createMobileGameObject(Vector2 position, String teamName, String gameobjecttype) {

		if (!getTeamManager().isTeamNameValid(teamName)) {
			return;
		}

		Team team = getTeamManager().getTeam(teamName);

		if (gameobjecttype.equals("harvester")) {
			getMobileManager().createHarvester(position, team);

		} else if (gameobjecttype.equals("container")) {
			getMobileManager().createContainer(position, team);

		} else if (gameobjecttype.equals("carrier")) {
			getMobileManager().createCarrier(position, team);
		}
	}

	public void updateGameObjectPosition(float xPos, float yPos, String id) {

		GameObject gameObject = main.getGameObject().getGameObjectByID(id);
		gameObject.setPosition(new Vector2(xPos, yPos));
	}

	public ObjectNode getGameObjectByID(String id) {
		ObjectNode gameObject = main.getGameObject().getGameObjectByIDAsJSON(id);

		return gameObject;

	}

	public void createBaseGameObject(Vector2 position, String basename, String teamname) {

		if (!getTeamManager().isTeamNameValid(teamname)) {
			return;
		}

		Team team = getTeamManager().getTeam(teamname);

		getBaseManager().createBase(position, basename, team);

	}

	public void removeGameObject(String id) {
		main.getGameObject().removeGameObject(id);
	}

	public void clearAllGameObjects() {
		getMobileManager().clear();
		getBaseManager().clear();
		getRessourceManager().clear();

	}

	public void resetGame() {
		main.restart();

	}

	public void setVisibleOfGameObject(String id, boolean visible) {
		GameObject gameObject = main.getGameObject().getGameObjectByID(id);
		if (gameObject != null) {
			gameObject.setVisible(visible);
		}
	}

	public void createRessource(Vector2 position, String type, float amount, boolean visible) {

		getRessourceManager().createRessource(position, type, amount, visible);

	}

	public void setRessourceOfHarvest(String ressource, String harvester) {

		GameObject ress = main.getGameObject().getGameObjectByID(ressource);
		GameObject harv = main.getGameObject().getGameObjectByID(harvester);
		if (ress == null || !(ress instanceof Ressource) || harv == null || !(harv instanceof Harvester)) {
			return;
		}

		((Harvester) harv).setRessourceToHarvest((Ressource) ress);

	}

	public void setContainerOfHarvest(String container, String harvester) {
		GameObject con = main.getGameObject().getGameObjectByID(container);
		GameObject harv = main.getGameObject().getGameObjectByID(harvester);
		if (con == null || !(con instanceof Container) || harv == null || !(harv instanceof Harvester)) {
			return;
		}

		((Harvester) harv).setHarvestingToContainer(((Container) con));

	}

	public void dissolveContainer(String container) {
		// TODO Inhalt des Containers wird dem Team zum handeln gutgeschrieben

		GameObject con = main.getGameObject().getGameObjectByID(container);
		if (con == null || !(con instanceof Container)) {
			return;
		}
		Container tmp = (Container) con;
		tmp.setUsable(false);
		Team team = tmp.getTeam();

	}

	public void setToIdle(String id) {
		// TODO

		GameObject gobj = main.getGameObject().getGameObjectByID(id);

		if (gobj instanceof Harvester) {
			Harvester harvester = (Harvester) gobj;
			// harvester.setStateMachine(HarvesterState.IDLE);
		}
		if (gobj instanceof Container) {

		}
		if (gobj instanceof Carrier) {

		}

	}

	public void loadCarrierTransportObject(String carrier, String gameobject) {

		GameObject car = main.getGameObject().getGameObjectByID(carrier);
		GameObject toContain = main.getGameObject().getGameObjectByID(gameobject);

		if (car == null || !(car instanceof Carrier) || toContain == null
				|| !(toContain instanceof Harvester && toContain instanceof Container)) {
			return;
		}
		Carrier tmp = (Carrier) car;
		tmp.setTransportingobject((Mobile) toContain);
	}

	public void releaseCarrierTransportObject(String carrier) {
		GameObject car = main.getGameObject().getGameObjectByID(carrier);

		if (car == null || !(car instanceof Carrier)) {
			return;
		}
		Carrier tmp = (Carrier) car;
		tmp.releaseTransportingobject();

	}

	public void claimCarrier(String carrierID, String userName) {
		GameObject car = main.getGameObject().getGameObjectByID(carrierID);

		if (car == null || !(car instanceof Carrier)) {
			return;
		}
		Carrier tmp = (Carrier) car;
		if (tmp.getControlledByPlayer() != null) {
			return;
		}

		Player ply = getTeamManager().getPlayerOfTeamWithName(userName);
		if(ply != null) {
			tmp.setControlledByPlayer(ply);
		}
	}
}

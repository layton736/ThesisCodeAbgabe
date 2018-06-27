package game.network;

import com.badlogic.gdx.math.Vector2;

import game.Main;
import game.gameobjects.manager.BaseManager;
import game.gameobjects.manager.MobileManager;
import game.gameobjects.manager.RessourcenManager;
import game.gameobjects.manager.TeamManager;
import game.gameobjects.team.Player;
import game.gameobjects.team.Team;

public class ServerTeamObject {

	Main main;

	public ServerTeamObject(Main main) {

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

	public void assignPlayerToTeam(String userName, String teamName) {

		Team team = getTeamManager().getTeam(teamName);

		team.addPlayer(new Player(new Vector2(), userName));

	}
}

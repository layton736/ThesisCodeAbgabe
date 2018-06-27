package game.gameobjects.manager;

import java.util.Iterator;

import game.exception.BadNameException;
import game.gameobjects.team.Player;
import game.gameobjects.team.Team;

public class TeamManager extends Manager<Team> {

	public synchronized void addPlayerToTeam(Player player, String teamname) {

		try {
			for (Iterator<Team> iterator = list.iterator(); iterator.hasNext();) {
				Team team = (Team) iterator.next();
				if (team.getTeamName().equals(teamname)) {
					team.addPlayer(player);
					return;
				}
			}
			throw new BadNameException("Team ist nicht existend");
		} catch (BadNameException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) {

	}

	public boolean isTeamNameValid(String teamName) {

		for (Iterator<Team> iterator = list.iterator(); iterator.hasNext();) {
			Team team = (Team) iterator.next();
			if (team.getTeamName().equals(teamName)) {
				return true;
			}
		}
		return false;
	}

	public Team getTeam(String teamName) {
		for (Iterator<Team> iterator = list.iterator(); iterator.hasNext();) {
			Team team = (Team) iterator.next();
			if (team.getTeamName().equals(teamName)) {
				return team;
			}
		}
		return null;

	}

	public Player getPlayerOfTeamWithID(String id) {
		for (Iterator<Team> iterator = list.iterator(); iterator.hasNext();) {
			Team team = (Team) iterator.next();
			Player player = team.searchPlayerWithID(id);
			if (player != null) {
				return player;
			}

		}
		return null;
	}

	public Player getPlayerOfTeamWithName(String name) {
		for (Iterator<Team> iterator = list.iterator(); iterator.hasNext();) {
			Team team = (Team) iterator.next();
			Player player = team.searchPlayerWithName(name);
			if (player != null) {
				return player;
			}

		}
		return null;
	}

}

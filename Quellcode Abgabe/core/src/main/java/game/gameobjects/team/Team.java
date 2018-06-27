package game.gameobjects.team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.exception.BadNameException;
import game.gameobjects.GameObject;

public class Team extends GameObject {

	private List<Player> playerList;

	private String teamName;
	private int emblemNumber;
	private int money;

	public Team(Vector2 initPosition, String teamName, int emblemNumber) {
		super(initPosition);

		try {
			if (teamName == null || teamName.equals("") || emblemNumber < 0 || emblemNumber > 7) {

				throw new BadNameException("Ungültige Werte");
			}

			playerList = new ArrayList<>();
			this.teamName = teamName;
			this.setEmblemNumber(emblemNumber);
		} catch (BadNameException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void createNameOfObject() {
		this.setNameOfObjectType("Team");
	}

	public void addPlayer(Player player) {
		if (player != null) {
			for (Player ply : playerList) {
				if (ply.getNameOfPlayer().equals(player.getNameOfPlayer())) {
					return;
				}
			}
		}
		playerList.add(player);

	}

	public Player searchPlayerWithID(String id) {

		if (id == null) {
			return null;
		}

		for (Iterator<Player> iterator = playerList.iterator(); iterator.hasNext();) {
			Player ply = iterator.next();

			if (ply.getID().equals(id)) {
				return ply;
			}
		}
		return null;
	}

	public Player searchPlayerWithName(String name) {

		if (name == null) {
			return null;
		}

		for (Iterator<Player> iterator = playerList.iterator(); iterator.hasNext();) {
			Player ply = iterator.next();

			if (ply.getNameOfPlayer().equals(name)) {
				return ply;
			}
		}
		return null;
	}

	public Player removeGameobject(String id) {

		Player ply = searchPlayerWithID(id);
		playerList.remove(ply);

		return ply;

	}

	@Override
	public ObjectNode asJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTeamName() {
		return teamName;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public int getEmblemNumber() {
		return emblemNumber;
	}

	public void setEmblemNumber(int emblemNumber) {
		this.emblemNumber = emblemNumber;
	}

	public int getMoney() {
		return money;
	}

	public synchronized void addMoney(int money) {
		this.money += money;
	}

	public synchronized void setMoney(int money) {
		this.money = money;
	}

}

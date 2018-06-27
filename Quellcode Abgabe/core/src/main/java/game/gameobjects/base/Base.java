package game.gameobjects.base;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.exception.BadNameException;
import game.gameobjects.GameObject;
import game.gameobjects.team.Team;

public class Base extends GameObject {

	private Team team;
	private String basename;

	public Base(Vector2 initPosition, String baseName, Team team) {
		super(initPosition);

		setBasename(baseName);
		setVisible(true);
		setTeam(team);
	}

	@Override
	public ObjectNode asJSON() {
		JsonNode json = super.asJSON();

		ObjectNode objectnode = (ObjectNode) json;
		objectnode.put("team", getTeam().getTeamName());
		objectnode.put("visible", isVisible());

		return objectnode;
	}

	@Override
	protected void createNameOfObject() {
		setNameOfObjectType("Base");

	}

	public Team getTeam() {
		return team;
	}

	public synchronized void setTeam(Team team) {
		if (team == null) {
			try {
				throw new BadNameException("Team darf nicht null sein!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.team = team;
	}



	public String getBasename() {
		return basename;
	}

	public synchronized void setBasename(String basename) {
		this.basename = basename;
	}

	@Override
	public void update(float deltaTime) {

	}

}

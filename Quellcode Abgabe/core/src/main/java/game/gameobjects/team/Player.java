package game.gameobjects.team;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.GameObject;
import game.gameobjects.message.Messages;

public class Player extends GameObject {

	String nameOfPlayer;

	public Player(Vector2 initPosition, String nameOfPlayer) {
		super(initPosition);
		this.nameOfPlayer = nameOfPlayer;

	}

	@Override
	protected void createNameOfObject() {
		setNameOfObjectType(Messages.getString("player")); //$NON-NLS-1$
	}

	@Override
	public ObjectNode asJSON() {

		return null;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public String getNameOfPlayer() {
		// TODO Auto-generated method stub
		return nameOfPlayer;
	}

}

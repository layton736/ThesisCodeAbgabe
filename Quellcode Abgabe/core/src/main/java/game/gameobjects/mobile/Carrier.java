package game.gameobjects.mobile;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.team.Player;
import game.gameobjects.team.Team;
import game.states.CarrierState;;

public class Carrier extends Mobile {

	private Mobile transportingobject;
	private Player controlledByPlayer;

	public Carrier(Vector2 initPosition, Team team) {
		super(initPosition, team);
		setStateMachine(new DefaultStateMachine<Carrier, CarrierState>(this, CarrierState.IDLE));
	}

	@Override
	protected void createNameOfObject() {
		setNameOfObjectType("Carrier");

	}

	@Override
	public ObjectNode asJSON() {

		ObjectNode objNode = super.asJSON();

		if (getControlledByPlayer() == null) {
			objNode.put("controlledByPlayer", "undefined");

		} else {
			objNode.put("controlledByPlayer", getControlledByPlayer().getNameOfPlayer());
		}

		if (getTransportingobject() == null) {
			objNode.put("transportingobject", "undefined");
		} else {
			objNode.put("transportingobject", getTransportingobject().getID());
		}

		objNode.put("state", getStateMachine().toString());

		return objNode;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public Mobile getTransportingobject() {
		return transportingobject;
	}

	public void setTransportingobject(Mobile transportingobject) {
		this.transportingobject = transportingobject;
	}
	public void releaseTransportingobject() {
		this.transportingobject = null;
	}

	public Player getControlledByPlayer() {
		return controlledByPlayer;
	}

	public void setControlledByPlayer(Player controlledByPlayer) {
		this.controlledByPlayer = controlledByPlayer;
	}

}

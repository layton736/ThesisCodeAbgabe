package game.gameobjects.mobile;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.GameObject;
import game.gameobjects.team.Team;
import game.states.CarrierState;

/**
 * Diese Klasse steht für alle Fahrzeuge im Spiel.
 * 
 * @author Ahmed Salame
 *
 */

public abstract class Mobile extends GameObject {

	private Team team;

	private boolean isAlive;

	private StateMachine<? extends Mobile, ? extends State<?>> stateMachine;

	Mobile(Vector2 initPosition, Team team) {
		super(initPosition);
		setTeam(team);
		setAlive(true);
	}

	@Override
	public ObjectNode asJSON() {

		ObjectNode objNode = super.asJSON();

		if (team == null) {
			objNode.set("team", null);
		} else {
			objNode.put("team", team.getTeamName());
		}

		objNode.put("isalive", this.isAlive());

		return objNode;
	}

	public Team getTeam() {
		return team;
	}

	public synchronized void setTeam(Team team) {
		this.team = team;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public synchronized void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public  StateMachine<? extends Mobile, ? extends State<?>> getStateMachine() {

		return stateMachine;
	}

	public void setStateMachine(StateMachine<? extends Mobile, ? extends State<?>> state) {
		this.stateMachine = state;
	}
	public <E> void setSate(State<E> state) {
		//TODO 
//		getStateMachine().setInitialState(state);
		
	}

}

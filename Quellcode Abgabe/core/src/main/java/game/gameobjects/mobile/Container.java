package game.gameobjects.mobile;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.ressource.Ressource;
import game.gameobjects.team.Team;
import game.states.ContainerState;

public class Container extends Mobile {

	private boolean usable;
	private Ressource ressource;
	private float amount;
	private int capacity;

	public Container(Vector2 initPosition, Team team) {
		super(initPosition, team);

		this.setUsable(true);

		setStateMachine(new DefaultStateMachine<Container, ContainerState>(this, ContainerState.IDLE));
	}

	@Override
	protected void createNameOfObject() {
		setNameOfObjectType("Container");

	}

	@Override
	public ObjectNode asJSON() {
		ObjectNode objNode = super.asJSON();
		objNode.put("state", getStateMachine().toString());
		objNode.put("amount", amount);
		objNode.put("capacity", capacity);

		
		if(ressource == null) {
			objNode.put("ressource", "undefined");
		}else {
			objNode.put("ressource", ressource.getNameOfObjectType());
		}
		
		return objNode;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public float getAmount() {
		return amount;
	}

	public synchronized void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isUsable() {
		return usable;
	}

	public synchronized void setUsable(boolean usable) {
		this.usable = usable;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public synchronized void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public int getCapacity() {
		return capacity;
	}

	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}

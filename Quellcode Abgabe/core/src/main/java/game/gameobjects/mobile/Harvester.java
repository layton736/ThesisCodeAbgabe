package game.gameobjects.mobile;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.ressource.Ressource;
import game.gameobjects.team.Team;
import game.states.HarvesterState;

public class Harvester extends Mobile {

	private int harvestPerMinute;
	private Container harvestingToContainer;
	private Ressource ressourceNameHarvesting;

	public Harvester(Vector2 initPosition, Team team) {
		super(initPosition, team);
		setStateMachine(new DefaultStateMachine<Harvester, HarvesterState>(this, HarvesterState.IDLE));
	}

	@Override
	protected void createNameOfObject() {
		setNameOfObjectType("Harverster");
	}

	@Override
	public ObjectNode asJSON() {
		ObjectNode objNode = super.asJSON();
		objNode.put("state", getStateMachine().toString());
		objNode.put("harvestPerMinute", harvestPerMinute);
		if (harvestingToContainer == null) {
			objNode.put("harvestingToContainer", "undefined");
		} else {
			objNode.put("harvestingToContainer", harvestingToContainer.getID());
		}
		if (ressourceNameHarvesting == null) {
			objNode.put("ressourceNameHarvesting", "undefined");
			objNode.put("ressourceToHarvest", "undefined");
		} else {
			objNode.put("ressourceNameHarvesting", ressourceNameHarvesting.getID());
			objNode.put("ressourceToHarvest", ressourceNameHarvesting.getType().toString());
		}

		return objNode;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public int getHarvestPerMinute() {
		return harvestPerMinute;
	}

	public void setHarvestPerMinute(int harvestPerMinute) {
		this.harvestPerMinute = harvestPerMinute;
	}

	public Container getHarvestingToContainer() {
		return harvestingToContainer;
	}

	public void setHarvestingToContainer(Container harvestingToContainer) {
		this.harvestingToContainer = harvestingToContainer;
	}

	public Ressource getRessourceToHarvest() {
		return ressourceNameHarvesting;
	}

	public void setRessourceToHarvest(Ressource ressourceToHarvest) {
		this.ressourceNameHarvesting = ressourceToHarvest;
	}

}

package game.gameobjects.ressource;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.GameObject;

public class Ressource extends GameObject {

	public enum RessourceType {
		HELIUM3 {
			@Override
			public String toString() {
				return "helium3";
			}
		},
		OIL {
			@Override
			public String toString() {
				return "oil";
			}
		},
		PLANT {
			@Override
			public String toString() {
				return "plant";
			}
		},
		ARTEFACT {
			@Override
			public String toString() {
				return "artefact";
			}
		};
	}

	private RessourceType type;
	private int amount;
	private int amountrelative;

	public Ressource(Vector2 initPosition, String resTyp, float amount, boolean visible) {
		super(initPosition);

		if (resTyp == null) {
			throw new NullPointerException("Fehlender Ressourcentyp");
		}

		setVisible(visible);
		this.setType(resTyp);
	}

	@Override
	protected void createNameOfObject() {

		setNameOfObjectType(getType().toString());
	}

	@Override
	public ObjectNode asJSON() {
		ObjectNode json = super.asJSON();

		json.put("amount", amount);
		json.put("amountrelative", amountrelative);
		json.put("type", getType().toString());

		return null;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public RessourceType getType() {
		return type;
	}

	private void setType(String resTyp) {

		switch (resTyp.toLowerCase()) {

		case "hellium3":
			this.type = RessourceType.HELIUM3;
		case "oil":
			this.type = RessourceType.OIL;
		case "plant":
			this.type = RessourceType.PLANT;
		case "artefact":
			this.type = RessourceType.ARTEFACT;
		}
	}

	public int getAmount() {
		return amount;
	}

	public synchronized void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmountrelative() {
		return amountrelative;
	}

	public synchronized void setAmountrelative(int amountrelative) {
		this.amountrelative = amountrelative;
	}

}

package game.gameobjects;

import java.util.concurrent.atomic.AtomicLong;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Diese Klasse repräsentiert ein Gameobject in der Spielwelt. Alle Objecte der
 * Spielwelt leiten sich von diesem Objekt ab.
 * 
 * @author Ahmed Salame
 *
 */

public abstract class GameObject {

	private Vector2 position;

	private int direction;

	private int speed;

	private String nameOfObjectType;

	private String id;

	private static AtomicLong counter;

	private float lifepoints;

	private boolean visible;

	static {
		counter = new AtomicLong();
		counter.set(0);
	}

	protected Object lock;

	public GameObject(Vector2 initPosition) {
		position = new Vector2(initPosition);

		synchronized (initPosition) {
			id = "" + counter.getAndIncrement();
		}
		setLifepoints(100);

		createNameOfObject();
	}

	public ObjectNode asJSON() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		node.put("x", position.x);
		node.put("y", position.y);
		// node.put("direction", );
		node.put("name", id);
		node.put("lifepoints", lifepoints);
		node.put("direction", direction);
		node.put("speed", speed);
		node.put("visible", visible);
		
		return node;
	}

	/**
	 * Erstellt den Namen des Objectes betw. dessen Bezeichnung (z B. Contrainer
	 * etc).
	 */
	abstract protected void createNameOfObject();

	/**
	 * Die ID, die das Object eindeutig Referenziert.
	 * 
	 * @return
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * Gibt den Typ des Objektes an. Der Typ ist NICHT eindeutig. FÜr die eindeutige
	 * Referenzierung siehe {@link #getID() getID}
	 */
	public String getNameOfObjectType() {
		return nameOfObjectType;
	}

	/**
	 * Setzt den namen, des ObjektTypes. Dient dazu, die Object anhand eines
	 * Stringwertes zu Identifizieren.
	 * 
	 * @param name
	 */
	protected void setNameOfObjectType(String name) {
		nameOfObjectType = name;
	}

	public Vector2 getPosition() {
		return position;
	}

	public synchronized void setPosition(Vector2 position) {
		this.position = position;
	}

	abstract public void update(float deltaTime);

	public float getLifepoints() {
		return lifepoints;
	}

	public synchronized void setLifepoints(float newLifepoints) {
		if (newLifepoints > 100) {
			this.lifepoints = 100;
		} else {
			this.lifepoints = newLifepoints;
		}
	}

	public int getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public synchronized void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Wird beim zerstören des Objektes aufgerufen
	 */
	public void dispose() {

	}

	public boolean isVisible() {
		return visible;
	}

	public synchronized void setVisible(boolean visible) {
		this.visible = visible;
	};

}

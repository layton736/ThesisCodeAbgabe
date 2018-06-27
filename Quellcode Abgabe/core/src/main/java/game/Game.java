package game;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.GameObject;
import game.gameobjects.manager.BaseManager;
import game.gameobjects.manager.MobileManager;
import game.gameobjects.manager.RessourcenManager;
import game.gameobjects.manager.TeamManager;
import game.states.GameState;

/**
 * Das bestimmt dem Ablauf des Spiels.
 * 
 * @author Ahmed Salame
 *
 */
public class Game implements Runnable {

	DefaultStateMachine<Game, GameState> state;
	Main main;

	private MobileManager mmanager;
	private RessourcenManager rmanager;
	private TeamManager tmanager;
	private BaseManager bmanager;

	Game(Main main) {
		this.main = main;
	}

	/**
	 * Diese Methode Initialisiert alle Objekte, damit zum Spielbeginn alle Klassen
	 * Vorhanden sind, die benötigt werden.
	 */
	public void init() {
		state = new DefaultStateMachine<Game, GameState>(this, GameState.RUNNING);
		mmanager = new MobileManager();
		rmanager = new RessourcenManager();
		tmanager = new TeamManager();
		bmanager = new BaseManager();
	}

	@Override
	public void run() {

		while (state.getCurrentState() != GameState.END) {
			if (state.getCurrentState() == GameState.RUNNING) {
				state.update();
			} else if (state.getCurrentState() == GameState.LOCKED) {
				state.update();
			} else if (state.getCurrentState() == GameState.PAUSE) {
				state.update();
			}
		}

		this.main.gameEnd();
	}

	public void end() {

	}

	DefaultStateMachine<Game, GameState> getStateMachine() {
		return state;
	}

	public MobileManager getMobileManger() {
		return mmanager;
	}

	public RessourcenManager getRessourceManger() {
		return rmanager;
	}

	public BaseManager getBaseManager() {
		return bmanager;

	}

	public TeamManager getTeamManager() {
		return tmanager;
	}

	public GameObject getGameObjectByID(String id) {
		GameObject gameobjectByID = mmanager.getGameobjectByID(id);
		if (gameobjectByID != null) {
			return gameobjectByID;
		}
		gameobjectByID = rmanager.getGameobjectByID(id);
		if (gameobjectByID != null) {
			return gameobjectByID;
		}
		gameobjectByID = bmanager.getGameobjectByID(id);
		if (gameobjectByID != null) {
			return gameobjectByID;
		}

		return null;
	}

	public ObjectNode getGameObjectByIDAsJSON(String id) {

		GameObject gameObjectByID = getGameObjectByID(id);
		if (gameObjectByID == null) {
			return null;
		}

		return gameObjectByID.asJSON();

	}

	public void removeGameObject(String id) {
		if (mmanager.removeGameobject(id) != null) {
			return;
		}
		if (rmanager.removeGameobject(id) != null) {
			return;
		}
		if (bmanager.removeGameobject(id) != null) {
			return;
		}

	}

	public void damage() {
		mmanager.damage(0.5f);
	}

}

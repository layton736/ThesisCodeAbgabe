package game;

import game.gameobjects.manager.MobileManager;
import game.gameobjects.manager.RessourcenManager;

/**
 * Übergreifende Funktione des Servers sind in dieser Klasse verankert. Beispiel
 * wäre die Berechnung der Spielzeit oder der Status des Spiels..
 * 
 * @author Ahmed Salame
 *
 */

public class Util {

	private static long timeSinceGameStartSec;

	private static void init() {
	}

	static {

		init();
	}

	private Util() {
		// Do nothing
	}

	public static long getTimeSinceGameStartMillis() {

		return System.currentTimeMillis() - timeSinceGameStartSec;
	}

	public static void startGameTime() {
		Util.timeSinceGameStartSec = System.currentTimeMillis();
	}

	// public static void destroyGameobject(String id) {
	// if (mobilemanager.removeGameobject(id) == null) {
	// ressoucemanager.removeGameobject(id);
	// }
	// }

}

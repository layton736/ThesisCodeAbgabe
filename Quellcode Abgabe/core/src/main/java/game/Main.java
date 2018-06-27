package game;

import game.network.ServerService;
import game.network.ServerTeamObject;
import game.network.ServerGameObject;

/**
 * Diese Klasse dient als Einstiegsklasse für das Spiel.
 * 
 * @author Ahmed Salame
 */
public class Main {

	Game game;
	Main main;
	Thread gameThread;

	public void gameStart() {
		System.out.println("=========Game Start===========");
		Util.startGameTime();

		game = new Game(this);
		game.init();
		gameThread = new Thread(game);
		gameThread.start();
	}

	public void restart() {

		gameThread = null;
		
		System.out.println("=========Game Restart===========");
		Util.startGameTime();
		game = new Game(this);
		gameThread = new Thread(game);
		gameThread.start();

	}

	void gameEnd() {
		game.end();
		gameThread = null;
		System.out.println("Game Ende");

	}

	public Game getGameObject() {
		return game;
	}

	public ServerGameObject getServerGameObject() {

		return ServerService.createServerGameObject(this);
	}
	
	public ServerTeamObject getServerTeamObject() {

		return ServerService.createServerTeamObject(this);
	}

}

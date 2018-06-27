package game.network;

import game.Main;

/**
 * Repräsentant des Servers
 * 
 * @author Ahmed Salame
 *
 */

public class ServerService {

	public static ServerGameObject createServerGameObject(Main main) {

		return new ServerGameObject(main);

	}

	public static ServerTeamObject createServerTeamObject(Main main) {
		
		return new ServerTeamObject(main);

	}

}

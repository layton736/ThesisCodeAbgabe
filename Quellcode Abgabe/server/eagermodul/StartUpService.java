package eagermodul;

import java.net.InetSocketAddress;

import javax.inject.Inject;
import javax.inject.Singleton;

import game.Main;
import game.network.ServerGameObject;
import game.network.ServerTeamObject;
import websocket.WebsocketServerImpl;

@Singleton
public class StartUpService {

	private static ServerGameObject serverobject;
	private static ServerTeamObject serverTeamObject;
	private static WebsocketServerImpl socketServer;
	@Inject
	public StartUpService() {

		Main main = new Main();
		main.gameStart();
		serverobject = main.getServerGameObject();
		serverTeamObject = main.getServerTeamObject();
		
		socketServer = new WebsocketServerImpl(new InetSocketAddress("localhost", 9001));
		socketServer.start();
		
		
	}

	public static ServerGameObject getServerGameObject() {
		return serverobject;
	}
	
	public static ServerTeamObject getServerTeamObject() {
		return serverTeamObject;
	}

	public static WebsocketServerImpl getSocketServer() {
		return socketServer;
	}
}

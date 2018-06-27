package websocket;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * Test Websocket
 * @author Ahmed Salame
 *
 */
public class WebsocketServerImpl extends WebSocketServer {
	public WebsocketServerImpl (InetSocketAddress inet) {
		super(inet);
	}

	public void sendToClients(String message) {
		broadcast(message);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {

	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

}

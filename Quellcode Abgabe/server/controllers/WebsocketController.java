package controllers;

import javax.inject.Inject;

import actor.SocketActor;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import websocket.WebsocketService;

/**
 * speichert alle Actors. Das ist eine notlösung, die Actors sind anderswo im
 * System gespeichert.
 * 
 * @author Ahmed Salame
 *
 */

public class WebsocketController extends Controller {
	private final ActorSystem actorSystem;
	private final Materializer materializer;

	@Inject
	public WebsocketController(ActorSystem actorSystem, Materializer materializer) {
		this.actorSystem = actorSystem;
		this.materializer = materializer;
	}

	public WebSocket ws() {
		
		WebSocket socket = WebSocket.Text
				.accept(request -> ActorFlow.actorRef(SocketActor::props, actorSystem, materializer));
		return socket;

	}

	public Result sendToClient() {
		WebsocketService.sendToClients("Nachricht über Websocket");
		return ok("");
	}

}

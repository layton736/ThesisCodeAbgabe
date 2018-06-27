package actor;

import org.java_websocket.server.WebSocketServer;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import websocket.WebsocketService;

public class SocketActor extends AbstractActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	private final ActorRef out;

	public SocketActor(ActorRef out) {
		this.out = out;
	}

	@Override
	public void preStart() throws Exception {
		log.info("Actor has been created");
	}

	@Override
	public void postStop() throws Exception {
		log.info("Actor has been stopped");
	}

	public static Props props(ActorRef out) {
		WebsocketService.add(out);
		return Props.create(SocketActor.class, out);
	}

	public void message(String s) {
		System.out.println("Message kam an: " + s);
	}

	@Override
	public Receive createReceive() {

		return receiveBuilder().match(String.class, message -> {
			this.message(message);
		}).build();
	}
}

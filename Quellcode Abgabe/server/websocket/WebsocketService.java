package websocket;

import java.util.Collection;
import java.util.HashSet;

import actor.SocketActor;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

/**
 * 
 * @author Ahmed Salame
 *
 */

public class WebsocketService {

	private static Collection<ActorRef> list = new HashSet<>();

	public static void add(ActorRef newactor) {
		list.add(newactor);
	}

	public static void clear() {
		list = new HashSet<>();
	}

	public static void removeClient(Actor actor) {

		if (actor == null) {
			return;
		}
		list.remove(actor);
	}

	public static void sendToClients(String message) {
		
		for (ActorRef actor : list) {
			if (actor == null) {
				list.remove(actor);
			} else {
				actor.tell(message, actor);
			}
		}
	}
}

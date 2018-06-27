package game.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

import game.gameobjects.mobile.Container;

public enum ContainerState implements State<Container> {

	IDLE() {

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "idle";
		}

	},
	FILLING() {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "filling";
		}
	},
	INTRANSPORT() {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "intransport";
		}
	},
	DESTROYED() {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "destroyed";
		}
	};

	@Override
	public void enter(Container entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Container entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Container entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMessage(Container entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}

}

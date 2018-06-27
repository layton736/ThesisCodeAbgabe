package game.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

import game.gameobjects.mobile.Carrier;

public enum CarrierState implements State<Carrier> {

	IDLE() {

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "idle";

		}
	},

	CONTROLLED_BY_PLAYER() {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "controlled_by_player";
		}

	};

	@Override
	public void enter(Carrier entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Carrier entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Carrier entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMessage(Carrier entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}

}

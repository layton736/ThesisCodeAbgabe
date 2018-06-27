package game.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

import game.gameobjects.mobile.Harvester;

public enum HarvesterState implements State<Harvester> {

	IDLE() {

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "idle";
		}
	},
	HARVERSTING() {
		
		@Override
		public void update(Harvester entity) {
			super.update(entity);
			
		
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "harversting";
		}
	},
	INTRANSPORT() {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "intransport";
		}
	};

	@Override
	public void enter(Harvester entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Harvester entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Harvester entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMessage(Harvester entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
}

package game.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

import game.Game;

public enum GameState implements State<Game> {

	RUNNING() {
		@Override
		public void update(Game entity) {
			super.update(entity);

			entity.getMobileManger().update(0f);
			entity.getBaseManager().update(0f);
			entity.getRessourceManger().update(0f);

		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "running";
		}
	},
	PAUSE {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "pause";
		}
	},
	LOCKED {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "locked";
		}
	},
	END {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "end";
		}
	};

	@Override
	public void enter(Game entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Game entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMessage(Game entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}

}

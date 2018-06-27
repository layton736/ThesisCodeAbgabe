package game.gameobjects.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.node.ObjectNode;

import game.gameobjects.GameObject;
import game.gameobjects.manager.Manager;

public class ManagerTest {

	private class GameobjectTest extends GameObject{

		public GameobjectTest(Vector2 initPosition) {
			super(initPosition);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void createNameOfObject() {
			// TODO Auto-generated method stub
		}


		@Override
		public ObjectNode asJSON() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void update(float deltaTime) {
			// TODO Auto-generated method stub
			
		}
	}
	
	Manager<GameObject> manager;

	@Before
	public void initEachTime() {

		manager = new Manager<GameObject>() {

			@Override
			public void update(float deltaTime) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Test
	public void addObject() {
		assertEquals(0, manager.size());

		GameObject gobj1;
		Vector2 vec = new Vector2();

		gobj1 = new GameobjectTest(vec);

		manager.addGameobject(gobj1);

		gobj1 = new GameobjectTest(vec);

		manager.addGameobject(gobj1);

		assertEquals(2, manager.size());

		manager.addGameobject(gobj1);

		assertEquals(2, manager.size());

	}

	@Test
	public void removeObject() {

		GameObject gobj1;
		Vector2 vec = new Vector2();

		gobj1 = new GameobjectTest(vec);

		manager.addGameobject(gobj1);

		gobj1 = new GameobjectTest(vec);

		manager.addGameobject(gobj1);

		assertEquals(2, manager.size());

		manager.removeGameobject(gobj1);

		assertEquals(1, manager.size());

		manager.removeGameobject("0");

		assertEquals(0, manager.size());

	}

	public void addElements() {

	}

	@Test
	public void checkUniqueIDs() {

		addElements();
		
		

	}

}

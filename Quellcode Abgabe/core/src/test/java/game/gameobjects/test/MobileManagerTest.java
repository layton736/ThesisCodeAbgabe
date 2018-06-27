package game.gameobjects.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.JsonNode;

import game.gameobjects.manager.MobileManager;
import game.gameobjects.mobile.Harvester;

public class MobileManagerTest {

	MobileManager mmanager;

	@Before
	public void init() {
		mmanager = new MobileManager();

	}

	@Test
	public void testGameObjectList() {
		mmanager.createHarvester(new Vector2(5, 3), null);
		mmanager.createHarvester(new Vector2(544, 12), null);
		mmanager.createHarvester(new Vector2(12312, 123123), null);
		
		assertEquals(3, mmanager.getGameObjectList(Harvester.class).size());
		
//		JsonNode haversterListAsJSON = mmanager.getHaversterListAsJSON();
//		System.out.println(haversterListAsJSON.toString());
		
	}

	@Test
	public void testCreate() {

		mmanager.createHarvester(new Vector2(5, 3), null);
		mmanager.createHarvester(new Vector2(544, 12), null);
		mmanager.createHarvester(new Vector2(12312, 123123), null);

//		mmanager.getHaversterListAsJSON();

		assertEquals(3, mmanager.size());
	}


	
}

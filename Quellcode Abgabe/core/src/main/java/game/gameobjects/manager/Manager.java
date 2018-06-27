package game.gameobjects.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.gameobjects.GameObject;

public abstract class Manager<E extends GameObject> {

	protected List<E> list;

	public Manager() {
		list = new ArrayList<>();
	}

	public void addGameobject(E obj) {
		if (obj == null || list.contains(obj)) {
			return;
		}

		list.add(obj);
	}

	public E getGameobjectByID(String id) {

		if (id == null) {
			return null;
		}

		for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
			E e = (E) iterator.next();

			if (e.getID().equals(id)) {
				return e;
			}
		}
		return null;
	}

	public E removeGameobject(String id) {

		E e = getGameobjectByID(id);
		e.dispose();
		list.remove(e);

		return e;

	}

	public void clear() {

		for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			e.dispose();
		}
		list = new ArrayList<>();

	}

	public E removeGameobject(E obj) {
		if (obj == null) {
			return null;
		}
		return removeGameobject(obj.getID());
	}

	public int size() {
		return list.size();

	}

	/**
	 * Repariert ein Gameobject
	 * 
	 * @param id
	 * @return True, falls eines repariert wurde, sonst false
	 */
	public boolean repair(String id) {

		E e = getGameobjectByID(id);
		if (e != null) {
			e.setLifepoints(100);
			return true;
		}

		return false;

	}

	/**
	 * schädigt alle GameObjecte im Manager
	 * 
	 * @param damage
	 *            Den verursachten Schaden
	 */
	public void damage(float damage) {

		for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
			E e = (E) iterator.next();

			e.setLifepoints(e.getLifepoints() - damage);

		}

	}

	abstract public void update(float deltaTime);

}

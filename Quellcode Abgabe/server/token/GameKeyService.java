package token;

import java.util.ArrayList;
import java.util.List;

public class GameKeyService {

	private static List<String> gameKeys = new ArrayList<>();

	public static void clear() {

		gameKeys = new ArrayList<>();
	}

	public synchronized static void add(String token) {

		gameKeys.add(token);
	}

}

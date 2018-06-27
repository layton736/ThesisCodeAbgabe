package token;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Klasse ist nur angelegt, aber noch nicht Fertig
 * 
 * @author Ahmed Salame
 *
 */

public class TokenService {

	private static List<String> tokens = new ArrayList<>();

	public static void clear() {

		tokens = new ArrayList<>();
	}

	public synchronized static void add(String token) {

		tokens.add(token);
	}

	/**
	 * Prüft ein Token
	 */
	public static boolean isValid(String token) {
		return true;
	}

}

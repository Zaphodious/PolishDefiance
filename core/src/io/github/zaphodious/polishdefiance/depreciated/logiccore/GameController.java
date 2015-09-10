package io.github.zaphodious.polishdefiance.depreciated.logiccore;

public class GameController {
	private static GameController controller;

	private GameController() {

	}

	public static final GameController getInstance() {
		if (controller == null) {
			controller = new GameController();
		}

		return controller;
	}

}

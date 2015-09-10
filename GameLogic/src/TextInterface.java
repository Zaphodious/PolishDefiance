import core.Command;
import core.GameController;
import entity.Goblin;
import entity.Hireling;
import entity.Unit;
import gameboard.Coords;
import gameboard.GameMap;

import java.util.Random;
import java.util.Scanner;

/**
 * A text interface for testing the game's logic in the command line.
 * 
 * @author achyt_000
 *
 */
final class TextInterface {

	GameController	controller;
	Random rand;

	public TextInterface(GameController controller, Random random) {
		this.controller = controller;
		this.rand = random;
	}

	public void start() {
		GameMap map = GameMap.newInstance(18, 8);

		Unit hireling = new Hireling();

		map.placeUnitOnMap(Coords.at(2, 6), hireling);
		map.placeUnitOnMap(Coords.at(6, 8), new Goblin());

		Scanner scanner = new Scanner(System.in);
		boolean runLoop = true;

		while (runLoop) {
			System.out.println(map.getMapAsString());
			System.out.println("Please enter w,a,s, or d to move the @ (enter 'e' to exit)");
			System.out.print(">");
			String stringCommand = scanner.nextLine();
			char[] toParse = stringCommand.toCharArray();
			for (char character : toParse) {
				Command command = Command.getCommand(character + "");
				switch (command) {
					case DOWN:
						map.moveUnit(hireling, Coords.Direction.SOUTH);
						break;
					case EXIT:
						runLoop = false;
						break;
					case INVALID:
						System.out.println("Sorry, " + character + " is not a valid command. Please try again.");
						break;
					case LEFT:
						map.moveUnit(hireling, Coords.Direction.WEST);
						break;
					case RIGHT:
						map.moveUnit(hireling, Coords.Direction.EAST);
						break;
					case UP:
						map.moveUnit(hireling, Coords.Direction.NORTH);
						break;
					default:
						break;

				}
				map.cullDamagedUnits();
			}

		}
		scanner.close();
	}

}

}

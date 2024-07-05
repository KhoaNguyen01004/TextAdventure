import java.util.List;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);
    private String userTextInput;
    private final GameMap map = new GameMap();
    private final Move userMove = new Move();
    private Bag bag = new Bag();

    /**
     * Takes user's input and validate it.
     */
    public void takeInput() {
        System.out.print("> ");
        userTextInput = scanner.nextLine();
    }

    public GameMap getMap(){
        return map;
    }

    private void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Process input according to the Scene
     * 
     * @param scene - Which scene to play
     */
    public void processInput(Scene scene) {
        if (scene == Scene.MAIN_MENU) {
            processMainMenu();
        }
        if (scene == Scene.MAIN_GAME) {
            processMainGame();
        }
    }

    private void processMainMenu() {
        validateMainMenuInput();
        switch (userTextInput) {
            case "1":
                App.setScene(Scene.MAIN_GAME);
                clearTerminal();
                printCurrentRoom();
                break;
            case "2":
                printHelp();
                System.out.println(App.mainMenu.options());
                break;
            default:
                System.exit(0);
        }
    }

    /**
     * Print the current room with its description
     */
    private void printCurrentRoom() {
        System.out.print(map.getRoom(userMove.getX(), userMove.getY()));
        System.out.print(":\n");
        System.out.println(map.getRoomDescription(userMove.getX(), userMove.getY()));
    }

    private void validateMainMenuInput() {
        if (!userTextInput.equals("1")
                && !userTextInput.equals("2")
                && !userTextInput.equals("3")) {
            System.out.print("Please only enter number from 1 to 3 only!\n");
            takeInput();
            processInput(Scene.MAIN_MENU);
        }
    }

    private void processMainGame() {
        validateMainGame();
        Coordinate currentCoordinate = new Coordinate(userMove.getX(), userMove.getY());
        if (userTextInput.equalsIgnoreCase("up")) {
            userMove.up();
        }
        if (userTextInput.equalsIgnoreCase("down")) {
            userMove.down();
        }
        if (userTextInput.equalsIgnoreCase("left")) {
            userMove.left();
        }
        if (userTextInput.equalsIgnoreCase("right")) {
            userMove.right();
        }
        if (userTextInput.equalsIgnoreCase("help")) {
            printHelp();
        }
        if (userTextInput.equalsIgnoreCase("use")) {
            List<Character> bagRemoved = map.unlockRoom(userMove.getX(), userMove.getY(), bag.getItem());
            for (Character c: bagRemoved){
                bag.remove(c);
            }
            
        }
        if (userTextInput.equalsIgnoreCase("take")) {
            takeItem(map.getKey(userMove.getX(), userMove.getY()));
            map.removeKeyAtRoom(userMove.getX(), userMove.getY());
        }
        if (userTextInput.equalsIgnoreCase("bag")) {
            bag.showItems();
        }

        Coordinate newCoordinate = new Coordinate(userMove.getX(), userMove.getY());
        if (!currentCoordinate.equals(newCoordinate)) {
            printCurrentRoom();
        }
    }

    private void takeItem(Character item) {
        if (map.getLock(userMove.getX(), userMove.getY()) == null) {
            if (item == null) {
                System.out.println("There is nothing to take here");
            } else {
                if (!bag.contains(item)) {
                    bag.add(item);
                    System.out.println("You have taken " + item + " key");
                } else {
                    System.out.println("You're already take that key");
                }
            }
        } else {
            System.out.println("The door is locked");
        }
    }

    private void validateMainGame() {
        if (!userTextInput.equalsIgnoreCase("up")
                && !userTextInput.equalsIgnoreCase("down")
                && !userTextInput.equalsIgnoreCase("left")
                && !userTextInput.equalsIgnoreCase("right")
                && !userTextInput.equalsIgnoreCase("take")
                && !userTextInput.equalsIgnoreCase("use")
                && !userTextInput.equalsIgnoreCase("bag")
                && !userTextInput.equalsIgnoreCase("help")
                && !userTextInput.equalsIgnoreCase("menu")) {
            System.out.print("Please only type the available command. Type help for more info\n ");
            takeInput();
            processInput(Scene.MAIN_GAME);
        }
    }

    private void printHelp() {
        System.out.println("Game Instructions:\n" +
                "Your goal is to unlock the last room (the room with 3 locks)\n" +
                "You could navigate through the game using the following command:\n" +
                "   up\n" +
                "   down\n" +
                "   left\n" +
                "   right\n" +
                "You could check your bag using command 'bag'\n" +
                "To pickup any item, use command 'take'\n");
    }

    @Override
    public String toString() {
        return userTextInput;
    }
}

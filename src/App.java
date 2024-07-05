public class App {

    public static final MainMenu mainMenu = new MainMenu();
    private static UserInput input = new UserInput();
    private static boolean run = true;
    private static Scene scene;

    public static void main(String[] args) {
        showMainMenu();
        while (run) {
            input.takeInput();
            input.processInput(scene);
            if (!input.getMap().locked(0, 2)){
                System.out.println("You Win!!! Thank you for playing!!");
                break;
            }
        }
    }

    public static void setScene(Scene newScene){
        scene = newScene;
    }

    private static void showMainMenu() {
        scene = Scene.MAIN_MENU;
        mainMenu.populateDefaultMainMenu();
        System.out.println(mainMenu.options());
    }
}

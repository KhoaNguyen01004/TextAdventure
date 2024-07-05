public class MainMenu extends Menu{

    /**
     * Populate the default main menu with given options: Start New, Resume, Help, and Quit
     */
    public void populateDefaultMainMenu(){
        super.clearOptions();
        String[] str = {"Start", "Help", "Quit"};
        super.addOptions(str);
    }

    /**
     * Set the options in the main menu
     * @param options - List of options that you want to set.
     */
    public void setMainMenuOption(String[] options){
        super.clearOptions();
        super.addOptions(options);
    }
}

public class Menu {
    private StringBuilder options;
    private int itemCount;

    public Menu() {
        this.itemCount = 0;
        this.options = new StringBuilder();
    }

    /**
     * Return all available options.
     */
    public String options() {
        return this.options.toString();
    }

    /**
     * Add an option to the option list in the menu
     * 
     * @param option - Option to add.
     */
    public void addOption(String option) {
        this.itemCount += 1;
        this.options.append(this.itemCount).append(". ").append(option).append("\n");
    }

    /**
     * Add a list of option to the option list in the menu
     * 
     * @param options - List of options to add.
     */
    public void addOptions(String[] options) {
        for (String option : options) {
            this.addOption(option);
        }
    }

    public void clearOptions(){
        options.setLength(0);
    }
}

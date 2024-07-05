import java.util.ArrayList;
import java.util.List;

public class Bag {
    
    private List<Character> bagList;

    public Bag(){
        bagList = new ArrayList<>();
    }

    /**
     * Add the item into the bag
     * @param item - Item to add
     */
    public void add(Character item){
        bagList.add(item);
    }

    /**
     * Remove that item from the bag
     * @param item - Item to remove
     */
    public void remove(Character item){
        bagList.remove(item);
    }

    /**
     * Check if that item is exist in the bag
     * @param item - Item to check
     * @return True if that item in the bag, false otherwise
     */
    public boolean contains(Character item){
        return bagList.contains(item);
    }

    /**
     * Return the number of item(s) currently in the bag
     * @return number of item(s)
     */
    public int length(){
        return bagList.size();
    }

    /**
     * Get the bagList object
     * @return the bagList object
     */
    public List<Character> getItem(){
        return bagList;
    }

    /**
     * Show the list of item(s) that currently in the bag
     */
    public void showItems(){
        StringBuilder bagString = new StringBuilder();
        int counter = 1;
        for (Character item: bagList){
            bagString.append(counter + ". ").append(item + "\n");
            counter += 1;
        }
        System.out.println(bagString);
    }
}

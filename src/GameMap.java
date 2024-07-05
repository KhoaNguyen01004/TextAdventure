import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class GameMap {
    private Map<Coordinate, String> map = new HashMap<>();
    private Map<Coordinate, String> rooms = new HashMap<>();
    private Map<Coordinate, Boolean> lockedRoom = new HashMap<>();
    private Map<Coordinate, Character> roomKey = new HashMap<>();
    private Map<Coordinate, List<Character>> roomLock = new HashMap<>();

    public GameMap() {
        populateMap();
        populateRoom();
        initLockRoom();
        initKey();
        initLock();
    }

    private void populateMap() {
        String stair = "Stair";
        map.put(new Coordinate(0, 2), "Master Bedroom");
        map.put(new Coordinate(1, 2), stair);
        map.put(new Coordinate(2, 2), "Hallway");
        map.put(new Coordinate(3, 2), "Balcony");

        map.put(new Coordinate(0, 1), "Laundry Room");
        map.put(new Coordinate(1, 1), "Living Room");
        map.put(new Coordinate(2, 1), stair);
        map.put(new Coordinate(3, 1), "Guest Room");

        map.put(new Coordinate(0, 0), "Storage Room");
        map.put(new Coordinate(1, 0), stair);
        map.put(new Coordinate(2, 0), "Kitchen");
        map.put(new Coordinate(3, 0), "Garage");
    }

    private void populateRoom() {
        rooms.put(new Coordinate(0, 2),
                "You step into a spacious room featuring a king-sized bed elegantly dressed in luxurious linens, bathed in ample natural light. The room is secured with three distinct locks: one shaped like '@' symbol, another like '#', and a third resembling the '$' symbol. These locks ensure access is restricted to those with the corresponding keys, adding a layer of mystery and intrigue to the serene ambiance.");
        rooms.put(new Coordinate(1, 2), "You can go down");
        rooms.put(new Coordinate(2, 2),
                "As you traverse the hallway, natural light streams in through large windows, casting a warm glow along the polished floors. The walls, adorned with tasteful artwork, guide your path past doors leading to various rooms. Its spacious layout and ambient lighting create a welcoming atmosphere, facilitating seamless movement between different areas of the home.");
        rooms.put(new Coordinate(3, 2),
                "Stepping onto the balcony, you're greeted by an expansive outdoor space offering panoramic views. With ample room for seating or lounging, it invites relaxation amidst the open air and the sights and sounds of the surrounding environment. Nearby, a key shaped like the '$' symbol glints in the sunlight, available for pickup and hinting at further exploration within the residence.");

        rooms.put(new Coordinate(0, 1),
                "Upon entering the laundry room, you're greeted by neatly organized shelves stocked with cleaning supplies and fresh linens. The room is illuminated by a soft overhead light, casting a warm glow over the washer and dryer. Notably, the door is secured with a lock shaped like a '*', hinting at the room's purpose and ensuring privacy for its contents. Nearby, an '@' shaped key rests on a small shelf, hinting at its significance within the home's layout.");
        rooms.put(new Coordinate(1, 1),
                "Entering the living room, you are welcomed by a cozy and inviting atmosphere. The space is adorned with comfortable sofas and chairs arranged around a central coffee table, perfect for gatherings or relaxation. Natural light filters through large windows, highlighting the tasteful decor and artwork on the walls. Notably, a '&' shaped key rests on a decorative tray, adding a touch of intrigue to the room's warm and inviting ambiance.");
        rooms.put(new Coordinate(2, 1), "You can either go up or down");
        rooms.put(new Coordinate(3, 1),
                "Stepping into the guest room, you find a comfortable retreat designed with hospitality in mind. The room features a cozy bed dressed in crisp linens and a bedside table adorned with thoughtful amenities. Soft lighting creates a welcoming ambiance, perfect for relaxation. Notably, a key shaped like '%' rests on the dresser, ready for use and adding a touch of charm to this inviting space.");

        rooms.put(new Coordinate(0, 0),
                "Upon entering the storage room, you're greeted by shelves neatly stacked with boxes and containers, each labeled for easy organization. Soft overhead lighting casts a gentle glow over the room's contents, revealing a collection of stored items. The door is securely locked with a '%' shaped lock, ensuring the room's contents remain protected. Nearby, a key shaped like '$' hangs on a hook, ready for access to the stored treasures within.");
        rooms.put(new Coordinate(1, 0), "You can go up");
        rooms.put(new Coordinate(2, 0),
                "Stepping into the kitchen, you enter a bustling hub of culinary activity. The room is filled with the comforting aromas of freshly cooked meals and the sound of utensils clinking against pots and pans. Bright overhead lights illuminate the spacious counter-tops and modern appliances, making it easy to prepare delicious dishes. Unlike other rooms, there are no locks or collectible items here—just a welcoming space dedicated to the art of cooking and gathering.");
        rooms.put(new Coordinate(3, 0),
                "Entering the garage, you encounter a functional space designed for practicality and storage. The air carries the scent of oil and tools, blending with the faint whiff of freshly cut grass from outside. The room is illuminated by fluorescent lights hanging overhead, casting a bright, industrial glow over the various tools, gardening equipment, and stored items neatly arranged along the walls. There are no locks or collectible items here, just a utilitarian space that serves as a workshop and storage area for household essentials and outdoor gear.");
    }

    private void initLockRoom() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                lockedRoom.put(new Coordinate(x, y), roomNeedLock(x, y));
            }
        }
    }

    /**
     * Determine if this room has a lock or not
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return True if this room initially has a lock, false otherwise
     */
    public boolean roomNeedLock(int x, int y) {
        return (((x == 0) && (y == 0)) ||
                ((x == 0) && (y == 1)) ||
                ((x == 0) && (y == 2)) ||
                ((x == 3) && (y == 0)));
    }

    private void initKey() {
        roomKey.put(new Coordinate(0, 0), '$');
        roomKey.put(new Coordinate(0, 1), '@');
        roomKey.put(new Coordinate(1, 1), '&');
        roomKey.put(new Coordinate(3, 0), '#');
        roomKey.put(new Coordinate(3, 1), '%');
    }

    private void initLock() {
        roomLock.put(new Coordinate(0, 0), new ArrayList<>(Arrays.asList('%')));
        roomLock.put(new Coordinate(0, 1), new ArrayList<>(Arrays.asList('*')));
        roomLock.put(new Coordinate(3, 0), new ArrayList<>(Arrays.asList('&')));
        roomLock.put(new Coordinate(0, 2), new ArrayList<>(Arrays.asList('@', '#', '$')));
    }

    /**
     * Get the name of the room
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return The string name of the room
     */
    public String getRoom(int x, int y) {
        return map.get(new Coordinate(x, y));
    }

    /**
     * Get the description of the room
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return The description of the room
     */
    public String getRoomDescription(int x, int y) {
        return rooms.get(new Coordinate(x, y));
    }

    /**
     * Determine if the room is locked or not.
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return True if the room is locked, false otherwise.
     */
    public boolean locked(int x, int y) {
        return lockedRoom.get(new Coordinate(x, y));
    }

    /**
     * Get the lock(s) of a specific room if it's locked
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return The list of lock(s) in this room
     */
    public List<Character> getLock(int x, int y) {
        return roomLock.get(new Coordinate(x, y));
    }

    /**
     * Get the key of a specific room if it's available
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return The key in this room
     */
    public Character getKey(int x, int y){
        return roomKey.get(new Coordinate(x, y));
    }

    /**
     * Return true if this room has a key, false otherwise
     * 
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @return - Whenever this room has a key or not.
     */
    public boolean thisRoomHasKey(int x, int y) {
        Coordinate currentCoordinate = new Coordinate(x, y);
        for (Coordinate room : roomKey.keySet()) {
            if (currentCoordinate.equals(room)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Unlock specific room with a given list of key(s)
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     * @param keys - List of key(s) use to unlock the door
     * @return the lock that has been unlocked
     */
    public List<Character> unlockRoom(int x, int y, List<Character> keys) {
        Coordinate coordinate = new Coordinate(x, y);
        List<Character> lockOpened = new ArrayList<>();
        if (!roomLock.containsKey(coordinate)) {
            System.out.println("This room does not have any locks.");
            return Collections.emptyList();
        }
    
        List<Character> locks = roomLock.get(coordinate);
        if (locks == null || locks.isEmpty()) {
            System.out.println("This room does not have any locks.");
            return Collections.emptyList();
        }
    
        for (Character key : keys) {
            if(locks.remove(key)){
                lockOpened.add(key);
            }
        }
    
        if (locks.isEmpty()) {
            lockedRoom.put(coordinate, false);
            System.out.println("This room has been unlocked.");
        } else {
            System.out.println("Some locks still remain in this room.");
        }
        return lockOpened;
    }
    /**
     * Remove the key at a specific room
     * @param x - x-coordinate of the room
     * @param y - y-coordinate of the room
     */
    public void removeKeyAtRoom(int x, int y) {
        Coordinate currentCoordinate = new Coordinate(x, y);
        if (roomKey.containsKey(currentCoordinate)) {
            roomKey.remove(currentCoordinate);
        }
    }
}

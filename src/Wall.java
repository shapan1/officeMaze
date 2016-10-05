/**
 * 
 */

/**
 * @author Shapan
 *
 */
public class Wall {
    /**
     * curremt Room and next Room.
     */
    private Room currRoom, nextRoom;
    public boolean isWallPresent;

    /**
     * walls without adjacent rooms.
     */
    public Wall(Room w) {
        currRoom = w;
        nextRoom = null;
    }

    /**
     * walls with adjacent rooms.
     */
    public Wall(Room currRoom, Room nextRoom) {
        this.currRoom = currRoom;
        this.nextRoom = nextRoom;
    }

    public Room getCurrRoom() {
        return currRoom;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public boolean isWallPresent() {
        return isWallPresent;
    }
}

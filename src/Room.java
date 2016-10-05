import java.util.LinkedList;

/**
 * 
 */

/**
 * @author Shapan
 *
 */
public class Room {

    /**
     * row and column for a room.
     */
    private int row, col;
    /**
     * the 4 walls for each room.
     */
    public Wall top, right, bottom, left;

    /**
     * pointer to previous room.
     */
    private Room prev;

    /**
     * room Number.
     */
    public int roomNum;

    /**
     * adjacent rooms stored in Linked List.
     */
    public LinkedList<Room> adjacentRooms;

    /**
     * constructor.
     * 
     * @param row
     * @param col
     */
    public Room(int row, int col) {
        this.row = row;
        this.col = col;
        adjacentRooms = new LinkedList<Room>();
        prev = null;
        roomNum = 0;
    }

    /**
     * to get room number.
     * 
     * @return
     */
    public int getRoomNum() {
        return roomNum++;
    }

    public int getRow() {
        return row;
    }

    public int getcol() {
        return col;
    }
}

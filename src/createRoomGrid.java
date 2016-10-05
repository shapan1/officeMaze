/**
 * concept of disjoint sets used.
 */

/**
 * @author Shapan
 *
 */
public class createRoomGrid {
    /**
     * to create grid of rooms.
     */
    private int[] grid;

    /**
     * constructor.
     * 
     * @param setSize
     */
    public createRoomGrid(int gridSize) {
        grid = new int[gridSize];
        // intialize the grid to -1
        for (int i = 0; i < grid.length; i++) {
            grid[i] = -1;
        }
    }

    /**
     * find in grid method.
     * 
     * @param num
     * @return
     */
    public int findInGrid(int num) {
        if (grid[num] < 0) {
            return num;
        } else {
            return grid[num] = findInGrid(grid[num]);
        }
    }

    /**
     * joining rooms.
     * 
     * @param room1
     * @param room2
     */
    public void joinRooms(int room1, int room2) {
        if (grid[room2] < grid[room1]) {
            grid[room1] = room2;
        } else {
            if (grid[room1] == grid[room2]) {
                grid[room1]--;
            }
            grid[room2] = room1;
        }
    }
}

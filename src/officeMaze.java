import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Shapan
 *
 */
public class officeMaze extends JPanel {
    /**
     * list of walls.
     */
    private ArrayList<Wall> walls;
    /**
     * to randomisze rooms
     */
    private Random random;
    /**
     * height and width of matrix.
     */
    private int height, width;
    /**
     * increment number.
     */
    private int incr;
    /**
     * create disjoint set join rooms.
     */
    private createRoomGrid disjointSet;
    /**
     * Rooms matrix
     */
    private Room[][] rooms;

    /**
     * panel variables.
     */
    private int w, h, size, randWall;
    /**
     * to get room number.
     */
    private int roomNumber;

    /**
     * 
     * @param height
     * @param width
     */

    public officeMaze(int height, int width) {
        this.height = height;
        this.width = width;
        rooms = new Room[height][width];
        walls = new ArrayList<Wall>((height - 1) * (width - 1));
        generateMaze();
        setPreferredSize(new Dimension(500, 700));
    }

    private void generateMaze() {
        // TODO Auto-generated method stub
        generateRooms();
        disjointSet = new createRoomGrid(width * height);
        incr = width * height;
        random = new Random();

        while (incr > 1) {
            // select random walls where we want walls to be present.
            randWall = random.nextInt(walls.size());
            // System.out.println(randWall);
            Wall temp = walls.get(randWall);
            // System.out.println("col:" + temp.getCurrRoom().getcol());
            // System.out.println("row:" + temp.getNextRoom().getRow());
            int room1 = temp.getCurrRoom().getcol() + temp.getNextRoom().getRow() * width;
            int room2 = temp.getNextRoom().getcol() + temp.getCurrRoom().getRow() * width;
            // check if room 1 and 2 are already neighbours.

            if (disjointSet.findInGrid(room1) != disjointSet.findInGrid(room2)) {
                walls.remove(randWall);
                disjointSet.joinRooms(disjointSet.findInGrid(room1), disjointSet.findInGrid(room2));
                temp.isWallPresent = true;
                temp.getCurrRoom().adjacentRooms.add(temp.getNextRoom());
                temp.getNextRoom().adjacentRooms.add(temp.getCurrRoom());
                incr--;
            }
        }

    }

    private void generateRooms() {
        // TODO Auto-generated method stub
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // create top wall.
                rooms[i][j] = new Room(i, j);
                if (i == 0) {
                    rooms[i][j].top = new Wall(rooms[i][j]);
                } else {
                    rooms[i][j].top = new Wall(rooms[i - 1][j], rooms[i][j]);
                    walls.add(rooms[i][j].top);
                }
                if (i == height - 1) {
                    rooms[i][j].bottom = new Wall(rooms[i][j]);
                }
                if (j == 0) {
                    rooms[i][j].left = new Wall(rooms[i][j]);
                } else {
                    rooms[i][j].left = new Wall(rooms[i][j - 1], rooms[i][j]);
                    walls.add(rooms[i][j].left);
                }
                if (j == width - 1) {
                    rooms[i][j].right = new Wall(rooms[i][j]);
                }
                rooms[i][j].roomNum = roomNumber++;
            }
        }
        // start point.
        // System.out.println(rooms[0][0].left.isWallPresent);
        rooms[0][0].top.isWallPresent = true;
        rooms[0][0].roomNum = 0;
        // end point.
        // System.out.println(rooms[height - 2][width - 2].right.isWallPresent);
        rooms[height - 1][width - 1].bottom.isWallPresent = true;
        rooms[height - 1][width - 1].roomNum = (height * width);
    }

    @Override
    public void paintComponent(Graphics g) {
        w = 50;
        h = 50;
        // could have taken height as well as width
        // just need something to base the roomsize
        size = (width - w) / width + 20;

        // temp variables used for painting
        int x = w;
        int y = h;

        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (!(rooms[i][j].top.isWallPresent)) {
                    g.drawLine(x, y, x + size, y);
                } // end of north if
                  // west wall not there draw the line
                if (rooms[i][j].left.isWallPresent == false) {
                    g.drawLine(x, y, x, y + size);
                } // end of west if
                if ((i == height - 1) && rooms[i][j].bottom.isWallPresent == false) {
                    g.drawLine(x, y + size, x + size, y + size);
                } // end of south if
                if ((j == width - 1) && rooms[i][j].right.isWallPresent == false) {
                    g.drawLine(x + size, y, x + size, y + size);
                } // end of east if
                x += size;// change the horizontal
            } // end of inner for loop
            x = w;
            y += size;
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.getContentPane().add(new officeMaze(30, 20));
        frame.pack();
        frame.setVisible(true);
    }

}

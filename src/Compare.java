import java.util.List;

/**
 * Class to compare JPS and A* on a manually created grid
 */
public class Compare{
    public static void main(String args[]){

        //Manually created dense Grid of bigger size to show that JPS is faster than A*

        Grid grid = new Grid(3000, 3000);

        for (int x = 0; x < 3000; x++) {
            for (int y = 0; y < 3000; y++) {

                // Add obstacles
                if ((x + y) % 3 == 0 && !(x == 0 && y == 0) && !(x == 2999 && y == 2999)) {
                    grid.setWalkable(x, y, false);
                }
            }
        }

        // Create a guaranteed path from the start to the goal
        for (int i = 0; i < 3000; i++) {
            grid.setWalkable(i, i, true);
            if (i < 2999){
                grid.setWalkable(i + 1, i, true);
                grid.setWalkable(i, i + 1, true);
            }
        }

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2999, 2999);

        JPS jps = new JPS(grid);
        long startjps = System.nanoTime();
        List<Node> path = jps.findPath(start, goal);
        long endjps = System.nanoTime();
        System.out.println("JPS Path found. Time: " + (endjps - startjps) / 1e6 + " ms");

        AStar aStar = new AStar(grid);
        long startStar = System.nanoTime();
        List<Node> pathStar = aStar.findPath(start, goal);
        long endStar = System.nanoTime();
        System.out.println("A* Path found. Time: " + (endStar - startStar) / 1e6 + " ms");
    }
}
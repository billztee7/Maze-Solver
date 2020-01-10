import java.util.ArrayDeque;
import java.util.Queue;

public class Lee {
    // M x N matrix
    private static final int M = 10;
    private static final int N = 10;

    // Below arrays details all 4 possible movements from a cell
    private static final int row[] = {-1, 0, 0, 1};
    private static final int col[] = {0, -1, 1, 0};

    // Function to check if it is possible to go to position (row, col)
    // from current position. The function returns false if (row, col)
    // is not a valid position or has value 0 or it is already visited
    private static boolean isValid(int mat[][], boolean visited[][],
                                   int row, int col) {
        return (row >= 0) && (row < M) && (col >= 0) && (col < N)
                && mat[row][col] == 1 && !visited[row][col];
    }

    // Find Shortest Possible Route in a matrix mat from source
    // cell (startX, startY) to destination cell (endX, endY)
    static void BFS(int mat[][], int startX, int startY, int endX, int endY) {
        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[M][N];

        // create an empty queue
        Queue<Node> q = new ArrayDeque<>();

        // mark source cell as visited and enqueue the source node
        visited[startX][startY] = true;
        q.add(new Node(startX, startY, 0));

        // stores length of longest path from source to destination
        int min_dist = Integer.MAX_VALUE;

        // run till queue is not empty
        while (!q.isEmpty()) {
            // pop front node from queue and process it
            Node node = q.poll();

            // (i, j) represents current cell and dist stores its
            // minimum distance from the source
            startX = node.x;
            startY = node.y;
            int dist = node.dist;

            // if destination is found, update min_dist and stop
            if (startX == endX && startY == endY) {
                min_dist = dist;
                break;
            }

            // check for all 4 possible movements from current cell
            // and enqueue each valid movement
            for (int k = 0; k < 4; k++) {
                // check if it is possible to go to position
                // (i + row[k], j + col[k]) from current position
                if (isValid(mat, visited, startX + row[k], startY + col[k])) {
                    // mark next cell as visited and enqueue it
                    visited[startX + row[k]][startY + col[k]] = true;
                    q.add(new Node(startX + row[k], startY + col[k], dist + 1));
                }
            }
        }

        if (min_dist != Integer.MAX_VALUE) {
            System.out.print("The shortest path from source to destination "
                    + "has length " + min_dist);
        } else {
            System.out.print("Destination can't be reached from source");
        }
    }
}

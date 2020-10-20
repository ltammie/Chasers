package ChaseLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class PathFinder {
    private final int width;
    private final int height;
    private final int[] row = {-1, 0, 0 , 1};
    private final int[] col = {0,-1, 1, 0};

    private boolean isValid(int[][] map, boolean[][] visited, int row, int col) {
        return row >= 0 && row < width && col >= 0 && col < height && !visited[row][col] && map[row][col] != -1;
    }

    private boolean check(int[][] map, int row, int col) {
        return row >= 0 && row < width && col >= 0 && col < height && map[row][col] != -1;
    }

    public PathFinder(int size) {
        this.width = size;
        this.height = size;
    }

    public boolean findPath(int[][] map, int startX, int startY, int targetX, int targetY) {
        boolean[][] visited = new boolean[width][height];
        Queue<Node> q = new ArrayDeque<>();
        int minDistance = Integer.MAX_VALUE;
        visited[startX][startY] = true;

        q.add(new Node(startX, startY, 0));

        while (!q.isEmpty()) {
            Node current = q.poll();
            startX = current.x;
            startY = current.y;
            int dist = current.dist;

            if (startX == targetX && startY == targetY) {
                minDistance = dist;
                break;
            }
            for (int i = 0; i < 4; i++) {
                if (isValid(map, visited, startX + row[i], startY + col[i])) {
                    visited[startX + row[i]][startY + col[i]] = true;
                    q.add(new Node(startX + row[i], startY + col[i], dist + 1));
                    map[startX + row[i]][startY + col[i]] = dist + 1;
                }
            }
        }
        return minDistance != Integer.MAX_VALUE;
    }

    public ArrayList<Node> recoverPath(int[][] map, int fromX, int fromY, int toX, int toY) {
        ArrayList<Node> path = new ArrayList<>();
        Node current = new Node(toX, toY, map[toX][toY]);
        path.add(current);


        while (current.x != fromX || current.y != fromY) {

            for (int i = 0; i < 4; i++) {
                if (check(map, current.x + row[i], current.y + col[i]) && map[current.x + row[i]][current.y + col[i]] == current.dist - 1) {
                    current = new Node(current.x + row[i], current.y + col[i], map[current.x + row[i]][current.y + col[i]]);
                    path.add(current);
                    break;
                }
            }

        }
        return path;
    }
}

package GameLogic;

import ChaseLogic.Node;
import ChaseLogic.PathFinder;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private int[][] pathMap;
    private Point pos;


    public Enemy(int[][] pathMap, int x, int y) {
        this.pathMap = pathMap;
        this.pos = new Point(x, y);
    }

    public GameState makeTurn(MapGenerator generator, PathFinder pathFinder) {
        this.pathMap[pos.x][pos.y] = 0;
        boolean pathExists = pathFinder.findPath(this.pathMap, pos.x, pos.y,
                                generator.getPlayerPos().x, generator.getPlayerPos().y);
        if (pathExists) {
            ArrayList<Node> path = pathFinder.recoverPath(this.pathMap, pos.x, pos.y,
                    generator.getPlayerPos().x, generator.getPlayerPos().y);
            Node newPos = path.get(path.size() - 2);
            this.pos.setLocation(newPos.getX(), newPos.getY());
        }
        this.pathMap[pos.x][pos.y] = -1;
        if (this.pos.x == generator.getPlayerPos().x && this.pos.y == generator.getPlayerPos().y) {
            return GameState.LOSE;
        } else {
            return GameState.PLAYING;
        }
    }

    public int getPosX() {
        return pos.x;
    }

    public int getPosY() {
        return pos.y;
    }
}

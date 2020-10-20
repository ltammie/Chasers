package GameLogic;

import ChaseLogic.PathFinder;
import com.diogonunes.jcolor.Attribute;

import java.awt.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

import static com.diogonunes.jcolor.Ansi.colorize;

public class MapGenerator {
    private final Console console;
    private String[][] map;
    private int[][] path;
    private Player player;
    private LinkedList<Enemy> enemies;
    private Point goalPos;
    public String enemyChar;
    public String playerChar;
    public String wallChar;
    public String goalChar;
    public String emptyChar;
    public String enemyColor;
    public String playerColor;
    public String wallColor;
    public String goalColor;
    public String emptyColor;


    public MapGenerator(Console console, Properties properties) {
        this.console = console;
        this.map = new String[console.getSize()][console.getSize()];
        this.path = new int[console.getSize()][console.getSize()];
        this.player = new Player(0, 0);
        this.enemies = new LinkedList<>();
        this.goalPos = new Point();

        this.enemyChar = properties.getProperty("enemy.char");
        this.playerChar = properties.getProperty("player.char");
        this.wallChar = properties.getProperty("wall.char");
        this.goalChar = properties.getProperty("goal.char");
        this.emptyChar = properties.getProperty("empty.char");

        this.enemyColor = properties.getProperty("enemy.color");
        this.playerColor = properties.getProperty("player.color");
        this.wallColor = properties.getProperty("wall.color");
        this.goalColor = properties.getProperty("goal.color");
        this.emptyColor = properties.getProperty("empty.color");
    }

    public void generateNewMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = emptyChar;
                path[i][j] = 0;
            }
        }
        addWalls();
        addGoal();
        spawnPlayer();
    }

    public boolean validateMap(PathFinder pathFinder) {
        return pathFinder.findPath(path, player.getPosition().x,
                player.getPosition().y, goalPos.x, goalPos.y);
    }

    public void resetPathMap() {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                path[i][j] = Math.min(path[i][j], 0);
            }
        }
    }


    private void addWalls() {
        int i = 0;
        Random random = new Random();
        while (i < console.getWallsCount()) {
            int x = random.nextInt(console.getSize());
            int y = random.nextInt(console.getSize());
            if (map[x][y].equals(emptyChar)) {
                map[x][y] = wallChar;
                path[x][y] = -1;
                i++;
            }
        }
    }

    private void addGoal() {
        int i = 0;
        Random random = new Random();
        while (i < 1) {
            int x = random.nextInt(console.getSize());
            int y = random.nextInt(console.getSize());
            if (map[x][y].equals(emptyChar)) {
                map[x][y] = goalChar;
                path[x][y] = 0;
                goalPos.setLocation(x, y);
                i++;
            }
        }
    }

    private void spawnPlayer() {
        int i = 0;
        Random random = new Random();
        while (i < 1) {
            int x = random.nextInt(console.getSize());
            int y = random.nextInt(console.getSize());
            if (map[x][y].equals(emptyChar)) {
                player.setPosition(x, y);
                i++;
            }
        }
    }

    public void spawnEnemies() {
        int i = 0;
        Random random = new Random();
        while (i < console.getEnemiesCount()) {
            int x = random.nextInt(console.getSize());
            int y = random.nextInt(console.getSize());
            if (map[x][y].equals(emptyChar) && x != player.getPosition().x && y != player.getPosition().y) {
                path[x][y] = -1;
                enemies.add(new Enemy(path, x, y));
                i++;
            }
        }
        path[goalPos.x][goalPos.y] = -1;
    }

    public void printStringMap() {
        boolean enemyPrinted = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                enemyPrinted = false;
                for (int k = 0; k < console.getEnemiesCount(); k++) {
                    if (i == enemies.get(k).getPosX() && j == enemies.get(k).getPosY()) {
                        System.out.print(colorize(enemyChar,Colors.getColor(enemyColor),
                                Attribute.BLACK_TEXT()));
                        enemyPrinted = true;
                        break;
                    }
                }
                if (enemyPrinted) {
                    continue;
                }
                if (player.getPosition().x == i && player.getPosition().y == j) {
                    System.out.print(colorize(playerChar,
                            Colors.getColor(playerColor), Attribute.BLACK_TEXT()));
                } else if (map[i][j].equals(goalChar)) {
                    System.out.print(colorize(map[i][j], Colors.getColor(goalColor),
                            Attribute.BLACK_TEXT()));
                } else if (map[i][j].equals(wallChar)) {
                    System.out.print(colorize(map[i][j], Colors.getColor(wallColor),
                            Attribute.BLACK_TEXT()));
                } else {
                    System.out.print(colorize(map[i][j],Colors.getColor(emptyColor),
                            Attribute.BLACK_TEXT()));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printPathMap() {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Point getPlayerPos() {
        return player.getPosition();
    }

    public Player getPlayer() {
        return player;
    }

    public Point getGoalPos() {
        return goalPos;
    }

    public String[][] getStringMap() {
        return this.map;
    }

    public int[][] getPathMap() {
        return this.path;
    }

    public int getMapSize() {
        return this.console.getSize();
    }

    public LinkedList<Enemy> getEnemies() {
        return this.enemies;
    }

    public int getEnemyCount() {
        return this.console.getEnemiesCount();
    }
}

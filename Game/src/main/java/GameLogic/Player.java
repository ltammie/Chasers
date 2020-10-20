package GameLogic;
import java.awt.*;
import java.util.Scanner;

public class Player {
    private Point position;

    public Player(int x, int y) {
        this.position = new Point(x, y);
    }

    private static boolean canGo(Point pos, MapGenerator g) {
        return pos.x >= 0 && pos.x < g.getMapSize() && pos.y >= 0 && pos.y < g.getMapSize() && isEnemy(g, pos);
    }

    private static boolean isEnemy(MapGenerator g, Point newPos) {
        for (int i = 0; i < g.getEnemyCount(); i++) {
            Enemy e = g.getEnemies().get(i);
            if (newPos.x == e.getPosX() && newPos.y == e.getPosY())
                return false;
        }
        return true;
    }

    public GameState makeTurn(MapGenerator generator, String appMode) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean finishedTurn = false;
        Point newPos = new Point(position.x, position.y);

        while (!finishedTurn) {
            Flush.clearScreen(appMode);
            generator.printStringMap();
            printCommands();
            command = scanner.nextLine();
            switch (command) {
                case "1":
                    newPos.setLocation(position.x, position.y - 1);
                    if (canGo(newPos, generator)) {
                        finishedTurn = generator.getStringMap()[newPos.x][newPos.y].equals(generator.emptyChar)
                                || generator.getStringMap()[newPos.x][newPos.y].equals(generator.goalChar);
                    }
                    if (!finishedTurn) {
                        System.out.println("You can't go there!");
                    }
                    break;
                case "2":
                    newPos.setLocation(position.x - 1, position.y);
                    if (canGo(newPos, generator)) {
                        finishedTurn = generator.getStringMap()[newPos.x][newPos.y].equals(generator.emptyChar)
                                || generator.getStringMap()[newPos.x][newPos.y].equals(generator.goalChar);
                    }
                    if (!finishedTurn) {
                        System.out.println("You can't go there!");
                    }
                    break;
                case "3":
                    newPos.setLocation(position.x, position.y  + 1);
                    if (canGo(newPos, generator)) {
                        finishedTurn = generator.getStringMap()[newPos.x][newPos.y].equals(generator.emptyChar)
                                || generator.getStringMap()[newPos.x][newPos.y].equals(generator.goalChar);
                    }
                    if (!finishedTurn) {
                        System.out.println("You can't go there!");
                    }
                    break;
                case "4":
                      newPos.setLocation(position.x + 1, position.y);
                    if (canGo(newPos, generator)) {
                        finishedTurn = generator.getStringMap()[newPos.x][newPos.y].equals(generator.emptyChar)
                                || generator.getStringMap()[newPos.x][newPos.y].equals(generator.goalChar);
                    }
                    if (!finishedTurn) {
                        System.out.println("You can't go there!");
                    }
                    break;
                case "9":
                    System.out.println("YOU DIED");
                    System.exit(0);
                default:
                    System.out.println("Wrong command");
                    break;

            }
        }
        Flush.clearScreen(appMode);
        position.setLocation(newPos);
        generator.printStringMap();
        if (generator.getStringMap()[position.x][position.y].equals(generator.goalChar)) {
            return GameState.WIN;
        } else {
            return GameState.PLAYING;
        }
    }


    public void printCommands() {
        System.out.println("Available commands:\n1:\tmove left\n2:\tmove up\n3:\tmove right\n4:\tmove down\n" +
                "9:\tconcede\n");
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
    }
}

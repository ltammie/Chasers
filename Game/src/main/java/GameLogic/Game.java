package GameLogic;

import ChaseLogic.PathFinder;

import java.util.ArrayList;
import java.util.Scanner;

enum GameState{
    WIN,
    LOSE,
    PLAYING
}

public class Game {
    private ArrayList<String> settings;
    private GameState state;

    public Game(){
        this.state = GameState.PLAYING;
    }

    public void startGame(MapGenerator generator, PathFinder pathFinder, String appMode) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (state == GameState.PLAYING) {
            state = generator.getPlayer().makeTurn(generator, appMode);
            if (state == GameState.WIN) {
                break;
            }
            for (int i = 0; i < generator.getEnemyCount(); i++) {
                Flush.clearScreen(appMode);
                state = generator.getEnemies().get(i).makeTurn(generator, pathFinder);
                generator.printStringMap();
                System.out.format("Enemy %d turn\n", i);
                if (appMode.equals("development")) {
                    do {
                        System.out.println("Confirm enemy turn by pressing 8");
                        command = scanner.nextLine();
                    } while (!command.equals("8"));
                }
                if (state == GameState.LOSE) {
                    break;
                }
            }
        }

        System.out.println(state);
    }
}

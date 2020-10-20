package GameLogic;

import ChaseLogic.PathFinder;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Console console = new Console();
        Game newGame = new Game();
        String fileName;

        try {
            JCommander.newBuilder().addObject(console).build().parse(args);
        } catch (ParameterException e) {
            System.out.println("One of parameters is wrong!");
            System.exit(-1);
        }

        if (!console.checkParameters()) {
            throw new IllegalParametersException("Wrong parameters: map is too small!");
        }

        Properties properties = new Properties();
        try {
            if (console.getProfile().equals("development")) {
                properties.load(Game.class.getResourceAsStream("/application-dev.properties"));
            } else if (console.getProfile().equals("production")) {
                properties.load(Game.class.getResourceAsStream("/application-production.properties"));
            } else {
                properties.load(Game.class.getResourceAsStream("/application-custom.properties"));
            }
        } catch (IOException e) {
            System.out.println("Cannot find or open properties file!");
            System.exit(-1);
        }

        for (String key : properties.stringPropertyNames()) {
            if (properties.getProperty(key).equals("")) {
                properties.setProperty(key, " ");
            }
        }

        MapGenerator generator = new MapGenerator(console, properties);
        PathFinder pathFinder = new PathFinder(console.getSize());

        generator.generateNewMap();
        while (!generator.validateMap(pathFinder)) {
            generator.generateNewMap();
        }
        generator.resetPathMap();
        generator.spawnEnemies();
        newGame.startGame(generator, pathFinder, console.getProfile());

    }
}

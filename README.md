# INFO
Simple Pac-Man like console-game
![](https://github.com/ltammie/Chasers/blob/master/scr/1.png)

* language - Java
* platform - macOS
* additional libraries - JCommander for parsong command-line arguments
                       - JCDP (JColor) for beautiful colors
                       
# Usage
Make sure u have installed 
* JDK-8 or higher to run
* Maven to compile project

After thin, run the following commands:
```
mvn -f ChaseLogic clean install && mvn -f Game clean package
```
To start the game:
```
java -jar Game/target/Game-1.0.jar --enemiesCount=2 --wallsCount=5 --size=6 --profile=production
```

Available command-line arguments:
* --enemiesCount=N (short -e): to set N of enemies to be spawned
* --wallsCount=N (short -w): to set number of walls
* --size=N or (short -s): to set the size of the map (N X N)
* --profile=production (short -p): to launch game in production mode (type develoment or custom for other modes)

In addition, you can customize *application-custom.properties* to set your own game settings: change how enemies/walls/target and player looks like, and change colors. Available colors: RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE, BLACK



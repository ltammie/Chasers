package GameLogic;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.Parameter;

@Parameters(separators = "=")
public class Console {
    @Parameter(names = {"--enemiesCount", "-e"})
    private int enemiesCount = 1; // default value if parameter is not specified

    @Parameter(names = {"--wallsCount", "-w"})
    private int wallsCount = 0; // default value if parameter is not specified

    @Parameter(names = {"--size", "-s"})
    private int size = 10; // default value if parameter is not specified

    @Parameter(names = {"--profile", "-p"})
    private String profile = "production"; // default value if parameter is not specified

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public int getWallsCount() {
        return wallsCount;
    }

    public int getSize() {
        return size;
    }

    public String getProfile() {
        return profile;
    }

    public void printParameters() {
        System.out.println("Game start-up parameters:");
        System.out.format("Enemies:\t%d\nWalls:\t\t%d\nsize:\t\t%d\nprofile:\t%s\n", this.enemiesCount,
                this.wallsCount, this.size, this.profile);
        System.out.println();
    }

    public boolean checkParameters() {
        return this.enemiesCount + this.wallsCount + 2 < this.size * this.size;
    }
}
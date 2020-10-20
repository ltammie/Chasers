package GameLogic;

public class Flush {

    public static void clearScreen(String status) throws InterruptedException {
        Thread.sleep(600);
        if ("development".equals(status)) {
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}

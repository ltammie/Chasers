package GameLogic;

import com.diogonunes.jcolor.Attribute;

public class Colors {
     public static Attribute getColor(String color) {
        switch (color) {
            case "BLACK":
                return Attribute.BRIGHT_BLACK_BACK();
            case "RED":
                return Attribute.BRIGHT_RED_BACK();
            case "GREEN":
                return Attribute.BRIGHT_GREEN_BACK();
            case "YELLOW":
                return Attribute.BRIGHT_YELLOW_BACK();
            case "BLUE":
                return Attribute.BRIGHT_BLUE_BACK();
            case "MAGENTA":
                return Attribute.BRIGHT_MAGENTA_BACK();
            case "CYAN":
                return Attribute.BRIGHT_CYAN_BACK();
            default:
                return Attribute.BRIGHT_WHITE_BACK();
        }
    }
}

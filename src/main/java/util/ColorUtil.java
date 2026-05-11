package util;

import javafx.scene.paint.Color;

public final class ColorUtil {
    private ColorUtil() {}

    public static Color lerp(Color a, Color b, double t) {
        t = Math.max(0, Math.min(1, t));
        return new Color(
                a.getRed()   + (b.getRed()   - a.getRed())   * t,
                a.getGreen() + (b.getGreen() - a.getGreen()) * t,
                a.getBlue()  + (b.getBlue()  - a.getBlue())  * t,
                a.getOpacity()+ (b.getOpacity()- a.getOpacity())* t
        );
    }
}
